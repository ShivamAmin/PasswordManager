package com.shivamamin.android.passwordmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by Shivam Amin on 2018-01-03.
 */

public class ServiceListActivity extends SingleFragmentActivity {

    private boolean mAuthenticated = false;
    private String mPassword = "";
    private SharedPreferences sp;

    @Override
    protected Fragment createFragment() {
        return new ServiceListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getApplicationContext().getSharedPreferences("masterPassword", 0);
        mPassword = sp.getString("mPassword", "");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Authenticate();
    }

    @Override
    public void onTrimMemory(final int level) {
        if (level >= 20) {
            mAuthenticated = false;
        }
    }

    private void Authenticate() {
        if(!mAuthenticated) {
            if(!mPassword.equals("")){
                Intent aIntent = AuthenticationActivity.newIntent(getApplicationContext(), mPassword);
                startActivityForResult(aIntent, 101);
            } else {
                Intent aIntent = AuthenticationActivity.newIntent(getApplicationContext(), mPassword);
                startActivityForResult(aIntent, 111);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 101 && resultCode == RESULT_OK) {
            mAuthenticated = data.getBooleanExtra("authentication", false);
        } else if(requestCode == 111 && resultCode == RESULT_OK) {
            mPassword = data.getStringExtra("password");
            SharedPreferences.Editor e = sp.edit();
            e.putString("mPassword", mPassword);
            e.apply();
        }
    }
}
