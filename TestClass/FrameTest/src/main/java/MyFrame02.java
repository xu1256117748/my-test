import javax.swing.*;
import java.awt.*;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-07-13 12:52
 * @Description 测试窗体类---使用Dimension类和Point类设置窗体大小和显示位置
 */
public class MyFrame02 {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("我的窗体02");
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

