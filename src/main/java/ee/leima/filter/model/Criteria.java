package ee.leima.filter.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Table("CRITERIA")
public class Criteria {
    @Id
    private Long id;
    private String type;
    private String comparison;
    private String valueText;
    private BigDecimal valueNumber;
    private LocalDate valueDate;
    private Long filterId;
}
