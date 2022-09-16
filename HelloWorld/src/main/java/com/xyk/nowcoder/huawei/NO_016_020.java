package com.xyk.nowcoder.huawei;

import com.xyk.XykApi;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-24 11:08
 * @Description
 */
@Slf4j
public class NO_016_020 {
    /**
     * HJ16 购物单(动态规划)
     *
     * 描述
     * 王强决定把年终奖用于购物，他把想买的物品分为两类：主件与附件，附件是从属于某个主件的，下表就是一些主件与附件的例子：
     * 主件	附件
     * 电脑	打印机，扫描仪
     * 书柜	图书
     * 书桌	台灯，文具
     * 工作椅	无
     * 如果要买归类为附件的物品，必须先买该附件所属的主件，且每件物品只能购买一次。
     * 每个主件可以有 0 个、 1 个或 2 个附件。附件不再有从属于自己的附件。
     * 王强查到了每件物品的价格（都是 10 元的整数倍），而他只有 N 元的预算。除此之外，他给每件物品规定了一个重要度，用整数 1 ~ 5 表示。他希望在花费不超过 N 元的前提下，使自己的满意度达到最大。
     * 满意度是指所购买的每件物品的价格与重要度的乘积的总和，假设设第ii件物品的价格为v[i]v[i]，重要度为w[i]w[i]，共选中了kk件物品，编号依次为j_1,j_2,...,j_kj
     * 1
     * ​
     *  ,j
     * 2
     * ​
     *  ,...,j
     * k
     * ​
     *  ，则满意度为：v[j_1]*w[j_1]+v[j_2]*w[j_2]+ … +v[j_k]*w[j_k]v[j
     * 1
     * ​
     *  ]∗w[j
     * 1
     * ​
     *  ]+v[j
     * 2
     * ​
     *  ]∗w[j
     * 2
     * ​
     *  ]+…+v[j
     * k
     * ​
     *  ]∗w[j
     * k
     * ​
     *  ]。（其中 * 为乘号）
     * 请你帮助王强计算可获得的最大的满意度。
     *
     * 输入描述：
     * 输入的第 1 行，为两个正整数N，m，用一个空格隔开：
     * （其中 N （ N<32000 ）表示总钱数， m （m <60 ）为可购买的物品的个数。）
     *
     * 从第 2 行到第 m+1 行，第 j 行给出了编号为 j-1 的物品的基本数据，每行有 3 个非负整数 v p q
     * （其中 v 表示该物品的价格（ v<10000 ）， p 表示该物品的重要度（ 1 ~ 5 ）， q 表示该物品是主件还是附件。如果 q=0 ，表示该物品为主件，如果 q>0 ，表示该物品为附件， q 是所属主件的编号）
     *
     * 输出描述：
     *  输出一个正整数，为张强可以获得的最大的满意度。
     * 示例1
     * 输入：
     * 1000 5
     * 800 2 0
     * 400 5 1
     * 300 5 1
     * 400 3 0
     * 500 2 0
     * 复制
     * 输出：
     * 2200
     * 复制
     * 示例2
     * 输入：
     * 50 5
     * 20 3 5
     * 20 3 5
     * 10 3 0
     * 10 2 0
     * 10 1 0
     * 复制
     * 输出：
     * 130
     * 复制
     * 说明：
     * 由第1行可知总钱数N为50以及希望购买的物品个数m为5；
     * 第2和第3行的q为5，说明它们都是编号为5的物品的附件；
     * 第4~6行的q都为0，说明它们都是主件，它们的编号依次为3~5；
     * 所以物品的价格与重要度乘积的总和的最大值为10*1+20*3+20*3=130

     * */
    @Test
    public void Test_016() {
        Scanner scanner = new Scanner(System.in);
        int totalMoney = scanner.nextInt(); // 用户的总金额 除以10只是为了简化时间和空间复杂度,最后输出的时候再*10
        int itemNum = scanner.nextInt(); // 物品数量
        int[][] price = new int[itemNum+1][3];// 统计物品的价格(每个主件一行,附件跟着主件) 一个商品最多有两个附件,加上其本身,一行最多有三个物品
        int[][] agreeNum = new int[itemNum+1][3];// 用户的期待值(物品价格*期待度)
        for (int i = 1; i <= itemNum; i++){
            int p = scanner.nextInt(); // 价格 除以10只是为了简化时间和空间复杂度,最后输出的时候再*10
            int a = scanner.nextInt(); // 期待度
            int v = scanner.nextInt(); // 父件id

            if (v==0){// 主件
                price[i][0] = p;
                agreeNum[i][0] = p*a;
            }else {
                if (price[v][1] == 0){// 附件1为空就添加到附件1
                    price[v][1] = p;
                    agreeNum[v][1] = p*a;
                }else { // 附件1不为空就添加到附件2
                    price[v][2] = p;
                    agreeNum[v][2] = p*a;
                }
            }
        }
//        System.out.println("price");
//        XykApi.print2VArray(price);
//        System.out.println("agreeNum");
//        XykApi.print2VArray(agreeNum);

        int[] dp = new int[totalMoney + 1]; // 创建动态规划数据区
        for (int i= 1;i<=itemNum;i++){ // 遍历物品
            if (price[i][0]==0){// 输入时创建的无数据行,跳过
                continue;
            }
            for (int j = totalMoney;j >= price[i][0];j-- ){
                int p0 = price[i][0];// 主件价格
                int a0 = agreeNum[i][0]; // 主件期待度
                int p1 = price[i][1];// 附件1价格
                int a1 = agreeNum[i][1]; // 附件1期待度
                int p2 = price[i][2];// 附件2价格
                int a2 = agreeNum[i][2]; // 附件2待度
                if (j > p0){
                    dp[j] = Math.max( dp[j], dp[j-p0]+a0);
                }
                if (j > p0 + p1){
                    dp[j] = Math.max( dp[j], dp[j - p0 - p1]+ a0 +a1);
                }
                if (j > p0 + p2){
                    dp[j] = Math.max( dp[j], dp[j - p0 - p2]+ a0 +a2);
                }
                if (j > p0 + p1 + p2){
                    dp[j] = Math.max( dp[j], dp[j - p0 - p1- p2]+ a0 + a1 + a2);
                }
            }
        }
        System.out.println(dp[totalMoney]);


    }

