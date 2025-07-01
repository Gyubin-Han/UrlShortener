package be.gyu.urlShortener.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClickStat {
    @Id
    private Long clickStatId;
    @ManyToOne
    @JoinColumn(name="url_map_id")
    private UrlMap urlMap;
    private LocalDateTime clickStatClickedAt;
    private String clickStatUserAgent;
    private String clickStatIpAddr;
}
