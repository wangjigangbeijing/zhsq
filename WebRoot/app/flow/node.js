
var curUserType = -1;

$(document).ready(function (){
	
	$('#btnAdd').click(addnode);
	
	loadnodes();
});

var dataTable;

function loadnodes()
{
	$.get(getContextPath()+"/flowController/getnodelist",
	function(result){
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
					{ "data": "nodename" ,"sClass":"text-center"},
					{ "data": "node_category" ,"sClass":"text-center"},
					{ "data": "nodetype" ,"sClass":"text-center"},
					{ "data": "canback" ,"sClass":"text-center",
						mRender : function(data,type,full){
							if(full.canback == 0) return "否";
							return "是";
						}
					},
					{ "data": "cangoon" ,"sClass":"text-center",
						mRender : function(data,type,full){
							if(full.cangoon == 0) return "否";
							return "是";
						}
					},
					{ "data": "" ,"sClass":"text-center"}
				],
				columnDefs: [ 
				{
					className: 'control',
					orderable: false,
					targets:   5,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" onclick=\"editData('"+full.id+"')\" class=\"btn btn-info btn-xs\"><i class=\"fa fa-pencil\"></i>修改</a>&nbsp;";

						btn += "<a href=\"#\" onclick=\"deleteData('"+full.id+"')\"  class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash-o\"></i>删除</a>";
						
						return btn;
					}
					}
				]
			} );
			
			/*$('#tableSelect').html('');
			var userArr = [];
			
			for(var i=0;i<obj.tableList.length;i++)
			{
				var table = obj.tableList[i];
				
				userArr[i] = "<option value='" + table.id + "'>" + table.tableName + "</option>";						
			}
			$('#tableSelect').html(userArr.join(''));
			*/
		}
		else
		{
			jError(obj.errMsg,{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'});
		}
	});
}

function addnode()
{
	$('#main-content').load("./flow/nodeDetail.html", function () {
		//EditableTable.init();
		//loadStyle();//如果放到ready方法里会出现加载了layerStyle被覆盖的情况
    });
}

function deleteData(id)
{
	$.confirm({
		title:"删除确认",
		text:"确认删除该业务节点?",
		confirm: function(button) {
			
			$.post(getContextPath()+"/flowController/delete",
			{
				id:id
			},
			function(result){
				var obj = jQuery.parseJSON(result);  
				if(obj.success)
				{
					jSuccess("业务节点删除成功!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'
					});
					
					loadnodes();
				}
				else
				{
					jError("业务节点删除成功!",{
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

var curId = '';

function editData(id)
{
	curId = id;
	
	$('#main-content').load("./flow/nodeDetail.html", function () {
		
    });
}
