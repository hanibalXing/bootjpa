package com.gx.bootjpa;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        /*boolean empty = Arrays.asList(new Apple(null), new Apple(null)).stream().map(Apple::getColor)
                .distinct().collect(Collectors.toList()).isEmpty();
        HashSet<Apple> apples = new HashSet<>();
        apples.add(new Apple("red"));
        apples.add(new Apple("red"));
        System.out.println(apples.size());
        Map<String, CompletableFuture> map = new HashMap<>();
        map.put("gx",CompletableFuture.runAsync(()->{
            try {
                TimeUnit.MICROSECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            apples.add(new Apple("yellow"));
        }));
        map.put("gx",CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            apples.add(new Apple("black"));
        }));
        List<CompletableFuture> collect = map.values().stream().collect(Collectors.toList());
        CompletableFuture.allOf(collect.toArray(new CompletableFuture[collect.size()])).whenComplete((r,t)->{
            System.out.println(apples.stream().map(Apple::getColor).collect(Collectors.joining()));
        }).join();



        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行supplyAsync的线程:" + Thread.currentThread().getName());
            int i = 2 / 1;
            return i;
        });

        completableFuture.whenComplete(new BiConsumer<Integer, Throwable>() {
            @Override
            public void accept(Integer integer, Throwable throwable) {
                System.out.println("执行whenComplete的线程:" + Thread.currentThread().getName());
                if (throwable == null) {
                    System.out.println("计算未出现异常,结果:" + integer);
                }
            }
        });

        completableFuture.exceptionally(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) {
                //出现异常时,则返回一个默认值
                System.out.println("计算出现异常,信息:" + throwable.getMessage());
                return -1;
            }
        });

        System.out.println(completableFuture.get());*/


        /*CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1开始");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1结束");
            return 2;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2开始");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务2结束");
            return 3;
        }), (result1, result2) -> result1 * result2);*/

        /*CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1开始");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1结束");
            return 2;
        }).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2开始");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务2结束");
            return 3;
        }), (result1, result2) -> result1 * result2).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
            System.out.println("任务3开始");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务3结束");
            return 3;
        }), (result1, result2) -> result1 * result2);

        System.out.println("计算结果:" + completableFuture.get());
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务4开始");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务4结束");
            return 2;
        });
        integerCompletableFuture.whenComplete((i,t)->{
            System.out.println(i);
        });*/
        System.out.println(1+1);
    }
    static class Apple{
        private String color;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Apple apple = (Apple) o;
            return Objects.equals(color, apple.color);
        }

        @Override
        public int hashCode() {
            return Objects.hash(color);
        }

        public Apple(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
