

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
	$.get(getContextPath()+"/contactController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				$("input[name='contacttype'][value='"+obj.contacttype+"']").attr("checked",true); 
				$('#name').val(obj.name);
				$('#tel').val(obj.tel);
				$('#mobile').val(obj.mobile);
				$('#unitid').val(obj.unitid);
				$('#unitname').val(obj.unitname);
				$('#job').val(obj.job);
				$('#order').val(obj.order);
				$('#note').val(obj.note);
				$('#authoritystaffid').val(obj.authoritystaffid);

					
			}
		});
}

function gobackPage()
{
	
	curId = '';
	
	$('#main-content').load("./oa/contact/contact.html", function () {
		
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
	
	
	$.post(getContextPath()+"/contactController/addOrUpdate",
	{
		id:curId,
		contacttype:$("input[name='contacttype']:checked").val(),
		name:$('#name').val(),
		tel:$('#tel').val(),
		mobile:$('#mobile').val(),
		unitid:$('#unitid').val(),
		unitname:$('#unitname').val(),
		job:$('#job').val(),
		order:$('#order').val(),
		note:$('#note').val(),
		authoritystaffid:$('#authoritystaffid').val()
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
