package lab4;

import java.util.*;
import java.util.stream.Collectors;

public class StreamClass {
    public static OptionalDouble getAverage(List<Integer> numbers){
        return numbers.stream().mapToInt(Integer::intValue).average();
    }

    public static List<String> strToUpperPrefix(List<String> strings){
        return strings.stream().map(str->"_new_" + str.toUpperCase()).toList();
    }

    public static List<Integer> squareSingleNumber(List<Integer> numbers){
        return numbers.stream()
                .filter(number-> Collections.frequency(numbers, number) == 1)
                .map(number -> number*number).toList();
    }

    public static <T> T getLastElement(Collection<T> collection) throws  NoSuchElementException {
        return collection.stream().reduce((first, second) -> second)
                .orElseThrow(() -> new NoSuchElementException("Коллекция пуста"));
    }

    public static int getEvenSum(int[] numbers){
        return Arrays.stream(numbers).filter(number -> number % 2 == 0).sum();
    }

    public static Map<Character, String> getStringsMap(List<String> strings){
        return strings.stream().filter(string -> !string.isEmpty())
                .collect(Collectors.toMap(string -> string.charAt(0),
                        string -> string.length() > 1 ? string.substring(1) : "",
                        (existing, replacement) -> existing));
    }

    public static void main(String[] args) {
        //1
        List<Integer> numbers = List.of(5, 6, 115);
        System.out.println(getAverage(numbers));

        //2
        List<String> words = List.of("hello", "world", "how");
        System.out.println(strToUpperPrefix(words));

        //3
        List<Integer> numbers2 = List.of(1,1,3,4,5,8,9,9);
        System.out.println(squareSingleNumber(numbers2));

        //4
        try{
            List<Integer> items = List.of(1, 2, 3);
            System.out.println(getLastElement(items));

            List<String> items2 = List.of();
            System.out.println(getLastElement(items2));
        }
        catch (NoSuchElementException e){
            System.out.println("Исключение! " + e.getMessage());
        }

        //5
        int[] evenArr = {1, 2, 3, 4, 5};
        System.out.println(getEvenSum(evenArr));

        int[] noEvenArr = {1, 1, 3, 3, 5};
        System.out.println(getEvenSum(noEvenArr));

        //6
        List<String> strings = List.of("how", "are", "you");
        System.out.println(getStringsMap(strings));
    }
}
