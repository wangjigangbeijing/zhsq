


function getContextPath() {
	var pathName = document.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = pathName.substr(0,index+1);
	return result;
	
	/*return "";*/
}

var VALUE_SPLITTER = ",";

var USER_TYPE_ADMIN = 0;
var USER_TYPE_PM = 1;

var curSXType = '';//事项类型

var curUserType = -1;

var curJCSQSJType = '';//基础社情数据类型

var curUserOrgId = '';

var curUserMenuRightArr = [];//当前用户具有的菜单权限

var curUserOptRightArr = [];//当前用户具有的操作权限

$.ajax({
  type: 'POST',
  url: getContextPath()+"/dictionaryController/getDataOfDic",
  data: JSON.stringify({id:'ofcommunity'}),
  contentType: "application/json",
  success:function(result){
			
		if(result.success)
		{
			$('#ofcommunity').html('');
			var filterArr = [];
			
			filterArr[0] = "<option value=''></option>";				
			
			for(var i=0;i<result.value.length;i++)
			{
				var filter = result.value[i];
				
				filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
			}
			$('#ofcommunity').html(filterArr.join(''));
			
			$('#ofresidebuilding').html('');				
			$('#ofunit').html('');
			$('#ofroom').html('');
			$('#offamily').html('');
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
				$('#offamily').html('');
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
				$('#offamily').html('');
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
				$('#offamily').html('');
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

$('#ofroom').change(function(){
	
	var ofresidebuilding = $('#ofresidebuilding').val();
	var ofcommunity = $('#ofcommunity').val();
	var ofunit = $('#ofunit').val();
	var ofroom = $('#ofroom').val();
	
	$.ajax({
	  type: 'POST',
	  url: getContextPath()+"/dictionaryController/getDataOfDic",
	  data: JSON.stringify({id:'offamily',params:[{enname:'ofcommunity',value:ofcommunity},{enname:'ofresidebuilding',value:ofresidebuilding},{enname:'ofunit',value:ofunit},{enname:'ofroom',value:ofroom}]}),
	  contentType: "application/json",
	  success:function(result){
				
			if(result.success)
			{
				$('#offamily').html('');
				var filterArr = [];
				filterArr[0] = "<option value=''></option>";							
				for(var i=0;i<result.value.length;i++)
				{
					var filter = result.value[i];
					
					filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
				}
				$('#offamily').html(filterArr.join(''));
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
	
	
	
	
	



$.ajax({
	  type: 'POST',
	  url: getContextPath()+"/dictionaryController/getDataOfDic",
	  data: JSON.stringify({id:'ofcommunity'}),
	  contentType: "application/json",
	  success:function(result){
				
			if(result.success)
			{
				$('#ofcommunityQuery').html('');
				var filterArr = [];
				
				filterArr[0] = "<option value=''></option>";				
				
				for(var i=0;i<result.value.length;i++)
				{
					var filter = result.value[i];
					
					filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
				}
				$('#ofcommunityQuery').html(filterArr.join(''));
				
				$('#ofresidebuildingQuery').html('');				
				$('#ofunitQuery').html('');
				$('#ofroomQuery').html('');
				$('#offamilyQuery').html('');
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
	
	$('#ofcommunityQuery').change(function(){
		
		var ofcommunity = $('#ofcommunityQuery').val();
		
		$.ajax({
		  type: 'POST',
		  url: getContextPath()+"/dictionaryController/getDataOfDic",
		  data: JSON.stringify({id:'ofresidebuilding',params:[{enname:'ofcommunity',value:ofcommunity}]}),
		  contentType: "application/json",
		  success:function(result){
					
				if(result.success)
				{
					$('#ofresidebuildingQuery').html('');
					var filterArr = [];
					filterArr[0] = "<option value=''></option>";				
					for(var i=0;i<result.value.length;i++)
					{
						var filter = result.value[i];
						
						filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
					}
					$('#ofresidebuildingQuery').html(filterArr.join(''));
					
					$('#ofunitQuery').html('');
					$('#ofroomQuery').html('');
					$('#offamilyQuery').html('');
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
	
	$('#ofresidebuildingQuery').change(function(){
		
		var ofresidebuilding = $('#ofresidebuildingQuery').val();
		var ofcommunity = $('#ofcommunityQuery').val();
		
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
					$('#ofunitQuery').html(filterArr.join(''));
					
					$('#ofroomQuery').html('');
					$('#offamilyQuery').html('');
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
	
	$('#ofunitQuery').change(function(){
		
		var ofresidebuilding = $('#ofresidebuildingQuery').val();
		var ofcommunity = $('#ofcommunityQuery').val();
		var ofunit = $('#ofunitQuery').val();
		
		$.ajax({
		  type: 'POST',
		  url: getContextPath()+"/dictionaryController/getDataOfDic",
		  data: JSON.stringify({id:'ofroom',params:[{enname:'ofcommunity',value:ofcommunity},{enname:'ofresidebuilding',value:ofresidebuilding},{enname:'ofunit',value:ofunit}]}),
		  contentType: "application/json",
		  success:function(result){
					
				if(result.success)
				{
					$('#ofroomQuery').html('');
					var filterArr = [];
					filterArr[0] = "<option value=''></option>";							
					for(var i=0;i<result.value.length;i++)
					{
						var filter = result.value[i];
						
						filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
					}
					$('#ofroomQuery').html(filterArr.join(''));
					$('#offamilyQuery').html('');
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
	
	$('#ofroomQuery').change(function(){
		
		var ofresidebuilding = $('#ofresidebuildingQuery').val();
		var ofcommunity = $('#ofcommunityQuery').val();
		var ofunit = $('#ofunitQuery').val();
		var ofroom = $('#ofroomQuery').val();
		
		$.ajax({
		  type: 'POST',
		  url: getContextPath()+"/dictionaryController/getDataOfDic",
		  data: JSON.stringify({id:'offamily',params:[{enname:'ofcommunity',value:ofcommunity},{enname:'ofresidebuilding',value:ofresidebuilding},{enname:'ofunit',value:ofunit},{enname:'ofroom',value:ofroom}]}),
		  contentType: "application/json",
		  success:function(result){
					
				if(result.success)
				{
					$('#offamilyQuery').html('');
					var filterArr = [];
					filterArr[0] = "<option value=''></option>";							
					for(var i=0;i<result.value.length;i++)
					{
						var filter = result.value[i];
						
						filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
					}
					$('#offamilyQuery').html(filterArr.join(''));
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
	
	
	
	

function downloadAttach(fileName)
{
	var url = getContextPath()+"/fileController/download?fileName="+fileName;
	
	window.open(encodeURI(url));
	
	//window.open(getContextPath()+"/fileController/downLoad/"+encodeURI(obj.fileName));
}

function refreshUserOptRight()
{
	for(var i=0;i<curUserOptRightArr.length;i++)
	{
		$('#'+curUserOptRightArr[i]).show();
	}
}

function haveRight(rightid)
{
	if(curUserOptRightArr.includes(rightid))
		return true;
	
	return false;
}






	