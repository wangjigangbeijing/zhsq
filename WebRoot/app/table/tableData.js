

//获取地址栏参数，name:参数名称
 function getUrlParms(name){
   var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
   var r = window.location.search.substr(1).match(reg);
   if(r!=null)
   return unescape(r[2]);
   return null;
   }
   
$(document).ready(function (){
	
	$('#btnAdd').click(ShowAddModal);
	
	generateQueryDiv(curTableId);
	
});

function loadData()
{
	$('#btnSearch').attr('disabled','disabled');
	
	var queryStr = '';
	
	for(var i=0;i<curAttribtueList.length;i++)
	{
		var attribute = curAttribtueList[i];
				
		if(attribute.supportQuery == '是')
		{
			queryStr += attribute.enName + " = '"+$('#'+attribute.enName).val()+"' AND ";
		}
	}
	
	$.get(getContextPath()+"/tableController/getTable?tableId="+curTableId,
		function(result){
	
			var obj = jQuery.parseJSON(result); 

			if(obj.success)
			{
				$('#dataTable').find("tr").html("");
				
				var attrArr = [];
				var tableHeader = '';
				for(var i=0;i<obj.attributeList.length;i++)
				{
					if(obj.attributeList[i].componentsType == '图片' || obj.attributeList[i].componentsType == '文件')
						continue;
					tableHeader += '<th style="text-align:center;">'+obj.attributeList[i].zhName+'</th>';
					
					attrArr[i] = { "data": obj.attributeList[i].enName ,"sClass":"text-center"};//obj.attributeList.enName;
				}
				
				tableHeader += '<th style="text-align:center;">操作</th>';
				attrArr[attrArr.length] = { "data": "" ,"sClass":"text-center"};
				
				$('#dataTable').find("tr").html(tableHeader);
				
				$.get(getContextPath()+"/dataController/loadDataOfTable?tableId="+curTableId+"&queryStr="+queryStr,
				function(result){
					$('#btnSearch').removeAttr('disabled');
					var obj = jQuery.parseJSON(result);  
					if(obj.success)
					{
						dataTable = $('#dataTable').dataTable( {
							"processing": true,
							"bJQueryUI": false,
							"bFilter": false,
							"bStateSave":true,
							"bAutoWidth": false, //自适应宽度 
							iDisplayLength: 10,
							lengthChange: false,
							"bProcessing": true, 
							"bDestroy":true,
							"bSort": false, //是否使用排序 		
							"oLanguage": { 
								"sProcessing": "正在加载中......", 
								"sLengthMenu": "每页显示 _MENU_ 条记录", 
								"sZeroRecords": "对不起，查询不到相关数据！", 
								"sInfoEmpty":"",
								"sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录", 
								"sInfoFiltered": "数据表中共为 _MAX_ 条记录", 
								"sSearch": "搜索", 
								"oPaginate":  
								{ 
									"sFirst": "首页", 
									"sPrevious": "上一页", 
									"sNext": "下一页", 
									"sLast": "末页" 
								}
							}, //多语言配置					
							"data":obj.dataList,
							"columns": attrArr,
							columnDefs: [ 
								{
								className: 'control',
								orderable: false,
								targets:  attrArr.length - 1,//从0开始
								mRender : function(data,type,full){
									var btn = "<a href=\"#\" onclick=\"viewDetail('"+full.id+"')\" data-toggle=\"tooltip\" title=\"信息\">查看</a>";
									
									btn += "<a href=\"#\" onclick=\"deleteData('"+full.id+"')\" data-toggle=\"tooltip\" title=\"删除\">删除</a>";
									
									return btn;
								}
								}
							]
						} );
					}
				});
			}
			else
			{
				jError(obj.errMsg,{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'});
			}
	});
}


var curId = '';

function viewDetail(id)
{
	curId = id;
	
	/*$('#modalDetail').show();
	
	$.get(getContextPath()+"/tableController/getTable?tableId="+curTableId,
		function(result){
	
			$('#dataDiv').empty();
			
			$('#dataDiv').height(500);
			
			var obj = jQuery.parseJSON(result);  
			
			generatePageByAttributeList(obj.attributeList);
			
			$.get(getContextPath()+"/dataController/getData?tableId="+curTableId+"&dataId="+curId,
			function(result){
				
				var objData = jQuery.parseJSON(result);  
				if(objData.success)
				{
					$('#modalDetail').show();
					
					for(var i=0;i<obj.attributeList.length;i++)
					{
						if(objData[obj.attributeList[i].enName] == null)
							continue;
								
						$('#'+obj.attributeList[i].enName).val(objData[obj.attributeList[i].enName]);
						
						if(obj.attributeList[i].componentsType == '下拉' || obj.attributeList[i].componentsType == '数字' 
								|| obj.attributeList[i].componentsType == '单行文本' || obj.attributeList[i].componentsType == '多行文本')
						{
							$('#'+obj.attributeList[i].enName).val(objData[obj.attributeList[i].enName]);
						}
						else if(obj.attributeList[i].componentsType == '单选')
						{
							$("input[name='"+curAttribtueList[i].enName+"'][value='"+objData[obj.attributeList[i].enName]+"']").attr('checked','true');
						}
						else if(obj.attributeList[i].componentsType == '多选')
						{
							var checkArr = objData[obj.attributeList[i].enName].split(VALUE_SPLITTER);
							
							for(var j=0;j<checkArr.length;j++)
							{
								if(checkArr[j] != '')
								{
									$("input[name='"+curAttribtueList[i].enName+"'][value='"+checkArr[j]+"']").attr('checked','true');
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
	});*/
	
	curId = id;
	$('#main-content').load("./table/tableDataDetail.html", function () {
		
    });
}
/*
function closeModalDetail()
{
	$('#modalDetail').hide();
}*/

var curAttribtueList = [];

function generatePageByAttributeList(attributeList)
{
	for(var i=0;i<attributeList.length;i++)
	{				
		var component = '<form class="form-horizontal" role="form"> ' +
							'<div class="form-group"> ' +
							  '<label class="col-md-2 control-label">' +attributeList[i].zhName+ '</label> ' +
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
		else if(attributeList[i].componentsType == '数字')
		{
			
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
					
		$('#dataDiv').append(component);
		
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
function ShowAddModal()
{
	curId = '';
	$('#main-content').load("./table/tableDataDetail.html", function () {
		
    });
	
}
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
