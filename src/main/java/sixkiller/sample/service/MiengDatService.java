package sixkiller.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sixkiller.sample.domain.MiengDat;
import sixkiller.sample.repository.MiengDatRepository;

@Service
public class MiengDatService {
     
     @Autowired
     private MiengDatRepository repository;
     
     public MiengDat save (MiengDat entity) {
          return repository.save(entity);
     }
     
     public MiengDat getByCode (String code) {
          return repository.findByCode(code);
     }
     
     public List<MiengDat> findByProjectId (String projectId) {
          return repository.findByProjectId(projectId);
     }
}
