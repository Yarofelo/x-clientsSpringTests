package com.example.xclientsSpringTests;

import com.example.xclientsSpringTests.model.Employee;
import com.example.xclientsSpringTests.api.XClientsServiceImpl;
import com.example.xclientsSpringTests.model.Company;
import com.example.xclientsSpringTests.db.XClientsRepoImplJDBC;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class XClientsSpringTestsApplicationTests {
    @Autowired
    private XClientsServiceImpl xClient;
    @Autowired
    private XClientsRepoImplJDBC xClientDB;

    @Test
    @Owner("Andropov")
    @DisplayName("Проверка №1")
    @Description("Проверить, что список компаний фильтруется по параметру [active]")
    public void CheckListCompaniesFilterByActive() throws IOException, SQLException {
        List<Company> companyList = xClient.getAllCompanyList();
        List<Company> companyListFromDB = xClientDB.getAllCompanyList();
        assertEquals(companyListFromDB.size(), companyList.size());
        List<Company> activeCopmanyList = xClient.getActiveCopmanyList();
        List<Company> activeCompanyListFromDB = xClientDB.getActiveCompanyList();
        assertEquals(activeCompanyListFromDB.size(), activeCopmanyList.size());
    }

    @Test
    @Owner("Andropov")
    @DisplayName("Проверка №2")
    @Description("Проверить создание сотрудника в несуществующей компании")
    public void CheckCreationEmployeeInNonExistentCompany() throws SQLException, IOException {
        int id = xClientDB.addCompany();
        xClientDB.deleteCompany(id);
        List<Employee> employeeListBefore = xClientDB.getAllEmployees();
        int employeeId = xClient.addEmployee(id);
        List<Employee> employeeListAfter = xClientDB.getAllEmployees();
        assertEquals(employeeListBefore.size(), employeeListAfter.size());
    }

    @Test
    @Owner("Andropov")
    @DisplayName("Проверка №3")
    @Description("Проверить, что неактивный сотрудник не отображается в списке")
    public void CheckInactiveEmployeeNotDisplayed() throws SQLException, IOException {
        int newCompanyId = xClientDB.addCompany();
        int employeeId = xClientDB.addEmployee(newCompanyId);
        xClientDB.changeEmployeeIsActiveInfo(employeeId);
        List<Employee> employeeByCompanyId = xClient.getEmployeeByCompanyId(newCompanyId);
        assertEquals(0, employeeByCompanyId.size(), "Неактивный сотрудник отображается в списке");
    }

    @Test
    @Owner("Andropov")
    @DisplayName("Проверка №4")
    @Description("Проверить, что у удаленной компании проставляется в БД поле [deletedAt]")
    public void CheckDeletedCompanyInBD() throws SQLException, IOException {
        int newCompanyId = xClientDB.addCompany();
        xClient.deleteCompany(newCompanyId);
        Company companyById = xClientDB.getCompanyById(newCompanyId);
        assertTrue(companyById.getDeletedAt() != null);
    }
}