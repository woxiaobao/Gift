<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app>
<head>

<script src="${pageContext.request.contextPath}/js/angular.js"></script>
 <!-- 
<script src="http://ngnice.com/lib/angular/1.2.16/angular.js"></script>
<script src="http://ngnice.com/lib/angular/1.2.16/angular.min.js"></script>
-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div ng-controller="testCtrl">
         <label>Name:</label>
         <input type="text" ng-model="yourName" placeholder="Enter a name here">
         <button ng-click="clearInputValue()">Clear Input Value</button>
         <button ng-click="ajaxValue()">ajax test</button>
         <hr>
         <h1>Hello {{yourName}}!</h1>
		<div ng-model="main"></div>
		<input type="checkbox" ng-model="showHide1">Show Button
		<button ng-show="showHide1">Click Me!</button><br>
		
		<input type="checkbox" ng-model="enableDisableButton">Disable Button
		<button ng-disabled="enableDisableButton">Click Me!</button><br>
		
		<input type="checkbox" ng-model="showHide2">Hide Button
		<button ng-hide="showHide2">Click Me!</button><br>
		
		<p>Total click: {{ clickCounter }}</p></td>
		<button ng-click="clickCounter = clickCounter + 1">Click Me!</button><br>
		
		
     </div>
     
     
     <script>
         function testCtrl($scope,$http) {
             $scope.yourName = "world";
             $scope.clearInputValue = function () {
                 $scope.yourName = "";
             }
             $scope.ajaxValue=function(){
            	 $http({method : 'POST',params : { id:'123'}, data:{name:'john',age:27}, url : "/Gift/user/testAjax"})
            	 .success(function(response, status, headers, config){
            	 	//do anything what you want;
            	 	//alert(response);
            	 	$scope.main = response;
            	 })
            	 .error(function(response, status, headers, config){
            	 	//do  anything what you want;
            	 	alert(status);
            	 });
             }
         }
         
         
     </script>

</body>
</html>