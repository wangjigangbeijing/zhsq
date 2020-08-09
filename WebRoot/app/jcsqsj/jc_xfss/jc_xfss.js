

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
	$('#btnSearch').attr('disabled','disabled');

	var searchtype = $("#searchtype").val();
	if(searchtype == 1){
		$('#btnSearch1').attr('disabled','disabled');
	}
	else {
		$('#btnSearch2').attr('disabled','disabled');
	}
	 var name = $('#typeQuery').val();
	if(searchtype == 2){
		name = $('#typeQuery2').val();
	}

 var objname = $('#objnameQuery').val();
 var deptname1 = $('#deptname1Query').val();
 var isincommunity = $('#isincommunityQuery').val();
 var material = $('#materialQuery').val();
 var form = $('#formQuery').val();
 var objState = $('#objStateQuery').val();

	
	$.get(getContextPath()+'/jc_xfssController/load?type='+type+'&objname='+objname+'&deptname1='+deptname1+'&isincommunity='+isincommunity+'&material='+material+'&form='+form+'&objState='+objState+'&',
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
					targets:  8,//从0开始
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
	$.get(getContextPath()+"/jc_xfssController/get?id="+curId,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#dateid').val(obj.dateid);
				$('#type').val(obj.type);
				$('#objid').val(obj.objid);
				$('#objname').val(obj.objname);
				$('#locatedsc').val(obj.locatedsc);
				$('#deptname1').val(obj.deptname1);
				$('#deptname2').val(obj.deptname2);
				$('#deptname3').val(obj.deptname3);
				$('#isincommunity').val(obj.isincommunity);
				$('#material').val(obj.material);
				$('#form').val(obj.form);
				$('#objState').val(obj.objState);
				$('#pictures').val(obj.pictures);
				$('#note').val(obj.note);

			}
		});
}

function closeModalDetail()
{
	$('#modalDetail').hide();
	curId = '';
	
		$('#dateid').val('');
	$('#type').val('');
	$('#objid').val('');
	$('#objname').val('');
	$('#locatedsc').val('');
	$('#deptname1').val('');
	$('#deptname2').val('');
	$('#deptname3').val('');
	$('#isincommunity').val('');
	$('#material').val('');
	$('#form').val('');
	$('#objState').val('');
	$('#pictures').val('');
	$('#note').val('');

}
*/

function editData(id)
{
	curId = id;
	$('#main-content').load("./jcsqsj/jc_xfss/jc_xfssDetail.html", function () {
		
    });
}


function ShowAddModal()
{
	//$('#modalDetail').show();
	
	curId = '';
	$('#main-content').load("./jcsqsj/jc_xfss/jc_xfssDetail.html", function () {
		
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
			
			$.post(getContextPath()+"/jc_xfssController/delete",
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


