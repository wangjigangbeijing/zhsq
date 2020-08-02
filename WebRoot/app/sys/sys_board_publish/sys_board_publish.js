

$(document).ready(function (){
	
	$('#btnAdd').click(ShowAddModal);
	
	$('.dpYears').datepicker({
		autoclose: true
	});
	
	//$('#btnReset').click(Reset);
	
	$('#btnSearch').click(load);
	
	load();
});

var curId;

function load()
{
	$('#btnSearch').attr('disabled','disabled');
	 var title = $('#titleQuery').val();
 var category = $('#categoryQuery').val();

	
	$.get(getContextPath()+'/sysBoardPublishController/load?title='+title+'&category='+category+'&',
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
					{ 'data': 'title' ,'sClass':'text-center'},
					{ 'data': 'category' ,'sClass':'text-center'},
					{ 'data': 'facilities' ,'sClass':'text-center'},
					{ 'data': 'contentShort' ,'sClass':'text-center'},
					//{ 'data': 'attachment' ,'sClass':'text-center'},
					{ 'data': 'starttime' ,'sClass':'text-center'},
					{ 'data': 'endtime' ,'sClass':'text-center'},
					{ 'data': 'status' ,'sClass':'text-center'},
					{ 'data': '' ,'sClass':'text-center'}

				],
				columnDefs: [ {
					className: 'control',
					orderable: false,
					targets:   3,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" data-toggle=\"tooltip\" title=\""+full.content+"\">"+full.contentShort+"</a>";
						return btn;
					}
					},
					{
					className: 'control',
					orderable: false,
					targets:  7,//从0开始
					mRender : function(data,type,full){
						//var btn = "<a href=\"#\" onclick=\"editData('"+full.id+"')\" class=\"btn btn-info btn-xs\"><i class=\"fa fa-pencil\">编辑</a>&nbsp;";
						
						//btn += "<a href=\"#\" onclick=\"deleteData('"+full.id+"')\" class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash-o\">删除</a>";
						
						var btn = "<a href=\"#\" onclick=\"view('"+full.id+"')\" class=\"btn btn-info btn-xs\"><i class=\"fa fa-pencil\"></i>查看</a>&nbsp;";
						
						btn += "<a href=\"#\" onclick=\"edit('"+full.id+"')\"  class=\"btn btn-primary btn-xs\"><i class=\"fa fa-trash-o\"></i>编辑</a>&nbsp;";

						btn += "<a href=\"#\" onclick=\"deleteObj('"+full.id+"')\"  class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash-o\"></i>删除</a>";
						
						return btn;
					}
					}
				]
			} );
		}
	});
}


function edit(id)
{
	curId = id;
	$('#main-content').load("./sys/sys_board_publish/sys_board_publishDetail.html", function () {
		
    });
}


function view(id)
{
	curId = id;
	
	$('#main-content').load("./sys/sys_board_publish/sys_board_publishDetail.html", function () {
		$('#confirmBtn').hide();
		
		$("select").attr("disabled","disabled");
		$("textarea").attr("disabled","disabled");
		$("input").attr("disabled","disabled");
		$("#attachmentpick").hide();
		
		$("#cancelBtn").text('返回');
    });
}

function ShowAddModal()
{
	//$('#modalDetail').show();
	
	curId = '';
	$('#main-content').load("./sys/sys_board_publish/sys_board_publishDetail.html", function () {
		
    });
	
	//$('#modalTitle').text('新增');
	
	//$('#addOrUpdateBtn').text('确定');
	
}

function deleteObj(id)
{
	$.confirm({
		title:"删除确认",
		text:"确认删除数据?",
		confirm: function(button) {
			
			$.post(getContextPath()+"/sysBoardPublishController/delete",
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


