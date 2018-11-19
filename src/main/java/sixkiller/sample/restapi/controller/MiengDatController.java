package sixkiller.sample.restapi.controller;

import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import sixkiller.sample.domain.MiengDat;
import sixkiller.sample.service.MiengDatService;

@RestController
@RequestMapping("/api/miengdat")
public class MiengDatController {
     
     @Autowired
     private MiengDatService service;
     
     @RequestMapping(method = RequestMethod.POST)
     public ResponseEntity<MiengDat> insert(@RequestBody MiengDat data) {
          MiengDat miengdat = service.save(data);
          return new ResponseEntity<MiengDat>(miengdat, HttpStatus.OK);
     }
     
     @RequestMapping(method = RequestMethod.PUT)
     public ResponseEntity<MiengDat> update(@RequestBody MiengDat data) {
          MiengDat miengdat = service.getByLoso(data.getLoso());
          BeanUtils.copyProperties(data, miengdat);
          MiengDat saved = service.save(miengdat);
          return new ResponseEntity<MiengDat>(saved, HttpStatus.OK);
     }
     
     @RequestMapping(method = RequestMethod.GET)
     public ResponseEntity<MiengDat> get(@RequestParam("loso") String loso) {
          MiengDat miengdat = service.getByLoso(loso);
          return new ResponseEntity<MiengDat>(miengdat, HttpStatus.OK);
     }
     
     @RequestMapping(value="/excel", method = RequestMethod.POST)
     public ResponseEntity<String> updateByExcel(@RequestParam("file") MultipartFile file) throws IOException {
          
          Workbook workbook = new XSSFWorkbook(file.getInputStream());
          Sheet datatypeSheet = workbook.getSheetAt(0);
          Iterator<Row> iterator = datatypeSheet.iterator();

          int row = 1;
          int cell = 1;
          while (iterator.hasNext()) {
              
              Row currentRow = iterator.next();
              Iterator<Cell> cellIterator = currentRow.iterator();

              MiengDat miengdat = null;
              while (cellIterator.hasNext()) {
                   
                  Cell currentCell = cellIterator.next();
                  if (currentCell.getCellTypeEnum() == CellType.STRING) {
                      System.out.print(currentCell.getStringCellValue() + "--");
                      if (cell == 1) {
                           miengdat = service.getByLoso(currentCell.getStringCellValue());
                      } else if (miengdat != null) {
                           if (cell == 2) {
                                miengdat.setSothuatheosodo(currentCell.getStringCellValue());
                           } else if (cell == 3) {
                                miengdat.setSoto(currentCell.getStringCellValue());
                           } else if (cell == 4) {
                                miengdat.setLoaidat(currentCell.getStringCellValue());
                           } else if (cell == 5) {
                                miengdat.setDientich(currentCell.getStringCellValue());
                           } else if (cell == 6) {
                                miengdat.setThanhtien(currentCell.getStringCellValue());
                           } else if (cell == 7) {
                                miengdat.setTenduong(currentCell.getStringCellValue());
                           } else if (cell == 8) {
                                miengdat.setLogioi(currentCell.getStringCellValue());
                           } else if (cell == 9) {
                                miengdat.setGhichu(currentCell.getStringCellValue());
                           }
                            
                      }
                  } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                      System.out.print(currentCell.getNumericCellValue() + "--");
                  }
                  cell++;
              }
              if (miengdat != null) {
                   service.save(miengdat);
              }
              cell = 1;
              row++; 
          }
          
          return new ResponseEntity<String>("OK", HttpStatus.OK);
     }
}
