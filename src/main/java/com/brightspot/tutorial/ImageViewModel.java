package com.brightspot.tutorial;

import java.util.Map;
// import java.util.Optional;
// import java.util.UUID;

import com.psddev.cms.image.ImageSize;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;
import com.psddev.styleguide.ImageAttributes;
// import com.brightspot.tutorial.Image;


@ViewInterface
public class ImageViewModel extends ViewModel<Image>{

    // @Override
    @ImageAttributes
    public Map<String, ?> getImageFile() {
        return ImageSize.getAttributes(model.getImageFile());
    }


    // @Override
    public String getTitle() {
        return model.getTitle();
    }

    public String getImageId() {
        return model.getImageId().toString();
    }
}