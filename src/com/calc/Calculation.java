package com.calc;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculation {
    public static String checkUserInput(String userInput) {
        while (userInput.contains(" ")) {
            userInput = userInput.replace(" ", "");
        }
        Pattern correctInputPattern = Pattern.compile("(([\\(]*([\\-]?\\d+)/([\\-]?\\d+)[\\+\\-\\*\\:][\\(]*)+" +
                "(([\\+\\-\\*\\:]?([\\-]?\\d+)/([\\-]?\\d+))[\\)]*[\\+\\-\\*\\:]?)+)+");
        Matcher correctInputMatcher = correctInputPattern.matcher(userInput);
        if (!(correctInputMatcher.matches())) return null;
        int bracketCount = 0;
        for (int i = 0; i < userInput.length(); i++) {
            if (userInput.charAt(i) == '(' ) {
                bracketCount += 1;
                if (userInput.charAt(i + 1) == '+' | userInput.charAt(i + 1) == '*' |
                        userInput.charAt(i + 1) == ':') return null;
            }
            else if (userInput.charAt(i) == ')') bracketCount -= 1;
            else if ((userInput.charAt(i) == '+' | userInput.charAt(i) == '-' |
                    userInput.charAt(i) == '*' | userInput.charAt(i) == ':') &&
                    (userInput.charAt(i + 1) == '+' | userInput.charAt(i + 1) == '-' |
                            userInput.charAt(i + 1) == '*' | userInput.charAt(i + 1) == ':')) return null;
        }
        if (bracketCount != 0) return null;
        return userInput;
    }

    public static String getAnswer(String expression) {
        StringBuilder expressionRPN = reversePolishNotation(expression);
        Fraction calculatedAnswer;
        try {
            calculatedAnswer = calculateAnswer(expressionRPN);
        } catch (ArithmeticException e) {
            return null;
        }
        return calculatedAnswer.getValue();
    }

    private static int getPriority(char element){
        if (element == '*' | element == ':')      return 3;
        else if (element == '+' | element == '-') return 2;
        else if (element == '(')                  return 1;
        else if (element == ')')                  return -1;
        else                                      return 0;
    }

    private static StringBuilder reversePolishNotation(String expression){
        StringBuilder expressionRPN = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            if (getPriority(expression.charAt(i)) == 0) expressionRPN.append(expression.charAt(i));
            if (getPriority(expression.charAt(i)) == 1) stack.push(expression.charAt(i));
            if (getPriority(expression.charAt(i)) > 1) {
                if (expression.charAt(i) == '-' && i == 0) {
                    expressionRPN.append(" 0/1 ");
                    stack.push('-');
                    continue;
                } else if (expression.charAt(i) == '-' && expression.charAt(i - 1) == '(') {
                    expressionRPN.append("0/1 ");
                    stack.push('-');
                    continue;
                } else if (expression.charAt(i) == '-' && expression.charAt(i - 1) == '/') {
                    expressionRPN.append(expression.charAt(i));
                    continue;
                }
                expressionRPN.append(" ");
                while (!(stack.empty())) {
                    if (getPriority(stack.peek()) >= getPriority(expression.charAt(i)))
                        expressionRPN.append(stack.pop());
                    else break;
                }
                stack.push(expression.charAt(i));
            }
            if (getPriority(expression.charAt(i)) == -1) {
                expressionRPN.append(" ");
                while (getPriority(stack.peek()) != 1) expressionRPN.append(stack.pop());
                stack.pop();
            }
        }
        while (!(stack.empty())) expressionRPN.append(stack.pop());
        return expressionRPN;
    }

    private static Fraction calculateAnswer(StringBuilder expressionRPN){
        String stringElement = "";
        Fraction fractionElement;
        Stack<Fraction> stack = new Stack<>();
        for (int i = 0; i < expressionRPN.length(); i++) {
            if (expressionRPN.charAt(i) == ' ') continue;
            if (getPriority(expressionRPN.charAt(i)) == 0 | (expressionRPN.charAt(i) == '-' &&
                    (i == 0 | expressionRPN.charAt(i - 1) == '/' | expressionRPN.charAt(i - 1) == '('))) {
                stringElement = "";
                while (expressionRPN.charAt(i) != ' ' && (getPriority(expressionRPN.charAt(i)) == 0 |
                        (expressionRPN.charAt(i) == '-' && (i == 0 | expressionRPN.charAt(i - 1) == '/' | expressionRPN.charAt(i - 1) == '(')))) {
                    stringElement += expressionRPN.charAt(i++);
                    if (i == expressionRPN.length()) break;
                }
                String[] parseElement = stringElement.split("/");
                int elementNum = Integer.parseInt(parseElement[0]);
                int elementDenom = Integer.parseInt(parseElement[1]);
                fractionElement = new Fraction(elementNum, elementDenom);
                stack.push(fractionElement);
            }
            if (getPriority(expressionRPN.charAt(i)) > 1) {
                Fraction second = stack.pop(), first = stack.pop();
                    switch (expressionRPN.charAt(i)) {
                        case '+' -> stack.push(Fraction.summation(first, second));
                        case '-' -> stack.push(Fraction.substraction(first, second));
                        case '*' -> stack.push(Fraction.multiplication(first, second));
                        case ':' -> stack.push(Fraction.division(first, second));
                    }
            }
        }
        return stack.peek();
    }
}
