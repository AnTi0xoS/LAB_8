package com.calc;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("*** КАЛЬКУЛЯТОР ДРОБЕЙ ***");
        System.out.println();
        System.out.println("Пожалуйста, следуйте инструкции для корректной работы программы:");
        System.out.println("    - используйте цифры для ввода значений числителя и знаменателя дроби");
        System.out.println("    - используйте '/' для обозначения черты деления дроби");
        System.out.println("    - используйте '+', '-', '*' и '/' для обозначения арифметических операций");
        System.out.println("    - ставьте пробелы между дробями и знаком арифметической операции");
        System.out.println("    - для ввода отрицательного значения поставте '-' перед числителем или знаменателем дроби без пробела и скобок");
        System.out.println("    Пример ввода: 1/2 + -3/4");
        System.out.println();
        System.out.print("Введите выражение: ");
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        Pattern correctEnterPattern = Pattern.compile("((\\d+)|[\\-](\\d+))/((\\d+)|[\\-](\\d+))\\s[\\+\\-\\*\\/]\\s((\\d+)|[\\-](\\d+))/((\\d+)|[\\-](\\d+))");
        Matcher correctEnterMatcher = correctEnterPattern.matcher(userInput);
        if (!(correctEnterMatcher.matches())) {
            System.out.println("ОШИБКА: Неверный ввод");
            System.out.println("        Пожалуйста, повторите попытку");
            System.exit(1);
        } else {
            String[] expression = userInput.split("\\s");
            String[] firstFraction = expression[0].split("/");
            int firstNum = Integer.parseInt(firstFraction[0]);
            int firstDenom = Integer.parseInt(firstFraction[1]);
            Fraction first = new Fraction(firstNum, firstDenom);

            String operation = expression[1];

            String[] secondFraction = expression[2].split("/");
            int secondNum = Integer.parseInt(secondFraction[0]);
            int secondDenom = Integer.parseInt(secondFraction[1]);
            Fraction second = new Fraction(secondNum, secondDenom);

            Fraction result = new Fraction();
            switch (operation) {
                case "+" -> {
                    result.summation(first, second);
                    first.summation(second);
                }
                case "-" -> {
                    result.substraction(first, second);
                    first.substraction(second);
                }
                case "*" -> {
                    result.multiplication(first, second);
                    first.multiplication(second);
                }
                case "/" -> {
                    result.division(first, second);
                    first.division(second);
                }
            }
            System.out.println("Ответ:");
            System.out.print("способ подсчета 1: ");
            System.out.println(result.getValue());
            System.out.print("способ подсчета 2: ");
            System.out.println(first.getValue());
        }
    }
}
