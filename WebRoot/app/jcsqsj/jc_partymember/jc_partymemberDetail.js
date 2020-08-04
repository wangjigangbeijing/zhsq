

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
	$.get(getContextPath()+"/jc_partymemberController/get?id="+id,
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
				$('#partymembertype').val(obj.partymembertype);
				$('#of_partyorganization').val(obj.of_partyorganization);
				$('#isincommunity').val(obj.isincommunity);
				$('#homeaddress').val(obj.homeaddress);
				$('#zhiwu').val(obj.zhiwu);
				$('#joinpartydate').val(obj.joinpartydate);
				$('#inpartydate').val(obj.inpartydate);
				$('#dyage').val(obj.dyage);
				$('#membership').val(obj.membership);
				$('#islost').val(obj.islost);
				$('#lostdate').val(obj.lostdate);
				$('#movemember').val(obj.movemember);
				$('#moveto').val(obj.moveto);
				var picturesArr = obj.pictures.split(VALUE_SPLITTER);				for(var j=0;j<picturesArr.length;j++)				{					if(picturesArr[j] != '')					{						$('#picturespicktable').append('<tr><td>'+picturesArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					}				}				$('#note').val(obj.note);

					
			}
		});
}

function gobackPage()
{
	
	curId = '';
	
	$('#main-content').load("./jcsqsj/jc_partymember/jc_partymember.html", function () {
		
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
	
	
	$.post(getContextPath()+"/jc_partymemberController/addOrUpdate",
	{
		id:curId,
				name:$('#name').val(),
		idnumber:$('#idnumber').val(),
		sex:$('#sex').val(),
		birthday:$('#birthday').val(),
		age:$('#age').val(),
		mobile:$('#mobile').val(),
		education:$('#education').val(),
		partymembertype:$('#partymembertype').val(),
		of_partyorganization:$('#of_partyorganization').val(),
		isincommunity:$('#isincommunity').val(),
		homeaddress:$('#homeaddress').val(),
		zhiwu:$('#zhiwu').val(),
		joinpartydate:$('#joinpartydate').val(),
		inpartydate:$('#inpartydate').val(),
		dyage:$('#dyage').val(),
		membership:$('#membership').val(),
		islost:$('#islost').val(),
		lostdate:$('#lostdate').val(),
		movemember:$('#movemember').val(),
		moveto:$('#moveto').val(),
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
