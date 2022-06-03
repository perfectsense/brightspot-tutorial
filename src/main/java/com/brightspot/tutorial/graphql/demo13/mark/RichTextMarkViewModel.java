package com.brightspot.tutorial.graphql.demo13.mark;

import java.util.Optional;

import com.psddev.cms.db.RichTextElement;
import com.psddev.cms.db.RichTextElementTagSettings;
import com.psddev.cms.db.ToolUi;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;
import com.psddev.cms.view.ViewResponse;

@ViewInterface
public class RichTextMarkViewModel extends ViewModel<RichTextMark> implements MarkView {

    private Mark mark;

    @Override
    protected void onCreate(ViewResponse response) {
        super.onCreate(response);
        mark = model.getMark();
    }

    @Override
    public Integer getStart() {
        return mark.getStart();
    }

    @Override
    public Integer getEnd() {
        return mark.getEnd();
    }

    @Override
    public Boolean getSelfClosing() {
        return mark.isSelfClosing();
    }

    @Override
    public Boolean getIsBlock() {
        return Optional.of(mark)
            .map(Mark::getName)
            .map(n -> RichTextElement.getConcreteTagTypes().get(n))
            .map(t -> t.as(ToolUi.class).getRichTextElementTagSettings())
            .map(RichTextElementTagSettings::isBlock)
            .orElse(false);
    }

    public RichTextElementView getRichTextElement() {
        return createView(RichTextElementView.class, model.getRichTextElement());
    }

    @Override
    public MarkedTextViewModel getBody() {
        return createView(MarkedTextViewModel.class, mark.toMarkedText());
    }
}
