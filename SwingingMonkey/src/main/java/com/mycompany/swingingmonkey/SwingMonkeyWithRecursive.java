import java.io.*;
import java.util.*;

class Tree
{
    private int number;
    private int height;
    public Tree(int n,int h)
    {
        number = n;
        height = h;
    }
    public int getNumber(){return number;}
    public int getHeight(){return height;}
}

public class SwingMonkeyWithRecursive
{
    static int pair = 0;
    
    //Recursive function to find jump over case.
    public static void TraceBack(ArrayList<Tree> p, int begin, int among, int end)
    {
        if (p.get(begin).getHeight() > p.get(among).getHeight())
        {
            if (p.get(among).getHeight() >= p.get(end).getHeight())
            {
                if(end + 1 < p.size())
                {
                    TraceBack(p, begin, among + 1, end + 1);
                }
            }
            else
            {
                pair++;
                System.out.println(pair + " From Tree (" + p.get(begin).getNumber() + "), " + p.get(begin).getHeight() + " ft ---> Tree (" + p.get(end).getNumber() + "), " + p.get(end).getHeight() + " ft");
                if (end + 1 < p.size())
                {
                    TraceBack(p, begin, among + 1, end + 1);
                }
            }
        }//Found Additional
    }
    
    public static void main(String[] args)
    {
        boolean closing = false;
        
        //Rerun program until type "0".
        while (!closing)
        {
            ArrayList<Tree> obj = new ArrayList<Tree>();
            Scanner kb = new Scanner(System.in);
            int number_of_tree = 0;
            boolean finished = false;
            pair=0;
            
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
                Tree temp = new Tree(i + 1, height);
                obj.add(temp);
            }
            System.out.println("\nSolution");
            
            /////////////////////////////////////////////////////////////////SOLUTION/////////////////////////////////////////////////////////////////
            
            for(int i = 0; i < obj.size() - 1; ++i)
            {
                pair++;
                System.out.println(pair + " From Tree (" + obj.get(i).getNumber() + "), " + obj.get(i).getHeight() + " ft ---> Tree (" + obj.get(i+1).getNumber() + "), " + obj.get(i+1).getHeight() + " ft");
                if(i < obj.size() - 2)
                {
                    TraceBack(obj, i, i+1, i+2);
                }
            }
            ///////////////////////////////////////////////////////////////END SOLUTION///////////////////////////////////////////////////////////////
            System.out.println("\nTotal Tree-pairs = " + pair);
            System.out.println("\n\n\t Do you want to close this program? (Press 0 key and Enter to close this program.)");
            if(kb.next().equals("0")) {closing = true;}
        }
    }
}
