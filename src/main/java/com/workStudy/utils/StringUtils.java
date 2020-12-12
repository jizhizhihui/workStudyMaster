package com.workStudy.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StringUtils {

    /**
     * 分割字符串
     * @param rawer 字符串
     * @param spliter 分隔符
     * @return
     */
    public static String[] split(String rawer, String spliter) {
        //先拷贝一份，避免直接更改全局变量
        String rawerTemp = rawer;
        //容纳分割后的分隔符前的字符串
        List<String> list = new ArrayList<String>();

        while(rawerTemp.indexOf(spliter)!=-1) {
            //还存在spliter分割符的时候
            String temp = rawerTemp.substring(0, rawerTemp.indexOf(spliter));
            //将分隔符前的部分添加到list
            list.add(temp);
            //分割得到第一个分隔符后边的所有字符串
            rawerTemp = rawerTemp.substring(rawerTemp.indexOf(spliter)+spliter.length());
        }

        //如果不存在分割符则直接加入
        if(rawerTemp.length()!=0) {
            list.add(rawerTemp);
        }
        //生成数组并返回
        if(list.size()>0) {
            String[] arrays = new String[list.size()];
            for(int i=0;i<list.size();i++) {
                arrays[i] = list.get(i);
            }
            return arrays;
        }
        return new String[0];
    }

}
