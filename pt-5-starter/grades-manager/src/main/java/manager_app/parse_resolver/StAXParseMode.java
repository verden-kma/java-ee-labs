package manager_app.parse_resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import parser_app.GradeSheetType;
import parser_app.StAXParser;

import java.io.File;

@ConditionalOnBean(StAXParser.class)
@RequiredArgsConstructor
@Component
public class StAXParseMode implements IParseModeResolver, InitializingBean {
    private final StAXParser parser;

    @Override
    public String parseGradeSheet(String content, GradeSheetType type) {
        return parser.parse(new File(content), type);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("StAX parser will be used.");
    }
}
