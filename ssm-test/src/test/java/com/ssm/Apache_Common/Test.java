package com.ssm.Apache_Common;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.*;
import com.google.common.collect.*;
import com.google.common.hash.*;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.io.Charsets;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 */
public class Test {
    static DateTime dt = new DateTime();
    static DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {

        testCodec();
    }

    /**
     * Joda-time方法测试
     */
    public static void testJodaTime() {

        DateTime dt = new DateTime("2015-07-07");
        int month = dt.getMonthOfYear();  // where January is 1 and December is 12
        int year = dt.getYear();
        System.out.println(dt);
        DateTime newDt = dt.plusMonths(2); //日期计算
        System.out.println(fmt.print(newDt)); //日期格式化
    }

    /**
     * 测试protobuf的方法
     */
    public static void testProtobuf() {
        RuntimeSchema<UserEntiy> schema = RuntimeSchema.createFrom(UserEntiy.class);
        UserEntiy user01 = new UserEntiy(1L, dt.toDate(), "root1", "root1");

        //int writeListTo(final OutputStream out, final List<T> messages,final Schema<T> schema, final LinkedBuffer buffer) throws IOException
        //int writeTo(final OutputStream out, final T message, final Schema<T> schema, final LinkedBuffer buffer) throws IOException
        //byte[] toByteArray(T message, Schema<T> schema, LinkedBuffer buffer)
        byte[] bytes = ProtostuffIOUtil.toByteArray(user01, schema,
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE)); //将JavaBean转化为byte数组
        UserEntiy user02 = schema.newMessage();

