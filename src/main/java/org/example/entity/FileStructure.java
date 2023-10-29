package org.example.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

@Data
public class FileStructure implements Serializable {

    // 这个表示是什么开头的文件，帮助我们快速查找
    private String key;

    // 这个是核心的文件名字，如果是A都会被分配到这个里面的，帮助我们快速定文件
    // 一个文件对应一个文件路径下表
    // 但是这样就会有另一种可能性发生，相同的文件名，就会引起覆盖
    // 我们使用HashSet<Integer>这个可以防止重复
    // TODO 没有更换ListArray进行尝试
    private HashMap<String, HashSet<Integer>> fileName;


}
