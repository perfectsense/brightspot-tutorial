package com.brightspot.tutorial.graphql.demo13.rte.image;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.brightspot.tutorial.Image;
import com.psddev.cms.db.RichTextElement;
import com.psddev.cms.db.ToolUi;
import com.psddev.cms.tool.ToolPageContext;
import com.psddev.dari.db.Query;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.util.ObjectUtils;

@Recordable.DisplayName("Image")
@RichTextElement.Tag(value = ImageRichTextElement.TAG_NAME,
    block = true,
    initialBody = "Image",
    position = -50.0,
    preview = true,
    readOnly = true,
    root = true,
    tooltip = "Add Image",
    menu = "Enhancements"
)
@ToolUi.IconName("photo")
public class ImageRichTextElement extends RichTextElement {

    public static final String TAG_NAME = "bsp-image";

    private static final String IMAGE_STATE_ATTRIBUTE = "data-image-state";

    private static final String IMAGE_ATTRIBUTE = "data-image";
    private static final String WITH_BORDER_ATTRIBUTE = "data-border";
    private static final String STRETCHED_ATTRIBUTE = "data-stretched";
    private static final String WITH_BACKGROUND_ATTRIBUTE = "data-background";

    @Required
    private Image image;

    private Boolean withBorder;

    private Boolean stretched;

    private Boolean withBackground;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isWithBorder() {
        return Boolean.TRUE.equals(withBorder);
    }

    public void setWithBorder(Boolean withBorder) {
        this.withBorder = withBorder;
    }

    public boolean isStretched() {
        return Boolean.TRUE.equals(stretched);
    }

    public void setStretched(Boolean stretched) {
        this.stretched = stretched;
    }

    public boolean isWithBackground() {
        return Boolean.TRUE.equals(withBackground);
    }

    public void setWithBackground(Boolean withBackground) {
        this.withBackground = withBackground;
    }

    @Override
    public void fromAttributes(Map<String, String> attributes) {
        image = Optional.ofNullable(attributes)
            .map(attr -> attr.get(IMAGE_ATTRIBUTE))
            .map(val -> ObjectUtils.to(UUID.class, val))
            .map(id -> Query.fromAll().where("_id = ?", id).first())
            .filter(Image.class::isInstance)
            .map(Image.class::cast)
            .orElse(null);

        withBorder = Optional.ofNullable(attributes)
            .map(attr -> attr.get(WITH_BORDER_ATTRIBUTE))
            .map(Boolean::valueOf)
            .filter(Boolean.TRUE::equals)
            .orElse(null);

        stretched = Optional.ofNullable(attributes)
            .map(attr -> attr.get(STRETCHED_ATTRIBUTE))
            .map(Boolean::valueOf)
            .filter(Boolean.TRUE::equals)
            .orElse(null);

        withBackground = Optional.ofNullable(attributes)
            .map(attr -> attr.get(WITH_BACKGROUND_ATTRIBUTE))
            .map(Boolean::valueOf)
            .filter(Boolean.TRUE::equals)
            .orElse(null);
    }

    @Override
    public Map<String, String> toAttributes() {
        Map<String, String> attributes = new LinkedHashMap<>();

        if (image != null) {
            attributes.put(IMAGE_ATTRIBUTE, image.getId().toString());
        }

        if (Boolean.TRUE.equals(withBorder)) {
            attributes.put(WITH_BORDER_ATTRIBUTE, withBorder.toString());
        }

        if (Boolean.TRUE.equals(stretched)) {
            attributes.put(STRETCHED_ATTRIBUTE, stretched.toString());
        }

        if (Boolean.TRUE.equals(withBackground)) {
            attributes.put(WITH_BACKGROUND_ATTRIBUTE, withBackground.toString());
        }

        return attributes;
    }

    @Override
    public void fromBody(String body) {
        // do nothing
    }

    @Override
    public String toBody() {
        return Optional.ofNullable(getImage())
            .map(Image::getLabel)
            .orElse("Image");
    }

    @Override
    public void writePreviewHtml(ToolPageContext page) throws IOException {

        Image image = getImage();

        if (image != null) {
            String imageUrl = page.getPreviewThumbnailUrl(image);
            if (imageUrl != null) {
                page.writeElement("img", "src", imageUrl, "height", 300);
            }
        }
    }
}
