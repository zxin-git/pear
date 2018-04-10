package com.zxin.mvc.core.bean;
import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

public abstract class BasePojo {

    @Column("ct")
    protected Date createTime;
    @Column("ut")
    protected Date updateTime;

    public String toString() {
        // 这不是必须的, 只是为了debug的时候方便看
        return Json.toJson(this, JsonFormat.compact() );
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}