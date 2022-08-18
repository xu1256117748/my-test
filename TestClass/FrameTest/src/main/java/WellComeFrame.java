import Common.FrameCommon;

import javax.swing.*;
import java.awt.*;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-07-13 13:35
 * @Description
 */
public class WellComeFrame extends JFrame {

    public WellComeFrame(){
        String title = "Wellcome !";
        setTitle( title );
        setSize(FrameCommon.DEFAULT_WIDTH, FrameCommon.DEFAULT_HIGH);
        WellcomePanel wellcomePanel = new WellcomePanel();
        Container contentPane = getContentPane();
        contentPane.add(wellcomePanel);


    }
}
