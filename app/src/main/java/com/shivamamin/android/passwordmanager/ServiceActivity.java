package com.shivamamin.android.passwordmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class ServiceActivity extends SingleFragmentActivity {

    private static final String EXTRA_SERVICE_ID = "com.shivamamin.android.passwordmanager.service_id";

    public static Intent newIntent(Context packageContext, UUID serviceID) {
        Intent intent = new Intent(packageContext, ServiceActivity.class);
        intent.putExtra(EXTRA_SERVICE_ID, serviceID);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID serviceID = (UUID) getIntent()
                .getSerializableExtra(EXTRA_SERVICE_ID);
        return ServiceFragment.newInstance(serviceID);
    }
}
