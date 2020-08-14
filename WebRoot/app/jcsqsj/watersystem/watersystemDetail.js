

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
	$.get(getContextPath()+"/watersystemController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				$('#dateid').val(obj.dateid);
				$('#name').val(obj.name);
				$('#deptname1').val(obj.deptname1);
				$('#deptname2').val(obj.deptname2);
				$('#deptname3').val(obj.deptname3);
				$('#leadername').val(obj.leadername);
				$('#leadertel').val(obj.leadertel);
				$('#leaderorg').val(obj.leaderorg);
				$('#longitude').val(obj.longitude);
				$('#latitude').val(obj.latitude);
				//$('#status').val(obj.status);
				$("input[name='status'][value='"+obj.status+"']").attr("checked",true); 
				var picturesArr = obj.pictures.split(VALUE_SPLITTER);				for(var j=0;j<picturesArr.length;j++)				{					if(picturesArr[j] != '')					{						$('#picturespicktable').append('<tr><td>'+picturesArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					}				}				$('#note').val(obj.note);

					
			}
		});
}

function gobackPage()
{
	curId = '';
	
	$('#main-content').load("./jcsqsj/watersystem/watersystem.html", function () {
		
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
	
	
	$.post(getContextPath()+"/watersystemController/addOrUpdate",
	{
		id:curId,
				dateid:$('#dateid').val(),
		name:$('#name').val(),
		deptname1:$('#deptname1').val(),
		deptname2:$('#deptname2').val(),
		deptname3:$('#deptname3').val(),
		leadername:$('#leadername').val(),
		leadertel:$('#leadertel').val(),
		leaderorg:$('#leaderorg').val(),
		longitude:$('#longitude').val(),
		latitude:$('#latitude').val(),
		status:$('input:radio[name="status"]:checked').val(),//$('#status').val(),
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
