package org.example;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class TestWork {

    final static ArrayList<String> indexes = new ArrayList<>();
    final static ArrayList<Integer[]> myIntFinal = new ArrayList<>();
    final static char comma = ',';
    final static char dash = '-';
    static ArrayList<ArrayList<Integer>> arrayListBest;

    public static void firstFunction() {

        boolean OneLineChar = false;
        for (String item : indexes) {
            // массив, который содержим финальный набор преобразованных чисел
            ArrayList<Integer> currentInt = new ArrayList<>();
            if (item.length() == 1) {
                OneLineChar = true;
            }
            char lastChar = '1';
            boolean isTireExists = false;
            int startTure = 0;
            int CheckTwoDigit = 0;
            boolean NoNumber = false;
            int currentPosition = 0;
            int lastTwoDigit = 0;
            boolean TwoDigit = false;

            for (char it : item.toCharArray()) {
                if (it == comma) {
                    CheckTwoDigit = 0;
                    NoNumber = true;
                    if (TwoDigit) {
                        if (isTireExists) {
                            isTireExists = false;
                            IntStream.range(startTure, lastTwoDigit + 1)
                                    .forEachOrdered(currentInt::add);
                        } else {
                            currentInt.add(lastTwoDigit);
                        }
                        TwoDigit = false;
                    } else {
                        if (isTireExists) {
                            isTireExists = false;
                            IntStream.range(startTure, Integer.parseInt(String.valueOf(lastChar)) + 1)
                                    .forEachOrdered(currentInt::add);
                        } else {
                            currentInt.add(Integer.parseInt(String.valueOf(lastChar)));
                        }
                    }
                }
                if (it == dash) {
                    CheckTwoDigit = 0;
                    isTireExists = true;
                    NoNumber = true;
                    if (TwoDigit) {
                        startTure = lastTwoDigit;
                        TwoDigit = false;
                    } else {
                        startTure = Integer.parseInt(String.valueOf(lastChar));
                    }
                }
                currentPosition++;

                if (!NoNumber) {
                    CheckTwoDigit++;
                    if (CheckTwoDigit == 2) {
                        TwoDigit = true;
                        String s = String.valueOf(lastChar) + it;
                        lastTwoDigit = Integer.parseInt(s);
                        if (currentPosition == item.length()) {
                            if (isTireExists) {
                                isTireExists = false;
                                IntStream.range(startTure, lastTwoDigit + 1)
                                        .forEachOrdered(currentInt::add);
                            } else {
                                currentInt.add(lastTwoDigit);
                            }
                        }
                        CheckTwoDigit = 0;
                    }
                }
                if (currentPosition == item.length() && lastChar == comma) {
                    currentInt.add(Integer.parseInt(String.valueOf(it)));
                }
                if (currentPosition == item.length() && lastChar == dash) {
                    isTireExists = false;
                    IntStream.range(startTure, Integer.parseInt(String.valueOf(it)) + 1)
                            .forEachOrdered(currentInt::add);
                }
                if (OneLineChar) {
                    currentInt.add(Integer.parseInt(String.valueOf(it)));
                    OneLineChar = false;
                }
                lastChar = it;
                NoNumber = false;
            }
            Integer[] ara = currentInt.toArray(new Integer[0]);
            myIntFinal.add(ara);
        }
    }
    public static void SecondsFunction(ArrayList<Integer[]> sendsArrays) {
        arrayListBest = new ArrayList<>();

        ArrayList<Integer> listInt = new ArrayList<>();
        Integer[] first = sendsArrays.get(0);

        for (int a : first) {
            listInt.add(a);
            conclusion(1, sendsArrays, listInt);
            listInt.clear();
        }
        System.out.println("Финальный массив = " + arrayListBest.toString());
    }
    static void conclusion(int currentInt, ArrayList<Integer[]> currentArray, ArrayList<Integer> myMainInt) {
        if (currentInt == (currentArray.size() - 1)) {
            for (int b : currentArray.get(currentInt)) {
                ArrayList<Integer> fList = new ArrayList<>(myMainInt);
                fList.add(b);
                arrayListBest.add(fList);
            }
        } else {
            for (int b : currentArray.get(currentInt)) {
                if (myMainInt.size() <= currentInt) {
                    myMainInt.add(b);
                } else {
                    myMainInt.set(currentInt, b);
                }
                conclusion(currentInt + 1, currentArray, myMainInt);
            }
        }
    }

    public static void main(String[] args) {
        // начальные условия
        indexes.add("1,3-5");
        indexes.add("2");
        indexes.add("3-4");

        firstFunction();
        SecondsFunction(myIntFinal);
    }
}
