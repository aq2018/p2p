package com.aqfun.p2p.model.vo;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName : PaginationVO
 *
 * @author aq
 * @date 2018/9/6
 */
public class PaginationVO<T> implements Serializable{
    private static final long serialVersionUID = 5919534386136935175L;
    private Long total;
    private List<T> dataList;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "PaginationVO{" +
                "total=" + total +
                ", dataList=" + dataList +
                '}';
    }
}
