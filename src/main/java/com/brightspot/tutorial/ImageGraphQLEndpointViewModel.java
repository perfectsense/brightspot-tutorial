package com.brightspot.tutorial;

import com.psddev.cms.view.ViewModel;
import com.psddev.cms.view.ViewResponse;
import com.psddev.dari.util.PaginatedResult;
import com.psddev.dari.db.Query;
// import com.psddev.dari.db.QueryPhrase;
import com.psddev.dari.web.annotation.WebParameter;
import com.psddev.cms.page.CurrentSite;
import com.psddev.cms.view.ViewInterface;
// import com.psddev.cms.view.ViewModel;
// import com.psddev.cms.view.ViewResponse;

// import com.brightspot.tutorial.Image;


import com.psddev.cms.db.Site;
// import com.psddev.cms.page.CurrentSite;


@ViewInterface("Images")
public class ImageGraphQLEndpointViewModel extends ViewModel<ImageGraphQLEndpoint>{

    @CurrentSite
    private Site site;

    @WebParameter
    private int page;

    // @WebParameter
    // private int limit;

    private PaginatedResult<Image> paginatedResult;

    @Override
    protected void onCreate(ViewResponse response) {
        int limit = 20;
        if (page < 1) {
            page = 1;
        }

        long offset = limit * (page - 1L);

        paginatedResult = buildQuery().select(offset, limit);
    }

    public Iterable<ImageViewModel> getItems() {
        return createViews(ImageViewModel.class, paginatedResult.getItems());
    }

    public Integer getNextPage() {
        return paginatedResult.hasNext() ? page + 1 : null;
    }
    private Query<Image> buildQuery() {
        Query<Image> query = Query.from(Image.class)
        .where("* matches *")
        .and(site != null ? site.itemsPredicate(): null);

        return query;
    }
}
