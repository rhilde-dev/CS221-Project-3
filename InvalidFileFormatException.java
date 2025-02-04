import java.util.NoSuchElementException;

public class InvalidFileFormatException extends NoSuchElementException {
    public InvalidFileFormatException(String message){
        super(message);
    }
}