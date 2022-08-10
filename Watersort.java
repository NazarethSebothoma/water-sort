
/**
 * Write a description of class Watersort here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.Scanner;
public class Watersort
{
    static Character red= new Character('r'); 
    static Character green= new Character('g'); 
    static Character blue= new Character('b'); 

    public static void main(String [] args)
    {
        System.out.println("\f");
        Random rand = new Random();
        Scanner inputSource = new Scanner(System.in);
        Scanner inputTarget = new Scanner(System.in);

        int filled =0;//check the filled spaces 

        Character [] colours = new Character[3];
        colours[0]=red;
        colours[1]=green;
        colours[2]=blue;

        int countRed = 0;
        int countGreen = 0;
        int countBlue = 0;

        StackAsMyArrayList<Character> bottle1 = new StackAsMyArrayList<Character>(); 
        StackAsMyArrayList<Character> bottle2 = new StackAsMyArrayList<Character>();
        StackAsMyArrayList<Character> bottle3 = new StackAsMyArrayList<Character>();
        StackAsMyArrayList<Character> bottle4 = new StackAsMyArrayList<Character>();
        StackAsMyArrayList<Character> bottle5 = new StackAsMyArrayList<Character>();

        StackAsMyArrayList[] bottles = new StackAsMyArrayList[5];
        bottles[0] = bottle1;
        bottles[1] = bottle2;
        bottles[2] = bottle3;
        bottles[3] = bottle4;
        bottles[4] = bottle5;

        //First method
        while(filled < 12)
        {
            int bottle = rand.nextInt(5);
            int color = rand.nextInt(3);

            if(bottles[bottle].getStackSize()<4)
            {
                if(colours[color] == red && countRed<4)
                {
                    bottles[bottle].push(colours[color]);
                    countRed++;
                    filled++;
                }

                if(colours[color] == green && countGreen<4)
                {
                    bottles[bottle].push(colours[color]);
                    countGreen++;
                    filled++;
                }

                if(colours[color] == blue && countBlue<4)
                {
                    bottles[bottle].push(colours[color]);
                    countBlue++;
                    filled++;
                }
            }

        }

        ShowAll(bottles);
        boolean solved = solved(bottles);

        while(!solved)
        {
            System.out.println("Enter the Source bottle number: ");
            int source = inputSource.nextInt();

            if(source<1 || source>5)
            {
                System.out.println("please choose a number within range 1-5 inclusively");
            }
            else if(bottles[source-1].getStackSize()==0)
            {
                System.out.println("Source bottle is empty");
            }
            else
            {
                System.out.println("Enter the Target bottle number: ");
                int target = inputTarget.nextInt();
                while(target<1 || target>5)
                {
                    System.out.println("please choose a number within range 1-5 inclusively");
                    System.out.println("Enter the Target bottle number: ");
                    target = inputTarget.nextInt();
                }

                if(bottles[target-1].getStackSize() == 4)
                {
                    System.out.println("Target bottle full");
                }
                else
                { 

                    if(bottles[source-1].peek()==bottles[target-1].peek() || bottles[target-1].getStackSize() ==0)
                    {
                        bottles[target-1].push(bottles[source-1].peek());
                        bottles[source-1].pop();
                        
                        if(bottles[source-1] != bottles[target-1])
                        {
                            while(bottles[source-1].peek()==bottles[target-1].peek() && bottles[target-1].getStackSize() != 4)
                        {
                            bottles[target-1].push(bottles[source-1].peek());
                            bottles[source-1].pop();

                        }
                        }
                        
                        ShowAll(bottles);
                    }
                    else
                    {
                        System.out.println("Colours do not match!!");
                    }
                }
            }
            if(solved(bottles))
            {
                System.out.println("\nWell done..you have won ");
                break;
            }
        }
    }

    public static void ShowAll(StackAsMyArrayList<Character>[] bottles)
    {
        for(int i=0; i<5; i++)
        {
            System.out.println("Bottle" + (i+1) + ": " + bottles[i]);
        }
    }

    public static boolean solved( StackAsMyArrayList bottles[])
    {
        boolean solved = true;
        for(int i =0; i<5; i++)
        {
            if(bottles[i].getStackSize()>0 && bottles[i].getStackSize()<4)
            {
                solved = false;
            }
            if(bottles[i].checkStackUniform() == false)
            {
                return false;
            }
        }
        return solved;
    }

}
