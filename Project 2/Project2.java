/*
Emmanoel Dermkrdichyan MW 3:30 PM class
Project 2
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Point;
import java.util.ArrayList;




public class Project2 {
    public static void main(String[] args) throws FileNotFoundException {
        File text = new File ("input2.txt"); //name of the input file
        ArrayList<Point> points = new ArrayList<Point>();
        Scanner scanner = new Scanner(text);

        int smallestX = scanner.nextInt(),largestX = 0;
        int smallestY = scanner.nextInt(), largestY = 0;
        int counter = 0, first = smallestX, second = smallestY;

        while(scanner.hasNextInt()) {
            counter++;
            if (counter > 1) {
                first = scanner.nextInt();
                second = scanner.nextInt();
            }

            System.out.println("first: " + first + " second: " + second);
            Point location = new Point(first, second);
            points.add(location);

            if (first < smallestX) {
                smallestX = first;
            }
            else if (first > largestX) {
                largestX = first;
            }

            if (second < smallestY) {
                smallestY = second;
            }
            else if (second > largestY) {
                largestY = second;
            }
        }

        //points.get(0).getX();
        System.out.println("sX: " + smallestX + " lX: " + largestX + " sY: " + smallestY + " lY: " + largestY);

        for (int i = 0; i < points.size(); i++) {
            System.out.println(points.get(i));
        }
        System.out.println(points);
    }







}