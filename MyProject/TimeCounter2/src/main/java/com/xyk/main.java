package com.xyk;

import java.util.Scanner;

/**
 * @author 徐亚奎
 * @date 2021-09-05 23:46
 * @Description 主启动类
 */
public class main {
    public static void main(String[] args) {
        String s=null;
        do {
            Boolean success = true;
            while (success){
                success = TimeCounter.main();
                if (success){
                    System.out.println("-----------");
                    System.out.println("是否重新/继续?  1/enter: 是     0/其他: 否 ");
                    System.out.println("-----------");
                    s = new Scanner(System.in).nextLine();
                    if (s!="1" && !s.isEmpty()){
                        break;
                    }
                }else {
                    s = "1";
                }

            }
        }while ("1".equals(s));
        System.out.println("程序已终止!");
    }
}
