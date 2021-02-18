package parser_app;

import org.springframework.stereotype.Component;

import java.io.File;

//@Component
public class DOMParser implements IParser {
    @Override
    public String parse(File pdfGradesSheet, GradeSheetType mode) {
        return pdfGradesSheet.getName() + " parsed with DOM, mode=" + mode;
    }
}
