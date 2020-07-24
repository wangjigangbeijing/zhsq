

$(document).ready(function (){
	
	$('#btnAdd').click(ShowAddModal);
	
	$('.dpYears').datepicker({
		autoclose: true
	});
	
	//$('#btnReset').click(Reset);
	
	$('#btnSearch').click(load);
	
	//load();
	
	if(curId != '')
		viewDetail(curId);
});


function viewDetail(id)
{
	$.get(getContextPath()+"/sysBoardPublishController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#title').val(obj.title);
				$('#category').val(obj.category);
				$('#starttime').val(obj.starttime);
				$('#endtime').val(obj.endtime);
				$('#facilities').val(obj.facilities);
				$('#content').val(obj.content);
				var attachmentArr = obj.attachment.split(VALUE_SPLITTER);				for(var j=0;j<attachmentArr.length;j++)				{					if(attachmentArr[j] != '')					{						$('#attachmentpicktable').append('<tr><td>'+attachmentArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+attachmentArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					}				}
					
			}
		});
}

function gobackPage()
{
	
	curId = '';
	
	$('#main-content').load("./sys/sys_board_publish/sys_board_publish.html", function () {
		
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
function addOrUpdate()
{
	
	
	$.post(getContextPath()+"/sysBoardPublishController/addOrUpdate",
	{
		id:curId,
				title:$('#title').val(),
		category:$('#category').val(),
		starttime:$('#starttime').val(),
		endtime:$('#endtime').val(),
		facilities:$('#facilities').val(),
		content:$('#content').val(),
		attachment:$('#attachment').val()
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


function downloadAttach(fileName)
{
	var url = getContextPath()+"/fileController/download?fileName="+fileName;
	
	window.open(encodeURI(url));
	
	//window.open(getContextPath()+"/fileController/downLoad/"+encodeURI(obj.fileName));
}
