/**
 * Adetuyi Tolu
 */

package com.crevation.baking.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.crevation.baking.R;
import com.crevation.baking.adapter.StepListAdapter;
import com.crevation.baking.data.model.Step;
import com.crevation.baking.util.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.crevation.baking.util.Constants.STEP_BUNDLE;
import static com.crevation.baking.util.Constants.STEP_FRAG;


public class StepFragment extends Fragment implements StepListAdapter.StepSelectedListener {


    @BindView(R.id.step_recycler)
    RecyclerView step_recycler;
    private ArrayList<Step> stepArrayList;
    private Unbinder unbinder;
    private StepListener stepListener;
    private LinearLayoutManager mLinearManager;
    Parcelable mListState;

    public interface StepListener {
        void itemClicked(Step step);
    }

    public StepFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().getParcelableArrayList(Constants.BUNDLE_STEP) != null) {
            stepArrayList = getArguments().getParcelableArrayList(Constants.BUNDLE_STEP);
        }
        if (savedInstanceState != null) {
            mListState = savedInstanceState.getParcelable(STEP_BUNDLE);
        }
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (savedInstanceState != null) {
            mListState = savedInstanceState.getParcelable(STEP_BUNDLE);
        }

        mLinearManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        populateList();

        return view;
    }

    public void restoreState() {

        if (mListState != null) {
            Toast.makeText(getActivity(), "state retrieved" , Toast.LENGTH_SHORT).show();
            step_recycler.getLayoutManager().onRestoreInstanceState(mListState);
            step_recycler.getLayoutManager().scrollToPosition(6);

        }
    }

    private void populateList() {

        StepListAdapter stepListAdapter = new StepListAdapter(stepArrayList, this);
        step_recycler.setLayoutManager(mLinearManager);
        step_recycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        step_recycler.setAdapter(stepListAdapter);
        restoreState();
    }

    @Override
    public void onStepSelected(int position) {

        Step step = stepArrayList.get(position);
        stepListener.itemClicked(step);

    }

    @Override
    public void onDestroy() {

        super.onDestroy();

        unbinder.unbind();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        mListState = step_recycler.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(STEP_BUNDLE, mListState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            stepListener = (StepListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement StepListener");
        }
    }
}
