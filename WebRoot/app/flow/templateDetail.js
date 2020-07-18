

$(document).ready(function (){
	
	//$('#btnReset').click(Reset);
	
	//$('#btnSearch').click(load);
	
	//load();
	
	loadcommunity();
	
	loadsxdl();
	
	if(curId != '')
		viewDetail(curId);
});


function viewDetail(id)
{
	$.get(getContextPath()+"/flowtemplateController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				
				$("#id").val(obj.data.id);
				$('#templatename').val(obj.data.templatename);
				$("#communityid").val(obj.data.communityid);
				
			}
		});
}

function loadcommunity(){
	$.get(getContextPath()+"/flowtemplateController/getcommunitylist",
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				
				for(var i = 0; i < obj.list.length; i++)
				{
					var item = "<option value=" + obj.list[i].id + ">" + obj.list[i].name + "</option>";
					$("#communityid").append(item);
				}
				
			}
		});
}

function loadsxdl(){
	$.get(getContextPath()+"/flowtemplateController/getsxdllist",
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$("#sxdl option:not(:first)").remove();
				for(var i = 0; i < obj.list.length; i++)
				{
					var item = "<option value=" + obj.list[i].blsxdl + ">" + obj.list[i].blsxdl + "</option>";
					$("#sxdl").append(item);
				}
				
			}
		});
}

function loadsxxl(){
	var sxdl = $("#sxdl").val();
	$.post(getContextPath()+"/flowtemplateController/getsxxllist",
	{
		sxdl:sxdl
	},
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$("#sxxl option:not(:first)").remove();
				for(var i = 0; i < obj.list.length; i++)
				{
					var item = "<option value=" + obj.list[i].blsxxl + ">" + obj.list[i].blsxxl + "</option>";
					$("#sxxl").append(item);
				}
				
			}
		});
}

function gobackPage()
{
	
	curId = '';
	
	$('#main-content').load("./flow/template.html", function () {
		
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
	$.post(getContextPath()+"/flowtemplateController/addOrUpdate",
	{
		id:curId,
		templatename:$('#templatename').val(),
		communityid: $("#communityid").val(),
		sxdl: $("#sxdl").val(),
		sxxl: $("#sxxl").val()
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
