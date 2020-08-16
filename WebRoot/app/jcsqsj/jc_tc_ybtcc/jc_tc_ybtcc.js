
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
	 var pkName = $('#parkNameQuery').val();
	if(searchtype == 2){
		pkName = $('#parkNameQuery2').val();
	}


	 var tradeName = $('#tradeNameQuery').val();
 var jztype = $('#jztypeQuery').val();
 var unitAddres = $('#unitAddresQuery').val();
 var adminDep = $('#adminDepQuery').val();

	
	var Chargetype = $('#ChargetypeQuery').val();
	if(searchtype == 2){
		Chargetype = $('#ChargetypeQuery').val();
	}


	$.get(getContextPath()+'/jc_tc_ybtccController/load?parkName='+pkName+'&tradeName='+tradeName+'&jztype='+jztype+'&unitAddres='+unitAddres+'&adminDep='+adminDep+'&Chargetype='+Chargetype,
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

					{ 'data': 'parkName' ,'sClass':'text-center'},
					{ 'data': 'tradeName' ,'sClass':'text-center'},
					{ 'data': 'jztype' ,'sClass':'text-center'},
					{ 'data': 'unitName' ,'sClass':'text-center'},
					{ 'data': 'area' ,'sClass':'text-center'},
					//{ 'data': 'adminDep' ,'sClass':'text-center'},
					{ 'data': 'berthNum' ,'sClass':'text-center'},
					//{ 'data': 'chpileNum' ,'sClass':'text-center'},
					{ 'data': 'Chargetype' ,'sClass':'text-center'},
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
						var btn = "<a href=\"#\" onclick=\"viewData('"+full.id+"')\" class=\"btn btn-info btn-xs\"><i class=\"fa fa-pencil\"></i>查看</a>&nbsp;";
						
						btn += "<a href=\"#\" onclick=\"editData('"+full.id+"')\" class=\"btn btn-primary btn-xs\"><i class=\"fa fa-pencil\"></i>编辑</a>&nbsp;";

						btn += "<a href=\"#\" onclick=\"deleteData('"+full.id+"')\"  class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash-o\"></i>删除</a>";
						
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
	$.get(getContextPath()+"/jc_tc_ybtccController/get?id="+curId,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#parkID').val(obj.parkID);
				$('#parkName').val(obj.parkName);
				$('#tradeName').val(obj.tradeName);
				$('#jztype').val(obj.jztype);
				$('#unitName').val(obj.unitName);
				$('#unitAddres').val(obj.unitAddres);
				$('#area').val(obj.area);
				$('#adminDep').val(obj.adminDep);
				$('#ownerDep').val(obj.ownerDep);
				$('#maintDep').val(obj.maintDep);
				$('#berthNum').val(obj.berthNum);
				$('#UnberthNum').val(obj.UnberthNum);
				$('#GrberthNum').val(obj.GrberthNum);
				$('#ParkingNum').val(obj.ParkingNum);
				$('#openNum').val(obj.openNum);
				$('#parkTime').val(obj.parkTime);
				$('#chpileNum').val(obj.chpileNum);
				$('#BaFreeNum').val(obj.BaFreeNum);
				$('#MecNum').val(obj.MecNum);
if(obj.Chargetype != null){
	var ChargetypeArr = obj.Chargetype.split(VALUE_SPLITTER);
	for(var j=0;j<ChargetypeArr.length;j++)
	{
		if(ChargetypeArr[j] != '')
		{
			$("input[name='Chargetype'][value='"+ChargetypeArr[j]+"']").attr('checked','true');
		}
	}	
}	
				$('#LoLeChtype').val(obj.LoLeChtype);
				$('#ShLeChtype').val(obj.ShLeChtype);
if(obj.DyData != null){
	var DyDataArr = obj.DyData.split(VALUE_SPLITTER);
	for(var j=0;j<DyDataArr.length;j++)
	{
		if(DyDataArr[j] != '')
		{
			$("input[name='DyData'][value='"+DyDataArr[j]+"']").attr('checked','true');
		}
	}	
}	
				$('#picture').val(obj.picture);
				$('#note').val(obj.note);

			}
		});
}

function closeModalDetail()
{
	$('#modalDetail').hide();
	curId = '';
	
		$('#parkID').val('');
	$('#parkName').val('');
	$('#tradeName').val('');
	$('#jztype').val('');
	$('#unitName').val('');
	$('#unitAddres').val('');
	$('#area').val('');
	$('#adminDep').val('');
	$('#ownerDep').val('');
	$('#maintDep').val('');
	$('#berthNum').val('');
	$('#UnberthNum').val('');
	$('#GrberthNum').val('');
	$('#ParkingNum').val('');
	$('#openNum').val('');
	$('#parkTime').val('');
	$('#chpileNum').val('');
	$('#BaFreeNum').val('');
	$('#MecNum').val('');
	$('#Chargetype').val('');
	$('#LoLeChtype').val('');
	$('#ShLeChtype').val('');
	$('#DyData').val('');
	$('#picture').val('');
	$('#note').val('');

}
*/

function viewData(id)
{
	curId = id;
	$('#main-content').load("./jcsqsj/jc_tc_ybtcc/jc_tc_ybtccDetail.html", function () {
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
	$('#main-content').load("./jcsqsj/jc_tc_ybtcc/jc_tc_ybtccDetail.html", function () {
		
    });
}


function ShowAddModal()
{
	//$('#modalDetail').show();
	
	curId = '';
	$('#main-content').load("./jcsqsj/jc_tc_ybtcc/jc_tc_ybtccDetail.html", function () {
		
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
			
			$.post(getContextPath()+"/jc_tc_ybtccController/delete",
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


