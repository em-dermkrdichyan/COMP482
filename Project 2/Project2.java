/*
Emmanoel Dermkrdichyan MW 3:30 PM class
Project 2
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.awt.Point;


public class Project2 {
    public static void main(String[] args) throws FileNotFoundException {
        File text = new File ("input4.txt"); //name of the input file
        ArrayList<Point> points = new ArrayList<Point>();
        Scanner scanner = new Scanner(text);

        //reading the first two integers here and setting them as the smallest x and y to begin with
        int smallestX = scanner.nextInt(),largestX = 0;
        int smallestY = scanner.nextInt(), largestY = 0;
        int counter = 0;
        int first = smallestX, second = smallestY;

        //while the file has more integers the scanner keeps reading the file
        while(scanner.hasNextInt()) {
            counter++;
            //we read the rest of the x and y points and input them as first and second
            if (counter > 1) {
                first = scanner.nextInt();
                second = scanner.nextInt();
            }

            //first and second equal x and y and then we put them into a point object
            Point location = new Point(first, second);
            //add each point to the points arrayList
            points.add(location);

            //I use these if statements to find the search area, so this finds the max and min x and y point we will need to
            //visit to find L2
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

        //closing the scanner
        scanner.close();

        //this method steps through the boxed in area we created 
        stepThrough(points, smallestX, smallestY, largestX, largestY);
    }


    /*
    This method calls two methods, one to find L1 and another to find L2
    */
    static void stepThrough(ArrayList<Point> points, int sx, int sy, int lx, int ly) {
        Point bestLTwo = new Point (-1, -1);
        int bestDistOne = -1;
        double bestDistTwo = -1;

        //this method returns the most central point to all our points
        Point pointLOne = calcLOne(points);

        //we use the most central point and find the distance from all the points to the central point
        bestDistOne = calcDistOne(points, pointLOne);

        /*
        These forloops step through the boxed in area that I found and passes each point to the calcLTwo function
        to find the distance from all the points to the passed in point.
        */
        for (int x = sx; x <= lx; x++) {
            for (int y = sy; y <= ly; y++) {
                Point passingPoint = new Point (x, y);

                double distTwo = calcLTwo(points, passingPoint);

                //if the distance hasnt been set we set the distance and the location
                if (bestDistTwo == -1) {
                    bestDistTwo = distTwo;
                    bestLTwo.setLocation(passingPoint);
                }

                //compare the rest of the distances and keep replacing them with the shortest one
                if (distTwo < bestDistTwo) {
                    bestDistTwo = distTwo;
                    bestLTwo.setLocation(passingPoint);
                }

            }

        }

        //print out both L1 and L2
        System.out.println("(" + pointLOne.x + "," + pointLOne.y + ") " + bestDistOne);
        System.out.println("(" + bestLTwo.x + "," + bestLTwo.y + ") " + removeTrailingZeros(bestDistTwo));

    }

    //formats the numbers when printing by removing trailing zeros
    private static String removeTrailingZeros(double passedIn) {
        return String.valueOf(passedIn).replaceAll("[0]*$", "").replaceAll("\\.$", "");
    }

    //once we have the most central point we find the distance from all our points to the central point here
    //using the formula for L1.
    static int calcDistOne (ArrayList<Point> points, Point passedIn) {
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

    //sort all the points and calculate the most central point and return it to help find L1.
    //sorting these and finding the most central point helps reduce the complexity of the program.
    static Point calcLOne (ArrayList<Point> points) {
        Point pointOne = new Point (-1, -1);

        ArrayList<Integer> xCoord = new ArrayList<>();
        ArrayList<Integer> yCoord = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            xCoord.add(points.get(i).x);
            yCoord.add(points.get(i).y);
        }

        Collections.sort(xCoord);
        Collections.sort(yCoord);

        pointOne.setLocation(xCoord.get(xCoord.size() / 2), yCoord.get(yCoord.size() / 2));

        return pointOne;
    }


    //this method uses the L2 formula to find the distance from all our input points to the passed in point
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

}