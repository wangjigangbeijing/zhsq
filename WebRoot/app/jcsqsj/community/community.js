

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
	 var name = $('#nameQuery').val();
 var buildtype = $('#buildtypeQuery').val();
 var type = $('#typeQuery').val();
 var year = $('#yearQuery').val();
 var status = $('#statusQuery').val();
 var address = $('#addressQuery').val();
 var note = $('#noteQuery').val();

	
	$.get(getContextPath()+'/communityController/load?name='+name+'&buildtype='+buildtype+'&type='+type+'&year='+year+'&status='+status+'&address='+address+'&note='+note+'&',
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
										//{ 'data': 'dataid' ,'sClass':'text-center'},
					{ 'data': 'name' ,'sClass':'text-center'},
					{ 'data': 'buildtype' ,'sClass':'text-center'},
					{ 'data': 'type' ,'sClass':'text-center'},
					{ 'data': 'year' ,'sClass':'text-center'},
					{ 'data': 'buildings' ,'sClass':'text-center'},
					{ 'data': 'gates' ,'sClass':'text-center'},
					//{ 'data': 'groundparking' ,'sClass':'text-center'},
					//{ 'data': 'underparking' ,'sClass':'text-center'},
					//{ 'data': 'longitude' ,'sClass':'text-center'},
					//{ 'data': 'latitude' ,'sClass':'text-center'},
					{ 'data': 'status' ,'sClass':'text-center'},
					//{ 'data': 'address' ,'sClass':'text-center'},
					//{ 'data': 'note' ,'sClass':'text-center'},
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
					targets:  7,//从0开始
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
	$.get(getContextPath()+"/communityController/get?id="+curId,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#dataid').val(obj.dataid);
				$('#name').val(obj.name);
				$('#buildtype').val(obj.buildtype);
				$('#type').val(obj.type);
				$('#year').val(obj.year);
				$('#buildings').val(obj.buildings);
				$('#gates').val(obj.gates);
				$('#groundparking').val(obj.groundparking);
				$('#underparking').val(obj.underparking);
				$('#longitude').val(obj.longitude);
				$('#latitude').val(obj.latitude);
				$('#status').val(obj.status);
				$('#address').val(obj.address);
				$('#pictures').val(obj.pictures);
				$('#note').val(obj.note);

			}
		});
}

function closeModalDetail()
{
	$('#modalDetail').hide();
	curId = '';
	
		$('#dataid').val('');
	$('#name').val('');
	$('#buildtype').val('');
	$('#type').val('');
	$('#year').val('');
	$('#buildings').val('');
	$('#gates').val('');
	$('#groundparking').val('');
	$('#underparking').val('');
	$('#longitude').val('');
	$('#latitude').val('');
	$('#status').val('');
	$('#address').val('');
	$('#pictures').val('');
	$('#note').val('');

}
*/

function editData(id)
{
	curId = id;
	$('#main-content').load("./jcsqsj/community/communityDetail.html", function () {
		
    });
}


function ShowAddModal()
{
	//$('#modalDetail').show();
	
	curId = '';
	$('#main-content').load("./jcsqsj/community/communityDetail.html", function () {
		
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
			
			$.post(getContextPath()+"/communityController/delete",
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

