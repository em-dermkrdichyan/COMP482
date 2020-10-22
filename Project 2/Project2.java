/*
Emmanoel Dermkrdichyan MW 3:30 PM class
Project 2
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Point;


public class Project2 {
    public static void main(String[] args) throws FileNotFoundException {
        File text = new File ("input2.txt"); //name of the input file
        ArrayList<Point> points = new ArrayList<Point>();
        Scanner scanner = new Scanner(text);

        int smallestX = scanner.nextInt(),largestX = 0;
        int smallestY = scanner.nextInt(), largestY = 0;
        int counter = 0, first = smallestX, second = smallestY;
        Point midPoint;


        while(scanner.hasNextInt()) {
            counter++;
            if (counter > 1) {
                first = scanner.nextInt();
                second = scanner.nextInt();
            }

            //System.out.println("first: " + first + " second: " + second);
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

        scanner.close();

        stepThrough(points, smallestX, smallestY, largestX, largestY);
    }

    static void stepThrough(ArrayList<Point> points, int sx, int sy, int lx, int ly) {
        Point bestLOne = new Point (-1, -1);
        Point bestLTwo = new Point (-1, -1);
        int bestDistOne = -1;
        double bestDistTwo = -1;
        //DecimalFormat format = new DecimalFormat("0.##############################");
        //System.out.println(format.format(price));

        for (int x = sx; x <= lx; x++) {
            for (int y = sy; y <= ly; y++) {
                Point passingPoint = new Point (x, y);
                int distOne = calcLOne(points, passingPoint);
                
                if (bestDistOne == -1) {
                    bestDistOne = distOne;
                    bestLOne.setLocation(passingPoint);
                }

                if (distOne < bestDistOne) {
                    bestDistOne = distOne;
                    bestLOne.setLocation(passingPoint);
                }

                double distTwo = calcLTwo(points, passingPoint);

                if (bestDistTwo == -1) {
                    bestDistTwo = distTwo;
                    bestLTwo.setLocation(passingPoint);
                }

                if (distTwo < bestDistTwo) {
                    bestDistTwo = distTwo;
                    bestLTwo.setLocation(passingPoint);
                }

            }

        }

        System.out.println("(" + bestLOne.x + "," + bestLOne.y + ") " + bestDistOne);
        System.out.println("(" + bestLTwo.x + "," + bestLTwo.y + ") " + removeTrailingZeros(bestDistTwo));

    }

    private static String removeTrailingZeros(double passedIn) {
        return String.valueOf(passedIn).replaceAll("[0]*$", "").replaceAll("\\.$", "");
    }

    static int calcLOne (ArrayList<Point> points, Point passedIn) {
        int addedDist = 0;

        for (int i = 0; i < points.size(); i++) {
            int tempDist = 0;
            int tempX = 0;
            int tempY = 0;

            tempX = points.get(i).x - passedIn.x;
            tempY = points.get(i).y - passedIn.y;

            if (tempX < 0) {
                tempX = tempX * -1;
            }
            if (tempY < 0) { 
                tempY = tempY * -1;
            }

            tempDist = tempX + tempY;
            addedDist = addedDist + tempDist;            
        }
        return addedDist;
    }

    static double calcLTwo (ArrayList<Point> points, Point passedIn) {
        double addedDist = 0;

        for (int i = 0; i < points.size(); i++) {
            double tempDist = 0;
            int tempX = 0;
            int tempY = 0;

            tempX = points.get(i).x - passedIn.x;
            tempY = points.get(i).y - passedIn.y;

            tempX = tempX * tempX;
            tempY = tempY * tempY;

            tempDist = Math.sqrt(tempX + tempY);
            addedDist = addedDist + tempDist;            
        }
        return addedDist;
    }

    static Point findMid(ArrayList<Point> points) {
        double xNums = 0, yNums = 0;
        double xMid = 0, yMid = 0;

        for (int i = 0; i < points.size(); i++) {
            xNums = xNums + points.get(i).x;
            yNums = yNums + points.get(i).y;
        }
        System.out.println("xNum: " + xNums + " Y " + yNums + " size " + points.size());

        xMid = xNums / points.size();
        yMid = yNums / points.size();

        System.out.println("\n\n XM: " + xMid + " YM: " + yMid);

        Point midPoint = new Point();
        midPoint.setLocation(xMid, yMid);

        return midPoint; 
    }

}