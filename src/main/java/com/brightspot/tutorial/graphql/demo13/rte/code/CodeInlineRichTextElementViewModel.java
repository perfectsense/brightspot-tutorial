package com.brightspot.tutorial.graphql.demo13.rte.code;

import com.brightspot.tutorial.graphql.demo13.mark.RichTextElementView;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;

@ViewInterface
public class CodeInlineRichTextElementViewModel extends ViewModel<CodeInlineRichTextElement> implements
    RichTextElementView {

    public String getTagName() {
        return "code";
    }
}
