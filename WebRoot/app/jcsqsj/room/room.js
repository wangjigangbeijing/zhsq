


$(document).ready(function (){
	
	if(haveRight('jc_room_add') == false)
	{
		$('#btnAdd1').hide();
		$('#btnAdd2').hide();
	}
	$('#btnAdd1').click(ShowAddModal);
	$('#btnAdd2').click(ShowAddModal);
	
	$('.dpYears').datepicker({
		autoclose: true
	});
	
	//$('#btnReset').click(Reset);
	
	$('#btnSearch1').click(load);
	$('#btnSearch2').click(load);
	
	load();
	
	$.ajax({
	  type: 'POST',
	  url: getContextPath()+"/dictionaryController/getDataOfDic",
	  data: JSON.stringify({id:'ofcommunity'}),
	  contentType: "application/json",
	  success:function(result){
				
		if(result.success)
		{
			$('#ofcommunityQuery').html('');
			var filterArr = [];
			
			filterArr[0] = "<option value=''></option>";				
			
			for(var i=0;i<result.value.length;i++)
			{
				var filter = result.value[i];
				
				filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
			}
			$('#ofcommunityQuery').html(filterArr.join(''));
			
			$('#ofresidebuildingQuery').html('');				
			$('#ofunitQuery').html('');
			$('#ofroomQuery').html('');
			$('#offamilyQuery').html('');
		}
		else
		{
			jError("获取社区列表失败!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
		}
	},
  dataType: "json"
});

$('#ofcommunityQuery').change(function(){
	
	var ofcommunity = $('#ofcommunityQuery').val();
	
	$.ajax({
	  type: 'POST',
	  url: getContextPath()+"/dictionaryController/getDataOfDic",
	  data: JSON.stringify({id:'ofresidebuilding',params:[{enname:'ofcommunity',value:ofcommunity}]}),
	  contentType: "application/json",
	  success:function(result){
				
			if(result.success)
			{
				$('#ofresidebuildingQuery').html('');
				var filterArr = [];
				filterArr[0] = "<option value=''></option>";				
				for(var i=0;i<result.value.length;i++)
				{
					var filter = result.value[i];
					
					filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
				}
				$('#ofresidebuildingQuery').html(filterArr.join(''));
				
				$('#ofunitQuery').html('');
				$('#ofroomQuery').html('');
				$('#offamilyQuery').html('');
			}
			else
			{
				jError("获取居民楼列表失败!",{
					VerticalPosition : 'center',
					HorizontalPosition : 'center'
				});
			}
		},
	  dataType: "json"
	});
});

$('#ofresidebuildingQuery').change(function(){
	
	var ofresidebuilding = $('#ofresidebuildingQuery').val();
	var ofcommunity = $('#ofcommunityQuery').val();
	
	$.ajax({
	  type: 'POST',
	  url: getContextPath()+"/dictionaryController/getDataOfDic",
	  data: JSON.stringify({id:'ofunit',params:[{enname:'ofcommunity',value:ofcommunity},{enname:'ofresidebuilding',value:ofresidebuilding}]}),
	  contentType: "application/json",
	  success:function(result){
				
			if(result.success)
			{
				$('#ofunit').html('');
				var filterArr = [];
				filterArr[0] = "<option value=''></option>";							
				for(var i=0;i<result.value.length;i++)
				{
					var filter = result.value[i];
					
					filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
				}
				$('#ofunitQuery').html(filterArr.join(''));
				
				$('#ofroomQuery').html('');
				$('#offamilyQuery').html('');
			}
			else
			{
				jError("获取单元列表失败!",{
					VerticalPosition : 'center',
					HorizontalPosition : 'center'
				});
			}
		},
	  dataType: "json"
	});
});



});

var curId;
var dataGridTable;;

function load()
{
	var searchtype = $("#searchtype").val();
	if(searchtype == 1){
		$('#btnSearch1').attr('disabled','disabled');
	}
	else {
		$('#btnSearch2').attr('disabled','disabled');
	}
	 var number  = $('#numberQuery').val();
	if(searchtype == 2){
		number  = $('#numberQuery2').val();
	}
	
	
 var ofcommunity = $('#ofcommunityQuery').val();
 var ofresidebuilding = $('#ofresidebuildingQuery').val();
 var ofunit = $('#ofunitQuery').val();
 var status = $('#statusQuery').val();
 var isgrouporiented = $('#isgrouporientedQuery').val();

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
		"ordering": true,
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
		"sAjaxSource" : getContextPath()+'/roomController/load',
		"fnServerParams" : function(aoData) {  
			aoData.push({  
				"name" : "statId",  
				"value" : "abc"
			});  
		},  
		"columns": [
					//{ 'data': 'dataid' ,'sClass':'text-center'},
					{ 'data': 'number' ,'sClass':'text-center'},
					{ 'data': 'ofcommunity' ,'sClass':'text-center'},
					//{ 'data': 'ofresidebuilding' ,'sClass':'text-center'},
					//{ 'data': 'ofunit' ,'sClass':'text-center'},
					//{ 'data': 'level' ,'sClass':'text-center'},
					{ 'data': 'status' ,'sClass':'text-center'},
					{ 'data': 'isgrouporiented' ,'sClass':'text-center'},
					{ 'data': 'ownertype' ,'sClass':'text-center'},
					//{ 'data': 'propertypapertype' ,'sClass':'text-center'},
					//{ 'data': 'propertypaperid' ,'sClass':'text-center'},
					//{ 'data': 'address' ,'sClass':'text-center'},
					//{ 'data': 'note' ,'sClass':'text-center'},
					{ 'data': '' ,'sClass':'text-center'}
				],
		columnDefs: [
				{
					className: 'control',
					orderable: false,
					targets:  5,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" onclick=\"viewData('"+full.id+"')\" class=\"btn btn-info btn-xs\"><i class=\"fa fa-pencil\"></i>查看</a>&nbsp;";
						
						if(haveRight('jc_room_edit') == true)
							btn += "<a href=\"#\" onclick=\"editData('"+full.id+"')\" class=\"btn btn-primary btn-xs\"><i class=\"fa fa-pencil\"></i>编辑</a>&nbsp;";

						if(haveRight('jc_room_del') == true)
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
					ofcommunity:ofcommunity,
					ofresidebuilding:ofresidebuilding,
					ofunit:ofunit,
					status:status,
					isgrouporiented:isgrouporiented
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
	$('#main-content').load("./jcsqsj/room/roomDetail.html", function () {
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
	$('#main-content').load("./jcsqsj/room/roomDetail.html", function () {
		
    });
}


function ShowAddModal()
{
	curId = '';
	$('#main-content').load("./jcsqsj/room/roomDetail.html", function () {
		
    });
}

function deleteData(id)
{
	$.confirm({
		title:"删除确认",
		text:"确认删除数据?",
		confirm: function(button) {
			
			$.post(getContextPath()+"/roomController/delete",
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


