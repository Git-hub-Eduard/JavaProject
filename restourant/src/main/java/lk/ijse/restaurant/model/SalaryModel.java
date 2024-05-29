package lk.ijse.restaurant.model;

import lk.ijse.restaurant.dto.Salary;
import lk.ijse.restaurant.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryModel {

    public static int save(Salary salary) throws SQLException {
        String sql = "INSERT INTO salary VALUES (?,?,?,?,?,?)";
        try {
        return CrudUtil.execute(
                sql,
                salary.getCode(),
                salary.getName(),
                salary.getDiscription(),
                salary.getIngredients(),
                salary.getPrice(),
                salary.getDatetime()
        );
        }catch (SQLException e){
                System.err.println("SQL помилка: " + e.getMessage());
                e.printStackTrace(); // Виведення детальної інформації про помилку
                return -1;
            }

    }

    public static Salary search(String code) throws SQLException {

        String sql = "SELECT * FROM salary WHERE code=?";
        ResultSet resultSet = CrudUtil.execute(sql, code);

        if (resultSet.next()) {
            return new Salary(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6)
            );
        }
        return null;
    }

    public static int update(Salary salary) throws SQLException {

        String sql = "UPDATE salary SET NameSalary=? , discription=? , ingredients=? , price=? , date=? WHERE code=?";
        return CrudUtil.execute(
                sql,
                salary.getName(),
                salary.getDiscription(),
                salary.getIngredients(),
                salary.getPrice(),
                salary.getDatetime(),
                salary.getCode()
        );
    }

    public static int delete(String code) throws SQLException {
        String sql = "DELETE FROM salary WHERE code=?";
        return CrudUtil.execute(sql, code);
    }

    public static List<Salary> getAll() throws SQLException {

        List<Salary> salaryList = new ArrayList<>();
        String sql = "SELECT * FROM salary";
        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()) {
            Salary salary= new Salary(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6)

            );
            salaryList.add(salary);
        }
        return salaryList;
    }
}
