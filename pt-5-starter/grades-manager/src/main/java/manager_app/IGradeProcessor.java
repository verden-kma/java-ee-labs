package manager_app;

import parser_app.GradeSheetType;

public interface IGradeProcessor {
    String processGrades(String source, GradeSheetType mode);
}
