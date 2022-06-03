package com.brightspot.tutorial.graphql.demo13.rte.externalcontent;

import java.util.LinkedHashMap;
import java.util.Map;

import com.brightspot.tutorial.graphql.demo13.mark.RichTextElementView;
import com.psddev.cms.rte.ExternalContentCache;
import com.psddev.cms.rte.ExternalContentRichTextElement;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;
import com.psddev.cms.view.ViewResponse;
import com.psddev.dari.util.ObjectUtils;

/**
 * oEmbed Content
 */
@ViewInterface
public class ExternalContentRichTextElementViewModel extends ViewModel<ExternalContentRichTextElement> implements
    RichTextElementView {

    private Map<String, ?> rawData;

    @Override
    protected void onCreate(ViewResponse response) {
        super.onCreate(response);

        rawData = ExternalContentCache.get(model.getUrl(), model.getMaximumWidth(), model.getMaximumHeight());

        if (rawData != null) {

            Map<String, Object> rawDataClean = new LinkedHashMap<>();

            for (Map.Entry<String, ?> entry : rawData.entrySet()) {

                // Temp workaround for GraphQL bug
                if (entry.getValue() != null) {
                    rawDataClean.put(entry.getKey(), entry.getValue());
                }
            }

            rawData = rawDataClean;
        }
    }

    /**
     * The resource type. Valid values, along with value-specific parameters, are described below.
     *
     * @return Nonnull.
     */
    public String getType() {
        return getRawDataField("type");
    }

    /**
     * The oEmbed version number. This must be <code>1.0</code>.
     *
     * @return Nonnull.
     */
    public String getVersion() {
        return getRawDataField("version");
    }

    /**
     * A text title, describing the resource.
     *
     * @return Nullable.
     */
    public String getTitle() {
        return getRawDataField("title");
    }

    /**
     * The name of the author/owner of the resource.
     *
     * @return Nullable.
     */
    public String getAuthorName() {
        return getRawDataField("author_name");
    }

    /**
     * A URL for the author/owner of the resource.
     *
     * @return Nullable.
     */
    public String getAuthorUrl() {
        return getRawDataField("author_url");
    }

    /**
     * The name of the resource provider.
     *
     * @return Nullable.
     */
    public String getProviderName() {
        return getRawDataField("provider_name");
    }

    /**
     * The url of the resource provider.
     *
     * @return Nullable.
     */
    public String getProviderUrl() {
        return getRawDataField("provider_url");
    }

    /**
     * The <i>suggested</i> cache lifetime for this resource, in seconds. Consumers may choose to use this value or not.
     *
     * @return Nullable.
     */
    public Long getCacheAge() {
        return ObjectUtils.to(Long.class, getRawDataField("cache_age"));
    }

    /**
     * A URL to a thumbnail image representing the resource. The thumbnail must respect any <code>maxwidth</code> and
     * <code>maxheight</code> parameters. If this parameter is present, <code>thumbnail_width</code> and
     * <code>thumbnail_height</code> must also be present.
     *
     * @return Nullable.
     */
    public String getThumbnailUrl() {
        return getRawDataField("thumbnail_url");
    }

    /**
     * The width of the optional thumbnail. If this parameter is present, <code>thumbnail_url</code> and
     * <code>thumbnail_height</code> must also be present.
     *
     * @return Nullable.
     */
    public Integer getThumbnailWidth() {
        return ObjectUtils.to(Integer.class, getRawDataField("thumbnail_width"));
    }

    /**
     * The height of the optional thumbnail. If this parameter is present, <code>thumbnail_url</code> and
     * <code>thumbnail_width</code> must also be present.
     *
     * @return Nullable.
     */
    public Integer getThumbnailHeight() {
        return ObjectUtils.to(Integer.class, getRawDataField("thumbnail_height"));
    }

    /**
     * Gets the raw data as it was returned in the response.
     *
     * @return Nonnull.
     */
    public Map<String, ?> getRawData() {
        return rawData;
    }

    private String getRawDataField(String key) {
        if (rawData != null) {
            return ObjectUtils.to(String.class, rawData.get(key));
        } else {
            return null;
        }
    }

    /**
     * TODO: Split out to separate type
     *
     * @return Nullable.
     */
    public String getUrl() {
        return getRawDataField("url");
    }

    /**
     * TODO: Split out to separate type
     *
     * @return Nullable.
     */
    public String getHtml() {
        return getRawDataField("html");
    }

    /**
     * TODO: Split out to separate type
     *
     * @return Nullable.
     */
    public Integer getWidth() {
        return ObjectUtils.to(Integer.class, getRawDataField("width"));
    }

    /**
     * TODO: Split out to separate type
     *
     * @return Nullable.
     */
    public Integer getHeight() {
        return ObjectUtils.to(Integer.class, getRawDataField("height"));
    }
}
