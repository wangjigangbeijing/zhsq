
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
	else
	{
		$.get(getContextPath()+"/tableController/getTable?tableId="+curTableId,
			function(result){
		
				var obj = jQuery.parseJSON(result);  
				
				if(obj.success == true)
				{
					generatePageByAttributeList(obj.attributeList);
				}
				else
				{
					alert('失败了');
				}
		});
	}	
});

function viewDetail()
{
	$('#modalDetail').show();
	
	$.get(getContextPath()+"/tableController/getTable?tableId="+curTableId,
		function(result){
	
			var obj = jQuery.parseJSON(result);  
			
			generatePageByAttributeList(obj.attributeList);
			
			$.get(getContextPath()+"/dataController/getData?tableId="+curTableId+"&dataId="+curId,
			function(result){
				
				var objData = jQuery.parseJSON(result);  
				if(objData.success)
				{
					$('#modalDetail').show();
					
					for(var i=obj.attributeList.length - 1;i>=0 ;i--)
					{
						if(objData[obj.attributeList[i].enName] == null)
							continue;
								
						$('#'+obj.attributeList[i].enName).val(objData[obj.attributeList[i].enName]);
						
						if(obj.attributeList[i].componentsType == '下拉' || obj.attributeList[i].componentsType == '数字-整型'  || obj.attributeList[i].componentsType == '数字-浮点' 
								|| obj.attributeList[i].componentsType == '单行文本' || obj.attributeList[i].componentsType == '多行文本' || obj.attributeList[i].componentsType == '字典')
						{
							$('#'+obj.attributeList[i].enName).val(objData[obj.attributeList[i].enName]);
						}
						else if(obj.attributeList[i].componentsType == '单选')
						{
							$("input[name='"+obj.attributeList[i].enName+"'][value='"+objData[obj.attributeList[i].enName]+"']").attr('checked','true');
						}
						else if(obj.attributeList[i].componentsType == '多选')
						{
							var checkArr = objData[obj.attributeList[i].enName].split(VALUE_SPLITTER);
							
							for(var j=0;j<checkArr.length;j++)
							{
								if(checkArr[j] != '')
								{
									$("input[name='"+obj.attributeList[i].enName+"'][value='"+checkArr[j]+"']").attr('checked','true');
								}
							}
						}
						else if(obj.attributeList[i].componentsType == '图片' || obj.attributeList[i].componentsType == '文件')
						{
							var fjArr = objData[obj.attributeList[i].enName].split(VALUE_SPLITTER);
								
							for(var j=0;j<fjArr.length;j++)
							{
								if(fjArr[j] != '')
								{
									$('#'+obj.attributeList[i].enName+'picktable').append('<tr><td>'+fjArr[j]+'</td><td>上传成功</td>'+
										'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+fjArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+
										'</tr>');
								}
							}					
						}
					}
				}
			});
	});
}

function gobackPage()
{
	$('#main-content').load("./table/tableData.html", function () {
        
    });
}

var curAttribtueList = [];

