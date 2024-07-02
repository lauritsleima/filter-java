package ee.leima.filter.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CriteriaDto {
    Long id;
    String type;
    String comparison;
    String valueText;
    BigDecimal valueNumber;
    LocalDate valueDate;
}
