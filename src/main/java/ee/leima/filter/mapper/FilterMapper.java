package ee.leima.filter.mapper;

import ee.leima.filter.dto.FilterRequest;
import ee.leima.filter.dto.FilterResponse;
import ee.leima.filter.model.Criteria;
import ee.leima.filter.model.Filter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FilterMapper {

    @Mapping(source = "filterName", target = "name")
    @Mapping(source = "filterId", target = "id")
    Filter toEntity(FilterRequest filterRequest);

    @Mapping(source = "filter.name", target = "filterName")
    @Mapping(source = "filter.id", target = "filterId")
    FilterResponse toResponse(Filter filter, List<Criteria> criteria);
}
