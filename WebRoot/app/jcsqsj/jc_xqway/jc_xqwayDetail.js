

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
	  url: getContextPath()+"/communityController/load",
	  data: JSON.stringify({id:'ofcommunity',params:[{'enname':'ofcommunity',value:curUserOrgId}]}),
	  contentType: "application/json",
	  success:function(result){
				
			if(result.success)
			{
				$('#ssxq').html('');
				var filterArr = [];
				
				filterArr[0] = "<option value=''></option>";				
				
				for(var i=0;i<result.list.length;i++)
				{
					var filter = result.list[i];
					
					filterArr[i+1] = "<option value='" + filter.id + "'>" + filter.name + "</option>";						
				}
				$('#ssxq').html(filterArr.join(''));
				
				if(curId != '')
					viewDetail(curId);
			}
			else
			{
				jError("获取小区列表失败!",{
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
	$.get(getContextPath()+"/jc_xqwayController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				$("input[name='type'][value='"+obj.type+"']").attr("checked",true); 
				$("input[name='sfymj'][value='"+obj.sfymj+"']").attr("checked",true); 
				$("input[name='mjlx'][value='"+obj.mjlx+"']").attr("checked",true); 
				
				$('#dataid').val(obj.dataid);
				$('#name').val(obj.name);
				$('#type').val(obj.type);
				$('#sfymj').val(obj.sfymj);
				$('#mjlx').val(obj.mjlx);
				
				debugger;
				
				$('#ssxq').val(obj.ssxq);
				
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
	$.post(getContextPath()+"/jc_xqwayController/addOrUpdate",
	{
		id:curId,
				dataid:$('#dataid').val(),
		name:$('#name').val(),
		type:$('input:radio[name="type"]:checked').val(),
		sfymj:$('input:radio[name="sfymj"]:checked').val(),
		mjlx:$('input:radio[name="mjlx"]:checked').val(),
		ssxq:$('#ssxq').val(),
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
