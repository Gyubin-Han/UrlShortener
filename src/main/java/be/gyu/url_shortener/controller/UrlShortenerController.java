package be.gyu.url_shortener.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import be.gyu.url_shortener.service.UrlShortenerService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UrlShortenerController {
  @Autowired
  UrlShortenerService service;
  
  @GetMapping("/")
  public String getIndexPage(){
    return "index";
  }

  // 줄였을 때 사용할 단어 URL를 응답하는 메소드
  @PostMapping("/getWordList")
  @ResponseBody
  public HashMap<String,Object> getWordList(){
    HashMap<String,Object> result=new HashMap<>();
    result.put("wordList",service.getAvailableShortWords());
    return result;
  }

  // 줄이려는 URL의 단어를 선택하는 페이지를 반환하는 메소드
  // @GetMapping("/addWord")
  // public ModelAndView getShortWordList(@RequestParam String url){
  //   ModelAndView mv=new ModelAndView();
    
  //   mv.addObject("wordList",service.getAvailableShortWords());
  //   mv.setViewName("shortWordList");
  //   return mv;
  // }

  // word를 선택한 후 결과 페이지를 반환하는 메소드
  @PostMapping("/addWord")
  public ModelAndView runUrlShortWord(HttpServletRequest request){
    ModelAndView mv=new ModelAndView();
    
    service.addUrlShortWord(request.getParameter("selectWord"),request.getParameter("url"));
    mv.setViewName("urlShortWordResult");
    return mv;
  }

  // 줄인 URL로 요청을 수신하는 메소드
  @GetMapping("/word/{word}")
  public ModelAndView goUrlShortWord(@PathVariable String word, HttpServletRequest request){
    ModelAndView mv=new ModelAndView();

    System.out.println("요청 word : "+word);

    request.setAttribute("url",service.goUrlShortWord(word));
    mv.setViewName("goUrlShortWord");
    return mv;
  }
}
