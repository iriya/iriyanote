<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.google.appengine.api.users.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>new - iriyanote</title>
<link href="/stylesheet/index.css" rel="stylesheet" type="text/css" />
<%
	User user = (User) request.getAttribute("user");
	Integer sd = request.getParameter("sd") == null ? 0 : Integer
			.parseInt(request.getParameter("sd"));
	String[] weekdays = { "未选择", "星期日", "星期一", "星期二", "星期三", "星期四",
			"星期五", "星期六", };
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
  	<td colspan="2" id="statutext" height="29">&nbsp;</td>
    <td colspan="2" id="statuform">
    <a href='<%=request.getAttribute("logout") %>'>傲娇</a></td>
  </tr>
</table>

<table width="780" id="newnotecontainer" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="204">&nbsp;</td>
    <td align="center">
		<form id="newnoteform" name="newnoteform" method="post" action="/newnote">
		<table class="newnote" width="480" height="206" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td width="24" height="26">&nbsp;</td>
			<td width="191" height="26">&nbsp;</td>
			<td height="26">&nbsp;</td>
		  </tr>
		  <tr>
			<td height="148">&nbsp;</td>
			<td height="148"><img id="imgpreview" src="/images/1.jpg" width="128" height="95" /></td>
			<td height="148">
			    <p>名称：&nbsp;&nbsp;&nbsp;&nbsp;  
			      <input type="text" name="name" />
			    </p><p>
			      当前话数：
			      <input type="text" name="chapter" />
			    </p><p>
			      最大话数：
			      <input type="text" name="maxchapter" />
			    </p>
			    <p>
			    播放时间：
			    <select name="showday">
				<%
					for (int i = 0; i < 8; i++) {
						String s = "";
						if(new Integer(i).equals(sd)) s = "selected=\"selected\"";
				%>
					<option value="<%=i %>" <%=s %>><%=weekdays[i] %></option>
				<%
					}
				%>
				</select>
			    </p>			</td>
		  </tr>
		  <tr>
		    <td height="28">&nbsp;</td>
		    <td colspan="2">获得预览：
	        	<input type="text" id="imgurl" name="imgurl" size=50 />
				<input type="hidden" name="author" value="<%=user.getEmail()%>" />
			</td>
	      </tr>
		  <tr>
			<td>&nbsp;</td>
			<td><p><input type="button" name="preview" value="预览" onclick="document.getElementById('imgpreview').src=document.getElementById('imgurl').value;" /> <input type="submit" name="Submit" value="提交" /></p></td>
			<td>&nbsp;</td>
		  </tr>
	  </table>
	  </form>
</td>
    <td>&nbsp;</td>
  </tr>
</table>

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
