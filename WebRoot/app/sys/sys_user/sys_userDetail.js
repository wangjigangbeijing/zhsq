

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
	$.get(getContextPath()+"/sysUserController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#name').val(obj.name);
				$('#loginid').val(obj.loginid);
				$('#password').val(obj.password);
				//$('#gender').val(obj.gender);
				
				$("input[name='gender'][value='"+obj.gender+"']").attr("checked",true); 
				
				$('#birthday').val(obj.birthday);
				$('#joinday').val(obj.joinday);
				$('#mobile').val(obj.mobile);
				$('#department').val(obj.department);
				$('#job').val(obj.job);
				$('#role').val(obj.role);
				$('#status').val(obj.status);

					
			}
		});
}

function gobackPage()
{
	
	curId = '';
	
	$('#main-content').load("./sys/sys_user/sys_user.html", function () {
		
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
	
	
	$.post(getContextPath()+"/sysUserController/addOrUpdate",
	{
		id:curId,
				name:$('#name').val(),
		loginid:$('#loginid').val(),
		password:$('#password').val(),
		gender:$("input[name='gender']:checked").val(),//$('#gender').val(),
		birthday:$('#birthday').val(),
		joinday:$('#joinday').val(),
		mobile:$('#mobile').val(),
		department:$('#department').val(),
		job:$('#job').val(),
		role:$('#role').val(),
		status:$('#status').val()
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
