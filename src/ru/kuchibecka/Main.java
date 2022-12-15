package ru.kuchibecka;

import java.util.Arrays;

/**
 * Задание:
 *
 * Дан набор костяшек домино, где каждая из сторон обозначена цифрой от 0 до 6.
 * Ваша задача расположить костяшки домино в линию таким образом, чтобы они соприкасались сторонами с одинаковыми цифрами. Допустимо вращать костяшки меняя левую и правую сторону.
 *
 * Ввод
 * Есть массив из N (1 ≤ N ≤ 10) костяшек домино: каждая костяшка представлена набором из двух цифр от 0 до 6.
 *
 * Вывод
 * Напишите «Решения нет», если их невозможно расположить описанным способом. Если это возможно – предложите любой вариант расположения. Каждая из N строк содержит порядковый номер костяшки со знаком «+» или «-» («+», если костяшка не вращалась, «-», если вращалась)
 *
 * Пример исходного массива костяшек домино
 * 1 2
 * 2 4
 * 2 4
 * 6 4
 * 2 1
 *
 * Пример вывода
 * 2 -
 * 5 +
 * 1 +
 * 3 +
 * 4 -
 */

public class Main {
    public static Domino findDomino(Domino connectedDomino, Domino[] dominos) {
        for (Domino domino : dominos) {
            if (domino.getLeft() == connectedDomino.getRight() && domino.getRight() == connectedDomino.getRight() && !domino.isUsed()) {
                domino.setUsed(true);
                // System.out.println("Нашёл " + domino.toString());
                return domino;
            }
        }
        for (Domino domino : dominos) {
            if (domino.getLeft() == connectedDomino.getRight() && !domino.isUsed() && !domino.equals(connectedDomino)) {
                domino.setUsed(true);
                // System.out.println("Нашёл " + domino.toString());
                return domino;
            } else if (domino.getRight() == connectedDomino.getRight() && !domino.isUsed() && !domino.equals(connectedDomino)) {
                domino.setUsed(true);
                domino.swap();
                // System.out.println("Нашёл " + domino.toString());
                return domino;
            }
        }
        for (Domino domino : dominos) {
            if (domino.getLeft() == connectedDomino.getRight() && !domino.isUsed()) {
                domino.setUsed(true);
                // System.out.println("Нашёл " + domino.toString());
                return domino;
            } else if (domino.getRight() == connectedDomino.getRight() && !domino.isUsed()) {
                domino.setUsed(true);
                domino.swap();
                // System.out.println("Нашёл " + domino.toString());
                return domino;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // init of domino things
        Domino domino1 = new Domino(1, 5, 1);
        Domino domino2 = new Domino(2, 1, 6);
        Domino domino3 = new Domino(3, 6, 2);
        Domino domino4 = new Domino(4, 2, 3);
        Domino domino5 = new Domino(5, 4, 5);
        Domino domino6 = new Domino(6, 1, 2);
        Domino[] dominos = new Domino[5];
        dominos[0] = domino1;
        dominos[1] = domino2;
        dominos[2] = domino3;
        dominos[3] = domino4;
         dominos[4] = domino5;
         /*dominos[5] = domino6;*/
        System.out.println("Массив имеющихся домино:" + Arrays.toString(dominos));
        //todo: написать функцию нахождения есть ли ещё костяшки, который можно подставить под нынешнюю цепочку под while()
        // если нечётных цифр от 1 до 6 на концах домино больше 2, то цепь не собрать, тк такие цифры должны быть на концах цепочки домино, а концов всего 2
        int[] numbers = new int[6];
        for (int i=0; i<= dominos.length - 1; i++) {
            switch (dominos[i].getLeft()) {
                case 1 -> numbers[0]++;
                case 2 -> numbers[1]++;
                case 3 -> numbers[2]++;
                case 4 -> numbers[3]++;
                case 5 -> numbers[4]++;
                case 6 -> numbers[5]++;
                default -> {
                    System.out.println("Что-то пошло не так. допустимые значения для концов домино находятся в диапазоне [1, 6]");
                    return;
                }
            }
            switch (dominos[i].getRight()) {
                case 1 -> numbers[0]++;
                case 2 -> numbers[1]++;
                case 3 -> numbers[2]++;
                case 4 -> numbers[3]++;
                case 5 -> numbers[4]++;
                case 6 -> numbers[5]++;
                default -> {
                    System.out.println("Что-то пошло не так. допустимые значения для концов домино находятся в диапазоне [1, 6]");
                    return;
                }
            }
        }
        int numberOfOddSides = 0;
        for (int number : numbers) {
            if (number % 2 == 1)
                numberOfOddSides++;
        }
        System.out.println("Кол-во нечётных концов домино: " + numberOfOddSides);
        if (numberOfOddSides > 2) {
            System.out.println("Решения нет");
        } else {
            if (numberOfOddSides == 0) {
                // Простейший алгоритм, любое стартовое домино
                Domino domino = dominos[0];
                domino.setUsed(true);
                int serialNumber = 2;
                // System.out.println("Стартовый домино: " + domino.toString());
                while (true) {
                    domino = findDomino(domino, dominos);
                    if (domino == null)
                        break;
                    // System.out.println("Очередной домино: " + domino.toString());
                    domino.setPosition(serialNumber);
                    serialNumber++;
                }
            } else {
                // Нужно начинать с домино с нечётным кол-вом на стороне и чтобы это была внешняя (левая) сторона
                // Возможно для 1 и 2 будут разные случаи
                int oddNumber = 0;
                for (int i=0; i<6; i++) {
                    if (numbers[i] % 2 == 1) {
                        oddNumber = i+1;
                        break;
                    }
                }
                Domino currentDomino = dominos[0];
                for (Domino domino : dominos) {
                    if (domino.getLeft() == oddNumber) {
                        currentDomino = domino;
                        domino.setPosition(1);
                        domino.setUsed(true);
                        break;
                    } else if (domino.getRight() == oddNumber) {
                        domino.swap();
                        domino.setPosition(1);
                        currentDomino = domino;
                        domino.setUsed(true);
                        break;
                    }
                }
                int serialNumber = 2;
                System.out.println("Стартовый домино: " + currentDomino.toString());
                while (true) {
                    currentDomino = findDomino(currentDomino, dominos);
                    if (currentDomino == null)
                        break;
                    System.out.println("Очередной домино: " + currentDomino.toString());
                    currentDomino.setPosition(serialNumber);
                    serialNumber++;
                }
            }
            for (Domino domino : dominos) {
                if (!domino.isUsed()) {
                    System.out.println("Решения нет");
                    return;
                }

            }
            System.out.println("Домино в нужном порядке: ");
            for (int i = 0; i < dominos.length+1; i++) {
                for (Domino domino7 : dominos) {
                    if (domino7.getPosition() == i)
                        System.out.println(domino7.toString());
                }
            }
        }
    }
}
