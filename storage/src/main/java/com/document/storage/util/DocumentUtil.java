package com.document.storage.util;

import java.util.Random;

public class DocumentUtil {


    private static String[] chars = new String[] { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };

    /**
     * Randomly generate alphanumeric document ID with a length of 20 characters
     * @return a string with 20 characters in length
     */
    public static String getUuid() {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            builder.append(chars[random.nextInt(35)]);
        }
        return builder.toString();
    }

}
