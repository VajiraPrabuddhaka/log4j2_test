package logging.log4j.custom.plugins;

import logging.error.format.ErrorLog;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.impl.MutableLogEvent;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.json.simple.JSONObject;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Plugin(name = "CustomJsonLayout", category = Node.CATEGORY, elementType = Layout.ELEMENT_TYPE, printObject = true)

public class CustomJsonLayout  extends AbstractStringLayout {
    protected CustomJsonLayout( Charset charset ){
        super( charset );
    }

    @Override
    public String toSerializable(LogEvent event) {
        StringBuilder throwable = new StringBuilder();
        if (event.getThrown() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            pw.println();
            event.getThrown().printStackTrace(pw);
            pw.close();
            throwable.append(sw.toString());
        }
        JSONObject obj = new JSONObject();
        StringBuilder retValue=new StringBuilder();
        obj.put("timestamp", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:S").format(event.getTimeMillis()));
        obj.put("level", event.getLevel().toString());
        obj.put("logger", event.getLoggerName());
        obj.put("message", event.getMessage().getFormattedMessage());
        MutableLogEvent mutableLogEvent = (MutableLogEvent) event;
        Object[] parameters = mutableLogEvent.getParameters();
        if (event.getLevel() == Level.ERROR){
            if (Arrays.stream(parameters).anyMatch(p -> p.getClass().getName().equals(ErrorLog.class.getName()))){
                Arrays.stream(parameters).filter(p ->
                        p.getClass().getName().equals(ErrorLog.class.getName())).forEach((c) -> {
                            ErrorLog errorLog = (ErrorLog) c;
                            obj.put("severity", errorLog.getSeverity());
                            obj.put("error_code", errorLog.getCode());
                });
            } else {
                obj.put("severity", "normal");
                obj.put("error_code", 0);
            }
        }
        retValue.append(obj.toString()).append(throwable).append("\n");
        return retValue.toString();
    }

    @PluginFactory
    public static CustomJsonLayout createLayout(@PluginAttribute(value = "charset", defaultString = "UTF-8") Charset charset) {
        return new CustomJsonLayout(charset);
    }
}