    /**
     * HJ17 坐标移动
     *
     * 开发一个坐标计算工具， A表示向左移动，D表示向右移动，W表示向上移动，S表示向下移动。从（0,0）点开始移动，从输入字符串里面读取一些坐标，并将最终输入结果输出到输出文件里面。
     *
     * 输入：
     *
     * 合法坐标为A(或者D或者W或者S) + 数字（两位以内）
     *
     * 坐标之间以;分隔。
     *
     * 非法坐标点需要进行丢弃。如AA10;  A1A;  $%$;  YAD; 等。
     *
     * 下面是一个简单的例子 如：
     *
     * A10;S20;W10;D30;X;A1A;B10A11;;A10;
     *
     * 处理过程：
     *
     * 起点（0,0）
     *
     * +   A10   =  （-10,0）
     *
     * +   S20   =  (-10,-20)
     *
     * +   W10  =  (-10,-10)
     *
     * +   D30  =  (20,-10)
     *
     * +   x    =  无效
     *
     * +   A1A   =  无效
     *
     * +   B10A11   =  无效
     *
     * +  一个空 不影响
     *
     * +   A10  =  (10,-10)
     *
     * 结果 （10， -10）
     *
     * 数据范围：每组输入的字符串长度满足 1\le n \le 10000 \1≤n≤10000  ，坐标保证满足 -2^{31} \le x,y \le 2^{31}-1 \−2
     * 31
     *  ≤x,y≤2
     * 31
     *  −1  ，且数字部分仅含正数
     * 输入描述：
     * 一行字符串
     *
     * 输出描述：
     * 最终坐标，以逗号分隔
     *
     * 示例1
     * 输入：
     * A10;S20;W10;D30;X;A1A;B10A11;;A10;
     * 复制
     * 输出：
     * 10,-10
     * 复制
     * 示例2
     * 输入：
     * ABC;AKL;DA1;
     * 复制
     * 输出：
     * 0,0
     * */
    @Test
    public void test_017(){
        Scanner scanner = new Scanner(System.in);
        String[] inputArray = scanner.nextLine().split(";");
        int x = 0;
        int y = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (String str : inputArray){
            if (str.matches("^[WASD][0-9]{1,2}$")){
                // 方法一:
//                int value = Integer.parseInt(str.substring(1));
//                switch (str.charAt(0)){
//                    case 'W' :
//                        y+=value;
//                        break;
//                    case 'A' :
//                        x-=value;
//                        break;
//                    case 'S' :
//                        y-=value;
//                        break;
//                    case 'D' :
//                        x+=value;
//                        break;
//                }

//                方法二: 利用HashMap二次优化
                char c = str.charAt(0);
                int v = Integer.parseInt(str.substring(1));
                if (!map.containsKey(c)) {
                    map.put(c,v);
                }else {
                    map.put(c,map.get(c)+v);
                }

            }
        }
        x = x + map.get('D') - map.get('A');
        y = y + map.get('W') - map.get('S');
        System.out.println(x+","+y);


    }
    /**
     * HJ18 识别有效的IP地址和掩码并进行分类统计
     *
     * 描述
     * 请解析IP地址和对应的掩码，进行分类识别。要求按照A/B/C/D/E类地址归类，不合法的地址和掩码单独归类。
     *
     * 所有的IP地址划分为 A,B,C,D,E五类
     *
     * A类地址从1.0.0.0到126.255.255.255;
     *
     * B类地址从128.0.0.0到191.255.255.255;
     *
     * C类地址从192.0.0.0到223.255.255.255;
     *
     * D类地址从224.0.0.0到239.255.255.255；
     *
     * E类地址从240.0.0.0到255.255.255.255
     *
     *
     * 私网IP范围是：
     *
     * 从10.0.0.0到10.255.255.255
     *
     * 从172.16.0.0到172.31.255.255
     *
     * 从192.168.0.0到192.168.255.255
     *
     *
     * 子网掩码为二进制下前面是连续的1，然后全是0。（例如：255.255.255.32就是一个非法的掩码）
     * （注意二进制下全是1或者全是0均为非法子网掩码）
     *
     * 注意：
     * 1. 类似于【0.*.*.*】和【127.*.*.*】的IP地址不属于上述输入的任意一类，也不属于不合法ip地址，计数时请忽略
     * 2. 私有IP地址和A,B,C,D,E类地址是不冲突的
     *
     * 输入描述：
     * 多行字符串。每行一个IP地址和掩码，用~隔开。
     *
     * 请参考帖子https://www.nowcoder.com/discuss/276处理循环输入的问题。
     * 输出描述：
     * 统计A、B、C、D、E、错误IP地址或错误掩码、私有IP的个数，之间以空格隔开。
     *
     * 示例1
     * 输入：
     * 10.70.44.68~255.254.255.0
     * 1.0.0.1~255.0.0.0
     * 192.168.0.2~255.255.255.0
     * 19..0.~255.255.255.0
     * 复制
     * 输出：
     * 1 0 1 0 0 2 1
     * 复制
     * 说明：
     * 10.70.44.68~255.254.255.0的子网掩码非法，19..0.~255.255.255.0的IP地址非法，所以错误IP地址或错误掩码的计数为2；
     * 1.0.0.1~255.0.0.0是无误的A类地址；
     * 192.168.0.2~255.255.255.0是无误的C类地址且是私有IP；
     * 所以最终的结果为1 0 1 0 0 2 1
     * 示例2
     * 输入：
     * 0.201.56.50~255.255.111.255
     * 127.201.56.50~255.255.111.255
     * 复制
     * 输出：
     * 0 0 0 0 0 0 0
     * 复制
     * 说明：
     * 类似于【0.*.*.*】和【127.*.*.*】的IP地址不属于上述输入的任意一类，也不属于不合法ip地址，计数时请忽略
     *
     * */
    @Test
    public void test_018(){

        Integer invalidNum = 0;// 错误的IP/子网掩码个数统计
        Integer typeA = 0;// A类地址个数统计
        Integer typeB = 0;// B类地址个数统计
        Integer typeC = 0;// C类地址个数统计
        Integer typeD = 0;// D类地址个数统计
        Integer typeE = 0;// E类地址个数统计
        Integer typePrivate = 0;//私有地址个数统计

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String input = scanner.nextLine();
            int index = input.indexOf('~');
            String ip = input.substring(0, index); // ip
            String netmask = input.substring(index+1);
            try {
                String v = ip.substring(0, ip.indexOf('.'));
                if (v .equals("0")  ||  v.equals("127")){
                    continue;
                }
            }catch (Exception e){
                continue;
            }
//            System.out.println("ip="+ip);
//            System.out.println("netmask="+netmask);
            if ( (!isValidIP(ip) )
                    || (!isValidNetmask(netmask))){ // 判断ip是否非法,统计错误的ip个数
                invalidNum++;
            }else{ // 合法的ip则统计ip地址种类
                String[] split = ip.split("\\.");
                int v1 = Integer.parseInt(split[0]);
                int v2 = Integer.parseInt(split[1]);
                int v3 = Integer.parseInt(split[2]);
                int v4 = Integer.parseInt(split[3]);
                if (v1 == 10 // 统计私有ip
                        || (v1 == 172 && v2 >= 16 && v2 <= 31)
                        || (v1 == 192 && v2 == 168)){
                    typePrivate++;
                }

                if (1 <= v1 && v1 <= 126){
                    typeA++;
                }else if (128 <= v1 && v1 <= 191){
                    typeB++;
                }else if (192 <= v1 && v1 <= 223){
                    typeC++;
                } else if (224 <= v1 && v1 <= 239){
                    typeD++;
                } else if (240 <= v1 && v1 <= 255){
                    typeE++;
                }
            }
        }

