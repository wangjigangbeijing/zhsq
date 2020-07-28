var curnodeprocess;

//获取地址栏参数，name:参数名称
 function getUrlParms(name){
   var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
   var r = window.location.search.substr(1).match(reg);
   if(r!=null)
   return unescape(r[2]);
   return null;
   }

$(document).ready(function (){
	
	if(curId != '')
		viewDetail(curId);
	
	if(curId == ''){
		loadTemplateProcess();
	}
	
});

function viewDetail(id)
{
	$.get(getContextPath()+"/sqcsfwController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#sjbt').val(obj.data.sjbt);
				
				$('#sjcd').val(obj.data.sjcd);
				
				$('#sjdl').val(obj.data.sjdl);
				
				$('#sjxl').val(obj.data.sjxl);
				
				$('#dsr').val(obj.data.dsr);
				
				$('#dsrlxdh').val(obj.data.dsrlxdh);
				
				$('#sjxq').val(obj.data.sjxq);
				
				$('#bz').val(obj.data.bz);
				
				var picturesArr = obj.data.fj.split(VALUE_SPLITTER);				
				for(var j=0;j<picturesArr.length;j++)				
				{					
					if(picturesArr[j] != '')					
					{						
						$('#picturespicktable').append('<tr><td>'+picturesArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					
					}
				}	
			}
		});
}

//加载流程节点信息
function loadTemplateProcess(){
	$.get(getContextPath()+"/flowtemplateController/getdatatemplateprocessinfo?service=sqcs&dataid=",
		function(result){
			var obj = jQuery.parseJSON(result); 
			//console.log(obj);
			if(obj.success)
			{
				if(obj.isfinish){
					
				}
				else {
					curnodeprocess = obj.data;
				}
			}
			else {
				jError("获取业务流程数据失败!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
			}
		});
}

function addOrUpdate()
{
	$.post(getContextPath()+"/sqcsfwController/addOrUpdate",
	{
		id:curId,
		sjbt:$('#sjbt').val(),
		sjcd:$('#sjcd').val(),
		sjdl:$('#sjdl').val(),
		sjxl:$('#sjxl').val(),
		dsr:$('#dsr').val(),
		dsrlxdh:$('#dsrlxdh').val(),
		sjxq: $('#sjxq').val(),
		bz: $('#bz').val(),
		fj:$('#pictures').val()
	},
	function(result){
		var obj = jQuery.parseJSON(result);  
		console.log(obj);
		if(obj.success)
		{
			jSuccess("事项保存成功!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'});
						
			//存储业务流信息		
			if(curId == ''){
				saveProcessInfo(obj.dataid, curnodeprocess.nextstatus);
			}
			else {
				gobackPage();
			}
		}
		else
		{
			jError("事项创建失败!"+data.errMsg,{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'});
			return ;
		}
	});
}

//保存业务流程信息
function saveProcessInfo(dataid, stat){
	$.post(getContextPath()+"/flowtemplateController/saveprocessdata",
	{
		attach: '',
		desc: '',
		type: 2,
		dataid:dataid,
		processid:curnodeprocess.id,
		stat:stat
	},
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{			
			gobackPage();
		}
		else
		{
			jError("保存流程数据失败!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
		}
	});
}

function gobackPage()
{
	curId = '';
	
	$('#main-content').load("./nfw/sqcsfw.html", function () {
		
	});
}