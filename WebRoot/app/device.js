var baseUrl = "ws://127.0.0.1:12345";	

var dialog;

function openSocket() {				
	socket = new WebSocket(baseUrl);
	socket.onclose = function()
	{
		console.error("web channel closed");
	};
	socket.onerror = function(error)
	{
		console.error("web channel error: " + error);
	};
	socket.onopen = function()
	{
		output("WebSocket connected");
		new QWebChannel(socket, function(channel) {
			//获取注册的对象
			dialog = channel.objects.dialog;
			//网页关闭函数
			window.onbeforeunload = function() {
				dialog.get_actionType("closeSignal");
			}
			window.onunload = function() {
				dialog.get_actionType("closeSignal");
			}
			//反初始化
			
			//主摄像头拍照按钮点击
			document.getElementById("photographPri").onclick = function() {
				//dialog.get_actionType("setdeskew");	
				dialog.photoBtnClicked("primaryDev_");
				//dialog.get_actionType("savePhotoPriDev");
				//dialog.get_actionType("setdeskew");	
			};
		
			
			//服务器返回消息
			dialog.sendPrintInfo.connect(function(message) {
				//读取二代证返回信息
				/*
				if(message.indexOf("idFaceInfo:") >= 0)
				{
					var value = message.substr(11);
					var element = document.getElementById("devPhoto2");
					element.src = "data:image/jpg;base64," + value;	
					return;
				}
				*/
				
				if(message.indexOf("IDcardInfo") >= 0)
				{
					var dot = message.indexOf(":");
					if(dot >= 0)
					{
						document.getElementById("blrname").value = message.substr(dot + 1);
						var strs = new Array(); //定义一数组
						strs = message.substr(dot + 1).split(" "); //字符分割
						
						queryresident(strs[7]);
					}
				}
				output(message);
				
				
				//图片保存后返回路径关键字savePhoto_success:
				if(message.indexOf("savePhoto_success:") >= 0)
				{
					imgPath = message.substr(18);
					addImgDiv();
				}
			
			});
			//接收图片流用来展示，多个，较小的base64，主头数据
			dialog.send_priImgData.connect(function(message) {
				var element = document.getElementById("bigPriDev");
				if(element != null){
					element.src = "data:image/jpg;base64," + message;							
				}
			});
			
			//接收拍照base64，主头数据
			dialog.send_priPhotoData.connect(function(message) {
				var element = document.getElementById("devPhoto");
				if(element != null){
					element.src = "data:image/jpg;base64," + message;	
				}

				//console.log(element);
				//上传图像
				uploadimage(message);
				console.log(message);
			});
			
			//output("ready to send/receive messages!");
			//网页加载完成信号
			dialog.html_loaded("two");
			//dialog.html_loaded("faceDetect_two");
		});
	}
}

