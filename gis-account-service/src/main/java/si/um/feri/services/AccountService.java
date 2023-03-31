package si.um.feri.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.smallrye.common.annotation.Blocking;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import si.um.feri.controllers.RegisterController;
import si.um.feri.mappers.AccountMapper;
import si.um.feri.models.Account;
import si.um.feri.models.AccountEntity;
import si.um.feri.repository.AccountRepository;

import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@ApplicationScoped
@Slf4j
public class AccountService {

    private Logger logger = LoggerFactory.getLogger(AccountService.class);
    private final String JWT_SECRET = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";
    private final String JWT_SUBJECT = "valid account";

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private AccountMapper accountMapper;

    @Transactional
    public Boolean registerAccount(Account domain) {
        try {
            logger.info("Converting domain object to entity and inserting it.");
            AccountEntity entity = accountMapper.toEntity(domain);
            accountRepository.persist(entity);
            return true;
        } catch (Exception e) {
            logger.info("There was an error while inserting new user into the database.");
            e.printStackTrace();
            return false;
        }
    }

    @Blocking
    public String loginAccount(Account account) {
        logger.info(("Getting account with username: ").concat(account.getUsername()));
        AccountEntity accountEntity = accountRepository.find("username", account.getUsername()).firstResult();

        if (accountEntity == null) {
            logger.info("There was no such account.");
            return null;
        }

        Account domainAccount = accountMapper.toDomain(accountEntity);

        if (!account.getPassword().equals(domainAccount.getPassword())) {
            logger.info("The password doesn't match");
            return null;
        }


        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(JWT_SECRET),
                SignatureAlgorithm.HS256.getJcaName());

        String jwtToken = Jwts.builder()
                .claim("name", account.getUsername())
                .setSubject(JWT_SUBJECT)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(60L, ChronoUnit.MINUTES)))
                .signWith(hmacKey)
                .compact();

        return jwtToken;
    }

    @Blocking
    public Boolean checkJwtToken(String token) {
        logger.info(("Checking if JWT token is not expired and is valid: ").concat(token));

        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(JWT_SECRET),
                SignatureAlgorithm.HS256.getJcaName());

        Jws<Claims> jwt = Jwts.parserBuilder()
                .setSigningKey(hmacKey)
                .build()
                .parseClaimsJws(token);

        return jwt.getBody().get("sub").equals(JWT_SUBJECT) &&
                jwt.getBody().get("name") != null &&
                (Integer) jwt.getBody().get("exp") > Integer.valueOf((int) new Date().getTime());
    }
}
