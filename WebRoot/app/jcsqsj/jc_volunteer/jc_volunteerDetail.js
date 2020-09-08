

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
	  url: getContextPath()+"/volunteerteamController/load",
	  data: JSON.stringify({id:'ofcommunity'}),
	  contentType: "application/json",
	  success:function(result){
				
			if(result.success)
			{
				$('#of_volunteerteam').html('');
				var filterArr = [];
				
				filterArr[0] = "<option value=''></option>";				
				
				for(var i=0;i<result.list.length;i++)
				{
					var filter = result.list[i];
					
					filterArr[i+1] = "<option value='" + filter.id + "'>" + filter.value + "</option>";						
				}
				$('#of_volunteerteam').html(filterArr.join(''));
				
				if(curId != '')
					viewDetail(curId);
			}
			else
			{
				jError("获取志愿组织列表失败!",{
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
	$.get(getContextPath()+"/jc_volunteerController/get?id="+id,
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
				$('#of_volunteerteam').val(obj.of_volunteerteam);
				$('#join_date').val(obj.join_date);
				$('#certificate_id').val(obj.certificate_id);
				$('#special_skill').val(obj.special_skill);
				//$('#status').val(obj.status);
				
				$("input[name='status'][value='"+obj.status+"']").attr("checked",true); 
				$("input[name='sex'][value='"+obj.sex+"']").attr("checked",true); 
				
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
	
	
	$.post(getContextPath()+"/jc_volunteerController/addOrUpdate",
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
		of_volunteerteam:$('#of_volunteerteam').val(),
		join_date:$('#join_date').val(),
		certificate_id:$('#certificate_id').val(),
		special_skill:$('#special_skill').val(),
		//status:$('#status').val(),
		status:$('input:radio[name="status"]:checked').val(),//$('#status').val(),
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
