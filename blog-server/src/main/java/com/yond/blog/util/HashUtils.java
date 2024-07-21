package com.yond.blog.util;

import cn.hutool.core.lang.hash.MurmurHash;

/**
 * @Description: Hash工具类
 * @Author: Naccl
 * @Date: 2020-11-17
 */
public class HashUtils {


    public static long getMurmurHash32(String str) {
        int i = MurmurHash.hash32(str);
        long num = i < 0 ? Integer.MAX_VALUE - (long) i : i;
        return num;
    }


}
