package sample;

public class WrongGenderException extends Exception {
    WrongGenderException(){}
    WrongGenderException(String msg){
        super(msg);
    }
}
