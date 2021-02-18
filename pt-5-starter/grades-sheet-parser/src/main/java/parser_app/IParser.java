package parser_app;

import java.io.File;

public interface IParser {
    String parse(File pdfGradesSheet, GradeSheetType mode);
}
