/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.phase.pkg2;

import java.util.ArrayList;

/**
 *
 * @author amira
 */
public class FunctionCall implements Node
{
    Identifier identifier ;
    Expression expression ;
    @Override
    public int parser(ArrayList<Token>tokens , int[] index)
    {
        identifier=new Identifier();
        expression=new Expression();
        
        if(identifier.parser(tokens, index) == -1)
        {
            return -1 ;
        }
        if(tokens.get(index[0]).type.equals("LEFT_ROUND_B"))
        {
            pretty_print(tokens.get(index[0]));
            index[0]=index[0]+1;
        }
        else
            return -1;
        if(expression.parser(tokens, index)==1)
        {
            
            while(tokens.get(index[0]).type.equals("COMMA"))
            {
                pretty_print(tokens.get(index[0]));
                index[0]=index[0]+1;
                if(expression.parser(tokens, index)== -1)
                {
                    return -1;
                }
            }
        }
        if(tokens.get(index[0]).type.equals("RIGHT_ROUND_B"))
        {
            pretty_print(tokens.get(index[0]));
            index[0]=index[0]+1;
        }
        else
            return -1;

        return 1; 
    }
    @Override
    public void pretty_print(Token t) {
        System.out.print(t.value+" ");
        if(t.value.equals("{"))
            System.out.println("\n");
        
        if(t.value.equals("}")||t.value.equals(";"))
            System.out.println();
    }
}
