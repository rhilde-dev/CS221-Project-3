import java.io.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
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
        if(!(args.length == 0)){
            for (String arg : args){
    
                //instance variables
                int row; //row condition
                int col; //column condition
                double[][] checkArray; //array condition
                int count = 0;
    
                //prints out name of file being checked
                System.out.println(arg);
    
                try{
                    //scans file and tokenizes data into array
                    File file = new File(arg);
                    Scanner fileScan = new Scanner(file);
                    Scanner dimensionScan = new Scanner(fileScan.nextLine());
                    dimensionScan.useDelimiter(" ");
                    row = dimensionScan.nextInt();
                    col = dimensionScan.nextInt();
                    dimensionScan.close();
    
                    //instantiate double array
                    checkArray = new double[row][col];
    
                    //add values in file to the checkArray
                    while(fileScan.hasNextLine()){
                        //TODO: use while loop to count through row and add conditional if it is over or under amount specified
                        
    
                        Scanner lineScan = new Scanner(fileScan.nextLine());
                        lineScan.useDelimiter(" ");
    
                        for (int i = 0; i < checkArray[count].length; i++){
                            //add each number to the row
                            checkArray[count][i] = Double.parseDouble(lineScan.next());
            
                        }
                        lineScan.close();
                        
                        if(!(count + 1 > row)){
                            count++;
                        } else {
                            throw new InvalidFileFormatException("Invalid File Format: Exceeded max rows (Max Row:" + row + ") specified");
                        }

                        //get this to be thrown and caught ^^^
                    }
    
                    //print out array for testing
                    for(int i = 0; i < checkArray.length; i++){
                        for(int j = 0; j < checkArray[i].length; j++){
                            System.out.println(checkArray[i][j]);
                        }
                    }
    
                    //===================================
    
    
                    fileScan.close();
                    
                } catch (InvalidFileFormatException e) {
                    System.err.println(e.getMessage());
                } catch (NoSuchElementException e) {
                    System.err.println("java.util.NoSuchElementException: Exceeded max rows specified");
                } catch (NumberFormatException e){
                    System.err.println("java.lang.NumberFormatException: " + e.getMessage());
                } catch (FileNotFoundException e){
                    System.err.println(e);
                }
            }

        } else {
            System.out.println("Usage: $ java FormatChecker file1 [file2 ... fileN]");
        }

    }

}