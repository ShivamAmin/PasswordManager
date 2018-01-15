package com.shivamamin.android.passwordmanager.database;


import android.database.Cursor;
import android.database.CursorWrapper;

import com.shivamamin.android.passwordmanager.Service;

import java.util.UUID;

/**
 * Created by Shivam Amin on 2018-01-14.
 */

public class ServiceCursorWrapper extends CursorWrapper {
    public ServiceCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Service getService() {
        String uuidString = getString(getColumnIndex(ServiceDbSchema.ServiceTable.Cols.UUID));
        String service = getString(getColumnIndex(ServiceDbSchema.ServiceTable.Cols.Service));
        String username = getString(getColumnIndex(ServiceDbSchema.ServiceTable.Cols.Username));
        String password = getString(getColumnIndex(ServiceDbSchema.ServiceTable.Cols.Password));
        String description = getString(getColumnIndex(ServiceDbSchema.ServiceTable.Cols.Description));
        Service s = new Service(UUID.fromString(uuidString));
        s.setService(service);
        s.setUsername(username);
        s.setPassword(password);
        s.setDescription(description);
        return s;
    }
}
