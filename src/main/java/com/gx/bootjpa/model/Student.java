package com.gx.bootjpa.model;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 学号
     */
    private String number;

    /**
     * 姓名
     */
    private String name;

    /**
     * 所属班级
     */
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "class_id")
    private Classes classes;
}
