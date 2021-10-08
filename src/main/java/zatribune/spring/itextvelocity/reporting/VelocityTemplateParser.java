package zatribune.spring.itextvelocity.reporting;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Component;
import zatribune.spring.itextvelocity.db.entities.Report;
import zatribune.spring.itextvelocity.errors.BadReportEntryException;

import java.io.StringWriter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Loads/Parses a velocity template file then, injects provided
 * data from the user.
 * This data needs to be a representation of a {@link Map} with
 * {@link String} as the key type and {@link Object} as the value type.
 **/

@Component
public class VelocityTemplateParser {

    public String generateHTML(Map<String, Object> data, Report report) {

        // initialize velocity engine
        VelocityEngine ve = new VelocityEngine();
        ve.init();

        // create a new VelocityContext
        VelocityContext context = new VelocityContext();

        // first extract lists
        report.getReportLists().parallelStream().forEach(reportList -> {
            // initialize a list for each report list -->to be injected to the VelocityContext
            List<Map<Object, Object>> injectedList = new ArrayList<>();
            //get the list using its name defined on DB
            List<?> list = Optional.ofNullable((List<?>) data.get(reportList.getName()))
                    .orElseThrow(() -> new BadReportEntryException(reportList.getName(), report));

            list.forEach((listItem) -> {//for each map/entry on the list
                Map<?, ?> entry = (Map<?, ?>) listItem;
                Map<Object, Object> modelMap = new HashMap<>();//create a model map for each
                entry.forEach(modelMap::put);//put each key & value
                injectedList.add(modelMap);
            });
            //finally,add the list
            context.put(reportList.getName(), injectedList);
        });

        // then extract first level fields
        report.getReportFields().parallelStream()
                .forEach(reportField -> context.put(reportField.getName(),
                        Optional.ofNullable(data.get(reportField.getName()))
                                .orElseThrow(() -> new BadReportEntryException(reportField.getName(), report))
                ));

        //for the title
        context.put("title", report.getName());

        //case the template has dates
        context.put("dateTool", new AugmentedDateTool());

        // get the Template
        Template t = ve.getTemplate(report.getTemplate());

        // render the template into a Writer, here a StringWriter
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        return writer.toString();
    }
}
