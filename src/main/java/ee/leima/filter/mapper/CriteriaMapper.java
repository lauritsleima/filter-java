package ee.leima.filter.mapper;

import ee.leima.filter.dto.CriteriaDto;
import ee.leima.filter.model.Criteria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CriteriaMapper {

    @Mapping(target = "filterId", ignore = true)
    Criteria toEntity(CriteriaDto criteriaDto);

    List<CriteriaDto> toDto(List<Criteria> criteria);
}
