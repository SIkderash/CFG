/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfg;
import java.util.regex.PatternSyntaxException;

/**
 *
 * @author sikde
 */
public class AllMethods {
    
    
    public boolean isStatement(String s){
        return (s.charAt(s.length()-1)==':');
    }
    public boolean isIf(String s){
        return s.matches("^(\\s)*if(\\s)*"+"(");//^(\\s)*
    }
    
    public boolean isElseIf(String s){
        return s.matches("^(\\s)*else(\\s)*if(\\s)*"+"(");
    }
    public boolean isElse(String s){
        return s.matches("^(\\s)*else(\\s)*"+"{");
    }
    public boolean isWhile(String s){
        return s.matches("^(//s)*while(\\s)*"+"(");
    }
    public boolean isFor(String s){
        return s.matches("^(//s)*for(//s)*"+"(");
    }
    public boolean foundEnd(String s){
        return s.matches("}");
    }
    
    
}
