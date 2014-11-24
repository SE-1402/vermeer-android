package com.candroid.app.views;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.candroid.app.R;
import com.candroid.app.activities.MainActivity;
import com.candroid.app.util.Convert;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DataMaskFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DataMaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataMaskFragment extends Fragment {
    private static final String DATA_ID = "data_mask_id";
    private static final String DATA_NAME = "data_mask_name";

    private int id;

    private OnFragmentInteractionListener mListener;

    private View view;
    private RelativeLayout layout;

    private int color;
    private int softKeyMaskId;
    private ArrayList<View> viewList = new ArrayList<View>();

    /**
     * @return A new instance of fragment DataMaskFragment.
     */
    public static DataMaskFragment newInstance() {
        DataMaskFragment fragment = new DataMaskFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public DataMaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_data_mask, container, false);
        layout = (RelativeLayout) view.findViewById(R.id.layout_data_mask_fragment);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setBackgroundColor();
        addIncludedObjects();
    }

    /* Helper Methods */
    public DataMaskFragment setId(int id) {
        this.id = id;
        return this;
    }

    public int getMaskId(){
        return this.id;
    }

    public DataMaskFragment setBackgroundColor(int color) {
        this.color = color;
        return this;
    }

    public DataMaskFragment setSoftKeyMaskId(int id) {
        this.softKeyMaskId = id;
        return this;
    }

    private void setBackgroundColor() {
        layout.setBackgroundColor(getActivity().getResources().getColor(getActivity().getResources().getIdentifier("vt" + color, "color", getActivity().getPackageName())));
    }

    public void addIncludeObject(View child) {
        viewList.add(child);
    }

    private void addIncludedObjects() {
        for (View view : viewList) {
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            layout.addView(view);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
