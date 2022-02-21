package com.example.mymedcine.edit_drug.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.mymedcine.R;
import com.example.mymedcine.database.ConcreteLocalSource;
import com.example.mymedcine.edit_drug.presenter.UpdateDrugPresenter;
import com.example.mymedcine.edit_drug.presenter.UpdateDrugPresenterInterface;
import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditDrugFramgent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditDrugFramgent extends Fragment implements EditDrugInterface{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private UpdateDrugPresenterInterface presenterInterface;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditDrugFramgent() {
        // Required empty public constructor
    }
    public static EditDrugFramgent newInstance(String param1, String param2) {
        EditDrugFramgent fragment = new EditDrugFramgent();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_drug_framgent, container, false);
        presenterInterface = new UpdateDrugPresenter(this, Repository.getInstance(ConcreteLocalSource.getInstance(getContext())
                , getContext()));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void updateView(Drug drug) {

    }
}