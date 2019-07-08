package com.knowledge_network.support.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * Created by pentonbin on 17-12-2.
 */
public class IOUtils {

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从HttpServletRequest中读取数据
     *
     * @param request HttpServletRequest请求
     * @return 获取请求中的数据
     */
    public static String readDataFromHttpServletRequest(HttpServletRequest request) {

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Read data from HttpServletRequest error:" + e.getMessage());
        } finally {
            close(reader);
        }
        return sb.toString();
    }

    /**
     * 从文件中获取内容的字节刘
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static byte[] readDataFromFile(File file) throws IOException {
        if (file == null || !file.exists() || file.isDirectory()) {
            return null;
        }
        ByteArrayOutputStream baos = null;
        FileInputStream fis = null;
        byte[] buff = new byte[1024];
        int len = 0;
        try {
            baos = new ByteArrayOutputStream();
            fis = new FileInputStream(file);
            while ((len = fis.read(buff, 0, 1024)) != -1) {
                baos.write(buff, 0, len);
            }
            return baos.toByteArray();
        } finally {
            close(fis);
            close(baos);
        }
    }
}
