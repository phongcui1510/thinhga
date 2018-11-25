package sixkiller.sample.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import sixkiller.sample.exception.GlobalExceptionHandler;
import sixkiller.sample.restapi.dto.FailMessageDto;

@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

     private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);
     
     @Override
     public void commence(HttpServletRequest request,HttpServletResponse response,AuthenticationException authException)
          throws IOException, ServletException {
          logger.error("--MyAuthenticationEntryPoint--error");
          String msg = buildFailure(HttpStatus.UNAUTHORIZED.toString(), "Vui lòng đăng nhập.");
          response.setStatus(HttpStatus.UNAUTHORIZED.value());
          response.addHeader("Content-Type", "application/json");
          response.getWriter().write(msg);
     }

     protected String buildFailure(String errorCode, String errorMsg) {
          Gson gson = new Gson();
          FailMessageDto message = new FailMessageDto();
          message.setError(errorCode);
          message.setErrorDescription(errorMsg);
          return gson.toJson(message);
     }
}
