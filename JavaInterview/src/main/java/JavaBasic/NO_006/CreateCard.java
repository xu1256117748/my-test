package JavaBasic.NO_006;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-22 18:32
 * @Description
 */
public class CreateCard extends Thread{

    @Override
    public void run() {
        Card card = new Card();
        card.say();
    }
}
