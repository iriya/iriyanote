package com.google.code.iriyanote;

import java.io.IOException;
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
public class FollowServlet extends HttpServlet {
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        if (user != null) {
        	Long id = req.getParameter("nid") == null ? -1 : Long.parseLong(req.getParameter("nid"));
        	String cmd = req.getParameter("f");
        	if(id != -1) {
        		PersistenceManager pm = PmFactory.getInstance().getPersistenceManager();
        		try {
        			List<Note> notes = (List<Note>) pm.newQuery("select from " + Note.class.getName() + " where author=='" + 
        					user.getEmail() + "' && id==" + id + " order by createTime range 0,1").execute();
        			Note note = notes.size() > 0 ? notes.get(0) : null;
        			if(note != null) {
        				if("add".equalsIgnoreCase(cmd)) { // +1
        					note.addChapter();
                		} else if("sub".equalsIgnoreCase(cmd)) { // -1
                			note.subChapter();
                		} else if("del".equalsIgnoreCase(cmd)) { // 删除
                			pm.deletePersistent(note);
                		} else if("fin".equalsIgnoreCase(cmd)) { // 完结
                			note.finChapter();
                		} else if("ign".equalsIgnoreCase(cmd)) { // ignore
                			note.ignChapter();
                		} else if("res".equalsIgnoreCase(cmd)) { // 复活
                			note.resChapter();
                		} else if("set".equalsIgnoreCase(cmd)) { // 设置第#话
                			String c = req.getParameter("c");
                			Integer chapter = 1;
                			try {
								chapter = Integer.parseInt(c);
							} catch (Exception ignore) {}
                			note.setChapter(chapter);
                		}
        			}
        		} finally {
        			pm.close();
        		}
        	}
        	resp.sendRedirect("/home");
        }
        else {
        	resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
        }
	}
}