function openSocket2() {				
	socket = new WebSocket(baseUrl);
	socket.onclose = function()
	{
		console.error("web channel closed");
	};
	socket.onerror = function(error)
	{
		console.error("web channel error: " + error);
	};
	socket.onopen = function()
	{
		output("WebSocket connected");
		new QWebChannel(socket, function(channel) {
			//获取注册的对象
			dialog = channel.objects.dialog;
			//网页关闭函数
			window.onbeforeunload = function() {
				dialog.get_actionType("closeSignal");
			}
			window.onunload = function() {
				dialog.get_actionType("closeSignal");
			}
			//反初始化
			
			//主摄像头拍照按钮点击
			document.getElementById("photographPri").onclick = function() {
				//dialog.get_actionType("setdeskew");	
				dialog.photoBtnClicked("primaryDev_");
				//dialog.get_actionType("savePhotoPriDev");
				//dialog.get_actionType("setdeskew");	
			};
		
			
			//服务器返回消息
			dialog.sendPrintInfo.connect(function(message) {
				//读取二代证返回信息
				/*
				if(message.indexOf("idFaceInfo:") >= 0)
				{
					var value = message.substr(11);
					var element = document.getElementById("devPhoto2");
					element.src = "data:image/jpg;base64," + value;	
					return;
				}
				*/
				
				if(message.indexOf("IDcardInfo") >= 0)
				{
					var dot = message.indexOf(":");
					if(dot >= 0)
					{
						document.getElementById("blrname").value = message.substr(dot + 1);
						var strs = new Array(); //定义一数组
						strs = message.substr(dot + 1).split(" "); //字符分割
						
						$('#name').val(strs[0]);
						$("input[name='sex'][value=" + strs[1] + "]").attr("checked",true);
						$('#nation').val(strs[2]);
						$('#birthday').val(strs[3] + strs[4] + strs[5]);
						$('#residenceaddress').val(strs[6]);
						$('#idnumber').val(strs[7]);
					}
				}
				output(message);
				
				
				//图片保存后返回路径关键字savePhoto_success:
				if(message.indexOf("savePhoto_success:") >= 0)
				{
					imgPath = message.substr(18);
					addImgDiv();
				}
			
			});
			//接收图片流用来展示，多个，较小的base64，主头数据
			dialog.send_priImgData.connect(function(message) {
				var element = document.getElementById("bigPriDev");
				if(element != null){
					element.src = "data:image/jpg;base64," + message;							
				}
			});
			
			//接收拍照base64，主头数据
			dialog.send_priPhotoData.connect(function(message) {
				var element = document.getElementById("devPhoto");
				if(element != null){
					element.src = "data:image/jpg;base64," + message;	
				}

				//console.log(element);
				//上传图像
				uploadimage(message);
				console.log(message);
			});
			
			//output("ready to send/receive messages!");
			//网页加载完成信号
			dialog.html_loaded("two");
			//dialog.html_loaded("faceDetect_two");
		});
	}
}

//主摄像头拍照
function doPhoto1(){
}

//副摄像头拍照
function doPhoto2(){
}

//读取身份证
function doIdCard(){
	if(dialog  == null){
		jError("高拍仪正在初始化，请重新点击!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'});
		return;
	}
	dialog.get_actionType("singleReadIDCard");
}

//查询居民信息
function queryresident(idnumber){
	$.get(getContextPath()+"/idcardController/queryresident?idnumber="+idnumber,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				var val = $('#blr').val();
				if(val == ''){
					$('#blr').val(obj.data.id);
				}
				else {
					$('#blr').val(val + "," + obj.data.id);
				}
				
				val = $('#blrname').val();
				if(val == ''){
					$('#blrname').val(obj.data.blrname);	
				}
				else {
					$('#blrname').val(val + "," + obj.data.name);
				}
				
				val = $("#lxdh").val();
				if(val == ''){
					$('#lxdh').val(obj.data.mobile);
				}
				else {
					$('#lxdh').val(val + "," + obj.data.mobile);
				}
			}
		});
}

function uploadimage(img){
	$.post(getContextPath()+"/fileController/uploadfilestring",
	{
		imgstr:img
	},
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			jSuccess("图片上传成功!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'});
				
			var header = getContextPath()+"/fileController/download?fileName=";
			var url = header + obj.fileName;
			var val = $('#pictures').val();
			$( '#pictures').val(val + VALUE_SPLITTER+ obj.fileName);
						
			$('#picturespicktable').append('<tr><td><a href="' + url + '" data-lightbox="' + obj.fileName + '" data-title="' + obj.fileName + '" style="color:#64A600; font-size: 12px;">'+obj.fileName+'</a></td><td>已上传</td>'+
					"<td><button type='button' id='Button' class='btn btn-success btn-xs' onclick='javascript:downloadAttach('" + obj.fileName + "');return false;'><i class='fa fa-check'></i></button></td>"+
					'</tr>');
			
			$("#deviceDialog").dialog("close");
		}
		else
		{
			jError("图片上传失败!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'});
			return ;
		}
	});
}

//输出信息
function output(message){
	var output = document.getElementById("output");
	output.innerHTML = output.innerHTML + message + "\n";
	output.scrollTop = output.scrollHeight;				
}