

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
	$.get(getContextPath()+"/populationgroupController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#dateid').val(obj.dateid);
				$('#name').val(obj.name);
				$('#contactorg').val(obj.contactorg);
				$('#regstatus').val(obj.regstatus);
				$('#regunit').val(obj.regunit);
				$('#competentunit').val(obj.competentunit);
				$('#address').val(obj.address);
				$('#establishdate').val(obj.establishdate);
				$('#persionsize').val(obj.persionsize);
				$('#introduction').val(obj.introduction);
				$('#leadername').val(obj.leadername);
				$('#leaderid').val(obj.leaderid);
				$('#leadermobile').val(obj.leadermobile);
				$('#contact').val(obj.contact);
				$('#contactmobile').val(obj.contactmobile);
				var picturesArr = obj.pictures.split(VALUE_SPLITTER);				for(var j=0;j<picturesArr.length;j++)				{					if(picturesArr[j] != '')					{						$('#picturespicktable').append('<tr><td>'+picturesArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					}				}				$('#note').val(obj.note);

					
			}
		});
}

function gobackPage()
{
	
	curId = '';
	
	$('#main-content').load("./jcsqsj/populationgroup/populationgroup.html", function () {
		
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
	
	
	$.post(getContextPath()+"/populationgroupController/addOrUpdate",
	{
		id:curId,
				dateid:$('#dateid').val(),
		name:$('#name').val(),
		contactorg:$('#contactorg').val(),
		regstatus:$('#regstatus').val(),
		regunit:$('#regunit').val(),
		competentunit:$('#competentunit').val(),
		address:$('#address').val(),
		establishdate:$('#establishdate').val(),
		persionsize:$('#persionsize').val(),
		introduction:$('#introduction').val(),
		leadername:$('#leadername').val(),
		leaderid:$('#leaderid').val(),
		leadermobile:$('#leadermobile').val(),
		contact:$('#contact').val(),
		contactmobile:$('#contactmobile').val(),
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
