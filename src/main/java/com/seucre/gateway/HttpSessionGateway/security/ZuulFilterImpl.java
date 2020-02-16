package com.seucre.gateway.HttpSessionGateway.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.seucre.gateway.HttpSessionGateway.UserInfo;

@Component
public class ZuulFilterImpl extends ZuulFilter
{
	
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpSession sess = request.getSession(false);
        if(sess== null) {
        	System.out.println("session is null in ZULL FILTER");
        } else {
        UserInfo user = 	(UserInfo) sess.getAttribute("authUser");
        if(user ==null) {
        	System.out.println("user is null in zuul fiter");
        }else {
        	System.out.println("user in zull filter is "+user.toString());
        }
        }
        
        ctx.addZuulRequestHeader("userId", "123456789");
        ctx.setRequest(request);
        return ctx;
    }

}
