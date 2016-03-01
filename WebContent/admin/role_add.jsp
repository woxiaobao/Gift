<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- wtree css -->
<link rel="stylesheet"	href="${pageContext.request.contextPath}/js/checkdtree/dtree.css"
	type="text/css">
<!-- wtree js -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/checkdtree/wtree.js"></script>
</head>
<body>
<!-- general form elements -->
              <div class="box box-primary">
                <div class="box-header">
                  <h3 class="box-title">Quick Example</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form  name="roleForm" id="roleForm" method="post">
                  <div class="box-body">
                  
                    <div class="form-group">
                      <label for="">角色名称</label>
                      <input type="text" class="form-control" name="roleName" id="" placeholder="name">
                    </div>
                    
                    <div class="form-group">
                      <label for="">角色说明</label>
                      <input type="text" class="form-control" name="comment" id="" placeholder="comment">
                    </div>
                    <input type="hidden" id="moduleId" name="mid" value="">
                    <div class="form-group" id="moduleTree">
                    </div>
                    <script type="text/javascript">
        
		        d = new dTree('d','${pageContext.request.contextPath}/js/checkdtree/images/system/menu/');
		        d.config.folderLinks=true;
                d.config.useCookies=false;
                d.config.check=true;
		        
		        d.add(0,-1,"模块","javascript:callback('');");
		        //d.add(1,0,'模块',"javascript:callback(2);");
	         	<c:forEach items="${dtree }" var="t">
		         	<c:choose>
		             <c:when test="${t.parentId>-1}">
		             	d.add(${t.id},${t.parentId},"${t.name}","javascript:;");
		             </c:when>
		             <c:otherwise>
		             d.add(${t.id},0,'${t.name}',"javascript:;");
		             </c:otherwise>
		            </c:choose>
	     		</c:forEach>
	     		document.getElementById('moduleTree').innerHTML = d;
                d.openAll();
	     		
		        //$("#moduleTree").html(d.toString());
		        
		        function subRoleForm(){
                    var rs="";
                    $("input[name='rm']").each(function (){
                        if(this.checked&&this.value!=0){
                        	if(rs=="")
                            	rs = this.value;
                        	else
                        		rs = rs + ","+this.value;
                        }
                    });
                    $("#moduleId").val(rs);
                    var Url="/Gift/role/addObj";
            		$.ajax({
                        cache: false,//是否读取缓存
                        type: "POST",
                        url:Url,
                        data:$('#roleForm').serialize(),// 你的formid
                        async: true,
                        dataType:"text",
                        success: function(data) {
                        	//alert("保存成功！");
                            $(".content").html(data);
                        },error: function(error) {
                            alert(error);
                        }
                    });
                    //$("#rs").val(rs);
                    //$(document.forms[0]).submit();
                    //alert(rs);
                }
		        
    			</script>
                    
                  <div class="box-footer">
                    <input type="button" onClick="subRoleForm()" class="btn btn-primary" value="保存"/>
                    <input type="button" onClick="backTo('role')" class="btn bg-olive" value="返回"/>
                  </div>
                </form>
              </div><!-- /.box -->
              <div id="success"></div>

</body>
</html>