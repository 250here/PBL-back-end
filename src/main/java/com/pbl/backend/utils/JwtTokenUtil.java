package com.pbl.backend.utils;

import com.pbl.backend.common.exception.CustomException;
import com.pbl.backend.common.response.ResultCode;
import com.pbl.backend.entity.Audience;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author: 杜东方
 * @date: 2020/5/3
 * @description:
 */
public class JwtTokenUtil {

    private static Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);

    public static final String AUTH_HEADER_KEY = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 构建JWT
     * @param userId
     * @param username
     * @param role
     * @param audience
     * @return
     */
    public static String createJWT(String userId, String username, String role, Audience audience){
        try{
            // 使用HS256加密算法
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);

            //生成签名密钥
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(audience.getBase64Secret());
            Key signingKey = new SecretKeySpec(apiKeySecretBytes,signatureAlgorithm.getJcaName());

            //userId是重要信息，进行加密下
            String encryId = Base64Util.encode(userId);

            //添加构成JWT的参数
            JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                    // 可以将基本不重要的对象信息放到claims
                    .claim("role", role)
                    .claim("userId", userId)
                    .setSubject(username)           // 代表这个JWT的主体，即它的所有人
                    .setIssuer(audience.getClientId())              // 代表这个JWT的签发主体；
                    .setIssuedAt(new Date())        // 是一个时间戳，代表这个JWT的签发时间；
                    .setAudience(audience.getName())          // 代表这个JWT的接收对象；
                    .signWith(signatureAlgorithm, signingKey);
            //添加Token过期时间
            int TTLMillis = audience.getExpiresSecond()*1000;
            if (TTLMillis >= 0) {
                long expMillis = nowMillis + TTLMillis;
                Date exp = new Date(expMillis);
                builder.setExpiration(exp)  // 是一个时间戳，代表这个JWT的过期时间；
                        .setNotBefore(now); // 是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的
            }

            //生成JWT
            return builder.compact();
        }
        catch (Exception e){
            log.error("签名失败",e);
            e.printStackTrace();
            throw new CustomException(ResultCode.PERMISSION_SIGNATURE_ERROR);
        }
    }

    /**
     * 解析jwt
     * @param jsonWebToken
     * @param base64Security
     * @return
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                .parseClaimsJws(jsonWebToken).getBody();
        return claims;
    }

    /**
     * 从token中获取用户名
     * @param token
     * @param base64Security
     * @return
     */
    public static String getUsername(String token, String base64Security){
        return parseJWT(token, base64Security).getSubject();
    }

    /**
     * 从token中获取用户ID
     * @param token
     * @param base64Security
     * @return
     */
    public static String getUserId(String token, String base64Security){
        return parseJWT(token, base64Security).get("userId", String.class);
    }

    /**
     * 从token中获取用户角色
     * @param token
     * @param base64Security
     * @return
     */
    public static String getUserRole(String token, String base64Security){
        return parseJWT(token, base64Security).get("role", String.class);
    }

    /**
     * 是否已过期
     * @param token
     * @param base64Security
     * @return
     */
    public static boolean isExpiration(String token, String base64Security) {
        return parseJWT(token, base64Security).getExpiration().before(new Date());
    }

    /**
     * 根据HttpRequest里的token获取userId
     * @param request
     * @return
     */
    public static String getUserIdFromToken(HttpServletRequest request, Audience audience){
        // 获取请求头信息authorization信息
        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);

        // 获取token
        final String token = authHeader.substring(7);

        return getUserId(token, audience.getBase64Secret());
    }

    public static String getUserRoleFromToken(HttpServletRequest request, Audience audience){
        // 获取请求头信息authorization信息
        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);

        // 获取token
        final String token = authHeader.substring(7);

        return getUserRole(token, audience.getBase64Secret());
    }
}
