

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
	$.get(getContextPath()+"/worklogController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#title').val(obj.title);
				$('#date').val(obj.date);
				$('#type').val(obj.type);
				$('#hours').val(obj.hours);
				$('#content').val(obj.content);
				var filesArr = obj.files.split(VALUE_SPLITTER);				for(var j=0;j<filesArr.length;j++)				{					if(filesArr[j] != '')					{						$('#filespicktable').append('<tr><td>'+filesArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+filesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					}				}				$('#owner').val(obj.owner);

					
			}
		});
}

function gobackPage()
{
	
	curId = '';
	
	$('#main-content').load("./worklog/worklog.html", function () {
		
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
	
	
	$.post(getContextPath()+"/worklogController/addOrUpdate",
	{
		id:curId,
				title:$('#title').val(),
		date:$('#date').val(),
		type:$('#type').val(),
		hours:$('#hours').val(),
		content:$('#content').val(),
		files:$('#files').val(),
		owner:$('#owner').val()
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
