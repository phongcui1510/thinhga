package sixkiller.sample.domain;

import java.io.Serializable;

public class Image implements Serializable  {
     private static final long serialVersionUID = 1L;
     private String url;
     private Integer width;
     private Integer height;
     private String format;
     private Integer bytes;
     private Integer sequence;
     public Integer getSequence() {
          return sequence;
     }
     public void setSequence(Integer sequence) {
          this.sequence = sequence;
     }
     public String getUrl() {
          return url;
     }
     public void setUrl(String url) {
          this.url = url;
     }
     public Integer getWidth() {
          return width;
     }
     public void setWidth(Integer width) {
          this.width = width;
     }
     public Integer getHeight() {
          return height;
     }
     public void setHeight(Integer height) {
          this.height = height;
     }
     public String getFormat() {
          return format;
     }
     public void setFormat(String format) {
          this.format = format;
     }
     public Integer getBytes() {
          return bytes;
     }
     public void setBytes(Integer bytes) {
          this.bytes = bytes;
     }
     
}
