package manager_app.shared;

import lombok.RequiredArgsConstructor;
import manager_app.IGradeProcessor;
import manager_app.parse_resolver.IParseModeResolver;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import parser_app.GradeSheetType;

@Service
@Profile("shared")
@RequiredArgsConstructor
public class SharedGradeProcessor implements IGradeProcessor {
    private final IParseModeResolver parseResolver;

    @Override
    public String processGrades(String source, GradeSheetType mode) {
        return parseResolver.parseGradeSheet(source, mode);
    }
}
