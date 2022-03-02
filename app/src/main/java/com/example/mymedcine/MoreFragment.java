package com.example.mymedcine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.mymedcine.database.ConcreteLocalSource;
import com.example.mymedcine.homescreen.moreFragment.presenter.MoreFragmentPresenter;
import com.example.mymedcine.homescreen.moreFragment.presenter.MoreFragmentPresenterInterface;
import com.example.mymedcine.homescreen.moreFragment.view.MoreFragmentViewInterface;
import com.example.mymedcine.login.view.LoginActivity;
import com.example.mymedcine.model.Repository;
import com.example.mymedcine.network.FireBaseConnection;
import com.example.mymedcine.utils.SharedPreferencesUtils;

public class MoreFragment extends Fragment implements MoreFragmentViewInterface {
    final String TAG = "TAG";
    LinearLayout logoutLayout;
    MoreFragmentPresenterInterface presenter;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new MoreFragmentPresenter(getContext()
                , Repository.getInstance(FireBaseConnection.getInstance(), ConcreteLocalSource.getInstance(getContext()),getContext())
                ,this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = getActivity().getSharedPreferences(SharedPreferencesUtils.FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Log.i(TAG, "onViewCreated: more fragment" );
        logoutLayout = view.findViewById(R.id.ll_log_out);
        logoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: log out");
                presenter.logout();
                editor.clear();
                editor.commit();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
    }

}