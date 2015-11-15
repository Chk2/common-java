package com.github.chk2.common.command;

public interface CommandHandler<C extends Command, R> {
	R execute(C command);
}
