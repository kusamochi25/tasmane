<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>タスマネくん</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/tasmane.css">
</head>
<body>

<div class="header">
<h1 class="headW">タスマネくん</h1>
<h5>ver. 1.0.2</h5>
</div>

<div class ="register">
	<form action="/Tasmane/ToDoMain" method="post">
	<input class="taskBar" type="text" name="task" maxlength="100" placeholder="タスク名をここに記述">
	<input type="submit" value="登録">
	<h5>最大100文字まで登録できます。</h5>
	<input class="infoBar" type="text" name="exp" maxlength="255" placeholder="内容をここに記述">
	<h5>最大255文字まで登録できます。</h5>
	</form> 
</div>

<div class="warng">
 <c:if test="${not empty errorMsg}">
	<p>${errorMsg}</p>
</c:if>
</div>

<div>
<table border="1" class="targetTable" width="1000">
		<tr>
		<td class="tttask">タスク名</td>
		<td class="ttexp">内容記述・管理</td>
		<td class="ttdate">日付</td>
		<td class="ttsubmt">完了ボタン</td>
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
			<form action="Collect" method="post">
			<button class="edtB" type="submit" name="edt" value="<%=idL.get(i)%>">変更</button>
			</form>
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
</div>
</body>
</html>