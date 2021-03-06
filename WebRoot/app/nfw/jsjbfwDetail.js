﻿var curnodeprocess;

$(document).ready(function (){
	
	$("#characterQry").select2({	 
		multiple: true
	});
	
	$("#characterQry").val('abc').trigger("change"); //赋值一个不存在的value,解决默认选择第一个的问题
	
	$('#btnAdd').click(ShowAddModal);
	
	$('.dpYears').datepicker({
		autoclose: true
	});
	
	//$('#btnReset').click(Reset);
	
	$('#btnSearch').click(load);
	
	//load();
	
	if(curId != '')
		viewDetail(curId);
	
	if(curId == ''){
		loadTemplateProcess();
	}

});

//加载流程节点信息
function loadTemplateProcess(){
	$.get(getContextPath()+"/flowtemplateController/getdatatemplateprocessinfo?service=jsjb&dataid=",
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


function viewDetail(id)
{
	$.get(getContextPath()+"/jsjbfwController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			console.log(obj);
			if(obj.success)
			{
				//$('#modalDetail').show();
				
				//$('#name').val(),
				$('#sjbt').val(obj.data.sjbt);
				$('#sjjjcd').val(obj.data.sjjjcd);
				$('#sjlyjb').val(obj.data.sjlyjb);
				$('#sjly').val(obj.data.sjly);
				$('#sjlybh').val(obj.data.sjlybh);
				$('#sjfl').val(obj.data.sjfl);
				$('#wtfl').val(obj.data.wtfl);
				$('#fsdz').val(obj.data.fsdz);
				$('#dsr').val(obj.data.dsr);
				$('#dsrname').val(obj.data.dsrname);
				$('#dsrdh').val(obj.data.dsrdh);
				$('#sfyqhf').val(obj.data.sfyqhf);
				$('#pdsj').val(obj.data.pdsj);
				$('#clsx').val(obj.data.clsx);
				$('#cljzsj').val(obj.data.cljzsj);
				$('#sjnr').val(obj.data.sjnr);
				$('#bz').val(obj.data.bz);
				
				$('#pictures').val(obj.data.fj);
				
				var header = getContextPath()+"/fileController/download?fileName=";
				
				
				var picturesArr = obj.data.fj.split(VALUE_SPLITTER);				
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
				
				//$('#pictures').val(obj.pictures);
			}
		});
}

function gobackPage()
{
	curId = '';
	
	$('#main-content').load("./nfw/jsjbfw.html", function () {
		
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
	$.post(getContextPath()+"/jsjbfwController/addOrUpdate",
	{
		id:curId,
		sjbt:$('#sjbt').val(),
		sjjjcd:$('#sjjjcd').val(),
		sjlyjb:$('#sjlyjb').val(),
		sjly:$('#sjly').val(),
		sjlybh:$('#sjlybh').val(),
		sjfl:$('#sjfl').val(),
		wtfl:$('#wtfl').val(),
		fsdz:$('#fsdz').val(),
		dsr:$('#dsr').val(),
		dsrname:$('#dsrname').val(),
		dsrdh:$('#dsrdh').val(),
		sfyqhf:$('#sfyqhf').val(),
		pdsj:$('#pdsj').val(),
		clsx:$('#clsx').val(),
		cljzsj:$('#cljzsj').val(),
		sjnr:$('#sjnr').val(),
		bz:$('#bz').val(),
		fj:$('#pictures').val()
	},
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			jSuccess("数据保存成功!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
			
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
			jError("数据修改失败!",{
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

//回退业务
function backData(){
	$.post(getContextPath()+"/flowtemplateController/saveprocessdata",
	{
		dataid:curId,
		processid:curnodeprocess.id,
		stat:curnodeprocess.prevstatus
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


function downloadAttach(fileName)
{
	var url = getContextPath()+"/fileController/download?fileName="+fileName;
	
	window.open(encodeURI(url));
	
	//window.open(getContextPath()+"/fileController/downLoad/"+encodeURI(obj.fileName));
}


