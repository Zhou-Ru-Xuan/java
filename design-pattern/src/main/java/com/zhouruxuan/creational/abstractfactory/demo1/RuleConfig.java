package com.zhouruxuan.creational.abstractfactory.demo1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-03-14
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleConfig {
    private String description;

}
