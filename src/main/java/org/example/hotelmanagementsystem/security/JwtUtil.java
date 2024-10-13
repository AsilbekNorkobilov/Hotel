package org.example.hotelmanagementsystem.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.hotelmanagementsystem.dto.RegisterDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    public String generateToken(UserDetails userDetails) {
        String roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("roles",roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getKey())
                .compact();
    }
    public String generateRefreshToken(UserDetails userDetails) {
        String roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("roles",roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*24*14))
                .signWith(getKey())
                .compact();
    }
    public String generateConfirmToken(RegisterDto reqDto, Integer code) {
        return Jwts.builder()
                .claim("mailCode",code)
                .claim("email",reqDto.getEmail())
                .claim("password",reqDto.getPassword())
                .claim("firstName",reqDto.getFirstName())
                .claim("lastName",reqDto.getLastName())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*10))
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        String secretKey = "1234567891234567891234567891234567891234567891234567891234567891";
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }

    public boolean isValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getEmail(String authorization) {
        Claims claims = getClaims(authorization);
        return claims.getSubject();
    }



    public Integer getMailCode(String confirm){
        Claims claims = getClaims(confirm);
        return claims.get("mailCode",Integer.class);
    }

    public RegisterDto getReqDto(String confirm){
        Claims claims = getClaims(confirm);
        return new RegisterDto(claims.get("email", String.class),
                claims.get("firstName",String.class),
                claims.get("lastName",String.class),
                claims.get("password", String.class));
    }

    public List<GrantedAuthority> getRoles(String token) {
        Claims claims = getClaims(token);
        String roles = claims.get("roles", String.class);
        return Arrays.stream(roles.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}