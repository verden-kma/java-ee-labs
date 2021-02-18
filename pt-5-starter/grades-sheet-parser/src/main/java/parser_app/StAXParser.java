package parser_app;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@ConditionalOnProperty(value = "parse.approach", havingValue = "stax")
public class StAXParser implements IParser{
    @Override
    public String parse(File pdfGradesSheet, GradeSheetType mode) {
        return pdfGradesSheet.getName() + " parsed with StAX, mode=" + mode;
    }
}
