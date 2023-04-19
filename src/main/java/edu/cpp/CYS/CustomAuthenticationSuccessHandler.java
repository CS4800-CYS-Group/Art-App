package edu.cpp.CYS;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Makes it so access isn't denied when a user logs in
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

   @Override
   public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
       response.sendRedirect("/us/" + authentication.getName());

   }
}