package sixkiller.sample.service;

import java.util.List;

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
     
     public Project getByProjectId (String code) {
          return repository.findByProjectId(code);
     }
     
     public List<Project> getAll () {
          return repository.findAll();
     }
}
