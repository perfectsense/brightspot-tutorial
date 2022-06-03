package com.brightspot.tutorial.graphql.demo13.mark;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.psddev.cms.db.RichTextElement;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;
import com.psddev.cms.view.ViewResponse;

@ViewInterface
public class MarkedTextViewModel extends ViewModel<MarkedText> {

    private MarkedText markedText;

    @Override
    protected void onCreate(ViewResponse response) {
        super.onCreate(response);
        markedText = model;
    }

    public String getText() {
        return markedText.getText();
    }

    public Iterable<MarkView> getMarks() {
        return getMarkViews(markedText.getMarks());
    }

    public Iterable<MarkView> getNestedMarks() {

        List<Mark> topLevelMarks = new ArrayList<>();

        Mark prevMark = null;

        for (Mark mark : markedText.getMarks()) {
            if (prevMark == null || mark.getStart() >= prevMark.getEnd()) {
                prevMark = mark;
                topLevelMarks.add(mark);
            }
        }

        return getMarkViews(topLevelMarks);
    }

    private Iterable<MarkView> getMarkViews(List<Mark> marks) {
        List<?> markObjects = marks.stream()
            .map(m -> {

                RichTextElement richTextElement = Optional.ofNullable(m)
                    .map(Mark::getName)
                    .map(n -> RichTextElement.getConcreteTagTypes().get(n))
                    .map(t -> t.createObject(null))
                    .filter(RichTextElement.class::isInstance)
                    .map(RichTextElement.class::cast)
                    .map(rte -> {
                        rte.fromAttributes(m.getAttributes());
                        rte.fromBody(m.toMarkedText().getText());
                        return rte;
                    })
                    .orElse(null);

                return richTextElement != null
                    ? new RichTextMark(m, richTextElement)
                    : m;
            })
            .collect(Collectors.toList());

        return createViews(MarkView.class, markObjects);
    }
}
