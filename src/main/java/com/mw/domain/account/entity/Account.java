package com.mw.domain.account.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Account {

    @Id
    private Long id;
}
