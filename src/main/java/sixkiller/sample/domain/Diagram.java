package sixkiller.sample.domain;

import java.io.Serializable;
import java.util.List;

public class Diagram implements Serializable {
     private static final long serialVersionUID = 1L;
     private String url;
     private Integer width;
     private Integer height;
     private DiagramChildren children;
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
     public DiagramChildren getChildren() {
          return children;
     }
     public void setChildren(DiagramChildren children) {
          this.children = children;
     }
     
}
