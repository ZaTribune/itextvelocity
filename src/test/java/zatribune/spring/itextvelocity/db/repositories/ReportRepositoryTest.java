package zatribune.spring.itextvelocity.db.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import zatribune.spring.itextvelocity.db.entities.Report;
import zatribune.spring.itextvelocity.db.entities.ReportField;
import zatribune.spring.itextvelocity.db.entities.ReportList;
import zatribune.spring.itextvelocity.db.entities.ReportListField;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReportRepositoryTest {

    @Autowired
    private ReportRepository repository;


    @BeforeEach
    private void setup(){

        ReportListField field1=new ReportListField();

        field1.setName("Name");

        ReportListField field2=new ReportListField();

        field2.setName("salary");


        ReportList reportList1=new ReportList();
        reportList1.setName("employeesList");
        reportList1.addListField(field1);
        reportList1.addListField(field2);


        ReportField reportField1=new ReportField();
        reportField1.setName("Title");

        ReportField reportField2=new ReportField();
        reportField2.setName("Reason");

        Report report1=new Report();
        report1.setName("employees report");
        report1.addReportField(reportField1);
        report1.addReportField(reportField2);

        report1.addReportList(reportList1);

        repository.save(report1);
    }

    @Test
    void findById() {
        Report report=repository.findById(1L).orElseThrow();


        assertEquals(report.getReportFields().size(),2);
        assertEquals(report.getReportLists().size(),1);
    }
}