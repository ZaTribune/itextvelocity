package zatribune.spring.itextvelocity.db.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String template;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "report")
    private Set<ReportField> reportFields=new HashSet<>(1);

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "report")
    private Set<ReportList>reportLists=new HashSet<>(1);

    public Report(String name) {
        this.name = name;
    }

    public void addReportField(ReportField field){
        field.setReport(this);
        reportFields.add(field);
    }

    public void addReportList(ReportList list){
        list.setReport(this);
        reportLists.add(list);
    }

}
