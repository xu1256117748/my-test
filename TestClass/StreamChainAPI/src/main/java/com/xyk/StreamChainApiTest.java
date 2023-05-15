package com.xyk;

import com.xyk.pojo.StaticResource;
import com.xyk.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ---本类用于测试Stream流的api---
 * Stream接口简介:
 *      流是java API中的新的成员，它可以让你用声明式的方式处理集合，简单点说，可以看成遍历数据的一个高级点的迭代器，
 *      也可以看做一个工厂，数据处理的工厂，当然，流还天然的支持并行操作；也就不用去写复杂的多线程的代码
 *      parallelStream
 *      想要把串行流转换为并行流很简单，只需要将stream修改为parallelStream，其它操作不变。
 *      串行和并行和for循环效率对比:
 *          https://www.cnblogs.com/yulinfeng/p/12577551.html
 * 如何理解Stream支持并行操作:
 *      1.什么是并行
 *      在说到并行的时候，相信很多人都会想到并发的概念。那么并行和并发两者一字之差，有什么区别呢？
 *      并行：多个任务在同一时间点发生，并由不同的cpu进行处理，不互相抢占资源
 *      并发：多个任务在同一时间点内同时发生了，但由同一个cpu进行处理，互相抢占资源
 *      当在大量数据处理上，数据并行化可以大量缩短任务的执行时间，将一个数据分解成多个部分，然后并行处理，最后将多个结果汇总，得到最终结果。
 *      2.stream并行流的原理
 *      对于并行流，其在底层实现中，是沿用了Java7提供的fork/join分解合并框架进行实现。
 *      fork根据cpu核数进行数据分块，join对各个fork进行合并。
 *      实现过程：首先把一个任务通过fork分块功能,根据CPU核数进行分块成多个小人物,然后由多个CPU处理计算,
 *              最后再通过join方法把小任务执行结果合并成原任务,提高了CPU的利用率
 *      3.影响并行流性能主要存在5个因素：
 *      对于并行流，一定不要陷入一个误区：并行一定比串行快。
 *      并行在不同的情况下它不一定是比串行快的。
 *      1）数据大小：输入数据的大小，直接影响了并行处理的性能。
 *          因为在并行内部实现中涉及到了fork/join操作，它本身就存在性能上的开销。因此只有当数据量很大，使用并行处理才有意义。
 *      2）源数据结构：
 *          fork时会对源数据进行分割，数据源的特性直接影响了fork的性能。
 *          ArrayList、数组或IntStream.range，可分解性最佳，因为他们都支持随机读取，因此可以被任意分割。
 *          HashSet、TreeSet，可分解性一般，其虽然可被分解，但因为其内部数据结构，很难被平均分解。
 *          LinkedList、Streams.iterate、BufferedReader.lines，可分解性极差，因为他们长度未知，无法确定在哪里进行分割。
 *      3）装箱拆箱
 *          尽量使用基本数据类型，避免装箱拆箱。
 *      4）CPU核数
 *          fork的产生数量是与可用CPU核数相关，可用的核数越多，获取的性能提升就会越大。
 *      5）单元处理开销
 *          花在流中每个元素的时间越长，并行操作带来的性能提升就会越明显。
 * @author 徐亚奎
 * @date 2021-08-24 13:49
 */
@Slf4j
public class StreamChainApiTest {
    private static List<User> userList = null;
    static {
        userList = StaticResource.userList;
//        for (int i =0;i<20;i++){
//            userList.addAll(userList);
//        }
//        System.out.println(userList.size());
    }

