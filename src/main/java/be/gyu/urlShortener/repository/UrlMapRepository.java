package be.gyu.urlShortener.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import be.gyu.urlShortener.entity.UrlMap;

public interface UrlMapRepository extends JpaRepository<UrlMap,Integer> {
    public Optional<UrlMap> findByUrlMapShort(String urlMapShort);
}