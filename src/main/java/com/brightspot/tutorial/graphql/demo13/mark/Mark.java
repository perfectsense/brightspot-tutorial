package com.brightspot.tutorial.graphql.demo13.mark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Mark objects are used to store information about one specific HTML tag. This includes the name of the HTML tag,
 * whether the tag is self closing, the start and end positions of the tag in its original context, and (if applicable)
 * a map of attribute types and values.
 */
public class Mark {

    private final int start;

    private final int end;

    private final boolean selfClosing;

    private final String name;

    private Map<String, String> attributes;

    private final MarkedText parent;

    Mark(
        String name,
        Map<String, String> attributes,
        int start,
        int end,
        boolean selfClosing,
        MarkedText parentMarkedText) {
        this.start = start;
        this.end = end;
        this.name = name;
        this.attributes = attributes;
        this.selfClosing = selfClosing;
        this.parent = parentMarkedText;
    }

    MarkedText getParent() {
        return parent;
    }

    /**
     * Returns an integer representing the index of the character that the opening tag is positioned before in the text
     *
     * @return int
     */
    public int getStart() {
        return start;
    }

    /**
     * Returns an integer representing the index of the character that the closing tag is positioned before in the text
     *
     * @return int
     */
    public int getEnd() {
        return end;
    }

    /**
     * Returns a boolean representing whether the type of tag is self closing
     *
     * @return boolean
     */
    public boolean isSelfClosing() {
        return selfClosing;
    }

    /**
     * Returns an string representing the name of the type of tag
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a map of attributes if the specific tag contains any.
     *
     * @return Nonnull.
     */
    public Map<String, String> getAttributes() {
        if (attributes == null) {
            attributes = new LinkedHashMap<>();
        }
        return Collections.unmodifiableMap(attributes);
    }

    /**
     * Converts the Mark into a {@link MarkedText} object.
     *
     * @return MarkedText
     */
    public MarkedText toMarkedText() {
        String text = parent.getText();
        StringBuilder nestedText = new StringBuilder();

        int index = 0;
        while (index < end && index < text.length()) {
            char c = text.charAt(index);
            if (index >= start) {
                nestedText.append(c);
            }
            index++;
        }

        List<Mark> elements = new ArrayList<>();
        MarkedText markedText = new MarkedText(nestedText.toString().trim(), elements);
        for (Mark e : parent.getMarks()) {

            // First condition checks if Mark e is within the bounds of outer Mark
            // In addition, second condition checks the edge cases, when it's self-closing, or when it's an empty tag
            if ((e.getStart() >= start && e.getEnd() <= end)
                && (((e.getStart() != end && e.getEnd() != start) || selfClosing)
                || (e.getEnd() == e.getStart() && !e.isSelfClosing()))) {

                Mark eModified = new Mark(
                    e.getName(),
                    e.getAttributes(),
                    e.getStart() - start,
                    e.getEnd() - start,
                    e.isSelfClosing(), markedText);
                elements.add(eModified);
            }
        }

        // remove the first element since it represents this Mark and is redundant.
        if (!elements.isEmpty()) {
            elements.remove(0);
        }

        return markedText;
    }

}
