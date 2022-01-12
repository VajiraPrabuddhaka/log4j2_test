package logging.log4j.custom.plugins;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

@Plugin(name = "ErrorCodeConverter", category = "Converter")
@ConverterKeys({ "error_code" })
public class ErrorCodeConverter extends LogEventPatternConverter {
    protected ErrorCodeConverter(String name, String style) {
        super(name, style);
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {

    }
}
