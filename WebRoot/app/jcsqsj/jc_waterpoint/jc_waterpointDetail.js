

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
	$.get(getContextPath()+"/jc_waterpointController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#dataid').val(obj.dataid);
				$('#name').val(obj.name);
				$('#address').val(obj.address);
				$('#type').val(obj.type);
				$('#depth').val(obj.depth);
				$('#plan').val(obj.plan);
				$('#note').val(obj.note);
				var pictureArr = obj.picture.split(VALUE_SPLITTER);				for(var j=0;j<pictureArr.length;j++)				{					if(pictureArr[j] != '')					{						$('#picturepicktable').append('<tr><td>'+pictureArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+pictureArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					}				}				var fileArr = obj.file.split(VALUE_SPLITTER);				for(var j=0;j<fileArr.length;j++)				{					if(fileArr[j] != '')					{						$('#filepicktable').append('<tr><td>'+fileArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+fileArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					}				}
					
			}
		});
}

function gobackPage()
{
	curId = '';
	
	$('#main-content').load("./jcsqsj/jcsqsj.html", function () {
		
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
	
	
	$.post(getContextPath()+"/jc_waterpointController/addOrUpdate",
	{
		id:curId,
				dataid:$('#dataid').val(),
		name:$('#name').val(),
		address:$('#address').val(),
		type:$('#type').val(),
		depth:$('#depth').val(),
		plan:$('#plan').val(),
		note:$('#note').val(),
		picture:$('#picture').val(),
		file:$('#file').val()
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
