package com.pcocoon.provider.tools;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description:[TODO]</p>
 * Created on: 2020/9/21 11:11
 *
 * @author: <a href="mailto: Siolon_Ren@foxmail.com">Siolon_Ren</a>
 * version: 1.0
 * Copyright (c) 2019 timedu.com.cn
 */
public class ErrorTools {

    /**
     * 随机生成错误
     */
    @SneakyThrows
    public static void generateError() {

        switch (new Random().nextInt(6)) {
            case 1:
                //空指针
                Integer a = null;
                a.byteValue();
                break;
            case 2:
                //数组下标越界
                int[] b = new int[0];
                System.out.println(b[10]);
                break;
            case 3:
                //运行时异常
                throw new RuntimeException("看我作甚..");
            case 4:
                //睡死
                TimeUnit.SECONDS.sleep(100);
                break;
            case 5:
                //溢栈
                overStack(1);
                break;
            default:
                System.out.println("好运！");

        }


    }


    private static void overStack(int amount) {
        amount++;
        overStack(amount);
    }

}
