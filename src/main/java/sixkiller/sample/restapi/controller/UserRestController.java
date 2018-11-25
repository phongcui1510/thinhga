package sixkiller.sample.restapi.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sixkiller.sample.domain.User;
import sixkiller.sample.restapi.dto.CreateUserDto;
import sixkiller.sample.restapi.dto.ModifyUserDto;
import sixkiller.sample.restapi.dto.SuccessMessageDto;
import sixkiller.sample.restapi.resource.ResourceCollection;
import sixkiller.sample.restapi.resource.UserResource;
import sixkiller.sample.service.UserService;
import sixkiller.sample.service.exception.UpdatedUserNotFoundException;

/**
 * Created by ala on 9.5.16.
 */
@RestController
@RequestMapping("/api/users")
public class UserRestController extends BaseController {

    public static final String OWNER = "authentication.name == #userName";
    public static final String ADMIN = "hasRole('ADMIN')";

    private UserService userService;
    
    @Autowired
    private ConsumerTokenServices tokenStore;

    @PreAuthorize(ADMIN + " or " + OWNER)
    @RequestMapping(path = "/{userName}", method = RequestMethod.GET)
    public ResponseEntity<UserResource> getUser(@PathVariable String userName) {

        Optional<User> userOptional = userService.findByUserName(userName);

        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(
                new UserResource(userOptional.get())
        );
    }

    @PreAuthorize(ADMIN)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResourceCollection<UserResource>> getUsers() {

        return ResponseEntity.ok(
                new ResourceCollection<>(
                        userService.findAll().stream()
                                .map(user -> new UserResource(user))
                                .collect(Collectors.toList())
                )
        );

    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ResponseEntity<UserResource> createUser(@Validated @RequestBody CreateUserDto userDto) {

         try {
           if (userService.findByUserName(userDto.getUserName()).isPresent()) {
               return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
           }
   
           User savedUser = userService.save(userDto);
   
           return ResponseEntity.created(
                   linkTo(methodOn(UserRestController.class).getUser(savedUser.getUserName()))
                           .toUri()
           ).body(
                   new UserResource(savedUser)
           );
         } catch (Exception ex) {
              System.err.println("Exception when creating users: " + ex.getMessage());
         }
         return null;
    }

    @PreAuthorize(ADMIN)
    @RequestMapping(path = "/{userName}", method = RequestMethod.PUT)
    public ResponseEntity<UserResource> createUser(@PathVariable String userName,
                                                   @Validated @RequestBody ModifyUserDto userDto) {

        User savedUser;
        try {
            savedUser = userService.save(userDto, userName);
        } catch (UpdatedUserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(
                new UserResource(savedUser)
        );
    }

    @PreAuthorize(ADMIN)
    @RequestMapping(path = "/{userName}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable String userName) {

        Optional<User> userOptional = userService.findByUserName(userName);

        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        userService.delete(userOptional.get());

        return ResponseEntity.ok().build();
    }
    
    @RequestMapping(value="/logout", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            tokenValue = tokenValue.replace("bearer", "").trim();
            Boolean a = tokenStore.revokeToken(tokenValue);
        }
        
        SuccessMessageDto message = new SuccessMessageDto("OK", "Đăng xuất thành công.");
        return buildSuccess(message);
    }

    @Autowired
    protected void setUserRepository(UserService userService) {
        this.userService = userService;
    }
}