    /**
     * 1.数据源:
     * 数据源（source）也就是数据的来源，可以通过多种方式获得 Stream 数据源，下面列举几种常见的获取方式:
     *
     * Collection.stream(); 从集合获取流。
     * Collection.parallelStream(); 从集合获取并行流。
     * Arrays.stream(T array) 或 Stream.of(); 从数组获取流。
     * BufferedReader.lines(); 从输入流中获取流。
     * IntStream.of() ; 从静态方法中获取流。
     * Stream.generate(); 自己生成流
     * */
    @Test
    public void testGenerate(){
        // 生成 UUID 流
        Stream<UUID> generate = Stream.generate(UUID::randomUUID);
        generate.limit(5).forEach(System.out::println);
        // 生成 随机数 流 [-5 : 5] 10个不重复地 -->耗时较长,不推荐
        long s1 = System.currentTimeMillis();
        AtomicInteger count= new AtomicInteger(); //执行次数，用于对比效率
        Random random1 = new Random();

        List<Integer> randomNums = Stream.generate(() -> {
            count.getAndIncrement();
            return random1.nextInt(101)-50;})
                                            .distinct()
                                            .limit(100)
                                            .sorted(Comparator.comparing(Integer::intValue).reversed())
                                            .collect(Collectors.toList()); // 如果不需要返回可以直接用foreach遍历打印
        long e1 = System.currentTimeMillis();
        System.out.println("流操作执行了："+count+"次");
        System.out.println("耗时："+(e1-s1)+"毫秒");
        System.out.println(randomNums);
        // 方法对比： -->耗时较短,推荐
        long s2 = System.currentTimeMillis();
        int num=0;
        Random random = new Random();
        HashSet<Integer> integers = new HashSet<>();
        do {
            num++;
            integers.add(random.nextInt(101)-50);
        }while (integers.size()<100);
        List<Integer> collect = integers.stream().sorted(Comparator.comparing(Integer::intValue).reversed()).collect(Collectors.toList());
        long e2 = System.currentTimeMillis();
        System.out.println("基础操作执行了："+num+"次");
        System.out.println("耗时："+(e2-s2)+"毫秒");
        System.out.println(collect);
    }

    /**
     * 2.中间操作:
     * 数据处理/转换（intermedia）步骤可以有多个操作，这步也被称为intermedia（中间操作）。
     * 在这个步骤中不管怎样操作，它返回的都是一个新的流对象，原始数据不会发生任何改变，而且这个步骤是惰性计算处理的
     * 也就是说只调用方法并不会开始处理，只有在真正的开始收集结果时，中间操作才会生效，
     * 而且如果遍历没有完成，想要的结果已经获取到了（比如获取第一个值），会停止遍历，然后返回结果。
     * 惰性计算可以显著提高运行效率。
     * */

    /**
     * 3. 终止操作:
     * 结果处理（terminal ）是流处理的最后一步，执行完这一步之后流会被彻底用尽，流也不能继续操作了。
     * 也只有到了这个操作的时候，流的数据处理/转换等中间过程才会开始计算，也就是上面所说的惰性计算。
     * 结果处理也必定是流操作的最后一步。
     * */

