/*
Emmanoel Dermkrdichyan MW 3:30 PM class
Project 4
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Project4 {
    static int bestPath = 0; //made this a global just to make it easier to manipulate in the recursive method.

    public static void main(String[] args) throws FileNotFoundException {
        File text = new File("input4.txt"); // name of the input file.

        Scanner scanner = new Scanner(text);

        //taking the columns and rows as input
        int y = scanner.nextInt();
        int x = scanner.nextInt();

        //arr will hold the 2d array we will be going through, longestPath saves the paths of the nodes we visit as we.
        //traverse arr backwards. Checked is basically a flag to make skip a square if weve already checked for its longest path.
        int[][] arr = new int[x][y];
        int[][] longestPath = new int[x][y];
        boolean[][] checked = new boolean[x][y];

        int xp = 0, yp = 0; //x and y pointers used to fill the arrays.
        while (scanner.hasNext()) {
            int temp = scanner.nextInt();
            if (xp < x) {
                arr[xp][yp] = temp;
                longestPath[xp][yp] = 0;
                checked[xp][yp] = false;
                xp++;
            } else {
                xp = 0;
                yp++;
                arr[xp][yp] = temp;
                longestPath[xp][yp] = 0;
                checked[xp][yp] = false;
                xp++;
            }
        }

        //need to make a temp variable to store the return of bestPath as it is needs to return paths later on.
        int tempPath = 0;

        for (int yPointer = y - 1; yPointer >= 0; yPointer--) {
            for (int xPointer = x - 1; xPointer >= 0; xPointer--) {
                //this starts at the bottom right square and passes in longest path which is initialized to 0 since we know you cant go anywhere from the bottom right.
                //it then checks the boolean array to make sure we havent visited the square and begins the call to the recursive function.
                if (checked[xPointer][yPointer] == false) {
                    tempPath = bestPath(arr, xPointer, yPointer, longestPath[xPointer][yPointer], longestPath, checked);
                }
            }

        }

        /*for (int yPointer = 0; yPointer < y; yPointer++) {
            for (int xPointer = 0; xPointer < x; xPointer++) {
                System.out.print(" " + longestPath[xPointer][yPointer]);
            }
            System.out.println();
        }

        System.out.println();*/

        //prints the longest path that was found.
        System.out.println(bestPath);

        scanner.close();
    }

    public static int bestPath(int[][] arr, int x, int y, int pathLength, int[][] longestPath, boolean[][] checked) {
        //three possible ways to get a path length, by checking the top, left and the final option being no available path so +1.
        int path1 = 0, path2 = 0, path3 = 0;
        
        //if the program can move up and the block above is larger than the one we are currently on we make a recursive call to it.
        if (y - 1 >= 0 && arr[x][y] < arr[x][y - 1]) {
            path1 = bestPath(arr, x, y - 1, pathLength + 1, longestPath, checked);
        }
        //if the program can move left and the block to the left is larger than the one we are currently on we make a recursive call to it.
        if (x - 1 >= 0 && arr[x][y] < arr[x - 1][y]) {
            path2 = bestPath(arr, x - 1, y, pathLength + 1, longestPath, checked);
        }
        //no available paths so account for the block you are on and add 1.
        else {
            path3 = 1 + pathLength;
        }

        //the next 3 if else statements are identical except for one uses path1 next path2 and then path3.
        //if path1 is the largest of the three then save it into the longestPath array.
        if (path1 > path2 && path1 > path3) {
            if (path1 > bestPath) {
                //if the path is the largest one found yet replace it with the previous largest in the global variable.
                bestPath = path1;
            }
            longestPath[x][y] = pathLength + 1; //save the path.
            checked[x][y] = true; //mark it visited.
            return path1; //return the path length up to this point.
        }
        else if (path2 > path1 && path2 > path3) {
            if (path2 > bestPath) {
                bestPath = path2;
            }
            longestPath[x][y] = pathLength + 1;
            checked[x][y] = true;
            return path2;
        }
        else {
            if (path3 > bestPath) {
                bestPath = path3;
            }
            longestPath[x][y] = pathLength + 1;
            checked[x][y] = true;
            return path3;
        }
    }
}