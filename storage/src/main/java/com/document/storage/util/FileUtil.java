package com.document.storage.util;

import java.io.*;

/**
 * 文件操作工具类
 */
public class FileUtil {

    /**
     * 将内容写入文件
     * @param path 文件路径
     * @param text 文件内容
     * @param exist 文件是否已经存在，若不存在就先创建再写，若已存在就直接覆写修改，主要针对POST和PUT请求
     */
    public static void writeToFile(String path, String text, boolean exist) {
        File file = new File(path);
        FileWriter writer = null;
        BufferedWriter bufferedWriter = null;
        try {
            if (!exist) {
                if (file.createNewFile()) {
                    writer = new FileWriter(path, false);
                    bufferedWriter = new BufferedWriter(writer);
                    bufferedWriter.write(text);
                }
            } else {
                writer = new FileWriter(path, false);
                bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write(text);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从文件中读取内容
     * @param path 文件路径
     * @return 文件内容
     */
    public static String readFromFile(String path) {
        File file = new File(path);
        FileReader reader = null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (file.exists()) {
                String line;
                reader = new FileReader(file);
                bufferedReader = new BufferedReader(reader);
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new String(stringBuilder);
    }

}
