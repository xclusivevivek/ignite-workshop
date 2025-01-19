package com.vvsoft.ignite_workshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Person {
    String name;
    String companyID;
    int age;
}
