

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
	  data: JSON.stringify({id:'ofcommunity',params:[{'enname':'ofcommunity',value:curUserOrgId}]}),
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
					
					filterArr[i+1] = "<option value='" + filter.id + "'>" + filter.name + "</option>";						
				}
				$('#ofcommunity').html(filterArr.join(''));
				
				if(curId != '')
					viewDetail(curId);
			}
			else
			{
				jError("获取小区列表失败!",{
					VerticalPosition : 'center',
					HorizontalPosition : 'center'
				});
			}
		},
	  dataType: "json"
	});
	
	
});


function viewDetail(id)
{
	$.get(getContextPath()+"/jc_qtbuildingController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				$('#dataid').val(obj.dataid);
				$('#name').val(obj.name);
				$('#address').val(obj.address);
				$('#year').val(obj.year);
				$('#purpose').val(obj.purpose);
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
				//$('#status').val(obj.status);
				$("input[name='status'][value='"+obj.status+"']").attr("checked",true); 
				
				var picturesArr = obj.pictures.split(VALUE_SPLITTER);				for(var j=0;j<picturesArr.length;j++)				{					if(picturesArr[j] != '')					{						$('#picturespicktable').append('<tr><td>'+picturesArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					}				}				$('#note').val(obj.note);
				$('#orginbuilding').val(obj.orginbuilding);

					
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
	
	$.post(getContextPath()+"/jc_qtbuildingController/addOrUpdate",
	{
		id:curId,
		dataid:$('#dataid').val(),
		name:$('#name').val(),
		address:$('#address').val(),
		year:$('#year').val(),
		purpose:$('#purpose').val(),
		propertyyears:$('input:radio[name="propertyyears"]:checked').val(),//$('#propertyyears').val(),
		propertyrights:$('#propertyrights').val(),
		heatingsystem:$('input:radio[name="heatingsystem"]:checked').val(),//$('#heatingsystem').val(),
		ofcommunity:$('#ofcommunity').val(),
		buildtype:$('input:radio[name="buildtype"]:checked').val(),//$('#buildtype').val(),
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
		status:$('input:radio[name="status"]:checked').val(),//$('#status').val(),
		pictures:$('#pictures').val(),
		note:$('#note').val(),
		orginbuilding:$('#orginbuilding').val()
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
