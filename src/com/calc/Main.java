package com.calc;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.print("""
                *** КАЛЬКУЛЯТОР ДРОБЕЙ ***
                                            
                Данная программа реализует решение математического выражения с дробями
                                            
                Пожалуйста, следуйте инструкции для корректной работы программы:
                    - используйте цифры для ввода значений числителя и знаменателя дроби
                    - используйте '/' для обозначения черты деления дроби
                    - используйте '+', '-', '*' или ':' для обозначения арифметической операции
                    - для ввода отрицательного значения поставте '-' перед числителем или знаменателем дроби
                    - минимальный ввод для работы программы: две дроби и арифметическая операция между ними
                    - вы можете использовать скобки '(' и ')' для указания порядка действий
                    - количество дробей, скобок и арифметических операций неограничено
                    - вы можете ставить пробелы в выражении где угодно и в любом количестве
                    Пример ввода: ((-1/3 * 3/4 - 1/2)*2 / 1)  :(-1/-5 + ( 7/5 -6/7 ))
                """);
        System.out.print("\nВведите выражение: ");
        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();
        while (true) {
            if (Objects.equals(expression, "quit")) {
                System.out.println("Завершение работы");
                break;
            }
            expression = Calculation.checkUserInput(expression);
            if (expression == null) {
                System.out.println("""
                        ОШИБКА: Неверный ввод
                                Попробуйте еще раз
                        """);
            } else {
                String answer = Calculation.getAnswer(expression);
                if (answer == null) {
                    System.out.println("""
                            ОШИБКА: Деление на ноль
                                    Пожалуйста, повторите попытку
                            """);
                } else System.out.println("Ответ: " + answer + "\n");
            }
            System.out.print("Введите следующее выражение: ");
            expression = in.nextLine();
        }
        in.close();
    }
}
