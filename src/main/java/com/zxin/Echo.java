package com.zxin;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Echo {

    public static void main(String[] args) {
        if(args != null){
            for(String arg : args){
                System.out.println(arg);
            }

        }

        BufferedReader stdin = new BufferedReader(new InputStreamReader(
                                                                        System.in));
        String line = null;

        try {
            while ((line = stdin.readLine()) != null) {

                System.out.println(line);

            }
        } catch (IOException e) {
        }
    }
}
