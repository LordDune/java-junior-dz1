package ru.geekbrains.lesson1.task2;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // Задание 1.
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        Double avg = list.stream().filter(i -> i%2 == 0).mapToInt(e -> e).average().getAsDouble();
        System.out.println("Среднее арифметическое всех четных чисел списка list: " + avg); // 6.0
    }

}
