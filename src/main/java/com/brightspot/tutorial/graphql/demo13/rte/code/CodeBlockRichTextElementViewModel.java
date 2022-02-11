package com.brightspot.tutorial.graphql.demo13.rte.code;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.psddev.cms.mark.RichTextElementView;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;

@ViewInterface
public class CodeBlockRichTextElementViewModel extends ViewModel<CodeBlockRichTextElement> implements
    RichTextElementView {

    public String getTitle() {
        return model.getTitle();
    }

    public List<String> getLines() {
        String code = model.getCode();
        return code != null ? Arrays.stream(code.split("\\r?\\n")).collect(Collectors.toList()) : Collections.emptyList();
    }

    public Iterable<CodeBlockRichTextElementAnnotationViewModel> getAnnotations() {
        return createViews(CodeBlockRichTextElementAnnotationViewModel.class, model.getAnnotations());
    }
}
