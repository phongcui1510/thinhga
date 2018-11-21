package sixkiller.sample.exception;

public class SystemException extends RuntimeException {
     private String error;
     private String errorDescription;
     public SystemException(){}
     public SystemException (String error, String description) {
          this.error = error;
          this.errorDescription = description;
     }
     public String getError() {
          return error;
     }
     public void setError(String error) {
          this.error = error;
     }
     public String getErrorDescription() {
          return errorDescription;
     }
     public void setErrorDescription(String errorDescription) {
          this.errorDescription = errorDescription;
     }
     
}
