/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeedatabasewithdbclass;

import java.sql.SQLException;
import java.util.ArrayList;
import utilities.DatabaseDriver;
import utilities.DatabaseHandler;

/**
 *
 * @author user
 */
public class EmployeeDatabaseWithDBClass {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String driver = "org.apache.derby.jdbc.ClientDriver";
        String url = "jdbc:derby://localhost:1527/employee";
        String user = "app";
        String passwd = "app";
        /*String driver = "com.mysql.cj.jdbc.Driver";
        //String url="jdbc:mysql://localhost:3306/employee?serverTimezone=UTC";
        String user = "root";
        String passwd = "root";*/
        DatabaseDriver dbDriver = new DatabaseDriver(driver, url, user, passwd);
        DatabaseHandler dbHandler = new DatabaseHandler(dbDriver);
        Employee emp1 = new Employee(1, "John", 12345);
        Employee emp2 = new Employee(2, "Marry", 45678);
        EmployeeTable.insertEmployee(dbHandler, emp1);
        EmployeeTable.insertEmployee(dbHandler, emp2);
        //Employee emp = EmployeeTable.findEmployeeById(dbHandler, 1);
        //emp.setName("Jack");
        //emp.setSalary(98765);
        //EmployeeTable.updateEmployee(dbHandler, emp);
        //EmployeeTable.removeEmployee(dbHandler, emp);
        //ArrayList<Employee> employeeList = EmployeeTable.findEmployeeByName(dbHandler, "Marry");
        ArrayList<Employee> employeeList = EmployeeTable.findAllEmployee(dbHandler);
        if (employeeList != null) {
            printAllEmployee(employeeList);
        }
        dbHandler.closeConnection();
    }
    
    public static void printAllEmployee(ArrayList<Employee> empList) {
        for(int i = 0; i < empList.size(); i++) {
            System.out.print(empList.get(i).getId() + " ");
            System.out.print(empList.get(i).getName() + " ");
            System.out.println(empList.get(i).getSalary() + " ");
        }
        
    }
}
