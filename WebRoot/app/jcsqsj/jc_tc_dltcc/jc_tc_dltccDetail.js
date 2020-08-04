

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
	$.get(getContextPath()+"/jc_tc_dltccController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#berthID').val(obj.berthID);
				$('#name').val(obj.name);
				$('#roadname').val(obj.roadname);
				$('#area').val(obj.area);
				$('#rateinfo').val(obj.rateinfo);
				$('#parkeTime').val(obj.parkeTime);
				$('#parknum').val(obj.parknum);
				var pictureArr = obj.picture.split(VALUE_SPLITTER);				for(var j=0;j<pictureArr.length;j++)				{					if(pictureArr[j] != '')					{						$('#picturepicktable').append('<tr><td>'+pictureArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+pictureArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					}				}				$('#note').val(obj.note);
				$('#rateinfo').val(obj.rateinfo);

					
			}
		});
}

function gobackPage()
{
	
	curId = '';
	
	$('#main-content').load("./jcsqsj/jc_tc_dltcc/jc_tc_dltcc.html", function () {
		
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
	
	
	$.post(getContextPath()+"/jc_tc_dltccController/addOrUpdate",
	{
		id:curId,
				berthID:$('#berthID').val(),
		name:$('#name').val(),
		roadname:$('#roadname').val(),
		area:$('#area').val(),
		rateinfo:$('#rateinfo').val(),
		parkeTime:$('#parkeTime').val(),
		parknum:$('#parknum').val(),
		picture:$('#picture').val(),
		note:$('#note').val(),
		rateinfo:$('#rateinfo').val()
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
