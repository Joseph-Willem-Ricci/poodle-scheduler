package utils.exceptions;

public class InvalidFileException extends RuntimeException{
    public InvalidFileException(String message){
        super(message);
    }
}
