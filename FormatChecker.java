import java.io.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class will check the format of the files by scanning them
 * and printing to the console whether or not they are valid (VALID/INVALID)
 * file formats. If the file is invalid, a concise message will be
 * displayed as to what made the file invalid.
 * 
 * @param args - files wanting to be checked
 * @author rhilde-dev
 */
public class FormatChecker{
    public static void main(String[] args) {
        
        if(!(args.length == 0)){
            for (String arg : args){
    
                //instance variables
                int row; //row value
                int col; //column value
                double[][] checkArray; //array value
                int count = 0; //iteration value
                Scanner fileScan = null; //file scanner
                Scanner dimensionScan = null; //dimension value scanner
                Scanner lineScan = null; //array scanner

                //prints out name of file being checked
                System.out.println(arg);
    
                try{
                    //scans file and tokenizes data into array
                    File file = new File(arg);
                    fileScan = new Scanner(file);
                    dimensionScan = new Scanner(fileScan.nextLine());
                    
                    //scanning row value
                    if(dimensionScan.hasNextInt()){
                        row = dimensionScan.nextInt();  
                    } else {
                        throw new InputMismatchException("Invalid Dimension Type: Expected Integer for Row Variable");
                    }

                    //skips over whitespace
                    while(!dimensionScan.hasNextInt() && dimensionScan.next() == " "){
                        dimensionScan.next();
                    }
                    
                    //scanning col value
                    if(dimensionScan.hasNextInt()){
                        col = dimensionScan.nextInt();
                    } else {
                        throw new InputMismatchException("Invalid Dimension Type: Expected Integer for Column Variable");
                    }

                    //if extra unexpected variable is present, throw exception
                    if(dimensionScan.hasNextInt()){
                        throw new InvalidFileFormatException("Invalid File Format: Unexpected Dimension for input: " + dimensionScan.nextInt());
                    }
                    
                    dimensionScan.close();

                    //instantiate double array
                    checkArray = new double[row][col];
    
                    //add values in file to the checkArray
                    while(fileScan.hasNextLine()){
                        
                        lineScan = new Scanner(fileScan.nextLine());
    
                        //add each double value to the array
                        for (int i = 0; i < checkArray[count].length; i++){
                            if (lineScan.hasNextDouble()){
                                checkArray[count][i] = lineScan.nextDouble();
                                if(lineScan.hasNextDouble() && i == checkArray[count].length - 1){
                                    //exception handled for more than specified cols
                                    throw new InvalidFileFormatException("Invalid File Format: Exceeded max columns (Max Columns:" + col + ") specified");
                                }
                            } else {
                                //exception handled for less than specified cols
                                throw new InvalidFileFormatException("Invalid File Format: Subceeded max columns (Max Columns:" + col + ") specified");
                            }
                            
                        }
                        
                        lineScan.close();
                        
                        //exception checks for each row after adding it to array
                        if(!(count >= row)){
                            count++;
                        } else {
                            //exception handled for more than specified rows
                            throw new InvalidFileFormatException("Invalid File Format: Exceeded max rows (Max Rows:" + row + ") specified");
                        }

                        if((count == row) && fileScan.hasNextLine() && !(fileScan.nextLine() == "")){
                            //exception handled for less than specified rows
                            throw new InvalidFileFormatException("Invalid File Format: Subceeded max rows (Max Rows:" + row + ") specified");
                        }

                    }
    
                    fileScan.close();

                    //after all conditions passed VALID is printed
                    System.out.println("VALID");
                    
                } catch (InvalidFileFormatException e) {
                    //custom exception
                    System.err.println(e.getMessage());
                    System.out.println("INVALID");
                } catch (InputMismatchException e) {
                    //input mismatch excpetion for row/col values
                    System.err.println(e.getMessage());
                    System.out.println("INVALID");
                } catch (NoSuchElementException e) {
                    //exception if no element exists
                    System.err.println("Invalid File Format: Values missing");
                    System.out.println("INVALID");
                } catch (NumberFormatException e){
                    //exception for non integer values
                    System.err.println("Invalid File Format: " + e.getMessage());
                    System.out.println("INVALID");
                } catch (FileNotFoundException e){
                    //exception if file argued is not found
                    System.err.println(e);
                } finally {
                    //closing all scanners when exception thrown
                    if (!(dimensionScan == null)){
                        dimensionScan.close();
                    }
                    if (!(lineScan == null)){
                        lineScan.close();
                    }
                    if (!(fileScan == null)){
                        fileScan.close();
                    }
                }
            }
        } else {
            //if no arguments presented, correct usage statement is provided
            System.out.println("Usage: $ java FormatChecker file1 [file2 ... fileN]");
        }
    }
}