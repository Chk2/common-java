package com.github.chk2.common.crud.model;

public interface MyEntity<I> {
    I getId();

    boolean isDisabled();

    void disable();

    boolean isEnabled();

    void enable();
}
