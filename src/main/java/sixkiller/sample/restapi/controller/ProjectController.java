package sixkiller.sample.restapi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import sixkiller.sample.common.CloudinaryConstants;
import sixkiller.sample.common.Constants;
import sixkiller.sample.common.ProjectUtil;
import sixkiller.sample.domain.Diagram;
import sixkiller.sample.domain.DiagramChildren;
import sixkiller.sample.domain.Image;
import sixkiller.sample.domain.Project;
import sixkiller.sample.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController extends BaseController {
     @Autowired
     private ProjectService service;

     @RequestMapping(method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
     public ResponseEntity<String> insert(@RequestBody Project data) {
          data.setCode(ProjectUtil.generateProjectCode());
          Project project = service.save(data);
          return buildSuccess(project.toDTO());
     }

     @RequestMapping(method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
     public ResponseEntity<String> getByCode(@RequestParam("id") String code) {
          Project project = service.getByCode(code);
          return buildSuccess(project.toDTO());
     }

     @RequestMapping(value="/diagrams", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
     public ResponseEntity<String> uploadDiagrams(@RequestParam("projectId") String code, @RequestParam("row") Integer row, @RequestParam("col") Integer col,
          @RequestParam("files") MultipartFile[] files) throws IOException {
          Project project = service.getByCode(code);
          if (project.getDiagramImage() != null) {
             if (files != null && files.length >  0) {
                  List<Image> images = new ArrayList<Image>();
                  for (int i = 0;i<files.length;i++) {
                       Map config = new HashMap();
                       config.put("cloud_name", Constants.CLOUDINARY_NAME);
                       config.put("api_key", Constants.CLOUDINARY_API_KEY);
                       config.put("api_secret", Constants.CLOUDINARY_API_SECRET);
                       Cloudinary cloudinary = new Cloudinary(config);
                       String filename = files[i].getOriginalFilename();
                       Map params = ObjectUtils.asMap("public_id", project.getCode() + "_" + filename);
                       Map<String, Object> uploadResult = cloudinary.uploader().upload(files[i].getBytes(), params);
                       Image img = new Image();
                       img.setUrl((String)uploadResult.get(CloudinaryConstants.url));
                       img.setHeight((Integer)uploadResult.get(CloudinaryConstants.height));
                       img.setWidth((Integer)uploadResult.get(CloudinaryConstants.height));
                       String se = filename.substring(filename.indexOf("_sequence_") + 10, 
                            filename.indexOf((String)uploadResult.get(CloudinaryConstants.format)) - 1);
                       img.setSequence(Integer.valueOf(se));
                       images.add(img);
                  }
                  if (CollectionUtils.isNotEmpty(images)) {
                       images.sort(new Comparator<Image>() {

                         @Override
                         public int compare(Image o1,Image o2) {
                              return o1.getSequence().compareTo(o2.getSequence());
                         }
                    });
                  }
                  DiagramChildren children = new DiagramChildren();
                  children.setImages(images);
                  children.setCol(col);
                  children.setRow(row);
                  project.getDiagramImage().setChildren(children);
                  project = service.save(project);
             }
             return buildSuccess(project.toDTO());
          } else {
             return buildFailure("400", "Vui lòng upload image tổng của dự án trước");
          }
     }

     @RequestMapping(value = "/diagram", method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
     public ResponseEntity<String> uploadDiagram(@RequestParam("projectId") String code,
          @RequestParam("file") MultipartFile file) throws IOException {
          Project project = service.getByCode(code);
          Map config = new HashMap();
          config.put("cloud_name", Constants.CLOUDINARY_NAME);
          config.put("api_key", Constants.CLOUDINARY_API_KEY);
          config.put("api_secret", Constants.CLOUDINARY_API_SECRET);
          Cloudinary cloudinary = new Cloudinary(config);
          Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
          Diagram diagram = new Diagram();
          diagram.setUrl((String) uploadResult.get(CloudinaryConstants.url));
          diagram.setHeight((Integer) uploadResult.get(CloudinaryConstants.height));
          diagram.setWidth((Integer) uploadResult.get(CloudinaryConstants.height));
          project.setDiagramImage(diagram);
          project = service.save(project);
          return buildSuccess(project);
     }
}
