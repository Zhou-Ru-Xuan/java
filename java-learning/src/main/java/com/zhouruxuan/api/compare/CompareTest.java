package com.zhouruxuan.api.compare;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.*;

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

    @Test
    public void testArrCmp(){
        List<Integer> A = Arrays.asList(1, 3, 5, 7, 9);
        List<CompareBean> B = Arrays.asList(new CompareBean(3), new CompareBean(9), new CompareBean(1), new CompareBean(7), new CompareBean(5));

        // 使用自定义的Comparator进行排序
        B.sort((o1, o2) -> {
            int index1 = A.indexOf(o1.getNo());
            int index2 = A.indexOf(o2.getNo());
            return Integer.compare(index1, index2);
        });

        System.out.println(B); // 打印排序后的序列B
    }
}

@NoArgsConstructor
@AllArgsConstructor
@Data
class CompareBean{
    Boolean a;
    int no;

    public CompareBean(Boolean a) {
        this.a = a;
    }

    public CompareBean(int no) {
        this.no = no;
    }
}