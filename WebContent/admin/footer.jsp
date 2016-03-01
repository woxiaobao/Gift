<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="box-footer clearfix">
			<small class="">共：${page.totalCount }&nbsp;条信息/共：${page.totalPage }页/当前是第&nbsp;${page.currentPage }&nbsp;页</small>
			<ul class="pagination pagination-sm no-margin pull-right">
				<c:if test="${page.hasPrePage }">
					<li><a href="javascript:menuTo('${menu }','${page.currentPage-1 }')">&laquo;</a></li>
					<li><a href="javascript:menuTo('${menu }','${page.currentPage-1 }')">${page.currentPage-1 }</a></li>
				</c:if>
				<li class="active"><a href="javascript:menuTo('${menu }','${page.currentPage }')">${page.currentPage }</a></li>
				<c:if test="${page.hasNextPage }">
					<li><a href="javascript:menuTo('${menu }','${page.currentPage+1 }')">${page.currentPage+1 }</a></li>
					<li><a href="javascript:menuTo('${menu }','${page.currentPage+1 }')">&raquo;</a></li>
				</c:if>
			</ul>
		</div>
</body>
</html>