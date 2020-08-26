
var curUserType = -1;

$(document).ready(function (){
	
	$('#btnAdd').click(addsxsqsj);
	
	$('#btnSearch').click(loadsxsqsj);
	
	loadsxsqsj();
	
	$('#sxType').text(curSXType);
	
});

var dataTable;

function loadsxsqsj()
{
	$('#btnSearch').attr('disabled','disabled');
	//var tableName = $('#tableNameQry').val();
	
	$.get(getContextPath()+"/sxsqsjController/load?sxdl="+curSXType,
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
					{ "data": "sxbm" ,"sClass":"text-center"},
					{ "data": "sxmc" ,"sClass":"text-center"},
					{ "data": "sxdl" ,"sClass":"text-center"},
					{ "data": "sxxl" ,"sClass":"text-center"},
					{ "data": "sxdd" ,"sClass":"text-center"},
					{ "data": "sxdsrname" ,"sClass":"text-center"},
					{ "data": "sxkssj" ,"sClass":"text-center"},
					{ "data": "sxjssj" ,"sClass":"text-center"},
					{ "data": "" ,"sClass":"text-center"}
				],
				columnDefs: [
					{
					className: 'control',
					orderable: false,
					targets:   8,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" onclick=\"view('"+full.id+"')\" class=\"btn btn-info btn-xs\"><i class=\"fa fa-pencil\"></i>查看</a>&nbsp;";

						btn += "<a href=\"#\" onclick=\"deleteObj('"+full.id+"')\"  class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash-o\"></i>删除</a>";
						
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
			$('#tableSelect').html(userArr.join(''));*/
	
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
function addsxsqsj()
{
	curId = '';
	$('#main-content').load("./sxsqsj/sxsqsjDetail.html", function () {
		//EditableTable.init();
		//loadStyle();//如果放到ready方法里会出现加载了layerStyle被覆盖的情况
    });
}

var curId = '';

function view(id)
{
	curId = id;
	
	$('#main-content').load("./sxsqsj/sxsqsjDetail.html", function () {
		get();
		
		$('#confirmBtn').hide();
		
		$("select").attr("disabled","disabled");
		$("textarea").attr("disabled","disabled");
		$("input").attr("disabled","disabled");
		$("#picturespick").hide();
    });
}



function deleteObj(id)
{
	$.confirm({
		title:"删除确认",
		text:"确认删除事项?",
		confirm: function(button) {
			
			$.post(getContextPath()+"/sxsqsjController/delete",
			{
				id:id
			},
			function(result){
				var obj = jQuery.parseJSON(result);  
				if(obj.success)
				{
					jSuccess("事项删除成功!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'
					});
					
					loadsxsqsj();
				}
				else
				{
					jError("事项删除成功!",{
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