function generatePageByAttributeList(attributeList)
{
	curAttribtueList = attributeList;
	
	for(var i= attributeList.length - 1;i>=0 ;i--)
	{				
		var component = //'<form class="form-horizontal" role="form"> ' +
							'<div class="form-group row"> ' +
							  '<label class="col-sm-2 col-form-label">' +attributeList[i].zhName+ '</label> ' +
							  '<div class="col-md-9"> ';
		
		//单选 下拉	多选	文件	图片	数字	单行文本	多行文本
		if(attributeList[i].componentsType == '单选')
		{
			var valArr = attributeList[i].values.split(VALUE_SPLITTER);
			
			for (var j=0;j<valArr.length ;j++ ) 
			{ 
				component += ' <label class="radio-inline"> '+
								'<input type="radio" name="' +attributeList[i].enName + '" value="'+valArr[j]+'"> '+valArr[j]+
							'</label>';
			}
		}
		else if(attributeList[i].componentsType == '下拉')
		{
			component += '<select class="form-control field" name="' +attributeList[i].enName + '" id="' +attributeList[i].enName + '">';//input-sm 
			
			var valArr = attributeList[i].values.split(VALUE_SPLITTER);
			
			for (var j=0;j<valArr.length ;j++ ) 
			{ 
				component += ' <option value="'+valArr[j]+'">'+valArr[j]+'</option>';
			}
			component += ' </select>';
		}
		else if(attributeList[i].componentsType == '字典')
		{
			$.get(getContextPath()+"/dictionaryController/getDataOfDic?id=",
				function(result){
					
					var obj = jQuery.parseJSON(result);  
					if(obj.success)
					{
						component += '<select class="form-control field" name="' +attributeList[i].enName + '" id="' +attributeList[i].enName + '">';//input-sm 
						
						//var valArr = attributeList[i].values.split(VALUE_SPLITTER);
						
						for (var j=0;j<value.length ;j++ ) 
						{ 
							component += ' <option value="'+value[j].key+'">'+value[j],value+'</option>';
						}
						component += ' </select>';
					}
				}
			);
		}
		else if(attributeList[i].componentsType == '多选')
		{
			var valArr = attributeList[i].values.split(VALUE_SPLITTER);
			
			for (var j=0;j<valArr.length ;j++ ) 
			{ 
				component += ' <label class="checkbox-inline"> '+
								'<input type="checkbox" name="' +attributeList[i].enName + '" value="'+valArr[j]+'"> '+valArr[j]+
							'</label>';
			}
		}
		else if(attributeList[i].componentsType == '文件')
		{
			component += '<div id="'+attributeList[i].enName +'div" class="wu-example">' + 
				'<!--用来存放文件信息-->' + 
				'<input type="text" class="form-control field" name="' +attributeList[i].enName + '" id="' +attributeList[i].enName + '"/ style="display:none;"> ' + 
				
				'<table class="table" id="'+attributeList[i].enName +'picktable"><thead><tr><th>名称</th><th>状态</th><th>操作</th></tr></thead><tbody></tbody></table>' + 
				
				//'<div id="' +attributeList[i].enName + 'picklist" class="uploader-list"></div>' + 
				
				'<div class="btns">' + 
				'	<div id="' +attributeList[i].enName + 'pick">选择文件</div>' + 
				//'	<button id="ctlBtn" class="btn btn-default">开始上传</button>' + 
				'</div>' + 
			'</div>';
		}
		else if(attributeList[i].componentsType == '图片')
		{
			//component += '<div id="' +attributeList[i].enName + '" style="height:100px;width:300px;">选择图片</div>';
			
			component += '<div id="'+attributeList[i].enName +'div" class="wu-example">' + 
				'<!--用来存放文件信息-->' + 
				'<input type="text" class="form-control field" name="' +attributeList[i].enName + '" id="' +attributeList[i].enName + '"/ style="display:none;"> ' + 
				
				'<table class="table" id="'+attributeList[i].enName +'picktable"><thead><tr><th>名称</th><th>状态</th><th>操作</th></tr></thead><tbody></tbody></table>' + 
				
				//'<div id="' +attributeList[i].enName + 'picklist" class="uploader-list"></div>' + 
				
				'<div class="btns">' + 
				'	<div id="' +attributeList[i].enName + 'pick">选择文件</div>' + 
				//'	<button id="ctlBtn" class="btn btn-default">开始上传</button>' + 
				'</div>' + 
			'</div>';
		}
		else if(attributeList[i].componentsType == '数字-整型' || attributeList[i].componentsType == '数字-浮点')
		{
			component += '<input type="number" class="form-control field" name="' +attributeList[i].enName + '" id="' +attributeList[i].enName + '"> ';
		}
		else if(attributeList[i].componentsType == '单行文本')
		{
			component += '<input type="text" class="form-control field" name="' +attributeList[i].enName + '" id="' +attributeList[i].enName + '"> ';
		}
		else if(attributeList[i].componentsType == '多行文本')
		{
			component += '<textarea class="form-control ckeditor field" name="' +attributeList[i].enName + '" id="' +attributeList[i].enName + '" rows="6"></textarea>';
		}
		else if(attributeList[i].componentsType == '日期')
		{
			
			component += '<div data-date-viewmode="years" data-date-format="yyyy-mm-dd" class="input-append date dpYears">' + 
				'<input type="text" readonly="" value="" size="16" class="form-control" id="' +attributeList[i].enName + '">' + 
				'	<span class="input-group-btn add-on">' + 
				'		<button class="btn btn-danger" type="button"><i class="fa fa-calendar"></i></button>' + 
				'	</span>' + 
			'</div>';
			//'<span class="help-block">Select date</span>';
			
		}
		else if(attributeList[i].componentsType == '时间')
		{
			
			/*component += '<div data-date-viewmode="years" data-date-format="yyyy-mm-dd" class="input-append date dpYears">' + 
				'<input type="text" readonly="" value="" size="16" class="form-control" id="' +attributeList[i].enName + '">' + 
				'	<span class="input-group-btn add-on">' + 
				'		<button class="btn btn-danger" type="button"><i class="fa fa-calendar"></i></button>' + 
				'	</span>' + 
			'</div>';*/
			//'<span class="help-block">Select date</span>';
			/*<div class="input-group date form_datetime-component">
                                              <input type="text" class="form-control" readonly="" size="16">
                                                <span class="input-group-btn">
                                                <button type="button" class="btn btn-danger date-set"><i class="fa fa-calendar"></i></button>
                                                </span>
                                          </div>*/
		}
		
		component += '</div> ' +
					'</div>';
					
		$('#dataDiv').prepend(component);
		
		if(attributeList[i].componentsType == '图片' || attributeList[i].componentsType == '文件')
		{
			var uploader = WebUploader.create({
				
				// 选完文件后，是否自动上传。
				auto: true,
			 
				// swf文件路径
				//swf: BASE_URL + '/js/Uploader.swf',
				
				// 文件接收服务端
				server: getContextPath()+'/fileController/upload',
			 
				// 选择文件的按钮。可选。
				// 内部根据当前运行是创建，可能是input元素，也可能是flash.
				pick: '#' +attributeList[i].enName + 'pick',
			 
				// 只允许选择图片文件。
				/*accept: {
					title: 'Images',
					extensions: 'gif,jpg,jpeg,bmp,png',
					mimeTypes: 'image/*'
				},*/
				formData:{
					attributeName:attributeList[i].enName
				}
			});
			
			uploader.on( 'fileQueued', function( file ) {
				
				/*$('#'+file.source._refer[0].id+'list').append( '<div id="' + file.id + '" class="item">' +
					'<h4 class="info">' + file.name + '</h4>' +
					'<p class="state">等待上传...</p>' +
				'</div>' );*/
				
				$('#'+file.source._refer[0].id+'table').append('<tr><td>'+file.name+'</td><td id='+file.id+'>等待上传...</td>'+
									'<td><button id="'+file.id+'Button" type="button" class="btn btn-success btn-xs"><i class="fa fa-check"></i></button></td>'+
									'</tr>');
			});

			uploader.on( 'uploadSuccess', function( file,response ) {
				
				$( '#'+response.attributeName ).val(response.fileName+VALUE_SPLITTER+$('#'+response.attributeName ).val());
				
				console.log($( '#'+response.attributeName ).val());
				
				//$( '#'+file.id ).find('p.state').text('已上传');
				$( '#'+file.id ).text('已上传');
				
				$('#'+file.id+'Button').attr('onclick', onclick='javascript:downloadAttach("'+response.fileName+'");return false;');
				
			});

			uploader.on( 'uploadError', function( file,response ) {
				//$( '#'+file.id ).find('p.state').text('上传出错');
				
				
			});

		}
		else if(attributeList[i].componentsType == '日期')
		{
			$('.dpYears').datepicker({
				autoclose: true
			});
		}
	}
}


