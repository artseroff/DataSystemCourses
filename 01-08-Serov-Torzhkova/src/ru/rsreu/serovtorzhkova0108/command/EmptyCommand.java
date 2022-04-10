package ru.rsreu.serovtorzhkova0108.command;

import javax.servlet.http.HttpServletRequest;

import com.prutzkow.resourcer.Resourcer;

public class EmptyCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		return Resourcer.getString("path.page.login");
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.FORWARD;
	}
}