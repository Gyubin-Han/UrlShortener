package be.gyu.url_shortener.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository("urlShortener")
@Mapper
public interface UrlShortenerDAO {
  String goUrlShortWord(String word);
  List<String> getAvailableShortWords();

  int setUrlShortWord(String word,String originalUrl);
}
