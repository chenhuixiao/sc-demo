package com.example.scexcel.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "TB_PERSON")
public class PersonDo {
    @Id
    private String id;
    private String name;
    private int age;
    private int sex;
}
