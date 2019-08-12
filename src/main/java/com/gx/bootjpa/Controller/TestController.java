package com.gx.bootjpa.Controller;

import com.gx.bootjpa.model.Goods;
import com.gx.bootjpa.model.GoodsKey;
import com.gx.bootjpa.model.ProductStore;
import com.gx.bootjpa.service.StoreService;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author gx
 * @ClassName: TestController
 * @Description: java类作用描述
 * @date 2019/8/9 5:54
 * @Version: 1.0
 * @since
 */
@RestController
public class TestController {
    @Autowired
    StoreService storeService;
    @RequestMapping(path = "/add", method = GET)
    public void test()
    {
        ProductStore p=new ProductStore();
        p.setName("123");
        ProductStore productStore = storeService.addStore(p);
        productStore.getGoods().add(new Goods(){{
            setId(new GoodsKey(){{
                setName("123");
                setProductStore(productStore);

            }});
            setPrice(123);
        }});

        storeService.addStore(p);
    }

    @RequestMapping(path = "/del/{id}", method = GET)
    public void test(@PathVariable Integer id)
    {
       storeService.deleteStore(id);
    }

    @RequestMapping(path = "/up/{id}", method = GET)
    public void update(@PathVariable Integer id)
    {
        ProductStore store = storeService.findStore(id);
        store.getGoods().clear();
        store.getGoods().addAll(Arrays.asList(new Goods(){{
            setId(new GoodsKey(){{
                setName("456");
                setProductStore(store);

            }});
            setPrice(125);
        }}));
        storeService.addStore(store);
    }


}
