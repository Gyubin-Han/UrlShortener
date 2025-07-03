package be.gyu.urlShortener.config;

import java.util.regex.Pattern;

public class UrlShortConfig {
    // URL 관련 상수
    public static final class Patterns {
        // HTTP(S) URL 검증 패턴
        public static final Pattern URL_PATTERN = Pattern.compile(
            "^((http|https):\\/\\/)?([a-z0-9-]{2,}\\.[a-z]{2,}|([0-9]{1,3}\\.){3}[0-9]{1,3})[\\w.\\/가-힣\\-\\ ?=\\u0026:%0-9A-Fa-f]*"
        );
        
        // URL 프로토콜 검증 패턴
        public static final Pattern PROTOCOL_PATTERN = Pattern.compile("^(http|https):\\/\\/");
        
        // 프로토콜 문자열
        public static final String DEFAULT_PROTOCOL = "http://";
    }
    
    // URL 단축 관련 상수
    public static final class ShortUrl {
        // 단축 URL 길이
        public static final int SHORT_URL_LENGTH = 7;
        
        // 단축 URL 생성에 사용되는 해시 알고리즘
        public static final String HASH_ALGORITHM = "sha256";
    }
    
    // URL 유효기간 관련 상수
    public static final class Expiration {
        // 기본 유효기간 (일 단위)
        public static final int DEFAULT_DAYS = 30;
        
        // 최대 유효기간 (일 단위)
        public static final int MAX_DAYS = 365;
    }
}
