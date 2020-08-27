

$(document).ready(function (){
	
	$('#btnAdd').click(ShowAddModal);
	
	$('.dpYears').datepicker({
		autoclose: true
	});
	
	//$('#btnReset').click(Reset);
	
	$('#btnSearch').click(load);
	
	//load();
	
	$("#characterQry").select2({	 
		multiple: true
	});
	
	$("#characterQry").val('abc').trigger("change"); //赋值一个不存在的value,解决默认选择第一个的问题
	
	if(curId != '')
		viewDetail(curId);
	
	try{
		TV_Initialize();
	}
	catch(error)
	{
		debugger;
		console.log('Failed to initialize...');
	}
	
	getEvent(T_GetEvent);
	
	loadcategory();
});

function loadcategory()
{
	$.get(getContextPath()+"/utilController/getsxsqsjdl",
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				for(var i = 0; i < obj.list.length; i++){
					var content = "<option value='" + obj.list[i].sxdl + "'>" + obj.list[i].sxdl + "</option>"
					$("#category").append(content);
				}
			}
		});
}

function loadtelxl()
{
	$('#telxl option').not(":first").remove(); 
	var dl = $("#category").val();
	if(dl == ''){
		return;
	}
	
	$.get(getContextPath()+"/utilController/getsxsqsjxl?sxdl=" + dl,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				for(var i = 0; i < obj.list.length; i++){
					var content = "<option value='" + obj.list[i].sxxl + "'>" + obj.list[i].sxxl + "</option>"
					$("#telxl").append(content);
				}
			}
		});
}

/*$(window).unload(function(){ 
	TV_Disable(); 
});*/

//离开页面
$(window).bind('beforeunload',function(){
	TV_Disable();
});

function viewDetail(id)
{
	$.get(getContextPath()+"/sysTelPublishController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				$('#title').val(obj.title);
				$('#category').val(obj.category);
				$('#telxl').val(obj.telxl);
				$('#content').val(obj.content);
				var audioArr = obj.audio.split(VALUE_SPLITTER);				for(var j=0;j<audioArr.length;j++)				{					if(audioArr[j] != '')					{						$('#audiopicktable').append('<tr><td>'+audioArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+audioArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					}				}				$('#target').val(obj.target);
				$('#publishtime').val(obj.publishtime);
				$('#status').val(obj.status);

					
			}
		});
}

function gobackPage()
{
	curId = '';
	
	$('#main-content').load("./sys/sys_tel_publish/sys_tel_publish.html", function () {
		
    });
	
}
/*
function ShowAddModal()
{
	$('#modalDetail').show();
	
	$('#modalTitle').text('新增');
	
	$('#addOrUpdateBtn').text('确定');
	
}
*/

var sleep = function(time) {
    var startTime = new Date().getTime() + parseInt(time, 10);
    while(new Date().getTime() < startTime) {}
};





var curPlayInprogress = false;

var canPlay = false;

var audioLocalPath = '';

var targetArr = [];

var curTargetIndex = 0;

function addOrUpdate()
{
	var residentList = $('#realMobileList').val();
	var internalMobileList = $('#internalMobileList').val();
	
	var target = residentList;
	
	if(target != null && mobileList != '')
		target += ','+internalMobileList;
	
	//var target = $('#target').val();
	
	audioLocalPath = $('#audioLocalPath').val();
	
	targetArr = target.split(',');
	
	debugger;
	/*
	for(var i=0;i<targetArr.length;i++)
	{
		canPlay = false;
		
		TV_StartDial(0,targetArr[i]);
	
		for(var k=0;k<60;k++)//接通等待60秒,通常运营商设置的超期时间为45秒
		{
			console.log('wait for end user to pick up');
			sleep(1000);
			
			if(canPlay == true)
				break;
		}
	
		TV_StartPlayFile(0,audioLocalPath);
		
		curPlayInprogress = true;
		
		for(var k=0;k<60;k++)//最多等待1分钟
		{
			console.log('wait for the audio to play');
			sleep(1000);
			
			if(curPlayInprogress == false)
				break;
		}
		
		//TV_StopPlayFile(0);
		
		TV_HangUpCtrl(0);
	}
	*/
	
	telPublish();
}

function postDataToServer()
{
	$.post(getContextPath()+"/sysTelPublishController/addOrUpdate",
	{
		id:curId,
		title:$('#title').val(),
		category:$('#category').val(),
		telxl:$('#telxl').val(),
		content:$('#content').val(),
		audio:$('#audio').val(),
		target:$('#target').val(),
		publishtime:$('#publishtime').val(),
		status:$('#status').val()
	},
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			jSuccess("数据修改成功!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
			
			gobackPage();
			
			//load();
		}
		else
		{
			jError("数据修改失败!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
		}
	});
}

