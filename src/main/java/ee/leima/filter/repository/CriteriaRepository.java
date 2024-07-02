package ee.leima.filter.repository;

import ee.leima.filter.model.Criteria;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CriteriaRepository extends CrudRepository<Criteria, Long>{
    List<Criteria> findByFilterId(Long id);
}
