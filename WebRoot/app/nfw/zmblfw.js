
var curUserType = -1;

$(document).ready(function (){
	
	$('#btnAdd').click(addzmblfw);
	
	$('#btnSearch').click(loadzmblfw);
	
	loadzmblfw();
	
	loadstatus();
	
});

var dataTable;

function loadzmblfw()
{
	$('#btnSearch').attr('disabled','disabled');
	var zmsxdl = $('#zmsxdl').val();
	var zmsxxl = $('#zmsxxl').val();
	var blrname = $('#blrname').val();
	var status=$('#status').val();
	
	$.get(getContextPath()+'/zmblfwController/getzmbldatalist?zmsxdl=' + zmsxdl + '&zmsxxl=' + zmsxxl + '&blrname=' + blrname + '&status=' + status,
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
				"data":obj.list,
				"columns": [
					{ 'data': 'zmsxdl' ,'sClass':'text-center'},
					{ 'data': 'zmsxxl' ,'sClass':'text-center'},
					{ 'data': 'blrname' ,'sClass':'text-center'},
					{ 'data': 'lxdh' ,'sClass':'text-center'},
					{ 'data': 'blqd' ,'sClass':'text-center'},
					{ 'data': 'blsj' ,'sClass':'text-center'},
					{ 'data': 'status' ,'sClass':'text-center',
						mRender : function(data,type,full){
							var btn = "<span style='color: blue;'>"+full.status+"</span>";
							return btn;
						}
					},
					{ 'data': '' ,'sClass':'text-center'}
				],
				columnDefs: [ /*{
					className: 'control',
					orderable: false,
					targets:   0,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" onclick=\"viewDetail('"+full.id+"')\" data-toggle=\"tooltip\" title=\"查看\">"+full.tableName+"</a>";
						return btn;
					}
					},*/
					{
					className: 'control',
					orderable: false,
					targets:   7,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" onclick=\"editData('"+full.id+"')\" class=\"btn-info lk-a\"><i class=\"fa fa-pencil\"></i>修改</a>&nbsp;";

						btn += "<a href=\"#\" onclick=\"deleteData('"+full.id+"')\"  class=\"btn-danger lk-b\"><i class=\"fa fa-trash-o\"></i>删除</a>&nbsp;";
						
						btn += "<a href=\"#\" onclick=\"enterFlow('"+full.id+"')\"  class=\"btn-primary lk-c\"><i class=\"fa fa-flask-o\"></i>业务流</a>";
						
						return btn;
					}
					}
				]
			} );
	
		}
		else
		{
			jError(obj.errMsg,{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'});
		}
	});
}

function loadstatus()
{
	$.get(getContextPath()+"/flowtemplateController/loadtemplatestatus?service=zmbl",
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				for(var i = 0; i < obj.list.length; i++){
					var s = "<option value='" + obj.list[i] + "'>" + obj.list[i] + "</option>";
					$("#status").append(s);
				}
			}
		});
}

function editData(id)
{
	curId = id;
	$('#main-content').load("./nfw/zmblfwDetail.html", function () {
		
    });
}

function deleteData(id)
{
	$.confirm({
		title:"删除确认",
		text:"确认删除数据?",
		confirm: function(button) {
			
			$.post(getContextPath()+"/zmblfwController/delete",
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
					
					loadzmblfw();
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

function enterFlow(id){
	curId = id;
	
	$('#main-content').load("./nfw/zmblfwFlowDetail.html", function () {
		
    });
	
}

var uuid = '';
function addzmblfw()
{
	//uuid = guid();
	$('#main-content').load("./nfw/zmblfwDetail.html", function () {
		//EditableTable.init();
		//loadStyle();//如果放到ready方法里会出现加载了layerStyle被覆盖的情况
    });
	
	curTableId = '';
}

function deleteTable(tableId)
{
	$.confirm({
		title:"删除确认",
		text:"确认删除表单?",
		confirm: function(button) {
			
			$.post(getContextPath()+"/tableController/deleteTable",
			{
				tableId:tableId
			},
			function(result){
				var obj = jQuery.parseJSON(result);  
				if(obj.success)
				{
					jSuccess("表单删除成功!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'
					});
					
					loadTable();
				}
				else
				{
					jError("表单删除成功!",{
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

function tableManagement(tableId)
{
	curTableId = tableId;
	
	$('#main-content').load("./table/tableDetail.html", function () {
		getTableInfo();
    });
}

function tableData(tableId)
{
	curTableId = tableId;
	
	$('#main-content').load("./table/tableData.html", function () {
		
    });
}
/*
function generateCode(tableId)
{
	$.post(getContextPath()+"/templateController/generateCode",
	{
		tableId:tableId
	},
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			jSuccess("代码生成成功!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
			
		}
		else
		{
			jError("代码生成失败!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
		}
	});
}


function guid() {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
    var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
    return v.toString(16);
  });
}
*/