import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-07-13 13:42
 * @Description
 */
public class WellcomePanel extends JPanel {
    public WellcomePanel(){
        String text = "请输入您的名字:";
        JLabel jLabel = new JLabel(text);

        JTextField input = new JTextField(10); // 创建接受文本的字符条
        JTextField output = new JTextField(25); // 创建接受文本的字符条

        //        创建欢迎按钮
        String buttonName = " wellcome ! ";
        Button button = new Button( buttonName );

        add(jLabel);
        add(input);
        add(output);
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = input.getText();
                output.setText(str + " 你好,这里是欢迎界面!");
            }
        });

    }
}
