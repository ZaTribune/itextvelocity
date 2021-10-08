package zatribune.spring.itextvelocity.db;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import zatribune.spring.itextvelocity.db.entities.Report;
import zatribune.spring.itextvelocity.db.entities.ReportField;
import zatribune.spring.itextvelocity.db.entities.ReportList;
import zatribune.spring.itextvelocity.db.entities.ReportListField;
import zatribune.spring.itextvelocity.db.repositories.ReportRepository;

@Component
public class DevBootstrap implements CommandLineRunner {



    private final ReportRepository repository;


    @Autowired
    public DevBootstrap(ReportRepository repository) {
        this.repository = repository;
    }

    public void initializePetsReport(){

        ReportList reportList1=new ReportList("petList");
        reportList1.addListField(new ReportListField("name"));
        reportList1.addListField(new ReportListField("price"));


        Report report1=new Report("Pets report");
        report1.addReportList(reportList1);

        report1.setTemplate("src/main/resources/reports/pet_template.vm");

        repository.save(report1);

    }

    @Override
    public void run(String... args) {

        initializePetsReport();

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
}
