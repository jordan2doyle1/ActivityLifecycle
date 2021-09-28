package com.example.android.lifecycle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentA extends Fragment {

    public FragmentA() {
        super(R.layout.fragment_a);
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

        Button finish_fragment = requireView().findViewById(R.id.btn_finish_fragment);
        finish_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("btn_finish_fragment:" + R.id.btn_finish_fragment);
                finishFragment();
            }
        });
    }

    public void displayMessage() {
        TextView message = requireView().findViewById(R.id.fragment_text);
        message.setText(R.string.display_message_text);
    }

    public void finishFragment() {
        requireActivity().finish();
    }
}