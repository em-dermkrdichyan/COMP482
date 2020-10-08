/*
Emmanoel Dermkrdichyan MW 3:30 PM class
Project 1
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;



public class Project1 {
    public static void main(String[] args) throws FileNotFoundException {
        File text = new File ("input1.txt"); //name of the input file
        ArrayList<Integer> numbers = new ArrayList<Integer> ();
        ArrayList<Integer> targets = new ArrayList<Integer> ();

        Scanner scanner = new Scanner(text);

        /*
        during a while loop if the scanner has a next integer keep scanning in integers, if the scanned integer == 0 we know that we hit the divider.
        then we dont use the 0 integer and go into a different while loop where we insert the rest of the integers into an arraylist of target values.
        If we havent hit a 0 yet we keep adding the integers into the numbers arraylist. I didnt see a need to make this its own method since
        it is very short and it would be too much work to pass in the two separate arraylists of numbers and targets and return them.
        */
        while(scanner.hasNextInt()) {
            int number = scanner.nextInt();
            if (number == 0) {
                while(scanner.hasNextInt()) {
                    int target = scanner.nextInt();
                    targets.add(target);
                }
            }
            else {
                numbers.add(number);
            }
        }

        //basic collections sort to sort the numbers  to make it easier to search for a sum.
        Collections.sort(numbers);

        /*
        this forloop executes the same number of times as the amount of targets we have. it calls the findSums method with a new target each time and 
        passes in the arraylist of integers that we will use to try and find a sum for the target.
        */
        for (int i = 0; i < targets.size(); i++) {
            findSums(numbers, targets.get(i));
        }
    }

    static void findSums (ArrayList<Integer> numbers, int target) {
        int midPt, rightPt;
        int listSize = numbers.size() - 1;
        boolean printed = false; //use this flag to determine if we already printed the YES statement or not

        /*
        we start the left pointer at 0 the middle pointer +1 to the right of left pointer and the left pointer at the end of the arraylist.
        then inside a do while loop we pull the three numbers at those indices and sum them. If the sum is == to the target then we print a statement saying so, and
        we set the leftPt to listSize - 1 to finish the for loop and we also set the flag to say that we printed the statement so that it does not print the NO statement once it
        is out of the loop. If the sum is bigger than the target we move the right pointer down 1, and if the sum is smaller we move the middle pointer up 1. The left pointer is moved via
        the for loop so we dont have to worry about that. 
        */
        for (int leftPt = 0; leftPt < listSize - 1; leftPt++) {
            midPt = leftPt + 1;
            rightPt = listSize;

            do {
                int num1 = numbers.get(leftPt);
                int num2 = numbers.get(midPt);
                int num3 = numbers.get(rightPt);
                int newSum = num1 + num2 + num3;

                if (newSum == target) {
                    System.out.println(target + " YES");
                    leftPt = listSize - 1;
                    printed = true;
                }
                else if (newSum > target) {
                    rightPt--;
                }
                else if (newSum < target) {
                    midPt++;
                }
                
            } while (midPt < rightPt);
        }
        /*
        we check this flag to make sure we havent already printed a yes statement for this target value,
        if we have not then we print out a statement saying no match was found.
        */
        if (printed == false) {
            System.out.println(target + " NO");
        }
    }

}