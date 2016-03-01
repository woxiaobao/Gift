<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh-cn" ng-app="TimeFormat">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>time-format</title>
    <script src="${pageContext.request.contextPath}/js/angular.js"></script>
</head>
<body>


<div ng-controller="MyCtrl" id="main">
    Date format: <input ng-model="format" type="text"/><hr/>
    <!--下面使用属性x-current-time，是为了试试上面说的合法命名~~current:time、current-time、current_time、data-current-time -_-!!! -->
    Current time is : <span x-current-time="format" id="myFormat"></span><br/>
    <button ng-click="remove()">remove the span</button>
    <div id="add">
    	<span>{{value}}123123123</span>
    	<span>666666666666666</span>
    	<span>777777777</span>
    </div>
</div>

 <div ng-controller="ExampleController">
  <input Type="text" ng-model="greeting" />
  <button ng-click="doGreeting()">ALERT</button>
  </div>

<form name="form" novalidate>
<label name="email">Your email</label>
<input type="email"
name="email"
ng-model="email" placeholder="Email Address" />
</form>
<br>
<input type="text" ng-model="someProperty" placeholder="TypetoEnable">
<button ng-model="button" ng-disabled="!someProperty">AButton</button>

<script type="text/javascript">
    angular.module("TimeFormat", [])
        //在TimeFormat应用中注册“currentTime”这个directive的工厂方法
        //前文提到过，依赖注入，可以直接在function的参数中写入，这里注入了$timeout、dataFilter
            .directive("currentTime", function (dateFilter) {
                //这个是上面提到的linking function。(不需要添加compile function，为啥？。。。)
                return function (scope, element, attr) {
                    var intervalId;
                    //更新对应element的text值，即更新时间
                    function updateTime() {
                        element.text(dateFilter(new Date(), scope.format));
                    }
                    //通过watch，监控span对象的currentTime的值(是format这个model值，即input的值！！)
                    //这个方法仅仅在format发生改变的时候执行
                    scope.$watch(attr.currentTime, function (value) {
                        scope.format = value;
                        updateTime();
                    });
                    //当span被去掉的时候，取消更新
                    element.bind("$destroy", function () {
                        clearInterval(intervalId);
                    });

                    intervalId = setInterval(updateTime, 1000);
                };
            })
            .controller("MyCtrl",function($scope,$rootScope) {
                $scope.format = "M/d/yy h:mm:ss a";
                $scope.value="woshixiaobao";
                $scope.remove = function() {
                    var oFormat = document.getElementById("myFormat");
                    var add = document.getElementById("add");
                    if(oFormat) {
                    	angular.element(add).attr("name","baolin");
                    	var name=angular.element(add).attr("name");
                    	alert(name);
                        angular.element(oFormat).remove();//通过这种方式调用remove，可以触发$destroy事件啊！！！试了我N久。。。
                    }
                };
            })
            .controller('ExampleController', function ($scope, $window){
                $scope.greeting = 'Hello, World!';
                $scope.doGreeting = function() {
                  $window.alert('greeting');
                };
              });
</script>


</body>
</html>
