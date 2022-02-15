package com.brightspot.tutorial.graphql.demo9;

import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;
import com.psddev.dari.util.PaginatedResult;

@ViewInterface
public class PaginationViewModel extends ViewModel<PaginatedResult<?>> {

    public long getOffset() {
        return model.getOffset();
    }

    public long getPreviousOffset() {
        return model.getPreviousOffset();
    }

    public long getNextOffset() {
        return model.getNextOffset();
    }

    public int getLimit() {
        return model.getLimit();
    }

    public long getCount() {
        return model.getCount();
    }

    public long getFirstItemIndex() {
        return model.getFirstItemIndex();
    }

    public long getLastItemIndex() {
        return model.getLastItemIndex();
    }

    public long getFirstOffset() {
        return model.getFirstOffset();
    }

    public long getLastOffset() {
        return model.getLastOffset();
    }

    public boolean getHasNext() {
        return model.getHasNext();
    }

    public boolean getHasPrevious() {
        return model.getHasPrevious();
    }

    public boolean getHasPages() {
        return model.getHasPages();
    }

    public long getPageIndex() {
        return model.getPageIndex();
    }

    public long getPageCount() {
        return model.getPageCount();
    }
}
