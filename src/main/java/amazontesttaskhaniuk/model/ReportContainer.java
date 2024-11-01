package amazontesttaskhaniuk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ReportContainer {
    @Id
    String id;
    ReportSpecification reportSpecification;
    List<SalesAndTrafficByDate> salesAndTrafficByDate;
    List<SalesAndTrafficByAsin> salesAndTrafficByAsin;
}
