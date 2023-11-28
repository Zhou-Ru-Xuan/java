package com.zhouruxuan.api.mapstruct.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-04-20
 **/
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BugSource {
    int bid;

    int cId;

    BugSource bugSource;
}
