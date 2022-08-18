import javax.swing.*;
import java.awt.*;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-07-13 12:52
 * @Description 测试窗体类---快速创建一个窗体
 * 注意：
 * ①：没有   setVisible(ture); 窗体不会显示。
 * ②：代码生成的窗体关闭按钮不能使程序关闭，需要使用组合键 ctrl+c  退出程序。
 */
public class MyFrame01 {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("我的窗体01");
        // 设置窗体大小
        jFrame.setSize(500,400);
//        设置窗体背景颜色
        jFrame.setBackground(Color.BLACK);
//        设置窗体位置
        jFrame.setLocation(300,200);
//        窗体是否可见(不设置为ture无法看到窗体)
        jFrame.setVisible(true);

        //注意生成的窗体关闭按钮不能使程序退出，需要使用ctrl+c组合件来退出

    }
}

