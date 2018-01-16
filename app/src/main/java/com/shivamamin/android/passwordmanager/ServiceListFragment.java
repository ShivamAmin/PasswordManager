package com.shivamamin.android.passwordmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.ClipboardManager;
import android.content.ClipData;
import android.content.Context;
import android.widget.Toast;

import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * Created by Shivam Amin on 2018-01-03.
 */

public class ServiceListFragment extends Fragment {
    private RecyclerView mServiceRecyclerView;
    private ServiceAdapter mAdapter;

    private TextView emptyViewMessage, emptyViewTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_list, container, false);
        emptyViewTitle = (TextView) view.findViewById(R.id.empty_view_title);
        emptyViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newService();
            }
        });
        emptyViewMessage = (TextView) view.findViewById(R.id.empty_view_message);
        emptyViewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newService();
            }
        });
        mServiceRecyclerView = (RecyclerView) view.findViewById(R.id.service_recycler_view);
        mServiceRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_service_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_service:
                newService();
                return true;
            case R.id.help:
                Intent hIntent = HelpActivity.newIntent(getActivity());
                startActivity(hIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUI() {
        ServiceLab serviceLab = ServiceLab.get(getActivity());
        List<Service> services = serviceLab.getServices();
        if(services.size() > 0) {
            emptyViewTitle.setVisibility(View.GONE);
            emptyViewMessage.setVisibility(View.GONE);
        } else {
            emptyViewTitle.setVisibility(View.VISIBLE);
            emptyViewMessage.setVisibility(View.VISIBLE);
        }
        if (mAdapter == null) {
            mAdapter = new ServiceAdapter(services);
            mServiceRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setServices(services);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void newService() {
        Service service = new Service();
        ServiceLab.get(getActivity()).addService(service);
        Intent sIntent = ServiceActivity.newIntent(getActivity(), service.getID());
        startActivity(sIntent);
    }

    private class ServiceHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Service mService;
        private TextView mTitleTextView;
        private TextView mUsernameTextView;
        private ImageButton mCopyButton;

        public ServiceHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_service, parent, false));
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.service_name);
            mUsernameTextView = (TextView) itemView.findViewById(R.id.service_username);
            mCopyButton = (ImageButton) itemView.findViewById(R.id.copyButton);
        }

        public void bind(Service service) {
            mService = service;
            mTitleTextView.setText(mService.getService());
            mUsernameTextView.setText(mService.getUsername());
            mCopyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClipboardManager cb = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText(mService.getService(), mService.getPassword());
                    cb.setPrimaryClip(clip);
                }
            });
            mCopyButton.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ClipboardManager cb = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText(mService.getService(), mService.getUsername());
                    cb.setPrimaryClip(clip);
                    return false;
                }
            });
        }

        @Override
        public void onClick(View view) {
            Intent intent = ServiceActivity.newIntent(getActivity(), mService.getID());
            startActivity(intent);
        }
    }

    private class ServiceAdapter extends RecyclerView.Adapter<ServiceHolder> {
        private List<Service> mServices;
        public ServiceAdapter(List<Service> services) {
            mServices = services;
        }
        @Override
        public ServiceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ServiceHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(ServiceHolder holder, int position) {
            Service service = mServices.get(position);
            holder.bind(service);
        }
        @Override
        public int getItemCount() {
            return mServices.size();
        }

        public void setServices(List<Service> services) {
            mServices = services;
        }
    }
}
