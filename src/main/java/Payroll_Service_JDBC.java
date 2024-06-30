
import java.sql.*;

public class Payroll_Service_JDBC{
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/payroll_service";
        String user = "root";
        String password = "****";
        String selectQuery="select * from employee_payroll;";
        String updateQuery = "UPDATE employee_payroll SET Salary=300000 WHERE name='Anjali'";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            if (con != null) {
                System.out.println("Database connected successfully!");
                System.out.println("Connection Info: " + con);
                java.sql.Driver driver = DriverManager.getDriver(url);
                System.out.println("JDBC Driver: " + driver.getClass().getName());
                java.util.Enumeration<java.sql.Driver> driverList = DriverManager.getDrivers();
                while (driverList.hasMoreElements()) {
                    java.sql.Driver d = driverList.nextElement();
                    System.out.println("Registered JDBC Driver: " + d.getClass().getName());
                }
                Statement stmt = con.createStatement();

                int rowsAffected = stmt.executeUpdate(updateQuery);
                if (rowsAffected > 0) {
                    System.out.println("Salary updated successfully for Anjali.");
                } else {
                    System.out.println("No records updated. Please check the name 'Anjali' in the database.");
                }

                ResultSet rs = stmt.executeQuery(selectQuery);
                while (rs.next()) {
                    int emp_id = rs.getInt("employee_id");
                    String name = rs.getString("name");
                    String gender = rs.getString("gender");
                    Double salary = rs.getDouble("salary");
                    String start_date = rs.getString("start_date");
                    String ph_number = rs.getString("phone_no");
                    String address = rs.getString("Address");
                    String department = rs.getString("Department");
                    int Basic_pay = rs.getInt("Basic_pay");
                    int Deductions = rs.getInt("Deductions");
                    int Taxable_pay = rs.getInt("Taxable_pay");
                    int Income_tax = rs.getInt("Income_tax");
                    int Net_pay = rs.getInt("Net_pay");
                    System.out.println(emp_id + " " + name + " " + gender + " " + salary + " " + start_date + " " + ph_number + " " + address + " " + department + " " + Basic_pay + " " + Deductions + " " + Taxable_pay + " " + Income_tax + " " + Net_pay);
                    System.out.println();
                }

                con.close();
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }

    }
}