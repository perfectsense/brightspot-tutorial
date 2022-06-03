package com.brightspot.tutorial.graphql.demo13.mark;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import com.psddev.cms.rte.EditorialMarkupRichTextPreprocessor;
import com.psddev.cms.rte.LineBreakRichTextPreprocessor;
import com.psddev.cms.rte.RichTextPreprocessor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MarkedTextFactory {

    public static MarkedText createWithDefaultPreprocessors(String html) {
        return create(html,
            new EditorialMarkupRichTextPreprocessor(),
            new LineBreakRichTextPreprocessor());
    }

    public static MarkedText create(String html, RichTextPreprocessor... preprocessors) {
        return create(html, Optional.ofNullable(preprocessors)
            .map(Arrays::stream)
            .map(s -> s.collect(Collectors.toList()))
            .orElseGet(Collections::emptyList));
    }

    public static MarkedText create(String html, Iterable<RichTextPreprocessor> preprocessors) {
        Document document = Jsoup.parseBodyFragment(html);
        document.outputSettings().prettyPrint(false);

        if (preprocessors != null) {
            for (RichTextPreprocessor preprocessor : preprocessors) {
                preprocessor.preprocess(document.body());
            }
        }

        return new MarkedText(document.body().html());
    }
}
