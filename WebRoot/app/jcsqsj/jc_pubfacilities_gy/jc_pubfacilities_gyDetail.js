

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
	$.get(getContextPath()+"/jc_pubfacilities_gyController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#dateid').val(obj.dateid);
										
				$('#type').val(obj.type);
				
				
				$('#objid').val(obj.objid);
				$('#objname').val(obj.objname);
				$('#locatedsc').val(obj.locatedsc);
				$('#deptname1').val(obj.deptname1);
				$('#deptname2').val(obj.deptname2);
				$('#deptname3').val(obj.deptname3);
				//$('#isincommunity').val(obj.isincommunity);
				$("input[name='isincommunity'][value='"+obj.isincommunity+"']").attr("checked",true); 
				$('#material').val(obj.material);
				$('#form').val(obj.form);
				$('#objState').val(obj.objState);
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
	
	
	$.post(getContextPath()+"/jc_pubfacilities_gyController/addOrUpdate",
	{
		id:curId,
				dateid:$('#dateid').val(),
		type:$('#type').val(),
		objid:$('#objid').val(),
		objname:$('#objname').val(),
		locatedsc:$('#locatedsc').val(),
		deptname1:$('#deptname1').val(),
		deptname2:$('#deptname2').val(),
		deptname3:$('#deptname3').val(),
		isincommunity:$('input:radio[name="isincommunity"]:checked').val(),//$('#isincommunity').val(),
		material:$('#material').val(),
		form:$('#form').val(),
		objState:$('#objState').val(),
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
