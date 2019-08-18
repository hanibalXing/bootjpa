package com.gx.bootjpa.model;

/**
 * @author gx
 * @ClassName: HandFeeCaculator
 * @Description: java类作用描述
 * @date 2019/8/18 19:10
 * @Version: 1.0
 * @since
 */
public enum  HandFeeCaculator {
    // 端游
    PC {
        @Override
        public double count(double amount) {
            return amount * 5 / 100;
        }
    },
    // 页游
    PAGE {
        @Override
        public double count(double amount) {
            return amount * 2 / 100;
        }
    },
    // 手游
    MOBILE {
        @Override
        public double count(double amount) {
            return 0.0;
        }
    };
    public abstract double count(double amount);
}
