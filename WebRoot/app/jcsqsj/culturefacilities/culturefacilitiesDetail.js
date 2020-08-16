

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
});


function viewDetail(id)
{
	$.get(getContextPath()+"/culturefacilitiesController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				$('#dateid').val(obj.dateid);
				$('#name').val(obj.name);
				
				$('#category').val(obj.category);
				$('#address').val(obj.address);
				$('#purpose').val(obj.purpose);
				$('#introduction').val(obj.introduction);
				$('#managedepart').val(obj.managedepart);
				$('#contact').val(obj.contact);
				$('#contacttel').val(obj.contacttel);
				$('#longitude').val(obj.longitude);
				$('#latitude').val(obj.latitude);
				
				$("input[name='tpye'][value='"+obj.tpye+"']").attr("checked",true); 
				$("input[name='status'][value='"+obj.status+"']").attr("checked",true); 
				
				//$('#tpye').val(obj.tpye);
				//$('#status').val(obj.status);
				$('#pictures').val(obj.pictures);
				var picturesArr = obj.pictures.split(VALUE_SPLITTER);  
				var header = getContextPath()+"/fileController/download?fileName=";	

				for(var j=0;j<picturesArr.length;j++)				
				{if(picturesArr[j] != '')	
				{	
					var url = header + picturesArr[j];
					if(url.indexOf('.pdf') >= 0 || url.indexOf('.PDF') >= 0){
						var uurl = getContextPath() + "/dist/js/pdf.html?param=" + url;
						$('#picturespicktable').append('<tr><td><a href="' + uurl + '" target="_blank")>' + picturesArr[j] + '</a></td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');											
					}						
					else 
					{							
						$('#picturespicktable').append('<tr><td><a href="' + url + '" data-lightbox="' + picturesArr[j] + '" data-title="' + picturesArr[j] + '" style="color:#64A600; font-size: 12px;">'+picturesArr[j]+'</a></td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');											
					}					
				}				
			}
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
	
	$.post(getContextPath()+"/culturefacilitiesController/addOrUpdate",
	{
		id:curId,
		dateid:$('#dateid').val(),
		name:$('#name').val(),
		tpye:$('input:radio[name="tpye"]:checked').val(),
		category:$('#category').val(),
		address:$('#address').val(),
		purpose:$('#purpose').val(),
		introduction:$('#introduction').val(),
		managedepart:$('#managedepart').val(),
		contact:$('#contact').val(),
		contacttel:$('#contacttel').val(),
		longitude:$('#longitude').val(),
		latitude:$('#latitude').val(),
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
