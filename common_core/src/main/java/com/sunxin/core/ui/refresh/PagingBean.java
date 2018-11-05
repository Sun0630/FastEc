package com.sunxin.core.ui.refresh;

/**
 * @author sunxin
 * @date 2018/11/5 8:45 PM
 * @desc 分页实体类
 */
public class PagingBean {
    /**
     * 当前是第几页
     */
    private int mPageIndex = 0;

    /**
     * 一共有多少数据
     */
    private int mTotal = 0;

    /**
     * 一页显示几条数据
     */
    private int mPageSize = 0;

    /**
     * 当前显示了几条了
     */
    private int mCount = 0;

    /**
     * 加载延迟
     */
    private int mDelayed = 0;


    public int getPageIndex() {
        return mPageIndex;
    }

    public PagingBean setPageIndex(int pageIndex) {
        mPageIndex = pageIndex;
        return this;
    }

    public int getTotal() {
        return mTotal;
    }

    public PagingBean setTotal(int total) {
        mTotal = total;
        return this;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public PagingBean setPageSize(int pageSize) {
        mPageSize = pageSize;
        return this;
    }

    public int getCount() {
        return mCount;
    }

    public PagingBean setCount(int count) {
        mCount = count;
        return this;
    }

    public int getDelayed() {
        return mDelayed;
    }

    public PagingBean setDelayed(int delayed) {
        mDelayed = delayed;
        return this;
    }

    PagingBean addIndex(){
        mPageIndex++;
        return this;
    }

}
