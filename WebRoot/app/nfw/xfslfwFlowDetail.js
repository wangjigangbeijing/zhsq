var curnodeprocess;

$(document).ready(function (){
	
	//$('#btnReset').click(Reset);
	
	//$('#btnSearch').click(load);
	
	//load();
	
	if(curId != '')
		viewDetail(curId);
	
	loadTemplateProcess(curId);
	
	loadprocessdata(curId);
	
	loadcuruser();

});

//加载流程节点信息
function loadTemplateProcess(id){
	$.get(getContextPath()+"/flowtemplateController/getdatatemplateprocessinfo?service=xfsl&dataid=" + id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				//console.log(obj);
				if(obj.finish){
					$("#btn_back").hide();
					$("#btn_goon").hide();
					
					$("#resultdiv").hide();
				}
				else {
					curnodeprocess = obj.data;
					
					if(obj.data.prevlabel != null && obj.data.prevlabel != ''){
						$("#btn_back").html(obj.data.prevlabel);
					}
					else {
						$("#btn_back").hide();
					}
					
					if(obj.data.nextlabel != null && obj.data.nextlabel != ''){
						$("#btn_goon").text(obj.data.nextlabel);
					}
					else {
						$("#btn_goon").hide();
					}
				}
				
				$("#curnode").html(obj.data.nodename);
			}
			else {
				jError("获取业务流程数据失败!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
			}
		});
}

//加载所有的业务操作流程
function loadprocessdata(id){
	$.get(getContextPath()+"/flowtemplateController/loadprocessdata?service=xfsl&dataid=" + id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				console.log(obj);
				
				for(var i = 0; i < obj.list.length; i++){
					
					var content = "<div>" +
						"<i class='fas fa-envelope bg-primary'></i>" +
							"<div class='timeline-item'>" + 
								"<span class='time'><i class='far fa-user'></i>" + obj.list[i].name + "</span>" +
								"<h3 class='timeline-header'><a href='#'>" + obj.list[i].nodename + "</a> </h3>" +
								"<div class='timeline-body'>";
								if(obj.list[i].result == '2'){
									content += "操作： " + obj.list[i].nextlabel;
								}
								else {
									content += "操作： " + obj.list[i].prevlabel;
								}
								if(obj.list[i].flowdesc != null && obj.list[i].flowdesc != ''){
									content += "<br> 意见： " + obj.list[i].flowdesc;
								}
								if(obj.list[i].attach != null && obj.list[i].attach != ''){
									
									var txt = '';
									var picturesArr = obj.list[i].attach.split(VALUE_SPLITTER);	
									console.log("size: " + picturesArr.length);
									for(var j=0;j<picturesArr.length;j++)				
									{					
										if(picturesArr[j] != '')					
										{		
											txt += "<a href='#' onclick='javascript:downloadAttach(\"" + picturesArr[j]+ "\");return false;' style='margin-right:10px;'>" + picturesArr[j] + "</a>";
										}
									}	
				
									if(txt.length > 0){
										content += "<br> 附件：" + txt;
									}
								}
								content += "</div>" +
								"<div class='timeline-footer'>" +
								"<span class='time'><i class='far fa-clock'></i>" + obj.list[i].inserttime + "</span>" +
								"</div>" +
							"</div>"
						"</div>"
					$(".timeline").append(content);					
				}
				
				var footer = "<div><i class='far fa-clock bg-gray'></i></div>";
				$(".timeline").append(footer);	
				
			}
			else {
				jError("获取业务流程数据失败!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
			}
		});
}

//获取当前用户
function loadcuruser(){
	$.get(getContextPath()+"/flowtemplateController/getcuruser",
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$("#curuser").html(obj.data);
			}
		});
}


function viewDetail(id)
{
	$.get(getContextPath()+"/xfslfwController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				console.log(obj);
				$('#bt').html(obj.data.bt);				
				$('#lb').html(obj.data.lb);
				$('#cd').html(obj.data.cd);
				$('#fkr').html(obj.data.fkr);
				$('#lxdh').html(obj.data.lxdh);
				$('#xq').html(obj.data.xq);
				$('#bz').html(obj.data.bz);
				/*
				
				var picturesArr = obj.fj.split(VALUE_SPLITTER);				
				for(var j=0;j<picturesArr.length;j++)				
				{					
					if(picturesArr[j] != '')					
					{						
						$('#picturespicktable').append('<tr><td>'+picturesArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					
					}
				}	
				*/			

				$("#showstatus").html(obj.data.status);
				
				//$('#pictures').val(obj.pictures);
			}
		});
}

function gobackPage()
{
	curId = '';
	
	$('#main-content').load("./nfw/xfslfw.html", function () {
		
    });
	
}

function addOrUpdate()
{
	$.post(getContextPath()+"/xfslfwController/addOrUpdate",
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
			
			console.log(obj);
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
		attach: $("#attach").val(),
		desc: $("#desc").val(),
		type: 1,
		dataid:curId,
		processid:curnodeprocess.id,
		stat:curnodeprocess.prevstatus
	},
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{			
			jSuccess("业务处理成功!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
			gobackPage();
		}
		else
		{
			jError("业务处理失败!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
		}
	});
}

//同意业务
function goondata(){
	$.post(getContextPath()+"/flowtemplateController/saveprocessdata",
	{
		attach: $("#attach").val(),
		desc: $("#desc").val(),
		type: 2,
		dataid:curId,
		processid:curnodeprocess.id,
		stat:curnodeprocess.nextstatus
	},
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{			
			jSuccess("业务处理成功!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
			gobackPage();
		}
		else
		{
			jError("业务处理失败!",{
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


