package com.javarush.test.level26.lesson02.task01;

import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Math.abs;

/* Почитать в инете про медиану выборки
Реализовать логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы
Вернуть отсортированный массив от минимального расстояния до максимального
Если удаленность одинаковая у нескольких чисел, то выводить их в порядке возрастания
*/
public class Solution {
    public static Integer[] sort(Integer[] array) {
        final Double median;

        Integer[] sortArray = Arrays.copyOf(array,array.length);

        Arrays.sort(sortArray);
        if (sortArray.length%2==1) {
            median = Double.valueOf(sortArray[sortArray.length/2]);
        } else {
            median = Double.valueOf((sortArray[sortArray.length/2 - 1] + sortArray[sortArray.length/2])/2);
        }

        Arrays.sort(array, new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                if (abs(o1 - median) > abs(o2 - median)) {
                    return 1;
                } else {
                    if (abs(o1 - median) < abs(o2 - median)) {
                        return -1;
                    } else {
                        return (o1 < o2) ? -1 : 1;
                    }
                }
            }
        });
        //implement logic here
        return array;
    }


}
