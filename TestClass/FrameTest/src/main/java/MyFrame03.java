import javax.swing.*;
import java.awt.*;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-07-13 12:52
 * @Description 测试窗体类--设置内容字体
 */
public class MyFrame03 {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("我的窗体02");

        //       设置字体
//              设置字体内容及其对齐方式
        String str = "我的文本!";
        JLabel jLabel = new JLabel( str, JLabel.CENTER);
//              设置字体样式  ( Serief/批量 ITALIC/斜体 BOLD/粗体 字体大小/28 )
        Font font = new Font("楷体", Font.ITALIC + Font.BOLD, 28);
        jLabel.setFont( font );
        jFrame.add(jLabel);




//        使用Dimension类设置窗体大小
        Dimension dimension = new Dimension();
        dimension.setSize(500,400);

        jFrame.setSize( dimension );
        jFrame.setBackground(Color.BLACK);
//-----------------------------------------------
//        使用Point类设置窗体显示位置
        Point point = new Point();
        point.setLocation(300,200);
        jFrame.setLocation( point );
//-----------------------------------------------
//        窗体是否可见(不设置为ture无法看到窗体)
        jFrame.setVisible(true);
        //注意生成的窗体关闭按钮不能使程序退出，需要使用ctrl+c组合件来退出

    }
}

