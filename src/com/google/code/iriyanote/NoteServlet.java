package com.google.code.iriyanote;

import java.io.IOException;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class NoteServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        if (user != null) {
        	req.setAttribute("user", user);
        	req.setAttribute("logout", userService.createLogoutURL("http://www.google.com"));
        	getServletContext().getRequestDispatcher("/new.jsp").forward(req, resp);
        }
        else {
        	resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
        }
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("name");
		Integer chapter = Integer.parseInt(req.getParameter("chapter"));
		Integer maxChapter = Integer.parseInt(req.getParameter("maxchapter"));
		Integer showDay = Integer.parseInt(req.getParameter("showday"));
		String imgUrl = req.getParameter("imgurl");
		String author = req.getParameter("author");
		
		Note note = new Note();
		note.setName(name);
		note.setChapter(chapter);
		note.setMaxChapter(maxChapter);
		note.setShowDay(showDay);
		note.setImgUrl(imgUrl);
		note.setAuthor(author);
		note.setCreateTime(new Date());
		note.setStatus(Note.STATUS_ACTIVE);
		
		if(!new Integer(-1).equals(showDay)) {
			PersistenceManager pm = PmFactory.getInstance().getPersistenceManager();
			try {
				pm.makePersistent(note);
			} finally {
				pm.close();
			}
		}
		resp.sendRedirect("/home");
		
	}
	
}
