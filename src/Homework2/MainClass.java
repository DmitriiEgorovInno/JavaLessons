package Homework2;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MainClass {
    public static void main(String[] args) {
        //удаление дубликатов
        List<Integer> list1 = List.of(1,2,3,4,5,1,2,4,5,6);
        System.out.println(remooveDupl(list1));
        System.out.println("------------------------");

        //найтие 3е наибольшее  число
        List<Integer> list2 = List.of(5, 2, 10, 9, 4, 3, 10, 1, 13);
        System.out.println(findThirdMax(list2));
        System.out.println("------------------------");

        //найти 3е уникальное наибольшее число
        List<Integer> list3 = List.of(5, 2, 10, 9, 4, 3, 10, 1, 13);
        System.out.println(findThirdUniqMax(list3));
        System.out.println("------------------------");

        List<Employee> list4 = new ArrayList<>(Arrays.asList(
                new Employee("Name1",18,"Инженер"),
                new Employee("Name2",43,"Босс>"),
                new Employee("Name3",45,"Рабочий"),
                new Employee("Name4",67,"Инженер"),
                new Employee("Name5",19,"Рабочий"),
                new Employee("Name6",38,"Инженер"),
                new Employee("Name7",56,"Инженер"),
                new Employee("Name8",67,"Босс"),
                new Employee("Name9",27,"Босс"),
                new Employee("Name10",33,"Рабочий"),
                new Employee("Name11",42,"Инженер")

        ));
        //поиск 3 старших инженеров
        System.out.println(findOldest(list4));
        System.out.println("------------------------");

        // поиск среднего возраста инженер
        System.out.println(findAverageAge(list4));
        System.out.println("------------------------");

        //поиск самого длинного слова
        List<String> list5=List.of("Word1","Wordd2","Wordddddd3","Word");
        System.out.println(findLongest(list5));
        System.out.println("------------------------");

        //построение хэшмапы
        String words = "word1 word2 word3 word2 word1 word4 word1 word2 word1 word3";
        System.out.println(countWords(words));
        System.out.println("------------------------");

        //строка по длинне и алфавиту
        List<String> list6=List.of("Word1","Wordd2","Wordddddd3","Word","Aaaa","rrrrrrrrrrrrrrrrrrrrrr");
        System.out.println(sortString(list6));
        System.out.println("------------------------");

        //поиск самого длиного слова в массиве строк
        String[] strings = {"aaa bbbb asdasd",
                "ggg werwerwerwrwrwerewr sd",
                "qwe qwe1 qwe2",
                "asd asdf asdfg",
                "rty rtyu rtyui"};
        System.out.println(findLongestMass(strings));
    }




    //удаление дубликатов
    public static <T> List<T> remooveDupl(List<T> list){
        return list.stream()
                .distinct()
                .collect(Collectors.toList());
    }
   //найтие 3е наибольшее  число
    public static Integer findThirdMax(List<Integer> list){
        return list.stream()
                .sorted(Collections.reverseOrder())
                .skip(2)
                .findFirst()
                .orElse(null);

    }
    //найти 3е уникальное наибольшее число
    public static Integer findThirdUniqMax(List<Integer> list){
        return list.stream()
                .sorted(Collections.reverseOrder())
                .distinct()
                .skip(2)
                .findFirst()
                .orElse(null);

    }
    //поиск 3 старших инженеров
    public static List<String> findOldest(List<Employee> employees){
        return employees.stream()
                .filter(employee -> "Инженер".equals(employee.getPosition()))
                .sorted(Comparator.comparing(Employee::getAge).reversed())
                .limit(3)
                .map(Employee::getName)
                .collect(Collectors.toList());

    }
    // поиск среднего возраста инженер
    public static double findAverageAge(List<Employee> employees){
        return employees.stream()
                .filter(employee -> "Инженер".equals(employee.getPosition()))
                .mapToInt(Employee::getAge)
                .average()
                .orElse(0.0);
    }
    //поиск самого длинного слова
    public static String findLongest(List<String> list){
        return list.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse(null);
    }
    //построение хэшмапы
    public static Map<String,Long> countWords(String words){
        return Arrays.stream(words.split(" "))
                .collect(Collectors.groupingBy(word ->word, Collectors.counting()));
    }
    //строка по длинне и алфавиту
    public static List<String> sortString(List<String> list){
        return list.stream()
                .sorted(Comparator.comparingInt(String::length).thenComparing(String::compareTo))
                .collect(Collectors.toList());
    }
    //поиск самого длиного слова в массиве строк
    public static String findLongestMass(String[] str){
        return Arrays.stream(str)
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .max(Comparator.comparingInt(String::length))
                .orElse(null);
    }
}
