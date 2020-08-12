

$(document).ready(function (){
	
	$('.dpYears').datepicker({
		autoclose: true
	});
	
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
	
	if(curId != '')
	{
		setTimeout(function(){ 
			viewDetail(curId);
		}, 1000);
	}
});


function viewDetail(id)
{
	$.get(getContextPath()+"/roomController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				$('#dataid').val(obj.dataid);
				$('#number').val(obj.number);
				$('#level').val(obj.level);
				//$('#status').val(obj.status);
				$("input[name='status'][value='"+obj.status+"']").attr("checked",true); 
				
				//$('#isgrouporiented').val(obj.isgrouporiented);
				$("input[name='isgrouporiented'][value='"+obj.isgrouporiented+"']").attr("checked",true); 
				
				//$('#ownertype').val(obj.ownertype);
				$("input[name='ownertype'][value='"+obj.ownertype+"']").attr("checked",true); 
				
				$('#propertypapertype').val(obj.propertypapertype);
				$('#propertypaperid').val(obj.propertypaperid);
				$('#address').val(obj.address);
				
				if(obj.propertymap != null)
				{
					var propertymapArr = obj.propertymap.split(VALUE_SPLITTER);				for(var j=0;j<propertymapArr.length;j++)				{					if(propertymapArr[j] != '')					{						$('#propertymappicktable').append('<tr><td>'+propertymapArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+propertymapArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					}				
					
					}
				}	
				
				$('#ofcommunity').html('');
				var filterArr = [];				
				filterArr[0] = "<option value='"+obj.ofcommunity+"'>"+obj.ofcommunity+"</option>";				
				$('#ofcommunity').html(filterArr.join(''));
				$('#ofcommunity').attr("readonly","readonly");
				
				$('#ofresidebuilding').html('');
				var filterArr = [];				
				filterArr[0] = "<option value='"+obj.ofresidebuilding+"'>"+obj.ofresidebuildingname+"</option>";				
				$('#ofresidebuilding').html(filterArr.join(''));
				$('#ofresidebuilding').attr("readonly","readonly");
				
				$('#ofunit').html('');
				var filterArr = [];				
				filterArr[0] = "<option value='"+obj.ofunit+"'>"+obj.ofunit+"</option>";				
				$('#ofunit').html(filterArr.join(''));
				$('#ofunit').attr("readonly","readonly");
				
				$('#note').val(obj.note);
			}
		});
}

function gobackPage()
{
	curId = '';
	
	$('#main-content').load("./jcsqsj/jcsqsj.html", function () {
		
    });
	
}


function addOrUpdate()
{
	$.post(getContextPath()+"/roomController/addOrUpdate",
	{
		id:curId,
		dataid:$('#dataid').val(),
		number:$('#number').val(),
		ofcommunity:$('#ofcommunity').val(),
		ofresidebuilding:$('#ofresidebuilding').val(),
		ofunit:$('#ofunit').val(),
		level:$('#level').val(),
		status:$('#status').val(),
		isgrouporiented:$('#isgrouporiented').val(),
		ownertype:$('#ownertype').val(),
		propertypapertype:$('#propertypapertype').val(),
		propertypaperid:$('#propertypaperid').val(),
		address:$('#address').val(),
		propertymap:$('#propertymap').val(),
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
