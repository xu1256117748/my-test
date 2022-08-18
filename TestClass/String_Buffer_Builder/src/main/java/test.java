/**
 * @author 徐亚奎
 * @date 21/07/2021 17:02
 */
public class test {
    public static void main(String[] args) {
//      证明string是被final修饰的不可变的-->通过replace方法,然后比较
        String s1="hello";
        String s2="hello";
//      s1==s2表明,String存放于常量池
        System.out.println(s1==s2);
//      对s2进行字符串拼接,发现地址发生变化,证明无法通过拼接的方式进行改变值
        String s3=s2+"";
        System.out.println("s3==s2 :"+(s3==s2));
//      通过字符串s2的方法对其值进行"修改",并将修改后的对象的地址值交给s3保存,通过==比较s2和s3的地址值,发现其在底层创建了新的String对象,原值也是没有发生变化
        String s4=s2.replace("", "");
        System.out.println(s2==s4);
        System.out.println(s2.equals(s4));


    }
//
}
