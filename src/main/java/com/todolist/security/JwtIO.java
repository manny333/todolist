package com.todolist.security;

import java.time.ZonedDateTime;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.todolist.util.GsonUtils;

import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;

@Component
public class JwtIO {

	@Value("${jms.jwt.token.secret:secret}")
	private String SECRET;
	@Value("${jms.jwt.timezone:UTC}")
	private String TIMEZONE;
	@Value("${jms.jwt.token.expires-in:3600}")
	private int EXPIRES_IN;
	@Value("${jms.jwt.issuer:none}")
	private String ISSUER;

	public String generateToken(Object src) {

		String subject = GsonUtils.serialize(src);
		// Contruir un HMAC signer usando el SHA-256
		Signer signer = HMACSigner.newSHA256Signer(SECRET);

		TimeZone tz = TimeZone.getTimeZone(TIMEZONE);

		ZonedDateTime zdt = ZonedDateTime.now(tz.toZoneId()).plusSeconds(EXPIRES_IN);

		JWT jwt = new JWT()
				.setIssuer(ISSUER)
				.setIssuedAt(ZonedDateTime.now(tz.toZoneId()))
				.setSubject(subject)
				.setExpiration(zdt);

		return JWT.getEncoder().encode(jwt, signer);
	}

	public boolean validateToken(String encodedJWT) {
		
		boolean result = false;
		try {
			JWT jwt = jwt(encodedJWT);
			result = jwt.isExpired();
		} catch (Exception e) {
			// TODO: handle exception
			result = true;
		}
		
		
		return result;
	}

	public String getPayload(String encodedJWT) {
		JWT jwt = jwt(encodedJWT);
		return jwt.subject;
	}

	private JWT jwt(String encodedJWT) {
		Verifier verifier = HMACVerifier.newVerifier(SECRET);
		return JWT.getDecoder().decode(encodedJWT, verifier);
	}

}
