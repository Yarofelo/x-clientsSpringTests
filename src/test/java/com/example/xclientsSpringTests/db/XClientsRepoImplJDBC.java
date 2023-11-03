package com.example.xclientsSpringTests.db;

import com.example.xclientsSpringTests.model.Company;
import com.example.xclientsSpringTests.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class XClientsRepoImplJDBC implements XClientsRepository {
    @Autowired
    private XClientsConnection connection;
    private String company_name = "Сюда иди, да?";
    private String company_desc = "Туда иди, да?";
    private String first_name = "Сюда";
    private String last_name = "Туда";
    private String phone = "1234567890";
    private final static String GET_ALL_COMPANY = "select*from company where \"deleted_at\" is null";
    private final static String GET_ALL_ACTIVE_COMPANY = "select*from company where \"is_active\"='true' and \"deleted_at\" is null";
    private final static String ADD_NEW_COMPANY = "insert into company (name, description) values (?,?)";
    private final static String GET_LAST_ADDED_COMPANY = "select*from company where \"deleted_at\" is null order by \"id\" desc limit 1";
    private final static String DELETE_COMPANY = "delete from company where \"id\"=?";
    private final static String GET_ALL_EMPLOYEES = "select*from employee";
    private final static String ADD_NEW_EMPLOYEE = "insert into employee (company_id, first_name, last_name, phone) values (?,?,?,?)";
    private final static String GET_LAST_ADDED_EMPLOYEE = "select*from employee order by \"id\" desc limit 1";
    private final static String CHANGE_IS_ACTIVE_EMPLOYEE_INFO = "update employee set is_active='false' where \"id\"=?";
    private final static String GET_EMPLOYEE_BY_ID = "select*employee where \"id\"=?";
    private final static String GET_COMPANY_BY_ID = "select*from company where \"id\"=?";


    @Override
    public List<Company> getAllCompanyList() throws SQLException {
        ResultSet resultSet = connection.getConnection().createStatement().executeQuery(GET_ALL_COMPANY);
        List<Company> list = new ArrayList<>();
        while (resultSet.next()) {
            Company company = new Company();
            company.setId(resultSet.getInt("id"));
            company.setIsActive(resultSet.getBoolean("is_active"));
            company.setName(resultSet.getString("name"));
            company.setDescription(resultSet.getString("description"));
            company.setDeletedAt(resultSet.getTimestamp("deleted_at"));
            list.add(company);
        }
        return list;
    }

    @Override
    public List<Company> getActiveCompanyList() throws SQLException {
        ResultSet resultSet = connection.getConnection().createStatement().executeQuery(GET_ALL_ACTIVE_COMPANY);
        List<Company> list = new ArrayList<>();
        while (resultSet.next()) {
            Company company = new Company();
            company.setId(resultSet.getInt("id"));
            company.setIsActive(resultSet.getBoolean("is_active"));
            company.setName(resultSet.getString("name"));
            company.setDescription(resultSet.getString("description"));
            company.setDeletedAt(resultSet.getTimestamp("deleted_at"));
            list.add(company);
        }
        return list;
    }

    @Override
    public int addCompany() throws SQLException {
        PreparedStatement statement = connection.getConnection().prepareStatement(ADD_NEW_COMPANY);
        statement.setString(1, company_name);
        statement.setString(2, company_desc);
        statement.executeUpdate();
        ResultSet resultSet = connection.getConnection().createStatement().executeQuery(GET_LAST_ADDED_COMPANY);
        resultSet.next();
        Company company = new Company();
        company.setId(resultSet.getInt("id"));
        return company.getId();
    }

    @Override
    public void deleteCompany(int id) throws SQLException {
        PreparedStatement statement = connection.getConnection().prepareStatement(DELETE_COMPANY);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    @Override
    public List<Employee> getAllEmployees() throws SQLException {
        ResultSet resultSet = connection.getConnection().createStatement().executeQuery(GET_ALL_EMPLOYEES);
        List<Employee> list = new ArrayList<>();
        while (resultSet.next()) {
            Employee employee = new Employee();
            employee.setId(resultSet.getInt("id"));
            employee.setFirstName(resultSet.getString("first_name"));
            employee.setLastName(resultSet.getString("last_name"));
            employee.setCompanyId(resultSet.getInt("company_id"));
            employee.setPhone(resultSet.getString("phone"));
            list.add(employee);
        }
        return list;
    }

    @Override
    public int addEmployee(int company_id) throws SQLException {
        PreparedStatement statement = connection.getConnection().prepareStatement(ADD_NEW_EMPLOYEE);
        statement.setInt(1, company_id);
        statement.setString(2, first_name);
        statement.setString(3, last_name);
        statement.setString(4, phone);
        statement.executeUpdate();
        ResultSet set = connection.getConnection().createStatement().executeQuery(GET_LAST_ADDED_EMPLOYEE);
        set.next();
        Employee employee = new Employee();
        employee.setId(set.getInt("id"));
        return employee.getId();
    }

    @Override
    public void changeEmployeeIsActiveInfo(int employeeId) throws SQLException {
        PreparedStatement statement = connection.getConnection().prepareStatement(CHANGE_IS_ACTIVE_EMPLOYEE_INFO);
        statement.setInt(1, employeeId);
        statement.executeUpdate();
    }

    @Override
    public Company getCompanyById(int companyId) throws SQLException {
        PreparedStatement statement = connection.getConnection().prepareStatement(GET_COMPANY_BY_ID);
        statement.setInt(1, companyId);
        ResultSet set = statement.executeQuery();
        set.next();
        Company company = new Company();
        company.setDeletedAt(set.getTimestamp("deleted_at"));
        return company;
    }
}