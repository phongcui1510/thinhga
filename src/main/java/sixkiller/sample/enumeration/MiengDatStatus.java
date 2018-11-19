package sixkiller.sample.enumeration;

public enum MiengDatStatus {
     FREE (0), BOOKED(1), COC_1_PHAN(2), COC_DU(3), KY_HOP_DONG(4);
     
     private int code;
     
     MiengDatStatus(int code) {
          this.code = code;
     }

     public int getCode() {
          return code;
     }

     public void setCode(int code) {
          this.code = code;
     }
}