    /**
     * 中间操作
     * .filter:过滤掉集合或对象中的部分属性
     * */
    @Test
    public void testFilter(){
        System.out.println(userList);
        // 过滤掉名字为张三的user
        List<User> list = userList.stream().filter(s -> {
            if (Objects.equals(s.getName(), "张三")) {
                return false; // 表示此部分的数据流不返回,即将此处数据流过滤掉
            } else {
                return true;
            }
        }).collect(Collectors.toList());
        System.out.println(list);
    }
    /**
     * 中间操作
     * .map:遍历并修改集合或对象中的部分属性
     * */
    @Test
    public void testMap() {
        System.out.println(userList);

        List<User> list = userList.stream().map(s -> {
            if (Objects.equals(s.getName(), "张三")) {
                s.setChildren(null); // 杀掉张三的孩子
            }
            if (Objects.equals(s.getName(), "李四")) {
                s.setName("小四").setId(999); // 修改李四的名字和id
            }
            return s;
        }).collect(Collectors.toList());
        System.out.println(list);
    }
    /**
     * 中间操作
     * .sorted() 排序
     * 相比较直接使用list集合的sort排序:
     *      使用流排序不会改变原串,而直接使用(void)list.sort()进行排序,会直接在原数据上修改
     *      使用流排序时可以配合其他流api使用,如果只是单纯做排序可以不使用流排序
     *      具体根据需求选择
     * 需要注意的是：
     *  使用sort()进行排序时候：
     *      不建议直接使用sort(new new Comparator<User>{}) 重写compare方法的方式，因为不够简洁，降序排序代码就更长了
     *      建议使用 sort(Comparator.comparing(User::getId)) 的方式根据每个user的id进行排序，默认为升序（从小到大）
     *              如果需要降序排序，则：sort(Comparator.comparing(User::getId).reversed()); 简洁！！！
     *
     * */
    @Test
    public void testSorted(){
        List<Integer> ints = Arrays.asList(5, 8, 6, 4, 1, 3, 8, 9);
        ints.sort(Comparator.comparing(Integer::intValue));
        System.out.println(ints);
//        List<Integer> collect = ints.stream().sorted().collect(Collectors.toList());
//        System.out.println(collect); // [1, 3, 4, 5, 6, 8, 8, 9]
        // ------在一个userList中，把所有user根据user的其中一个属性进行排序(通过该属性对应类型的包装类的compare方法返回一个int类型的比较结果)-------
        ArrayList<User> users = new ArrayList<>();
        User one = new User().setId(1).setName("老大");
        User two = new User().setId(2).setName("老二");
        User three = new User().setId(3).setName("老三");
        User four = new User().setId(4).setName("老四");
        User five = new User().setId(5).setName("老五");
        users.add(two); users.add(five); users.add(three); users.add(one); users.add(four);
        System.out.println(users);
//        users.sort(Comparator.comparing(User::getId).reversed());
//        System.out.println(users);

//        users.sort(Comparator.comparing(User::getId).thenComparing(User::getName));
//        System.out.println(users);

//        users.sort(new Comparator<User>() {
//            @Override
//            public int compare(User o1, User o2) {
//                return Integer.compare(o1.getId(), o2.getId());
//            }
//        });
//        System.out.println(users);

//        List<User> newUsers = users.stream().sorted(new Comparator<User>() {
//            @Override
//            public int compare(User o1, User o2) {
//
//                 return Integer.compare(o1.getId(), o2.getId());
//            }
//        }).collect(Collectors.toList());
        List<User> collect = users.stream()
                                  .sorted(Comparator.comparing(User::getId).thenComparing(User::getName).reversed())
                                  .collect(Collectors.toList());
        System.out.println(collect);
    }
    /**
     * 中间操作
     * .limit(x) 保留当前流中的前x个元素,其他的全部舍去,x>=0,类似于filter简化版
     * */
    @Test
    public void testLimit(){
        List<User> collect = userList.stream().limit(1).collect(Collectors.toList());
        System.out.println(collect);
    }
    /**
     * 中间操作
     * .distinct() 去除重复的数据
     * */
    @Test
    public void testDistinct(){
        List<User> list = userList.stream().map(u -> {
            if (u.getId() == 1 || u.getId() == 2) {
                u.setId(1);
                u.setName("李四");
                u.setChildren(null);
            }
            return u;
        }).collect(Collectors.toList());
        System.out.println("具有重复数据的list:"+list);
        List<User> collect = list.stream().distinct().collect(Collectors.toList());
        System.out.println("去除重复的数据后的list:"+collect);
    }

    /**
     *  终止操作
     * .count() 计算符合条件的流中元素的数量 等同于list.size()
     * */
    @Test
    public void testCount(){
        System.out.println(userList);
        int size = userList.size();
        long count = userList.stream().count();
        System.out.println(size+","+count);
    }

    /**
     * 终止操作
     * .collect(Collectors.toMap(lambda1-key,lambda2-value)): 把一个对象数组中的两个元素封装到一个map集合中
     * */
    @Test
    public void testToMap(){
        System.out.println(userList);
        Map<String, String> map = userList.stream().collect(Collectors.toMap(s->"0000"+s.getId(), User::getName));
        System.out.println(map);
    }

