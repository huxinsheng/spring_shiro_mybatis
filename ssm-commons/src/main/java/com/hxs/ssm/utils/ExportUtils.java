package com.hxs.ssm.utils;

import com.alibaba.fastjson.JSONArray;
import com.hxs.ssm.utils.export.ExportExcel;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @项目名称： hxs-mp
 * @类名称： ExportUtils
 * @类描述：
 * @创建人： huxinsheng
 * @创建时间： 2016-11-8 14:55
 * @联系方式： hxsysh@gmail.com
 * @修改备注：
 * @Version v1.0
 */
public class ExportUtils {
    /**
     * 导入excel保存到服务器上，提供下载地址
     *
     * @param fileName 文件名称
     * @param filePath 文件路径
     * @param headMap  表头
     * @param ja       数据对象
     * @param response
     * @return
     * @throws Exception
     */
    public static String exportToExcelServerFile(String fileName, String filePath, Map<String, String> headMap, JSONArray ja, HttpServletResponse response) throws Exception {
        try {
            File orderFile = new File(filePath);
            // 写文件
            if (!orderFile.exists()) {
                orderFile.mkdirs();// 创建目录
            }
            String downloadFile = filePath + fileName + ".xlxs";
            File file = new File(downloadFile);
            if (!file.exists())
                file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);
            ExportExcel.exportExcelX(fileName, headMap, ja, null, 0, outputStream);
            outputStream.flush();
            outputStream.close();
            return downloadFile;
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    /**
     * 导出excel到数据流
     *
     * @param fileName 文件名
     * @param headMap  表头
     * @param ja       数据对象
     * @param response
     * @throws Exception
     */
    public static void exportToExcelStream(String fileName, Map<String, String> headMap, JSONArray ja, HttpServletResponse response) throws Exception {
        try {
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8") + ".xlsx");// 设定输出文件头
            response.setHeader("name", java.net.URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setCharacterEncoding("UTF-8");

            ServletOutputStream outputStream = response.getOutputStream();
            ExportExcel.exportExcelX(fileName, headMap, ja, null, 0, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出excel到数据流
     *
     * @param fileName 文件名
     * @param headMap  表头
     * @param ja       数据对象
     * @param response
     * @throws Exception
     */
    public static void exportToExcelStream(String fileName, Map<String, String> headMap, Map<String, String> countField, JSONArray ja, HttpServletResponse response) throws Exception {
        try {
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8") + ".xlsx");// 设定输出文件头
            response.setHeader("name", java.net.URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setCharacterEncoding("UTF-8");

            ServletOutputStream outputStream = response.getOutputStream();
            ExportExcel.exportExcelX(fileName, headMap, ja, countField, null, 0, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
