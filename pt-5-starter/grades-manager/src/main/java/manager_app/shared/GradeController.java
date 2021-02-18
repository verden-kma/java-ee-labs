package manager_app.shared;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import parser_app.GradeSheetType;

@Profile("shared")
@RestController
public class GradeController {
    private final SharedGradeProcessor gradeProcessor;

    public GradeController(SharedGradeProcessor gradeProcessor) {
        this.gradeProcessor = gradeProcessor;
    }

    @PostMapping("/process-grades-spreadsheet")
    public String manageSpreadsheet(@RequestBody String data, @RequestParam String mode) {
        System.out.println("shared handle requested");
        return gradeProcessor.processGrades(data, GradeSheetType.valueOf(mode));
    }
}
