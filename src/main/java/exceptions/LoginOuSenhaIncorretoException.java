package exceptions;

public class LoginOuSenhaIncorretoException extends RuntimeException {
    public LoginOuSenhaIncorretoException(String message) {
        super(message);
    }
}
