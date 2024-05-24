package jerryli.taoyuandaytrip.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jerryli.taoyuandaytrip.pojo.User;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

/**
 * @author Jerry
 * @create 2024-05-22-下午 11:02
 */
@Service
public class JwtServiceImpl {

    // set expiration time : 15 min.
    private final Long EXPIRATION_MILLIS = Long.valueOf(15 * 60 * 1000);
    private Key secretKey;

    @PostConstruct
    private void init() {
        String myKey = "==============TaoyuanTripProject==============";
        secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(myKey));
    }


    // according user to create token
    public String createAccessToken(User user) {
        Claims claims = Jwts.claims();
        claims.setSubject("Access Token");
        claims.setIssuedAt(new Date());
        claims.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS));

        // add user information into token
        claims.put("userId", user.getId());
        claims.put("account", user.getAccount());
        claims.put("email", user.getEmail());

        // generate token
        return Jwts.builder()
                .setClaims(claims)
                .signWith(secretKey)
                .compact();
    }


    // extract token
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    /**
     * to verify if token is valid
     *
     * @param token current token
     * @param user  authenticate user
     * @return
     */
    public boolean isTokenValid(String token, User user) {
        Claims claims = this.extractClaims(token);
        String userEmail = (String) claims.get("email");

        return userEmail.equals(user.getEmail()) && !isTokenExpired(token);
    }


    private boolean isTokenExpired(String token) {
        Claims claims = this.extractClaims(token);
        Date expiration = claims.getExpiration();
        return expiration != null && expiration.before(new Date());

    }

}
