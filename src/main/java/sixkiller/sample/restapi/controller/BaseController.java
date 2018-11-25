package sixkiller.sample.restapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;

import sixkiller.sample.restapi.dto.FailMessageDto;

public class BaseController {
     
     protected ResponseEntity<String> buildSuccess(Object data) {
          Gson gson = new Gson();
          return new ResponseEntity<String>(gson.toJson(data), HttpStatus.OK);
     }
     
     protected ResponseEntity<String> buildFailure(String errorCode, String errorMsg) {
          Gson gson = new Gson();
          FailMessageDto message = new FailMessageDto();
          message.setError(errorCode);
          message.setErrorDescription(errorMsg);
          return new ResponseEntity<String>(gson.toJson(message), HttpStatus.BAD_REQUEST);
     }
}
