package f17comp1011w3prep;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 *
 * @author JWright
 */
public class StackTest {
    public static void main(String[] args)
    {
        Stack<Number> stack = new Stack<>();
        
        //use the push method
        stack.push(12L);    //push the long value 12
        System.out.println("Pushed 12L");
        
        stack.push(34567);
        System.out.println("Pushed 34567");
        
        printStack(stack);
        
        stack.push(1.0F); //push 1.0 floating point number
        System.out.println("Pushed 1.0F");
        
        printStack(stack);
        
        stack.push(1234.5678);
        System.out.println("Pushed 1234.5678");
        
        printStack(stack);
        
        //remove items from the stack
        try
        {
            Number removedObject = null;
            
            while(true)
            {
                removedObject = stack.pop();
                System.out.printf("Popped %s%n", removedObject);
                printStack(stack);
            }
        }
        catch (EmptyStackException e)
        {
            e.printStackTrace();
        }
    }
    
    private static void printStack(Stack<Number> stack)
    {
        if (stack.isEmpty())
            System.out.printf("stack is empty%n%n");
        else
            System.out.printf("stack contains: %s (top)%n", stack);
    }
}
