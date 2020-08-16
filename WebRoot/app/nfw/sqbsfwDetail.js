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
	
	loadsxdl();
	
	if(curId != '')
		viewDetail(curId);
	
	if(curId == ''){
		loadTemplateProcess();
	}
	
	
});


var attachList = [];

function reloadFiles(fileName)
{
	console.log(fileName);
	attachList.push(fileName);
}


function viewDetail(id)
{
	$.get(getContextPath()+"/sqbsfwController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#blr').val(obj.data.blr);
				
				$('#blrname').val(obj.data.blrname);
				
				$('#lxdh').val(obj.data.lxdh);
				
				$('#blqd').val(obj.data.blqd);
				
				$('#blsxdl').val(obj.data.blsxdl);
				
				$('#blsxxl').val(obj.data.blsxxl);
				
				$('#blsj').val(obj.data.blsj);
				
				$('#xq').val(obj.data.xq);
				
				$('#bz').val(obj.data.bz);
				
				var picturesArr = obj.data.fj.split(VALUE_SPLITTER);	
				
				var header = getContextPath()+"/fileController/download?fileName=";

				for(var j=0;j<picturesArr.length;j++)				
				{					
					if(picturesArr[j] != '')					
					{						
						var url = header + picturesArr[j];
						if(url.indexOf('.pdf') >= 0 || url.indexOf('.PDF') >= 0){
							var uurl = getContextPath() + "/dist/js/pdf.html?param=" + url;
							$('#picturespicktable').append('<tr><td><a href="' + uurl + '" target="_blank")>' + picturesArr[j] + '</a></td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					
						}
						else {
							$('#picturespicktable').append('<tr><td><a href="' + url + '" data-lightbox="' + picturesArr[j] + '" data-title="' + picturesArr[j] + '" style="color:#64A600; font-size: 12px;">'+picturesArr[j]+'</a></td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					
						}

					}
				}
				
				if(curId != ''){
					loadsxxl();
				}
			}
		});
}

function addOrUpdate()
{
	$.post(getContextPath()+"/sqbsfwController/addOrUpdate",
	{
		id:curId,
		blr:$('#blr').val(),
		blrname:$('#blrname').val(),
		lxdh:$('#lxdh').val(),
		blqd:$('#blqd').val(),
		blsxdl:$('#blsxdl').val(),
		blsxxl:$('#blsxxl').val(),
		blsj:$('#blsj').val(),
		xq:$('#xq').val(),
		bz:$('#bz').val(),
		fj:$('#pictures').val()
	},
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			jSuccess("事项创建成功!",{
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
			jError("事项创建失败!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'});
			return ;
		}
	});
}

function loadsxdl(){
	$.get(getContextPath()+"/sqbsfwController/loadsxdl",
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				for(var i = 0; i < obj.list.length; i++){
					var content = "<option value='" + obj.list[i].sxlb + "'>" + obj.list[i].sxlb + "</option>"
					$("#blsxdl").append(content);
				}
			}
			
		});
}

function loadsxxl(){
	$('#blsxxl option').not(":first").remove(); 
	var sxlb = $("#blsxdl").val();
	if(sxlb == ''){
		return;
	}
	$.get(getContextPath()+"/sqbsfwController/loadsxxl?sxlb=" + sxlb,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				for(var i = 0; i < obj.list.length; i++){
					var content = "<option value='" + obj.list[i].sxmc + "'>" + obj.list[i].sxmc + "</option>"
					$("#blsxxl").append(content);
				}
				
				
			}
			
		});
}

//加载流程节点信息
function loadTemplateProcess(){
	$.get(getContextPath()+"/flowtemplateController/getdatatemplateprocessinfo?service=sqbs&dataid=",
		function(result){
			var obj = jQuery.parseJSON(result);  
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
	$('#main-content').load("./nfw/sqbsfw.html", function () {
		
	});
}
