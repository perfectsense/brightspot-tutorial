package com.brightspot.tutorial.graphql.demo1;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;
import com.psddev.dari.db.Query;
import com.psddev.dari.util.StorageItem;

@ViewInterface
public class Demo1ViewModel extends ViewModel<Demo1> {

    public String getName() {
        return model.getAaString();
    }

    public String getShortName() {
        String name = model.getAaString();
        return name != null ? name.substring(0, Math.min(10, name.length())) : null;
    }

    public Integer getDay() {
        return Optional.ofNullable(getLocalDateTime())
            .map(LocalDateTime::getDayOfMonth)
            .orElse(null);
    }

    public Integer getMonth() {
        return Optional.ofNullable(getLocalDateTime())
            .map(LocalDateTime::getMonth)
            .map(Month::getValue)
            .orElse(null);
    }

    public Integer getYear() {
        return Optional.ofNullable(getLocalDateTime())
            .map(LocalDateTime::getYear)
            .orElse(null);
    }

    public String getImageUrl() {
        return Optional.ofNullable(model.getKkFile())
            .map(StorageItem::getPublicUrl)
            .orElse(null);
    }

    public long getTotalRecordCount() {
        return Query.from(Demo1.class).count();
    }

    public Iterable<Demo1RelationViewModel> getAllRelations() {
        List<Demo1Relation> relations = new ArrayList<>();

        Optional.ofNullable(model.getGgRef()).ifPresent(relations::add);
        Optional.ofNullable(model.getHhEmbedded()).ifPresent(relations::add);
        relations.addAll(model.getIiListRef());
        relations.addAll(model.getJjListEmbedded());

        return createViews(Demo1RelationViewModel.class, relations);
    }

    private LocalDateTime getLocalDateTime() {
        return Optional.ofNullable(model.getCcDate())
            .map(Date::toInstant)
            .map(instant -> LocalDateTime.ofInstant(instant, ZoneId.systemDefault()))
            .orElse(null);
    }
}
