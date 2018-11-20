package sixkiller.sample.configuration.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import sixkiller.sample.common.response.CommonResponseBody;

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
     private static Logger logger = LoggerFactory.getLogger(MyAccessDeniedHandler.class);

     @Override
     public void handle(HttpServletRequest httpServletRequest,
          HttpServletResponse httpServletResponse,
          AccessDeniedException e) throws IOException, ServletException {
          Authentication auth = SecurityContextHolder.getContext().getAuthentication();
          if (auth != null) {
               logger.info("User '" + auth.getName()
                    + "' attempted to access the protected URL: "
                    + httpServletRequest.getRequestURI());
          }
          CommonResponseBody body = new CommonResponseBody();
          body.setError("1");
          body.setData("Access denied!!");
          Gson gson = new Gson();
          httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
          httpServletResponse.addHeader("Content-Type", "application/json");
          httpServletResponse.getWriter().write(gson.toJson(body));
     }
}
