package sixkiller.sample.restapi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import sixkiller.sample.common.ProjectUtil;
import sixkiller.sample.common.response.MiengDatDTO;
import sixkiller.sample.domain.MiengDat;
import sixkiller.sample.exception.SystemException;
import sixkiller.sample.service.MiengDatService;

@RestController
@RequestMapping("/api/miengdat")
public class MiengDatController extends BaseController {
     
     @Autowired
     private MiengDatService service;
     
     @RequestMapping(method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
     public ResponseEntity<String> insert(@RequestBody MiengDat data) {
          if (StringUtils.isEmpty(data.getProjectId())) {
               return buildFailure("400", "Nhập ID của dự án");
          }
          data.setCode(ProjectUtil.generateMiengdatCode(data.getProjectId()));
          MiengDat miengdat = service.save(data);
          return buildSuccess(miengdat.toDTO());
     }
     
     @RequestMapping(method = RequestMethod.PUT, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
     public ResponseEntity<String> update(@RequestBody MiengDat data) throws SystemException {
          MiengDat miengdat = service.getByCode(data.getCode());
          BeanUtils.copyProperties(data, miengdat);
          MiengDat saved = service.save(miengdat);
          return buildSuccess(saved.toDTO());
     }
     
     @RequestMapping(method = RequestMethod.GET,produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
     public ResponseEntity<String> get(@RequestParam("id") String id) {
          MiengDat miengdat = service.getByCode(id);
          return buildSuccess(miengdat.toDTO());
     }
     
     @RequestMapping(value="/list", method = RequestMethod.GET,produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
     public ResponseEntity<String> list(@RequestParam("projectId") String projectId) {
          List<MiengDat> miengdat = service.findByProjectId(projectId);
          List<MiengDatDTO> dtos = new ArrayList<>();
          if (CollectionUtils.isNotEmpty(miengdat)) {
               for (MiengDat m : miengdat) {
                    dtos.add(m.toDTO());
               }
          }
          return buildSuccess(dtos);
     }
     
     @RequestMapping(value="/excel", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
     public ResponseEntity<String> updateByExcel(@RequestParam("file") MultipartFile file) throws IOException {
          
          Workbook workbook = new XSSFWorkbook(file.getInputStream());
          Sheet datatypeSheet = workbook.getSheetAt(0);
          Iterator<Row> iterator = datatypeSheet.iterator();

          int row = 1;
          int cell = 1;
          List<MiengDatDTO> returnLst = new ArrayList<MiengDatDTO>();
          while (iterator.hasNext()) {
              
              Row currentRow = iterator.next();
              Iterator<Cell> cellIterator = currentRow.iterator();

              MiengDat miengdat = null;
              while (cellIterator.hasNext()) {
                   
                  Cell currentCell = cellIterator.next();
                  if (currentCell.getCellTypeEnum() == CellType.STRING) {
                      System.out.print(currentCell.getStringCellValue() + "--");
                      if (cell == 1) {
                           miengdat = service.getByCode(currentCell.getStringCellValue());
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
                   miengdat = service.save(miengdat);
                   returnLst.add(miengdat.toDTO());
              }
              cell = 1;
              row++; 
          }
          return buildSuccess(returnLst);
     }
     
}
