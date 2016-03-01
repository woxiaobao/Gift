<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- tree css -->	
<link rel="stylesheet"	href="${pageContext.request.contextPath}/js/dtree/dtree.css"
	type="text/css">
<!-- dtree js -->
	<script type="text/javascript"
						src="${pageContext.request.contextPath}/js/dtree/dtree.js"></script>
</head>
<body>
	<div class="row">
		<div class="col-md-6">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">模块</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body" id="moduleTree">

				
				<script type="text/javascript">
        
		        d = new dTree('d','${pageContext.request.contextPath}/js/dtree/');
		        d.add(0,-1,"模块","javascript:callback('');");
	         	<c:forEach items="${dtree }" var="t">
		         	<c:choose>
		             <c:when test="${t.parentId>-1}">
		             	d.add(${t.id},${t.parentId},"${t.name}","javascript:callback(${t.id});");
		             </c:when>
		             <c:otherwise>
		             d.add(${t.id},0,'${t.name}',"javascript:callback(${t.id});");
		             </c:otherwise>
		            </c:choose>
	     		</c:forEach>
		        
		        $("#moduleTree").html(d.toString());
		        
		        /**
		        *点击模块树的方法
		        *@param id 模块的id
		        */
		        function callback(id){
		    		var Url="/Gift/module/toModule";
		    		$.ajax({
		                cache: false,//是否读取缓存
		                type: "POST",
		                url:Url,
		                data:{'id':id},
		                async: true,
		                dataType:"text",
		                success: function(data) {
		                	if(data=="error"){
		                		//alert("id空");
		                	}else{
		                		$("#toModule").html(data);
		                	}
		                    	
		                },error: function(error) {
		                    alert("500-发生错误！");
		                }
		            });
		        	
		        }
		    		function toAddModule(menu,sid){
			        	//alert("123");
			        	var $active=$("#homePath");
		    			var m=$("#"+menu).attr(menu+"_add");
		    			$active.html(m);
		    			var Url="/Gift/"+menu+"/toAdd?sid="+sid;
		    			$(".content").load(Url,{},function(responseTxt,statusTxt,xhr){});
			            
		    		
		        	}
        
    </script>

				</div>
				<!-- /.box-body -->

			</div>
			<!-- /.box -->


		</div>
		<!-- /.col -->
		<div class="col-md-6">
			<div class="box" id="toModule">
				<div class="box-header">
					<h3 class="box-title">上级模块：**</h3>
					<!--  
					<button class="btn bg-purple margin" style="float:right;" onClick="toAddModule('module',-1)">新增</button>
					-->
					<button class="btn btn-default pull-right" onClick="toAddModule('module',-1)"><i class="fa fa-plus"></i> 新增</button>
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
						<c:forEach var="module" items="${moduleList}">
						<tr>
							<td><% out.print(++i); %></td>
							<td>${module.name }</td>
							<td>${module.permissions }</td>
							<td>
								<a href="javascript:objEdit('module','${module.id }','${page.currentPage }')"><span class="badge bg-red">编辑</span></a>
								<a href="javascript:objDel('module','${module.id }','${page.currentPage }')"><span class="badge bg-light-blue">删除</span></a>
							</td>
						</tr>
						</c:forEach>
					</table>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->


		</div>
		<!-- /.col -->
</body>
</html>