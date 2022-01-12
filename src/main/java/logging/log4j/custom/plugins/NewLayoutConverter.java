package logging.log4j.custom.plugins;

import logging.error.format.ErrorLog;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.impl.MutableLogEvent;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
import org.apache.logging.log4j.core.layout.JsonLayout;
import org.apache.logging.log4j.util.ReadOnlyStringMap;

import java.util.Arrays;

@Plugin(name = "NewLayoutConverter", category = "Converter")
@ConverterKeys({"custLayConv"})
public class NewLayoutConverter extends LogEventPatternConverter {
    protected NewLayoutConverter(String name, String style) {
        super(name, style);
    }

    public NewLayoutConverter(String[] options) {
        super("CustLayConv", "custLayConv");
    }

    public static NewLayoutConverter newInstance(final String[] options) {
        return new NewLayoutConverter(options);
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {
        ReadOnlyStringMap contextData = event.getContextData();
        MutableLogEvent mevent = (MutableLogEvent) event;
        Object[] parameters = mevent.getParameters();
        if (event.getLevel() == Level.ERROR){
            if (Arrays.stream(parameters).anyMatch(p -> p.getClass().getName().equals(ErrorLog.class.getName()))){
                ErrorLog e = (ErrorLog) Arrays.stream(parameters).filter(
                        p -> p.getClass().getName().equals(ErrorLog.class.getName()));
            } else {
                toAppendTo.append("text2");
            }
        }

    }
}
