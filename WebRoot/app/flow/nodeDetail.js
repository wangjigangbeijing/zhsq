

$(document).ready(function (){
	
	//$('#btnReset').click(Reset);
	
	//$('#btnSearch').click(load);
	
	//load();
	
	if(curId != '')
		viewDetail(curId);
});


function viewDetail(id)
{
	$.get(getContextPath()+"/flowController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				
				$("#id").val(obj.data.id);
				$('#nodename').val(obj.data.nodename);
				var categories = obj.data.node_category.split(",");
				for(var i = 0; i < categories.length; i++){
					var v = categories[i];
					$("input:checkbox[value=" + v + "]").attr('checked','true');
				}
				
				$('#nodetype').val(obj.data.nodetype);
				$("input[name='canback'][value='"+obj.data.canback+"']").attr("checked",true);
				$("input[name='cangoon'][value='"+obj.data.cangoon+"']").attr("checked",true);
					
			}
		});
}

function gobackPage()
{
	
	curId = '';
	
	$('#main-content').load("./flow/node.html", function () {
		
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
	var categories = "";
	$.each($('input:checkbox:checked'),function(){
		if(categories.length == 0) categories = $(this).val();
		else categories += "," + $(this).val();
	});
	$.post(getContextPath()+"/flowController/addOrUpdate",
	{
		id:curId,
		nodename:$('#nodename').val(),
		node_category:categories,
		nodetype: $("#nodetype").val(),
		canback:$("input[name='canback']:checked").val(),//$('#buildtype').val(),
		cangoon:$("input[name='cangoon']:checked").val()//$('#buildtype').val(),
	},
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			jSuccess("数据添加成功!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
			
			gobackPage();
			
			//load();
		}
		else
		{
			jError("数据添加失败!",{
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
