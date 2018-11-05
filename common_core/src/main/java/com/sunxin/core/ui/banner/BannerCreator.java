package com.sunxin.core.ui.banner;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.sunxin.core.R;

import java.util.ArrayList;

/**
 * @author sunxin
 * @date 2018/11/5 4:40 PM
 * @desc
 */
public class BannerCreator {
    public static final void setDefault(
            ConvenientBanner<String> convenientBanner,
            ArrayList<String> banners,
            OnItemClickListener clickListener
    ) {
        convenientBanner
                .setPages(new HolderCreator(), banners)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setOnItemClickListener(clickListener)
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .startTurning(3000)
                .setCanLoop(true);
    }
}
