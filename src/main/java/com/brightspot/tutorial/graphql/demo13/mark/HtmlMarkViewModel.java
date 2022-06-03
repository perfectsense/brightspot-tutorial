package com.brightspot.tutorial.graphql.demo13.mark;

import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;
import com.psddev.dari.mark.Mark;
import org.jsoup.parser.Tag;

@ViewInterface
public class HtmlMarkViewModel extends ViewModel<Mark> implements MarkView {

    @Override
    public Integer getStart() {
        return model.getStart();
    }

    @Override
    public Integer getEnd() {
        return model.getEnd();
    }

    @Override
    public Boolean getSelfClosing() {
        return model.isSelfClosing();
    }

    @Override
    public Boolean getIsBlock() {
        return Tag.valueOf(model.getName()).isBlock();
    }

    public String getName() {
        return model.getName();
    }

    public MapDataViewModel getAttributes() {
        return createView(MapDataViewModel.class, model.getAttributes());
    }

    @Override
    public MarkedTextViewModel getBody() {
        return createView(MarkedTextViewModel.class, model.toMarkedText());
    }
}
