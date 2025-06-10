package be.gyu.urlShortener.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class UrlMap {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int urlMapId;
    private String urlMapOriginal;
    private String urlMapShort;
    private boolean urlMapIsActive;
    @ManyToOne
    @JoinColumn(name="user_id")
    private Users user;
    private int urlMapClick;
    private LocalDateTime urlMapCreatedAt;
    private LocalDateTime urlMapEditedAt;
    private LocalDateTime urlMapExpiredAt;
}
