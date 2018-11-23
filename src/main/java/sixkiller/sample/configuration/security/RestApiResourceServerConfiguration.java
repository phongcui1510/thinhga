package sixkiller.sample.configuration.security;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.web.context.request.RequestAttributes;

import sixkiller.sample.configuration.MyAuthenticationEntryPoint;

@Configuration
@EnableResourceServer
public class RestApiResourceServerConfiguration extends ResourceServerConfigurerAdapter {
     public static final String RESOURCE_ID = "restservice";

     @Autowired
     private MyAccessDeniedHandler accessDeniedHandler;
     
     @Autowired
     private MyAuthenticationEntryPoint entryPoint;
     
     @Override
     public void configure(ResourceServerSecurityConfigurer resources) {
          resources.resourceId(RESOURCE_ID);
     }

     @Override
     public void configure(HttpSecurity http) throws Exception {
          http.csrf().disable()
               .authorizeRequests()
               .antMatchers("/api/miengdat/**").authenticated()
               .antMatchers("/api/project/**").authenticated()
               .anyRequest().permitAll()
               .and()
               .exceptionHandling()
               .accessDeniedHandler(accessDeniedHandler)
               .authenticationEntryPoint(entryPoint);
               
     }
     
     @Bean
     public ErrorAttributes errorAttributes() {
         return new DefaultErrorAttributes() {
             @Override
             public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
                 Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
                 Throwable error = getError(requestAttributes);
                 if(error instanceof AccessDeniedException){
                     errorAttributes.clear();
                     errorAttributes.put("error", "unauthorized");
                     errorAttributes.put("error_description", "Có lỗi xảy ra");
                 }
              return errorAttributes;
             }
         };
     }
}
