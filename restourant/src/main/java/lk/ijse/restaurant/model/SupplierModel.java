package lk.ijse.restaurant.model;

import lk.ijse.restaurant.dto.Supplier;
import lk.ijse.restaurant.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {

    public static int save(Supplier supplier) throws SQLException {

        String sql = "INSERT INTO suppliers VALUES (?,?,?,?)";

        return CrudUtil.execute(
                sql,
                supplier.getId(),
                supplier.getName(),
                supplier.getPrice(),
                supplier.getAmount()
        );
    }

    public static Supplier search(String id) throws SQLException {

        String sql = "SELECT * FROM suppliers WHERE id=?";

        ResultSet resultSet = CrudUtil.execute(sql, id);

        if (resultSet.next()) {
            return new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4)
            );
        }
        return null;
    }

    public static int update(Supplier supplier) throws SQLException {

        String sql = "UPDATE suppliers SET name=? , price=? , amount=? WHERE id=?";

        return CrudUtil.execute(
                sql,
                supplier.getName(),
                supplier.getPrice(),
                supplier.getAmount(),
                supplier.getId()
        );
    }

    public static int delete(String id) throws SQLException {
        String sql = "DELETE FROM suppliers WHERE id=?";
        return CrudUtil.execute(sql, id);
    }

    public static List<Supplier> getAll() throws SQLException {

        List<Supplier> supplierList = new ArrayList<>();
        String sql = "SELECT * FROM suppliers";
        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()) {
            Supplier supplier= new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4)
            );
            supplierList.add(supplier);
        }
        return supplierList;
    }
}
