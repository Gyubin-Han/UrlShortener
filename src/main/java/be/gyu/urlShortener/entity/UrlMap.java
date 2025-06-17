package be.gyu.urlShortener.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
public class UrlMap {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long urlMapId;
    private String urlMapOriginal;
    private String urlMapShort;
    private boolean urlMapIsActive;
    @ManyToOne
    @JoinColumn(name="user_id")
    private Users user;
    @Column(insertable=false)
    private Long urlMapClick;
    private LocalDateTime urlMapCreatedAt;
    private LocalDateTime urlMapUpdatedAt;
    private LocalDateTime urlMapExpiredAt;
}
