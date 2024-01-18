package CSCI1303;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.FileWriter;
import java.io.IOException;

public class ProgrammingAssignment {
    
    public static void main(String[] args) throws Exception {
        for (int choice = 1 ; choice != 3 ;){
            choice = menu();
            System.out.println("");
        }
        System.out.println("Thank you for using the system!\nHave a nice day.");
    }
    
    public static int menu() throws Exception { // DISPLAY MENU, CHOOSE OPTION
        Scanner input = new Scanner (System.in);
        int choice = 1;
        try {
            System.out.println("Choose one of the option for the recurrence relation:");
            System.out.println("1. First order recurrence relation");
            System.out.println("2. Second order recurrence relation");
            System.out.print("3. Exit\nChoice: ");
            
            choice = input.nextInt();

            switch (choice){
                case 1:  degree1(); break;
                case 2:  degree2(); break;
                case 3:  break;
                default: System.out.println("Choose 1-3 only.");
            }
        }
        catch (InputMismatchException e){
            System.out.println("Invalid input. Input number only.");
        }
        return choice;
    }
    
    public static void degree1 () throws Exception { // FIRST DEGREE RECURRENCE RELATION
        Scanner input = new Scanner (System.in);
        try {
            System.out.println("\nFor linear recurrence relation of degree 1: an = c x an-1");
            
            double[] a = new double[51];
            System.out.print("Enter c: ");
            double c = input.nextDouble();
            System.out.print("Enter a0: ");
            a[0] = input.nextDouble();
            
            degree1OutputDescription(c);
            
            System.out.print("\nEnter the file name to save the results: ");
            String fileName = input.next();
            FileWriter writer = new FileWriter(fileName+".txt");
            writer.write(Double.toString(a[0]) + "\n");
            for (int i = 1; i < a.length ; i++){
                 a[i] = c*a[i-1];
                 writer.write(Double.toString(a[i]) + "\n");
            }
            writer.close();
            
            System.out.println("Run saved!");
        } 
        catch (InputMismatchException e){
            System.out.println("Invalid input. Input number only.");
        } catch (IOException e){
            System.out.println("Error writing to file.");
        }
    }
    
    public static void degree2 () throws Exception { // SECOND DEGREE RECURRENCE RELATION
        Scanner input = new Scanner (System.in);
        try {
            System.out.println("\nFor linear recurrence relation of degree 2: an = [c1 x an-1] + [c2 x an-2]");
            
            double[] a = new double[52];
            System.out.print("Enter c1: ");
            double c1 = input.nextDouble();
            System.out.print("Enter a1: ");
            a[1] = input.nextDouble();
            System.out.print("Enter c2: ");
            double c2 = input.nextDouble();
            System.out.print("Enter a0: ");
            a[0] = input.nextDouble();
            
            degree2OutputDescription(c1,c2);
            
            System.out.print("\nEnter the file name to save the results: ");
            String fileName = input.next();
            FileWriter writer = new FileWriter(fileName+".txt");
            writer.write(Double.toString(a[0]) + "\n");
            writer.write(Double.toString(a[1]) + "\n");
            for (int i = 2; i < a.length ; i++){
                a[i] = (c1 * a[i-1]) + (c2 * a[i-2]);
                writer.write(Double.toString(a[i]) + "\n");
            }
            writer.close();
            
        System.out.println("Run saved!");
        }
        catch (InputMismatchException e){
            System.out.println("Invalid input. Input number only.");
        }catch (IOException e){
            System.out.println("Error writing to file.");
        }
    }
    
    public static void degree1OutputDescription (double c) throws Exception {
            String code;
            
            if ((c > 1)||(c < -1)) //Determine whether the graph is increasing/diverging or not
                code = "1";
            else
                code = "0";
            if ((c < 1)&&(c > -1)&&(c != 0)) //Determine whether the graph is decreasing/converging or not
                code = code.concat("1");
            else
                code = code.concat("0");
            if ((c == 1)||(c == 0)) //Determine whether the graph is constant or not
                code = code.concat("1");
            else
                code = code.concat("0");
            if (c < 0) //Determine whether the graph is up-down or not
                code = code.concat("1");
            else
                code = code.concat("0");
            
            displayOutputDescription(code);
    }
    
    public static void degree2OutputDescription (double c1, double c2) throws Exception {
            String code;    
            
            //Determine whether the graph is increasing/diverging or not
            if ((c1+c2>1)||(c2<-1)||((c1<=0.55)&&(c2>=0.55))||
                ((c1<-1)&&!((c2==-1)||((c2>-1)&&(c2<0)))))
                code = "1";
            else
                code = "0"; 
            
            //Determine whether the graph is decreasing/converging or not
            if (!(((c1 == 0)&&(c2 == 0))||(c1+c2==1)||(c2 == -1))       // Graph is not constant
                &&!((c1+c2>1)||(c2<-1)||((c1<=0.55)&&(c2>=0.55))        // Graph is not increasing/diverging
                ||((c1<-1)&&!((c2==-1)||((c2>-1)&&(c2<0))))))
                code = code.concat("1");
            else
                code = code.concat("0");
            
            //Determine whether the graph is constant or not
            if (((c1 == 0)&&(c2 == 0))||(c1+c2==1)||(c2 == -1))
                code = code.concat("1");
            else
                code = code.concat("0");
            
            //Determine whether the graph is up-down or not
            if ((c1 < 0)||(c2 < 0)) 
                code = code.concat("1");
            else
                code = code.concat("0");
            
            displayOutputDescription(code);
    }
    
    public static void displayOutputDescription (String code) throws Exception {
        /*Properties of Graph determine by constant input
        which will be turned into a serial number of 4 digit
        from these 4 digit, we can determine wether the graph trend is
        increasing, decreasing, constant, up-down, diverging or converging or
        consist multiple of it.*/
                            
        String increasing = "\nIncreasing : ";
        String decreasing = "Decreasing : ";
        String constant   = "Constant   : ";
        String updown     = "Up-down    : ";
        String diverging  = "Diverging  : ";
        String converging = "Converging : ";
        
        if (code.charAt(0) == '1')
            increasing = increasing.concat("yes");
        else
            increasing = increasing.concat("no");
        
        if (code.charAt(1) == '1')
            decreasing = decreasing.concat("yes");
        else
            decreasing = decreasing.concat("no");
        
        if (code.charAt(2) == '1')
            constant = constant.concat("yes");
        else
            constant = constant.concat("no");
        
        if (code.charAt(3) == '1')
            updown = updown.concat("yes");
        else
            updown = updown.concat("no");
        
        if (code.charAt(0) == '1')
            diverging = diverging.concat("yes");
        else
            diverging = diverging.concat("no");
        
        if (code.charAt(1) == '1')
            converging = converging.concat("yes");
        else
            converging = converging.concat("no");
            
        System.out.println(increasing);
        System.out.println(decreasing);
        System.out.println(constant);
        System.out.println(updown);
        System.out.println(diverging);
        System.out.println(converging);
    }
    
}
