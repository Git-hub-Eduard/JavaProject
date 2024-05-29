package lk.ijse.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Supplier {
    private String id;
    private String name;
    private int price;
    private int amount;
}
