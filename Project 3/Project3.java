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

        //System.out.println(leftList + "\n\n" + rightList);

        //if even ignore 1 from the bigger side of the one youre throwing away
        findMed(leftList, rightList);

    }

    static void findMed (ArrayList<Integer> firstList, ArrayList<Integer> secondList) {
        int median = -1;
        int size = firstList.size();
        int size2 = secondList.size();
        boolean even = false;

        System.out.println("first " + size + " second " + size2);

        int index = -1;
        while (size > 4) {

            if (size > 1) {
                if (size % 2 ==  1) {
                    index = size / 2;
                }
                else if (size % 2 == 0) {
                    index = size / 2 - 1;
                    even = true;
                }
                int firstInt = firstList.get(index);
                int secondInt = secondList.get(index);
        
                if (firstInt > secondInt) {
                    if (even == false) {
                        ArrayList<Integer> first2 = new ArrayList<Integer>(firstList.subList(0, index));
                        ArrayList<Integer> second2 = new ArrayList<Integer>(secondList.subList(index, size));

                        System.out.println("first2 " + first2 + "\nsecond2 " + second2); 
        
                        findMed(first2, second2);
                    }
                    else  { //if index is even so its an odd #
                        ArrayList<Integer> first2 = new ArrayList<Integer>(firstList.subList(0, index + 1));
                        ArrayList<Integer> second2 = new ArrayList<Integer>(secondList.subList(index, size));
        
                        System.out.println("first2 " + first2 + "\nsecond2 " + second2); 

                        findMed(first2, second2);
                    }
                }
                else if (firstInt < secondInt) {
                    if (even == false) {
                        ArrayList<Integer> first2 = new ArrayList<Integer>(secondList.subList(0, index));
                        ArrayList<Integer> second2 = new ArrayList<Integer>(firstList.subList(index, size));
                        System.out.println("first2 " + first2 + "\nsecond2 " + second2); 

                        findMed(first2, second2);
                    }
                    else  { //if index is even so its an odd #
                        ArrayList<Integer> first2 = new ArrayList<Integer>(secondList.subList(0, index + 1));
                        ArrayList<Integer> second2 = new ArrayList<Integer>(firstList.subList(index, size));
                        System.out.println("first2 " + first2 + "\nsecond2 " + second2); 

                        findMed(first2, second2);
                    }
                }
            }
            else {
                System.out.println("firstL " + firstList + "\nsecondList " + secondList);
                double average = (firstList.get(0) + secondList.get(0)) / 2;
                System.out.println("The Average: " + average);
            }
        }
    
    }
}