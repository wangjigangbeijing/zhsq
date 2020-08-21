

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
	  url: getContextPath()+"/communityController/load",
	  data: JSON.stringify({id:'ofcommunity'}),
	  contentType: "application/json",
	  success:function(result){
				
			if(result.success)
			{
				$('#ofcommunity').html('');
				var filterArr = [];
				
				filterArr[0] = "<option value=''></option>";				
				
				for(var i=0;i<result.list.length;i++)
				{
					var filter = result.list[i];
					
					filterArr[i+1] = "<option value='" + filter.id + "'>" + filter.value + "</option>";						
				}
				$('#ofcommunity').html(filterArr.join(''));
				
				$('#ofresidebuilding').html('');				
				$('#ofunit').html('');
				$('#ofroom').html('');
			}
			else
			{
				jError("获取社区列表失败!",{
					VerticalPosition : 'center',
					HorizontalPosition : 'center'
				});
			}
		},
	  dataType: "json"
	});
	
	
	$.ajax({
		  type: 'POST',
		  url: getContextPath()+"/residebuildingController/load",
		  data: JSON.stringify({id:'ofresidebuilding'}),
		  contentType: "application/json",
		  success:function(result){
					
				if(result.success)
				{
					$('#ofresidebuilding').html('');
					var filterArr = [];
					filterArr[0] = "<option value=''></option>";				
					for(var i=0;i<result.list.length;i++)
					{
						var filter = result.list[i];
						
						filterArr[i+1] = "<option value='" + filter.id + "'>" + filter.value + "</option>";						
					}
					$('#ofresidebuilding').html(filterArr.join(''));
					
					$('#ofunit').html('');
					$('#ofroom').html('');
				}
				else
				{
					jError("获取居民楼列表失败!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'
					});
				}
			},
		  dataType: "json"
		});
	
	$('#ofcommunity').change(function(){
		
		var ofcommunity = $('#ofcommunity').val();
		
		$.ajax({
		  type: 'POST',
		  url: getContextPath()+"/dictionaryController/getDataOfDic",
		  data: JSON.stringify({id:'ofresidebuilding',params:[{enname:'ofcommunity',value:ofcommunity}]}),
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
					
					$('#ofunit').html('');
					$('#ofroom').html('');
				}
				else
				{
					jError("获取居民楼列表失败!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'
					});
				}
			},
		  dataType: "json"
		});
	});
	
	$('#ofresidebuilding').change(function(){
		
		var ofresidebuilding = $('#ofresidebuilding').val();
		var ofcommunity = $('#ofcommunity').val();
		
		$.ajax({
		  type: 'POST',
		  url: getContextPath()+"/dictionaryController/getDataOfDic",
		  data: JSON.stringify({id:'ofunit',params:[{enname:'ofcommunity',value:ofcommunity},{enname:'ofresidebuilding',value:ofresidebuilding}]}),
		  contentType: "application/json",
		  success:function(result){
					
				if(result.success)
				{
					$('#ofunit').html('');
					var filterArr = [];
					filterArr[0] = "<option value=''></option>";							
					for(var i=0;i<result.value.length;i++)
					{
						var filter = result.value[i];
						
						filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
					}
					$('#ofunit').html(filterArr.join(''));
					
					$('#ofroom').html('');
				}
				else
				{
					jError("获取单元列表失败!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'
					});
				}
			},
		  dataType: "json"
		});
	});
	
	$('#ofunit').change(function(){
		
		var ofresidebuilding = $('#ofresidebuilding').val();
		var ofcommunity = $('#ofcommunity').val();
		var ofunit = $('#ofunit').val();
		
		$.ajax({
		  type: 'POST',
		  url: getContextPath()+"/dictionaryController/getDataOfDic",
		  data: JSON.stringify({id:'ofroom',params:[{enname:'ofcommunity',value:ofcommunity},{enname:'ofresidebuilding',value:ofresidebuilding},{enname:'ofunit',value:ofunit}]}),
		  contentType: "application/json",
		  success:function(result){
					
				if(result.success)
				{
					$('#ofroom').html('');
					var filterArr = [];
					filterArr[0] = "<option value=''></option>";							
					for(var i=0;i<result.value.length;i++)
					{
						var filter = result.value[i];
						
						filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
					}
					$('#ofroom').html(filterArr.join(''));
				}
				else
				{
					jError("获取房间列表失败!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'
					});
				}
			},
		  dataType: "json"
		});
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
	$.get(getContextPath()+"/familyController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
				$('#name').val(obj.name);
				$('#registrationcategory').val(obj.registrationcategory);
				$('#registrationaddress').val(obj.registrationaddress);
				$('#ofcommunity').val(obj.ofcommunity);
				$('#ofresidebuilding').val(obj.ofresidebuilding);
				//$('#ofunit').val(obj.ofunit);
				//$('#ofroom').val(obj.ofroom);
				//$('#status').val(obj.status);
				$("input[name='status'][value='"+obj.status+"']").attr("checked",true); 

				/*
				$('#ofcommunity').html('');
				var filterArr = [];				
				filterArr[0] = "<option value='"+obj.ofcommunity+"'>"+obj.ofcommunity+"</option>";				
				$('#ofcommunity').html(filterArr.join(''));
				//$('#ofcommunity').attr("readonly","readonly");
				
				$('#ofresidebuilding').html('');
				var filterArr = [];				
				filterArr[0] = "<option value='"+obj.ofresidebuilding+"'>"+obj.ofresidebuildingname+"</option>";				
				$('#ofresidebuilding').html(filterArr.join(''));
				//$('#ofresidebuilding').attr("readonly","readonly");
				*/
				$('#ofunit').html('');
				var filterArr = [];				
				filterArr[0] = "<option value='"+obj.ofunit+"'>"+obj.ofunit+"</option>";				
				$('#ofunit').html(filterArr.join(''));
				//$('#ofunit').attr("readonly","readonly");	
				
				$('#ofroom').html('');
				var filterArr = [];				
				filterArr[0] = "<option value='"+obj.ofroom+"'>"+obj.ofroomname+"</option>";				
				$('#ofroom').html(filterArr.join(''));
				//$('#ofroom').attr("readonly","readonly");	
				
			}
		});
}

function gobackPage()
{
	
	curId = '';
	
	//$('#main-content').load("./jcsqsj/family/family.html", function () {
	$('#main-content').load("./jcsqsj/jcsqsj.html", function () {	
		
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
	
	
	$.post(getContextPath()+"/familyController/addOrUpdate",
	{
		id:curId,
				name:$('#name').val(),
		registrationcategory:$('#registrationcategory').val(),
		registrationaddress:$('#registrationaddress').val(),
		ofcommunity:$('#ofcommunity').val(),
		ofresidebuilding:$('#ofresidebuilding').val(),
		ofunit:$('#ofunit').val(),
		ofroom:$('#ofroom').val(),
		status:$('input:radio[name="status"]:checked').val()//,$('#status').val()
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
