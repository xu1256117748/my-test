package nowcoder.huawei.exam;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-08-26 10:47
 * @Description
 */
@Slf4j
public class OnlineExam {

    private static Scanner scanner = null;

    @Before
    public void before(){
        scanner = new Scanner(System.in);

    }
    @After
    public void after(){
        try {
            scanner.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试代码区 NO_01
     * */
    @Test
    public void test_01(){



    }

    /**
     * 测试代码区 NO_02
     * */
    @Test
    public void test_02(){



    }

    /**
     * 测试代码区 NO_03
     * */
    @Test
    public void test_03(){



    }

    /**
     * 测试代码区 NO_04
     * */
    @Test
    public void test_04(){



    }

    /**
     * 测试代码区 NO_05
     * */
    @Test
    public void test_05(){


    }
}
