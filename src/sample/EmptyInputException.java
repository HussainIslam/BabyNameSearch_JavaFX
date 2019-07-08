package sample;

public class EmptyInputException extends Exception{
    EmptyInputException(){}
    EmptyInputException(String msg){
        super(msg);
    }
}
