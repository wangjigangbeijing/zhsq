

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
	$.get(getContextPath()+"/sysEquipmentController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#equipment_no').val(obj.equipment_no);
				$('#equipment_name').val(obj.equipment_name);
				$('#equipment_type').val(obj.equipment_type);
				$('#equipment_vendor').val(obj.equipment_vendor);
				$('#price').val(obj.price);
				$('#purchase_date').val(obj.purchase_date);
				$('#status').val(obj.status);

					
			}
		});
}

function closeModalDetail()
{
	
	curId = '';
	
	$('#main-content').load("./sysEquipment/sysEquipment.html", function () {
		
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
	
	
	$.post(getContextPath()+"/sysEquipmentController/addOrUpdate",
	{
		id:curId,
				equipment_no:$('#equipment_no').val(),
		equipment_name:$('#equipment_name').val(),
		equipment_type:$('#equipment_type').val(),
		equipment_vendor:$('#equipment_vendor').val(),
		price:$('#price').val(),
		purchase_date:$('#purchase_date').val(),
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
			
			closeModalDetail();
			
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
