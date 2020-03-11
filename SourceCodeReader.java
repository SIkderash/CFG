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

    public SourceCodeReader(String s) throws FileNotFoundException{
      
        File file = new File(s);
        Scanner scan = new Scanner(file);
        ArrayList<String> Lines = new ArrayList<>();
        ArrayList<String> Statements = new ArrayList<>();
      
        SyntaxChecker CheckSyntax = new SyntaxChecker();
        
        
        while (scan.hasNextLine()) {
                String currentLine = scan.nextLine();
                Lines.add(currentLine);
                
            }
         makeGraph MG = new makeGraph(Lines);   
           
            

            /*for (int i = 0; i < Statements.size(); i++) {
               System.out.println(Statements.get(i));
            }*/
            
            
     
        
    }
}
    
