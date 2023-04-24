package co.com.cattleya.auth.infrastructure.security.jwt;

import co.com.cattleya.auth.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private Long expirationTimeMs; // 30 minutes
    public String generateToken(User user){
        return Jwts.builder()
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expirationTimeMs))
            .setSubject(user.getUsername())
            /*.setClaims(new HashMap<>(){{
                put("role", user.getRole());
            }})*/
            .signWith(SignatureAlgorithm.HS256, jwtSecret)
        .compact();
    }
    private Claims extractClaims(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }
    private <T> T extractAllClaims(String token, Function<Claims, T> claimsResolver){
        return claimsResolver.apply(this.extractClaims(token));
    }
    public String extractUsername(String token){
        return this.extractAllClaims(token, Claims::getSubject);
    }
    public boolean isTokenExpired(String token){
        return this.extractAllClaims(token, Claims::getExpiration).before(new Date());
    }
    public boolean validateToken(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
