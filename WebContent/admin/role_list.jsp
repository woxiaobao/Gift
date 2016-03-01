<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="box">
		<div class="box-header with-border">
			<h3 class="box-title">角色列表</h3>
			<c:set var="menu" value="role"></c:set>
			<!--  
			<button class="btn bg-purple margin" onClick="toAdd('role')">新增</button>
			-->
			<button class="btn btn-default pull-right" onClick="toAdd('role')"><i class="fa fa-plus"></i> 新增</button>
		</div>
		<!-- /.box-header -->
		<div class="box-body">
			<table class="table table-bordered">
				<tr>
					<th style="width: 10px">#</th>
					<th>角色名称</th>
					<th>说明</th>
					<th>创建时间</th>
					<th style="width: 100px;">操作</th>
				</tr>
				<% int i=0; %>
				<c:if test="${!empty roleList }">
				<c:forEach var="role" items="${roleList}">
				<tr>
					<td><% out.print(++i); %></td>
					<td>${role.roleName }</td>
					<td>${role.comment }</td>
					<td>${role.dateCreated }</td>
					<td>
						<a href="javascript:objEdit('role','${role.id }','${page.currentPage }')"><span class="badge bg-red">编辑</span></a>
						<a href="javascript:objDel('role','${role.id }','${page.currentPage }')"><span class="badge bg-light-blue">删除</span></a>
					</td>
				</tr>
				</c:forEach>
				</c:if>
				
			</table>
		</div>
		<%@ include file="footer.jsp"%>
		
	</div>
	<!-- /.box -->
</body>
</html>