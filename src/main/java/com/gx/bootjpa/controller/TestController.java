package com.gx.bootjpa.controller;

import com.gx.bootjpa.Locked;
import com.gx.bootjpa.model.*;
import com.gx.bootjpa.service.*;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private RedissonService redissonService;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    TaskService taskService;
    @Autowired
    RoleService roleService;
    @Autowired
    StudentService studentService;

    @RequestMapping(path = "/add/{id}", method = GET)
    public void add(@PathVariable Integer id)
    {
        /*ProductStore p=new ProductStore();
        p.setName("123");
        ProductStore productStore = storeService.addStore(p);
        productStore.getGoods().add(new Goods(){{
            setId(new GoodsKey(){{
                setName("123");
                setT(productStore);

            }});
            setPrice(123);
        }});

        storeService.addStore(p);

           ProductStore store = storeService.findStore(id);
        store.getAction().p("gx");*/
       // Object gx = redissonService.getRMap("123").addAndGet("gx", 0.5);
       /* RBloomFilter<String> bloomFilter = redissonService.getRBloomFilter("sample");
        bloomFilter.delete();
        bloomFilter.tryInit(1000000L, 0.0001);
        bloomFilter.add("123");
        bloomFilter.add("456");
        boolean contains = bloomFilter.contains("123");
        System.out.println(contains);*/
       /* try(Locked locked= new Locked("gxx", Locked.LockType.eWrite,redissonClient)) {
            System.out.println("1");
            TimeUnit.SECONDS.sleep(10);
            try(Locked locked1= new Locked("gxx", Locked.LockType.eWrite,redissonClient)) {
                System.out.println("2");
                TimeUnit.SECONDS.sleep(10);
            } catch (IOException |InterruptedException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException |InterruptedException e) {
            throw new RuntimeException(e);
        }*/

        taskService.start(String.valueOf(id));

    }

    @RequestMapping(path = "/in/{id}", method = GET)
    public void in(@PathVariable Integer id)
    {
        List<Student> permissionByNames = studentService.getStudentByClassNames(Arrays.asList("xxx", null));
    }
    @RequestMapping(path = "/del/{id}", method = GET)
    public void test(@PathVariable Integer id)
    {
        taskService.pause(String.valueOf(id));
       //storeService.deleteStore(id);
    }

    @RequestMapping(path = "/up/{id}", method = GET)
    public void update(@PathVariable Integer id)
    {
        ProductStore store = storeService.findStore(id);
        store.getGoods().clear();
        store.getGoods().addAll(Arrays.asList(new Goods(){{
            setId(new GoodsKey(){{
                setName("456");
                setT(store);

            }});
            setPrice(123);
        }}));
        storeService.addStore(store);
    }

    @RequestMapping(path = "/redissonconfig", method = GET)
    public void redissonConfig()
    {
        try {
            redissonService.getRedissonClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path = "/redissonadd", method = GET)
    public void redissonAdd()
    {
            redissonService.getRMap("gx").put(123,123);
    }

}
