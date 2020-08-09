

$(document).ready(function (){
	
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

	$.get(getContextPath()+'/residebuildingController/load?name='+name+'&address='+address+'&propertyyears='+propertyyears+'&propertyrights='+propertyrights+'&heatingsystem='+heatingsystem+'&ofcommunity='+ofcommunity+'&buildtype='+buildtype+'&buildframework='+buildframework+'&constructiontype='+constructiontype+'&status='+status+'&',
	function(result){
	
		if(searchtype == 1){
			
			$('#btnSearch1').removeAttr('disabled');
		}
		else {
			$('#btnSearch2').removeAttr('disabled');
		}


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
				columnDefs: [ /*{
					className: 'control',
					orderable: false,
					targets:   0,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" onclick=\"viewDetail('"+full.id+"')\" data-toggle=\"tooltip\" title=\"查看\">"+full.name+"</a>";
						return btn;
					}
					},*/
					{
					className: 'control',
					orderable: false,
					targets:  12,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" onclick=\"mingqing('"+full.id+"')\" data-toggle=\"tooltip\" class=\"btn btn-primary btn-xs\" title=\"查看\">民情图</a>&nbsp;";
						
						btn += "<a href=\"#\" onclick=\"editData('"+full.id+"')\" data-toggle=\"tooltip\" class=\"btn btn-info btn-xs\" title=\"查看\">编辑</a>&nbsp;";
						
						btn += "<a href=\"#\" onclick=\"deleteData('"+full.id+"')\" class=\"btn btn-danger btn-xs\" data-toggle=\"tooltip\">删除</a>";
						
						return btn;
					}
					}
				]
			} );
		}
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


