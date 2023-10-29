package org.example.search;

import lombok.Data;
import org.example.entity.FileStructure;
import org.example.entity.GlobalData;
import org.example.error.InvalidError;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 进行文件扫描操作
 */
@Data
public class ScannerFile {


    // 加载分配的对象
    List<FileStructure> fileStructures;
    private String streamFilePath; // 序列号的路径
    private String localHref;
    private GlobalData globalData;


    /**
     * 初始化扫描的盘符
     *
     * @param localHref      传入的盘符
     * @param globalData     这个类是一个整体配置
     * @param streamFilePath 表示加载的结果
     */
    public ScannerFile(String localHref, GlobalData globalData, String streamFilePath) {
        this.localHref = localHref;
        this.globalData = globalData;
        this.fileStructures = globalData.getFileStructures();
        this.streamFilePath = streamFilePath;
    }

    /**
     * 进行初始化本地文件，然后保存到本地
     * 这个需要写一个递归进行一直进行递归回调测试
     */
    public void loadLocalFile() {
        File initFileName = new File(localHref);
        // 进行判断是否有这个盘符
        if (!initFileName.exists()) {
            throw new InvalidError("不是有效盘符请重新输入");
        }

        // 初始化换成没有文件进行递归加载
        long currentTimeMillis = System.currentTimeMillis();
        this.recursion(initFileName);
        long endCurrentTimeMillis = System.currentTimeMillis();
        System.out.println("初始化完成" + (endCurrentTimeMillis - currentTimeMillis) / 1000 + "S");
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(streamFilePath));
            objectOutputStream.writeObject(globalData);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 序列化完成

    }

    /**
     * 一直递归的加载文件
     *
     * @param path 路径
     */
    public void recursion(File path) {

        File[] files = path.listFiles();
        if (files != null) {
            // 获取当前的父路径，然后保存普通文件
            String absolutePath = path.getPath();
            // 表示这个文件夹下面没有内容了
            int add = globalData.add(absolutePath);

            List<File> item = new ArrayList<>();
            for (File file : files) {
                // 获取当前文件下面的目录文件,判断是否是文件夹
                if (file.isDirectory()) {
                    // 不能当前进行调用
                    item.add(file);
                } else {
                    // 表示当前是文件，需要进行保存操作
                    String name = file.getName();
                    globalData.addFileItem(name, add);
                }
            }
            // 保存完后之后在进行调用
            for (File file : item) {
                this.recursion(file);
            }
        }


    }

}
