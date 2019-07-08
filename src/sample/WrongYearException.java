package sample;

public class WrongYearException extends Exception {
    WrongYearException(){}
    WrongYearException(String msg){
        super(msg);
    }
}
