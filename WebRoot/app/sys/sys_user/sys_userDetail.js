

$(document).ready(function (){
	
	$('#btnAdd').click(ShowAddModal);
	
	$('.dpYears').datepicker({
		autoclose: true
	});
	
	//$('#btnReset').click(Reset);
	
	$('#btnSearch').click(load);
	
	//load();
	
	$("#role").select2({	 
		multiple: true
	});
	
	$.get(getContextPath()+"/sysRoleController/load",
		function(result){
			
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#role').html('');
				
				var roleArr = [];
				
				for(var i=0;i<obj.list.length;i++)
				{
					var role = obj.list[i];
					
					roleArr[i] = "<option value='" + role.id + "'>" + role.name + "</option>";						
				}
				$('#role').html(roleArr.join(''));
			}
		}
	);
	
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
				//$('#role').val(obj.role);
				
				if(obj.role != null && obj.role != undefined)							
					$("#role").val(obj.role.split(',')).trigger("change");
						
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
	var roleArr = $('#role').val();
	
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
		role:roleArr.join(','),
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
