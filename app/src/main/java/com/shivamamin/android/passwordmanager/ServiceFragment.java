package com.shivamamin.android.passwordmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by Shivam Amin on 2018-01-03.
 */

public class ServiceFragment extends Fragment {

    private static final String ARG_SERVICE_ID = "service_ID";

    private Service mService;
    private EditText mServiceField;
    private EditText mUsernameField;
    private EditText mPasswordField;
    private EditText mDescriptionField;
    private Button deleteButton;

    public static ServiceFragment newInstance(UUID serviceID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SERVICE_ID, serviceID);
        ServiceFragment fragment = new ServiceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID serviceID = (UUID) getArguments().getSerializable(ARG_SERVICE_ID);
        mService = ServiceLab.get(getActivity()).getService(serviceID);
    }

    @Override
    public void onPause() {
        super.onPause();
        ServiceLab.get(getActivity()).updateService(mService);
    }

    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState) {
        View v = inflator.inflate(R.layout.fragment_password, container, false);
        mServiceField = (EditText) v.findViewById(R.id.service_name);
        mUsernameField = (EditText) v.findViewById(R.id.username);
        mPasswordField = (EditText) v.findViewById(R.id.password);
        mDescriptionField = (EditText) v.findViewById(R.id.description);
        deleteButton = (Button) v.findViewById(R.id.deleteButton);
        mServiceField.setText(mService.getService());
        mUsernameField.setText(mService.getUsername());
        mPasswordField.setText(mService.getPassword());
        mDescriptionField.setText(mService.getDescription());
        mServiceField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mService.setService(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mUsernameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mService.setUsername(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mPasswordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mService.setPassword(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mService.setDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceLab.get(getActivity()).deleteService(mService);
                getActivity().finish();
            }
        });
        return v;
    }
}
