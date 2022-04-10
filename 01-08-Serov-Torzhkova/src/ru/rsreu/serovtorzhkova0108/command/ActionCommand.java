package ru.rsreu.serovtorzhkova0108.command;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {

	String execute(HttpServletRequest request);

	AdressingMethodEnum getAdressingMethod();
}