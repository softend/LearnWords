package com.example.learnenglish_20;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.learnenglish_20.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {

    private String[] modulesArr = new String[] {"Модуль 1" , "Модуль 2", "Модуль 3"};
    private ListView modulesList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, // Здесь указывается какой дизайн используется для фрагмента
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Inflate the layout for this fragment


        modulesList =  view.findViewById(R.id.modules_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(),R.layout.modules_lessons_list, R.id.button, modulesArr);
        modulesList.setAdapter(adapter);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}