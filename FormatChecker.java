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
                    
                    if(dimensionScan.hasNextInt()){
                        row = dimensionScan.nextInt();
                    } else {
                        throw new InputMismatchException("Invalid Dimension Type: Expected Integer for Row Variable");
                    }
                    if(dimensionScan.hasNextInt()){
                        col = dimensionScan.nextInt();
                    } else {
                        throw new InputMismatchException("Invalid Dimension Type: Expected Integer for Column Variable");
                    }
                    if(dimensionScan.hasNextInt()){
                        throw new InvalidFileFormatException("Invalid File Format: Unexpected Dimension for input: " + dimensionScan.nextInt());
                    }
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
                            if (lineScan.hasNextInt()){
                                checkArray[count][i] = Double.parseDouble(lineScan.next());
                            } else {
                                //exception handled for less than specified cols
                                throw new InvalidFileFormatException("Invalid File Format: Subceeded max columns (Max Columns:" + col + ") specified");
                            }
                            
                        }
                        lineScan.close();
                        
                        if(!(count >= row)){
                            count++;
                            System.out.println(count);
                        } else {
                            //exception handled for more than specified rows
                            throw new InvalidFileFormatException("Invalid File Format: Exceeded max rows (Max Rows:" + row + ") specified");
                        }

                        // if(){
                        //     throw new InvalidFileFormatException("Invalid File Format: Subceeded max rows (Max Rows:" + row + ") specified");
                        // }

                        //TODO make exception for exceeded max rows
                        //Make exception for
                    }
    
                    //print out array for testing
                    // for(int i = 0; i < checkArray.length; i++){
                    //     for(int j = 0; j < checkArray[i].length; j++){
                    //         System.out.println(checkArray[i][j]);
                    //     }
                    // }
    
                    //===================================
    
    
                    fileScan.close();
                    System.out.println("VALID");
                    
                } catch (InvalidFileFormatException e) {
                    System.err.println(e.getMessage());
                    System.out.println("INVALID");
                } catch (InputMismatchException e) {
                    System.err.println(e.getMessage());
                    System.out.println("INVALID");
                } catch (NoSuchElementException e) {
                    System.err.println("NoSuchElementExists");
                    System.out.println("INVALID");
                } catch (NumberFormatException e){
                    System.err.println("java.lang.NumberFormatException: " + e.getMessage());
                    System.out.println("INVALID");
                } catch (FileNotFoundException e){
                    System.err.println(e);
                } finally {
                    dimensionScan.close();
                    lineScan.close();
                    fileScan.close();
                    //make this work ^^^
                }
            }

        } else {
            System.out.println("Usage: $ java FormatChecker file1 [file2 ... fileN]");
        }

    }

}