package wiki.cwm.tiny.blog.api.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import wiki.cwm.tiny.blog.api.common.ExceptionEnum;
import wiki.cwm.tiny.blog.api.common.ServiceException;
import wiki.cwm.tiny.blog.api.service.IAuthService;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AuthServiceImpl implements IAuthService {

    @Value("${auth0.jwt.secret}")
    private String secret;

    @Override
    public Long verify(String s) throws ServiceException {
        try {
            Algorithm algorithm = algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                                      .withIssuer("blog")
                                      .build();
            DecodedJWT jwt = verifier.verify(s);
            return Long.valueOf(jwt.getClaim("userId").asString());
        } catch (JWTVerificationException exception) {
            log.error(exception.getMessage());
            throw new ServiceException(ExceptionEnum.LOGIN_TOKEN_ERROR);
        }
    }

    @Override
    public String generate(String userId) throws ServiceException {
        String token;
        try {
            token = generateWithTime(userId,30 * TimeUnit.MINUTES.toMillis(1));
        } catch (JWTCreationException exception) {
            log.error(exception.getMessage());
            throw new ServiceException(ExceptionEnum.LOGIN_TOKEN_ERROR);
        }
        return token;
    }

    protected String generateWithTime(String userId, Long time) {
        Algorithm algorithm = algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
           .withIssuer("blog")
           .withClaim("userId", userId)
           .withExpiresAt(Instant.now().plusMillis(time))
           .sign(algorithm);
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
