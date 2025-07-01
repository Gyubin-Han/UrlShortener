package be.gyu.urlShortener.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import be.gyu.urlShortener.dto.GenerateShortUrlRequestDto;
import be.gyu.urlShortener.service.MainService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MainController {
    @Autowired
    private MainService mainService;
    
    /**
     * Main Index 페이지 매핑 메소드
     * @return index 페이지의 View Name을 반환
     */
    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

    /**
     * 입력한 원본 URL을 검증 및 단축 후, 결과 반환 메소드
     * @param request Request 객체
     * @return 결과 값을 담아서 ResponseEntity<Map<String,String>> 객체로 반환 (json 형태로 전송)
     */
    @PostMapping("/short")
    @ResponseBody
    public ResponseEntity<Map<String,String>> postGenerateShortUrl(@RequestBody GenerateShortUrlRequestDto data, HttpServletRequest request){
        // 서비스 로직 메소드 호출
        Map<String,String> resultMap=mainService.generateShortUrl(data.getOriginalUrl());

        if(!resultMap.containsKey("status") || resultMap.get("status").equals("failed")){
            resultMap.put("status","failed");
            resultMap.put("message","생성 실패");
        }

        return ResponseEntity.ok(resultMap);
    }

    /**
     * 단축 URL 조회 및 원본 URL로 Re-Direct 처리 메소드
     * @param shortUrl 단축 URL
     * @param response Re-Direct 응답 처리를 위한 매개변수
     */
    @GetMapping("/{shortUrl}")
    public void getShortUrl(@PathVariable(name="shortUrl") String shortUrl, HttpServletRequest request, HttpServletResponse response){
        // 접속 정보들 추출
        String userAgent=request.getHeader("User-Agent");
        String userIpAddr=request.getRemoteAddr();

        // 단축 URL을 통해, 원본 URL을 가져옴.
        String originalUrl=mainService.getOriginalUrl(shortUrl,userAgent,userIpAddr);
        // 가져온 원본 URL 값을 적잘한 URL이 될 수 있도록 Re-Direct하기 전에 URL-Encoding
        String encodeUrl=UriComponentsBuilder.fromUriString(originalUrl).build().encode().toUriString();
        // Re-Direct 상태 코드 설정
        response.setStatus(HttpStatus.MOVED_PERMANENTLY.value());
        // Re-Direct 목적지(원본 URL) 설정 및 Re-Direct 처리
        response.setHeader("Location",encodeUrl);
    }
}