function addOrUpdateData()
{
	var dataJson = '[';
	var fileIdArr = [];
	
	for(var i=0;i<curAttribtueList.length;i++)
	{	
		var val = '';
		//
		//单选 下拉	多选	文件	图片	数字	单行文本	多行文本
		if(curAttribtueList[i].componentsType == '单选')
		{
			//var valArr=new Array();  
			
			$('input[name="'+curAttribtueList[i].enName+'"]:checked').each(function()
			{      
				val = $(this).val();//向数组中添加元素  			
			});			
		}
		else if(curAttribtueList[i].componentsType == '多选')
		{
			var valArr=new Array();  
		
			$('input[name="'+curAttribtueList[i].enName+'"]:checked').each(function()
			{      
				valArr.push($(this).val());//向数组中添加元素  			
			});
			
			val = valArr.join(VALUE_SPLITTER);//将数组元素连接起来以构建一个字符串 
		}
		else if(curAttribtueList[i].componentsType == '文件')
		{
			val = $('#'+curAttribtueList[i].enName).val();
		}
		else if(curAttribtueList[i].componentsType == '图片')
		{			
			val = $('#'+curAttribtueList[i].enName).val();
		}
		else if(curAttribtueList[i].componentsType == '日期')
		{			
			val = $('#'+curAttribtueList[i].enName).val();
		}
		else if(curAttribtueList[i].componentsType == '下拉' || curAttribtueList[i].componentsType == '数字-整型' || curAttribtueList[i].componentsType == '数字-浮点' 
					|| curAttribtueList[i].componentsType == '单行文本' || curAttribtueList[i].componentsType == '多行文本' || curAttribtueList[i].componentsType == '字典')
		{
			val = $('#'+curAttribtueList[i].enName).val();
		}
		
		dataJson += "{attrId:'"+curAttribtueList[i].id+"',attrName:'"+curAttribtueList[i].enName+"',attrValue:'"+val+"'},";
	}
	
	dataJson = dataJson.substring(0,dataJson.length - 1);
	
	dataJson += ']';

	var submitPayload = 
	{
		dataId:curId,
		tableId:curTableId,
		data:dataJson
	};
	
	$.ajaxFileUpload  
	(  
		{
			url: getContextPath()+"/dataController/addOrUpdateData", //用于文件上传的服务器端请求地址  
			secureuri: false,           //一般设置为false  
			fileElementId: fileIdArr, //文件上传控件的id属性  <input type="file" id="file" name="file" /> 注意，这里一定要有name值     
			dataType: 'json',//返回值类型 一般设置为json  
			data:submitPayload,
			complete: function (data, status) {//只要完成即执行，最后执行  
			
				debugger;
			
				var obj = jQuery.parseJSON(data.responseText);
				if(obj.success == false)
				{
					jError("信息提交失败!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'});
				}
				else
				{
					jSuccess("提交成功!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'});
					//clearInput();
					
					//closeModalDetail();
					
					//loadData();
				}
			},  
			success: function (data, status)  //服务器成功响应处理函数  
			{
				
			},  
			error: function (data, status, e)//服务器响应失败处理函数  
			{  
				jError("信息提交失败!",{
					VerticalPosition : 'center',
					HorizontalPosition : 'center'});
			}  
		}  
	)
}

/*
function deleteData(dataId)
{
	$.confirm({
		title:"删除确认",
		text:"确认删除数据?",
		confirm: function(button) {
			
			$.post(getContextPath()+"/dataController/deleteData",
			{
				tableId:curTableId,
				dataId:dataId
			},
			function(result){
				var obj = jQuery.parseJSON(result);  
				if(obj.success)
				{
					jSuccess("数据删除成功!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'
					});
					
					loadData();
				}
				else
				{
					jError("数据删除失败!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'
					});
				}
			});
			
		},
		cancel: function(button) {
			//alert("You aborted the operation.");
		},
		confirmButton: "删除",
		cancelButton: "放弃"
	});
	
}

function generateQueryDiv(tableId)
{
	$.get(getContextPath()+"/tableController/getTable?tableId="+tableId,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				
				curAttribtueList = obj.attributeList;
	
				for(var i=obj.attributeList.length - 1;i>=0;i--)
				{
					var attribute = obj.attributeList[i];
					
					if(attribute.supportQuery == '是')
					{
						var tempComponent = '<label id="streetLabel" class="col-sm-1 col-sm-1 control-label" style="text-align:right;">' + attribute.zhName + '</label>';
						
						tempComponent += '<div class="col-sm-2"> ';
						
						if(attribute.componentsType == '下拉')
						{
							//attribute.values
							tempComponent += ' <select class="form-control m-bot15 formQuery" id="'+attribute.enName+'">';
							tempComponent += ' <option value="">全部</option>';
							
							var valArr = attribute.values.split(VALUE_SPLITTER);
							
							for (var j=0;j<valArr.length ;j++ ) 
							{ 
								tempComponent += ' <option value="'+valArr[j]+'">'+valArr[j]+'</option>';
							}
			
							tempComponent += '</select>';
						}				  
						else// if (attribute.componentsType == '')
						{
							tempComponent += '<input type="text" class="form-control formQuery" id="'+attribute.enName+'">';
						}
						
						tempComponent += '</div>';
						
						$('#queryDiv').prepend(tempComponent);						
					}
					
					$('#editable-sample').dataTable().fnAddData({
						id:attribute.id,
						attrZHName:attribute.zhName,
						attrENName:attribute.enName,
						attrComponentType:attribute.componentsType,
						attrValue:attribute.values,
						attrDBType:attribute.dbType,
						attrDBLength:attribute.dbLength,
						seq:attribute.seq,
						appDisplay:attribute.appDisplay,			
						supportQuery:attribute.supportQuery,
						editField:'<a id="inspectorAnchor" class="edit'+uuid+' btn btn-info btn-xs" href=""><i class="fa fa-pencil"></i></a>&nbsp;' +
									'<a href="" class="delete'+uuid+' btn btn-danger btn-xs"><i class="fa fa-trash-o"></i></a>'
					});
				}
				
				$('#editable-sample').dataTable().fnDraw();
			}
		});
}
*/


function downloadAttach(fileName)
{
	var url = getContextPath()+"/fileController/download?fileName="+fileName;
	
	window.open(encodeURI(url));
}

