package com.sunxin.core.delegates;

/**
 * @author sunxin
 * @date 2018/10/26 9:32 AM
 * @desc 我们用到的
 */
public abstract class CommonDelegate extends PermissionCheckerDelegate {

    public <T extends CommonDelegate> T  getParentDelegate(){
        return (T) getParentFragment();
    }

}
