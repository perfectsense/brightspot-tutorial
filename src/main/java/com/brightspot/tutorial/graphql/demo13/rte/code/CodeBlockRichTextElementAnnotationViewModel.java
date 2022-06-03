package com.brightspot.tutorial.graphql.demo13.rte.code;

import com.brightspot.tutorial.graphql.demo13.mark.MarkedTextFactory;
import com.brightspot.tutorial.graphql.demo13.mark.MarkedTextViewModel;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;

@ViewInterface
public class CodeBlockRichTextElementAnnotationViewModel extends ViewModel<CodeBlockRichTextElementAnnotation> {

    public Integer getLineNumber() {
        return model.getLineNumber();
    }

    public MarkedTextViewModel getAnnotation() {
        return createView(MarkedTextViewModel.class,
            MarkedTextFactory.createWithDefaultPreprocessors(model.getAnnotation()));
    }
}
