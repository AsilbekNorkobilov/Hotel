package org.example.hotelmanagementsystem.model.resDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hotelmanagementsystem.model.resDto.TokenDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenResDto {
    private String message;
    private TokenDto body;
}
