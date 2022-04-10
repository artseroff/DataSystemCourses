package ru.rsreu.serovtorzhkova0108.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.AdressingMethodEnum;
import ru.rsreu.serovtorzhkova0108.command.factory.ActionFactory;

/**
 * Servlet implementation class Controller. Intercepts the request and 
 * calls the method doGet() or doPost();
 */
public class FrontController extends HttpServlet {

	/**
	 * Default value serial version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public FrontController() {
	}

	/**
	 * Defines command from request, execute it and redirect to 
	 * the page by adressing method of this command. 
	 * 
	 * @param request            httpRequest
	 * @param response           httpResponse
	 * @throws ServletException  is throwing if were errors with servlet mapping
	 * @throws IOException       is throwing if were I/O errors.
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		removeSessionAttributesExceptUserAttributes(session);
		String page = null;
		// define command from JSP
		ActionFactory client = new ActionFactory();
		ActionCommand command = client.defineCommand(request);
		// method returns response page
		page = command.execute(request);
		if (page != null) {
			if (command.getAdressingMethod().equals(AdressingMethodEnum.SEND_REDIRECT)) {
				response.sendRedirect(String.format("%s%s", request.getContextPath(), page));
			} else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
				dispatcher.forward(request, response);
			}
		} else {
			// set error messages
			page = Resourcer.getString("path.page.index");
			request.getSession().setAttribute("nullPage", Resourcer.getString("message.nullpage"));
			response.sendRedirect(String.format("%s%s", request.getContextPath(), page));
		}
	}

	/**
	 * Removes from current session all attributes except user attributes
	 * 
	 * @param session current session
	 */
	private void removeSessionAttributesExceptUserAttributes(HttpSession session) {
		if (session != null) {
			Enumeration<String> attributes = session.getAttributeNames();
			while (attributes.hasMoreElements()) {
				String attribute = attributes.nextElement();
				if (!("user".equals(attribute) || "disableElement".equals(attribute) || "lockStatus".equals(attribute))) {
					session.removeAttribute(attribute);
				}
			}
		}
	}

	/**
	 * Processing request sent by the method GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);

		
	}

	/**
	 * Processing request sent by the method POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}