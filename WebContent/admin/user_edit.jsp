<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- general form elements -->
              <div class="box box-primary">
                <div class="box-header">
                  <h3 class="box-title">Quick Example</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form  name="userForm" id="userForm" method="post">
                	<input type="hidden" name="id" id="id" value="${user.id }">
                	<input type="hidden" name="dateCreated" value="${user.dateCreated }">
                  <div class="box-body">
                    <div class="form-group">
                      <label for="">称呼</label>
                      <input type="text" class="form-control" name="nickname" id="" value="${user.nickname }" placeholder="Enter email">
                    </div>
                    
                    <div class="form-group">
                      <label for="">用户名</label>
                      <input type="text" class="form-control" name="userName" value="${user.userName }" id="" placeholder="Enter email">
                    </div>
                    
                    <div class="form-group">
                      <label for="">密码</label>
                      <input type="password" class="form-control" name="password" value="${user.password }" id="" placeholder="Enter email">
                    </div>
                    
                    <div class="form-group">
                      <label for="exampleInputEmail1">Email address</label>
                      <input type="email" class="form-control" name="email" value="${user.email }" id="" placeholder="Enter email">
                    </div>
                    
                    <div class="form-group">
                      <label for="">电话</label>
                      <input type="text" class="form-control" name="phone" value="${user.phone }" id="" placeholder="Enter email">
                    </div>
                    
                    <div class="form-group">
                      <label for="">角色</label>
                      <select id="roleid" class="form-control" ng="false"  name="roleid" >
                      	<option value="${user.role.id }" selected=true>${user.role.roleName }</option>
					  </select>
                    </div>
                    
                    <div class="form-group">
                      <label for="exampleInputFile">File input</label>
                      <input type="file" id="exampleInputFile">
                      <p class="help-block">Example block-level help text here.</p>
                    </div>
                    
                    <div class="checkbox">
                      <label>
                        <input type="checkbox"> Check me out
                      </label>
                    </div>
                  </div><!-- /.box-body -->

                  <div class="box-footer">
                    <input type="button" onClick="objSaveEdit('user')" class="btn btn-primary" value="保存"/>
                    <input type="button" onClick="backTo('user')" class="btn bg-olive" value="返回"/>
                  </div>
                </form>
              </div><!-- /.box -->
              <div id="success"></div>
				
				<script type="text/javascript">
              var rid="${user.role.id }";
              
             
              
              $(function(){
            	  toJson();
              })
              function toJson(){
          		//alert(id);
          		var ng=$("#roleid");
          		if(ng.attr("ng")=="true"){
          			return;
          		}
          		ng.attr("ng","true");
          		var Url="/Gift/role/toJson";
          		$.ajax({
                      cache: true,//是否读取缓存
                      type: "POST",
                      url:Url,
                      data:{},
                      async: true,
                      dataType:"text",
                      success: function(data) {
                      	//alert(data);
                      	//var obj = data.parseJSON();
                      	var obj = JSON.parse(data);
                      	var str="";
                      	for(var item in obj){
                      		//alert(obj[item].id);
                      		str+="<option value='"+obj[item].id+"'>"+obj[item].roleName+"</option>";
                      	}
                      	ng.html(str);
                      	
                      	//menuTo(menu,currentPage);
                      },error: function(error) {
                          alert(error);
                      }
                  });
          	}
              
              
              </script>
				
</body>
</html>