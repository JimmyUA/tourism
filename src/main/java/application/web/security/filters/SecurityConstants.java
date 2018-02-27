package application.web.security.filters;

public interface SecurityConstants {

    String SECRET = "SecretKeyToGenJWTs";
    long EXPIRATION_TIME = 864_000_0; // 1 day
    String TOKEN_PREFIX = "Bearer:";
    String HEADER_STRING = "Authorization";
    String COOKIE_TOKEN = "TOKEN";
}
