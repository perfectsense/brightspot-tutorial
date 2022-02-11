package com.brightspot.tutorial.graphql.demo13.rte.quote;

import com.psddev.cms.mark.RichTextElementView;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;

@ViewInterface
public class QuoteRichTextElementViewModel extends ViewModel<QuoteRichTextElement> implements RichTextElementView {

    public String getQuote() {
        // Plain text
        return model.getQuote();
    }

    public String getAttribution() {
        // Plain text
        return model.getAttribution();
    }
}
