package com.brightspot.tutorial.graphql.demo13.mark;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import com.psddev.dari.util.HtmlWriter;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

/**
 * This class gives developers the option to retrieve data from an RTE field as a structured object versus a string of
 * HTML. The structure of the MarkedText object allows for infinitely nested tags to be represented in a clean way
 * without prior knowledge of the entire state and without having to parse the plain text elements of the string.
 *
 * MarkedText objects store information about an HTML string and its tags, then returns specific markup data in an
 * organized manner. This includes the text content (without any HTML tags) as it was provided from the original HTML
 * text which is used in the {@link #processMarkedText()} logic, and a list of {@link Mark} for each HTML tag and its
 * corresponding information.
 */
public class MarkedText {

    // 0 position of an HTML tag indicates that it occurs before the char at index 0 in the String (e.g. "<b>example text</b>")
    private static final int FIRST_POSITION = 0;
    private static final String BR_TAG = "br";

    private final String originalHtml;

    private String text;

    private List<Mark> marks;

    MarkedText(String text, List<Mark> marks) {
        this.originalHtml = text;
        this.text = text;
        this.marks = marks;
    }

    public MarkedText(String originalHtml) {
        this.originalHtml = originalHtml;
        this.processMarkedText();
    }

    /**
     * Returns a string of the plain text from the original HTML text without any HTML tags and users can take this
     * string and use it as is.
     *
     * @return String
     */
    public String getText() {
        return text;
    }

    /**
     * Returns a list of {@link Mark}, which represents the information about each HTML tag from the
     * {@code originalHtml} text
     *
     * @return Nonnull.
     */
    public List<Mark> getMarks() {
        if (marks == null) {
            marks = new ArrayList<>();
        }
        return Collections.unmodifiableList(marks);
    }

    /**
     * The fields of a {@link MarkedText} object will be processed through this method. Using the {@code originalHtml},
     * a list of {@link Node} is generated, which contains both plain text ({@link TextNode}) and HTML tags
     * ({@link Element}). The list is then iterated to build the {@code text} string and also pull the necessary
     * information to be able to add {@link Mark} to the list of {@code elements} that a {@link MarkedText} object will
     * contain.
     */
    private void processMarkedText() {

        Element docBody = Jsoup.parseBodyFragment(originalHtml).body();
        text = docBody.text();
        marks = new ArrayList<>();
        List<Node> expandedNodes = new ArrayList<>();
        List<Node> unexpandedNodes = docBody.childNodes();

        // Expand the list of Nodes that jsoup returns to include deeper nested child Nodes to iterate over in later
        // method logic
        for (Node n : unexpandedNodes) {
            expandNodes(n, expandedNodes);
        }

        AtomicInteger position = new AtomicInteger(FIRST_POSITION);
        boolean leadingWhitespace = true;
        boolean previousWhitespace = false;

        for (Node n : expandedNodes) {
            if (n instanceof Element) {

                Element e = (Element) n;
                Mark mark = populateMark(e, position);
                marks.add(mark);
                int start = mark.getStart();
                int end = mark.getEnd();

                if (e.isBlock()) {

                    if (end < text.length() && text.charAt(end) == ' ') {
                        text = text.substring(0, end) + '\n' + text.substring(end + 1);
                    }

                    if (start > 0 && text.charAt(start - 1) == ' ') {
                        text = text.substring(0, start - 1) + '\n' + text.substring(start);
                    }

                } else if (e.tagName().equals(BR_TAG)) {

                    if (start <= text.length()) {

                        String suffix = text.charAt(start - 1) == ' '
                            && !(e.nextSibling() instanceof Element && ((Element) e.nextSibling()).isBlock())
                            ? text.substring(start)
                            : text.substring(start - 1);

                        text = text.substring(0, start - 1) + '\n' + suffix;

                    } else {
                        text = text.substring(0, start - 1) + '\n' + text.substring(start - 1);
                    }
                }
            } else if (n instanceof TextNode) {

                TextNode textNode = (TextNode) n;
                String textNodeText = textNode.text();
                int textNodeTextLength = textNodeText.length();
                boolean blank = textNode.isBlank();

                if (leadingWhitespace && !blank) {
                    leadingWhitespace = false;
                }

                int initialPosition = position.get();
                int initialPositionEnd = initialPosition + textNodeTextLength;
                if (initialPositionEnd < text.length()
                    && !text.substring(initialPosition, initialPositionEnd).equals(textNodeText)
                    && text.substring(initialPosition + 1, initialPositionEnd + 1).equals(textNodeText)) {

                    position.incrementAndGet();
                }

                if (!leadingWhitespace && !(blank && previousWhitespace)) {
                    position.addAndGet(textNodeTextLength);
                }

                previousWhitespace =
                    blank || StringUtils.isBlank(String.valueOf(textNodeText.charAt(textNodeTextLength - 1)));
            }
        }
    }