        System.out.println(typeA+" "+typeB+" "+ typeC+" "+typeD+" "+typeE+" "+invalidNum+" "+typePrivate);

    }

    /**
     * 判断ip是否有效
     * */
    private Boolean isValidIP(String ip) {
        if (ip == null || ip.length() == 0) return false;
        String[] split = ip.split("\\.");
        if (split.length != 4 ) return false;
        try {
            int v1 = Integer.parseInt(split[0]);
            if (v1 < 0 || v1 > 255){
                return false;
            }
            for (int i=1;i<4;i++) {
                int value = Integer.parseInt(split[i]);
                if (value<0 || value>255){
                    return false;
                }
            }
        }catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 判断子网掩码是否有效
     * */
    private Boolean isValidNetmask(String netmask) {
        // 1.将netmask分割成数组,转为二进制,不足8位的补0 比较最后一个0和最后一个1的下标大小
        StringBuilder sb = new StringBuilder("");
        try {
            String[] split = netmask.split("\\.");
            for(String s : split) {
                String v = Integer.toBinaryString(Integer.parseInt(s));
                while(v.length()<8){
                    v = "0" + v;
                }
                sb.append(v);
            }

            int index0 = sb.indexOf("0"); // 第一个0 的下标应该大于 最后一个1 的下标 否则就是非法的
            int index1 = sb.lastIndexOf("1");
            if (index0 < index1){
                return false;
            }
        }catch (Exception e) {
            return false;
        }
        return true;
    }


    /**
     * HJ19 简单错误记录
     *
     * 描述
     * 开发一个简单错误记录功能小模块，能够记录出错的代码所在的文件名称和行号。
     *
     *
     * 处理：
     *
     *
     * 1、 记录最多8条错误记录，循环记录，最后只用输出最后出现的八条错误记录。对相同的错误记录只记录一条，但是错误计数增加。最后一个斜杠后面的带后缀名的部分（保留最后16位）和行号完全匹配的记录才做算是“相同”的错误记录。
     * 2、 超过16个字符的文件名称，只记录文件的最后有效16个字符；
     * 3、 输入的文件可能带路径，记录文件名称不能带路径。也就是说，哪怕不同路径下的文件，如果它们的名字的后16个字符相同，也被视为相同的错误记录
     * 4、循环记录时，只以第一次出现的顺序为准，后面重复的不会更新它的出现时间，仍以第一次为准
     *
     * 数据范围：错误记录数量满足 1 \le n \le 100 \1≤n≤100  ，每条记录长度满足 1 \le len \le 100 \1≤len≤100
     * 输入描述：
     * 每组只包含一个测试用例。一个测试用例包含一行或多行字符串。每行包括带路径文件名称，行号，以空格隔开。
     *
     * 输出描述：
     * 将所有的记录统计并将结果输出，格式：文件名 代码行数 数目，一个空格隔开，如：
     *
     * 示例1
     * 输入：
     * D:\zwtymj\xccb\ljj\cqzlyaszjvlsjmkwoqijggmybr 645
     * E:\je\rzuwnjvnuz 633
     * C:\km\tgjwpb\gy\atl 637
     * F:\weioj\hadd\connsh\rwyfvzsopsuiqjnr 647
     * E:\ns\mfwj\wqkoki\eez 648
     * D:\cfmwafhhgeyawnool 649
     * E:\czt\opwip\osnll\c 637
     * G:\nt\f 633
     * F:\fop\ywzqaop 631
     * F:\yay\jc\ywzqaop 631
     * D:\zwtymj\xccb\ljj\cqzlyaszjvlsjmkwoqijggmybr 645
     * 复制
     * 输出：
     * rzuwnjvnuz 633 1
     * atl 637 1
     * rwyfvzsopsuiqjnr 647 1
     * eez 648 1
     * fmwafhhgeyawnool 649 1
     * c 637 1
     * f 633 1
     * ywzqaop 631 2
     * 复制
     * 说明：
     * 由于D:\cfmwafhhgeyawnool 649的文件名长度超过了16个字符，达到了17，所以第一个字符'c'应该被忽略。
     * 记录F:\fop\ywzqaop 631和F:\yay\jc\ywzqaop 631由于文件名和行号相同，因此被视为同一个错误记录，哪怕它们的路径是不同的。
     * 由于循环记录时，只以第一次出现的顺序为准，后面重复的不会更新它的出现时间，仍以第一次为准，所以D:\zwtymj\xccb\ljj\cqzlyaszjvlsjmkwoqijggmybr 645不会被记录。
     * */

    @Test
    public void test_019(){

        Map< String ,Integer> map = new LinkedHashMap<>(); // 存储错误信息
        List<String> queue = new LinkedList<>(); // 存储显示顺序

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String err = scanner.nextLine();
//            if (err  == null || "".equals(err)){
//                break;
//            }
            int index = err.lastIndexOf("\\");
            String subStr = err.substring(index + 1);
            if (subStr.length()>16){
                subStr = subStr.substring(subStr.length()-16);
            }
            if (queue.size()>8){
                if (!queue.contains(subStr)){
                    queue.add(subStr);

                }
            }else {

            }



        }
        System.out.println("程序结束");


    }

    /**
     * HJ5 进制转换
     *
     * warning 校招时部分企业笔试将禁止编程题跳出页面，为提前适应，练习时请使用在线自测，而非本地IDE。
     * 描述
     * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
     *
     * 数据范围：保证结果在 1 \le n \le 2^{31}-1 \1≤n≤2
     * 31
     *  −1
     * 输入描述：
     * 输入一个十六进制的数值字符串。
     *
     * 输出描述：
     * 输出该数值的十进制字符串。不同组的测试用例用\n隔开。
     *
     * 示例1
     * 输入：
     * 0xAA
     * 复制
     * 输出：
     * 170
     * 复制
     * */
    @Test
    public void test_020(){

                String inputNumStr = "100";
        int i = Integer.parseInt(inputNumStr, 16);
        System.out.println(i);


//        String inputNumStr = "100";
//        String inputNumStr = new Scanner(System.in).nextLine();
//        HashMap<String, Integer> numMap = new HashMap<>();
//        numMap.put("0", 0);
//        numMap.put("1", 1);
//        numMap.put("2", 2);
//        numMap.put("3", 3);
//        numMap.put("4", 4);
//        numMap.put("5", 5);
//        numMap.put("6", 6);
//        numMap.put("7", 7);
//        numMap.put("8", 8);
//        numMap.put("9", 9);
//        numMap.put("a", 10);
//        numMap.put("b", 11);
//        numMap.put("c", 12);
//        numMap.put("d", 13);
//        numMap.put("e", 14);
//        numMap.put("f", 15);
//        numMap.put("A", 10);
//        numMap.put("B", 11);
//        numMap.put("C", 12);
//        numMap.put("D", 13);
//        numMap.put("E", 14);
//        numMap.put("F", 15);
//        StringBuilder reverse = new StringBuilder(inputNumStr).reverse();
//        char[] chars = reverse.toString().toCharArray();
//        Integer num = 0;
//        for (int i=0;i<chars.length;i++) {
//            Integer num1 = numMap.get(String.valueOf(chars[i]));
//            Integer num2 = getFullNum(i,16);
//            num+=num1*num2;
//        }
//        System.out.println(num);


    }



}
