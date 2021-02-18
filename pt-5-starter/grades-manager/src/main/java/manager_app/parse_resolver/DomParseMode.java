package manager_app.parse_resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;
import parser_app.DOMParser;
import parser_app.GradeSheetType;
import parser_app.StAXParser;

import java.io.File;

@ConditionalOnMissingBean(StAXParser.class)
@RequiredArgsConstructor
@Component
public class DomParseMode implements IParseModeResolver, InitializingBean {
    private final DOMParser parser;

    @Override
    public String parseGradeSheet(String content, GradeSheetType type) {
        return parser.parse(new File(content), type);
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("Dom parser will be used.");
    }
}
