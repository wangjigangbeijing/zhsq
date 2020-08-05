

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
	$.get(getContextPath()+"/jc_organizationController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#dataid').val(obj.dataid);
				$('#name').val(obj.name);
				$('#haslicence').val(obj.haslicence);
				$('#socialcode').val(obj.socialcode);
				$('#socialcodedate').val(obj.socialcodedate);
				$('#orgtype').val(obj.orgtype);
				$('#economictype').val(obj.economictype);
				$('#industry').val(obj.industry);
				$('#subordination').val(obj.subordination);
				$('#establishdate').val(obj.establishdate);
				$('#capitaltype').val(obj.capitaltype);
				$('#capital').val(obj.capital);
				$('#businessscope').val(obj.businessscope);
				$('#scale').val(obj.scale);
				$('#regaddress').val(obj.regaddress);
				$('#officeaddress').val(obj.officeaddress);
				$('#ofbizbuilding').val(obj.ofbizbuilding);
				$('#legalname').val(obj.legalname);
				$('#contactname').val(obj.contactname);
				$('#contacttel').val(obj.contacttel);
				$('#moveindate').val(obj.moveindate);
				$('#responsibilityplateno').val(obj.responsibilityplateno);
				$('#hasfirefacilities').val(obj.hasfirefacilities);
				$('#wastedisposal').val(obj.wastedisposal);
				$('#status').val(obj.status);
				var picturesArr = obj.pictures.split(VALUE_SPLITTER);				for(var j=0;j<picturesArr.length;j++)				{					if(picturesArr[j] != '')					{						$('#picturespicktable').append('<tr><td>'+picturesArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					}				}				$('#note').val(obj.note);

					
			}
		});
}

function gobackPage()
{
	
	curId = '';
	
	$('#main-content').load("./jcsqsj/jc_organization/jc_organization.html", function () {
		
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
	
	
	$.post(getContextPath()+"/jc_organizationController/addOrUpdate",
	{
		id:curId,
				dataid:$('#dataid').val(),
		name:$('#name').val(),
		haslicence:$('#haslicence').val(),
		socialcode:$('#socialcode').val(),
		socialcodedate:$('#socialcodedate').val(),
		orgtype:$('#orgtype').val(),
		economictype:$('#economictype').val(),
		industry:$('#industry').val(),
		subordination:$('#subordination').val(),
		establishdate:$('#establishdate').val(),
		capitaltype:$('#capitaltype').val(),
		capital:$('#capital').val(),
		businessscope:$('#businessscope').val(),
		scale:$('#scale').val(),
		regaddress:$('#regaddress').val(),
		officeaddress:$('#officeaddress').val(),
		ofbizbuilding:$('#ofbizbuilding').val(),
		legalname:$('#legalname').val(),
		contactname:$('#contactname').val(),
		contacttel:$('#contacttel').val(),
		moveindate:$('#moveindate').val(),
		responsibilityplateno:$('#responsibilityplateno').val(),
		hasfirefacilities:$('#hasfirefacilities').val(),
		wastedisposal:$('#wastedisposal').val(),
		status:$('#status').val(),
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