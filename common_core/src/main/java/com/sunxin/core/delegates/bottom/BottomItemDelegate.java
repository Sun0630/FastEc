package com.sunxin.core.delegates.bottom;

import android.view.View;
import android.widget.Toast;

import com.sunxin.core.R;
import com.sunxin.core.delegates.CommonDelegate;

/**
 * @author sunxin
 * @date 2018/11/2 11:11 AM
 * @desc
 */
public abstract class BottomItemDelegate extends CommonDelegate implements View.OnClickListener {

    private static final long WAIT_TIME = 2000L;

    private long TOUCH_TIME = 0;


    @Override
    public boolean onBackPressedSupport() {

        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "再次点击退出" + getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
