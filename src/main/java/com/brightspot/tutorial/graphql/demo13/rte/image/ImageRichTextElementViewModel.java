package com.brightspot.tutorial.graphql.demo13.rte.image;

import java.util.Map;
import java.util.Optional;

import com.brightspot.tutorial.Image;
import com.brightspot.tutorial.graphql.demo13.mark.MarkedTextFactory;
import com.brightspot.tutorial.graphql.demo13.mark.MarkedTextViewModel;
import com.brightspot.tutorial.graphql.demo13.mark.RichTextElementView;
import com.psddev.cms.image.ImageSize;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;
import com.psddev.dari.util.StorageItem;
import com.psddev.styleguide.ImageAttributes;

@ViewInterface
//@ViewTemplate("/rte/Image")
public class ImageRichTextElementViewModel extends ViewModel<ImageRichTextElement> implements RichTextElementView {

    @Override
    public boolean shouldCreate() {
        return Optional.ofNullable(model.getImage())
            .map(Image::getFile)
            .isPresent();
    }

    public String getFileUrl() {
        return Optional.ofNullable(model.getImage())
            .map(Image::getFile)
            .map(StorageItem::getPublicUrl)
            .orElse(null);
    }

    @ImageAttributes
    private Map<String, ?> getImage() {
        return Optional.ofNullable(model.getImage())
            .map(image -> ImageSize.getAttributes(image.getFile()))
            .orElse(null);
    }

    public String getAlt() {
        return Optional.ofNullable(model.getImage())
            .map(Image::getAltText)
            .orElse(null);
    }

    public MarkedTextViewModel getCaption() {
        return Optional.ofNullable(model.getImage())
            .map(image -> createView(MarkedTextViewModel.class,
                MarkedTextFactory.createWithDefaultPreprocessors(image.getCaption())))
            .orElse(null);
    }

    public CharSequence getCredit() {
        return Optional.ofNullable(model.getImage())
            .map(Image::getCredit)
            .orElse(null);
    }

    public Boolean getWithBorder() {
        return model.isWithBorder();
    }

    public Boolean getStretched() {
        return model.isStretched();
    }

    public Boolean getWithBackground() {
        return model.isWithBackground();
    }
}
