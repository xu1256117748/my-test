package JavaBasic.NO_006;


import org.slf4j.MDC;

/**
 * Created by shengjk1 on 2017/11/30
 */
public class Main {


    public static void main(String[] args) {
        String[] destinations = {"a","b","c"};

        Thread thread=null;
        for (final String destination:destinations ) {
            thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    MDC.put("logFileName", destination);
                    //自己要运行的代码如  ClusterCanalClient.run(destination);
                    MDC.remove("logFileName");
                }
            });
            thread.setName(destination);
            thread.start();
        }
    }
}

