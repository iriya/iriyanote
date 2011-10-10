<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="com.google.code.iriyanote.NoteShelf"%>
<%@page import="com.google.code.iriyanote.Note"%>
<%@page import="com.google.code.iriyanote.StringUtils" %>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>home - iriyanote</title>
<link href="/stylesheet/index.css" rel="stylesheet" type="text/css" />
<%
	User user = (User) request.getAttribute("user");
	Integer sn = (Integer) request.getAttribute("sn");
	NoteShelf shelf = (NoteShelf) request.getAttribute("shelf");
	Integer dow = (Integer) request.getAttribute("dow"); // 今天周几
	Integer dowb = (dow == 1) ? 7 : dow - 1; // 昨天周几
	String[] weekdays = { "", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
			"星期六", };
%>
</head>

<body>
<table width="780" height="26" border="0" cellpadding="0" cellspacing="0">
  <tr id="header">
    <td width="28" height="26">&nbsp;</td>
    <td width="75" height="26" id="logo">&nbsp;</td>
    <td width="65" height="26" class="hei" id="logotext"><a href="/">随贴</a></td>
    <td height="26" class="hei" id="welcome">欢迎：<%=user.getEmail()%></td>
  </tr>
  <tr id="statubar">
  	<td colspan="2" id="statutext" height="29">共watching：<%=sn%>个</td>
    <td colspan="2" id="statuform">
    <a href='<%=request.getAttribute("logout") %>'>蹭得累</a></td>
  </tr>
</table>

<%
	for (int i = 0; i < 7; i++) {
		int dowc = (dowb + i == 7) ? 7 : (dowb + i) % 7;
		List<Note> notes = shelf.getNotes(dowc);
%>
<table width="780" border="0" cellpadding="0" cellspacing="0" class="content" onmouseover="this.style.backgroundColor='#ffffcc'"
	onmouseout="this.style.backgroundColor='#ffffff'">
<%
	if (notes.size() == 0) { // 没有
%>
  <tr>
    <td width="54" height="54" class="weekday"><p><span class="hei"><%=weekdays[dowc]%></span></p><p><a href="/newnote?sd=<%=dowc %>">添加</a></p></td>
	<td width="135"><p>暂时还没有</p></td>
    <td width="135"><p>&nbsp;</p></td>
    <td width="135"><p>&nbsp;</p></td>
    <td width="135"><p>&nbsp;</p></td>
    <td width="135"><p>&nbsp;</p></td>
	<td width="3" class="content-right">&nbsp;</td>
  </tr>
<%
	}
	if (notes.size() > 0) {
%>
  <tr>
    <td width="54" height="154" class="weekday"><p><span class="hei"><%=weekdays[dowc]%></span></p><p><a href="/newnote?sd=<%=dowc %>">添加</a></p></td>
	<td colspan="5">
<%
	for (int j = 0; j < notes.size(); j++) {
		Note n = notes.get(j);
%>
		<div class="pnote">
			<div class="imgholder"><img src="<%=n.getImgUrl() %>" width="64" height="48" /></div>
			<div class="nameholder">
				<p><%=n.getName() %> 
				<%
					if(n.getStatus() == Note.STATUS_FIN) { 
				%>
				<p><a class="gray">共<%=n.getChapter() %>/<%=n.getMaxChapter() %>话</a> <a title="复活/最大话x2" class="gray" href="/follow?nid=<%=n.getId() %>&f=res">RES</a></p>
				<%	}else{ %>
				<ul>
					<%
						for(int c=1;c<=n.getMaxChapter();c++){
							%> <li><a title='第#<%=c %>话' href="/follow?nid=<%=n.getId() %>&f=set&c=<%=c %>" class='<%=c<=n.getChapter()?"watched":"na" %>'><%=StringUtils.paddingLeft(String.valueOf(c), 2, '0') %></a></li> <%
						}
					%>
					<li><a title="忽略" class="tools" href="/follow?nid=<%=n.getId() %>&f=ign">IGN</a></li>
					<li><a title="删除" class="tools" href="/follow?nid=<%=n.getId() %>&f=del">DEL</a></li>
					<li><a title="完结" class="tools" href="/follow?nid=<%=n.getId() %>&f=fin">FIN</a></li>
				</ul>
				<%	} // end else %>
			</div>
		</div>
<%
	} // end for (int j = 0; j < notes.size(); j++) {
%>
	</td>
    <td class="content-right">&nbsp;</td>
  </tr>
<%
	}
%>
</table>
<%
	} // end for
%>

<table width="780" height="69" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center">Copyright(c)2010 随贴 iriyanote.appspots.com 版权所有</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
</html>
