var baseUrl = "ws://127.0.0.1:12345";	

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
			window.dialog = channel.objects.dialog;
			//网页关闭函数
			window.onbeforeunload = function() {
				dialog.get_actionType("closeSignal");
			}
			window.onunload = function() {
				dialog.get_actionType("closeSignal");
			}
			//反初始化
			/*
			document.getElementById("closeHtml").onclick = function() {
				dialog.get_actionType("closeSignal");
				var element = document.getElementById("bigPriDev");
				element.src = "";
				var element1 = document.getElementById("bigSubDev");
				element1.src = "";							
			};
			//初始化
			
			document.getElementById("openHtml").onclick = function() {
				dialog.html_loaded("two");			
			};
			
			//网页控件事件，模式列表点击
			document.getElementById("priModelList").onchange = function() {
				//清除展示信息
				var resolutionList = document.getElementById("priResolutionList");
				resolutionList.options.length = 0;
				var select = document.getElementById("priModelList");
				dialog.devChanged("primaryDev_:" + select.value);
			};
			//网页控件事件，分辨率列表点击
			document.getElementById("priResolutionList").onchange = function() {
				//清除展示信息
				var select = document.getElementById("priResolutionList");
				dialog.devChanged("primaryDev_:" + select.value);
			};
			//网页控件事件，模式列表点击
			document.getElementById("subModelList").onchange = function() {
				//清除展示信息
				var resolutionList = document.getElementById("subResolutionList");
				resolutionList.options.length = 0;
				var select = document.getElementById("subModelList");
				dialog.devChanged("subDev_:" + select.value);
			};
			//网页控件事件，分辨率列表点击
			document.getElementById("subResolutionList").onchange = function() {
				//清除展示信息
				var select = document.getElementById("subResolutionList");
				dialog.devChanged("subDev_:" + select.value);
			};
			//设置尺寸列表点击，只有主头有设置尺寸
			document.getElementById("setScanSize").onchange = function() {
				var select = document.getElementById("setScanSize");
				if(select.value == "原始尺寸")
				{
					dialog.get_actionType("setScanSize_ori");
				}
				if(select.value == "A5")
				{
					dialog.get_actionType("setScanSize_A5");
				}
				if(select.value == "卡片")
				{
					dialog.get_actionType("setScanSize_card");
				}
			};
			
			//打开主视频
			document.getElementById("openPriVideo").onclick = function() {
				var resolutionList = document.getElementById("priResolutionList");
				resolutionList.options.length = 0;
				var modelList = document.getElementById("priModelList");
				modelList.options.length = 0;
				var label1 = document.getElementById("lab1").innerHTML;
				dialog.devChanged("primaryDev_:" + label1);
			};
			//关闭主视频
			document.getElementById("closePriVideo").onclick = function() {
				dialog.get_actionType("closePriVideo");	
				var element = document.getElementById("bigPriDev");
				element.src = "";	
			};
			//打开副视频
			document.getElementById("openSubVideo").onclick = function() {
				var resolutionList = document.getElementById("subResolutionList");
				resolutionList.options.length = 0;
				var modelList = document.getElementById("subModelList");
				modelList.options.length = 0;
				var label1 = document.getElementById("lab2").innerHTML;
				dialog.devChanged("subDev_:" + label1);
			};
			//关闭副视频
			document.getElementById("closeSubVideo").onclick = function() {
				dialog.get_actionType("closeSubVideo");	
				var element = document.getElementById("bigSubDev");
				element.src = "";	
			};
			
			
			//副摄像头拍照按钮点击
			document.getElementById("photographSub").onclick = function() {
				dialog.photoBtnClicked("subDev_");
				dialog.get_actionType("savePhotoSubDev");
			};
			*/
			
			//主摄像头拍照按钮点击
			document.getElementById("photographPri").onclick = function() {
				//dialog.get_actionType("setdeskew");	
				dialog.photoBtnClicked("primaryDev_");
				//dialog.get_actionType("savePhotoPriDev");
				//dialog.get_actionType("setdeskew");	
			};
			
			//左转
//				document.getElementById("rotateLeft").onclick = function() {
//					dialog.get_actionType("rotateLeft");
//				};
			//右转
//				document.getElementById("rotateRight").onclick = function() {
//					dialog.get_actionType("rotateRight");
//				};
			//属性设置
//			document.getElementById("showProperty").onclick = function() {
//				dialog.get_actionType("showProperty");
//			};
			//纠偏裁边
//				document.getElementById("setdeskew").onclick = function() {
//				dialog.get_actionType("setdeskew");	
//				};
			//二代证阅读
