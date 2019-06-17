package com.mding.chatfeng.db;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class Demo {
    public Demo(Context mContext) {
        Realm.init(mContext);

        RealmConfiguration config = new RealmConfiguration.Builder().build();
        config.getRealmFileName();
        Realm.setDefaultConfiguration(config);

    }
}
