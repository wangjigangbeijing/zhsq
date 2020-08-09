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
	
	$("#characterQry").select2({	 
		multiple: true
	});
	
	$("#characterQry").val('abc').trigger("change"); //赋值一个不存在的value,解决默认选择第一个的问题
	
	if(curId != '')
		viewDetail(curId);
	
	if(curId == ''){
		loadTemplateProcess();
	}
	
});

function viewDetail(id)
{
	$.get(getContextPath()+"/xfslfwController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#bt').val(obj.data.bt);
				
				$('#lb').val(obj.data.lb);
				
				$('#cd').val(obj.data.cd);
				
				$('#fkr').val(obj.data.fkr);
				
				$('#fkrname').val(obj.data.fkrname);
				
				$('#lxdh').val(obj.data.lxdh);
				
				$('#slsj').val(obj.data.slsj);
				
				$('#xq').val(obj.data.xq);
				
				$('#bz').val(obj.data.bz);
				
				$('#yjflfgmc').val(obj.yjflfgmc);
				
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
	$.get(getContextPath()+"/flowtemplateController/getdatatemplateprocessinfo?service=xfsl&dataid=",
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
	$.post(getContextPath()+"/xfslfwController/addOrUpdate",
	{
		id:curId,
		bt:$('#bt').val(),
		lb:$('#lb').val(),
		cd:$('#cd').val(),
		fkr:$('#fkr').val(),
		fkrname:$('#fkrname').val(),
		lxdh:$('#lxdh').val(),
		slsj:$('#slsj').val(),
		xq: $('#xq').val(),
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
						
			console.log("curId:" + curId);
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
	
	$('#main-content').load("./nfw/xfslfw.html", function () {
		
	});
}
