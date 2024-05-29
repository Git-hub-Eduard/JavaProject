package lk.ijse.restaurant.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SalaryTM {
    private String code;
    private String name;
    private String discription;
    private String ingredients;
    private int price;
    private String datetime;
}
