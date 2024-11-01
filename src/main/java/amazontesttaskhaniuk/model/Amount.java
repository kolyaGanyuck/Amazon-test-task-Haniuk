package amazontesttaskhaniuk.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Amount {
    double amount;
    String currencyCode;
}
