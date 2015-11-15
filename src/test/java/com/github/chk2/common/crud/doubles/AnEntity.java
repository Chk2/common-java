package com.github.chk2.common.crud.doubles;

import com.github.chk2.common.crud.model.MyEntity;

public class AnEntity implements MyEntity<Integer> {
	private int id;
	private String txt;

	private boolean disabled = false;

	public AnEntity() {
	}

	public AnEntity(String txt) {
		this.txt = txt;
	}

	public AnEntity(int id, String txt) {
		this.id = id;
		this.txt = txt;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public boolean isDisabled() {
		return disabled;
	}

	@Override
	public void disable() {
		disabled = true;
	}

	@Override
	public boolean isEnabled() {
		return !disabled;
	}

	@Override
	public void enable() {
		disabled = false;
	}

	public String getTxt() {
		return txt;
	}
}