package com.zhouruxuan.lombok;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class LombokEntityFather {
    private int fatherName;

    public int getFatherName() {
        return fatherName;
    }

    public void setFatherName(int fatherName) {
        this.fatherName = fatherName;
    }
}
