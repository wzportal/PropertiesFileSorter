package com.wzportal.idea.tool.util;

public class StringUtil {

    public static String sort(String[] strArr) {
        if (null == strArr || strArr.length == 0) {
            return "";
        }
        if (strArr.length == 1) {
            return strArr[0];
        }
        int total = strArr.length;
        int startLength = 0;
        int i = 0;
        while (i < total) {

            // 使用这个str 冒泡排序，与后面的所有字符串比较，如果比后面的大，那就向后移动，
            // 执行第一遍的结果是，全部最大的一个，放到了最后，此时总长度减一，再从第一个开始，重复上述步骤
            for (int idx = i; idx < total; idx++) {
                // 空行 #号开头 与后续第一个有效的Key值一起排序
                if (strArr[idx] == null || strArr[idx].trim().isEmpty() || strArr[idx].trim().startsWith("#")) {
                    startLength++;
                    continue;
                }
                int endLength = 0;
                for (int j = idx + 1; j < total; j++) {
                    if (strArr[j] == null || strArr[j].trim().isEmpty() || strArr[j].trim().startsWith("#")) {
                        endLength++;
                        continue;
                    }
                    if (strArr[idx].compareTo(strArr[j]) > 0) {
                        rank(strArr, idx - startLength, j, endLength + 1);
                        idx = idx + endLength + 1;
                    }
                    endLength = 0;
                }
                startLength = 0;
            }
            total--;
            i = 0;
        }
        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < strArr.length; idx++) {
            sb.append(strArr[idx]);
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * 将后面的多行当成一组，一起插入到指定索引位置
     * 一组需要一行一行的单独移动
     * 那么就需要将每一行之前的所有行都往后移动，直到指定索引位置为止
     *
     * @param strArr
     * @param start
     * @param end
     * @param size
     * @return
     */
    private static String[] rank(String[] strArr, int start, int end, int size) {
        for (int movedCount = 0; movedCount < size; movedCount++) {
            String temp = strArr[end];
            for (int i = end; i > start; i--) {
                strArr[i] = strArr[i - 1];
            }
            strArr[start] = temp;
        }
        return strArr;
    }
}
