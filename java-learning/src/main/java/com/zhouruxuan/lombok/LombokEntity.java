package com.zhouruxuan.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LombokEntity {
    private int a;
    private String b;
    private Object c;
    private List<Object> objects;

    public LombokEntity getObject() {
        return new LombokEntity(this.a, this.b, this.c, this.objects);
    }
}
