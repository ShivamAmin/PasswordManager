package com.shivamamin.android.passwordmanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Shivam Amin on 2018-01-14.
 */

public class HelpActivity extends Activity {
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, HelpActivity.class);
        return intent;
    }
}
