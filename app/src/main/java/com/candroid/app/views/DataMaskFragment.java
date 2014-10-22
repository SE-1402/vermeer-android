package com.candroid.app.views;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
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
    private String name;

    public String getName() {
        return name;
    }

    private OnFragmentInteractionListener mListener;

    private View view;
    private RelativeLayout layout;

    private String color;
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
        layout = (RelativeLayout) view.findViewById(R.id.layout_data_mask);
        setBackgroundColor();
        addIncludedObjects();
        return view;
    }

    /* Helper Methods */
    public DataMaskFragment setName(String name) {
        this.name = name;
        return this;
    }

    public DataMaskFragment setId(int id) {
        this.id = id;
        return this;
    }

    public DataMaskFragment setBackgroundColor(String color) {
        this.color = color;
        return this;
    }

    private void setBackgroundColor() {
        if (Convert.isNumeric(color)) {
            layout.setBackgroundColor(getActivity().getResources().getColor(getActivity().getResources().getIdentifier("vt" + color, "color", getActivity().getPackageName())));
        } else {
            // Attempt to Parse Color:
            layout.setBackgroundColor(Color.parseColor(color));
        }
    }

    public void addIncludeObject(View child) {
        viewList.add(child);
    }

    private void addIncludedObjects() {
        for (View view : viewList) {
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
