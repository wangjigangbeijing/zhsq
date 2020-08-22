

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
	$.get(getContextPath()+"/jc_sqorgmemberController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				$('#name').val(obj.name);
				$('#idnumber').val(obj.idnumber);
				$('#sex').val(obj.sex);
				$('#birthday').val(obj.birthday);
				$('#age').val(obj.age);
				$('#mobile').val(obj.mobile);
				$('#education').val(obj.education);
				$('#politicalstatus').val(obj.politicalstatus);
				$('#of_sqorganization').val(obj.of_sqorganization);
				$('#orgjob').val(obj.orgjob);
				$('#duty').val(obj.duty);
				$('#unit').val(obj.unit);
				//$('#status').val(obj.status);
				$("input[name='status'][value='"+obj.status+"']").attr("checked",true); 
				$("input[name='sex'][value='"+obj.sex+"']").attr("checked",true); 
				$("input[name='orgjob'][value='"+obj.orgjob+"']").attr("checked",true); 
				var picturesArr = obj.pictures.split(VALUE_SPLITTER);				for(var j=0;j<picturesArr.length;j++)				{					if(picturesArr[j] != '')					{						$('#picturespicktable').append('<tr><td>'+picturesArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					}				}				$('#note').val(obj.note);

					
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
	
	
	$.post(getContextPath()+"/jc_sqorgmemberController/addOrUpdate",
	{
		id:curId,
		name:$('#name').val(),
		idnumber:$('#idnumber').val(),
		//sex:$('#sex').val(),
		birthday:$('#birthday').val(),
		age:$('#age').val(),
		mobile:$('#mobile').val(),
		education:$('#education').val(),
		politicalstatus:$('#politicalstatus').val(),
		of_sqorganization:$('#of_sqorganization').val(),
		//orgjob:$('#orgjob').val(),
		duty:$('#duty').val(),
		unit:$('#unit').val(),
		status:$('input:radio[name="status"]:checked').val(),//$('#status').val(),
		orgjob:$('input:radio[name="orgjob"]:checked').val(),//$('#status').val(),
		sex:$('input:radio[name="sex"]:checked').val(),//$('#status').val(),
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
