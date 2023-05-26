package com.oven.server.api.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ListResponse<T> extends BaseResponse {

    List<T> dataList;

}
