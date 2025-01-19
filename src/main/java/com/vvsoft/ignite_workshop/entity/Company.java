package com.vvsoft.ignite_workshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Company {
    private String companyId;
    private String name;
}