//				document.getElementById("startIDCard").onclick = function() {
//					dialog.get_actionType("startIDCard");	
//				};
			//停止二代证阅读
//				document.getElementById("stopIDCard").onclick = function() {
//					dialog.get_actionType("stopIDCard");	
//				};
			//单次二代证阅读
//			document.getElementById("singleReadIDCard").onclick = function() {
//				dialog.get_actionType("singleReadIDCard");	
//			};
			//人证比对
//				document.getElementById("verifyFaceDetect").onclick = function() {
//					dialog.get_actionType("verifyFaceDetect");
//				};
			//获取二代证图像 
			//Flag 1表示头像，2表示正面，3表示反面，4表示正反垂直合成，
			//5表示反正垂直合成，6表示正反水平合成，7表示反正水平合成
//			document.getElementById("getIdcardImage").onclick = function() {
//				dialog.get_functionTypes("getIdcardImage", "5", "", "");
//			};
			/*********************活体功能*******************************************/
			//启动活体识别
//				document.getElementById("startLive").onclick = function() {
//					dialog.get_actionType("startLive");
				
//				};
			//关闭活体识别
//				document.getElementById("stopLive").onclick = function() {
//					dialog.get_actionType("stopLive");
				
//				};
			
			
			
			//ftp服务器创建目录，多层目录需要一层一层创建
//				document.getElementById("createFTPDir").onclick = function() {
				//第2个参数为ftp服务器url，第3个参数为需要创建的路径，第4个为空
				//ftp带用户名密码格式：ftp://liuliangjin:123456@192.168.1.162:21
//					dialog.get_functionTypes("createFTPDir", "ftp://192.168.1.16:2121", "/first", "");
//					dialog.get_functionTypes("createFTPDir", "ftp://192.168.1.16:2121", "/first/second", "");
				
//				};
			//ftp上传按钮点击
//				document.getElementById("ftpUpload").onclick = function() {
				//ftp带用户名密码格式：ftp://liuliangjin:123456@192.168.1.162:21
				//第2个参数为url，第3个为本地文件地址，第4个为上传后的地址/文件名
				//dialog.get_functionTypes("createFTPDir", "ftp://192.168.1.16:2121", "/11", "");
				//dialog.get_functionTypes("ftpUpload", "ftp://192.168.1.16:2121", "d:\\Com接口文档.pdf", "/11/Com接口文档.pdf");
//					dialog.get_functionTypes("ftpUpload", "ftp://192.168.1.16:2121", "d:\\idface.jpg", "idface.jpg");
//				};
			//删除本地文件
	//		document.getElementById("deleteFile").onclick = function() {
				//dialog.get_functionTypes("deleteFile", "C:\\Users\\Administrator\\Desktop\\eloamPhoto\\20180903-200102046.jpg", "", "");
				//dialog.get_functionTypes("deleteFile", "C:/Users/Administrator/Desktop/eloamPhoto/eeee.jpg", "", "");
	//			for (var i = 0; i < imgPathArray.length; i++) 
	//			{ 
				//删除文件，第2个参数：图片文件路径，第3，第4个参数为空
	//				var path = imgPathArray[i];
	//				if(path.indexOf("file:///") >= 0)
	//				{
	//					path = path.substr(8);
	//				}
	//				dialog.get_functionTypes("deleteFile", path, "", "");
	//			}
	//			removeAll();
				//imgPathArray = [];
	//		};
		
			
			//服务器返回消息
			dialog.sendPrintInfo.connect(function(message) {
				//读取二代证返回信息
				if(message.indexOf("idFaceInfo:") >= 0)
				{
					var value = message.substr(11);
					var element = document.getElementById("devPhoto2");
					element.src = "data:image/jpg;base64," + value;	
					return;
				}
				output(message);
				
				/********主头设备信息***********/
				//设备名
//				if(message.indexOf("priDevName:") >= 0)
//				{
//					message = message.substr(11);
//					var label = document.getElementById("lab1");
//					label.innerHTML = message;
//				}
				//副头设备分辨率
//				else if(message.indexOf("subResolution:") >= 0)
//				{
//					message = message.substr(14);
//					var select = document.getElementById("subResolutionList");
//					select.add(new Option(message));
//					if(select.options.length > 2)
//					{
//						select.selectedIndex = 1;
//					}
//				}
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
				element.src = "data:image/jpg;base64," + message;							
			});
			
			//接收拍照base64，主头数据
			dialog.send_priPhotoData.connect(function(message) {
				var element = document.getElementById("devPhoto");
				element.src = "data:image/jpg;base64," + message;	

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