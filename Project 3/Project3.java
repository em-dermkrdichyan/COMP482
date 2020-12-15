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

        //the first integer is the size of each array
        int size = scanner.nextInt();

        //this forloop splits the inputs into the two separate arrays
        for (int i = 0; i < (size * 2 + 1); i++) {
            if (i < size) {
                leftList.add(scanner.nextInt());
            }
            else if (i > size) {
                rightList.add(scanner.nextInt());
            }
        }

        scanner.close();

        //calling the median function
        findMed(leftList, rightList);
        //calling the inversion function
        countInversions (leftList, rightList);

    }

    /*
    This function checks the median of each arraylist and throws away the bigger half of the one
    with the larger median. Then throws away the smaller half of the arraylist with the smaller median.
    After it throws away enough of the arraylists where the size of each arraylist is equal to two it then
    merges the two lists and finds the average of the two middle integers and prints it out.
    */
    static void findMed (ArrayList<Integer> firstList, ArrayList<Integer> secondList) {
        int size = firstList.size();

        //assigned the arraylists passed in to these ones so we could easily modify these ones while keeping
        //the original ones the same.
        ArrayList<Integer> first2 = firstList;
        ArrayList<Integer> second2 = secondList;
        boolean even = false;


        int index = -1;
        while (size > 2) {
            //setting the index of the median depending on wether the array size is even or odd
            if (size % 2 ==  1) {
                index = size / 2;
                even = false;
            }
            else if (size % 2 == 0) {
                index = size / 2 - 1;
                even = true;
            }
            //pulling the medians of each arraylist
            int firstInt = first2.get(index);
            int secondInt = second2.get(index);
        
            /*
            There are four cases here either with any combination of (even or odd) and (first median is bigger or second median is bigger)
            if its even we have to keep an extra integer from the array with the bigger median to keep the sizes equal.
            */
            if (firstInt > secondInt) {
                if (even == false) {
                    first2 = new ArrayList<Integer>(first2.subList(0, index + 1));
                    second2 = new ArrayList<Integer>(second2.subList(index, size));
                    size = first2.size();        
                }
                else  {
                    first2 = new ArrayList<Integer>(first2.subList(0, index + 2));
                    second2 = new ArrayList<Integer>(second2.subList(index, size));
                    size = first2.size();
                }
            }
            else if (firstInt < secondInt) {
                if (even == false) {
                    first2 = new ArrayList<Integer>(first2.subList(index, size));
                    second2 = new ArrayList<Integer>(second2.subList(0, index + 1));
                    size = first2.size();
                }
                else  {
                    first2 = new ArrayList<Integer>(first2.subList(index, size));
                    second2 = new ArrayList<Integer>(second2.subList(0, index + 2));
                    size = first2.size();
                }
            }
        }

        //combines the two arraylists and sorts them
        first2.addAll(second2);
        Collections.sort(first2);

        //gets the average of the two middle integers and prints them out
        double average = (first2.get(1) + first2.get(2)) / 2.0;
        System.out.println(average);
    }

    /*
    This method counts the inversions of the arraylists. If it takes from the left list it decrements the counter,
    if it takes from the right arraylist it adds the counter to the total. Then at the end it prints out the total counter.
    */
    static void countInversions (ArrayList<Integer> firstList, ArrayList<Integer> secondList) {
        ArrayList<Integer> inversions = new ArrayList<Integer>();
        int counter = firstList.size(); //initializing the counter to the size of the left array
        int totalCT = 0;

        int leftPT = 0;
        int rightPT = 0;
        while (leftPT < firstList.size() && rightPT < secondList.size()) {
            //take from the left arraylist and decrement counter while moving the left pointer up
            if (firstList.get(leftPT) < secondList.get(rightPT)) {
                counter--;
                inversions.add(leftPT);
                leftPT++;
            }
            //take from the right arraylist and add the counter to the total counter and move the right pointer up
            else if (firstList.get(leftPT) > secondList.get(rightPT)) {
                totalCT = totalCT + counter;
                inversions.add(rightPT);
                rightPT++;
            }
        }

        System.out.println(totalCT);

    }
}