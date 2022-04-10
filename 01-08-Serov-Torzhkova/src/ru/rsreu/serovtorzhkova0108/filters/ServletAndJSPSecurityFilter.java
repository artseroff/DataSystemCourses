package ru.rsreu.serovtorzhkova0108.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;

public class ServletAndJSPSecurityFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);
		String command = httpRequest.getParameter("command");
		String indexPath = Resourcer.getString("path.page.index");
		// if session ended, when user was on page and send command to controller
		if (session == null) {
			httpResponse.sendRedirect(String.format("%s%s", httpRequest.getContextPath(), indexPath));
			return;
		// if user logged out and returned back to page and send command to controller
		} else {
			User user = (User) session.getAttribute("user");
			if (user == null) {
				// (if user not logged in, not forward him or jsp was opened)
				if ((command != null && !"login".equals(command)) || isJspOpened(httpRequest)) {
					httpResponse.sendRedirect(String.format("%s%s", httpRequest.getContextPath(), indexPath));
					return;
				}
			} else {
				session.setAttribute("lockStatus", user.getLockStatus());
			}
		}
		chain.doFilter(request, response);
	}

	private boolean isJspOpened(HttpServletRequest request) {
		String stringURI = request.getRequestURI();
		if (stringURI != null) {
			return stringURI.matches(".*.jsp");
		} else {
			return false;
		}
	}
}