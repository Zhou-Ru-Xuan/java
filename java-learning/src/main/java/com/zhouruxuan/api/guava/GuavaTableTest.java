package com.zhouruxuan.api.guava;

import com.google.common.collect.*;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class GuavaTableTest {

    // ================== 基础操作测试 ==================
    
    @Test
    public void testBasicOperations() {
        // 创建 Table (行: 部门, 列: 年份, 值: 销售额)
        Table<String, String, Integer> salesTable = HashBasedTable.create();
        
        // 添加数据
        salesTable.put("研发部", "2022", 500000);
        salesTable.put("研发部", "2023", 600000);
        salesTable.put("市场部", "2022", 300000);
        salesTable.put("市场部", "2023", 450000);
        
        // 验证基础操作
        assertEquals(4, salesTable.size());
        assertTrue(salesTable.contains("研发部", "2023"));
        assertFalse(salesTable.contains("财务部", "2022"));
        assertEquals(Integer.valueOf(450000), salesTable.get("市场部", "2023"));
        
        // 移除数据
        Integer removed = salesTable.remove("研发部", "2022");
        assertEquals(Integer.valueOf(500000), removed);
        assertEquals(3, salesTable.size());
    }

    // ================== 行操作测试 ==================
    
    @Test
    public void testRowOperations() {
        Table<String, String, Double> temperatureTable = HashBasedTable.create();
        temperatureTable.put("北京", "1月", -5.2);
        temperatureTable.put("北京", "7月", 32.5);
        temperatureTable.put("上海", "1月", 3.8);
        temperatureTable.put("上海", "7月", 35.1);
        
        // 获取单行数据
        Map<String, Double> beijingTemps = temperatureTable.row("北京");
        assertEquals(2, beijingTemps.size());
        assertEquals(32.5, beijingTemps.get("7月"), 0.001);
        
        // 获取行键集合
        assertEquals(ImmutableSet.of("北京", "上海"), temperatureTable.rowKeySet());
        
        // 行视图
        Map<String, Map<String, Double>> rowMap = temperatureTable.rowMap();
        assertEquals(2, rowMap.get("上海").size());
    }

    // ================== 列操作测试 ==================
    
    @Test
    public void testColumnOperations() {
        Table<Integer, String, Boolean> taskTable = HashBasedTable.create();
        taskTable.put(1, "任务A", true);
        taskTable.put(1, "任务B", false);
        taskTable.put(2, "任务A", true);
        taskTable.put(2, "任务C", true);
        
        // 获取单列数据
        Map<Integer, Boolean> taskAStatus = taskTable.column("任务A");
        assertEquals(2, taskAStatus.size());
        assertTrue(taskAStatus.get(1));
        assertTrue(taskAStatus.get(2));
        
        // 获取列键集合
        assertEquals(ImmutableSet.of("任务A", "任务B", "任务C"), taskTable.columnKeySet());
        
        // 列视图
        Map<String, Map<Integer, Boolean>> columnMap = taskTable.columnMap();
        assertEquals(2, columnMap.get("任务A").size());
    }

    // ================== 单元格操作测试 ==================
    
    @Test
    public void testCellOperations() {
        Table<String, String, String> chessBoard = HashBasedTable.create();
        chessBoard.put("A", "1", "白车");
        chessBoard.put("E", "1", "白王");
        chessBoard.put("D", "8", "黑后");
        
        // 获取单元格集合
        Set<Table.Cell<String, String, String>> cells = chessBoard.cellSet();
        assertEquals(3, cells.size());
        
        // 遍历单元格
        for (Table.Cell<String, String, String> cell : cells) {
            String position = cell.getRowKey() + cell.getColumnKey();
            System.out.println(position + ": " + cell.getValue());
        }
        
        // 验证特定单元格
        assertTrue(cells.contains(Tables.immutableCell("D", "8", "黑后")));
    }

    // ================== 不可变Table测试 ==================
    
    @Test
    public void testImmutableTable() {
        // 构建不可变Table
        ImmutableTable<String, String, Integer> immutableTable = ImmutableTable.<String, String, Integer>builder()
            .put("苹果", "红色", 10)
            .put("苹果", "绿色", 5)
            .put("香蕉", "黄色", 15)
            .put("葡萄", "紫色", 8)
            .build();
        
        // 验证数据
        assertEquals(4, immutableTable.size());
        assertEquals(Integer.valueOf(15), immutableTable.get("香蕉", "黄色"));
        
        // 尝试修改 (应抛出异常)
        try {
            immutableTable.put("橙子", "橙色", 12);
            fail("Should throw UnsupportedOperationException");
        } catch (UnsupportedOperationException expected) {
            // 预期异常
        }
    }

    // ================== 表格转换测试 ==================
    
    @Test
    public void testTableTransformation() {
        Table<String, String, Integer> original = HashBasedTable.create();
        original.put("A", "X", 10);
        original.put("A", "Y", 20);
        original.put("B", "X", 30);
        
        // 1. 转换值
        Table<String, String, String> transformed = Tables.transformValues(original, 
            value -> "值:" + value
        );
        
        assertEquals("值:20", transformed.get("A", "Y"));
        
        // 2. 转置表格 (行列互换)
        Table<String, String, Integer> transposed = Tables.transpose(original);
        assertEquals(Integer.valueOf(10), transposed.get("X", "A"));
        assertEquals(Integer.valueOf(30), transposed.get("X", "B"));
    }

    // ================== TreeBasedTable 排序测试 ==================
    
    @Test
    public void testSortedTable() {
        // 创建排序表格 (行和列都自然排序)
        TreeBasedTable<String, String, Integer> sortedTable = TreeBasedTable.create();
        sortedTable.put("Chicago", "2023-03", 120);
        sortedTable.put("Boston", "2023-01", 95);
        sortedTable.put("Atlanta", "2023-02", 110);
        sortedTable.put("Boston", "2023-03", 105);
        
        // 验证行顺序
        assertEquals(
            ImmutableList.of("Atlanta", "Boston", "Chicago"), 
            ImmutableList.copyOf(sortedTable.rowKeySet())
        );
        
        // 验证列顺序
        assertEquals(
            ImmutableList.of("2023-01", "2023-02", "2023-03"), 
            ImmutableList.copyOf(sortedTable.columnKeySet())
        );
        
        // 获取Boston行 (自动按列排序)
        Map<String, Integer> bostonRow = sortedTable.row("Boston");
        assertEquals(
            ImmutableMap.of(
                "2023-01", 95,
                "2023-03", 105
            ),
            bostonRow
        );
    }

    // ================== 自定义Table实现测试 ==================
    
    @Test
    public void testCustomTableImplementation() {
        // 创建基于ArrayTable的固定大小表格
        ArrayTable<String, String, Double> fixedTable = ArrayTable.create(
            ImmutableList.of("学生A", "学生B"),
            ImmutableList.of("数学", "英语")
        );
        
        // 填充数据
        fixedTable.put("学生A", "数学", 90.5);
        fixedTable.put("学生A", "英语", 85.0);
        fixedTable.put("学生B", "数学", 78.5);
        fixedTable.put("学生B", "英语", 92.0);
        
        // 验证数据
        assertEquals(4, fixedTable.size());
        assertEquals(85.0, fixedTable.get("学生A", "英语"), 0.01);
        
        // 尝试添加新行 (应失败)
        try {
            fixedTable.put("学生C", "数学", 88.0);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException expected) {
            // 预期异常
        }
    }
}
