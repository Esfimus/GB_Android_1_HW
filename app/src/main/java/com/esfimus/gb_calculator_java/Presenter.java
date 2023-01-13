package com.esfimus.gb_calculator_java;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public class Presenter implements Parcelable {

    private final StringBuilder mainResult = new StringBuilder();
    private final StringBuilder subResult = new StringBuilder();
    private boolean somethingWrong;

    public Presenter() {
        this.somethingWrong = false;
    }

    public void addSymbols(String line) {
        this.mainResult.append(line);
    }

    public String getMainResult() {
        return this.mainResult.toString();
    }

    public String getSubResult() {
        return this.subResult.toString();
    }

    /**
     * Receives values from user input
     * @param value given symbol
     */
    public void passValue(String value) {
        if (this.subResult.length() > 0) {
            this.subResult.delete(0, this.subResult.length());
        }
        if (this.somethingWrong) {
            this.somethingWrong = false;
        }
        switch (value) {
            case "c" : {
                this.mainResult.delete(0, this.mainResult.length());
                return;
            }
            case "d" : {
                if (this.mainResult.length() > 0) {
                    this.mainResult.deleteCharAt(this.mainResult.length() - 1);
                }
                return;
            }
        }
        distributor(value);
    }

    /**
     * Distributes digits, operators, brackets and dot between corresponding methods
     * @param value given symbol
     */
    private void distributor(String value) {
        if (value.matches("[0-9]")) {
            this.digitsAnalyser(value);
        } else if (value.equals(".")) {
            this.dotAnalyser();
        } else if (value.equals("-")) {
            this.minusAnalyser();
        } else if (value.matches("[x/+]")) {
            this.operatorAnalyser(value);
        } else if (value.equals("(")) {
            this.bracketsAnalyser();
        } else if (value.equals("=")) {
            this.equalsAnalyser();
        }
    }

    /**
     * Adds digits to input string or ignoring it
     * @param value given symbol
     */
    private void digitsAnalyser(String value) {
        // adding digit as first symbol, after other digits, after operators and open bracket
        if (this.mainResult.length() == 0 ||
            this.mainResult.substring(this.mainResult.toString().length() - 1).matches("[1-9.\\-+x/(]")) {
            this.mainResult.append(value);
        // special attention to zero: adding decimal fraction dot to already existing single zero
        } else if (this.mainResult.substring(this.mainResult.toString().length() - 1).matches("0")) {
            if (this.mainResult.toString().matches(".*[\\-+x/(]+0") ||
                this.mainResult.toString().matches("0")) {
                this.mainResult.append(".");
            }
            this.mainResult.append(value);
        }
    }

    /**
     * Adds dot to input string or ignoring it
     */
    private void dotAnalyser() {
        // adding dot with leading zero as first symbols, after operators and open bracket
        if (this.mainResult.length() == 0 ||
            this.mainResult.substring(this.mainResult.toString().length() - 1).matches("[\\-+x/(]")) {
            this.mainResult.append("0.");
        // adding dot after digit with condition that this dot is only single
        } else if (this.mainResult.substring(this.mainResult.toString().length() - 1).matches("[0-9]")) {
            if (this.mainResult.toString().matches("[\\-+x/(]*[0-9]+") ||
                this.mainResult.toString().matches(".*[\\-+x/(]+[0-9]+")) {
                this.mainResult.append(".");
            }
        }
    }

    /**
     * Adds minus operator to input string or ignoring it
     */
    private void minusAnalyser() {
        // adding minus after any digit or closing bracket
        if (this.mainResult.length() == 0 ||
            this.mainResult.substring(this.mainResult.toString().length() - 1).matches("[0-9)]")) {
            this.mainResult.append("-");
        // changing dot and other operators to minus
        } else if (this.mainResult.substring(this.mainResult.toString().length() - 1).matches("[.+x/]")) {
            this.mainResult.deleteCharAt(this.mainResult.length() - 1);
            this.mainResult.append("-");
        }
    }

    /**
     * Adds other operators to input string or ignoring it
     * @param value given symbol
     */
    private void operatorAnalyser(String value) {
        // no operator is allowed when the line is empty (minus is allowed for negative numbers)
        if (this.mainResult.length() == 0) {
            return;
        // adding any operator after any digit or closing bracket
        } else if (this.mainResult.substring(this.mainResult.toString().length() - 1).matches("[0-9)]")) {
            this.mainResult.append(value);
        // replacing dot and other operators (minus at the beginning cannot be replaced)
        } else if (this.mainResult.substring(this.mainResult.toString().length() - 1).matches("[.+x/]") ||
            this.mainResult.toString().matches(".+-")) {
            this.mainResult.deleteCharAt(this.mainResult.length() - 1);
            this.mainResult.append(value);
        }
    }

    /**
     * Adds brackets to input string or ignoring it
     */
    private void bracketsAnalyser() {
        // counting brackets to decide which bracket to add with single button
        int openBrackets = 0;
        int closeBrackets = 0;
        for (int i = 0; i < this.mainResult.length(); i++) {
            if (this.mainResult.charAt(i) == '(') {
                openBrackets++;
            }
            if (this.mainResult.charAt(i) == ')') {
                closeBrackets++;
            }
        }
        if (mainResult.length() == 0 || this.mainResult.substring(this.mainResult.toString().length() - 1).matches("\\(")) {
            return;
        // adding closing bracket after any digit or closing bracket but only if there are enough opening brackets
        } else if (this.mainResult.substring(this.mainResult.toString().length() - 1).matches("[0-9)]") &&
            openBrackets > closeBrackets) {
            this.mainResult.append(")");
        // replacing dot by closing bracket but only if there are enough opening brackets
        } else if (this.mainResult.substring(this.mainResult.toString().length() - 1).matches("[.]") &&
            openBrackets > closeBrackets) {
            this.mainResult.deleteCharAt(this.mainResult.length() - 1);
            this.mainResult.append(")");
        // adding opening bracket after digits and any operator
        } else if (this.mainResult.toString().matches(".+[\\-+x/]")) {
            this.mainResult.append("(");
        }
    }

    /**
     * Analyses the input string, calculates the result,
     * places the result in the main text field and
     * places the whole expression to auxiliary field
     */
    private void equalsAnalyser() {
        this.subResult.append(this.mainResult);
        this.mainResult.delete(0, this.mainResult.length());
        try {
            // no result if the line is empty or if there is only one minus
            if (this.subResult.length() == 0 || this.subResult.toString().matches("-")) {
                this.subResult.delete(0, this.subResult.length());
                return;
            }
            // when the line is not finished and the last symbol is opening bracket
            if (this.subResult.substring(this.subResult.toString().length() - 1).matches("[(]")) {
                this.subResult.deleteCharAt(this.subResult.length() - 1);
            }
            // when the line is not finished and the last symbol is operator
            if (this.subResult.substring(this.subResult.toString().length() - 1).matches("[\\-+x/]")) {
                // clearing the last operator if it follows closing bracket
                if (this.subResult.toString().matches(".*[)][\\-+x/]")) {
                    this.subResult.deleteCharAt(this.subResult.length() - 1);
                // when operator follows any number, adding this whole number at the end of expression inside brackets
                } else if (this.subResult.toString().matches(".*[0-9][\\-+x/]")) {
                    // saving the last operator and removing it from expression
                    char operator = this.subResult.charAt(this.subResult.length() - 1);
                    this.subResult.deleteCharAt(this.subResult.length() - 1);
                    // building the last number in reversed order while cleaning the result from this number
                    System.out.println("subResult " + this.subResult);
                    StringBuilder numCollector = new StringBuilder();
                    StringBuilder reversedSubResult = new StringBuilder();
                    reversedSubResult.append(this.subResult);
                    reversedSubResult.reverse();
                    for (int i = 0; i < reversedSubResult.length(); i++) {
                        String lastChar = String.valueOf(reversedSubResult.charAt(i));
                        if (lastChar.matches("[0-9.]")) {
                            numCollector.append(lastChar);
                            this.subResult.deleteCharAt(this.subResult.length() - 1);
                        } else {
                            break;
                        }
                    }
                    // returning the last number in form of expression with itself and the last operator
                    String lastNumber = numCollector.reverse().toString();
                    this.subResult.append("(").append(lastNumber).append(operator).append(lastNumber).append(")");
                }
            }
            // counting brackets and adding the missing ones at the end of expression
            int openBrackets = 0;
            int closeBrackets = 0;
            for (int i = 0; i < this.subResult.length(); i++) {
                if (this.subResult.charAt(i) == '(') {
                    openBrackets++;
                }
                if (this.subResult.charAt(i) == ')') {
                    closeBrackets++;
                }
            }
            if (openBrackets > closeBrackets) {
                while (openBrackets > closeBrackets) {
                    this.subResult.append(")");
                    closeBrackets++;
                }
            }
            // starting calculations
            Calculations calculations = new Calculations(subResult.toString());
            String result = calculations.getNumber();
            if (calculations.error) somethingWrong = true;
            mainResult.append(result);
        } catch (Exception e) {
            this.somethingWrong = true;
        }
    }

    // Parcel package below
    protected Presenter(Parcel in) {
        this.mainResult.append(in.readString());
    }

    public static final Creator<Presenter> CREATOR = new Creator<>() {
        @Override
        public Presenter createFromParcel(Parcel in) {
            return new Presenter(in);
        }

        @Override
        public Presenter[] newArray(int size) {
            return new Presenter[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(this.mainResult.toString());
    }
}
