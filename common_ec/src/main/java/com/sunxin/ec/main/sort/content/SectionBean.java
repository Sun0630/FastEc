package com.sunxin.ec.main.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * @author sunxin
 * @date 2018/11/8 2:35 PM
 * @desc
 */
public class SectionBean extends SectionEntity<SectionContentItemEntity> {

    private boolean isMore = false;

    private int id;

    public SectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SectionBean(SectionContentItemEntity entity) {
        super(entity);
    }


    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
