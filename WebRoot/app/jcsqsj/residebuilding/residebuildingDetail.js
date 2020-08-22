

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
				//$('#propertyyears').val(obj.propertyyears);				
				$("input[name='propertyyears'][value='"+obj.propertyyears+"']").attr("checked",true); 
				
				$('#propertyrights').val(obj.propertyrights);
				//$('#heatingsystem').val(obj.heatingsystem);
				$("input[name='heatingsystem'][value='"+obj.heatingsystem+"']").attr("checked",true); 
				
				$('#ofcommunity').val(obj.ofcommunity);
				//$('#buildtype').val(obj.buildtype);
				$("input[name='buildtype'][value='"+obj.buildtype+"']").attr("checked",true); 
				
				$('#buildframework').val(obj.buildframework);
				$('#constructiontype').val(obj.constructiontype);
				$('#units').val(obj.units);
				$('#levels').val(obj.levels);
				$('#gralevels').val(obj.gralevels);
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
				$('#pictures').val(obj.pictures);
				var picturesArr = obj.pictures.split(VALUE_SPLITTER); 
				var header = getContextPath()+"/fileController/download?fileName=";				
				for(var j=0;j<picturesArr.length;j++)				{if(picturesArr[j] != '')	{	var url = header + picturesArr[j];						if(url.indexOf('.pdf') >= 0 || url.indexOf('.PDF') >= 0){							var uurl = getContextPath() + "/dist/js/pdf.html?param=" + url;							$('#picturespicktable').append('<tr><td><a href="' + uurl + '" target="_blank")>' + picturesArr[j] + '</a></td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');											}						else {							$('#picturespicktable').append('<tr><td><a href="' + url + '" data-lightbox="' + picturesArr[j] + '" data-title="' + picturesArr[j] + '" style="color:#64A600; font-size: 12px;">'+picturesArr[j]+'</a></td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');											}					}				}				$('#note').val(obj.note);
				$('#familiesinbuilding').val(obj.familiesinbuilding);

					
			}
		});
}

function gobackPage()
{
	
	curId = '';
	
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
	$.post(getContextPath()+"/residebuildingController/addOrUpdate",
	{
		id:curId,
		dataid:$('#dataid').val(),
		name:$('#name').val(),
		address:$('#address').val(),
		year:$('#year').val(),
		propertyyears:$("input[name='propertyyears']:checked").val(), //$('#propertyyears').val(),
		propertyrights:$('#propertyrights').val(),
		heatingsystem:$("input[name='heatingsystem']:checked").val(),//$('#heatingsystem').val(),
		ofcommunity:$('#ofcommunity').val(),
		buildtype:$("input[name='buildtype']:checked").val(),//$('#buildtype').val(),
		buildframework:$('#buildframework').val(),
		constructiontype:$('#constructiontype').val(),
		units:$('#units').val(),
		levels:$('#levels').val(),
		gralevels:$('#gralevels').val(),
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
		//longitude:$('#longitude').val(),
		//latitude:$('#latitude').val(),
		status:$("input[name='status']:checked").val(),//$('#status').val(),
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
