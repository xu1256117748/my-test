package com.xyk;

import com.xyk.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author 徐亚奎
 * @date 2021/7/8 12:21
 * 此类用于计算各个算法/方法的耗时,进行比较
 */
@BenchmarkMode(Mode.AverageTime)
//时间单位--纳秒
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Slf4j
public class TimeOutComparent {
//-----------------------
//    类静态属性
    public static List<User> list;
//    在静态代码块中赋值
    {
        list= new ArrayList<User>();
        for (int i=0;i<1024*1024*20;i++){
            list.add(new User().setId(i));
        }
    }
//-----------------------
    @Benchmark
    public void test1(){
//        for循环
        for (int i=0;i<list.size();i++){
            list.get(i).getId();
//            System.out.println(list.get(i));
        }
    }
    @Benchmark()
    public void test2(){
//        增强for循环
        for (User user:list ) {
            user.getId();
        }
    }
    @Benchmark
    public void test3(){
//        list的foreach的方法
        list.forEach(s->{s.getId();});
    }
    @Benchmark
    public void test4(){
//      list的迭代器
        Iterator<User> iterator = list.iterator();
        while (iterator.hasNext()){
            iterator.next().getId();
        }
    }
    @Benchmark
    public void test5(){
        // strem 流遍历
        list.stream().map(s->{
            s.getId();
            return s;
        }).collect(Collectors.toList());
    }

    public static void main(String[] args) throws RunnerException {

        Options build = new OptionsBuilder()
                .include(TimeOutComparent.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(build).run();

    }
}
