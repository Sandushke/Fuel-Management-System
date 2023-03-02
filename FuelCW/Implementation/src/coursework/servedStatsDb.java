package coursework;

import java.sql.*;
import java.util.ArrayList;

public class servedStatsDb {
   public void addServedInfo(int customerID, Customer customer, DateTime dateTime, int dispenseId){
      String query="insert into customer values (?,?,?,?,?)";
      try {
         Class.forName("com.mysql.jdbc.Driver");
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fuelcw", "root", "sandushkedealwis_03");
         PreparedStatement pst = con.prepareStatement(query);
         pst.setInt(1, customerID);
         pst.setInt(2, customer.getRegistrationNumber());
         pst.setInt(3, customer.getVehicleType());
         pst.setInt(4, customer.getFuelType());
         pst.setFloat(5, customer.getFuelNeeded());
         pst.execute();

         String query2 = "insert into serverinfo values (?,?,?,?,?)";
         PreparedStatement pst2 = con.prepareStatement(query2);
         pst2.setInt(1, customerID);
         pst2.setInt(2, dateTime.getDate());
         pst2.setInt(3, dateTime.getMonth());
         pst2.setInt(4, dateTime.getYear());
         pst2.setInt(5, dispenseId);
         pst2.execute();
      } catch (Exception e){
         System.out.println(e);
      }
   }
}
