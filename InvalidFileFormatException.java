import java.util.NoSuchElementException;
/**
 * This is a custom-made exception for the
 * FormatChecker class to check for invalid
 * file formats.
 * 
 * @author rhilde-dev
 */
public class InvalidFileFormatException extends NoSuchElementException {
    public InvalidFileFormatException(String message){
        super(message);
    }
}