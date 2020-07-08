

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
	
	var params = {id:'ofcommunity'};
	
	var paramsStr = JSON.stringify(params);
	
	$.ajax({
	  type: 'POST',
	  url: getContextPath()+"/dictionaryController/getDataOfDic",
	  data: paramsStr,
	  contentType: "application/json",
	  success:function(result){
				
		if(result.success)
		{
			$('#ofcommunity').html('');
			var filterArr = [];
						
			for(var i=0;i<result.value.length;i++)
			{
				var filter = result.value[i];
				
				filterArr[i] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
			}
			$('#ofcommunity').html(filterArr.join(''));
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



	/*
	$.post(getContextPath()+"/dictionaryController/getDataOfDic",
	{
		
	},
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			$('#ofcommunity').html('');
			var filterArr = [];
						
			for(var i=0;i<obj.value.length;i++)
			{
				var filter = obj.value[i];
				
				filterArr[i] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
			}
			$('#ofcommunity').html(filterArr.join(''));
		}
		else
		{
			jError("获取社区列表失败!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
		}
	});
	*/
	
	
});


function viewDetail(id)
{
	$.get(getContextPath()+"/residebuildingController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#dataid').val(obj.dataid);
				$('#name').val(obj.name);
				$('#address').val(obj.address);
				$('#year').val(obj.year);
				$('#propertyyears').val(obj.propertyyears);
				$('#propertyrights').val(obj.propertyrights);
				$('#heatingsystem').val(obj.heatingsystem);
				$('#ofcommunity').val(obj.ofcommunity);
				$('#buildtype').val(obj.buildtype);
				$('#buildframework').val(obj.buildframework);
				$('#constructiontype').val(obj.constructiontype);
				$('#units').val(obj.units);
				$('#levels').val(obj.levels);
				$('#elevators').val(obj.elevators);
				$('#area').val(obj.area);
				$('#developer').val(obj.developer);
				$('#propertyowner').val(obj.propertyowner);
				$('#propertyownertel').val(obj.propertyownertel);
				$('#user').val(obj.user);
				$('#usertel').val(obj.usertel);
				$('#propertymanage').val(obj.propertymanage);
				$('#propertymanagecontact').val(obj.propertymanagecontact);
				$('#propertymanagecontacttel').val(obj.propertymanagecontacttel);
				$('#longitude').val(obj.longitude);
				$('#latitude').val(obj.latitude);
				$('#status').val(obj.status);
				var picturesArr = obj.pictures.split(VALUE_SPLITTER);				for(var j=0;j<picturesArr.length;j++)				{					if(picturesArr[j] != '')					{						$('#picturespicktable').append('<tr><td>'+picturesArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					}				}				$('#note').val(obj.note);
				$('#familiesinbuilding').val(obj.familiesinbuilding);

					
			}
		});
}

function gobackPage()
{
	
	curId = '';
	
	$('#main-content').load("./jcsqsj/residebuilding/residebuilding.html", function () {
		
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
	$.post(getContextPath()+"/residebuildingController/addOrUpdate",
	{
		id:curId,
		dataid:$('#dataid').val(),
		name:$('#name').val(),
		address:$('#address').val(),
		year:$('#year').val(),
		propertyyears:$('#propertyyears').val(),
		propertyrights:$('#propertyrights').val(),
		heatingsystem:$('#heatingsystem').val(),
		ofcommunity:$('#ofcommunity').val(),
		buildtype:$('#buildtype').val(),
		buildframework:$('#buildframework').val(),
		constructiontype:$('#constructiontype').val(),
		units:$('#units').val(),
		levels:$('#levels').val(),
		elevators:$('#elevators').val(),
		area:$('#area').val(),
		developer:$('#developer').val(),
		propertyowner:$('#propertyowner').val(),
		propertyownertel:$('#propertyownertel').val(),
		user:$('#user').val(),
		usertel:$('#usertel').val(),
		propertymanage:$('#propertymanage').val(),
		propertymanagecontact:$('#propertymanagecontact').val(),
		propertymanagecontacttel:$('#propertymanagecontacttel').val(),
		longitude:$('#longitude').val(),
		latitude:$('#latitude').val(),
		status:$('#status').val(),
		pictures:$('#pictures').val(),
		note:$('#note').val(),
		familiesinbuilding:$('#familiesinbuilding').val()
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
