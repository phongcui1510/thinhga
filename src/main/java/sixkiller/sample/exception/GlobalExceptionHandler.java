package sixkiller.sample.exception;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.omg.CORBA.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.google.gson.Gson;

import sixkiller.sample.common.response.CommonResponseBody;
import sixkiller.sample.restapi.dto.FailMessageDto;

@ControllerAdvice(basePackages = "sixkiller.sample.restapi.controller")
public class GlobalExceptionHandler {
     private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

     @ExceptionHandler(Exception.class)
     public ResponseEntity<String> handleException(HttpServletRequest request, Exception ex) {
          logger.error("Error -handleException", ex);
          return buildFailure(HttpStatus.BAD_REQUEST.toString(), "Có lỗi xảy ra. Vui lòng thử lại.");
     }
     
     @ExceptionHandler(SystemException.class)
     public ResponseEntity<String> handleSystemException(HttpServletRequest request,SystemException ex) {
          logger.error("Error -handleSystemException", ex);
          return buildFailure(HttpStatus.BAD_REQUEST.toString(), "Có lỗi xảy ra. Vui lòng thử lại.");
     }
     
     @ExceptionHandler(AccessDeniedException.class)
     public ResponseEntity<String> handleAccessDeniedException(HttpServletRequest request,SystemException ex) {
          logger.error("Error -handleAccessDeniedException", ex);
          return buildFailure(HttpStatus.BAD_REQUEST.toString(), "Có lỗi xảy ra. Vui lòng thử lại.");
     }
     
     protected ResponseEntity<String> buildFailure(String errorCode, String errorMsg) {
          Gson gson = new Gson();
          FailMessageDto message = new FailMessageDto();
          message.setError(errorCode);
          message.setErrorDescription(errorMsg);
          return new ResponseEntity<String>(gson.toJson(message), HttpStatus.BAD_REQUEST);
     }
}