    /**
     * short-circuiting操作
     * 有时候需要在遍历中途停止操作，比如查找第一个满足条件的元素或者limit操作。
     * 在Stream中short-circuiting操作有：anyMatch、allMatch、noneMatch、findFirst、findAny，
     *
     * .findFirst().get() 获取第一个元素
     * */
    @Test
    public void testFindFirst(){
        System.out.println(userList);
        User user = userList.stream().findFirst().get();
        System.out.println(user);
    }
    /**
     * short-circuiting操作
     * 有时候需要在遍历中途停止操作，比如查找第一个满足条件的元素或者limit操作。
     * 在Stream中short-circuiting操作有：anyMatch、allMatch、noneMatch、findFirst、findAny，
     *
     * .findAny()，返回的元素是不确定的，对于同一个列表多次调用findAny()有可能会返回不同的值。使用findAny()是为了更高效的性能。
     * 如果是数据较少，串行地情况下，一般会返回第一个结果，如果是并行的情况，那就不能确保是第一个。
     * */
    @Test
    public void testFindAny(){
        User user = userList.stream().findAny().get();
        System.out.println(user);
    }
    /**
     * short-circuiting操作
     * 有时候需要在遍历中途停止操作，比如查找第一个满足条件的元素或者limit操作。
     * 在Stream中short-circuiting操作有：anyMatch、allMatch、noneMatch、findFirst、findAny，
     *
     * anyMatch() : 判断当前流条中，是否存在至少一个元素符合条件,返回true/false
     * allMatch() : 判断条件中，是否全部元素都符合条件,返回true/false
     * noneMatch(): 判断条件中，是否全部元素都不符合条件,返回true/false
     * */
    @Test
    public void testMatch(){
        List<Integer> ints = Arrays.asList(1, 3, 4, 6, 6, 7);
        System.out.println(ints);
        // 判断当前流中是否存在至少一个符合条件的元素
        boolean a = ints.stream().anyMatch(s -> s == 1);
        System.out.println(a); // true
        // 判断当前流中所有元素是否都符合条件
        boolean b = ints.stream().allMatch(s -> s == 1);
        System.out.println(b); // false
        // 判断当前流中所有元素是否都不符合条件
        boolean c = ints.stream().noneMatch(s -> s == 1);
        System.out.println(c); // false
    }

    /**
     * 将流转化为特化流,提供特化api,如：
     *      sum()-------> 求和
     *      average() --> 求平均数
     * mapToInt()
     * mapToDouble()
     * mapToLong()
     * */
    @Test
    public void testMaptoObj(){
        int[] ints = userList.stream().mapToInt(s -> s.getId()).toArray();
        System.out.println(Arrays.toString(ints));
    }



    /**
     * stream流实操(一):
     * 注意:
     *      .map(s -> s.getName()) // 推荐此种写法
     *         等价于
     *      .map(s -> {
     *         return s.getName();
     *      })
     *
     * 遍历List<User>集合,遍历并对每一个元素进行修改/过滤
     * */
    @Test
    public void do1(){
        System.out.println(userList);
        List<User> list = userList.stream()
                                    // 将所有user的children设置未null
                                    .map(s -> s.setChildren(null))
                                    // 过滤掉name为张三的user
                                    .filter(s ->!"张三".equals(s.getName()))
                                    .collect(Collectors.toList());
        System.out.println(list);
    }

    /**
     * stream流实操(二):
     * 遍历map集合,并对键值对数据进行修改/过滤
     * */
    @Test
    public void do2(){
        HashMap<String, String> map = new HashMap<>(10);
        map.put("id", null);
        map.put("name", "admin");
        map.put("salary","xxx元/月");
        map.put("sort", "1");
        map.put("deleted", "true");
        System.out.println(map);
        Map<String, String> collect = map.entrySet().stream()
                                                    .filter(s -> {
            // 过滤掉key值为sort的键值对
            if (s.getKey() == "sort") {
                return false;
            }
            // 过滤掉value值为null的键值对
            if (s.getValue() == null) {
                return false;
            }
            return true;
        }).map(s->{
            // 把key值为name的key修改为"张三"
            if (s.getKey()== "name"){
                s.setValue("张三");
            }
            // 把key值为salary的value修改为"3500元/月"
            if (s.getKey()== "salary"){
                s.setValue("3500元/月");
            }
            // 把key值为deleted的value修改为"false"
            if (s.getKey()== "deleted"){
                s.setValue("false");
            }
         return s;
        }).collect(Collectors.toMap(
                // 将key的值当作map的key
                s -> s.getKey(),
                // 将value的值当作map的value
                s -> s.getValue()));
        System.out.println(collect);
    }
    /**
     * stream流实操(三):
     * 遍历list/map集合,并将某一个属性封装到一个list/数组中
     * */
    @Test
    public void do3(){
        System.out.println(userList);
        // (一)遍历list集合,并将某一个属性(name)封装到一个list/数组中
        List<String> names = userList.stream().map(s -> s.getName()
        ).collect(Collectors.toList());
        System.out.println(names);
        // (二)遍历map集合,并将某一个属性封装到一个list/数组中
        HashMap<String, String> map = new HashMap<>(10);
        map.put("id", null);
        map.put("name", "admin");
        map.put("salary","xxx元/月");
        map.put("sort", "1");
        map.put("deleted", "true");
        System.out.println(map);
        List<String> keys = map.entrySet().stream().map(s -> s.getKey()
        ).collect(Collectors.toList());
        System.out.println(keys);
    }
    /**
     * stream流实操(四）：
     * 获取id最大的user的不同实现
     * */
    @Test
    public void do4(){
        System.out.println(userList);
        // sorted
        User user1 = userList.stream().sorted(Comparator.comparing(User::getId).reversed()).findFirst().get();
        System.out.println(user1);
        // max
        User user2 = userList.stream().max(Comparator.comparing(User::getId)).get();
        System.out.println(user2);
    }

