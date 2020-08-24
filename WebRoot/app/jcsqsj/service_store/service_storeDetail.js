

$(document).ready(function (){
	
	//$('#btnAdd').click(ShowAddModal);
	
	$('.dpYears').datepicker({
		autoclose: true
	});
	
	//$('#btnReset').click(Reset);
	
	//$('#btnSearch').click(load);
	
	//load();
	
	if(curId != '')
		viewDetail(curId);
});


function viewDetail(id)
{
	$.get(getContextPath()+"/service_storeController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#dateid').val(obj.dateid);
				$('#name').val(obj.name);
				$('#type').val(obj.type);
				$('#address').val(obj.address);
				$('#socialcode').val(obj.socialcode);
				$('#businessscope').val(obj.businessscope);
				$('#businessarea').val(obj.businessarea);
				//$('#ischain').val(obj.ischain);
				
				$("input[name='ischain'][value='"+obj.ischain+"']").attr("checked",true); 
				$("input[name='is24hours'][value='"+obj.is24hours+"']").attr("checked",true); 
				$("input[name='status'][value='"+obj.status+"']").attr("checked",true); 
				
				$('#otherbusiness').val(obj.otherbusiness);
				$('#contact').val(obj.contact);
				$('#contacttel').val(obj.contacttel);
				$('#opentime').val(obj.opentime);
				$('#closetime').val(obj.closetime);
				$('#is24hours').val(obj.is24hours);
				$('#longitude').val(obj.longitude);
				$('#latitude').val(obj.latitude);
				//$('#status').val(obj.status);
				$('#pictures').val(obj.pictures);
				var picturesArr = obj.pictures.split(VALUE_SPLITTER);
				var header = getContextPath()+"/fileController/download?fileName=";				for(var j=0;j<picturesArr.length;j++)				{if(picturesArr[j] != '')	{	var url = header + picturesArr[j];						if(url.indexOf('.pdf') >= 0 || url.indexOf('.PDF') >= 0){							var uurl = getContextPath() + "/dist/js/pdf.html?param=" + url;							$('#picturespicktable').append('<tr><td><a href="' + uurl + '" target="_blank")>' + picturesArr[j] + '</a></td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');											}						else {							$('#picturespicktable').append('<tr><td><a href="' + url + '" data-lightbox="' + picturesArr[j] + '" data-title="' + picturesArr[j] + '" style="color:#64A600; font-size: 12px;">'+picturesArr[j]+'</a></td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');											}					}				}				$('#note').val(obj.note);

					
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
	$.post(getContextPath()+"/service_storeController/addOrUpdate",
	{
		id:curId,
		dateid:$('#dateid').val(),
		name:$('#name').val(),
		type:$('#type').val(),
		address:$('#address').val(),
		socialcode:$('#socialcode').val(),
		businessscope:$('#businessscope').val(),
		businessarea:$('#businessarea').val(),
		//ischain:$('#ischain').val(),
		ischain:$('input:radio[name="ischain"]:checked').val(),
		otherbusiness:$('#otherbusiness').val(),
		contact:$('#contact').val(),
		contacttel:$('#contacttel').val(),
		opentime:$('#opentime').val(),
		closetime:$('#closetime').val(),
		//is24hours:$('#is24hours').val(),
		is24hours:$('input:radio[name="is24hours"]:checked').val(),
		longitude:$('#longitude').val(),
		latitude:$('#latitude').val(),
		//status:$('#status').val(),
		status:$('input:radio[name="status"]:checked').val(),
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
