

$(document).ready(function (){
	
	if(haveRight('jc_pubfacilities_gy_add') == false)
	{
		$('#btnAdd1').hide();
		$('#btnAdd2').hide();
	}
	
	if(haveRight('jc_pubfacilities_gy_exp') == false)
	{
		$('#btnExport1').hide();
		$('#btnExport2').hide();
	}
	
	$('#btnAdd1').click(ShowAddModal);
	$('#btnAdd2').click(ShowAddModal);
	
	$('.dpYears').datepicker({
		autoclose: true
	});
	
	//$('#btnReset').click(Reset);
	
	$('#btnSearch1').click(load);
	$('#btnSearch2').click(load);
	
	$('#btnExport1').click(exportData);
	$('#btnExport2').click(exportData);
	
	load();
});

var curId;

var dataGridTable;

function load()
{
	$('#btnSearch').attr('disabled','disabled');

	var searchtype = $("#searchtype").val();

	if(searchtype == 1){
		$('#btnSearch1').attr('disabled','disabled');
	}
	else {
		$('#btnSearch2').attr('disabled','disabled');
	}
	 var type = $('#typeQuery').val();
	if(searchtype == 2){
		type = $('#typeQuery2').val();
	}

	var objid = $('#objidQuery').val();
	var deptname1 = $('#deptname1Query').val();
	var isincommunity = $('#isincommunityQuery').val();
	var material = $('#materialQuery').val();
	var form = $('#formQuery').val();
	var objState = $('#objStateQuery').val();
	
	lTotalCnt = 0;
			
	if(dataGridTable!=null){  
		dataGridTable.fnClearTable(0);  
	}
	
	dataGridTable = $("#dataTable").dataTable({	
		destroy: true,
		stateSave: false,
		iDisplayLength: 10,
		lengthChange: false,
		"paging": true,
		"lengthChange": false,
		"searching": false,
		"bProcessing" : true,  
		"aaSorting": [[ 0, "asc" ]],  
		"ordering": false,
		"info": true,
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
		"autoWidth": false,
		"bServerSide" : true,
		"sAjaxSource" : getContextPath()+'/jc_pubfacilities_gyController/load',
		"fnServerParams" : function(aoData) {  
			aoData.push({  
				"name" : "statId",  
				"value" : "abc"
			});  
		},  
		"columns": [
					{ 'data': 'type' ,'sClass':'text-center'},
					{ 'data': 'objid' ,'sClass':'text-center'},
					{ 'data': 'locatedsc' ,'sClass':'text-center'},
					{ 'data': 'deptname1' ,'sClass':'text-center'},
					{ 'data': 'isincommunity' ,'sClass':'text-center'},
					{ 'data': 'material' ,'sClass':'text-center'},
					{ 'data': 'form' ,'sClass':'text-center'},
					{ 'data': 'objState' ,'sClass':'text-center'},
					{ 'data': '' ,'sClass':'text-center'}
				],
		columnDefs: [
				{
			className: 'control',
			orderable: false,
			targets:  8,//从0开始
			mRender : function(data,type,full){
				var btn = "<a href=\"#\" onclick=\"viewData('"+full.id+"')\" class=\"btn btn-info btn-xs\"><i class=\"fa fa-pencil\"></i>查看</a>&nbsp;";
				
				if(haveRight('jc_pubfacilities_gy_edit') == true)
					btn += "<a href=\"#\" onclick=\"editData('"+full.id+"')\" class=\"btn btn-primary btn-xs\"><i class=\"fa fa-pencil\"></i>编辑</a>&nbsp;";

				if(haveRight('jc_pubfacilities_gy_del') == true)
					btn += "<a href=\"#\" onclick=\"deleteData('"+full.id+"')\"  class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash-o\"></i>删除</a>";
				
				return btn;
			}
			}
		],
		"fnServerData" : function(sSource, aoData, fnCallback) {  
			$('#btnSearch').attr('disabled','disabled');
			
			$.ajax({
				"type" : 'get',  
				"url" : sSource,  
				"dataType" : "json",  
				"data" : {  
					aoData : JSON.stringify(aoData),
					totalCnt : lTotalCnt,
					objid:objid,
					deptname1:deptname1,
					isincommunity:isincommunity,
					material:material,
					form:form,
					objState:objState
				},
				"success" : function(resp) {
					
					
					if(searchtype == 1){
						$('#btnSearch1').removeAttr('disabled');
					}
					else {
						$('#btnSearch2').removeAttr('disabled');
					}
					
					if(resp.success == true)
					{
						fnCallback(resp);
					
						lTotalCnt = resp.iTotalRecords;
					}
					else
					{
						jError("加载数据出错!",{
							VerticalPosition : 'center',
							HorizontalPosition : 'center'
						});
					}
				},
				"failure":function (result) {
					$('#btnSearch').removeAttr('disabled');
				}
			});  
		}
	});
}

