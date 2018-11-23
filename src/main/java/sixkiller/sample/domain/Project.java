package sixkiller.sample.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Project {
     @Id
     @JsonIgnore
     private String id;
     private String code;
     private String name;
     private Address address;
     private String owner;
     private String acreage;
     private String introduction;
     private String priceRange;
     private Diagram diagramImage;
     private List<User> admins;
     private List<User> salers;
     public String getCode() {
          return code;
     }
     public void setCode(String code) {
          this.code = code;
     }
     public String getName() {
          return name;
     }
     public void setName(String name) {
          this.name = name;
     }
     public Address getAddress() {
          return address;
     }
     public void setAddress(Address address) {
          this.address = address;
     }
     public String getOwner() {
          return owner;
     }
     public void setOwner(String owner) {
          this.owner = owner;
     }
     public String getAcreage() {
          return acreage;
     }
     public void setAcreage(String acreage) {
          this.acreage = acreage;
     }
     public String getIntroduction() {
          return introduction;
     }
     public void setIntroduction(String introduction) {
          this.introduction = introduction;
     }
     public String getPriceRange() {
          return priceRange;
     }
     public void setPriceRange(String priceRange) {
          this.priceRange = priceRange;
     }
     public Diagram getDiagramImage() {
          return diagramImage;
     }
     public void setDiagramImage(Diagram diagramImage) {
          this.diagramImage = diagramImage;
     }
     public List<User> getAdmins() {
          return admins;
     }
     public void setAdmins(List<User> admins) {
          this.admins = admins;
     }
     public List<User> getSalers() {
          return salers;
     }
     public void setSalers(List<User> salers) {
          this.salers = salers;
     }
}
