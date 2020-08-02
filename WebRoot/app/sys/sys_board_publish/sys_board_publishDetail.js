

$(document).ready(function (){
	
	$('#btnAdd').click(ShowAddModal);
	
	$('.dpYears').datepicker({
		autoclose: true
	});
	
	//$('#btnReset').click(Reset);
	
	$('#btnSearch').click(load);
	
	//load();
	
	
	$("#facilities").select2({	 
		multiple: true
	});
	
	$.get(getContextPath()+"/culturefacilitiesController/load?tpye=布告栏设施",//tpye拼写的时候有错误
		function(result){
			
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#facilities').html('');
				
				var roleArr = [];
				
				for(var i=0;i<obj.list.length;i++)
				{
					var role = obj.list[i];
					
					roleArr[i] = "<option value='" + role.id + "'>" + role.name + "</option>";						
				}
				$('#facilities').html(roleArr.join(''));
			}
		}
	);
	
	
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
				//$('#facilities').val(obj.facilities);
				
				if(obj.facilities != null && obj.facilities != undefined)							
					$("#facilities").val(obj.facilities.split(',')).trigger("change");

				$('#content').val(obj.content);
				var attachmentArr = obj.attachment.split(VALUE_SPLITTER);

				for(var j=0;j<attachmentArr.length;j++)				
				{					
					if(attachmentArr[j] != '')					
					{						
						$('#attachmentpicktable').append('<tr><td>'+attachmentArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+attachmentArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					
					}
				}	
			}
		});
}

function gobackPage()
{
	curId = '';
	
	$('#main-content').load("./sys/sys_board_publish/sys_board_publish.html", function () {
		
    });
}

function addOrUpdate()
{
	var facilities = $('#facilities').val().join(',');
	$.post(getContextPath()+"/sysBoardPublishController/addOrUpdate",
	{
		id:curId,
		title:$('#title').val(),
		category:$('#category').val(),
		starttime:$('#starttime').val(),
		endtime:$('#endtime').val(),
		facilities:facilities,
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
