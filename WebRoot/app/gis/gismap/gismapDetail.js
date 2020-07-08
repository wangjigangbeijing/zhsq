

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
	$.get(getContextPath()+"/gismapController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#mapid').val(obj.mapid);
				$('#mapname').val(obj.mapname);
				$('#maptype').val(obj.maptype);
				$('#mapstatus').val(obj.mapstatus);
				$('#mapscope').val(obj.mapscope);
				$('#mapnote').val(obj.mapnote);

					
			}
		});
}

function gobackPage()
{
	
	curId = '';
	
	$('#main-content').load("./gismap/gismap.html", function () {
		
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
	
	
	$.post(getContextPath()+"/gismapController/addOrUpdate",
	{
		id:curId,
				mapid:$('#mapid').val(),
		mapname:$('#mapname').val(),
		maptype:$('#maptype').val(),
		mapstatus:$('#mapstatus').val(),
		mapscope:$('#mapscope').val(),
		mapnote:$('#mapnote').val()
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
