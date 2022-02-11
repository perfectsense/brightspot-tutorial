package com.brightspot.tutorial.graphql.demo13.rte.heading;

import com.psddev.cms.mark.MarkedTextViewModel;
import com.psddev.cms.mark.RichTextElementView;
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
        System.out.println("heading value: " + model.getValue());
        return createView(MarkedTextViewModel.class, model.getValue());
    }
}
