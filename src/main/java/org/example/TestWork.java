package org.example;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class TestWork {
    final static ArrayList<String> indexes = new ArrayList<>();
    final static ArrayList<Integer[]> myIntFinal = new ArrayList<>();
    final static char comma = ',';
    final static char dash = '-';
    static ArrayList<ArrayList<Integer>> arrayListBest;

    public static ArrayList<Integer[]> firstFunction(ArrayList<String> index) {

        boolean oneLineChar = false;
        for (String item: index) {
            // массив, который содержим финальный набор преобразованных чисел
            ArrayList<Integer> currentInt = new ArrayList<>();
            if (item.length() == 1) {
                oneLineChar = true;
            }
            char lastChar = '1';
            boolean isTireExists = false;
            int startTure = 0;
            int checkTwoDigit = 0;
            boolean noNumber = false;
            int currentPosition = 0;
            int lastTwoDigit = 0;
            boolean twoDigit = false;

            for (char it : item.toCharArray()) {
                if (it == comma) {
                    checkTwoDigit = 0;
                    noNumber = true;
                    if (twoDigit) {
                        if (isTireExists) {
                            isTireExists = false;
                            IntStream.range(startTure, lastTwoDigit + 1)
                                    .forEachOrdered(currentInt::add);
                        } else {
                            currentInt.add(lastTwoDigit);
                        }
                        twoDigit = false;
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
                    checkTwoDigit = 0;
                    isTireExists = true;
                    noNumber = true;
                    if (twoDigit) {
                        startTure = lastTwoDigit;
                        twoDigit = false;
                    } else {
                        startTure = Integer.parseInt(String.valueOf(lastChar));
                    }
                }
                currentPosition++;

                if (!noNumber) {
                    checkTwoDigit++;
                    if (checkTwoDigit == 2) {
                        twoDigit = true;
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
                        checkTwoDigit = 0;
                    }
                }
                if (oneLineChar) {
                    currentInt.add(Integer.parseInt(String.valueOf(it)));
                    oneLineChar = false;
                }
                lastChar = it;
                noNumber = false;
            }
            Integer[] ara = currentInt.toArray(new Integer[0]);
            myIntFinal.add(ara);
        }
        return myIntFinal;
    }
    public static void secondsFunction(ArrayList<Integer[]> sendsArrays) {
        arrayListBest = new ArrayList<>();

        ArrayList<Integer> listInt = new ArrayList<>();
        Integer[] first = sendsArrays.get(0);

        for (int a: first) {
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
            for (int b: currentArray.get(currentInt)) {
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
        indexes.add("12,15-17,9,43");
        indexes.add("10-14,3,5,9,10-11");
        indexes.add("2,4-6,70-81");

        firstFunction(indexes);
        secondsFunction(myIntFinal);
    }
}
