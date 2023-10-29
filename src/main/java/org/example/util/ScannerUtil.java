package org.example.util;

public class ScannerUtil {

    /**
     * @param fileName 根据文件名字进行搜索对应的下表位置
     * @return 返回对应的下表位置
     */
    public static int charIndex(String fileName) {
        // 通过fileName进行计算
        char c = fileName.charAt(0);
        // 进行计算存放的位置在哪里
        // 计算的下表
        int index;
        if (c >= 65 && c <= 90 || c >= 97 && c <= 122) {
            // 在进行判断
            c = c >= 97 ? (char) (c - 32) : c; // 表示这个是一个小写字母，需要进行转换大写字母
            index = c - 65; // -65 表示我们初始化的时候是从0开始初始化的  A 表示下表0
        } else {
            index = 26;
        }
        return index;
    }


}
