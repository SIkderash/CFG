/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfg;

import java.beans.Statement;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author sikde
 */
public class SourceCodeReader {

    public static void main(String[] args) {
        File file = new File("input.txt");
        Scanner scan = new Scanner("file");
        ArrayList<String> Statements = new ArrayList<>();
        if (file.exists()) {
            while (scan.hasNextLine()) {
                Statements.add(scan.nextLine());
            }

            for (int i = 0; i < Statements.size(); i++) {
                System.out.println(Statements.get(i));
            }
        } else {
            System.out.println("File Not Found");
        }
    }

}
