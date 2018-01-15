package com.shivamamin.android.passwordmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.shivamamin.android.passwordmanager.database.ServiceBaseHelper;
import com.shivamamin.android.passwordmanager.database.ServiceCursorWrapper;
import com.shivamamin.android.passwordmanager.database.ServiceDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Shivam Amin on 2018-01-03.
 */

public class ServiceLab {

    private static ServiceLab sServiceLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    public static ServiceLab get(Context context) {
        if (sServiceLab == null) {
            sServiceLab = new ServiceLab(context);
        }
        return sServiceLab;
    }
    private ServiceLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new ServiceBaseHelper(mContext).getWritableDatabase();
    }
    public void addService(Service s) {
        ContentValues values = getContentValues(s);
        mDatabase.insert(ServiceDbSchema.ServiceTable.NAME, null, values);
    }
    public void updateService(Service s) {
        String uuidString = s.getID().toString();
        ContentValues values = getContentValues(s);
        mDatabase.update(ServiceDbSchema.ServiceTable.NAME, values,
                ServiceDbSchema.ServiceTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }
    public void deleteService(Service s) {
        String uuidString = s.getID().toString();
        ContentValues values = getContentValues(s);
        mDatabase.delete(ServiceDbSchema.ServiceTable.NAME,
                ServiceDbSchema.ServiceTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }
    private ServiceCursorWrapper queryServices(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                ServiceDbSchema.ServiceTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new ServiceCursorWrapper(cursor);
    }

    public List<Service> getServices() {
        List<Service> services = new ArrayList<>();
        ServiceCursorWrapper cursor = queryServices(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                services.add(cursor.getService());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return services;
    }
    public Service getService(UUID id) {
        ServiceCursorWrapper cursor = queryServices(
                ServiceDbSchema.ServiceTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );
        try {
            if(cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getService();
        } finally {
            cursor.close();
        }
    }
    private static ContentValues getContentValues(Service service) {
        ContentValues values = new ContentValues();
        values.put(ServiceDbSchema.ServiceTable.Cols.UUID, service.getID().toString());
        values.put(ServiceDbSchema.ServiceTable.Cols.Service, service.getService());
        values.put(ServiceDbSchema.ServiceTable.Cols.Username, service.getUsername());
        values.put(ServiceDbSchema.ServiceTable.Cols.Password, service.getPassword());
        values.put(ServiceDbSchema.ServiceTable.Cols.Description, service.getDescription());
        return values;
    }
}
