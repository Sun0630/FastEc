package com.sunxin.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * @author sunxin
 * @date 2018/10/30 2:59 PM
 * @desc 数据库管理类
 */
public class DatabaseManager {

    private DaoSession mDaoSession;
    private UserProfileDao mUserProfileDao;

    private DatabaseManager() {
    }

    public DatabaseManager init(Context context) {
        initDao(context);
        return this;
    }

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }


    private void initDao(Context context) {
        final ReleaseOpenHelper releaseOpenHelper = new ReleaseOpenHelper(context, "fast_ec.db");
        final Database db = releaseOpenHelper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mUserProfileDao = mDaoSession.getUserProfileDao();
    }

    /**
     * 获取到个人资料的数据库操作类
     *
     * @return
     */
    public UserProfileDao getUserProfileDao() {
        return mUserProfileDao;
    }
}
