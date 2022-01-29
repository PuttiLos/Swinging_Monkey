import java.io.*;
import java.util.*;

class TreeBF
{
    private int number;
    private int height;
    public TreeBF(int n,int h)
    {
        number = n;
        height = h;
    }
    public int getNumber(){return number;}
    public int getHeight(){return height;}
}

public class SwingMonkeyWithBF
{
    public static void main(String[] args)
    {
        boolean closing = false;
        
        //Rerun program until type "0".
        while (!closing)
        {
            ArrayList<TreeBF> obj = new ArrayList<TreeBF>();
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
                TreeBF temp = new TreeBF((i+1), height);
                obj.add(temp);
            }
            int pair = 0;
            TreeBF temp;
            System.out.println("\nSolution");
            
            /////////////////////////////////////////////////////////////////SOLUTION/////////////////////////////////////////////////////////////////
            //Loop for i(0) -> i(number_of_tree).
            boolean ok;
            for(int i=0 ; i<number_of_tree-1 ; ++i)
            {
                pair++;
                System.out.println(pair + " From Tree (" + obj.get(i).getNumber() + "), " + obj.get(i).getHeight() + " ft ---> Tree (" + obj.get(i+1).getNumber() + "), " + obj.get(i+1).getHeight() + " ft");

                //Loop for jump over to j.
                for(int j=i+2 ; j<number_of_tree ; ++j)
                {
                    ok = true;
                    //Loop for check between i,j is lower or not.
                    for(int k=i+1 ; k<j ; ++k)
                    {
                        if(!(obj.get(i).getHeight()>obj.get(k).getHeight()  &&  obj.get(j).getHeight()>obj.get(k).getHeight()))
                        {
                            ok = false;
                        }
                    }
                    if(ok){
                        pair++;
                        System.out.println(pair + " From Tree (" + obj.get(i).getNumber() + "), " + obj.get(i).getHeight() + " ft ---> Tree (" + obj.get(j).getNumber() + "), " + obj.get(j).getHeight() + " ft");
                    }
                }
            }
            ///////////////////////////////////////////////////////////////END SOLUTION///////////////////////////////////////////////////////////////
            System.out.println("\nTotal Tree-pairs = " + pair);
            System.out.println("\n\n\t Do you want to close this program? (Press 0 key and Enter to close this program.)");
            if(kb.next().equals("0")) {closing = true;}
        }
    }
}