function viewData(id)
{
	curId = id;
	$('#main-content').load("./jcsqsj/jc_pubfacilities_gy/jc_pubfacilities_gyDetail.html", function () {
		$('#confirmBtn').hide();
		
		$("select").attr("disabled","disabled");
		$("textarea").attr("disabled","disabled");
		$("input").attr("disabled","disabled");
		$("#picturespick").hide();
		
		$("#cancelBtn").text('返回');
    });
}

function editData(id)
{
	curId = id;
	$('#main-content').load("./jcsqsj/jc_pubfacilities_gy/jc_pubfacilities_gyDetail.html", function () {
		
    });
}


function ShowAddModal()
{
	//$('#modalDetail').show();
	
	curId = '';
	$('#main-content').load("./jcsqsj/jc_pubfacilities_gy/jc_pubfacilities_gyDetail.html", function () {
		
    });
	
	//$('#modalTitle').text('新增');
	
	//$('#addOrUpdateBtn').text('确定');
	
}

function deleteData(id)
{
	$.confirm({
		title:"删除确认",
		text:"确认删除数据?",
		confirm: function(button) {
			
			$.post(getContextPath()+"/jc_pubfacilities_gyController/delete",
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


function exportData()
{
	var queryStr = '';
	
	var searchtype = $("#searchtype").val();
	if(searchtype == 1){
		$('#btnExport1').attr('disabled','disabled');
	}
	else {
		$('#btnExport2').attr('disabled','disabled');
	}
	 var name = $('#nameQuery').val();
	if(searchtype == 2){
		name = $('#nameQuery2').val();
	}
	if(name != '')
		queryStr += "name like '%"+name+"%' AND ";
 
	 var type = $('#typeQuery').val();
	if(searchtype == 2){
		type = $('#typeQuery2').val();
	}
	if(type != '')
		queryStr += "type = '"+type+"' AND ";
	
	var deptname1 = $('#deptname1Query').val();
	if(deptname1 != '')
		queryStr += "deptname1 like '%"+deptname1+"%' AND ";
	
	var isincommunity = $('#isincommunityQuery').val();
	if(isincommunity != '')
		queryStr += "isincommunity = '"+isincommunity+"' AND ";
	
	var material = $('#materialQuery').val();
	if(material != '')
		queryStr += "material = '"+material+"' AND ";
	
	var form = $('#formQuery').val();
	if(form != '')
		queryStr += "form = '"+form+"' AND ";
	
	var objState = $('#objStateQuery').val();
	if(objState != '')
		queryStr += "objState = '"+objState+"' AND ";
	
	$.post(getContextPath()+"/dataController/exportDataOfTable",
		{
			tableId:'jc_pubfacilities_gy',
			queryStr:queryStr
		},
		function(result){
			
			$('#btnExport1').removeAttr('disabled');
			$('#btnExport2').removeAttr('disabled');
			//$('#loading').hide();
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				window.open(getContextPath()+"/fileController/download?fileName="+encodeURI(obj.fileName));
			}
			else
			{
				jError("数据导出失败,请联系管理员!",{
							VerticalPosition : 'center',
							HorizontalPosition : 'center'
						});
			}
	});
}
