

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
	$.get(getContextPath()+"/undergroundspaceController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#dateid').val(obj.dateid);
				$('#name').val(obj.name);
				$('#type').val(obj.type);
				$('#address').val(obj.address);
				$('#ofresidebuilding').val(obj.ofresidebuilding);
				$('#ofbizbuilding').val(obj.ofbizbuilding);
				$('#level').val(obj.level);
				$('#area').val(obj.area);
				$('#purpose').val(obj.purpose);
				$('#longitude').val(obj.longitude);
				$('#latitude').val(obj.latitude);
				$('#status').val(obj.status);
				var picturesArr = obj.pictures.split(VALUE_SPLITTER);				for(var j=0;j<picturesArr.length;j++)				{					if(picturesArr[j] != '')					{						$('#picturespicktable').append('<tr><td>'+picturesArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					}				}				$('#note').val(obj.note);

					
			}
		});
}

function gobackPage()
{
	
	curId = '';
	
	$('#main-content').load("./jcsqsj/undergroundspace/undergroundspace.html", function () {
		
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
	
	
	$.post(getContextPath()+"/undergroundspaceController/addOrUpdate",
	{
		id:curId,
				dateid:$('#dateid').val(),
		name:$('#name').val(),
		type:$('#type').val(),
		address:$('#address').val(),
		ofresidebuilding:$('#ofresidebuilding').val(),
		ofbizbuilding:$('#ofbizbuilding').val(),
		level:$('#level').val(),
		area:$('#area').val(),
		purpose:$('#purpose').val(),
		longitude:$('#longitude').val(),
		latitude:$('#latitude').val(),
		status:$('#status').val(),
		pictures:$('#pictures').val(),
		note:$('#note').val()
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
