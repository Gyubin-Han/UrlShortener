package be.gyu.url_shortener.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.gyu.url_shortener.dao.UrlShortenerDAO;

@Service
public class UrlShortenerService {
  @Autowired
  UrlShortenerDAO dao;

  public String goUrlShortWord(String word){
    String url=dao.goUrlShortWord(word);
    
    System.out.println(url);
    if(url!=null && url.equals("")){
      url=null;
    }

    return url;
  }

  public List<String> getAvailableShortWords(){
    return dao.getAvailableShortWords();
  }

  public void addUrlShortWord(String word,String originalUrl){
    System.out.println(word);
    System.out.println(originalUrl);
    System.out.println(dao.setUrlShortWord(word,originalUrl));
    return;
  }
}
