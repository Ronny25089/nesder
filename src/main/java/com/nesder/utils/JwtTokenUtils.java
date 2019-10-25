package com.nesder.utils;

import java.util.Date;
import java.util.stream.Collectors;

import com.nesder.model.UserContext;
import com.nimbusds.oauth2.sdk.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtils {

    public static final String TOKEN_HEADER = "Token";
    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET = "nesderSecretdemo";
    private static final String ISS = "echisan";

    // 角色的key
    private static final String ROLE_CLAIMS = "role";
    
    private static final String SCOPE = "scopes";

    // 过期时间是3600秒，既是1个小时
//    private static final long EXPIRATION = 3600L;

    // 过期时间为7天
    private static final long EXPIRATION_7DAY = 604800L;

    // 创建token
    public static String createToken(UserContext userContext) {
    	if (StringUtils.isBlank(userContext.getUsername())) 
            throw new IllegalArgumentException("Cannot create JWT Token without username");

        if (userContext.getAuthorities() == null || userContext.getAuthorities().isEmpty()) 
            throw new IllegalArgumentException("User doesn't have any privileges");

        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        claims.put(SCOPE, userContext.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));
        claims.put(ROLE_CLAIMS, userContext.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));
        
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setId(userContext.getId().toString())
                .setClaims(claims)
                .setIssuer(ISS)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_7DAY * 1000))
                .compact();
    }
    
    // 刷新token
//    public static String refreshToken(UserContext userContext) {
//    	if (StringUtils.isBlank(userContext.getUsername())) {
//            throw new IllegalArgumentException("Cannot create JWT Token without username");
//        }
//        
//    	Claims claims = Jwts.claims().setSubject(userContext.getUsername());
//        claims.put(SCOPE, Arrays.asList(Scopes.REFRESH_TOKEN.authority()));
//        claims.put(ROLE_CLAIMS, userContext.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));
//        
//        return Jwts.builder()
//                .signWith(SignatureAlgorithm.HS512, SECRET)
//                .setClaims(claims)
//                .setIssuer(ISS)
//                .setId(UUID.randomUUID().toString())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_7DAY * 1000))
//                .compact();    	
//    }

    // 从token中获取用户名
    public static String getUsername(String token){
        return getTokenBody(token).getSubject();
    }

    // 获取用户角色
    public static String getUserRole(String token){
        return getTokenBody(token).get(ROLE_CLAIMS).toString();
    }
    
    // 获取用户id
    public static int getUserId(String token){
        return Integer.parseInt(getTokenBody(token).getId());
    }

    // 是否已过期
    public static boolean isExpiration(String token) {
        try {
            return getTokenBody(token).getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    private static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}