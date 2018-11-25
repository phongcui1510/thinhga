package sixkiller.sample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import sixkiller.sample.domain.Project;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
     
     public Project findByProjectId(String projectId);
}
