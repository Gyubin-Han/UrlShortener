package be.gyu.urlShortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.gyu.urlShortener.entity.ClickStat;

@Repository
public interface ClickStatRepository extends JpaRepository<ClickStat,Long>{
    
}
