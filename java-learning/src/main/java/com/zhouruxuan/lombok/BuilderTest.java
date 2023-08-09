package com.zhouruxuan.lombok;

import lombok.Data;

@Data
public class BuilderTest {
    public int a = 1;
    public int b = 2;
    public int c;


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

