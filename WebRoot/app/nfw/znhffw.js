
var curUserType = -1;

$(document).ready(function (){
	
	$('#btnAdd').click(addznhffw);
	
	$('#btnSearch').click(loadznhffw);
	
	loadznhffw();
	
});

var dataTable;

function loadznhffw()
{
	$('#btnSearch').attr('disabled','disabled');
	
	var dsrxm = $('#dsrxm').val();
	var hfsfcg = $('#hfsfcg').val();
	var dsrsfmy = $('#dsrsfmy').val();
	
	$.get(getContextPath()+'/znhffwController/getznhfdatalist?jsjbid='+jsjbId + '&dsrxm=' + dsrxm + '&hfsfcg=' + hfsfcg + '&dsrsfmy=' + dsrsfmy,
	function(result){
		$('#btnSearch').removeAttr('disabled');
		var obj = jQuery.parseJSON(result);  
		console.log(obj);
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
					{ "data": "dsrxm" ,"sClass":"text-center"},
					{ "data": "dsrlxdh" ,"sClass":"text-center"},
					{ 'data': 'hfsfcg' ,'sClass':'text-center'},
					{ 'data': 'dsrsfmy' ,'sClass':'text-center'},
					{ "data": "hfr" ,"sClass":"text-center"},
					{ "data": "hfsj" ,"sClass":"text-center"},
					{ "data": "" ,"sClass":"text-center"}
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
					targets:   6,//从0开始
					mRender : function(data,type,full){
						
						var btn = "<a href=\"#\" onclick=\"viewData('"+full.id+"')\"  class=\"btn-success lk-d\"><i class=\"fa fa-trash-o\"></i>查看</a>&nbsp;";
						
						btn += "<a href=\"#\" onclick=\"editData('"+full.id+"')\" class=\"btn-info lk-a\"><i class=\"fa fa-pencil\"></i>修改</a>&nbsp;";

						btn += "<a href=\"#\" onclick=\"deleteData('"+full.id+"')\"  class=\"btn-danger lk-b\"><i class=\"fa fa-trash-o\"></i>删除</a>&nbsp;";
						
						
						
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

var uuid = '';
function addznhffw()
{
	//uuid = guid();
	$('#main-content').load("./nfw/znhffwDetail.html", function () {
		//EditableTable.init();
		//loadStyle();//如果放到ready方法里会出现加载了layerStyle被覆盖的情况
    });
	
	curTableId = '';
}

function viewData(id)
{
	curId = id;
	$('#main-content').load("./nfw/znhffwDetail2.html", function () {
		
    });
}

function editData(id)
{
	curId = id;
	$('#main-content').load("./nfw/znhffwDetail.html", function () {
		
    });
}

function deleteData(id)
{
	$.confirm({
		title:"删除确认",
		text:"确认删除数据?",
		confirm: function(button) {
			
			$.post(getContextPath()+"/znhffwController/delete",
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
					
					loadznhffw();
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

function gobackPage()
{
	$('#main-content').load("./nfw/jsjbfw.html", function () {
		
	});
}
