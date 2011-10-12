package com.google.code.iriyanote;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class HomeServlet extends HttpServlet {
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        String isFull = req.getParameter("full");
        
        if (user != null) {
        	req.setAttribute("user", user);
        	PersistenceManager pm = PmFactory.getInstance().getPersistenceManager();
        	try {
        		String addition = "";
        		// 如果非FULL DISPLAY则只查询ACTIVE状态的
        		if(!"1".equals(isFull)) { 
        			addition = " && status==" + Note.STATUS_ACTIVE;
        		}
        		
	        	List<Note> notes = (List<Note>) pm.newQuery("select from " + Note.class.getName() + " where author=='" + 
	        			user.getEmail() + "' " + addition + " order by createTime range 0,100").execute();
	        	
	        	req.setAttribute("shelf", new NoteShelf(notes));
	        	req.setAttribute("sn", notes.size());
	        	req.setAttribute("dow", new Integer(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)));
	        	req.setAttribute("logout", userService.createLogoutURL("http://www.google.com"));
	        	
	        	getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
        	} finally {
        		pm.close();
        	}
        }
        else {
        	resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
        }
	}
}
