

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
	$.get(getContextPath()+"/xgyqinfoController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
				$('#name').val(obj.name);
				$('#mobile').val(obj.mobile);
				$('#address').val(obj.address);
				$('#quezhen').val(obj.quezhen);
				$('#qzdate').val(obj.qzdate);
				$('#qznote').val(obj.qznote);
				$('#yisi').val(obj.yisi);
				$('#mijie').val(obj.mijie);
				$('#mjnote').val(obj.mjnote);
				$('#glstartdate').val(obj.glstartdate);
				$('#glenddate').val(obj.glenddate);
				$('#note').val(obj.note);
				$('#hsjc').val(obj.hsjc);
				$('#hsjcdate').val(obj.hsjcdate);
				$('#hsjcjigou').val(obj.hsjcjigou);
				$('#hsjcjieguo').val(obj.hsjcjieguo);
			}
		});
}

function gobackPage()
{
	curId = '';
	
	$('#main-content').load("./nfw/zhfw/xgyqinfo.html", function () {
		
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
	
	
	$.post(getContextPath()+"/xgyqinfoController/addOrUpdate",
	{
		id:curId,
				name:$('#name').val(),
		mobile:$('#mobile').val(),
		address:$('#address').val(),
		quezhen:$('#quezhen').val(),
		qzdate:$('#qzdate').val(),
		qznote:$('#qznote').val(),
		yisi:$('#yisi').val(),
		mijie:$('#mijie').val(),
		mjnote:$('#mjnote').val(),
		glstartdate:$('#glstartdate').val(),
		glenddate:$('#glenddate').val(),
		note:$('#note').val(),
		hsjc:$('#hsjc').val(),
		hsjcdate:$('#hsjcdate').val(),
		hsjcjigou:$('#hsjcjigou').val(),
		hsjcjieguo:$('#hsjcjieguo').val()
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


