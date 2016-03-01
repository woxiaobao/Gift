/**
 * @author lvbaolin
 * @date 2015-06-15
 * @version 1.0
 */


	//全局变量 设定定当前位置 如：用户列表页
	var $active=$("#homePath");
	/**
	 * 左边导航触发的事件
	 * @param menu 是哪一个模块如：user
	 */
	function menuTo(menu,currentPage){
		var m=$("#"+menu).attr(menu+"_list");
		$active.html(m);
		var Url="/Gift/"+menu+"/toList";
		var data={}
		if(currentPage!="1"){
			Url="/Gift/"+menu+"/toPageList";
			data.currentPage=currentPage;
		}
		$.ajax({
            cache: false,//是否读取缓存
            type: "POST",
            url:Url,
            data:data,
            async: true,
            //dataType:"html",
            success: function(data) {
                $(".content").html(data);
            },error: function(error) {
                alert("500-发生错误！");
            }
        });
	}
	
	/**
	 * 点击新增触发事件
	 * @param menu 是哪一个模块如：user
	 */
	function toAdd(menu){
		var m=$("#"+menu).attr(menu+"_add");
		$active.html(m);
		var Url="/Gift/"+menu+"/toAdd";
		$(".content").load(Url,{},function(responseTxt,statusTxt,xhr){});
		//send("user,add");
		//alert("dialog");
		//$("#dialog").dialog();
		
	}
	
	/**
	 * 新增提交表单
	 * @param menu 是哪一个模块如：user
	 */
	function objSave(menu){
		var Url="/Gift/"+menu+"/addObj";
		$.ajax({
            cache: false,//是否读取缓存
            type: "POST",
            url:Url,
            data:$('#'+menu+'Form').serialize(),// 你的formid
            async: true,
            dataType:"text",
            success: function(data) {
                $(".content").html(data);
                //send("测试websocket");//webSocket推送
                send("user,add");
            },error: function(error) {
                alert(error);
            }
        });
	}
	
	/**
	 * 编辑提交表单
	 * @param menu 是哪一个模块如：user
	 */
	function objSaveEdit(menu){
		var Url="/Gift/"+menu+"/editObj";
		$.ajax({
            cache: false,//是否读取缓存
            type: "POST",
            url:Url,
            data:$('#'+menu+'Form').serialize(),// 你的formid
            async: true,
            dataType:"text",
            success: function(data) {
                $(".content").html(data);
            },error: function(error) {
                alert(error);
            }
        });
	}
	
	/**
	 * 点击编辑触发事件
	 * @param menu 是哪一个模块如：user
	 * @param id 对象id
	 */
	function objEdit(menu,id){
		var m=$("#"+menu).attr(menu+"_edit");
		$active.html(m);
		var Url="/Gift/"+menu+"/toEdit";
		$.ajax({
            cache: false,//是否读取缓存
            type: "POST",
            url:Url,
            data:{'id':id},
            async: true,
            dataType:"text",
            success: function(data) {
                $(".content").html(data);
            },error: function(error) {
                alert(error);
            }
        });
	}
	
	/**
	 * 点击删除触发事件
	 * @param menu 是哪一个模块如：user
	 * @param id 对象id
	 * @param currentPage 当前页
	 */
	function objDel(menu,id,currentPage){
		//alert(id);
		var Url="/Gift/"+menu+"/delObj";
		$.ajax({
            cache: true,//是否读取缓存
            type: "POST",
            url:Url,
            data:{'id':id},
            async: true,
            dataType:"text",
            success: function(data) {
            	menuTo(menu,currentPage);
            },error: function(error) {
                alert(error);
            }
        });
		
	}
	
	/**
	 * 返回
	 * @param menu
	 */
	function backTo(menu){
		menuTo(menu,1);
	}
	
	
	
	function clock(){
		$("#dialog").css("display","none");
	}
	
	
	
	/**
	 * 以下是websocket
	 * 
	 * */
	
	var socket;
	if (!window.WebSocket) 
	{
		window.WebSocket = window.MozWebSocket;
	}
	if (window.WebSocket) {
		socket = new WebSocket("ws://192.168.1.186:8081/gift");
		socket.onmessage = function(event) {
			//var ta = document.getElementById('responseText');
			//ta.value="";
			//alert(event.data);
			$("#massegelist").html("<li>"+event.data+"</li>");
			$("#dialog").css("display","block");
			
		};
		socket.onopen = function(event) {
			//var ta = document.getElementById('responseText');
			//ta.value = "打开WebSocket服务正常，浏览器支持WebSocket!";
			alert("打开WebSocket服务正常，浏览器支持WebSocket!");
		};
		socket.onclose = function(event) {
			//var ta = document.getElementById('responseText');
			//ta.value = "";
			//ta.value = "WebSocket 关闭!"; 
			alert("WebSocket 关闭!");
		};
	}
	else
		{
		alert("抱歉，您的浏览器不支持WebSocket协议!");
		}

	function send(message) {
		if (!window.WebSocket) { return; }
		if (socket.readyState == WebSocket.OPEN) {
			socket.send(message);
		}
		else
			{
			  alert("WebSocket连接没有建立成功!");
			}
	}
	
	
	