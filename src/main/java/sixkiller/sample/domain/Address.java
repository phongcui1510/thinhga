package sixkiller.sample.domain;

import java.io.Serializable;

public class Address implements Serializable {
     private static final long serialVersionUID = 1L;
     private String description;
     private String latutude;
     private String longitude;
     public String getDescription() {
          return description;
     }
     public void setDescription(String description) {
          this.description = description;
     }
     public String getLatutude() {
          return latutude;
     }
     public void setLatutude(String latutude) {
          this.latutude = latutude;
     }
     public String getLongitude() {
          return longitude;
     }
     public void setLongitude(String longitude) {
          this.longitude = longitude;
     }
     
}
