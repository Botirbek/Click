package com.example.psb_click.dto.basic;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataDTO<T> implements Serializable {
    protected T data;
    protected AppErrorDTO error;
    protected boolean success;

    protected List<T> dataList;

    protected Boolean isList;

//    private Long totalCount;

    public DataDTO(T data) {
        this.data = data;
        this.success = true;
        this.isList = false;
        this.dataList = new ArrayList<>();
        this.error = new AppErrorDTO();
    }

    public DataDTO(List<T> dataList) {
        this.data = (T) new Object();
        this.success = true;
        this.isList = true;
        this.dataList = dataList;
        this.error = new AppErrorDTO();
    }

    public DataDTO(AppErrorDTO error) {
        this.error = error;
        this.success = false;
        this.isList = false;
        this.dataList = new ArrayList<>();
        this.data = (T) new Object();
    }

//    public DataDTO(T data, Long totalCount) {
//        this.data = data;
//        this.success = true;
////        this.totalCount = totalCount;
//    }

}
