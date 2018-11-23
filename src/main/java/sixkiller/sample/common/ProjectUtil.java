package sixkiller.sample.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectUtil {
     
     public static String generateProjectCode () {
          SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
          return df.format(new Date());
     }
     
     public static String generateProjectCode (Date date) {
          SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
          return df.format(date);
     }
     
     public static String generateMiengdatCode (String projectId) {
          Date date = new Date();
          StringBuffer sb = new StringBuffer("P" + projectId + "MD" + date.getTime());
          return sb.toString();
     }
}
