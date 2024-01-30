package com.example.springlearning.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.google.common.collect.Lists;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class EasyExcelUtils {

    /**
     * 从文件中读取数据
     *
     * @param file      文件对象
     * @param clazz     数据对象的类
     * @param excelType 文件类型枚举
     * @param <T>       数据对象的类型
     * @return 读取到的数据列表
     */
    public static <T> List<T> readFromFile(File file, Class<T> clazz, ExcelTypeEnum excelType) {
        return EasyExcel.read(file).head(clazz).excelType(excelType).sheet().doReadSync();
    }

    /**
     * 从输入流中读取数据
     *
     * @param inputStream 输入流
     * @param clazz       数据对象的类
     * @param excelType   文件类型枚举
     * @param <T>         数据对象的类型
     * @return 读取到的数据列表
     */
    public static <T> List<T> readFromInputStream(InputStream inputStream, Class<T> clazz, ExcelTypeEnum excelType) {
        return EasyExcel.read(inputStream).head(clazz).excelType(excelType).sheet().doReadSync();
    }

    /**
     * 将数据写入文件
     *
     * @param file      文件对象
     * @param dataList  数据列表
     * @param clazz     数据对象的类
     * @param excelType 文件类型枚举
     * @param <T>       数据对象的类型
     */
    public static <T> void writeToFile(File file, List<T> dataList, Class<T> clazz, ExcelTypeEnum excelType) {
        EasyExcel.write(file, clazz).excelType(excelType).sheet().doWrite(dataList);
    }

    /**
     * 将数据写入输出流
     *
     * @param outputStream 输出流
     * @param dataList     数据列表
     * @param clazz        数据对象的类
     * @param excelType    文件类型枚举
     * @param <T>          数据对象的类型
     */
    public static <T> void writeToOutputStream(OutputStream outputStream, List<T> dataList, Class<T> clazz, ExcelTypeEnum excelType) {
        EasyExcel.write(outputStream, clazz).head(clazz).excelType(excelType).sheet().doWrite(dataList);
    }

    /**
     * 将数据写入文件
     *
     * @param dataList 数据列表
     * @param clazz    数据对象的类
     * @param fileName 文件名
     * @param <T>      数据对象的类型
     */
    public static <T> void writeToExcel(List<T> dataList, Class<T> clazz, String fileName) {
        File file = new File(fileName);
        EasyExcel.write(file, clazz).head(clazz).sheet().doWrite(dataList);
    }

    /**
     * 将对象的部分字段写入文件
     *
     * @param dataList      数据列表
     * @param clazz         数据对象的类
     * @param fileName      文件名
     * @param includeFields 需要包含的字段名数组
     * @param headNames     列名称
     * @param <T>           数据对象的类型
     */
    public static <T> void writeToExcelWithExpectedField(List<T> dataList, Class<T> clazz, String fileName, Collection<String> includeFields, Set<String> headNames) {
        File file = new File(fileName);
        EasyExcel.write(file, clazz).head(generateHead(headNames)).includeColumnFieldNames(includeFields).sheet().doWrite(dataList);
    }

    /**
     * 生成表头
     *
     * @param headNames 用户自定义的表头
     * @return
     */
    private static List<List<String>> generateHead(Set<String> headNames) {
        List<List<String>> head = new ArrayList<>(headNames.size());
        for (String headName : headNames) {
            head.add(Lists.newArrayList(headName));
        }
        return head;
    }
}
