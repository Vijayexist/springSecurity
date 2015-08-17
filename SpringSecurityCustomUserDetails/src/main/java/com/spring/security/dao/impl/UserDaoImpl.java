package com.spring.security.dao.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

import com.spring.security.domain.MyUser;

public class UserDaoImpl extends  SavedRequestAwareAuthenticationSuccessHandler {

	public MyUser getUser(String username) {
		
		
		MyUser user = new MyUser();
		user.setUserId(3035);
		user.setPassword("ss3035");
		user.setRole("user");
		user.setUsername("3035");
		return user;
	}

	public void saveUser(MyUser user) 
	{
		System.out.println("user saved successfully");
	}
	
	
	 @Override
	    public void onAuthenticationSuccess(HttpServletRequest request,
	            HttpServletResponse response, Authentication authentication)
	            throws ServletException, IOException {
	        // TODO Auto-generated method stub
	        String ctoken = (String) request.getSession().getAttribute("mytoken");
	        DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY");
	        if( defaultSavedRequest != null && ctoken != null ) {
	            String requestUrl = defaultSavedRequest.getRequestURL() + "?" + defaultSavedRequest.getQueryString();
	            requestUrl = UrlTool.addParamToURL(requestUrl, "mytoken", ctoken, true);
	            getRedirectStrategy().sendRedirect(request, response, requestUrl);
	        } else {
	            super.onAuthenticationSuccess(request, response, authentication);
	        }
	    }
}
