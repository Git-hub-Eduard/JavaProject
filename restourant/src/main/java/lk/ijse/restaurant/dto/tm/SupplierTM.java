package lk.ijse.restaurant.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SupplierTM {
    private String id;
    private String name;
    private int price;
    private int amount;
}
