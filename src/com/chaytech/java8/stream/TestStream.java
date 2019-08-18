package com.chaytech.java8.stream;

import com.chaytech.java8.lambda.User;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream的三个操作步骤：
 *      1、创建Stream
 *      2、中间操作
 *      3、终止操作（终端操作）
 *
 *
 * @author chaytech@163.com
 * @date 2019/08/17 16:33
 */
public class TestStream {

    // 创建stream
    @Test
    public void test1(){
        // 1、 通过Collection集合提供的stream()或parallelStream()
        List<String> list = new ArrayList<>();
        // stream()
        Stream<String> stream1 = list.stream();
        // parallelStream()
        Stream<String> parallelStream = list.parallelStream();


        // 2、通过Arrays中的静态方法stream()获取数组流
        String[] strArray = {"1","2"};
        Stream<String> stream2 = Arrays.stream(strArray);

        // 3、通过stream类中的静态方法of()
        Stream<String> stream3 = Stream.of("1", "2");

        // 4、创建无限流
        // 使用迭代
        Stream<Integer> iterateStream = Stream.iterate(0, (e) -> e + 2);
        iterateStream.limit(10).forEach(System.out::println);

        // 使用生成
        Stream<Double> generateStream = Stream.generate(() -> Math.random());
        generateStream.limit(10).forEach(System.out::println);

    }



    List<User> userList = new ArrayList<>(Arrays.asList(
            new User("小明", "男", 20),
            new User("小红", "女", 30),
            new User("小张", "男", 40),
            new User("小张", "男", 40)
    ));


    /**stream中间操作  start*/

    /**
     * 切片与筛选
     *
     * filter(Predicatep)：接收Lambda ，从流中排除某些元素。
     * limit(long maxSize)：截断流，使其元素不超过给定数量。
     * skip(long n)：跳过元素，返回一个扔掉了前n 个元素的流。若流中
     * distinct()：筛选，通过流所生成元素的hashCode() 和equals() 去除重复元素
     */
    @Test
    public void test2(){
        // 中间操作：不会执行任何操作
        Stream<User> userStream = userList.stream().filter((e) -> {
            System.out.println("stream 中间操作");
            return e.getAge() > 20;
        });

        // 终止操作：一次性执行完全部内容，称为"惰性求值"
        userStream.forEach(System.out::println);
    }

    // limit(long maxSize)：截断流，使其元素不超过给定数量
    @Test
    public void test3(){
        userList.stream().limit(2).forEach(System.out::println);
    }


    // skip(long n)：跳过元素，返回一个扔掉了前n 个元素的流。若流中
    @Test
    public void test4(){
        userList.stream().skip(2).forEach(System.out::println);

    }

    // distinct()：筛选，通过流所生成元素的hashCode() 和equals() 去除重复元素
    @Test
    public void test5(){
        userList.stream().distinct().forEach(System.out::println);
    }

    /**
     * 映射
     *
     *
     * map(Functionf)：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * flatMap(Function f)：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    // map(Functionf)：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
    @Test
    public void test6(){
        userList.stream().map(User::getName).forEach(System.out::println);
    }

    // flatMap(Function f)：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
    @Test
    public void test7() {
        List<String> list = Arrays.asList("a a", "c c", "d d", "b b");
        Stream<String> stringStream = list.stream().flatMap(e -> Arrays.stream(e.split(" ")));
        stringStream.forEach(System.out::println);
    }

    /**
     * 排序
     *
     * sorted()：产生一个新流，其中按自然顺序排序
     * sorted(Comparatorcomp)：产生一个新流，其中按比较器顺序排序
     */

    // sorted()：产生一个新流，其中按自然顺序排序
    @Test
    public void test8(){
        List<String> list = Arrays.asList("a a", "c c", "d d", "b b");
        list.stream().sorted().forEach(System.out::println);
    }

    // sorted(Comparatorcomp)：产生一个新流，其中按比较器顺序排序
    @Test
    public void test9(){
       userList.stream().sorted((e1,e2) -> {
           if(e1.getAge() > e2.getAge()){
                return 1;
           }
           return 0;
       }).forEach(System.out::println);
    }
    /**stream中间操作  end*/






    /**stream终止操作  start*/
    /**
     * 查找与匹配
     *
     * allMatch(Predicate p)：检查是否匹配所有元素
     * anyMatch(Predicate p)： 检查是否至少匹配一个元素
     * noneMatch(Predicatep)：检查是否没有匹配所有元素
     * findFirst()：返回第一个元素
     * findAny()：返回当前流中的任意元素
     * count()：返回流中元素总数
     * max(Comparatorc)：返回流中最大值
     * min(Comparatorc)：返回流中最小值
     * forEach(Consumerc)：内部迭代(使用Collection 接口需要用户去做迭代，称为外部迭代。相反，Stream API 使用内部迭代——它帮你把迭代做了)
     */
    // allMatch(Predicate p)：检查是否匹配所有元素
    @Test
    public void test10() {
        boolean b = userList.stream().allMatch((e) -> "男".equals(e.getGender()));
        System.out.println("allMatch：" + b);
    }

    // anyMatch(Predicate p)： 检查是否至少匹配一个元素
    @Test
    public void test11(){
        boolean b = userList.stream().anyMatch((e) -> "男".equals(e.getGender()));
        System.out.println("anyMatch：" + b);
    }

