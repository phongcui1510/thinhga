package sixkiller.sample.restapi.dto;

public class MessageDto {
     private String error;
     private String error_description;
     public MessageDto() {
          
     }
     public MessageDto(String error, String description) {
          this.error = error;
          this.error_description = description;
     }
     public String getError() {
          return error;
     }
     public void setError(String error) {
          this.error = error;
     }
     public String getErrorDescription() {
          return error_description;
     }
     public void setErrorDescription(String errorDescription) {
          this.error_description = errorDescription;
     }
     
}
