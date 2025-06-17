package be.gyu.urlShortener.exception;

public class ShortUrlNotFoundException extends RuntimeException{
    public ShortUrlNotFoundException(){ super("존재하지 않는 단축 URL 입니다."); }
    public ShortUrlNotFoundException(String msg){ super(msg); }
}