    private Mark populateMark(Element e, AtomicInteger position) {

        int start = position.get();
        int end = start;
        boolean selfClosing = e.tag().isSelfClosing();

        if (!selfClosing) {

            String elementText = e.text();
            int textLength = elementText.length();
            end = start + textLength;

            if (start > 0 && end < text.length() && !text.substring(start, end)
                .equals(elementText.substring(0, textLength))) {

                if (text.substring(start + 1, end + 1).equals(elementText.substring(0, textLength))) {
                    start = position.incrementAndGet();
                } else if (text.substring(start - 1, end - 1).equals(elementText.substring(0, textLength))) {
                    start = position.decrementAndGet();
                }

                end = start + textLength;
            }

            end += e.getElementsByTag(BR_TAG)
                .stream()
                .filter(br -> !(isEffectivelyInline(br.previousSibling()) && isEffectivelyInline(br.nextSibling())))
                .count();

        } else if (e.tagName().equals(BR_TAG)) {

            start = position.incrementAndGet();
            end = start;
        }

        return new Mark(e.tagName(), getAttributesMap(e.attributes().asList()), start, end, selfClosing, this);
    }

    private boolean isEffectivelyInline(Node n) {
        if (n instanceof TextNode) {
            return true;
        } else if (n instanceof Element) {
            Element e = (Element) n;
            return !(e.tagName().equals(BR_TAG) || e.isBlock());
        }
        return false;
    }

    /**
     * When a {@link MarkedText} objects calls this method, using the {@code text} and {@code elements} a fully
     * formatted string of HTML text is rebuilt and returned to the user.
     *
     * @return String
     */
    public String toHtml() {
        return toHtml(getText(), getMarks());
    }

    private static String toHtml(String text, List<Mark> elements) {
        StringWriter stringWriter = new StringWriter();
        HtmlWriter htmlWriter = new HtmlWriter(stringWriter);

        // In order to save execution time, starting and ending positions (keys) are mapped to Marks (values)
        // at those positions to be repeatedly traversed in later logic
        Map<Integer, List<Mark>> startIndexMap = new LinkedHashMap<>();
        elements.forEach(s -> startIndexMap.computeIfAbsent(
            s.getStart(),
            k -> new ArrayList<>()).add(s));
        Map<Integer, List<Mark>> endIndexMap = new LinkedHashMap<>();
        elements.forEach(s -> endIndexMap.computeIfAbsent(
            s.getEnd(),
            k -> new ArrayList<>()).add(s));

        char[] textChars = text.toCharArray();
        int maxEndPosition = textChars.length;
        StringBuilder textNode = new StringBuilder();
        try {
            // The following loop iterates through maxEndPosition since tags positioned at the very end of the original HTML
            // string would have a start/end index of maxEndPosition, and this way they'll be accounted for
            for (int i = 0; i <= maxEndPosition; i++) {

                // Append any end tags that exist at position i first to ensure that already opened tags are being closed
                if (endIndexMap.containsKey(i)) {
                    // Append the escaped text back to the final HTML string separately from the tags in order to keep the
                    // tags formatting as is (e.g. <span> will not convert to &lt;span&gt;)
                    htmlWriter.writeHtml(textNode.toString());
                    textNode = new StringBuilder();

                    List<Mark> endTags = endIndexMap.get(i);
                    Collections.reverse(endTags);

                    for (Mark endTag : endTags) {

                        if (!endTag.isSelfClosing() && endTag.getStart() != endTag.getEnd()) {
                            htmlWriter.writeEnd();
                        }
                    }
                }

                // Append any start tags that exist at position i
                if (startIndexMap.containsKey(i)) {
                    htmlWriter.writeHtml(textNode.toString());
                    textNode = new StringBuilder();

                    List<Mark> startTags = startIndexMap.get(i);

                    for (Mark startTag : startTags) {

                        if (startTag.isSelfClosing()) {
                            htmlWriter.writeElement(startTag.getName(), startTag.getAttributes());

                        } else {
                            htmlWriter.writeStart(startTag.getName(), startTag.getAttributes());
                        }

                        if (startTag.getEnd() == startTag.getStart() && !startTag.isSelfClosing()) {
                            htmlWriter.writeEnd();
                        }
                    }
                }

                if (i < maxEndPosition) {
                    char c = textChars[i];
                    textNode.append(c);
                }
            }
            htmlWriter.writeHtml(textNode.toString());

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        return stringWriter.toString();
    }

    private static Map<String, String> getAttributesMap(List<Attribute> attributes) {
        LinkedHashMap<String, String> attributeMap = new LinkedHashMap<>();
        for (Attribute a : attributes) {
            attributeMap.put(a.getKey(), a.getValue());
        }

        return attributeMap;
    }

    private static void expandNodes(Node n, List<Node> expandedNodes) {
        expandedNodes.add(n);
        for (Node child : n.childNodes()) {
            expandNodes(child, expandedNodes);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        MarkedText otherMarkedText = (MarkedText) other;
        String otherText = otherMarkedText.getText();
        List<Mark> otherMarks = otherMarkedText.getMarks();

        boolean isEqualPlainText = otherText.equals(getText());
        boolean isEqualElements = otherMarks.equals(getMarks());

        return isEqualPlainText && isEqualElements;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMarks(), getText());
    }
}
