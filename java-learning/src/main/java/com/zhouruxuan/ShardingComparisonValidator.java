package com.zhouruxuan;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiFunction;

/**
 * 分库分表验证逻辑
 */
public class ShardingComparisonValidator {

    // 分片策略接口
    interface ShardingStrategy {
        int[] calculateShard(long goodsId);
    }

    // 配置参数
    private static final int DB_COUNT = 4;
    private static final int TABLE_COUNT_PER_DB = 32;
    private static final long TOTAL_GOODS = 2_000_000_000L; // 20亿商品

    // 统计数据结构
    private static final AtomicLong[] dbCounts = new AtomicLong[DB_COUNT];
    private static final ConcurrentHashMap<String, AtomicLong> tableCounts = new ConcurrentHashMap<>();

    // 当前验证的策略
    private static ShardingStrategy currentStrategy;

    static {
        // 初始化统计器
        for (int i = 0; i < DB_COUNT; i++) {
            dbCounts[i] = new AtomicLong(0);
            for (int j = 0; j < TABLE_COUNT_PER_DB; j++) {
                String tableKey = "db_" + i + "_table_" + j;
                tableCounts.put(tableKey, new AtomicLong(0));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 验证问题方案
        System.out.println("==================== 验证问题方案 ====================");
        currentStrategy = (goodsId) -> {
            int dbIndex = (int) (goodsId % DB_COUNT);
            int tableIndex = (int) (goodsId % TABLE_COUNT_PER_DB);
            return new int[]{dbIndex, tableIndex};
        };
        validateShardingStrategy("goodsId%4分库 + goodsId%32分表");

        // 重置统计器
        resetCounters();

        // 验证正确方案
        System.out.println("\n\n==================== 验证正确方案 ====================");
        currentStrategy = (goodsId) -> {
            int dbIndex = (int) (goodsId % DB_COUNT);
            long tableIndex = (goodsId >>> 2) % TABLE_COUNT_PER_DB; // (goodsId/4)%32
            return new int[]{dbIndex, (int) tableIndex};
        };
        validateShardingStrategy("goodsId%4分库 + (goodsId/4)%32分表");
    }

    private static void resetCounters() {
        // 重置库统计
        for (int i = 0; i < DB_COUNT; i++) {
            dbCounts[i].set(0);
        }

        // 重置表统计
        for (int db = 0; db < DB_COUNT; db++) {
            for (int table = 0; table < TABLE_COUNT_PER_DB; table++) {
                String tableKey = "db_" + db + "_table_" + table;
                tableCounts.get(tableKey).set(0);
            }
        }
    }

    private static void validateShardingStrategy(String strategyName) throws InterruptedException {
        System.out.println("验证策略: " + strategyName);
        System.out.println("配置: " + DB_COUNT + "库 × " + TABLE_COUNT_PER_DB + "表 = " +
                (DB_COUNT * TABLE_COUNT_PER_DB) + "张表");
        System.out.println("数据量: " + String.format("%,d", TOTAL_GOODS) + " 条商品记录");

        final int threadCount = Runtime.getRuntime().availableProcessors();
        final long goodsPerThread = TOTAL_GOODS / threadCount;
        System.out.println("使用线程数: " + threadCount);

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        long startTime = System.currentTimeMillis();

        // 启动验证线程
        for (int i = 0; i < threadCount; i++) {
            final long start = i * goodsPerThread;
            final long end = (i == threadCount - 1) ? TOTAL_GOODS : (i + 1) * goodsPerThread;

            executor.submit(() -> validateRange(start, end));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);

        long duration = System.currentTimeMillis() - startTime;
        System.out.println("\n验证完成! 耗时: " + duration + "ms");

        // 打印统计结果
        printDistributionReport();
    }

    private static void validateRange(long start, long end) {
        for (long goodsId = start; goodsId < end; goodsId++) {
            int[] shard = currentStrategy.calculateShard(goodsId);
            int dbIndex = shard[0];
            int tableIndex = shard[1];

            dbCounts[dbIndex].incrementAndGet();
            String tableKey = "db_" + dbIndex + "_table_" + tableIndex;
            tableCounts.get(tableKey).incrementAndGet();
        }
    }

    private static void printDistributionReport() {
        // 1. 库级分布
        System.out.println("\n========== 库级分布 ==========");
        long expectedPerDB = TOTAL_GOODS / DB_COUNT;
        System.out.println("理论每库数据量: " + String.format("%,d", expectedPerDB));

        for (int i = 0; i < DB_COUNT; i++) {
            long actual = dbCounts[i].get();
            double deviation = (actual - expectedPerDB) * 100.0 / expectedPerDB;
            System.out.printf("库 %d: %,d 条 (偏差: %.6f%%)%n",
                    i, actual, deviation);
        }

        // 2. 表级分布
        System.out.println("\n========== 表级分布 ==========");
        long expectedPerTable = TOTAL_GOODS / (DB_COUNT * TABLE_COUNT_PER_DB);
        System.out.println("理论每表数据量: " + String.format("%,d", expectedPerTable));

        // 统计表级数据
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        long total = 0;
        int emptyTables = 0;
        int tableCounter = 0;

        for (int db = 0; db < DB_COUNT; db++) {
            for (int table = 0; table < TABLE_COUNT_PER_DB; table++) {
                String key = "db_" + db + "_table_" + table;
                long count = tableCounts.get(key).get();

                min = Math.min(min, count);
                max = Math.max(max, count);
                total += count;
                tableCounter++;

                if (count == 0) emptyTables++;
            }
        }

        // 3. 分布统计摘要
        System.out.println("\n========== 分布统计摘要 ==========");
        System.out.println("总表数量: " + tableCounter);
        System.out.println("最小表数据量: " + String.format("%,d", min));
        System.out.println("最大表数据量: " + String.format("%,d", max));
        System.out.println("平均表数据量: " + String.format("%,d", total / tableCounter));
        System.out.println("空闲表数量: " + emptyTables + " (占比: " +
                String.format("%.2f", emptyTables * 100.0 / tableCounter) + "%)");

        double deviationRange = (max - min) * 100.0 / expectedPerTable;
        System.out.printf("最大偏差范围: %.2f%%%n", deviationRange);

        // 4. 热点分析
        System.out.println("\n========== 热点分析 ==========");
        if (min == 0) {
            System.out.println("❌ 存在空闲表: " + emptyTables + " 张");
        }

        if (max > 1.5 * expectedPerTable) {
            System.out.printf("❌ 存在热点表: 最大表数据量是理论值的 %.2f 倍%n",
                    (double) max / expectedPerTable);
        }
    }
}
