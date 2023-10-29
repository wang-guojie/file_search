package org.example.entity;

import lombok.Data;
import org.example.util.ScannerUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


/**
 * 全局定义一份，系统的配置
 * 但是我们还是需要实例化和初始化
 * 我们不设置成static，容易拓展其他盘符
 */
@Data
public class GlobalData implements Serializable {
    // 这个是进行保存文件路径的名字的,全局一份
    private List<String> pathFileName;
    private Integer index = 0; // 表示到存储到哪里了,全局的，计数的
    // 初始化26个字母的集合并保存
    private List<FileStructure> fileStructures;

    public GlobalData() {
        this.pathFileName = new ArrayList<>();
        this.fileStructures = new ArrayList<>();
        // 进行初始化26个a-z
        for (int i = 0; i < 26; i++) {
            FileStructure fileStructure = new FileStructure();
            fileStructure.setKey(String.valueOf((char) (i + 65)));
            fileStructure.setFileName(new HashMap<>());
            fileStructures.add(fileStructure);
        }
        // 这个保存其他开头的内容
        FileStructure fileStructure = new FileStructure();
        fileStructure.setKey("other");
        fileStructure.setFileName(new HashMap<>());
        fileStructures.add(fileStructure);

    }

    /**
     * 进行添加路径并且返回对应的下表
     *
     * @param path 文件路径
     * @return 返回list的下表，方便查找对应的路径
     */
    public int add(String path) {
        pathFileName.add(path);
        return index++;
    }


    /**
     * 进行添加文件，通过第一个字母进行添加
     *
     * @param fileName 文件名字
     * @param index    对应的文件路径
     */
    public void addFileItem(String fileName, int index) {

        // TODO 文件是是一个链表，如果重复了，则继续向下添加
        int charIndex = ScannerUtil.charIndex(fileName);
        HashMap<String, HashSet<Integer>> hashMap = fileStructures.get(charIndex).getFileName();

        if (hashMap.containsKey(fileName)) {
            HashSet<Integer> hashSet = hashMap.get(fileName);
            hashSet.add(index);
        } else {
            HashSet<Integer> hashSet = new HashSet<>();
            hashSet.add(index);
            hashMap.put(fileName, hashSet);
        }

    }


}