    /**
     * stream流实操(五）：
     *  修改嵌套数据结构的值-->多级流嵌套
     * */
    @Test
    public void do5(){
        List<User> collect = userList.stream().map(s -> {
            // 修改子辈的名字
            if (!Objects.isNull(s.getChildren())) {
                s.getChildren().stream().map(c -> {
                    c.setName(s.getName() + "的children"+c.getName());
                    // 修改孙子辈的名字
                    if (!Objects.isNull(c.getChildren())){
                        c.getChildren().stream().map(d->{
                            d.setName(c.getName()+"的children"+d.getName());
                            return d;
                        }).collect(Collectors.toList());
                    }


                    return c;
                } ).collect(Collectors.toList());
            }
            return s;
        }).collect(Collectors.toList());
        System.out.println(collect);
    }



    @Test
    public void test1(){
        User user = userList.get(0);
        List<User> collect = userList.stream().map((s) -> {
            s.setChildren(null);
            return s;
        }).collect(Collectors.toList());
//        System.out.println(collect);
    }
    @Test
    public void test2(){
        User user = userList.get(0);
        List<User> collect = userList.parallelStream().map((s) -> {
            s.setChildren(null);
            return s;
        }).collect(Collectors.toList());
//        System.out.println(collect);
    }
    @Test
    public void test3(){
        for (User user : userList){
            user.setChildren(null);
        }
    }
    @Test
    public void methodCompare(){
        long l1 = System.currentTimeMillis();
        test1();
        long l2 = System.currentTimeMillis();
        System.out.println("方法耗时:"+(l2-l1)+"毫秒");

        long l3 = System.currentTimeMillis();
        test2();
        long l4 = System.currentTimeMillis();
        System.out.println("方法耗时:"+(l4-l3)+"毫秒");

        long l5 = System.currentTimeMillis();
        test3();
        long l6 = System.currentTimeMillis();
        System.out.println("方法耗时:"+(l6-l5)+"毫秒");
    }
    /**
     * 实现把多个集合合并成一个集合
     * */
    @Test
    public void test(){
        List<String> names = Arrays.asList("张三;李四;王五", "赵六;刘七","孙八;钱九");
        System.out.println("names:"+names+"; size:"+names.size());
        // 使用.map是无法实现的
        List<String[]> list1 = names.stream().map(s -> s.split(";")).collect(Collectors.toList());
        System.out.println("list1:"+list1);
        // 使用.map是无法实现的
        final List<String> list2 = names.stream()
                                        .map(s -> Stream.of(s.split(";")).collect(Collectors.joining()))
                                        .collect(Collectors.toList());
        System.out.println("list2:"+list2+"; size:"+list2.size());
        // 使用.flagMap实现: (把一个集合/数组中的数据提取出来， 变成和原集合同级的元素)
        List<String> collect = names.stream().flatMap(s -> Stream.of(s.split(";"))).collect(Collectors.toList());
        System.out.println("collect:"+collect+"; size:"+collect.size());

        System.out.println("----------");
        List<Object> collect1 = userList.stream().flatMap(s -> {
            if (s.getChildren() != null) {
                return Stream.of(s,s.getChildren().get(0));
            } else {
                return Stream.of(s);
            }
        }).collect(Collectors.toList());
        System.out.println(collect1);

    }

}
