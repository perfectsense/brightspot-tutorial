package com.brightspot.tutorial;

import java.util.ArrayList;
import java.util.HashSet;
// import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.psddev.cms.image.ImageSize;
import com.psddev.cms.image.ImageSizeProvider;

public class CustomImageSizeProvider implements ImageSizeProvider {

    public static final ImageSize TALL = ImageSize.builder()
            .displayName("3:2 Ratio Large")
            .internalName("3-2-large")
            .group("3:2 Ratio")
            .width(1080)
            .height(720)
            .strip(false)
            .srcsetDescriptors(new ArrayList<String>() {{add("1x"); add("2x");}})
            .alternateFormats(new ArrayList<String>() {{add("webp");}})
            .build();

    public static final ImageSize PORTRAIT = ImageSize.builder()
            .displayName("16:9 Ratio")
            .internalName("16-9")
            .group("16:9 Ratio")
            .width(1920)
            .height(1080)
            .srcsetDescriptors(new ArrayList<String>() {{add("1x"); add("2x");}})
            .alternateFormats(new ArrayList<String>() {{add("webp");}})
            .build();

    public static final ImageSize SOCIAL_MEDIA = ImageSize.builder()
            .displayName("9:16 Ratio")
            .internalName("9-16")
            .group("9:16 Ratio")
            .width(1080)
            .height(1920)
            .srcsetDescriptors(new ArrayList<String>() {{add("1x"); add("2x");}})
            .alternateFormats(new ArrayList<String>() {{add("webp");}})
            .quality(90) // default is 90
            .build();

    public static final ImageSize SQUARE = ImageSize.builder()
            .displayName("1:1 Ratio Large")
            .internalName("1-1-large")
            .group("1:1 Ratio")
            .width(1080)
            .height(1080)
            .srcsetDescriptors(new ArrayList<String>() {{add("300w");} {add("400w");} {add("500w");} {add("600w");} {add("700w");} {add("800w");} {add("900w");} {add("1000w");}})
            .alternateFormats(new ArrayList<String>() {{add("webp");}})
            .build();

 
    private final ImageSizeProvider previousProvider;

    public CustomImageSizeProvider(ImageSizeProvider previousProvider) {
        this.previousProvider = previousProvider;
    }

  
    @Override
    public Set<ImageSize> getAll() {
        Set<ImageSize> imageSizes = new HashSet<>();
        Optional.ofNullable(previousProvider)
                .map(ImageSizeProvider::getAll)
                .ifPresent(imageSizes::addAll);
        
        imageSizes.add(TALL);
        imageSizes.add(PORTRAIT);
        imageSizes.add(SOCIAL_MEDIA);
        imageSizes.add(SQUARE);
        return imageSizes;
    }

    @Override
    public ImageSize get(List<String> contexts, String field) {
        return previousProvider != null ? previousProvider.get(contexts, field) : null;
    }
}