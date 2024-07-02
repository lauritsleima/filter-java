package ee.leima.filter.repository;

import ee.leima.filter.model.Filter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilterRepository extends CrudRepository<Filter, Long>{

    @Override
    List<Filter> findAll();

}
