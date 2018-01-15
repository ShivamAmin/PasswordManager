package com.shivamamin.android.passwordmanager;

import android.support.v4.app.Fragment;

/**
 * Created by Shivam Amin on 2018-01-03.
 */

public class ServiceListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ServiceListFragment();
    }
}
