package com.zhouruxuan.api.compare;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CompareTest {
    @Test
    public void testBooleanCmp(){
        CompareBean trueBean = new CompareBean(true);
        CompareBean falseBean = new CompareBean(false);
        List<CompareBean> list = new ArrayList<>();
        list.add(trueBean);
        list.add(falseBean);
        list.add(new CompareBean(null));
        list.sort(Comparator.comparing(CompareBean::getA,Comparator.nullsFirst(Boolean::compare)).reversed());
        System.out.println(list);
    }
}

@NoArgsConstructor
@AllArgsConstructor
@Data
class CompareBean{
    Boolean a;
}