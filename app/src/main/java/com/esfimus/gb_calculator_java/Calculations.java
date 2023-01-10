package com.esfimus.gb_calculator_java;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.Objects;

public class Calculations {

    private final double result;
    Boolean error = false;

    public Calculations(String expression) {
        ArrayList<String> infixList = expressionStringToList(expression);
        ArrayList<String> postfixList = expressionListToPostfix(infixList);
        this.result = postfixToResult(postfixList);
    }

    /**
     * Converts string expression to list
     */
    private ArrayList<String> expressionStringToList(String line) {
        ArrayList<String> list = new ArrayList<>();
        StringBuilder digit = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            String c = String.valueOf(line.charAt(i));
            if (c.matches("[0-9.]")) {
                digit.append(c);
            } else if (c.matches("[()\\-+x/]")) {
                if (digit.length() != 0) list.add(digit.toString());
                digit.delete(0, digit.length());
                list.add(c);
            }
        }
        if (digit.length() != 0) list.add(digit.toString());
        if (list.get(0).equals("-")) list.add(0, "0");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("x")) list.set(i, "*");
        }
        return list;
    }

    /**
     * Converts list of all numbers, brackets and operators to postfix form for further calculations
     */
    private ArrayList<String> expressionListToPostfix(ArrayList<String> list) {
        ArrayList<String> postfixList = new ArrayList<>();
        try {
            // converting infix to postfix form
            ArrayList<String> stack = new ArrayList<>();
            for (String v : list) {
                // number is added to postfix form at once
                if (v.matches(".*[0-9]")) {
                    postfixList.add(v);
                    // operators and brackets are added according to their priority
                } else {
                    // when the stack is empty, operator or bracket goes there at once
                    if (stack.isEmpty()) {
                        stack.add(v);
                    } else {
                        // higher priority operators are collected in stack above lower priority operators
                        if (v.matches("[*/]")) {
                            if (stack.get(stack.size() - 1).matches("[\\-+(]")) {
                                stack.add(v);
                                // similar priority operators change each other
                            } else if (stack.get(stack.size() - 1).matches("[*/]")) {
                                postfixList.add(stack.get(stack.size() - 1));
                                stack.remove(stack.size() - 1);
                                stack.add(v);
                            }
                            // lower priority operators unload stack to postfix completely or until the first bracket is met
                        } else if (v.matches("[\\-+]")) {
                            for (int i = stack.size() - 1; i >= 0; i--) {
                                if (stack.get(i).equals("(")) break;
                                postfixList.add(stack.get(stack.size() - 1));
                                stack.remove(stack.size() - 1);
                            }
                            stack.add(v);
                            // opening brackets have the highest priority and move to stack
                        } else if (v.equals("(")) {
                            stack.add(v);
                            // closing brackets unload stack to postfix completely or until the first bracket is met
                        } else if (v.equals(")")) {
                            for (int i = stack.size() - 1; i >= 0; i--){
                                String value = stack.get(i);
                                if (!Objects.equals(stack.get(i), "(") && !Objects.equals(stack.get(i), ")")) {
                                    postfixList.add(stack.get(stack.size() - 1));
                                }
                                stack.remove(stack.size() - 1);
                                if (value.equals("(")) break;
                            }
                        }
                    }
                }
            }
            // any elements left in stack are moved to postfix
            if (!stack.isEmpty()) {
                for (int i = stack.size() - 1; i >= 0; i--) {
                    postfixList.add(stack.get(stack.size() - 1));
                    stack.remove(stack.size() - 1);
                }
            }
        } catch (Exception e) {
            this.error = true;
        }
        return postfixList;
    }

    /**
     * Calculates the result of postfix form expression
     */
    private double postfixToResult(ArrayList<String> list) {
        double res = 0.0;
        try {
            ArrayList<String> stack = new ArrayList<>();
            for (String v : list) {
                stack.add(v);
                String operator;
                String num1;
                String num2;
                // calculating blocks of two numbers and operator
                if (stack.size() >= 3 &&
                        stack.get(stack.size() - 1).matches("[\\-+*/]") &&
                        stack.get(stack.size() - 2).matches(".*[0-9]") &&
                        stack.get(stack.size() - 3).matches(".*[0-9]")) {
                    operator = stack.get(stack.size() - 1);
                    num2 = stack.get(stack.size() - 2);
                    num1 = stack.get(stack.size() - 3);
                    String miniExpression = String.valueOf(calculateTwoNumbers(Double.parseDouble(num1), Double.parseDouble(num2), operator));
                    stack.remove(stack.size() - 1);
                    stack.remove(stack.size() - 1);
                    stack.remove(stack.size() - 1);
                    stack.add(miniExpression);
                }
            }
            // representing the final result
            if (stack.size() == 1) {
                res = Double.parseDouble(stack.get(0));
            } else if (stack.size() == 2 && stack.get(stack.size() - 1).equals("-")) {
                res = Double.parseDouble("-" + stack.get(0));
            }
        } catch (Exception e) {
            this.error = true;
        }
        return res;
    }

    /**
     * Calculates two numbers with given operator
     */
    private double calculateTwoNumbers(double num1, double num2, String operator) {
        double res = 0.0;
        try {
            switch (operator) {
                case "*" : {
                    res = num1 * num2;
                    return res;
                }
                case "/" : {
                    res = num1 / num2;
                    return res;
                }
                case "+" : {
                    res = num1 + num2;
                    return res;
                }
                case "-" : {
                    res = num1 - num2;
                    return res;
                }
            }
        } catch (Exception e) {
            this.error = true;
        }
        return res;
    }

    /**
     * Cuts .0 for integer numbers and limits the precision of double to n decimal places
     */
    @SuppressLint("DefaultLocale")
    public String getNumber() {
        String numberString = String.valueOf(this.result);
        String finalResult = "0";
        if (numberString.matches(".*[.]0")) {
            finalResult = numberString.substring(0, numberString.length() - 2);
        } else if (numberString.matches(".*[.][0-9]{7,}")) {
            finalResult = String.format("%.7f", this.result);
            finalResult = finalResult.replace(",", ".");
        }
        return finalResult;
    }
}
