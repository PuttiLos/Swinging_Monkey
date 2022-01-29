import java.io.*;
import java.util.*;

class TreeStack
{
    private int number;
    private int height;
    public TreeStack(int n,int h)
    {
        number = n;
        height = h;
    }
    public int getNumber(){return number;}
    public int getHeight(){return height;}
}

public class SwingMonkeyWithStack
{
    public static void main(String[] args)
    {
        int pair;
        boolean closing = false;
        
        //Rerun program until type "0".
        while (!closing)
        {
            ArrayList<TreeStack> obj = new ArrayList<TreeStack>();
            pair=0;
            Scanner kb = new Scanner(System.in);
            int number_of_tree = 0;
            boolean finished = false;
            
            //Loop for define number of tree.
            while (!finished)
            {
                System.out.println("\nNumber of trees = ");
                try
                {
                    number_of_tree = kb.nextInt();
                    if (number_of_tree >= 3)
                    {
                        finished = true;
                    }
                    else
                    {
                        System.out.println("\tThe number entered was " + number_of_tree + ", but the number of trees must be more than 3.\n");
                    }
                }
                catch (InputMismatchException e)
                {
                    System.out.println("\tInvalid input type (must be an integer)\n");
                    kb.nextLine();
                }
            }
            
            //Loop for generate all tree.
            for (int i = 0; i < number_of_tree; ++i)
            {
                int height = 1;
                boolean finished1 = false;
                
                //Loop for define height of tree.
                while (!finished1)
                {
                    System.out.println("Height of tree (" + (i + 1) + ") = ");
                    try
                    {
                        height = kb.nextInt();
                        if (height >= 1)
                        {
                            finished1 = true;
                        }
                        else
                        {
                            System.out.println("\tThe number entered was " + height + ", but the height of trees must be more than or equal to 1.\n");
                        }
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("\tInvalid input type (must be an integer)\n");
                        kb.nextLine();
                    }
                }
                TreeStack temp = new TreeStack((i+1), height);
                obj.add(temp);
            }
            System.out.println("\nSolution");
            
            //Function call
            pair = solve(obj);
            
            System.out.println("\nTotal Tree-pairs = " + pair);
            System.out.println("\n\n\t Do you want to close this program? (Press 0 key and Enter to close this program.)");
            if(kb.next().equals("0")) {closing = true;}
        }
    }
  
    /////////////////////////////////////////////////////////////////SOLUTION/////////////////////////////////////////////////////////////////
    public static int solve(ArrayList<TreeStack> arr)
    {
        Stack<TreeStack> stack = new Stack<TreeStack>();
        TreeStack input;
        int numPairs = 0;

        for(int i=0; i < arr.size(); i++)
        {
            input = arr.get(i);
            if(stack.isEmpty()) //if stack is empty, just push the input
            {
                stack.push(input);
            }
            else if(!stack.isEmpty())
            {
                //Do a while loop to pop all possible top stack element until
                //the top element is bigger than the input
                //or the stack is empty
                while(!stack.isEmpty()  &&  input.getHeight() > stack.peek().getHeight()) 
                {
                    TreeStack temp = stack.pop();
                    numPairs++;
                    System.out.println(numPairs + " From Tree (" + temp.getNumber() + "), " + temp.getHeight() + " ft ---> Tree (" + input.getNumber() + "), " + input.getHeight() + " ft");
                }

                //if the stack is empty after exiting the while loop
                //push the current element onto the stack
                if(stack.isEmpty())
                {
                    stack.push(input);
                }
                //this condition applies for two cases:
                //1. the while loop is never entered because the input is smaller than the top element by default
                //2. the while loop is exited and the input is pushed onto the non-empty stack with numPairs being incremented 
                else if(!stack.isEmpty() && input.getHeight() < stack.peek().getHeight())
                {
                    TreeStack temp = stack.peek();
                    stack.push(input);
                    numPairs++;
                    System.out.println(numPairs + " From Tree (" + temp.getNumber() + "), " + temp.getHeight() + " ft ---> Tree (" + input.getNumber() + "), " + input.getHeight() + " ft");
                }
                //this is the last condition:
                //the input is never pushed if the input is identical to the top element
                //instead we increment the numPairs
                else if(input.getHeight() == stack.peek().getHeight())
                {
                    TreeStack temp = stack.pop();
                    stack.push(input);
                    numPairs++;
                    System.out.println(numPairs + " From Tree (" + temp.getNumber() + "), " + temp.getHeight() + " ft ---> Tree (" + input.getNumber() + "), " + input.getHeight() + " ft");
                }
            }
        }
        while(!stack.isEmpty())
        {
            stack.pop();
        }
        return numPairs;
    }
    ///////////////////////////////////////////////////////////////END SOLUTION///////////////////////////////////////////////////////////////
}
