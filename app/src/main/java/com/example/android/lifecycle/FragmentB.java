package com.example.android.lifecycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentB extends Fragment {

    public FragmentB() {
        super(R.layout.fragment_b);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @SuppressWarnings("Convert2Lambda")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button finish_fragment = requireView().findViewById(R.id.btn_finish_fragment);
        finish_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("btn_finish_fragment:" + R.id.btn_finish_fragment);
                finishFragment();
            }
        });
    }

    public void finishFragment() {
        getParentFragmentManager().popBackStack();
    }
}