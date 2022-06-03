package com.brightspot.tutorial.graphql.demo13.rte.delimiter;

import com.brightspot.tutorial.graphql.demo13.mark.RichTextElementView;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;

@ViewInterface
public class DelimiterRichTextElementViewModel extends ViewModel<DelimiterRichTextElement> implements
    RichTextElementView {

    public String getStyle() {
        return model.getStyle();
    }
}
