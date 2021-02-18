package manager_app.local;

import lombok.RequiredArgsConstructor;
import manager_app.IGradeProcessor;
import manager_app.parse_resolver.IParseModeResolver;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import parser_app.GradeSheetType;

@Component
@RequiredArgsConstructor
@Profile("local")
public class LocalGradeProcessor implements IGradeProcessor, InitializingBean {
    private final IParseModeResolver parseResolver;

    @Value("${default.input.directory}")
    private final String inputPath;

    @Value("${default.output.directory}")
    private final String outputPath;

    @Value("${grade-sheet.mode}")
    private final String sheetMode;

    @Override
    public String processGrades(String source, GradeSheetType mode) {
        String res = parseResolver.parseGradeSheet(source, mode);
        System.out.println("Store result locally to " + outputPath);
        return res;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(processGrades(inputPath, GradeSheetType.valueOf(sheetMode)));
    }
}
