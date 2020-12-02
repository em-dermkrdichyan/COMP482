/*
Emmanoel Dermkrdichyan MW 3:30 PM class
Project 3
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Project3 {
    public static void main(String[] args) throws FileNotFoundException {
        File text = new File ("input3.txt"); //name of the input file
        ArrayList<Integer> leftList = new ArrayList<Integer>();
        ArrayList<Integer> rightList = new ArrayList<Integer>();

        Scanner scanner = new Scanner(text);

        int size = scanner.nextInt();

        for (int i = 0; i < (size * 2 + 1); i++) {
            if (i < size) {
                leftList.add(scanner.nextInt());
            }
            else if (i > size) {
                rightList.add(scanner.nextInt());
            }
        }

        scanner.close();

        //System.out.println(leftList + "\n\n" + rightList);
        Collections.sort(leftList); //remove these 
        Collections.sort(rightList); //remove

        //if even ignore 1 from the bigger side of the one youre throwing away
        findMed(leftList, rightList);
        countInversions (leftList, rightList);

    }

    static void findMed (ArrayList<Integer> firstList, ArrayList<Integer> secondList) {
        int size = firstList.size();
        int size2 = secondList.size();

        ArrayList<Integer> first2 = firstList;
        ArrayList<Integer> second2 = secondList;
        boolean even = false;


        int index = -1;
        while (size > 2) {
            System.out.println("first size " + size + " second size " + size2);

            if (size % 2 ==  1) {
                index = size / 2;
                even = false;
            }
            else if (size % 2 == 0) {
                index = size / 2 - 1;
                even = true;
            }
            int firstInt = first2.get(index);
            int secondInt = second2.get(index);

            System.out.println("first int " + firstInt + " second int " + secondInt);
        
            if (firstInt > secondInt) {
                if (even == false) {
                    first2 = new ArrayList<Integer>(first2.subList(0, index + 1));
                    second2 = new ArrayList<Integer>(second2.subList(index, size));
                    size = first2.size();

                    //System.out.println("firstaaa2 " + first2 + "\nsecond2 " + second2); //good
        
                }
                else  { //if index is even so its an odd #
                    first2 = new ArrayList<Integer>(first2.subList(0, index + 2));
                    second2 = new ArrayList<Integer>(second2.subList(index, size));
                    size = first2.size();

                    System.out.println("firstbbb2 " + first2 + "\nsecond2 " + second2); //check these


                }
            }
            else if (firstInt < secondInt) {
                if (even == false) {
                    first2 = new ArrayList<Integer>(first2.subList(index, size));
                    second2 = new ArrayList<Integer>(second2.subList(0, index + 1));
                    size = first2.size();

                    //System.out.println("firstccc2 " + first2 + "\nsecond2 " + second2); //good

                }
                else  { //if index is even so its an odd #
                    first2 = new ArrayList<Integer>(first2.subList(index, size));
                    second2 = new ArrayList<Integer>(second2.subList(0, index + 2));
                    size = first2.size();

                    System.out.println("firstddd2 " + first2 + "\nsecond2 " + second2); //check these

                }
            }
        }

        first2.addAll(second2);

        Collections.sort(first2);

        int temp = first2.get(1) + first2.get(2);

        double average = (first2.get(1) + first2.get(2)) / 2.0;
        System.out.println(average);
    }

    static void countInversions (ArrayList<Integer> firstList, ArrayList<Integer> secondList) {
        //System.out.println(firstList + " " + secondList);
        ArrayList<Integer> inversions = new ArrayList<Integer>();
        int counter = firstList.size();
        int totalCT = 0;

        int leftPT = 0;
        int rightPT = 0;
        while (leftPT < firstList.size() && rightPT < secondList.size()) {
            if (firstList.get(leftPT) < secondList.get(rightPT)) {
                counter--;
                inversions.add(leftPT);
                leftPT++;
            }
            else if (firstList.get(leftPT) > secondList.get(rightPT)) {
                totalCT = totalCT + counter;
                inversions.add(rightPT);
                rightPT++;
            }
        }

        System.out.println(totalCT);

    }
}