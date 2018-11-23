package sixkiller.sample.common.response;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class MiengDatDTO implements Serializable {
     private static final long serialVersionUID = 1L;
     private String id;
     private String loso;
     private String sothuatheosodo;
     private String soto;
     private String loaidat;
     private String dientich;
     private String thanhtien;
     private String tenduong;
     private String logioi;
     private String status;
     private String toado;
     private String ghichu;
     private Map diagram;
     private String projectId;
     private String createdBy;
     private Date createdDate;
     private String modifiedBy;
     private Date modifiedDate;
     
     public String getId() {
          return id;
     }
     public void setId(String id) {
          this.id = id;
     }
     public String getLoso() {
          return loso;
     }
     public void setLoso(String loso) {
          this.loso = loso;
     }
     public String getSothuatheosodo() {
          return sothuatheosodo;
     }
     public void setSothuatheosodo(String sothuatheosodo) {
          this.sothuatheosodo = sothuatheosodo;
     }
     public String getSoto() {
          return soto;
     }
     public void setSoto(String soto) {
          this.soto = soto;
     }
     public String getLoaidat() {
          return loaidat;
     }
     public void setLoaidat(String loaidat) {
          this.loaidat = loaidat;
     }
     public String getDientich() {
          return dientich;
     }
     public void setDientich(String dientich) {
          this.dientich = dientich;
     }
     public String getThanhtien() {
          return thanhtien;
     }
     public void setThanhtien(String thanhtien) {
          this.thanhtien = thanhtien;
     }
     public String getTenduong() {
          return tenduong;
     }
     public void setTenduong(String tenduong) {
          this.tenduong = tenduong;
     }
     public String getLogioi() {
          return logioi;
     }
     public void setLogioi(String logioi) {
          this.logioi = logioi;
     }
     public String getStatus() {
          return status;
     }
     public void setStatus(String status) {
          this.status = status;
     }
     public String getToado() {
          return toado;
     }
     public void setToado(String toado) {
          this.toado = toado;
     }
     public String getGhichu() {
          return ghichu;
     }
     public void setGhichu(String ghichu) {
          this.ghichu = ghichu;
     }
     public Map getDiagram() {
          return diagram;
     }
     public void setDiagram(Map diagram) {
          this.diagram = diagram;
     }
     public String getProjectId() {
          return projectId;
     }
     public void setProjectId(String projectId) {
          this.projectId = projectId;
     }
     public String getCreatedBy() {
          return createdBy;
     }
     public void setCreatedBy(String createdBy) {
          this.createdBy = createdBy;
     }
     public Date getCreatedDate() {
          return createdDate;
     }
     public void setCreatedDate(Date createdDate) {
          this.createdDate = createdDate;
     }
     public String getModifiedBy() {
          return modifiedBy;
     }
     public void setModifiedBy(String modifiedBy) {
          this.modifiedBy = modifiedBy;
     }
     public Date getModifiedDate() {
          return modifiedDate;
     }
     public void setModifiedDate(Date modifiedDate) {
          this.modifiedDate = modifiedDate;
     }
     
}
