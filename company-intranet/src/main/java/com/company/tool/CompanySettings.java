package com.company.tool;

import java.util.List;

import com.psddev.cms.tool.Plugin;
import com.psddev.cms.tool.Tool;
import com.psddev.dari.db.Recordable;

@Recordable.DisplayName("Company")
public class CompanySettings extends Tool {

    @Override
    public String getApplicationName() {
        return "company";
    }

    @Override
    public List<Plugin> getPlugins() {
        return super.getPlugins();
    }
}
