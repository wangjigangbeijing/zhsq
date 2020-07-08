
//获取地址栏参数，name:参数名称
 function getUrlParms(name){
   var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
   var r = window.location.search.substr(1).match(reg);
   if(r!=null)
   return unescape(r[2]);
   return null;
   }

$(document).ready(function (){
	
	/*$.get(getContextPath()+"/dictionaryController/load",
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
	);*/
	
	if(curId != null && curId != '')
		get();
});

function get()
{
	$.get(getContextPath()+"/dictionaryController/get?id="+curId,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#dic_enname').val(obj.dic_enname);
				$('#dic_zhname').val(obj.dic_zhname);
				$('#dic_type').val(obj.dic_type);
				$('#dic_value').val(obj.dic_value);
			}
		});
}

function addOrUpdate()
{
	$.post(getContextPath()+"/dictionaryController/addOrUpdate",
	{
		id:curId,
		dic_enname:$('#dic_enname').val(),
		dic_zhname:$('#dic_zhname').val(),
		dic_type:$('#dic_type').val(),
		dic_value:$('#dic_value').val()
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
	$('#main-content').load("./dictionary/dictionary.html", function () {
		
	});
}
