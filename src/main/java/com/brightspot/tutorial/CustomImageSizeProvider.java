package com.brightspot.tutorial;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
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
            .group("landscape") // aspect ratio 3:2
            .width(600)
            .height(400)
            .strip(false)
            .srcsetDescriptors(new ArrayList<String>() {{add("1x"); add("2x");}})
            .alternateFormats(new ArrayList<String>() {{add("webp");}})
            .build();

            public static final ImageSize PORTRAIT = ImageSize.builder()
            .displayName("Portrait")
            .internalName("portrait")
            .width(400)
            .height(600)
            .group("portrait")// aspect ratio 2:3
            .srcsetDescriptors(new ArrayList<String>() {{add("1x"); add("2x");}})
            .alternateFormats(new ArrayList<String>() {{add("webp");}})
            .build();

    public static final ImageSize EXAMPLE_SMALL = ImageSize.builder()
            .displayName("Example Large")
            .internalName("example-large")
            .maximumWidth(1000)
            .group("landscape")
            .srcsetDescriptors(new ArrayList<String>() {{add("350w"); add("550w"); add("750w"); add("1000w");}})
            .formatMappings(new HashMap<String, String>() {{put("jpg", "png");}}) // will convert jpg image to png
            .alternateFormats(new ArrayList<String>() {{add("webp");}})
            .quality(80) // default is 90
            .build();

    public static final ImageSize EXAMPLE_UNCROPPED = ImageSize.builder()
            .displayName("Square")
            .internalName("square")
            .height(800)
            .width(800)
            .group("square") //aspect ratio: 1: 1
            .srcsetDescriptors(new ArrayList<String>() {{add("1x"); add("2x");}})
            .alternateFormats(new ArrayList<String>() {{add("webp");}})
            .build();

    public static final ImageSize THUMBNAIL = ImageSize.builder()
            .displayName("Thumbnail")
            .internalName("thumbnail")
            .height(112)
            .width(112)
            .group("square")  //aspect ratio: 1: 1
            .format("png") // will convert all uploaded images to png
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