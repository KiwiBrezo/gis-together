package si.um.feri.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.smallrye.common.annotation.Blocking;
import lombok.extern.slf4j.Slf4j;
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

    private final String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private AccountMapper accountMapper;

    @Transactional
    public Boolean registerAccount(Account domain) {
        try {
            AccountEntity entity = accountMapper.toEntity(domain);
            accountRepository.persist(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Blocking
    public String loginAccount(Account account) {
        AccountEntity accountEntity = accountRepository.find("username", account.getUsername()).firstResult();

        if (accountEntity == null) {
            return null;
        }

        Account domainAccount = accountMapper.toDomain(accountEntity);

        if (!account.getPassword().equals(domainAccount.getPassword())) {
            return null;
        }


        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());

        String jwtToken = Jwts.builder()
                .claim("name", account.getUsername())
                .setSubject("valid account")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(60l, ChronoUnit.MINUTES)))
                .signWith(hmacKey)
                .compact();

        return jwtToken;
    }

    @Blocking
    public Boolean checkJwtToken(String token) {
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());

        Jws<Claims> jwt = Jwts.parserBuilder()
                .setSigningKey(hmacKey)
                .build()
                .parseClaimsJws(token);

        return jwt.getBody().get("sub").equals("valid account");
    }
}
