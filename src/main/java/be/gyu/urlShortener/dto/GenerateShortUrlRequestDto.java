package be.gyu.urlShortener.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
// @Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenerateShortUrlRequestDto {
    private String originalUrl;
}
