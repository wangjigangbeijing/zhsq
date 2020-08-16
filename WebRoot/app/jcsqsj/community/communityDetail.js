

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
	$.get(getContextPath()+"/communityController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				console.log(obj);
				$('#modalDetail').show();
				
				$('#dataid').val(obj.dataid);
				$('#name').val(obj.name);
				
				//$('#buildtype').val(obj.buildtype);
				$("input[name='buildtype'][value='"+obj.buildtype+"']").attr("checked",true);
				$('#type').val(obj.type);
				$('#year').val(obj.year);
				$('#buildings').val(obj.buildings);
				$('#gates').val(obj.gates);
				$('#groundparking').val(obj.groundparking);
				$('#underparking').val(obj.underparking);
				$('#longitude').val(obj.longitude);
				$('#latitude').val(obj.latitude);
				//$('#status').val(obj.status);
				$("input[name='status'][value='"+obj.status+"']").attr("checked",true);
				$('#address').val(obj.address);
				
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
	
	
	$.post(getContextPath()+"/communityController/addOrUpdate",
	{
		id:curId,
		dataid:$('#dataid').val(),
		name:$('#name').val(),
		buildtype:$("input[name='buildtype']:checked").val(),//$('#buildtype').val(),
		type:$('#type').val(),
		year:$('#year').val(),
		buildings:$('#buildings').val(),
		gates:$('#gates').val(),
		groundparking:$('#groundparking').val(),
		underparking:$('#underparking').val(),
		longitude:$('#longitude').val(),
		latitude:$('#latitude').val(),
		status:$("input[name='status']:checked").val(),//$('#status').val(),
		address:$('#address').val(),
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
