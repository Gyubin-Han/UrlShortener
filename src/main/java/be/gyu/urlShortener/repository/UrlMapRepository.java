package be.gyu.urlShortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.gyu.urlShortener.entity.UrlMap;

public interface UrlMapRepository extends JpaRepository<UrlMap,Integer> {
    
}
