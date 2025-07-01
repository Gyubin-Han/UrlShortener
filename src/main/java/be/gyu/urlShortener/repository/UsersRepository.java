package be.gyu.urlShortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.gyu.urlShortener.entity.Users;

public interface UsersRepository extends JpaRepository<Users,Long>{
    
}
