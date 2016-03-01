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
	<div class="box-header">
					<h3 class="box-title">上级模块：${module.name }</h3>
					<!-- 
					<button class="btn bg-purple margin" style="float:right;" onClick="toAddModule('module',{sid})">新增</button>
					 -->
					<button class="btn btn-default pull-right" onClick="toAddModule('module',${sid})"><i class="fa fa-plus"></i> 新增</button>
				</div>
				<!-- /.box-header -->
				<div class="box-body no-padding">
					<table class="table">
						<tr>
							<th style="width: 10px">#</th>
							<th>名称</th>
							<th>权限名</th>
							<th style="width: 120px">操作</th>
						</tr>
						<% int i=0; %>
						<c:forEach var="m" items="${mlist}">
						<tr>
							<td><% out.print(++i); %></td>
							<td>${m.name }</td>
							<td>${m.permissions }</td>
							<td>
								<a href="javascript:objEdit('module','${m.id }','${page.currentPage }')"><span class="badge bg-red">编辑</span></a>
								<a href="javascript:objDel('module','${m.id }','${page.currentPage }')"><span class="badge bg-light-blue">删除</span></a>
							</td>
						</tr>
						</c:forEach>
					</table>
				</div>
</body>
</html>