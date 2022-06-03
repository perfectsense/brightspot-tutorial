package com.brightspot.tutorial.graphql.demo13.article;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.brightspot.tutorial.Image;
import com.brightspot.tutorial.graphql.demo13.author.Author;
import com.brightspot.tutorial.graphql.demo13.link.DirectoryItemUtils;
import com.brightspot.tutorial.graphql.demo13.mark.MarkedTextFactory;
import com.brightspot.tutorial.graphql.demo13.mark.MarkedTextViewModel;
import com.brightspot.tutorial.graphql.demo13.tag.Tag;
import com.brightspot.tutorial.graphql.demo13.tag.TaggableData;
import com.psddev.cms.db.Site;
import com.psddev.cms.page.CurrentSite;
import com.psddev.cms.view.JsonView;
import com.psddev.cms.view.PageEntryView;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;
import com.psddev.cms.view.ViewTemplate;
import com.psddev.dari.util.StorageItem;

@JsonView
@ViewInterface
@ViewTemplate("/page/Article")
public class ArticleViewModel extends ViewModel<Article> implements PageEntryView {

    @CurrentSite
    private Site site;

    public String getId() {
        return model.getId().toString();
    }

    public String getPath() {
        return DirectoryItemUtils.getCanonicalUrl(site, model);
    }

    public String getTitle() {
        return model.getHeadline();
    }

    public String getAuthor() {
        return Optional.ofNullable(model.getAuthor())
            .map(Author::getDisplayName)
            .orElse(null);
    }

    public String getLeadImageUrl() {
        return Optional.ofNullable(model.getLeadImage())
            .map(Image::getFile)
            .map(StorageItem::getPublicUrl)
            .orElse(null);
    }

    public MarkedTextViewModel getBody() {
        return createView(MarkedTextViewModel.class,
            MarkedTextFactory.createWithDefaultPreprocessors(model.getBody()));
    }

    public List<String> getTags() {
        return model.as(TaggableData.class)
            .getTags()
            .stream()
            .map(Tag::getName)
            .collect(Collectors.toList());
    }
}
