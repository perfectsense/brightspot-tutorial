package com.company.page;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.Directory;
import com.psddev.cms.db.Site;
import com.psddev.dari.db.Singleton;

public class EmployeeDirectory extends Content implements
        Directory.Item,
        Singleton {

    @Override
    public String createPermalink(Site site) {
        return "/employees";
    }

    @Override
    public String getLabel() {
        return "Employee Directory";
    }
}
