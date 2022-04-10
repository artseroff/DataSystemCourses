package ru.rsreu.serovtorzhkova0108.command;

import javax.servlet.http.HttpServletRequest;

import com.prutzkow.resourcer.Resourcer;

public class LogoutCommand implements ActionCommand {
	@Override
	public String execute(HttpServletRequest request) {
		String page = Resourcer.getString("path.page.index");
		request.getSession().invalidate();
		return page;
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.FORWARD;
	}
}