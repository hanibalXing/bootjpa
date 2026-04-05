package com.gx.bootjpa.other;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Demo {
    public static void main(String[] args) throws Exception {


        RushHour r = new RushHour("G:\\board1.txt");


        r.makeMove('C', RushHour.DOWN, 1);
        r.makeMove('B', RushHour.DOWN, 1);
        r.makeMove('X', RushHour.RIGHT, 3);

        System.out.println(r.isSolved());

    }


}
