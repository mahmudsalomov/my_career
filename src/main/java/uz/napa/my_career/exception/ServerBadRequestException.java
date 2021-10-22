package uz.napa.my_career.exception;

public class ServerBadRequestException extends RuntimeException {
    public ServerBadRequestException(String message) {
        super(message);
    }
}

