package com.brightspot.tutorial;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.psddev.cms.image.ImageSize;
import com.psddev.cms.image.ImageSizeProvider;


/**
 * Wraps the current ImageSizeProvider on the {@link ImageSizeProvider#getCurrentStack() stack} and adds custom sizes
 * for use with Algolia.
 */
public class CustomImageSizeProvider implements ImageSizeProvider {

    public static final ImageSize LANDSCAPE = ImageSize.builder()
            .displayName("Landscape")
            .internalName("landscape")
            .width(338)
            .height(150)
            .srcsetDescriptors(new ArrayList<String>() {{add("1x"); add("2x");}})
            .alternateFormats(new ArrayList<String>() {{add("webp");}})
            .build();

            public static final ImageSize PORTRAIT = ImageSize.builder()
            .displayName("Portrait")
            .internalName("portrait")
            .width(338)
            .height(375)
            .srcsetDescriptors(new ArrayList<String>() {{add("1x"); add("2x");}})
            .alternateFormats(new ArrayList<String>() {{add("webp");}})
            .build();

    public static final ImageSize EXAMPLE_SMALL = ImageSize.builder()
            .displayName("Example Small")
            .internalName("example-small")
            .width(421)
            .height(421)
            .srcsetDescriptors(new ArrayList<String>() {{add("1x"); add("2x");}})
            .alternateFormats(new ArrayList<String>() {{add("webp");}})
            .build();

    public static final ImageSize EXAMPLE_UNCROPPED = ImageSize.builder()
            .displayName("Example (Uncropped)")
            .internalName("example-uncropped")
            .maximumHeight(421)
            .maximumWidth(421)
            .srcsetDescriptors(new ArrayList<String>() {{add("1x"); add("2x");}})
            .alternateFormats(new ArrayList<String>() {{add("webp");}})
            .build();

    public static final ImageSize THUMBNAIL = ImageSize.builder()
            .displayName("Thumbnail")
            .internalName("thumbnail")
            .maximumHeight(112)
            .maximumWidth(112)
            .srcsetDescriptors(new ArrayList<String>() {{add("1x"); add("2x");}})
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

        imageSizes.add(EXAMPLE_SMALL);
        imageSizes.add(LANDSCAPE);
        imageSizes.add(PORTRAIT);
        imageSizes.add(THUMBNAIL);
        return imageSizes;

    }

    @Override
    public ImageSize get(List<String> contexts, String field) {
        return previousProvider != null ? previousProvider.get(contexts, field) : null;
    }
}