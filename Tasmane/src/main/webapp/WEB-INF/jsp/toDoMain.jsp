<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>タスマネくん</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/head.css">
</head>
<body>

<p>
<h1>タスマネくん</h1>
<h6>ver. 0.0.1</h6>
</p>

<form action="/Tasmane/ToDoMain" method="post">
タスク名: <input type="text" name="task">
<input type="submit" value="登録">
<br>
<h4>最大100文字まで登録できます。</h4>
<br>
内容: <input type="text" name="exp">
<h4>最大255文字まで登録できます。</h4>
</form> 

 <c:if test="${not empty errorMsg}">
	<p>${errorMsg}</p>
</c:if>

<table border="1" id="targetTable">
		<tr>
		<td>タスク名</td>
		<td>内容記述・管理</td>
		<td>日付</td>
		<td>完了ボタン</td>
		</tr>
	<%
	List<String> tasks = (List<String>)request.getAttribute("taskL");
	List<String> exps = (List<String>)request.getAttribute("expL");
	List<Date> dates = (List<Date>)request.getAttribute("dateL");
	List<Boolean> marks = (List<Boolean>)request.getAttribute("markL");
	List<Integer> idL = (List<Integer>)request.getAttribute("idL");
	for(int i = 0; i<tasks.size(); i++) {
	%>
	<tr>
		<td>
			<% if (tasks != null){%>
			<%= tasks.get(i) %>
			<%}%>
		</td>
		
		<td>
			<% if (exps != null) {%>
			<%=exps.get(i) %>
			<%}%>
		</td>
		
		<td>
			<% if (dates != null) {%>
			<%=dates.get(i) %>
			<%}%>
		</td>
		
		<td>
		<% if (marks != null) {
			if(!marks.get(i)) {
			%>
			<form action="/Tasmane/ToDoMain" method="post">
			<button type="submit" name="getid" value="<%=idL.get(i)%>">完了</button>
			</form>
			<%}
			} %>
		</td>
		
	</tr>
	<%}%>
</table>
</body>
</html>