        //void mergeFrom(byte[] data, T message, Schema<T> schema)
        //void mergeFrom(InputStream in, T message, Schema<T> schema)
        ProtostuffIOUtil.mergeFrom(bytes, user02, schema);//将byte数组转换为JavaBean
        System.out.println(user02);
    }

    /**
     * 测试jackson的方法
     */
    public static void testJackSon() {

        UserEntiy user01 = new UserEntiy(1L, dt.toDate(), "root1", "root1");
        UserEntiy user02 = new UserEntiy(2L, dt.toDate(), "root2", "root2");
        System.out.println(user01);
        ObjectMapper mapper = new ObjectMapper(); //核心类
        //Java集合转JSON
        List<UserEntiy> users = new ArrayList<UserEntiy>();
        users.add(user01);
        users.add(user02);
        String json = "";
        String json_list = "";
        //User类转JSON
        try {
            System.out.println(json = mapper.writeValueAsString(user01));
            System.out.println(json_list = mapper.writeValueAsString(users));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //将json转化为JavaBean
        try {
            UserEntiy user = mapper.readValue(json, UserEntiy.class);
            List<UserEntiy> lUser = mapper.readValue(json_list, List.class);
            System.out.println(user);
            System.out.println(lUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试ComparisonChain链懒操作和SortedMultiset集合
     */
    public static void testOptional() {
        UserEntiy user01 = new UserEntiy(1L, dt.toDate(), "root1", "root1");
        UserEntiy user02 = new UserEntiy(1L, dt.toDate(), "root2", "root1");
        //treeMultiSet保证元素有序
        SortedMultiset<UserEntiy> sets = TreeMultiset.create();
        sets.add(user01);
        sets.add(user02);
        System.out.println(sets);
    }

    /**
     * 测试不可变集合
     */
    public static void testImmutable() {
        ImmutableSortedSet<String> imSet = ImmutableSortedSet.of("a", "b", "", "c", "a", "d", "b");//不支持null，快速失败
        System.out.println(imSet); //[a, b, c, d]
        List list = new ArrayList();
        list.add(1);
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        ImmutableList<Integer> imSet2 = ImmutableList.copyOf(list);
        System.out.println(imSet2.get(0) + "---" + imSet2); //[a, b, c, d]
        // imSet2.remove(0); 不可变集合
        imSet2.get(0);
    }

    /**
     * 测试字符串操作
     */
    public static void testStringUtil() {
        System.out.println(Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("foo,bar,,   qux"));

        Joiner.on(",").join(Arrays.asList(1, 5, 7)); // returns "1,5,7"
    }

    /**
     * IO方法测试
     */
    public static void testIO() {
        try {
            URL url = new URL("https://wizardforcel.gitbooks.io/guava-tutorial/content/26.html");
            File file = new File("C:/WiFi_Log.txt");
            //Read the lines of a UTF-8 text file
            ImmutableList<String> lines = Files.asCharSource(file, Charsets.UTF_8).readLines();
            //Count distinct word occurrences in a file
            Multiset<String> wordOccurrences = HashMultiset.create(
                    Splitter.on(CharMatcher.WHITESPACE)
                            .trimResults()
                            .omitEmptyStrings()
                            .split(Files.asCharSource(file, Charsets.UTF_8).read()));

            //SHA-1 a file
            HashCode hash = Files.asByteSource(file).hash(Hashing.sha1());

            //Copy the data from a URL to a file
            Resources.asByteSource(url).copyTo(Files.asByteSink(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试guava的集合
     */
    public static void testCollection() {
        //BiMap双向map
        BiMap<Integer, String> map = HashBiMap.create();
        map.put(1, "hello");
        map.put(2, "helloa");
        map.put(3, "world");
        map.put(4, "worldb");
        map.put(5, "my");
        map.put(6, "myc");
        //map.put(7, "myc");// 双向map---因为是双向的，key集合和value集合必须都是唯一的
        System.out.println(map.toString());
        System.out.println(map.inverse().toString());
        System.out.println(map.containsValue("hello"));
        System.out.println(map.toString());

        //MultiMap
        ListMultimap<Integer, String> listMutilMap = ArrayListMultimap.create();
        listMutilMap.put(100, "1001");
        listMutilMap.put(2, "21");
        listMutilMap.put(2, "22");
        listMutilMap.put(1, "1");
        listMutilMap.put(1, "2");
        listMutilMap.put(43, "431");
        System.out.println(listMutilMap.keySet());//key采用hash的方式，不保证key的顺序，value放到List中

        Multimap<Integer, String> multiMap = LinkedHashMultimap.create();
        multiMap.put(100, "1001");
        multiMap.put(2, "21");
        multiMap.put(2, "22");
        multiMap.put(1, "1");
        multiMap.put(1, "2");
        multiMap.put(43, "431");
        System.out.println(multiMap.keySet());//key和add的顺序一致


        Multimap<Integer, String> hashMultimap = HashMultimap.create();
        hashMultimap.put(100, "1001");
        hashMultimap.put(2, "21");
        hashMultimap.put(2, "22");
        hashMultimap.put(1, "1");
        hashMultimap.put(1, "2");
        hashMultimap.put(43, "431");

        System.out.println(hashMultimap.keySet());//key采用hash的方式，不保证顺序，value放到set中

        SortedSetMultimap<Integer, String> sortedSetMultiMap = TreeMultimap.create();
        sortedSetMultiMap.put(100, "1001");
        sortedSetMultiMap.put(2, "21");
        sortedSetMultiMap.put(2, "22");
        sortedSetMultiMap.put(1, "1");
        sortedSetMultiMap.put(1, "2");
        sortedSetMultiMap.put(43, "431");
        System.out.println(sortedSetMultiMap);//key保证有序（升序），相同的key的value也有序（升序）

        //RangeSet
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10)); // {[1,10]}
        System.out.println(rangeSet);

        rangeSet.add(Range.closedOpen(11, 15));//不相连区间:{[1,10], [11,15)}
        System.out.println(rangeSet);

        rangeSet.add(Range.closedOpen(15, 20)); //相连区间; {[1,10], [11,20)}
        System.out.println(rangeSet);

        rangeSet.add(Range.openClosed(0, 0)); //空区间; {[1,10], [11,20)}
        System.out.println(rangeSet);

        rangeSet.remove(Range.open(5, 10)); //分割[1, 10]; {[1,5], [10,10], [11,20)}
        System.out.println(rangeSet);

        /**
         * 对集合添加唯一索引
         * @param technicianId
         * @return
         */
//        List<String> list = new ArrayList<String>();
//        for (int i = 0; i < 10; i++) {
//            list.add(i + "");
//        }
//        ImmutableMap<Integer, TechnicianDTO> uniqueIndex = FluentIterable.from(technicianDTOs).uniqueIndex(new Function<TechnicianDTO, Integer>() {
//            public Integer apply(TechnicianDTO technicianDTO) {
//                return technicianDTO.getTechnicianId();
//            }
//        });
//        System.out.println(uniqueIndex.get(technicianId));
    }

    public static void testBloomFilter() {

        Funnel<Person> personFunnel = new Funnel<Person>() {
            public void funnel(Person from, PrimitiveSink into) {
                into.putInt(from.getAge1());
                into.putString(from.getName1(), Charsets.UTF_8);
                into.putInt(from.getAge2());
                into.putString(from.getName2(), Charsets.UTF_8);
            }
        };
        //计算Hash
        HashFunction hashFunction = Hashing.md5();
        HashCode hashCode = hashFunction.newHasher().putLong(1).putString(
                "zhangsan", Charset.forName("utf-8")).putObject(
                new Person(1983, "zhang", 1, "san"), personFunnel).hash();
        System.out.println(hashCode.hashCode());
        System.out.println(hashCode.toString());//cff805d850adcf9e936d76019502153a
        System.out.println(hashCode.asInt());
        System.out.println(Integer.toHexString(-670697265));//d805f8cf  刚好取前4个字节，从后向前

        //第二个参数的意思是：这个filter中预期要存入多少个对象，这个值一定要往大里估，因为实际存储对象个数超过这个值
        //错误率会迅速升高
        BloomFilter<Person> personBloomFilter = BloomFilter.create(personFunnel, 10000000, 0.00001);
        personBloomFilter.put(new Person(24, "zhang", 22, "lisi"));
        personBloomFilter.put(new Person(25, "li", 21, "a"));
        personBloomFilter.put(new Person(21, "dd", 22, "lisi"));
        personBloomFilter.put(new Person(23, "fdsa", 21, "fdse"));
        personBloomFilter.put(new Person(26, "s", 22, "ac"));
        personBloomFilter.put(new Person(24, "yy", 12, "oi"));
        if (personBloomFilter.mightContain(new Person(25, "li", 21, "a"))) {
            System.out.println("contains");
        }
        if (personBloomFilter.mightContain(new Person(25, "li", 20, "a"))) {
            System.out.println("contains too");
        } else {
            System.out.println("not contain");
        }
    }


    public static void testFunctionProgram() {
        //传统写法
        List<String> list = Lists.newArrayList("ZHANG", "Chen", "LI", "wang", "ZHAO");
        List<Integer> length = Lists.newArrayList();
        for (String tmp : list) {
            if (CharMatcher.JAVA_UPPER_CASE.matchesAllOf(tmp)) {
                length.add(tmp.length());
            }
        }
        System.out.println(length.toString());
        //guava函数式写法
        ArrayList<Integer> integers = Lists.newArrayList(FluentIterable.from(list).filter(new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return CharMatcher.JAVA_UPPER_CASE.matchesAllOf(input);
            }
        }).transform(new Function<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return input.length();
            }
        }));
        System.out.println(integers.toString());
    }

    //jdk8+ lambda写法
//    ArrayList<Integer> integers = Lists.newArrayList(FluentIterable.from(list)
//            .filter(input -> CharMatcher.JAVA_UPPER_CASE.matchesAllOf(input))
//            .transform(input -> input.length()));
//    System.out.println(integers.toString());

    /**
     * 测试codec方法
     */
    public static void testCodec() {
        String str = "ijsoij8W#Fsd4QER#$%segdsfWR@#R";
        String url = "http://baidu.com/中文";
        System.out.println("md5:" + DigestUtils.md5Hex(str)); //生成md5
        //System.out.println("sha1:" + new String(DigestUtils.sha1(str)));//生成sha1 (返回byte[])
        System.out.println("sha1Hex:" + DigestUtils.sha1Hex(str));
        byte[] b = Base64.encodeBase64(str.getBytes(), true);
        System.out.println("Base64 Encode:" + new String(b));
        System.out.println("Base64 Decode:" + new String(Base64.decodeBase64(b)));
        try {
            URLCodec urlCodec = new URLCodec();
            String encodeUrl = urlCodec.encode(url, CharEncoding.UTF_8);
            String decodeUrl = urlCodec.decode(encodeUrl, CharEncoding.UTF_8);
            System.out.println("编码：" + encodeUrl + "  解码：" + decodeUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testCommon() {
        String str = "abc,bcd,efg";
        System.out.println(StringUtils.split(str, ","));
        System.out.println(RandomStringUtils.randomAlphabetic(10));
    }
}
