package com.example.mymedcine.homescreen.moreFragment.presenter;

import android.content.Context;

import com.example.mymedcine.homescreen.homeFragment.view.HomeFragmentViewInterface;
import com.example.mymedcine.homescreen.moreFragment.view.MoreFragmentViewInterface;
import com.example.mymedcine.model.RepositoryInterface;

public class MoreFragmentPresenter implements MoreFragmentPresenterInterface {

    Context context;
    RepositoryInterface repository;
    MoreFragmentViewInterface view;

    public MoreFragmentPresenter(Context context, RepositoryInterface repository, MoreFragmentViewInterface view) {
        this.context = context;
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void logout() {
        repository.logout();
    }
}
