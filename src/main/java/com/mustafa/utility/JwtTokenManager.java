package com.mustafa.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mustafa.exception.ErrorType;
import com.mustafa.exception.UserException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JwtTokenManager {

    private final String SECRETKEY = "QBBo6MNSm6xat+6B%zHCL_sBev2e~=KEOfdqk(ihaAj3Kuv9JX"; //255^6 ihtimal -> 3^6*100^6 ->81.000.000.000.000 / 3.400.000.000saniyede ->27.000sn
    private final String ISSUER = "Java13Auth";
    private final String AUDIENCE = "Java13AuthAudience";
    private final Long EXDATE = 1000L * 60 * 5 ; // 5 dk

    public Optional<String> createToken(String authId){
        String token;
        try{
            token = JWT.create().withAudience(AUDIENCE)
                    .withClaim("authid", authId)
                    .withIssuer(ISSUER) //token sahibi
                    .withIssuedAt(new Date()) //oluşma zamanı
                    .withExpiresAt(new Date(System.currentTimeMillis()+EXDATE)) // token ın ne zaman süresinin dolacağı
                    .sign(Algorithm.HMAC512(SECRETKEY));
            return Optional.of(token);

        }catch (Exception e){
            return Optional.empty();
        }

    }


    public Optional<Long> validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC512(SECRETKEY);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if(decodedJWT == null)
                return Optional.empty();
            Long authId = decodedJWT.getClaim("authid").asLong();
            return Optional.of(authId);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    public Optional<String> getIdFromToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC512(SECRETKEY);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).withAudience(AUDIENCE).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if(decodedJWT == null){
                throw new UserException(ErrorType.INVALID_TOKEN);
            }
            String id = decodedJWT.getClaim("authid").asString();
            return Optional.of(id);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new UserException(ErrorType.INVALID_TOKEN);
        }
    }
}
