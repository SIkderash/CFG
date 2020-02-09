/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfg;

import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author sikde
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Control Flow Graph\n\n");
       System.out.println("Enter the directory of a C source code file\n");
       Scanner scan = new Scanner(System.in);
       String fileName = scan.nextLine();
       SourceCodeReader Sc = new SourceCodeReader(fileName);
       
  
        
                
    }
    
}
