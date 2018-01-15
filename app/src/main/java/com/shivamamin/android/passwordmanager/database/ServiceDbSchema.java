package com.shivamamin.android.passwordmanager.database;

/**
 * Created by Shivam Amin on 2018-01-14.
 */

public class ServiceDbSchema {
    public static final class ServiceTable {
        public static final String NAME = "Services";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String Service = "service";
            public static final String Username = "username";
            public static final String Password = "password";
            public static final String Description = "description";
        }
    }
}
