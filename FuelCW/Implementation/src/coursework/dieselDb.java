package coursework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class dieselDb {
    public void addDieselDispenser(String vehiclesAllowed, float price, int dispenseID){
        String query="insert into dieseldispenser values (?,?,?,?)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fuelcw", "root", "sandushkedealwis_03");
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, dispenseID);
            pst.setString(2, vehiclesAllowed);
            pst.setFloat(3, price);
            pst.execute();
        } catch (Exception e){
            System.out.println(e);
        }

    }
}