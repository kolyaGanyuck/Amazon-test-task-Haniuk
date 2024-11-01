package amazontesttaskhaniuk.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderedProductSales {
    double amount;
    String currencyCode;
}
