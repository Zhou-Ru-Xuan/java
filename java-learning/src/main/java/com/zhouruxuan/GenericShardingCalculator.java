package com.zhouruxuan;

import lombok.Data;
import lombok.Getter;

public class GenericShardingCalculator {

    private final int clusterCount;   // 集群数量 (x)
    private final int dbPerCluster;   // 每个集群的库数量 (y)
    private final int tablesPerDb;    // 每个库的表数量 (z)
    private final int totalDbs;       // 总库数 (x*y)

    public GenericShardingCalculator(int clusterCount, int dbPerCluster, int tablesPerDb) {
        if (clusterCount <= 0 || dbPerCluster <= 0 || tablesPerDb <= 0) {
            throw new IllegalArgumentException("所有分片参数必须大于0");
        }
        
        this.clusterCount = clusterCount;
        this.dbPerCluster = dbPerCluster;
        this.tablesPerDb = tablesPerDb;
        this.totalDbs = clusterCount * dbPerCluster;
    }

    /**
     * 计算商品的分片位置
     * @param goodsId 商品ID
     * @return 分片位置信息
     */
    public ShardLocation calculateShardLocation(long goodsId) {
        // 1. 计算全局库索引 (0 到 totalDbs-1)
        int globalDbIndex = (int) (Math.abs(goodsId) % totalDbs);
        
        // 2. 计算集群索引 (0 到 clusterCount-1)
        int clusterIndex = globalDbIndex / dbPerCluster;
        
        // 3. 计算集群内库索引 (0 到 dbPerCluster-1)
        int dbInCluster = globalDbIndex % dbPerCluster;
        
        // 4. 计算表索引 (0 到 tablesPerDb-1)
        int tableIndex = (int) ((Math.abs(goodsId) / totalDbs) % tablesPerDb);
        
        return new ShardLocation(clusterIndex, dbInCluster, tableIndex);
    }

    /**
     * 分片位置信息类
     */
    @Getter
    public static class ShardLocation {
        private final int clusterIndex;  // 集群索引
        private final int dbIndex;       // 集群内库索引
        private final int tableIndex;    // 表索引

        public ShardLocation(int clusterIndex, int dbIndex, int tableIndex) {
            this.clusterIndex = clusterIndex;
            this.dbIndex = dbIndex;
            this.tableIndex = tableIndex;
        }

        @Override
        public String toString() {
            return String.format("集群[%d] -> 库[%d] -> 表[%d]", clusterIndex, dbIndex, tableIndex);
        }
    }

    public static void main(String[] args) {
        // 示例配置：16集群，每集群16库，每库16表
        GenericShardingCalculator calculator = new GenericShardingCalculator(16, 16, 16);
        
        // 测试用例
        long[] testGoodsIds = {1199574172L};
        
        System.out.println("分片配置: " + calculator.getConfigDescription());
        for (long goodsId : testGoodsIds) {
            ShardLocation location = calculator.calculateShardLocation(goodsId);
            System.out.printf("goodsId: %-6d → %s%n", goodsId, location);
        }
        
        // 测试不同配置
        System.out.println("\n=== 测试不同配置 (2集群, 3库/集群, 4表/库) ===");
        GenericShardingCalculator customCalc = new GenericShardingCalculator(2, 3, 4);
        System.out.println("分片配置: " + customCalc.getConfigDescription());
        
        for (long goodsId = 0; goodsId < 10; goodsId++) {
            ShardLocation loc = customCalc.calculateShardLocation(goodsId);
            System.out.printf("goodsId: %d → %s%n", goodsId, loc);
        }
    }
    
    public String getConfigDescription() {
        return String.format("%d集群 × %d库/集群 × %d表/库 (总库数: %d)", 
            clusterCount, dbPerCluster, tablesPerDb, totalDbs);
    }
}
