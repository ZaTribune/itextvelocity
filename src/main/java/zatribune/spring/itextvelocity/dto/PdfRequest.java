package zatribune.spring.itextvelocity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
public class PdfRequest {

    private String reportName;
    private Map<String,Object> data;

    @Override
    public String toString() {
        return "PdfRequest{" +
                "reportName=" + reportName +
                ", data=" + data +
                '}';
    }
}
