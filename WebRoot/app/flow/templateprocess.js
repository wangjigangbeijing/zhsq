
var curUserType = -1;

var nodelist;

$(document).ready(function (){
	
	console.log("tid: " + curId);
	
	$('#btnAdd').click(addnode);
	
	loadtemplateprocess(curId);
	
	loadtemplate(curId);
	
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
			nodelist = obj.list;
			for(var i = 0; i < obj.list.length; i++)
			{
				var item = "<option value=" + obj.list[i].id + ">" + obj.list[i].nodename + "</option>";
				$("#curnode").append(item);
				$("#prevnode").append(item);
				$("#nextnode").append(item);
			}
			
			$("#prev1").hide();
			$("#prev2").hide();
			$("#next1").hide();
			$("#next2").hide();
		}
	});
}

function loadtemplateprocess(id)
{
	$.get(getContextPath()+"/flowtemplateController/gettemplateprocesslist?templateid=" + id,
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			console.log(obj.list);
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
					{ "data": "prevnodename" ,"sClass":"text-center"},
					{ "data": "nextnodename" ,"sClass":"text-center"},
					{ "data": "" ,"sClass":"text-center"}
				],
				columnDefs: [ 
				{
					className: 'control',
					orderable: false,
					targets:   3,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" onclick=\"deleteData('"+full.id+"')\" class=\"btn btn-info btn-xs\"><i class=\"fa fa-pencil\"></i>删除</a>&nbsp;";
						
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

function loadtemplate(id){
	$.get(getContextPath()+"/flowtemplateController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{				
				$('#tname').html(obj.data.templatename);
				
			}
		});
}

function selectcurnode(){
	var index = $('#curnode').get(0).selectedIndex;
	var nodeinfo = nodelist[index - 1];
	if(nodeinfo.canback == 1){
		$("#prev1").show();
		$("#prev2").show();
	}
	else {
		$("#prev1").hide();
		$("#prev2").hide();
	}
	if(nodeinfo.cangoon == 1){
		$("#next1").show();
		$("#next2").show();
	}
	else {
		$("#next1").hide();
		$("#next2").hide();
	}
}

function add()
{
	$.post(getContextPath()+"/flowtemplateController/addtemplateprocess",
	{
		templateid:curId,
		nodeid:$('#curnode').val(),
		prevnode:$('#prevnode').val(),
		nextnode:$('#nextnode').val()
	},
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			jSuccess("数据添加成功!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
			
			loadtemplateprocess(curId);
		}
		else
		{
			jError("数据添加失败!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
		}
	});
}

function addnode()
{
	$('#main-content').load("./flow/templateDetail.html", function () {
		//EditableTable.init();
		//loadStyle();//如果放到ready方法里会出现加载了layerStyle被覆盖的情况
    });
}

function deleteData(id)
{
	$.confirm({
		title:"删除确认",
		text:"确认删除该流程模板?",
		confirm: function(button) {
			
			$.post(getContextPath()+"/flowtemplateController/delete",
			{
				id:id
			},
			function(result){
				var obj = jQuery.parseJSON(result);  
				if(obj.success)
				{
					jSuccess("流程模板删除成功!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'
					});
					
					loadtemplates();
				}
				else
				{
					jError("流程模板删除成功!",{
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
	
	$('#main-content').load("./flow/templateDetail.html", function () {
		
    });
}
