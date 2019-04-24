package xyz.zzyymaggie.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

/**
 * allArtists.stream()
 * .filter(artist -> artist.isFrom("London"));
 * 这行代码并未做什么实际性的工作， filter 只刻画出了 Stream ，但没有产生新的集合。像
 * filter 这样只描述 Stream ，最终不产生新集合的方法叫作惰性求值方法；而像 count 这样
 * 最终会从 Stream 产生值的方法叫作及早求值方法。
 *
 * 判断一个操作是惰性求值还是及早求值很简单：只需看它的返回值。如果返回值是 Stream ，
 * 那么是惰性求值；如果返回值是另一个值或为空，那么就是及早求值。
 */
public class StreamMethodTest {
    @Test
    public void testFilter() {
        Artist artist1 = new Artist("A", "China", 20);
        Artist artist2 = new Artist("B", "London", 20);
        List<Artist> allArtists = Arrays.asList(artist1, artist2);
        System.out.println("只过滤不计数");
        allArtists.stream()
                .filter(artist -> {
                    System.out.println(artist.getName());
                    return artist.isFrom("London");
                });
        System.out.println("先过滤再计数");
        long count = allArtists.stream()
                .filter(artist -> {
                    System.out.println(artist.getName());
                    return artist.isFrom("London");
                })
                .count();
    }

    @Test
    public void toUpper(){
        List<String> collected = new ArrayList<>();
        for (String string : Arrays.asList("a", "b", "hello")) {
            String uppercaseString = string.toUpperCase();
            collected.add(uppercaseString);
        }
        assertEquals(Arrays.asList("A", "B", "HELLO"), collected);
    }
    /**
     * 如果有一个函数可以将一种类型的值转换成另外一种类型， map 操作就可以
     * 使用该函数，将一个流中的值转换成一个新的流.
     */
    @Test
    public void testMap(){
        List<String> collected = Stream.of("a", "b", "hello")
                .map(string -> string.toUpperCase())
                .collect(toList());
        assertEquals(Arrays.asList("A", "B", "HELLO"), collected);
    }

    /**
     * flatMap 方法可用 Stream 替换值，然后将多个 Stream 连接成一个 Stream
     */
    @Test
    public void testFlatMap(){
        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(numbers -> numbers.stream())
                .collect(toList());
        assertEquals(Arrays.asList(1, 2, 3, 4), together);
    }

    /**
     * 查找 Stream 中的最大或最小元素
     * Java 8提供了一个新的静态方法 comparing ，使用它可以方便地实现一个比较器
     */
    @Test
    public void testMinAndMax() {
        Artist artist1 = new Artist("A", "China", 20);
        Artist artist2 = new Artist("B", "London", 18);
        Artist artist3 = new Artist("C", "London", 22);
        List<Artist> allArtists = Arrays.asList(artist1, artist2, artist3);

        Artist youngestArtist = allArtists.stream()
                .min(Comparator.comparing(artist -> artist.getAge()))
                .get();
        assertEquals(allArtists.get(1), youngestArtist);
        Artist oldestArtist = allArtists.stream()
                .max(Comparator.comparing(artist -> artist.getAge()))
                .get();
        assertEquals(allArtists.get(2), oldestArtist);
    }

    @Test
    public void testReduce(){
        int count = Stream.of(1, 2, 3)
                .reduce(0, (acc, element) -> acc + element);
        assertEquals(6, count);
    }

}
