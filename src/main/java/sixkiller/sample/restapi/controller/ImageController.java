package sixkiller.sample.restapi.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import sixkiller.sample.common.Constants;
import sixkiller.sample.common.response.CommonResponseBody;
import sixkiller.sample.domain.MiengDat;
import sixkiller.sample.service.MiengDatService;

@RestController
@RequestMapping("/api/image")
public class ImageController {
     
     @Autowired
     private MiengDatService service;
     
     @RequestMapping(value="/{loso}", method = RequestMethod.POST)
     public ResponseEntity<CommonResponseBody> updateByExcel(@PathVariable("loso") String loso, @RequestParam("file") MultipartFile file) throws IOException {
          Map config = new HashMap();
          config.put("cloud_name", Constants.CLOUDINARY_NAME);
          config.put("api_key", Constants.CLOUDINARY_API_KEY);
          config.put("api_secret", Constants.CLOUDINARY_API_SECRET);
          Cloudinary cloudinary = new Cloudinary(config);
          Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
          MiengDat miengdat = service.getByLoso(loso);
          miengdat.setDiagram(uploadResult);
          miengdat = service.save(miengdat);
          CommonResponseBody body = new CommonResponseBody();
          body.setError(Constants.SUCCESS_CODE);
          body.setData(miengdat);
          return new ResponseEntity<CommonResponseBody>(body, HttpStatus.OK);
     }
}
