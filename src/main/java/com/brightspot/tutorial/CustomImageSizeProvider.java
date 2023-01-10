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

    public static final ImageSize PORTRAIT = ImageSize.builder()
            .displayName("3:2 Portrait")
            .internalName("3-2-portrait")
            .group("3:2 Ratio")
            .width(600)
            .height(400)
            .strip(false)
            .srcsetDescriptors(new ArrayList<String>() {{add("100w");}{add("200w");}{add("300w");} {add("400w");} {add("500w");} {add("600w");}})
            .alternateFormats(new ArrayList<String>() {{add("webp");}})
            .build();

    public static final ImageSize TALL = ImageSize.builder()
            .displayName("2:3 Tall")
            .internalName("2-3-tall")
            .group("2:3 Ratio")
            .width(400)
            .height(600)
            .srcsetDescriptors(new ArrayList<String>() {{add("100w");}{add("200w");}{add("300w");} {add("400w");}})
            .alternateFormats(new ArrayList<String>() {{add("webp");}})
            .build();

    public static final ImageSize SQUARE = ImageSize.builder()
            .displayName("1:1 Square")
            .internalName("1-1-square")
            .group("1:1 Ratio")
            .width(600)
            .height(600)
            .srcsetDescriptors(new ArrayList<String>() {{add("100w");}{add("200w");}{add("300w");} {add("400w");} {add("500w");} {add("600w");}})
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
        imageSizes.add(SQUARE);
        return imageSizes;
    }

    @Override
    public ImageSize get(List<String> contexts, String field) {
        return previousProvider != null ? previousProvider.get(contexts, field) : null;
    }
}