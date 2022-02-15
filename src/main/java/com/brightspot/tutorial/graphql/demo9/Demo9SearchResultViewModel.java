package com.brightspot.tutorial.graphql.demo9;

import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;
import com.psddev.cms.view.ViewResponse;
import com.psddev.dari.db.Query;
import com.psddev.dari.util.PaginatedResult;
import com.psddev.dari.web.annotation.WebParameter;

@ViewInterface("DemoNineSearchResult")
public class Demo9SearchResultViewModel extends ViewModel<MyNinthGraphQLEndpoint> {

    @WebParameter
    private String searchQuery;

    @WebParameter
    private boolean includeDrafts;

    @WebParameter
    private Long offset;

    @WebParameter
    private Integer limit;

    private PaginatedResult<Demo9> searchResult;

    @Override
    protected void onCreate(ViewResponse response) {
        super.onCreate(response);

        Query<Demo9> demo9Query = Query.from(Demo9.class);

        if (searchQuery != null) {
            demo9Query.where("* matches ?", searchQuery);
        }

        if (includeDrafts) {
            demo9Query.where("cms.content.draft = missing || cms.content.draft = ?", true);
        }

        searchResult = demo9Query.select(getOffset(), getLimit());
    }

    public Iterable<Demo9ViewModel> getItems() {
        return createViews(Demo9ViewModel.class, searchResult.getItems());
    }

    public PaginationViewModel getPagination() {
        return createView(PaginationViewModel.class, searchResult);
    }

    private long getOffset() {
        return offset != null ? Math.max(offset, 0) : 0;
    }

    private int getLimit() {
        return limit != null ? Math.max(limit, 0) : 10;
    }
}
