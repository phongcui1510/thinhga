package sixkiller.sample.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.omg.CORBA.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import sixkiller.sample.common.response.CommonResponseBody;

@ControllerAdvice(basePackages = "ixkiller.sample.restapi")
public class GlobalExceptionHandler {
     private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

     // TODO This SystemException will removed once we have implement all the exception
     @ExceptionHandler(Exception.class)
     public ResponseEntity<CommonResponseBody> handleSystemException(HttpServletRequest request,SystemException ex) {
          logger.error("Error -handleSystemException", ex);
          return getResponse(ex);
     }
     
     private ResponseEntity<CommonResponseBody> getResponse(Exception ex) {
          CommonResponseBody body = new CommonResponseBody();
          body.setError("1");
          body.setData("Exception!!");
          return new ResponseEntity<>(body, HttpStatus.OK);
     }
}
