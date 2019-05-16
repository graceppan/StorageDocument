package com.document.storage.util;

import java.io.*;

/**
 * File Util class
 */
public class FileUtil {

    /**
     * write context in file
     * @param path: file path
     * @param text: context
     * @param exist: check file exists or not. If not, then create first. If file exists, update context.
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
     * read context from file
     * @param path: file path
     * @return: file context
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
