package com.shopee.shopeebeadmindemo.commands;

import com.shopee.shopeebeadmindemo.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@AllArgsConstructor
@ShellComponent
public class AccountCommands {
    private final AccountService accountService;

    @ShellMethod("Add two numbers.")
    public int add(int a, int b) {
        return a + b;
    }
}
