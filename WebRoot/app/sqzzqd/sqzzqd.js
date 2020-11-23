
//var curUserType = -1;

$(document).ready(function (){
	
	$('#btnAdd').click(addsqzzqd);
	
	$('#btnSearch').click(loadsqzzqd);
	
	loadsqzzqd();
});

var dataTable;

function loadsqzzqd()
{
	$('#btnSearch').attr('disabled','disabled');
	
	var gzzz = $('#gzzzQry').val();
	var zzy = $('#zzyQry').val();
	var lb = $('#lbQry').val();

	$.get(getContextPath()+"/sqzzqdController/load?gzzz="+gzzz+"&zzy="+zzy+"&lb="+lb,
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
					{ "data": "bh" ,"sClass":"text-center"},
					{ "data": "gzzzShort" ,"sClass":"text-center"},
					{ "data": "zzly" ,"sClass":"text-center"},
					{ "data": "zzy" ,"sClass":"text-center"},
					{ "data": "lb" ,"sClass":"text-center"},
					{ "data": "yjflfgmcShort" ,"sClass":"text-center"},
					{ "data": "" ,"sClass":"text-center"}
				],
				columnDefs: [ {
					className: 'control',
					orderable: false,
					targets:   1,//从0开始
					mRender : function(data,type,full){
						var btn = "<span data-toggle=\"tooltip\" title=\""+full.gzzz+"\">"+full.gzzzShort+"</span>";
						return btn;
					}
					},
					{
					className: 'control',
					orderable: false,
					targets:   5,//从0开始
					mRender : function(data,type,full){
						var btn = "<span  data-toggle=\"tooltip\" title=\""+full.yjflfgmc+"\">"+full.yjflfgmcShort+"</span>";
						return btn;
					}
					},
					{
					className: 'control',
					orderable: false,
					targets:   6,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" onclick=\"view('"+full.id+"')\" class=\"lk-a\"><i class=\"fa fa-pencil\"></i>查看</a>&nbsp;";

						btn += "<a href=\"#\" onclick=\"edit('"+full.id+"')\"  class=\"lk-c\"><i class=\"fa fa-trash-o\"></i>编辑</a>&nbsp;";

						btn += "<a href=\"#\" onclick=\"deleteObj('"+full.id+"')\"  class=\"lk-b\"><i class=\"fa fa-trash-o\"></i>删除</a>";
						
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

function addsqzzqd()
{
	$('#main-content').load("./sqzzqd/sqzzqdDetail.html", function () {
		//EditableTable.init();
		//loadStyle();//如果放到ready方法里会出现加载了layerStyle被覆盖的情况
    });
}

function deleteObj(id)
{
	$.confirm({
		title:"删除确认",
		text:"确认删除事项?",
		confirm: function(button) {
			
			$.post(getContextPath()+"/sqzzqdController/delete",
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
					
					loadsqzzqd();
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

var curId = '';

function view(id)
{
	curId = id;
	
	$('#main-content').load("./sqzzqd/sqzzqdDetail.html", function () {
		$('#confirmBtn').hide();
		
		$("select").attr("disabled","disabled");
		$("textarea").attr("disabled","disabled");
		$("input").attr("disabled","disabled");
		$("#picturespick").hide();
		
		$("#cancelBtn").text('返回');
    });
}


function edit(id)
{
	curId = id;
	
	$('#main-content').load("./sqzzqd/sqzzqdDetail.html", function () {
		
    });
}