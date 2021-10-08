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
public class ReportList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "reportList")
    private Set<ReportListField> listFields=new HashSet<>(1);

    @ManyToOne
    private Report report;

    public ReportList(String name) {
        this.name = name;
    }

    public void addListField(ReportListField listField){
        listField.setReportList(this);
        listFields.add(listField);
    }



}