function telPublish()
{
	if(curTargetIndex == targetArr.length)
	{
		TV_Disable();
		
		postDataToServer();
		
		return ;
	}
	
	var target = targetArr[curTargetIndex];
	
	curTargetIndex ++;
	
	if(target.indexOf('-') != -1)
		target = target.substring(target.indexOf('-') + 1);
	
	TV_StartDial(0,target);
}

function downloadAttach(fileName)
{
	var url = getContextPath()+"/fileController/download?fileName="+fileName;
	
	window.open(encodeURI(url));
	
	//window.open(getContextPath()+"/fileController/downLoad/"+encodeURI(obj.fileName));
}


function  T_GetEvent(uID,uEventType,uHandle,uResult,szdata)
{
	//var vValueArray=qnviccub.QNV_Event(0,2,0,"","",1024);
	if(uEventType == -1)
		return;
	var vValue=" type="+uEventType+" Handle="+uHandle+" Result="+uResult+" szdata="+szdata;
	switch(uEventType)
	{
	case BriEvent_PhoneHook:// 本地电话机摘机事件
		AppendStatusEx(uID,"本地电话机摘机"+vValue);
	break;	
	case BriEvent_PhoneDial:// 只有在本地话机摘机，没有调用软摘机时，检测到DTMF拨号
		AppendStatusEx(uID,"本地话机拨号"+vValue);
	break;	
	case BriEvent_PhoneHang:// 本地电话机挂机事件
		TV_HangUpCtrl(uID);
		AppendStatusEx(uID,"本地电话机挂机"+vValue);
	break;
	case BriEvent_CallIn:// 外线通道来电响铃事件
		AppendStatusEx(uID,"外线通道来电响铃事件"+vValue);
	break;
	case BriEvent_GetCallID://得到来电号码
		AppendStatusEx(uID,"得到来电号码"+vValue);
	break;
	case BriEvent_StopCallIn:// 对方停止呼叫(产生一个未接电话)
		AppendStatusEx(uID,"对方停止呼叫(产生一个未接电话)"+vValue);
	break;
	case BriEvent_DialEnd:// 调用开始拨号后，全部号码拨号结束
		AppendStatusEx(uID,"调用开始拨号后，全部号码拨号结束"+vValue);
	break;
	case BriEvent_PlayFileEnd:// 播放文件结束事件
		
		TV_HangUpCtrl(0);
		
		telPublish();
		
		AppendStatusEx(uID,"播放文件结束事件"+vValue);
	break;
	case BriEvent_PlayMultiFileEnd:// 多文件连播结束事件
		AppendStatusEx(uID,"多文件连播结束事件"+vValue);
	break;
	case BriEvent_PlayStringEnd://播放字符结束
		AppendStatusEx(uID,"播放字符结束"+vValue);
	break
	case BriEvent_RepeatPlayFile:// 播放文件结束准备重复播放
		AppendStatusEx(uID,"播放文件结束准备重复播放"+vValue);
	break;
	case BriEvent_SendCallIDEnd:// 给本地设备发送震铃信号时发送号码结束
		AppendStatusEx(uID,"给本地设备发送震铃信号时发送号码结束"+vValue);
	break;
	case BriEvent_RingTimeOut://给本地设备发送震铃信号时超时
		AppendStatusEx(uID,"给本地设备发送震铃信号时超时"+vValue);
	break;	
	case BriEvent_Ringing://正在内线震铃
		AppendStatusEx(uID,"正在内线震铃"+vValue);
	break;
	case BriEvent_Silence:// 通话时检测到一定时间的静音.默认为5秒
		AppendStatusEx(uID,"通话时检测到一定时间的静音"+vValue);
	break;
	case BriEvent_GetDTMFChar:// 线路接通时收到DTMF码事件
		//canPlay = true;
		//TV_StartPlayFile(0,audioLocalPath);
		AppendStatusEx(uID,"线路接通时收到DTMF码事件"+vValue);
	break;
	case BriEvent_RemoteHook:// 拨号后,被叫方摘机事件
	
		TV_StartPlayFile(0,audioLocalPath);
	
		AppendStatusEx(uID,"拨号后,被叫方摘机事件"+vValue);
	break;
	case BriEvent_RemoteHang://对方挂机事件
		TV_HangUpCtrl(uID);
		
		telPublish();
		
		AppendStatusEx(uID,"对方挂机事件"+vValue);
	break;
	case BriEvent_Busy:// 检测到忙音事件,表示PSTN线路已经被断开
		TV_HangUpCtrl(uID);
		
		telPublish();
		
		AppendStatusEx(uID,"检测到忙音事件,表示PSTN线路已经被断开"+vValue);
	break;
	case BriEvent_DialTone:// 本地摘机后检测到拨号音
		AppendStatusEx(uID,"本地摘机后检测到拨号音"+vValue);
	break;
	case BriEvent_RingBack:// 电话机拨号结束呼出事件。
		AppendStatusEx(uID,"电话机拨号结束呼出事件"+vValue);
	break;
	case BriEvent_MicIn:// MIC插入状态
		AppendStatusEx(uID,"MIC插入状态"+vValue);
	break;
	case BriEvent_MicOut:// MIC拔出状态
		AppendStatusEx(uID,"MIC拔出状态"+vValue);
	break;
	case BriEvent_FlashEnd:// 拍插簧(Flash)完成事件，拍插簧完成后可以检测拨号音后进行二次拨号
		AppendStatusEx(uID,"拍插簧(Flash)完成事件，拍插簧完成后可以检测拨号音后进行二次拨号"+vValue);
	break;
	case BriEvent_RefuseEnd:// 拒接完成
		AppendStatusEx(uID,"拒接完成"+vValue);
	break;
	case BriEvent_SpeechResult:// 语音识别完成 
		AppendStatusEx(uID,"语音识别完成"+vValue);
	break;
	case BriEvent_FaxRecvFinished:// 接收传真完成
		AppendStatusEx(uID,"接收传真完成"+vValue);
	break;
	case BriEvent_FaxRecvFailed:// 接收传真失败
		AppendStatusEx(uID,"接收传真失败"+vValue);
	break;
	case BriEvent_FaxSendFinished:// 发送传真完成
		AppendStatusEx(uID,"发送传真完成"+vValue);
	break;
	case BriEvent_FaxSendFailed:// 发送传真失败
		AppendStatusEx(uID,"发送传真失败"+vValue);
	break;
	case BriEvent_OpenSoundFailed:// 启动声卡失败
		AppendStatusEx(uID,"启动声卡失败"+vValue);
	break;
	case BriEvent_UploadSuccess://远程上传成功
		AppendStatusEx(uID,"远程上传成功"+vValue);
	break;
	case BriEvent_UploadFailed://远程上传失败
		AppendStatusEx(uID,"远程上传失败"+vValue);
	break;
	case BriEvent_EnableHook:// 应用层调用软摘机/软挂机成功事件
		AppendStatusEx(uID,"应用层调用软摘机/软挂机成功事件"+vValue);
	break;
	case BriEvent_EnablePlay:// 喇叭被打开或者/关闭
		AppendStatusEx(uID,"喇叭被打开或者/关闭"+vValue);
	break;
	case BriEvent_EnableMic:// MIC被打开或者关闭
		AppendStatusEx(uID,"MIC被打开或者关闭"+vValue);
	break;
	case BriEvent_EnableSpk:// 耳机被打开或者关闭
		AppendStatusEx(uID,"耳机被打开或者关闭"+vValue);
	break;
	case BriEvent_EnableRing:// 电话机跟电话线(PSTN)断开/接通
		AppendStatusEx(uID,"电话机跟电话线(PSTN)断开/接通"+vValue);
	break;
	case BriEvent_DoRecSource:// 修改录音源
		AppendStatusEx(uID,"修改录音源"+vValue);
	break;
	case BriEvent_DoStartDial:// 开始软件拨号
		AppendStatusEx(uID,"开始软件拨号"+vValue);
	break;
	case BriEvent_RecvedFSK:// 接收到FSK信号，包括通话中FSK/来电号码的FSK
		AppendStatusEx(uID,"接收到FSK信号，包括通话中FSK/来电号码的FSK"+vValue);
	break;
	case BriEvent_PlugOut:
		AppendStatusEx(uID,"设备移除");
		break;
	case BriEvent_DevErr://设备错误
		AppendStatusEx(uID,"设备错误"+ decodeURIComponent(vValue) );
	break;
	default:
		if(uEventType < BriEvent_EndID)
			AppendStatusEx(uID,"忽略其它事件发生:ID=" + uEventType+ vValue);	
	break;
	}
	
}


function AppendStatus(szStatus)
{
	//qnviccub.QNV_Tool(QNV_TOOL_WRITELOG,0,szStatus,NULL,NULL,0);//写本地日志到控件注册目录的userlog目录下
	//$("#StatusArea").append(szStatus+"\r\n" );
	//var scrollTop = $("#StatusArea")[0].scrollHeight;  
	//$("#StatusArea").scrollTop(scrollTop);  	
	//var szHint = $("#StatusArea").val()+ szStatus +"\r\n";
	// $("#StatusArea").val(szHint);
	//  $("#StatusArea").scrollTop($("#StatusArea").scrollHeight());
	
	console.log(szStatus);
}
function AppendStatusEx(uID,szStatus)
{
	uID=uID+1;
	AppendStatus("通道"+uID+":"+szStatus);
}




