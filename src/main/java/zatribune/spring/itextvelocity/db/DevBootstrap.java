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


        Report report1=new Report("Employees Report");
        report1.addReportField(new ReportField("note"));
        report1.addReportField(new ReportField("reason"));
        report1.addReportField(new ReportField("issued_by"));

        ReportList reportList1=new ReportList();
        reportList1.setName("employeesList");
        reportList1.addListField(new ReportListField("name"));
        reportList1.addListField(new ReportListField("salary"));
        reportList1.addListField(new ReportListField("joining_date"));
        report1.addReportList(reportList1);

        ReportList reportList2=new ReportList();
        reportList2.setName("descriptionList");
        reportList2.addListField(new ReportListField("description"));
        report1.addReportList(reportList2);

        report1.setTemplate("src/main/resources/reports/employees_template.vm");

        repository.save(report1);
    }
}
