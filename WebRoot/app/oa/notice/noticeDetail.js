

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
	$.get(getContextPath()+"/noticeController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
				$('#title').val(obj.title);
				$('#type').val(obj.type);
				$('#authorityorg').val(obj.authorityorg);
				$('#body').val(obj.body);
				$('#attach').val(obj.attach);
				
				var attachArr = obj.attach.split(VALUE_SPLITTER);		

				debugger;
				
				for(var j=0;j<attachArr.length;j++)				
				{					
					if(attachArr[j] != '')					
					{						
						$('#attachpicktable').append('<tr><td>'+attachArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+attachArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					
						
					}
				}				
						
				$('#note').val(obj.note);
				$('#time').val(obj.time);

					
			}
		});
}

function gobackPage()
{
	
	curId = '';
	
	$('#main-content').load("./oa/notice/notice.html", function () {
		
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
	
	
	$.post(getContextPath()+"/noticeController/addOrUpdate",
	{
		id:curId,
		title:$('#title').val(),
		type:$('#type').val(),
		authorityorg:$('#authorityorg').val(),
		body:$('#body').val(),
		attach:$('#attach').val(),
		time:$('#time').val()
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
