package com.gx.bootjpa.other;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class RushHour {
    public final static int UP = 0;
    public final static int DOWN = 1;
    public final static int LEFT = 2;
    public final static int RIGHT = 3;

    public final static int SIZE = 6;

    private String array[][] = new String[6][6];

    /**
     * @param fileName Reads a board from file and creates the board
     * @throws Exception if the file not found or the board is bad
     */
    public RushHour(String fileName) throws Exception {
        // TODO implement me
        List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.size(); j++) {
                array[i][j] = lines.get(i).substring(j, j + 1);
            }
        }
    }

    /**
     * @param carName
     * @param dir
     * @param length  Moves car with the given name for length steps in the given direction
     * @throws IllegalMoveException if the move is illegal
     */
    public void makeMove(char carName, int dir, int length) throws IllegalMoveException {
		try {
            if (UP==dir) {
                for (int i=0;i<length;i++) {
                    List<Location> location = getLocation(carName);
                    for (Location l : location) {
                        swap(array, l.getX(), l.getY(), l.getX() - 1, l.getY());
                    }
                }
            }
            if (DOWN==dir) {
                for (int i=0;i<length;i++) {
                    List<Location> location = getLocation(carName);
                    Collections.reverse(location);
                    for (Location l : location) {
                        swap(array, l.getX(), l.getY(), l.getX() + 1, l.getY());
                    }
                }
            }
            if (LEFT==dir) {
                for (int i=0;i<length;i++) {
                    List<Location> location = getLocation(carName);
                    for (Location l : location) {
                        swap(array, l.getX(), l.getY(), l.getX(), l.getY() - 1);
                    }
                }
            }
            if (RIGHT==dir) {
                for (int i=0;i<length;i++) {
                    List<Location> location = getLocation(carName);
                    Collections.reverse(location);
                    for (Location l : location) {
                        swap(array, l.getX(), l.getY(), l.getX(), l.getY() + 1);
                    }
                }
            }


        } catch (IndexOutOfBoundsException e) {
			throw new IllegalMoveException("xxxxx");

		}
	}
		/**
		 * @return true if and only if the board is solved,
		 * i.e., the XX car is touching the right edge of the board
		 */
		public boolean isSolved() {
			// TODO implement me
			String edgeArray1[] = {array[0][5], array[1][5], array[2][5], array[3][5], array[4][5], array[5][5]};
			String edgeArray2[] = {array[0][4], array[1][4], array[2][4], array[3][4], array[4][4], array[5][4]};
			String edge1 = Arrays.toString(edgeArray1);
			String edge2 = Arrays.toString(edgeArray2);

			return edge1.contains("X")&&edge2.contains("X");
		}

    /**
     * get all locations
     * @param carName
     * @return
     * @throws IllegalMoveException
     */
		private List<Location> getLocation(char carName) {
		    List<Location> locations=new ArrayList<>();
		    for (int i=0;i<6;i++) {
		        for (int j=0;j<6;j++) {
                    if (String.valueOf(carName).equals(array[i][j])){
                        locations.add(new Location(i,j));
                    }
                }
            }
		    return locations;
        }


		private class Location {
		    private int x;
		    private int y;

            public Location(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public int getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }

            public int getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }
        }

    public static void swap(String[][] array,int x,int y,int tx,int ty) throws IllegalMoveException {
        String xx = array[x][y];
        String yy = array[tx][ty];
        if (!".".equals(yy)) {
            throw new IllegalMoveException("xxx");
        }
        array[x][y] = yy;
        array[tx][ty] = xx;
    }

    public String[][] getArray() {
        return array;
    }

    public void setArray(String[][] array) {
        this.array = array;
    }
}
