

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

	var roadname = $('#roadnameQuery').val();

	$.get(getContextPath()+'/jc_tc_dltccController/load?name='+name+'&roadname='+roadname+'&',
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
										{ 'data': 'berthID' ,'sClass':'text-center'},
					{ 'data': 'name' ,'sClass':'text-center'},
					{ 'data': 'roadname' ,'sClass':'text-center'},
					{ 'data': 'area' ,'sClass':'text-center'},
					{ 'data': 'rateinfo' ,'sClass':'text-center'},

					{ 'data': 'parknum' ,'sClass':'text-center'},

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
					targets:  6,//从0开始
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
	$.get(getContextPath()+"/jc_tc_dltccController/get?id="+curId,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#berthID').val(obj.berthID);
				$('#name').val(obj.name);
				$('#roadname').val(obj.roadname);
				$('#area').val(obj.area);
				$('#rateinfo').val(obj.rateinfo);
				$('#parkeTime').val(obj.parkeTime);
				$('#parknum').val(obj.parknum);
				$('#picture').val(obj.picture);
				$('#note').val(obj.note);
				$('#rateinfo').val(obj.rateinfo);

			}
		});
}

function closeModalDetail()
{
	$('#modalDetail').hide();
	curId = '';
	
		$('#berthID').val('');
	$('#name').val('');
	$('#roadname').val('');
	$('#area').val('');
	$('#rateinfo').val('');
	$('#parkeTime').val('');
	$('#parknum').val('');
	$('#picture').val('');
	$('#note').val('');
	$('#rateinfo').val('');

}
*/

function editData(id)
{
	curId = id;
	$('#main-content').load("./jcsqsj/jc_tc_dltcc/jc_tc_dltccDetail.html", function () {
		
    });
}


function ShowAddModal()
{
	//$('#modalDetail').show();
	
	curId = '';
	$('#main-content').load("./jcsqsj/jc_tc_dltcc/jc_tc_dltccDetail.html", function () {
		
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
			
			$.post(getContextPath()+"/jc_tc_dltccController/delete",
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


