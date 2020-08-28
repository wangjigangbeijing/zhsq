

$(document).ready(function (){
	if(haveRight('jc_residebuilding_add') == false)
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

});

var curId;
var dataGridTable;

function load()
{
	var searchtype = $("#searchtype").val();
	if(searchtype == 1){
		$('#btnSearch1').attr('disabled','disabled');
	}
	else {
		$('#btnSearch2').attr('disabled','disabled');
	}
	 var name = $('#nameQuery').val();
	if(searchtype == 2){
		name = $('#nameQuery2').val();
	}
	
	 var address = $('#addressQuery').val();
	 var propertyyears = $('#propertyyearsQuery').val();
	 var propertyrights = $('#propertyrightsQuery').val();
	 var heatingsystem = $('#heatingsystemQuery').val();
	 var ofcommunity = $('#ofcommunityQuery').val();
	 var buildtype = $('#buildtypeQuery').val();
	 var buildframework = $('#buildframeworkQuery').val();
	 var constructiontype = $('#constructiontypeQuery').val();
	 var status = $('#statusQuery').val();
	
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
		"sAjaxSource" : getContextPath()+'/residebuildingController/load',
		"fnServerParams" : function(aoData) {  
			aoData.push({  
				"name" : "statId",  
				"value" : "abc"
			});  
		},  
		"columns": [
					{ 'data': 'name' ,'sClass':'text-center'},
					{ 'data': 'year' ,'sClass':'text-center'},
					{ 'data': 'propertyyears' ,'sClass':'text-center'},
					{ 'data': 'propertyrights' ,'sClass':'text-center'},
					{ 'data': 'heatingsystem' ,'sClass':'text-center'},
					{ 'data': 'ofcommunity' ,'sClass':'text-center'},
					{ 'data': 'buildtype' ,'sClass':'text-center'},
					//{ 'data': 'buildframework' ,'sClass':'text-center'},					
					{ 'data': 'levels' ,'sClass':'text-center'},
					{ 'data': 'elevators' ,'sClass':'text-center'},					
					{ 'data': 'propertymanage' ,'sClass':'text-center'},
					{ 'data': 'propertymanagecontacttel' ,'sClass':'text-center'},					
					{ 'data': 'status' ,'sClass':'text-center'},
					{ 'data': '' ,'sClass':'text-center'}
				],
		columnDefs: [
				{
					className: 'control',
					orderable: false,
					targets:  12,//从0开始
					mRender : function(data,type,full){
						var btn = '';
						
						btn += "<a href=\"#\" onclick=\"mingqing('"+full.id+"')\" data-toggle=\"tooltip\" class=\"btn btn-success btn-xs\" title=\"查看民情图\">民情图</a>&nbsp;";
						
						btn += "<a href=\"#\" onclick=\"viewData('"+full.id+"')\" class=\"btn btn-info btn-xs\"><i class=\"fa fa-pencil\"></i>查看</a>&nbsp;";
						
						if(haveRight('jc_residebuilding_edit') == true)
							btn += "<a href=\"#\" onclick=\"editData('"+full.id+"')\" data-toggle=\"tooltip\" class=\"btn btn-primary btn-xs\" title=\"查看\">编辑</a>&nbsp;";
						
						if(haveRight('jc_residebuilding_edit') == true)
							btn += "<a href=\"#\" onclick=\"deleteData('"+full.id+"')\" class=\"btn btn-danger btn-xs\" data-toggle=\"tooltip\">删除</a>";

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
					name:name,
					address:address,
					propertyyears:propertyyears,
					propertyrights:propertyrights,
					heatingsystem:heatingsystem,
					ofcommunity:ofcommunity,
					buildtype:buildtype,
					buildframework:buildframework,
					constructiontype:constructiontype,
					status:status
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
	$('#main-content').load("./jcsqsj/residebuilding/residebuildingDetail.html", function () {
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
	$('#main-content').load("./jcsqsj/residebuilding/residebuildingDetail.html", function () {
		
    });
}

function mingqing(id)
{
	curId = id;
	$('#main-content').load("./jcsqsj/residebuilding/residebuildingMinqing.html", function () {
		
    });
}

function ShowAddModal()
{	
	curId = '';
	$('#main-content').load("./jcsqsj/residebuilding/residebuildingDetail.html", function () {
		
    });
}

function deleteData(id)
{
	$.confirm({
		title:"删除确认",
		text:"确认删除数据?",
		confirm: function(button) {
			
			$.post(getContextPath()+"/residebuildingController/delete",
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


