import java.sql.*;

public class Payroll_Service_JDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/payroll_service";
        String user = "root";
        String password = "****";
        String selectQuery = "SELECT * FROM employee_payroll WHERE start_date BETWEEN CAST('2024-01-03' AS DATE) AND DATE (NOW())";

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
                System.out.println();

                Statement stmt = con.createStatement();


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
                }
                System.out.println();

                System.out.println("------ Sum of salary ------");
                ResultSet rs1 = stmt.executeQuery("SELECT gender, SUM(salary) AS total_salary FROM employee_payroll WHERE gender = 'M' OR gender = 'F' GROUP BY gender");
                while (rs1.next()) {
                    System.out.println("Gender: " + rs1.getString("gender"));
                    System.out.println("Total Salary: " + rs1.getDouble("total_salary"));
                }
                System.out.println();


                System.out.println("------ Avg of salary ------");
                ResultSet rs2 = stmt.executeQuery("SELECT gender, AVG(salary) AS avg_salary FROM employee_payroll WHERE gender = 'M' OR gender = 'F' GROUP BY gender");
                while (rs2.next()) {
                    System.out.println("Gender: " + rs2.getString("gender"));
                    System.out.println("Avg Salary: " + rs2.getDouble("avg_salary"));
                }
                System.out.println();

                System.out.println("------ Min of salary ------");
                ResultSet rs3 = stmt.executeQuery("SELECT gender, MIN(salary) AS min_salary FROM employee_payroll WHERE gender = 'M' OR gender = 'F' GROUP BY gender");
                while (rs3.next()) {
                    System.out.println("Gender: " + rs3.getString("gender"));
                    System.out.println("Min Salary: " + rs3.getDouble("min_salary"));
                }
                System.out.println();

                System.out.println("------ Max of salary ------");
                ResultSet rs4 = stmt.executeQuery("SELECT gender, MAX(salary) AS max_salary FROM employee_payroll WHERE gender = 'M' OR gender = 'F' GROUP BY gender");
                while (rs4.next()) {
                    System.out.println("Gender: " + rs4.getString("gender"));
                    System.out.println("Max Salary: " + rs4.getDouble("max_salary"));
                }
                System.out.println();

                System.out.println("------ Count of employees ------");
                ResultSet rs5 = stmt.executeQuery("SELECT gender, COUNT(*) AS employee_count FROM employee_payroll WHERE gender = 'M' OR gender = 'F' GROUP BY gender");
                while (rs5.next()) {
                    System.out.println("Gender: " + rs5.getString("gender"));
                    System.out.println("Employee Count: " + rs5.getInt("employee_count"));
                }
                System.out.println();

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
