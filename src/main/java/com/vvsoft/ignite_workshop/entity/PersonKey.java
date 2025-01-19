package com.vvsoft.ignite_workshop.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.apache.ignite.cache.affinity.AffinityKeyMapped;

@AllArgsConstructor
@EqualsAndHashCode
public class PersonKey {
    String name;
    @AffinityKeyMapped
    String companyId;
}
