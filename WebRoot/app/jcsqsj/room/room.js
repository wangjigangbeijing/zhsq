

$(document).ready(function (){
	
	$('#btnAdd').click(ShowAddModal);
	
	$('.dpYears').datepicker({
		autoclose: true
	});
	
	//$('#btnReset').click(Reset);
	
	$('#btnSearch').click(load);
	
	load();
	
	$.ajax({
	  type: 'POST',
	  url: getContextPath()+"/dictionaryController/getDataOfDic",
	  data: JSON.stringify({id:'ofcommunity'}),
	  contentType: "application/json",
	  success:function(result){
				
			if(result.success)
			{
				$('#ofcommunity').html('');
				var filterArr = [];
							
				for(var i=0;i<result.value.length;i++)
				{
					var filter = result.value[i];
					
					filterArr[i] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
				}
				$('#ofcommunity').html(filterArr.join(''));
				
				$('#ofresidebuilding').html('');
				
				$('#ofunit').html('');
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
	
	$('#ofcommunity').change(function(){
		
		var ofcommunity = $('#ofcommunity').val();
		
		$.ajax({
		  type: 'POST',
		  url: getContextPath()+"/dictionaryController/getDataOfDic",
		  data: JSON.stringify({id:'ofresidebuilding',params:[{enname:'ofcommunity',value:ofcommunity}]}),
		  contentType: "application/json",
		  success:function(result){
					
				if(result.success)
				{
					$('#ofresidebuilding').html('');
					var filterArr = [];
								
					for(var i=0;i<result.value.length;i++)
					{
						var filter = result.value[i];
						
						filterArr[i] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
					}
					$('#ofresidebuilding').html(filterArr.join(''));
					
					$('#ofunit').html('');
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
	
	$('#ofresidebuilding').change(function(){
		
		var ofresidebuilding = $('#ofresidebuilding').val();
		var ofcommunity = $('#ofcommunity').val();
		
		$.ajax({
		  type: 'POST',
		  url: getContextPath()+"/dictionaryController/getDataOfDic",
		  data: JSON.stringify({id:'ofresidebuilding',params:[{enname:'ofcommunity',value:ofcommunity},{enname:'ofresidebuilding',value:ofresidebuilding}]}),
		  contentType: "application/json",
		  success:function(result){
					
				if(result.success)
				{
					$('#ofunit').html('');
					var filterArr = [];
								
					for(var i=0;i<result.value.length;i++)
					{
						var filter = result.value[i];
						
						filterArr[i] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
					}
					$('#ofunit').html(filterArr.join(''));
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

function load()
{
	$('#btnSearch').attr('disabled','disabled');
	 var number = $('#numberQuery').val();
 var ofcommunity = $('#ofcommunityQuery').val();
 var ofresidebuilding = $('#ofresidebuildingQuery').val();
 var ofunit = $('#ofunitQuery').val();
 var status = $('#statusQuery').val();
 var isgrouporiented = $('#isgrouporientedQuery').val();
 var ownertype = $('#ownertypeQuery').val();
 var propertypapertype = $('#propertypapertypeQuery').val();

	
	$.get(getContextPath()+'/roomController/load?number='+number+'&ofcommunity='+ofcommunity+'&ofresidebuilding='+ofresidebuilding+'&ofunit='+ofunit+'&status='+status+'&isgrouporiented='+isgrouporiented+'&ownertype='+ownertype+'&propertypapertype='+propertypapertype+'&',
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
										{ 'data': 'dataid' ,'sClass':'text-center'},
					{ 'data': 'number' ,'sClass':'text-center'},
					{ 'data': 'ofcommunity' ,'sClass':'text-center'},
					{ 'data': 'ofresidebuilding' ,'sClass':'text-center'},
					{ 'data': 'ofunit' ,'sClass':'text-center'},
					{ 'data': 'level' ,'sClass':'text-center'},
					{ 'data': 'status' ,'sClass':'text-center'},
					{ 'data': 'isgrouporiented' ,'sClass':'text-center'},
					{ 'data': 'ownertype' ,'sClass':'text-center'},
					{ 'data': 'propertypapertype' ,'sClass':'text-center'},
					{ 'data': 'propertypaperid' ,'sClass':'text-center'},
					{ 'data': 'address' ,'sClass':'text-center'},
					{ 'data': 'note' ,'sClass':'text-center'},
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
					targets:  13,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" onclick=\"editData('"+full.id+"')\" data-toggle=\"tooltip\" title=\"查看\">编辑</a>";
						
						btn += "<a href=\"#\" onclick=\"deleteData('"+full.id+"')\" data-toggle=\"tooltip\">删除</a>";
						
						return btn;
					}
					}
				]
			} );
		}
	});
}

/*
function viewDetail(id)
{
	//$('#modalTitle').text('修改用户信息');
	curId = id;
	$.get(getContextPath()+"/roomController/get?id="+curId,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#dataid').val(obj.dataid);
				$('#number').val(obj.number);
				$('#ofcommunity').val(obj.ofcommunity);
				$('#ofresidebuilding').val(obj.ofresidebuilding);
				$('#ofunit').val(obj.ofunit);
				$('#level').val(obj.level);
				$('#status').val(obj.status);
				$('#isgrouporiented').val(obj.isgrouporiented);
				$('#ownertype').val(obj.ownertype);
				$('#propertypapertype').val(obj.propertypapertype);
				$('#propertypaperid').val(obj.propertypaperid);
				$('#address').val(obj.address);
				$('#propertymap').val(obj.propertymap);
				$('#note').val(obj.note);

			}
		});
}

function closeModalDetail()
{
	$('#modalDetail').hide();
	curId = '';
	
		$('#dataid').val('');
	$('#number').val('');
	$('#ofcommunity').val('');
	$('#ofresidebuilding').val('');
	$('#ofunit').val('');
	$('#level').val('');
	$('#status').val('');
	$('#isgrouporiented').val('');
	$('#ownertype').val('');
	$('#propertypapertype').val('');
	$('#propertypaperid').val('');
	$('#address').val('');
	$('#propertymap').val('');
	$('#note').val('');

}
*/

function editData(id)
{
	curId = id;
	$('#main-content').load("./jcsqsj/room/roomDetail.html", function () {
		
    });
}


function ShowAddModal()
{
	//$('#modalDetail').show();
	
	curId = '';
	$('#main-content').load("./jcsqsj/room/roomDetail.html", function () {
		
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


