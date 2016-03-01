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
                <form  name="moduleForm" id="moduleForm" method="post">
                  <div class="box-body">
                  <input type="hidden" name="parentId" id="" value="${sid }">
                    <div class="form-group">
                      <label for="">模块名称</label>
                      <input type="text" class="form-control" name="name" id="" placeholder="name">
                    </div>
                    
                    <div class="form-group">
                      <label for="">权限名</label>
                      <input type="text" class="form-control" name="permissions" id="" placeholder="permissions">
                    </div>
                    

                  <div class="box-footer">
                    <input type="button" onClick="objSave('module')" class="btn btn-primary" value="保存"/>
                    <input type="button" onClick="backTo('module')" class="btn bg-olive" value="返回"/>
                  </div>
                </form>
              </div><!-- /.box -->
              <div id="success"></div>

</body>
</html>