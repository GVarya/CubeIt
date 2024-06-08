package com.example.cube;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class MyCubesFragment extends Fragment {


    public MyCubesFragment() {
        // Required empty public constructor
    }

    public static MyCubesFragment newInstance(String param1, String param2) {
        MyCubesFragment fragment = new MyCubesFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_my_cubes, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.cubesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<Animation> cubes = (ArrayList<Animation>) MainActivity.DBHelper.getAllCubes();
        CubesAdapter adapter = new CubesAdapter(cubes);
        recyclerView.setAdapter(adapter);
        return view;
    }
}