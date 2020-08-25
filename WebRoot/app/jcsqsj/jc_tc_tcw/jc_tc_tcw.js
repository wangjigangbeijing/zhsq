

$(document).ready(function (){
	if(haveRight('jc_tc_tcw_add') == false)
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
	 var name = $('#cwtypeQuery').val();
	if(searchtype == 2){
		name = $('#cwtypeQuery2').val();
	}


 var UseType = $('#UseTypeQuery').val();
 var arrange = $('#arrangeQuery').val();
 var hascharge = $('#haschargeQuery').val();

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
		"sAjaxSource" : getContextPath()+'/jc_tc_tcwController/load',
		"fnServerParams" : function(aoData) {  
			aoData.push({  
				"name" : "statId",  
				"value" : "abc"
			});  
		},  
		"columns": [
					{ 'data': 'inparknameDisplay' ,'sClass':'text-center'},
					{ 'data': 'cwtype' ,'sClass':'text-center'},
					{ 'data': 'location' ,'sClass':'text-center'},
					{ 'data': 'numbers' ,'sClass':'text-center'},
					{ 'data': 'UseType' ,'sClass':'text-center'},
					//{ 'data': 'arrange' ,'sClass':'text-center'},
					//{ 'data': 'chargenum' ,'sClass':'text-center'},
					{ 'data': 'cwcode' ,'sClass':'text-center'},
					{ 'data': '' ,'sClass':'text-center'}
				],
		columnDefs: [
				{
			className: 'control',
			orderable: false,
			targets:  6,//从0开始
			mRender : function(data,type,full){
				var btn = "<a href=\"#\" onclick=\"viewData('"+full.id+"')\" class=\"btn btn-info btn-xs\"><i class=\"fa fa-pencil\"></i>查看</a>&nbsp;";
				
				if(haveRight('jc_tc_tcw_edit') == true)
					btn += "<a href=\"#\" onclick=\"editData('"+full.id+"')\" class=\"btn btn-primary btn-xs\"><i class=\"fa fa-pencil\"></i>编辑</a>&nbsp;";

				if(haveRight('jc_tc_tcw_del') == true)
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
					UseType:UseType,
					arrange:arrange,
					hascharge:hascharge
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

/*
function viewDetail(id)
{
	//$('#modalTitle').text('修改用户信息');
	curId = id;
	$.get(getContextPath()+"/jc_tc_tcwController/get?id="+curId,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#inparkname').val(obj.inparkname);
				$('#cwtype').val(obj.cwtype);
				$('#location').val(obj.location);
				$('#numbers').val(obj.numbers);
				$('#UseType').val(obj.UseType);
				$('#sizeType').val(obj.sizeType);
				$('#heightType').val(obj.heightType);
				$('#arrange').val(obj.arrange);
				$('#hascharge').val(obj.hascharge);
				$('#chargenum').val(obj.chargenum);
				$('#cwcode').val(obj.cwcode);
				$('#pciture').val(obj.pciture);
				$('#note').val(obj.note);

			}
		});
}

function closeModalDetail()
{
	$('#modalDetail').hide();
	curId = '';
	
		$('#inparkname').val('');
	$('#cwtype').val('');
	$('#location').val('');
	$('#numbers').val('');
	$('#UseType').val('');
	$('#sizeType').val('');
	$('#heightType').val('');
	$('#arrange').val('');
	$('#hascharge').val('');
	$('#chargenum').val('');
	$('#cwcode').val('');
	$('#pciture').val('');
	$('#note').val('');

}
*/

function viewData(id)
{
	curId = id;
	$('#main-content').load("./jcsqsj/jc_tc_tcw/jc_tc_tcwDetail.html", function () {
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
	$('#main-content').load("./jcsqsj/jc_tc_tcw/jc_tc_tcwDetail.html", function () {
		
    });
}


function ShowAddModal()
{
	//$('#modalDetail').show();
	
	curId = '';
	$('#main-content').load("./jcsqsj/jc_tc_tcw/jc_tc_tcwDetail.html", function () {
		
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
			
			$.post(getContextPath()+"/jc_tc_tcwController/delete",
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


