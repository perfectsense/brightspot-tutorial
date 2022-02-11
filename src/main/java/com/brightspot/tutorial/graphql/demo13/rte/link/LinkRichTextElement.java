package com.brightspot.tutorial.graphql.demo13.rte.link;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.brightspot.tutorial.graphql.demo13.link.Attribute;
import com.brightspot.tutorial.graphql.demo13.link.ExternalLink;
import com.brightspot.tutorial.graphql.demo13.link.InternalLink;
import com.brightspot.tutorial.graphql.demo13.link.Link;
import com.brightspot.tutorial.graphql.demo13.link.Linkable;
import com.brightspot.tutorial.graphql.demo13.link.Target;
import com.psddev.cms.db.RichTextElement;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Query;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.util.CompactMap;
import com.psddev.dari.util.ObjectUtils;
import org.apache.commons.text.StringEscapeUtils;

@Recordable.DisplayName("Link")
@RichTextElement.Tag(value = "a",
    tooltip = "Link",
    keymaps = { "Mod-k" },
    position = -70.0)
@ToolUi.IconName("link")
public class LinkRichTextElement extends RichTextElement {

    private static final String HREF_ATTRIBUTE = "href";
    private static final String DATA_CMS_ID_ATTRIBUTE = "data-cms-id";
    private static final String DATA_CMS_HREF_ATTRIBUTE = "data-cms-href";
    private static final String TARGET_ATTRIBUTE = "target";
    private static final String LINK_DATA_ATTRIBUTE = "link-data";

    @Required
    private String linkText;

    @Required
    private Link link = Link.createDefault();

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    @Override
    public void fromBody(String body) {
        setLinkText(StringEscapeUtils.unescapeHtml4(body));
    }

    @Override
    public String toBody() {
        return getLinkText();
    }

    @Override
    public void fromAttributes(Map<String, String> attributes) {
        if (attributes != null) {
            String href = attributes.get(HREF_ATTRIBUTE);
            String target = attributes.get(TARGET_ATTRIBUTE);
            String cmsId = attributes.get(DATA_CMS_ID_ATTRIBUTE);
            String cmsHref = attributes.get(DATA_CMS_HREF_ATTRIBUTE);
            String linkData = attributes.get(LINK_DATA_ATTRIBUTE);

            if (!org.apache.commons.lang3.StringUtils.isBlank(linkData)) {
                @SuppressWarnings("unchecked")
                Map<String, Object> simpleValues = (Map<String, Object>) ObjectUtils.fromJson(linkData);
                getState().setValues(simpleValues);
            }

            if (link == null || link.getLinkUrl(null) == null) {
                if (href != null && (cmsId == null && cmsHref == null)) {
                    link = new ExternalLink();
                    ((ExternalLink) link).setUrl(href);
                } else if (cmsId != null && cmsHref != null) {
                    Linkable linkedObject = Query.from(Linkable.class)
                        .where("_id = ?", cmsId)
                        .first();

                    if (linkedObject != null) {
                        link = new InternalLink();
                        ((InternalLink) link).setItem(linkedObject);
                    } else {
                        link = null;
                    }
                }

                if (link != null) {
                    if (target != null) {
                        link.setTarget(Target.NEW);
                    }
                    List<Attribute> createdAttributes = attributes.keySet()
                        .stream()
                        .filter(key -> !key.equals(HREF_ATTRIBUTE) && !key.equals(TARGET_ATTRIBUTE)
                            && !key.equals(DATA_CMS_ID_ATTRIBUTE) && !key.equals(DATA_CMS_HREF_ATTRIBUTE)
                            && !key.equals(LINK_DATA_ATTRIBUTE))
                        .map(key -> {
                            Attribute attribute = new Attribute();
                            attribute.setName(key);
                            attribute.setValue(attributes.get(key));

                            return attribute;
                        })
                        .collect(Collectors.toList());

                    link.setAttributes(createdAttributes);
                }
            }
        }
    }

    @Override
    public Map<String, String> toAttributes() {
        Map<String, String> htmlAttributes = new CompactMap<>();

        if (link != null) {
            htmlAttributes.put(HREF_ATTRIBUTE, link.getLinkUrl(null));
            Optional.ofNullable(link.getTarget())
                .ifPresent(target -> htmlAttributes.put(TARGET_ATTRIBUTE, target.getValue()));
            Optional.ofNullable(link.getAttributes()).ifPresent(attributes -> attributes.stream()
                .filter(attribute -> {
                    String key = attribute.getName();
                    return !key.equals(HREF_ATTRIBUTE) && !key.equals(TARGET_ATTRIBUTE)
                        && !key.equals(DATA_CMS_ID_ATTRIBUTE) && !key.equals(DATA_CMS_HREF_ATTRIBUTE)
                        && !key.equals(LINK_DATA_ATTRIBUTE);
                })
                .forEach(attribute -> htmlAttributes.put(attribute.getName(), attribute.getValue())));

            if (link instanceof InternalLink) {
                InternalLink internalLink = (InternalLink) link;
                Optional.ofNullable(internalLink.getItem()).ifPresent(item -> {
                    htmlAttributes.put(DATA_CMS_ID_ATTRIBUTE, item.getState().getId().toString());
                    htmlAttributes.put(DATA_CMS_HREF_ATTRIBUTE, item.getLinkableUrl(null));
                });
            }
            htmlAttributes.put(LINK_DATA_ATTRIBUTE, ObjectUtils.toJson(getState().getSimpleValues()));
        }

        return htmlAttributes;
    }
}
