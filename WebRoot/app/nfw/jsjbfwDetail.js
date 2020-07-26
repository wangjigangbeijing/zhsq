var curnodeprocess;

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
	
	if(curId == ''){
		loadTemplateProcess();
	}
	
	var pdsj = $('#pdsj').datepicker({
			format: 'yyyy-mm-dd',
			todayBtn: 'linked',
			onRender: function(date) {
				console.log('onRender startDate');
				//return date.valueOf() < now.valueOf() ? 'disabled' : '';
			}
		}).on('changeDate', function(ev) {
				/*if (ev.date.valueOf() > checkout.date.valueOf()) {
					var newDate = new Date(ev.date)
					newDate.setDate(newDate.getDate() + 1);
					checkout.setValue(newDate);
				}*/
				//checkin.hide();
				//$('.dpd2')[0].focus();
				
				console.log('time Change');
			}).data('datepicker');
			
	var pdsj = $('#cljzsj').datepicker({
			format: 'yyyy-mm-dd',
			todayBtn: 'linked',
			onRender: function(date) {
				console.log('onRender startDate');
				//return date.valueOf() < now.valueOf() ? 'disabled' : '';
			}
		}).on('changeDate', function(ev) {
				/*if (ev.date.valueOf() > checkout.date.valueOf()) {
					var newDate = new Date(ev.date)
					newDate.setDate(newDate.getDate() + 1);
					checkout.setValue(newDate);
				}*/
				//checkin.hide();
				//$('.dpd2')[0].focus();
				
				console.log('time Change');
			}).data('datepicker');

});

//加载流程节点信息
function loadTemplateProcess(){
	$.get(getContextPath()+"/flowtemplateController/getdatatemplateprocessinfo?dataid=",
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
			if(obj.success)
			{
				//$('#modalDetail').show();
				
				//$('#name').val(),
				$('#sjbt').val(obj.sjbt);
				$('#sjjjcd').val(obj.sjjjcd);
				$('#sjlyjb').val(obj.sjlyjb);
				$('#sjly').val(obj.sjly);
				$('#sjlybh').val(obj.sjlybh);
				$('#sjfl').val(obj.sjfl);
				$('#wtfl').val(obj.wtfl);
				$('#fsdz').val(obj.fsdz);
				$('#dsr').val(obj.dsr);
				$('#dsrdh').val(obj.dsrdh);
				$('#sfyqhf').val(obj.sfyqhf);
				$('#pdsj').val(obj.pdsj);
				$('#clsx').val(obj.clsx);
				$('#cljzsj').val(obj.cljzsj);
				$('#sjnr').val(obj.sjnr);
				$('#bz').val(obj.bz);
				
				var picturesArr = obj.fj.split(VALUE_SPLITTER);				
				for(var j=0;j<picturesArr.length;j++)				
				{					
					if(picturesArr[j] != '')					
					{						
						$('#picturespicktable').append('<tr><td>'+picturesArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+picturesArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					
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
			
			console.log(curId);
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


