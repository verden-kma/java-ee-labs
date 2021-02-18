package manager_app.parse_resolver;

import parser_app.GradeSheetType;

public interface IParseModeResolver {
    String parseGradeSheet(String content, GradeSheetType type);
}
