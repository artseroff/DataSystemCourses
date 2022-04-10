package ru.rsreu.serovtorzhkova0108.command.administrator;

import javax.servlet.http.HttpServletRequest;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.AdressingMethodEnum;

public class ShowAddUserPageCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		return Resourcer.getString("path.page.admin.addUser");
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.FORWARD;
	}
}