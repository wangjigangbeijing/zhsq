
//获取地址栏参数，name:参数名称
 function getUrlParms(name){
   var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
   var r = window.location.search.substr(1).match(reg);
   if(r!=null)
   return unescape(r[2]);
   return null;
   }

$(document).ready(function (){
	
	
	
});

function get()
{
	$.get(getContextPath()+"/sqzzqdController/get?id="+curId,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#bh').val(obj.bh);
				
				$('#gzzz').val(obj.gzzz);
				
				$('#zzly').val(obj.zzly);
				
				$('#zzy').val(obj.zzy);
				
				$('#lb').val(obj.lb);
				
				$('#yjflfgmc').val(obj.yjflfgmc);
				
				
				
				if(obj.fj != null)
				{
					var picturesArr = obj.fj.split(VALUE_SPLITTER);				
					for(var j=0;picturesArr != null && j<picturesArr.length;j++)				
					{					
						if(picturesArr[j] != '')					
						{						
							$('#picturespicktable').append('<tr><td>'+picturesArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					
						}
					}
				}
				
			}
		});
}

function addOrUpdate()
{
	var fj = $('#pictures').val();
	debugger;
	$.post(getContextPath()+"/sqzzqdController/addOrUpdate",
	{
		id:curId,
		bh:$('#bh').val(),
		gzzz:$('#gzzz').val(),
		zzly:$('#zzly').val(),
		zzy:$('#zzy').val(),
		lb:$('#lb').val(),
		yjflfgmc:$('#yjflfgmc').val(),
		fj:$('#pictures').val()
	},
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			jSuccess("事项创建成功!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'});
						
			gobackPage();
		}
		else
		{
			jError("事项创建失败!"+data.errMsg,{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'});
			return ;
		}
	});
	
}

function gobackPage()
{
	$('#main-content').load("./sqzzqd/sqzzqd.html", function () {
		
	});
}
