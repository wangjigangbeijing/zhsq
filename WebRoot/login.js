/**
 * 登录系统所使用的js文件内容
 */

 
 
$(document).ready(function (){
    
	/*var ie = detectIE();
	
	if(ie != false)
	{
		$.confirm({
			title:"Confirmation",
			text:"Internet Explorer is not well supported, Please use Chrome or FireFox. <br> Still want to continue with IE?",
			confirm: function(button) {
				
			},
			cancel: function(button) {
				window.close();
			},
			confirmButton: "Yes I am",
			cancelButton: "No"
		});
		
	}*/
	
	$("#signInBtn").click(login);
	
	$('#usernameInput').bind('keypress',function(event){
		$("#errorDiv").hide();
	});
	
	$('#passwordInput').bind('keypress',function(event){
		$("#errorDiv").hide();
		if(event.keyCode == "13")    
		{
			login();
		}
	});
	
	javascript:window.history.forward(1);//禁用回退
	
});

function login()
{
	$("#loading").show();
	$("#errorDiv").hide();
	
	$.post(getContextPath()+"/loginController/login?username="+$("#usernameInput").val()+"&password="+$("#passwordInput").val(),
	function(result){
		$("#loading").hide();
		var obj = jQuery.parseJSON(result);  
		
		if(obj.success)
		{
			document.location.href = getContextPath()+obj.homePage;
			/*
			window.location.replace(getContextPath()+obj.homePage);
			
			
			var ie = detectIE();
			if(ie == false)
			{
				
			}
			else
			{
				window.location.replace(getContextPath()+'/app/homepageIE11.html');
			}*/
		}
		else
		{
			//$("#errorDiv").show();
			//$("#errMsgLabel").text(obj.errMsg);
			
			jError(obj.errMsg,{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
		}
	});
}


