package sixkiller.sample.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class RestApiResourceServerConfiguration extends ResourceServerConfigurerAdapter {
     public static final String RESOURCE_ID = "restservice";

     @Autowired
     private AccessDeniedHandler accessDeniedHandler;
     
     @Override
     public void configure(ResourceServerSecurityConfigurer resources) {
          resources.resourceId(RESOURCE_ID);
     }

     @Override
     public void configure(HttpSecurity http) throws Exception {
          http.csrf().disable()
               .authorizeRequests()
               .antMatchers("/api/miengdat/**").authenticated()
               .anyRequest().permitAll()
               .and()
               .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
     }
}
