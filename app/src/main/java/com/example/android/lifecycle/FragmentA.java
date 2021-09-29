package com.example.android.lifecycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentA extends Fragment {

    public FragmentA() {
        super(R.layout.fragment_a);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @SuppressWarnings("Convert2Lambda")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button display_message = requireView().findViewById(R.id.fragment_button);
        display_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("fragment_button:" + R.id.fragment_button);
                displayMessage();
            }
        });

        Button new_fragment = requireView().findViewById(R.id.new_fragment_button);
        new_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("new_fragment_button:" + R.id.new_fragment_button);
                loadNewFragment();
            }
        });

        Button finish_fragment = requireView().findViewById(R.id.btn_finish_fragment);
        finish_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("btn_finish_fragment:" + R.id.btn_finish_fragment);
                finishFragment();
            }
        });
    }

    public void loadNewFragment() {
        getParentFragmentManager().beginTransaction()
                .replace(((ViewGroup) requireView().getParent()).getId(), new FragmentB())
                .addToBackStack(null)
                .commit();
    }

    public void displayMessage() {
        TextView message = requireView().findViewById(R.id.fragment_text);
        message.setText(R.string.display_message_text);
    }

    public void finishFragment() {
        requireActivity().finish();
    }
}