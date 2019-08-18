package com.gx.bootjpa.model;

import com.gx.bootjpa.service.EnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumSet;
import java.util.function.BiConsumer;

/**
 * @author gx
 * @ClassName: EnumAction
 * @Description: java类作用描述
 * @date 2019/8/18 18:43
 * @Version: 1.0
 * @since
 */
public enum EnumAction {
    A{
        @Override
        public void p(String p) {
            getServiceA().print(p);
        }
    },
    B{
        @Override
        public void p(String p) {
            getServiceB().print(p);
        }
    };
    private int id;
    private EnumService serviceA;
    private EnumService serviceB;

    public int getId() {
        return id;
    }

    public EnumService getServiceA() {
        return serviceA;
    }

    public void setServiceA(EnumService serviceA) {
        this.serviceA = serviceA;
    }

    public EnumService getServiceB() {
        return serviceB;
    }

    public void setServiceB(EnumService serviceB) {
        this.serviceB = serviceB;
    }

    @Component
    public static class EnumClassInner {

        @Autowired
        @Qualifier("A")
        private EnumService serviceA;
        @Autowired
        @Qualifier("B")
        private EnumService serviceB;

        @PostConstruct
        public void postConstruct() {
            for (EnumAction aEnum : EnumSet.allOf(EnumAction.class)) {
                aEnum.setServiceA(serviceA);
                aEnum.setServiceB(serviceB);
            }
        }
    }
    public abstract void p(String p);
}
