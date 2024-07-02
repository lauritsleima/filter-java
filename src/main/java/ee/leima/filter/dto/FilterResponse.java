package ee.leima.filter.dto;

import lombok.Data;

import java.util.List;

@Data
public class FilterResponse {
    String filterId;
    String filterName;
    List<CriteriaDto> criteria;
}
