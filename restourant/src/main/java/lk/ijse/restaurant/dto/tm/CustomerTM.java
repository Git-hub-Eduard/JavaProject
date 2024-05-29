package lk.ijse.restaurant.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class CustomerTM {
    private String id;
    private String name;
    private int amountSalary;
    private int Price;
    private String NumberTable;
}
