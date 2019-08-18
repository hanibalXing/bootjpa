package com.gx.bootjpa.service.impl;

import com.gx.bootjpa.service.EnumService;
import org.springframework.stereotype.Service;

/**
 * @author gx
 * @ClassName: EnumServiceImpl
 * @Description: java类作用描述
 * @date 2019/8/18 18:36
 * @Version: 1.0
 * @since
 */
@Service("A")
public class EnumServiceAImpl implements EnumService {
    @Override
    public void print(String s) {
        System.out.println("a = [" + s + "]");
    }
}
