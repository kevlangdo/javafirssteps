package com.langdo.calcenginearray;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        double[] leftVals = {100.0d, 25.0d, 225,0d, 11.0d};
        double[] rightVals = {50.0d, 92.0d, 17.0d, 3.0d};
        char[] opCodes = {'d', 'a', 's', 'm'};
        double[] results = new double[opCodes.length];
        System.out.println(args.length);

        if(args.length==0){
            for(int i = 0; i < opCodes.length; i++){
                results[i] = execute(leftVals[i], rightVals[i], opCodes[i]);
            }
            for(double currentResult: results)
                System.out.println(currentResult);
        }else if(args.length==1 && args[0].equals("interactive")){
            executeInteractively();
        }else if(args.length==3) {
            handleCommandLine(args);

        }else
            System.out.println("wrong number of arguments");
    }
    private static char symbolFromOpCode(char opCode){
        char[] opCodes = {'a','s','m','d'};
        char[] symbols = {'+','-','*','/'};
        char symbol=   ' '  ;
        for(int index = 0 ; index <= opCodes.length;index++){
            if(opCode == opCodes[index]){
                symbol = symbols[index];
                break;
            }
        }
        return symbol;
    }

    private static void handleCommandLine(String[] args) {
        char opCode = args[0].charAt(0);
        double leftVal = Double.parseDouble(args[1]);
        double rightVal = Double.parseDouble(args[2]);
        double result = execute(leftVal,rightVal,opCode);
        System.out.println(result);;
    }
    static char opCodeFromString(String operationName){
        char opCode = operationName.charAt(0);
        return opCode;
    }
    static void executeInteractively(){
        System.out.println("Enter an operation and two numbers");
        Scanner scanner = new Scanner(System.in);
        String[] userInput = scanner.nextLine().split(" ");
        performOperation(userInput);
    }

    private static void performOperation(String[] userInput) {
        char opCode = opCodeFromString(userInput[0]);

        if(opCode=='w') {

            handleWhen(userInput);
        } else{
            double leftVal = valueFromWord(userInput[1]);
            double rightVal = valueFromWord(userInput[2]);
            double result = execute(leftVal, rightVal,opCode);
            displayResult(opCode,leftVal,rightVal,result);
        }

        
    }
    private static void handleWhen(String[] parts){
        LocalDate startDate  = LocalDate.parse(parts[1]);
        long daysToAdd = (long) valueFromWord(parts[2]);
        LocalDate newDate = startDate.plusDays(daysToAdd);
        String output = String.format("%s plus %d days is %s", startDate, daysToAdd, newDate);
        System.out.println(output);

    }

    private static void displayResult(char opCode, double leftVal, double rightVal, double result) {
        char symbol = symbolFromOpCode(opCode);
       /* StringBuilder builder = new StringBuilder(30);
        builder.append(leftVal);
        builder.append(" ");
        builder.append(symbol);
        builder.append(" ");
        builder.append(rightVal);
        builder.append(" ");
        builder.append(" = ");
        builder.append(result);
        String output = builder.toString();*/
       String output = String.format("%.3f %c %.3f = %.3f", leftVal,symbol,rightVal, result);
        System.out.println(output);
    }

    static double valueFromWord(String word) {
        String[] numberWords = {
                "zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine"
        };
        double value = -1d;
        for(int index = 0; index < numberWords.length; index++) {
            if(word.equals(numberWords[index])) {
                value = index;
                break;
            }
        }
        if(value == -1d)
            value = Double.parseDouble(word);

        return value;
    }

    private static double execute(double leftVals, double rightVals, char opCode) {
        double result;
        switch (opCode){
            case 'a':
                result= leftVals + rightVals;
                break;
            case 's':
                result= leftVals - rightVals;
                break;
            case 'm':
                result= leftVals * rightVals;
                break;
            case 'd':
                result= leftVals != 0 ?  leftVals / rightVals : 0.0d;
                break;
            default:{
                System.out.println("Invalid operation");
                result = 0.0d;
            }
    }
    return result;
    }
}
