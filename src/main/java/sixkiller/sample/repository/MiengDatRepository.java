package sixkiller.sample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import sixkiller.sample.domain.MiengDat;

@Repository
public interface MiengDatRepository extends MongoRepository<MiengDat, String> {
     
     public MiengDat findByLoso(String loso);
}
