

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
	$.get(getContextPath()+"/sysOrganizationController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#name').val(obj.name);
				$('#code').val(obj.code);
				if(obj.type != null){
					var typeArr = obj.type.split(VALUE_SPLITTER);
					for(var j=0;j<typeArr.length;j++)
					{
						if(typeArr[j] != '')
						{
							$("input[name='type'][value='"+typeArr[j]+"']").attr('checked','true');
						}
					}	
				}	
				$('#border').val(obj.border);
				$('#area').val(obj.area);
				$('#address').val(obj.address);
				$('#telphone').val(obj.telphone);
				$('#secretary').val(obj.secretary);
				$('#secretaryphone').val(obj.secretaryphone);
				$('#directorname').val(obj.directorname);
				$('#directorphone').val(obj.directorphone);
				$('#note').val(obj.note);

					
			}
		});
}

function gobackPage()
{
	
	curId = '';
	
	$('#main-content').load("./sys/sys_organization/sys_organization.html", function () {
		
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
	var valtypeArr=new Array();
	$('input[name="type"]:checked').each(function()
	{      
		valtypeArr.push($(this).val());//向数组中添加元素  		
	});
	var valtype = valtypeArr.join(VALUE_SPLITTER);//将数组元素连接起来以构建一个字符串

	$.post(getContextPath()+"/sysOrganizationController/addOrUpdate",
	{
		id:curId,
		name:$('#name').val(),
		code:$('#code').val(),
		type:valtype,
		border:$('#border').val(),
		area:$('#area').val(),
		address:$('#address').val(),
		telphone:$('#telphone').val(),
		secretary:$('#secretary').val(),
		secretaryphone:$('#secretaryphone').val(),
		directorname:$('#directorname').val(),
		directorphone:$('#directorphone').val(),
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
