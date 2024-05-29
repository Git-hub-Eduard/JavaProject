package lk.ijse.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Salary {
    private String code;
    private String name;
    private String discription;
    private String ingredients;
    private int price;
    private String datetime;
}
