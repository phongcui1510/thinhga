package sixkiller.sample.common.response;

import java.io.Serializable;

public class CommonResponseBody implements Serializable {

     /**
      * 
      */
     private static final long serialVersionUID = 1L;
     private String error;
     private Object data;
     public String getError() {
          return error;
     }
     public void setError(String error) {
          this.error = error;
     }
     public Object getData() {
          return data;
     }
     public void setData(Object data) {
          this.data = data;
     }
}
