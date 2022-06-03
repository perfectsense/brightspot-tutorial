package com.brightspot.tutorial.graphql.demo13.rte.heading;

import com.brightspot.tutorial.graphql.demo13.mark.MarkedTextFactory;
import com.brightspot.tutorial.graphql.demo13.mark.MarkedTextViewModel;
import com.brightspot.tutorial.graphql.demo13.mark.RichTextElementView;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;
import com.psddev.dari.util.ObjectUtils;

@ViewInterface
public class HeadingRichTextElementViewModel extends ViewModel<HeadingRichTextElement> implements RichTextElementView {

    @Override
    protected boolean shouldCreate() {
        return !ObjectUtils.isBlank(model.getValue());
    }

    public Integer getLevel() {
        return model.getLevel();
    }

    public MarkedTextViewModel getValue() {
        return createView(MarkedTextViewModel.class,
            MarkedTextFactory.createWithDefaultPreprocessors(model.getValue()));
    }
}
