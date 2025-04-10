package com.zhouruxuan.lombok;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

@Data
public class BuilderTest {
    public int a = 1;
    public int b = 2;
    public int c;

    @Test
    public void serializable_test() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String str = "{\n" +
                "    \"fatherName\":1\n" +
                "}";
        // 反序列化测试
        LombokEntityFather deserialized = mapper.readValue(str, LombokEntityFather.class);
        Assert.assertEquals(1, deserialized.getFatherName());

        // 序列化测试
        LombokEntityFather build = LombokEntityFather.builder().fatherName(1).build();
        String json = mapper.writeValueAsString(build);
        Assert.assertTrue(json.contains("1"));
    }


    public static final class Builder {
        private int a;
        private int b;
        private int c;

        private Builder() {
        }

        public static Builder aBuilderTest() {
            return new Builder();
        }

        public Builder a(int a) {
            this.a = a;
            return this;
        }

        public Builder b(int b) {
            this.b = b;
            return this;
        }

        public Builder c(int c) {
            this.c = c;
            return this;
        }

        public BuilderTest build() {
            BuilderTest builderTest = new BuilderTest();
            builderTest.setA(a);
            builderTest.setB(b);
            builderTest.setC(c);
            return builderTest;
        }
    }
}

