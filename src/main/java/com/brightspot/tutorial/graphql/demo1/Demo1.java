package com.brightspot.tutorial.graphql.demo1;

import java.util.Date;
import java.util.List;

import com.psddev.cms.db.Content;
import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.util.StorageItem;

@DisplayName("Demo 1")
public class Demo1 extends Content {

    private String string1;
    private Boolean boolean1;
    private Date date1;
    private Integer integer1;
    private List<String> list1;
    private StorageItem file1;
}
