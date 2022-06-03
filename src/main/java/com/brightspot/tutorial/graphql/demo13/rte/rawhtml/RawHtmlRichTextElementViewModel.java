package com.brightspot.tutorial.graphql.demo13.rte.rawhtml;

import com.brightspot.tutorial.graphql.demo13.mark.RichTextElementView;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;

@ViewInterface
public class RawHtmlRichTextElementViewModel extends ViewModel<RawHtmlRichTextElement> implements RichTextElementView {

    public String getSnippetName() {
        return model.getInternalName();
    }

    public String getRawHtml() {
        return model.getRawHtml();
    }
}
