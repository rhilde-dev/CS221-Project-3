import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * This class will check the format of the files by scanning them
 * and printing to the console whether or not they are valid
 * file formats
 * 
 * @author rhilde-dev
 */
public class FormatChecker{
    public static void main(String[] args) {
        
        //either print valid or invalid
        //check for file data in the wrong format
        for (String arg : args){

            //instance variables
            int row; //row condition
            int col; //column condition
            double[][] checkArray; //array condition

            //prints out name of file being checked
            System.out.println(arg);

            try{
                //scans file and tokenizes data into array
                File file = new File(arg);
                Scanner fileScan = new Scanner(file);
                Scanner dimensionScan = new Scanner(fileScan.nextLine());
                dimensionScan.useDelimiter(" ");
                //checked dimensions for integer value
                if(!dimensionScan.hasNextInt()){
                    throw new NumberFormatException("Missing Expected Integer" + dimensionScan.next());
                } else {
                    row = dimensionScan.nextInt();
                }
                if(!dimensionScan.hasNextInt()){
                    throw new NumberFormatException("Missing Expected Integer" + dimensionScan.next());
                } else {
                    col = dimensionScan.nextInt();
                }
                dimensionScan.close();

                //instantiate double array
                checkArray = new double[row][col];

                //add values in file to the checkArray
                while(fileScan.hasNextLine()){
                    //TODO: use while loop to count through row and add conditional if it is over or under amount specified
                    Scanner lineScan = new Scanner(fileScan.nextLine());
                    


                }




                fileScan.close();
                
            } catch (FileNotFoundException e){
                //for when
                System.err.println(e);
            }

        }

    }

}