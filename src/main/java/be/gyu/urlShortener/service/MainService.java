package be.gyu.urlShortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.gyu.urlShortener.entity.UrlMap;
import be.gyu.urlShortener.exception.ShortUrlNotFoundException;
import be.gyu.urlShortener.exception.UrlValidationFailedException;
import be.gyu.urlShortener.repository.UrlMapRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.time.LocalDateTime;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.seruco.encoding.base62.Base62;

@Service
public class MainService {
    @Autowired
    private UrlMapRepository urlMapRepository;

    // HTTP(S) URL 검증 패턴식
    private final String urlRegPattern="^((http|https):\\/\\/)?([a-z0-9-]{2,}\\.[a-z]{2,}|([0-9]{1,3}\\.){3}[0-9]{1,3})[\\w.\\/가-힣\\-\\ ?=&:]*";
    // Base62 Encoder Instance 생성 및 호출
    private Base62 base62=Base62.createInstance();

    // HTTP(S) URL 검증 메소드
    public boolean validOriginalUrl(String url){
        return url.matches(urlRegPattern);
    }

    // URL 단축 메소드
    public UrlMap createUrlShort(String url){
        // SHA-256 Hashing을 위해 Instance 생성 및 호출
        MessageDigest md;
        try{
            md=MessageDigest.getInstance("sha256");
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        
        // DB에 저장하기 위해 Entity 객체 정의
        UrlMap urlMap=UrlMap.builder()
            .urlMapOriginal(url)
            .urlMapShort("")
            .urlMapIsActive(false)
            .urlMapClick(0l)
            .urlMapCreatedAt(LocalDateTime.now())
            .urlMapUpdatedAt(LocalDateTime.now())
            .urlMapExpiredAt(LocalDateTime.now().plusDays(14))
            .build();
        
        // DB에 저장 - 초기 저장 (단축 URL은 빈 값으로 우선 저장)
        urlMapRepository.save(urlMap);

        // 고유 값 생성
        // 고유한 값은 DB의 ID 값 + 현재 시간을 결합한 문자열을 SHA-256으로 해싱한 후,
        // Base62로 인코딩 진행
        long id=urlMap.getUrlMapId();
        String urlMapIdString=String.format("%011d",id);
        String nowDateTimeString=LocalDateTime.now().toString();
        String result=urlMapIdString+nowDateTimeString;

        // Base62 Encoding
        byte[] barr=base62.encode(md.digest(result.getBytes()));
        StringBuilder sb=new StringBuilder();
        for(byte b : barr){
            sb.append(String.format("%c",b));
        }

        // 해싱 및 인코딩된 문장을 7자리 추출하여 저장
        String shortResult=sb.toString().substring(0,7);
        urlMap=urlMap.toBuilder()
            .urlMapShort(shortResult)
            .build();

        // TODO: 단축된 URL이 중복되는지 확인하는 검증 로직 필요 (중복 검증 로직)
        
        // 최종 저장 - 단축된 URL도 포함하여 저장
        // (기존 데이터에 Update하는 방법으로 저장)
        urlMapRepository.save(urlMap);

        // 단축된 URL 반환
        return urlMap;
    }

    // 단축 URL로 원본 URL 조회 및 반환 메소드
    public String getOriginalUrl(String shortUrl){
        Optional<UrlMap> optional=urlMapRepository.findByUrlMapShort(shortUrl);
        
        if(!optional.isPresent()){
            throw new ShortUrlNotFoundException();
        }

        return optional.get().getUrlMapOriginal();
    }

    // 단축 URL 생성 메소드 (검증 및 생성)
    public Map<String,String> generateShortUrl(String originalUrl){
        Map<String,String> result=new HashMap<>();
        
        // 원본 URL 무결점 검증 (HTTP(S) URL이 맞는지 검증)
        boolean isCorrectUrl=validOriginalUrl(originalUrl);
        if(!isCorrectUrl){
            // 올바른 HTTP(S) URL이 아닌 경우, 예외 발생
            throw new UrlValidationFailedException();
        }

        // 단축 URL 생성
        UrlMap urlMap=createUrlShort(originalUrl);
        result.put("status","success");
        result.put("message","생성 성공");
        result.put("originalUrl",originalUrl);
        result.put("shortUrl",urlMap.getUrlMapShort());
        result.put("expiredAt",urlMap.getUrlMapExpiredAt().toString());

        return result;
    }
}
