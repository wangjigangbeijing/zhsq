
//获取地址栏参数，name:参数名称
 function getUrlParms(name){
   var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
   var r = window.location.search.substr(1).match(reg);
   if(r!=null)
   return unescape(r[2]);
   return null;
   }

$(document).ready(function (){
	
	/*$.get(getContextPath()+"/tableController/loadTable",
		function(result){
			
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#table_id').html('');
				var userArr = [];
				
				for(var i=0;i<obj.tableList.length;i++)
				{
					var user = obj.tableList[i];
					
					userArr[i] = "<option value='" + user.id + "'>" + user.tableZHName + "</option>";						
				}
				$('#table_id').html(userArr.join(''));
			}
		}
	);
	*/
	
	$('#userName').val(curUserName); 
	
	if(curId != null && curId != '')
		get();
});

function get()
{
	$.get(getContextPath()+"/smsController/get?id="+curId,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#menu_enname').val(obj.menu_enname);
				$('#menu_zhname').val(obj.menu_zhname);
				$('#menu_type').val(obj.menu_type);
				$('#table_id').val(obj.table_id);
				$('#file_name').val(obj.fifth);
				$('#external_url').val(obj.external_url);

			}
		});
}

function addOrUpdate()
{
	$.post(getContextPath()+"/smsController/addOrUpdate",
	{
		id:curId,
		smsContent:$('#smsContent').val(),
		mobileList:$('#mobileList').val()
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

function gobackPage()
{
	$('#main-content').load("./sms/sms.html", function () {
		
	});
}
