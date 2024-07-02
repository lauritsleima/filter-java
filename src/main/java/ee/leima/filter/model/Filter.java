package ee.leima.filter.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("FILTER")
public class Filter {
    @Id
    private Long id;
    private String name;
}
