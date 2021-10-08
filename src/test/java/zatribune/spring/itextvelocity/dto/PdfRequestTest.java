package zatribune.spring.itextvelocity.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.*;

class PdfRequestTest {


    private final ObjectMapper mapper = new ObjectMapper();


    @Test
    void testDate() {
        String out = new SimpleDateFormat("yyyyMMddhhmmss'.pdf'").format(new Date());

        System.out.println(out);
    }

    @Test
    void TestJson() throws JsonProcessingException {

        // to fix: No serializer found for class zatribune.spring.itextvelocity.dto.Player
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        PdfRequest request = new PdfRequest();

        request.setReportName("Report One");

        Map<String, Object> data = new HashMap<>();


        Player p1 = new Player(1L, "Muhammad Ali");
        Player p2 = new Player(2L, "Rana Adel");
        List<Object> list1 = List.of(p1, p2);

        Car car1 = new Car(1L, "KIA", new Date());
        Car car2 = new Car(2L, "Cherry", new Date());
        Car car3 = new Car(3L, "Toyota", new Date());
        List<Object> list2 = List.of(car1, car2, car3);

        Long someLong = 20L;

        data.put("playersList", list1);
        data.put("carsList", list2);
        data.put("requestId", someLong);

        data.put("title", "Great Report");
        data.put("reason", "whatever");


        request.setData(data);

        //Field field = DataObject.class.getField("foo");

        String json = mapper.writeValueAsString(request);

        System.out.println(json);
    }

}