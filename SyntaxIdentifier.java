/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfg;

/**
 *
 * @author sikde
 */
public class SyntaxIdentifier {
    
    
    public boolean isExpression(String statement){
        statement = statement.replaceAll("\\s","");
        return (statement.charAt(0)!='/' && statement.charAt(1)!='/' && statement.charAt(statement.length()-1)==';');
    }
    public boolean isIf(String statement){
        statement = statement.replaceAll("\\s","");
        if(statement.length()>=3){
            if(statement.charAt(0)=='i' && statement.charAt(1)=='f' && statement.charAt(2)=='('){
                return true;
            }
        }
        return false;
    }
    
    public boolean isElseIf(String statement){
        statement = statement.replaceAll("\\s","");statement = statement.replaceAll("\\s","");
        if(statement.length()>=7){
            if(statement.charAt(0)=='e' && statement.charAt(1)=='l' && statement.charAt(2)=='s'
                                        && statement.charAt(3)=='e' &&  statement.charAt(4)=='i'
                                        && statement.charAt(5)=='f' &&  statement.charAt(6)=='('){
                return true;
            }
        }
        return false;
    }
    public boolean isElse(String statement){
        statement = statement.replaceAll("\\s","");
        if(statement.length()>=4){
            if(statement.charAt(0)=='e' && statement.charAt(1)=='l' && statement.charAt(2)=='s'
            && statement.charAt(3)=='e' ){
                return true;
            }
        }
        return false;
    }
    public boolean isWhile(String statement){
        statement = statement.replaceAll("\\s","");
        if(statement.length()>=6){
            if(statement.charAt(0)=='w' && statement.charAt(1)=='h' && statement.charAt(2)=='i'
                                        && statement.charAt(3)=='l' &&  statement.charAt(4)=='e'
                                        && statement.charAt(5)=='('){
                return true;
            }
        }
        return false;
    }
    public boolean isFor(String statement){
        statement = statement.replaceAll("\\s","");
         if(statement.length()>=4){
            if(statement.charAt(0)=='f' && statement.charAt(1)=='o' && statement.charAt(2)=='r' &&  statement.charAt(3)=='('){
                return true;
            }
        }
        return false;
    }
    public boolean isLoop(String statement){                        //"for(" nowt working, so instead using for
        statement = statement.replaceAll("\\s","");
        if(isFor(statement) || isWhile(statement)){
            return true;
        }
        return false;
    }
    public boolean foundEnd(String statement){
        statement = statement.replaceAll("\\s","");
        return statement.charAt(statement.length()-1)=='}';
    }
      
}


