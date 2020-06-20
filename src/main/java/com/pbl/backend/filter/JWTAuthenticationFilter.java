package com.pbl.backend.filter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbl.backend.common.response.Result;
import com.pbl.backend.common.response.ResultCode;
import com.pbl.backend.entity.JwtUser;
import com.pbl.backend.model.LoginUser;
import com.pbl.backend.entity.Audience;
import com.pbl.backend.utils.JwtTokenUtil;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author: 杜东方
 * @date: 2020/5/17
 * @description: 用户身份认证
 * 如果用户名和密码正确，那么过滤器将创建一个JWT Token
 * 并在HTTP Response 的header中返回它，格式：token: "Bearer +具体token值"
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private Audience audience;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, Audience audience){
        this.authenticationManager = authenticationManager;
        this.audience = audience;
        super.setFilterProcessesUrl("/user/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //从输入流获取到登录信息
        try{
            LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>())
            );
        }
        catch (Exception e){
            try{
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                response.getWriter().write(JSONObject.toJSONString(new Result(ResultCode.USERNAME_PASSWORD_ERROR)));
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
            return null;
        }
    }


    // 成功验证后调用的方法
    // 如果验证成功，就生成token并返回
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 查看源代码会发现调用getPrincipal()方法会返回一个实现了`UserDetails`接口的对象
        // 所以就是JwtUser啦
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        System.out.println("jwtUser:" + jwtUser.toString());


//        if(audience == null){
//            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
//            audience = (Audience) factory.getBean("audience");
//        }
        String role = "";
        // 因为在JwtUser中存了权限信息，可以直接获取，由于只有一个角色就这么干了
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        for (GrantedAuthority authority : authorities){
            role = authority.getAuthority();
        }
        String token = JwtTokenUtil.createJWT(jwtUser.getId(), jwtUser.getUsername(), role, audience);
        // 返回创建成功的token
        // 但是这里创建的token只是单纯的token
        // 按照jwt的规定，最后请求的格式应该是 `Bearer token`
        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token",token);
        jsonObject.put("role", role);
        jsonObject.put("profilePhoto", jwtUser.getProfilePhoto());
        Result result = Result.SUCCESS(jsonObject);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSONObject.toJSON(result).toString());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        System.out.println("------------");
        response.getWriter().write("authentication failed, reason: " + failed.getMessage());    }
}
