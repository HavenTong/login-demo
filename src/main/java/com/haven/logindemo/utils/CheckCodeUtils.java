package com.haven.logindemo.utils;

import java.util.Random;

/**
 * @author HavenTong
 * @date 2019/11/19 8:38 下午
 */
public class CheckCodeUtils {

    private static String seed = "0123456789";

    public static String getCheckCode(){
        Random random = new Random();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < 6; i++){
            buffer.append(seed.charAt(random.nextInt(10)));
        }
        return buffer.toString();
    }

}
