/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author sikde
 */
public class SourceCodeReader {

    public SourceCodeReader(String s) throws FileNotFoundException, IOException{
      
        File file = new File(s);
        Scanner scan = new Scanner(file);
        ArrayList<String> Lines = new ArrayList<>();
        
        while (scan.hasNextLine()) {
                String currentLine = scan.nextLine();
                //currentLine = currentLine.replaceAll("\\s","");
                Lines.add(currentLine);   
        }
         MakeGraph Graph = new MakeGraph(Lines);   
         Graph.start();
         //Graph.printGraph();
            

            /*for (int i = 0; i < Lines.size(); i++) {
               System.out.println(Lines.get(i));
            }*/
      
    }
}
    
