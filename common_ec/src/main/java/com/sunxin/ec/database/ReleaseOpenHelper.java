package com.sunxin.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * @author sunxin
 * @date 2018/10/30 2:59 PM
 * @desc
 */
public class ReleaseOpenHelper extends DaoMaster.OpenHelper {
    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }
}
