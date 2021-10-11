package zatribune.spring.itextvelocity.reporting;


import org.apache.velocity.tools.generic.DateTool;

import java.util.Date;

/**
 * This class helps in situations when the template parser encounters
 * a timestamp value, this value could be {@link Long} or {@link String}.
 **/

public class AugmentedDateTool extends DateTool {

    public Date epochToDate(String msSinceEpoch) {

        return new Date(Long.parseLong(msSinceEpoch));
    }

    public Date epochToDate(Long msSinceEpoch) {
        return new Date(msSinceEpoch);
    }


    public Date currentDate() {

        return new Date();
    }
}