<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスマネくん</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/collect.css">
</head>

<body>
	<div class="titleScene">
		タスマネくん・管理画面
	</div>
	
	<form action="Updated" method="post">
	
	<div class="task">
		タスク名<br>
		変更前: <%=request.getAttribute("task") %><br>
		変更後:<br>
		<textarea name="taskEdited" rows="2" cols="100" maxlength="100" placeholder="変更後の内容をここに記述してください"></textarea>
	</div>
	<br>
	<div class="exp">
		説明<br>
		変更前: <%=request.getAttribute("exp")%><br>
		変更後:<br>
		<textarea name="expEdited" rows="5" cols="100" maxlength="255" placeholder="変更後の内容をここに記述してください"></textarea>
	</div>
	<br>
	<div class="date">
		日付<br>
		変更前: <%=request.getAttribute("date") %><br>
		変更後:<input name="dateEdited" type="date"></input>
	</div>
	
	<br>
	<br>
	<p class="btn">
	<button type="submit" name="idset" value="<%=request.getAttribute("id")%>" >変更を確定する</button>
	</p>
	</form>
</body>
</html>