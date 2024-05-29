package lk.ijse.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Customer {
    private String id;
    private String name;
    private int amountSalary;
    private int Price;
    private String NumberTable;
}
