
//获取地址栏参数，name:参数名称
 function getUrlParms(name){
   var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
   var r = window.location.search.substr(1).match(reg);
   if(r!=null)
   return unescape(r[2]);
   return null;
   }

$(document).ready(function (){
	
	if(curId != '')
		get();
	
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
				
				
				
				$('#fj').val(obj.fj);
				var picturesArr = obj.fj.split(VALUE_SPLITTER);  
				var header = getContextPath()+"/fileController/download?fileName=";	

				for(var j=0;j<picturesArr.length;j++)				
				{if(picturesArr[j] != '')	
				{	
					var url = header + picturesArr[j];
					if(url.indexOf('.pdf') >= 0 || url.indexOf('.PDF') >= 0){
						var uurl = getContextPath() + "/dist/js/pdf.html?param=" + url;
						$('#picturespicktable').append('<tr><td><a href="' + uurl + '" target="_blank")>' + picturesArr[j] + '</a></td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');											
					}						
					else 
					{							
						$('#picturespicktable').append('<tr><td><a href="' + url + '" data-lightbox="' + picturesArr[j] + '" data-title="' + picturesArr[j] + '" style="color:#64A600; font-size: 12px;">'+picturesArr[j]+'</a></td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');											
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
