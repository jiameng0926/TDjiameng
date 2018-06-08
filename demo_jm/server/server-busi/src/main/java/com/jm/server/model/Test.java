package com.jm.server.model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 
 * </p>
 *
 * @since 2018-06-07
 */
public class Test extends Model<Test> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 公司类型 省： 00,市 01
     */
    @TableField("company_type")
    private String companyType;
    @TableField("parent_id")
    private String parentId;
    @TableField("company_id")
    private String companyId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Test{" +
        "id=" + id +
        ", companyType=" + companyType +
        ", parentId=" + parentId +
        ", companyId=" + companyId +
        "}";
    }
}
