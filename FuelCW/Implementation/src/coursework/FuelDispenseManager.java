package coursework;

import java.sql.SQLException;

public interface FuelDispenseManager {
    void serveCustomer(DateTime date) throws SQLException, ClassNotFoundException;
}
