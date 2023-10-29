package org.example;

import org.example.load.LoadStreamFile;

import java.util.Scanner;

/**
 * 此系统适用于Window操作系统
 */
public class App {
    public static void main(String[] args) {


        String streamFilePath = "D:\\streamFile.pem";

//        GlobalData globalData = new GlobalData();
//        ScannerFile scannerFile = new ScannerFile("D:/", globalData, streamFilePath);
//        scannerFile.loadLocalFile();

        long currentTimeMillis = System.currentTimeMillis();
        LoadStreamFile loadStreamFile = new LoadStreamFile(streamFilePath);
        long endCurrentTimeMillis = System.currentTimeMillis();
        System.out.println("加载完成" + (endCurrentTimeMillis - currentTimeMillis) / 1000 + "S");
        Scanner scanner = new Scanner(System.in);
        //
        while (true) {
            String next = scanner.next();
            currentTimeMillis = System.currentTimeMillis();
            String search = loadStreamFile.search(next);
            endCurrentTimeMillis = System.currentTimeMillis();
            System.out.println("搜索完成" + (endCurrentTimeMillis - currentTimeMillis) + "ms");

            System.out.println(search);
        }
    }
}
