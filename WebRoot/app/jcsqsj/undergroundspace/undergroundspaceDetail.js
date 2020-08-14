

$(document).ready(function (){
	
	$('#btnAdd').click(ShowAddModal);
	
	$('.dpYears').datepicker({
		autoclose: true
	});
	
	//$('#btnReset').click(Reset);
	
	$('#btnSearch').click(load);
	
	//load();
	
	$.ajax({
	  type: 'POST',
	  url: getContextPath()+"/dictionaryController/getDataOfDic",
	  data: JSON.stringify({id:'ofresidebuilding'}),
	  contentType: "application/json",
	  success:function(result){
				
			if(result.success)
			{
				$('#ofresidebuilding').html('');
				var filterArr = [];
				
				filterArr[0] = "<option value=''></option>";				
				
				for(var i=0;i<result.value.length;i++)
				{
					var filter = result.value[i];
					
					filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
				}
				$('#ofresidebuilding').html(filterArr.join(''));
				
			}
			else
			{
				jError("获取住宅楼宇列表失败!",{
					VerticalPosition : 'center',
					HorizontalPosition : 'center'
				});
			}
		},
	  dataType: "json"
	});
	
	
	$.ajax({
	  type: 'POST',
	  url: getContextPath()+"/dictionaryController/getDataOfDic",
	  data: JSON.stringify({id:'ofbizbuilding'}),
	  contentType: "application/json",
	  success:function(result){
				
			if(result.success)
			{
				$('#ofbizbuilding').html('');
				var filterArr = [];
				
				filterArr[0] = "<option value=''></option>";				
				
				for(var i=0;i<result.value.length;i++)
				{
					var filter = result.value[i];
					
					filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
				}
				$('#ofbizbuilding').html(filterArr.join(''));
				
			}
			else
			{
				jError("获取商务列表失败!",{
					VerticalPosition : 'center',
					HorizontalPosition : 'center'
				});
			}
		},
	  dataType: "json"
	});
	
	
	if(curId != '')
	{
		setTimeout(function(){ 
			viewDetail(curId);
		}, 1000);
	}
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
				$("input[name='status'][value='"+obj.status+"']").attr("checked",true); //$('#status').val(obj.status);
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
		status:$('input:radio[name="status"]:checked').val(),//$('#status').val(),
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
