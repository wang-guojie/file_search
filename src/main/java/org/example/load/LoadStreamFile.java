package org.example.load;


import lombok.Data;
import org.example.entity.GlobalData;
import org.example.util.ScannerUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 我们扫描好的文件，需要分序列化到对象中，快速搜索
 */
@Data
public class LoadStreamFile {

    private GlobalData globalData;

    public LoadStreamFile(String loadFile) {


        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(loadFile));
            this.globalData = (GlobalData) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * @param fileName 文件名字
     * @return 返回全局吧文件
     */
    public String search(String fileName) {
        // 进行全局搜索
        int charIndex = ScannerUtil.charIndex(fileName);
        HashMap<String, HashSet<Integer>> hashMap = this.globalData.getFileStructures().get(charIndex).getFileName();
        if (hashMap.containsKey(fileName)) {
            HashSet<Integer> hashSet = hashMap.get(fileName);
            Iterator<Integer> iterator = hashSet.iterator();
            StringBuilder sb = new StringBuilder();
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                String pathFileName = this.globalData.getPathFileName().get(next);
                sb.append(pathFileName).append(File.separator).append(fileName).append("\r\n");
            }
            return sb.toString();


        } else {
            // 判断是否有这个文件
            return "没有查找到此文件";
        }

    }


}
