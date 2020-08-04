

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
	$.get(getContextPath()+"/jc_tc_tcwController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#inparkname').val(obj.inparkname);
				$('#cwtype').val(obj.cwtype);
				$('#location').val(obj.location);
				$('#numbers').val(obj.numbers);
				$('#UseType').val(obj.UseType);
				$('#sizeType').val(obj.sizeType);
				$('#heightType').val(obj.heightType);
				$('#arrange').val(obj.arrange);
				$('#hascharge').val(obj.hascharge);
				$('#chargenum').val(obj.chargenum);
				$('#cwcode').val(obj.cwcode);
				var pcitureArr = obj.pciture.split(VALUE_SPLITTER);				for(var j=0;j<pcitureArr.length;j++)				{					if(pcitureArr[j] != '')					{						$('#pciturepicktable').append('<tr><td>'+pcitureArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+pcitureArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					}				}				$('#note').val(obj.note);

					
			}
		});
}

function gobackPage()
{
	
	curId = '';
	
	$('#main-content').load("./jcsqsj/jc_tc_tcw/jc_tc_tcw.html", function () {
		
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
	
	
	$.post(getContextPath()+"/jc_tc_tcwController/addOrUpdate",
	{
		id:curId,
				inparkname:$('#inparkname').val(),
		cwtype:$('#cwtype').val(),
		location:$('#location').val(),
		numbers:$('#numbers').val(),
		UseType:$('#UseType').val(),
		sizeType:$('#sizeType').val(),
		heightType:$('#heightType').val(),
		arrange:$('#arrange').val(),
		hascharge:$('#hascharge').val(),
		chargenum:$('#chargenum').val(),
		cwcode:$('#cwcode').val(),
		pciture:$('#pciture').val(),
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
