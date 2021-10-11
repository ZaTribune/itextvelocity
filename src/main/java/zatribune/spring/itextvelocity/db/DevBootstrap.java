package zatribune.spring.itextvelocity.db;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import zatribune.spring.itextvelocity.db.entities.Report;
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

    @Override
    public void run(String... args) {

        ReportList rl=new ReportList("petList");
        rl.addListField("name");
        rl.addListField("price");

        Report r=new Report("Pets report");
        r.addReportList(rl);

        r.setTemplate("src/main/resources/reports/pet_template.vm");

        repository.save(r);


        Report report1=new Report("Employees Report");
        report1.addReportField("note");
        report1.addReportField("reason");
        report1.addReportField("issued_by");

        ReportList reportList1=new ReportList();
        reportList1.setName("employeesList");
        reportList1.addListField("name");
        reportList1.addListField("salary");
        reportList1.addListField("joining_date");
        report1.addReportList(reportList1);

        ReportList reportList2=new ReportList();
        reportList2.setName("descriptionList");
        reportList2.addListField("description");
        report1.addReportList(reportList2);

        report1.setTemplate("src/main/resources/reports/employees_template.vm");

        repository.save(report1);


        Report report3=new Report("Auto Debit Recharge Information");

        ReportList reportList3=new ReportList("dataList");
        reportList3.addListField("month");
        reportList3.addListField("invoice_date");
        reportList3.addListField("invoice_number");
        reportList3.addListField("amount");
        reportList3.addListField("num_category");
        reportList3.addListField("call_plan");
        reportList3.addListField("payment_date");
        reportList3.addListField("upload_date");
        reportList3.addListField("status");

        report3.addReportList(reportList3);

        report3.addReportField("invoice_data");
        report3.addReportField("customer_name");
        report3.addReportField("invoice_number");
        report3.addReportField("address");
        report3.addReportField("amount");
        report3.addReportField("mobile_number");

        report3.setTemplate("src/main/resources/reports/invoice_template.vm");
        repository.save(report3);
    }
}
