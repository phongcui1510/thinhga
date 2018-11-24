package sixkiller.sample.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import sixkiller.sample.domain.MiengDat;

@Repository
public interface MiengDatRepository extends MongoRepository<MiengDat, String> {
     
     public MiengDat findByCode(String code);
     public List<MiengDat> findByProjectId(String projectId);
     public MiengDat findByProjectIdAndLoso(String projectId, String loso);
}
