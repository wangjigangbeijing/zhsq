

$(document).ready(function (){
	
	$('#btnAdd').click(addsms);
	
	/*$('.dpYears').datepicker({
		autoclose: true
	});
	*/
	
	$('#btnSearch').click(load);
	
	load();
});

var curId;

function load()
{
	$('#btnSearch').attr('disabled','disabled');
	
	var smsContent = $('#smsContentQry').val();
	
	$.get(getContextPath()+'/smsController/load?smsContent='+smsContent,
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
				//"sPaginationType": "full_numbers", 
				iDisplayLength: 10,
				lengthChange: false,
				"bProcessing": true, 
				"bDestroy":true,
				"bSort": false, //是否使用排序 		
				"oLanguage": { 
					"sProcessing": "正在加载中......", 
					"sLengthsms": "每页显示 _sms_ 条记录", 
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
				"data":obj.list,
				"columns": [
					{ 'data': 'createDate' ,'sClass':'text-center'},
					{ 'data': 'sendTime' ,'sClass':'text-center'},
					{ 'data': 'messageContent' ,'sClass':'text-center'},
					{ 'data': 'successCnt' ,'sClass':'text-center'},
					{ 'data': 'failCnt' ,'sClass':'text-center'},
					{ 'data': 'sendStatus' ,'sClass':'text-center'},
					{ 'data': '' ,'sClass':'text-center'}

				],
				columnDefs: [ {
					className: 'control',
					orderable: false,
					targets:   2,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" data-toggle=\"tooltip\" title=\""+full.messageContent+"\">"+full.messageContentShort+"</a>";
						return btn;
					}
					},
					{
					className: 'control',
					orderable: false,
					targets:  6,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" onclick=\"viewSendDetail('"+full.id+"')\" data-toggle=\"tooltip\" title=\"查看\">查看</a>";
						
						//btn += "<a href=\"#\" onclick=\"deleteData('"+full.id+"')\" data-toggle=\"tooltip\">删除</a>";
						
						return btn;
					}
					}
				]
			} );
		}
	});
}

function addsms()
{
	$('#main-content').load("./sms/smsDetail.html", function () {
		
		curId = '';
		
		$('#smsContent').val('');
		$('#userName').val('');
		$('#realMobileList').val('');
		$('#mobileList').val('');
		$('#internalMobileList').val('');
		
		//EditableTable.init();
		//loadStyle();//如果放到ready方法里会出现加载了layerStyle被覆盖的情况
    });
}

function viewSendDetail(id)
{
	curId = id;
	$('#main-content').load("./sms/smsSendDetail.html", function () {
		//EditableTable.init();
		//loadStyle();//如果放到ready方法里会出现加载了layerStyle被覆盖的情况
    });
}
/*
function addOrUpdate()
{
	$.post(getContextPath()+"/smsController/addOrUpdate",
	{
		id:curId,
		sms_enname:$('#sms_enname').val(),
		sms_zhname:$('#sms_zhname').val(),
		sms_type:$('#sms_type').val(),
		table_id:$('#table_id').val(),
		file_name:$('#file_name').val(),
		external_url:$('#external_url').val()
	},
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			jSuccess("数据修改成功!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
			
			closeModalDetail();
			
			load();
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
*/
function deleteData(id)
{
	$.confirm({
		title:"删除确认",
		text:"确认删除数据?",
		confirm: function(button) {
			
			$.post(getContextPath()+"/smsController/delete",
			{
				id:id
			},
			function(result){
				var obj = jQuery.parseJSON(result);  
				if(obj.success)
				{
					jSuccess("数据删除成功!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'
					});
					
					load();
				}
				else
				{
					jError(obj.errMsg,{
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


