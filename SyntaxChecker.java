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
public class SyntaxChecker {
    
    
    public boolean isStatement(String statement){
        return (statement.charAt(statement.length()-1)==':');
    }
    public boolean isIf(String statement){
        return statement.matches("^(\\s)*if(\\s)*"+"(");//^(\\s)*
    }
    
    public boolean isElseIf(String statement){
        return statement.matches("^(\\s)*else(\\s)*if(\\s)*"+"(");
    }
    public boolean isElse(String statement){
        return statement.matches("^(\\s)*else(\\s)*"+"{");
    }
    public boolean isWhile(String statement){
        return statement.matches("^(//s)*while(\\s)*"+"(");
    }
    public boolean isFor(String statement){
        return statement.matches("^(//s)*for(//s)*"+"(");
    }
    public boolean foundEnd(String statement){
        return statement.matches("}");
    }
    
    
}
