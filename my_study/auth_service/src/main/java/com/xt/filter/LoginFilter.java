package com.xt.filter;

import com.alibaba.fastjson.JSONObject;
import com.xt.entity.ResultInfo;
import com.xt.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebFault;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@WebFilter("/login/*")
public class LoginFilter implements Filter {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;

        //解决跨域问题
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        String token = request.getHeader("token");

        //如果未携带token，抛出异常
        if(token==null||token=="")
            return;

        //查询token在redis的剩余时间
        Long expire = redisUtil.getExpire(token);
        //登录，放行
        if(expire>0){
            //重置token的时间
            redisUtil.expire(token,30, TimeUnit.MINUTES);
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            //未登录响应前台
            String res= JSONObject.toJSONString(new ResultInfo<>(401,"未登录",null));
            response.setContentType("json/text;charset=utf-8");
            response.getWriter().write(res);
        }

    }

    @Override
    public void destroy() {

    }
}
