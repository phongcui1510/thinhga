package sixkiller.sample.common.response;

import java.io.Serializable;
import java.util.List;

import sixkiller.sample.domain.Address;
import sixkiller.sample.domain.Diagram;
import sixkiller.sample.domain.User;

public class ProjectDTO implements Serializable {
     private static final long serialVersionUID = 1L;
     private String projectId;
     private String name;
     private Address address;
     private String owner;
     private String acreage;
     private String introduction;
     private String priceRange;
     private Diagram diagramImage;
     private List<User> admins;
     private List<User> salers;
     public String getProjectId() {
          return projectId;
     }
     public void setProjectId(String projectId) {
          this.projectId = projectId;
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
