

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
	$.get(getContextPath()+"/jc_rubbishController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#name').val(obj.name);
				//$('#type').val(obj.type);
				//$('#kind').val(obj.kind);
				
				$("input[name='type'][value='"+obj.type+"']").attr("checked",true); 
				$("input[name='kind'][value='"+obj.kind+"']").attr("checked",true); 
				
if(obj.catagory != null){
	var catagoryArr = obj.catagory.split(VALUE_SPLITTER);
	for(var j=0;j<catagoryArr.length;j++)
	{
		if(catagoryArr[j] != '')
		{
			$("input[name='catagory'][value='"+catagoryArr[j]+"']").attr('checked','true');
		}
	}	
}	
				$('#address').val(obj.address);
				$('#department').val(obj.department);
				$('#departtel').val(obj.departtel);
				$('#cleartime').val(obj.cleartime);
				var pictureArr = obj.picture.split(VALUE_SPLITTER);				for(var j=0;j<pictureArr.length;j++)				{					if(pictureArr[j] != '')					{						$('#picturepicktable').append('<tr><td>'+pictureArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+pictureArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					}				}				$('#note').val(obj.note);

					
			}
		});
}

function gobackPage()
{
	
	curId = '';
	
	$('#main-content').load("./jcsqsj/jc_rubbish/jc_rubbish.html", function () {
		
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
	var valcatagoryArr=new Array();
$('input[name="catagory"]:checked').each(function()
{      
	valcatagoryArr.push($(this).val());//向数组中添加元素  		
});
var valcatagory = valcatagoryArr.join(VALUE_SPLITTER);//将数组元素连接起来以构建一个字符串

	
	$.post(getContextPath()+"/jc_rubbishController/addOrUpdate",
	{
		id:curId,
		name:$('#name').val(),
		type:$('input:radio[name="type"]:checked').val(),//$('#type').val(),
		kind:$('input:radio[name="kind"]:checked').val(),//$('#kind').val(),
		catagory:valcatagory,
		address:$('#address').val(),
		department:$('#department').val(),
		departtel:$('#departtel').val(),
		cleartime:$('#cleartime').val(),
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
