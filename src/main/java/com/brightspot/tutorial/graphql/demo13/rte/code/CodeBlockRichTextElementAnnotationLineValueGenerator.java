package com.brightspot.tutorial.graphql.demo13.rte.code;

import java.util.ArrayList;
import java.util.List;

import com.psddev.cms.db.ValueGenerator;
import com.psddev.cms.tool.ToolPageContext;
import com.psddev.dari.html.Nodes;
import org.apache.commons.lang3.StringUtils;

public class CodeBlockRichTextElementAnnotationLineValueGenerator implements ValueGenerator {

    @Override
    public List<Value> generate(ToolPageContext page, Object object, String input) {

        List<Value> values = new ArrayList<>();

        if (object instanceof CodeBlockRichTextElementAnnotation) {

            CodeBlockRichTextElementAnnotation annotation = (CodeBlockRichTextElementAnnotation) object;

            CodeBlockRichTextElement codeBlockRte = annotation.getParent();

            if (codeBlockRte != null) {
                String code = codeBlockRte.getCode();

                int lineNumber = 1;

                String[] lines = code.split("\\r?\\n");

                int maxLineNumberPadding = String.valueOf(lines.length).length();

                for (String line : lines) {
                    int lineNumberLength = String.valueOf(lineNumber).length();
                    String lineNumberPadding = StringUtils.repeat(" ", maxLineNumberPadding - lineNumberLength + 1);

                    values.add(Value.withLabelHtml(
                        String.valueOf(lineNumber),
                        Nodes.PRE
                            .style("font-family:monospace;white-space:pre;")
                            .with("Line " + lineNumber + ":" + lineNumberPadding + line)
                            .toString()));

                    lineNumber++;
                }
            }
        }

        return values;
    }
}
