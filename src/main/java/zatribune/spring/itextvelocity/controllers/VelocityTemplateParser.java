package zatribune.spring.itextvelocity.controllers;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Component;
import zatribune.spring.itextvelocity.db.entities.Report;

import java.io.StringWriter;
import java.util.*;

@Component
public class VelocityTemplateParser {

    public String generateHTML(Map<String,Object>data, Report report) {

        // initialize velocity engine
        VelocityEngine ve = new VelocityEngine();
        ve.init();

        List<Map<Object, Object>> injection = new ArrayList<>();

        // add that list to a VelocityContext
        VelocityContext context = new VelocityContext();

        // first extract lists
        report.getReportLists().parallelStream().forEach(element->{

            //get the list using its name defined on DB
            List<?>list=((List<?>)data.get(element.getName()));

            list.forEach((listItem)->{//for each map/entry on the list
                Map<?,?>entry= (Map<?,?>) listItem;
                Map<Object, Object> modelMap = new HashMap<>();//create a model map for each
                entry.forEach(modelMap::put);//put each key & value
                injection.add(modelMap);
            });
            context.put(element.getName(), injection);
        });
        // get the Template
        Template t = ve.getTemplate(report.getTemplate());

        // render the template into a Writer, here a StringWriter
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        return writer.toString();
    }
}