    // noneMatch(Predicatep)：检查是否没有匹配所有元素
    @Test
    public void test12(){
        boolean b = userList.stream().noneMatch((e) -> "男".equals(e.getGender()));
        System.out.println("noneMatch：" + b);
    }

    // findFirst()：返回第一个元素
    @Test
    public void test13(){
        Optional<User> first = userList.stream().findFirst();
        System.out.println(first.get());
    }

    // findAny()：返回当前流中的任意元素
    @Test
    public void test14(){
        Optional<User> any = userList.stream().findAny();
        System.out.println(any.get());
    }

    // count()：返回流中元素总数
    @Test
    public void test15(){
        long count = userList.stream().count();
        System.out.println(count);
    }

    // max(Comparatorc)：返回流中最大值
    @Test
    public void test16(){
        Optional<User> max = userList.stream().max((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));
        System.out.println(max.get());
    }

    // min(Comparatorc)：返回流中最小值
    @Test
    public void test17(){
        Optional<User> max = userList.stream().min((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));
        System.out.println(max.get());
    }

    // forEach(Consumerc)：内部迭代(使用Collection 接口需要用户去做迭代，称为外部迭代。相反，Stream API 使用内部迭代——它帮你把迭代做了)
    @Test
    public void test18(){
        userList.stream().forEach(System.out::println);
    }

    /**
     * 规约
     *
     * reduce(T iden, BinaryOperator b)：可以将流中元素反复结合起来，得到一个值。返回T
     * reduce(BinaryOperator b)：可以将流中元素反复结合起来，得到一个值。返回Optional<T>
     */
    // reduce(T iden, BinaryOperator b)：可以将流中元素反复结合起来，得到一个值。返回T
    @Test
    public void test19() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Integer reduce = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(reduce);
    }

    // reduce(BinaryOperator b)：可以将流中元素反复结合起来，得到一个值。返回Optional<T>
    @Test
    public void test20(){
        Optional<Integer> reduce = userList.stream().map(User::getAge).reduce(Integer::sum);
        System.out.println(reduce.get());
    }

    /**
     * 收集
     *
     * collect(Collector c)：将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
     *
     */
    @Test
    public void test21() {
        // toList
        List<User> list = userList.stream().collect(Collectors.toList());
        list.forEach(System.out::println);

        System.out.println("-----------------------");

        // toSet
        Set<User> userSet = userList.stream().collect(Collectors.toSet());
        userSet.forEach(System.out::println);

        System.out.println("-----------------------");

        // 计算流中元素的个数
        Long count = userList.stream().collect(Collectors.counting());
        System.out.println(count);

        System.out.println("-----------------------");

        // 平均值
        Double avg = userList.stream().collect(Collectors.averagingDouble(User::getAge));
        System.out.println(avg);

        System.out.println("-----------------------");

        // 总和
        Double sum = userList.stream().collect(Collectors.summingDouble(User::getAge));
        System.out.println(sum);

        System.out.println("-----------------------");

        // 最大值
        Optional<User> max = userList.stream().collect(Collectors.maxBy((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge())));
        System.out.println(max.get());

        System.out.println("-----------------------");

        // 最小值
        Optional<Integer> min = userList.stream().map(User::getAge).collect(Collectors.minBy(Integer::compare));
        System.out.println(min);

        System.out.println("-----------------------");

        // 分组
        Map<String, List<User>> group = userList.stream().collect(Collectors.groupingBy(User::getGender));
        System.out.println(group);

        System.out.println("-----------------------");

        // 多级分组
        Map<String, Map<Integer, List<User>>> multilevelGroup = userList.stream().collect(Collectors.groupingBy(User::getGender, Collectors.groupingBy(User::getAge)));
        System.out.println(multilevelGroup);

        System.out.println("-----------------------");

        // 根据true或false进行分区
        Map<Boolean, List<User>> area = userList.stream().collect(Collectors.partitioningBy((e) -> e.getGender().equals("男")));
        System.out.println(area);

        System.out.println("-----------------------");

        // 求统计值
        DoubleSummaryStatistics summaryStatistics = userList.stream().collect(Collectors.summarizingDouble(User::getAge));
        System.out.println(summaryStatistics.getMax());
        System.out.println(summaryStatistics.getMin());
        System.out.println(summaryStatistics.getCount());
        System.out.println(summaryStatistics.getAverage());

        System.out.println("-----------------------");

        // 连接字符串
        String joining = userList.stream().map(User::getName).collect(Collectors.joining());
        System.out.println(joining);

        System.out.println("-----------------------");

        // 连接字符串,按符号分隔
        String joiningSp = userList.stream().map(User::getName).collect(Collectors.joining("，"));
        System.out.println(joiningSp);

        // 从一个作为累加器的初始值开始，利用BinaryOperator与流中元素逐个结合，从而归约成单个值
        Integer reducing = userList.stream().collect(Collectors.reducing(0, User::getAge, Integer::sum));
        System.out.println(reducing);

        // 包裹另一个收集器，对其结果转换函数
        Integer collectingAndThen = userList.stream().collect(Collectors.collectingAndThen(Collectors.toList(), List::size));
        System.out.println(collectingAndThen);
    }
    /**stream终止操作  end*/
}
