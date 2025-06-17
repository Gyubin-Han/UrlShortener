package be.gyu.urlShortener.exception;

// TODO: url-short branch에서 추가 및 커밋할 것.
public class UrlValidationFailedException extends RuntimeException{
    public UrlValidationFailedException(){ super("적절한 HTTP(S) URL이 아닙니다."); }
    public UrlValidationFailedException(String msg){ super(msg); }
}
