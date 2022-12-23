package com.brightspot.tutorial;

import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;
import com.psddev.dari.db.Query;

@ViewInterface
public class AllProfilesViewModel extends ViewModel<GraphQLSchemaDocumentationEndpoint> {
   public Iterable<ProfileViewModel> getProfiles()  {
         return createViews(ProfileViewModel.class, Query.from(Profile.class).selectAll());
    }
}