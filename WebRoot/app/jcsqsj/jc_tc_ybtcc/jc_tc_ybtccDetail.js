

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
	$.get(getContextPath()+"/jc_tc_ybtccController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#parkID').val(obj.parkID);
				$('#parkName').val(obj.parkName);
				$('#tradeName').val(obj.tradeName);
				$('#jztype').val(obj.jztype);
				$('#unitName').val(obj.unitName);
				$('#unitAddres').val(obj.unitAddres);
				$('#area').val(obj.area);
				$('#adminDep').val(obj.adminDep);
				$('#ownerDep').val(obj.ownerDep);
				$('#maintDep').val(obj.maintDep);
				$('#berthNum').val(obj.berthNum);
				$('#UnberthNum').val(obj.UnberthNum);
				$('#GrberthNum').val(obj.GrberthNum);
				$('#ParkingNum').val(obj.ParkingNum);
				$('#openNum').val(obj.openNum);
				$('#parkTime').val(obj.parkTime);
				$('#chpileNum').val(obj.chpileNum);
				$('#BaFreeNum').val(obj.BaFreeNum);
				$('#MecNum').val(obj.MecNum);
if(obj.Chargetype != null){
	var ChargetypeArr = obj.Chargetype.split(VALUE_SPLITTER);
	for(var j=0;j<ChargetypeArr.length;j++)
	{
		if(ChargetypeArr[j] != '')
		{
			$("input[name='Chargetype'][value='"+ChargetypeArr[j]+"']").attr('checked','true');
		}
	}	
}	
				$('#LoLeChtype').val(obj.LoLeChtype);
				$('#ShLeChtype').val(obj.ShLeChtype);
if(obj.DyData != null){
	var DyDataArr = obj.DyData.split(VALUE_SPLITTER);
	for(var j=0;j<DyDataArr.length;j++)
	{
		if(DyDataArr[j] != '')
		{
			$("input[name='DyData'][value='"+DyDataArr[j]+"']").attr('checked','true');
		}
	}	
}	
				var pictureArr = obj.picture.split(VALUE_SPLITTER);				for(var j=0;j<pictureArr.length;j++)				{					if(pictureArr[j] != '')					{						$('#picturepicktable').append('<tr><td>'+pictureArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+pictureArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					}				}				$('#note').val(obj.note);

					
			}
		});
}

function gobackPage()
{
	
	curId = '';
	
	$('#main-content').load("./jcsqsj/jc_tc_ybtcc/jc_tc_ybtcc.html", function () {
		
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
	var valChargetypeArr=new Array();
$('input[name="Chargetype"]:checked').each(function()
{      
	valChargetypeArr.push($(this).val());//向数组中添加元素  		
});
var valChargetype = valChargetypeArr.join(VALUE_SPLITTER);//将数组元素连接起来以构建一个字符串
var valDyDataArr=new Array();
$('input[name="DyData"]:checked').each(function()
{      
	valDyDataArr.push($(this).val());//向数组中添加元素  		
});
var valDyData = valDyDataArr.join(VALUE_SPLITTER);//将数组元素连接起来以构建一个字符串

	
	$.post(getContextPath()+"/jc_tc_ybtccController/addOrUpdate",
	{
		id:curId,
				parkID:$('#parkID').val(),
		parkName:$('#parkName').val(),
		tradeName:$('#tradeName').val(),
		jztype:$('#jztype').val(),
		unitName:$('#unitName').val(),
		unitAddres:$('#unitAddres').val(),
		area:$('#area').val(),
		adminDep:$('#adminDep').val(),
		ownerDep:$('#ownerDep').val(),
		maintDep:$('#maintDep').val(),
		berthNum:$('#berthNum').val(),
		UnberthNum:$('#UnberthNum').val(),
		GrberthNum:$('#GrberthNum').val(),
		ParkingNum:$('#ParkingNum').val(),
		openNum:$('#openNum').val(),
		parkTime:$('#parkTime').val(),
		chpileNum:$('#chpileNum').val(),
		BaFreeNum:$('#BaFreeNum').val(),
		MecNum:$('#MecNum').val(),
		Chargetype:valChargetype,
		LoLeChtype:$('#LoLeChtype').val(),
		ShLeChtype:$('#ShLeChtype').val(),
		DyData:valDyData,
		picture:$('#picture').val(),
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
