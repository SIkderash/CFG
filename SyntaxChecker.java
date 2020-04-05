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
        statement = statement.replaceAll("\\s","");
        return (statement.charAt(statement.length()-1)==';');
    }
    public boolean isIf(String statement){
        statement = statement.replaceAll("\\s","");
        return statement.contains("if(");//^(\\s)*
    }
    
    public boolean isElseIf(String statement){
        statement = statement.replaceAll("\\s","");
        return statement.contains("elseif{");
    }
    public boolean isElse(String statement){
        statement = statement.replaceAll("\\s","");
        return statement.contains("else{");
    }
    public boolean isWhile(String statement){
        statement = statement.replaceAll("\\s","");
        return statement.contains("while(");
    }
    public boolean isFor(String statement){
        statement = statement.replaceAll("\\s","");
        return statement.contains("for(");
    }
    public boolean foundEnd(String statement){
        statement = statement.replaceAll("\\s","");
        return statement.contains("}");
    }
    public boolean contains(String Line, String literal){
        int literalIterator = 1;
        for(int LineIterator= 0;  LineIterator< Line.length();  LineIterator++){
            if(Line.charAt(LineIterator)==literal.charAt(literalIterator)){
                literalIterator++;
                if(literalIterator> literal.length()){
                    return true;
                }
            }
        }
        return false;
    }
    
    
}
