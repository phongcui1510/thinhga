package sixkiller.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sixkiller.sample.domain.Project;
import sixkiller.sample.repository.ProjectRepository;

@Service
public class ProjectService {
     
     @Autowired
     private ProjectRepository repository;
     
     public Project save (Project project) {
          return repository.save(project);
     }
     
     public Project getByCode (String code) {
          return repository.findByCode(code);
     }
}
