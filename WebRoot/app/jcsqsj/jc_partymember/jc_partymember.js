


$(document).ready(function (){
	
	if(haveRight('jc_partymember_add') == false)
	{
		$('#btnAdd1').hide();
		$('#btnAdd2').hide();
	}
	
	if(haveRight('jc_partymember_exp') == false)
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


 var idnumber = $('#idnumberQuery').val();
 var sex = $('#sexQuery').val();
 var age = $('#ageQuery').val();
 var mobile = $('#mobileQuery').val();
  var partymembertype = $('#partymembertypeQuery').val();
 var of_partyorganization = $('#of_partyorganizationQuery').val();
 var isincommunity = $('#isincommunityQuery').val();
 var dyage = $('#dyageQuery').val();
 var membership = $('#membershipQuery').val();
 var movemember = $('#movememberQuery').val();

	
	$.get(getContextPath()+'/jc_partymemberController/load?name='+name+'&idnumber='+idnumber+'&sex='+sex+'&age='+age+'&mobile='+mobile+'&partymembertype='+partymembertype+'&of_partyorganization='+of_partyorganization+'&isincommunity='+isincommunity+'&dyage='+dyage+'&membership='+membership+'&movemember='+movemember+'&',
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
					{ 'data': 'name' ,'sClass':'text-center',
					mRender : function(data,type,full){
						return showData(aesDecrypt(full.name), 1);
					}
					},	
					{ 'data': 'sex' ,'sClass':'text-center'},
					{ 'data': 'age' ,'sClass':'text-center'},
					{ 'data': 'mobile' ,'sClass':'text-center',
								mRender : function(data,type,full){
									return showData(aesDecrypt(full.mobile), 2);
								}
					},
					{ 'data': 'partymembertype' ,'sClass':'text-center'},
					{ 'data': 'of_partyorganizationname' ,'sClass':'text-center'},
					{ 'data': 'isincommunity' ,'sClass':'text-center'},
					{ 'data': 'zhiwu' ,'sClass':'text-center'},
					{ 'data': 'dyage' ,'sClass':'text-center'},
					{ 'data': 'membership' ,'sClass':'text-center'},
					{ 'data': 'movemember' ,'sClass':'text-center'},
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
					targets:  11,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" onclick=\"viewData('"+full.id+"')\" class=\"btn btn-info btn-xs\"><i class=\"fa fa-pencil\"></i>查看</a>&nbsp;";
						
						if(haveRight('jc_partymember_edit') == true)
							btn += "<a href=\"#\" onclick=\"editData('"+full.id+"')\" class=\"btn btn-primary btn-xs\"><i class=\"fa fa-pencil\"></i>编辑</a>&nbsp;";

						if(haveRight('jc_partymember_del') == true)
							btn += "<a href=\"#\" onclick=\"deleteData('"+full.id+"')\"  class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash-o\"></i>删除</a>";
						
						return btn;
					}
					}
				]
			} );
		}
	});
}

function viewData(id)
{
	curId = id;
	$('#main-content').load("./jcsqsj/jc_partymember/jc_partymemberDetail.html", function () {
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
	$('#main-content').load("./jcsqsj/jc_partymember/jc_partymemberDetail.html", function () {
		
    });
}


function ShowAddModal()
{
	//$('#modalDetail').show();
	
	curId = '';
	$('#main-content').load("./jcsqsj/jc_partymember/jc_partymemberDetail.html", function () {
		
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
			
			$.post(getContextPath()+"/jc_partymemberController/delete",
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
 
	 
	 
	 
 var idnumber = $('#idnumberQuery').val();
 if(idnumber != '')
		queryStr += "idnumber like '%"+idnumber+"%' AND ";
 
 var sex = $('#sexQuery').val();
 if(sex != '')
		queryStr += "address like '%"+sex+"%' AND ";
 
 var age = $('#ageQuery').val();
 if(age != '')
		queryStr += "age = '"+age+"' AND ";
 
 var mobile = $('#mobileQuery').val();
 if(mobile != '')
		queryStr += "mobile like '%"+mobile+"%' AND ";
 
  var partymembertype = $('#partymembertypeQuery').val();
  if(partymembertype != '')
		queryStr += "partymembertype like '%"+partymembertype+"%' AND ";
  
 var of_partyorganization = $('#of_partyorganizationQuery').val();
 if(of_partyorganization != '')
		queryStr += "of_partyorganization = '"+of_partyorganization+"' AND ";
 
 var isincommunity = $('#isincommunityQuery').val();
 if(isincommunity != '')
		queryStr += "isincommunity = '"+isincommunity+"' AND ";
 
 var dyage = $('#dyageQuery').val();
 if(dyage != '')
		queryStr += "dyage = '"+dyage+"' AND ";
 
 var membership = $('#membershipQuery').val();
 if(membership != '')
		queryStr += "membership = '"+membership+"' AND ";
 
 var movemember = $('#movememberQuery').val();
	 if(movemember != '')
		queryStr += "movemember = '"+movemember+"' AND ";
	 
 
	$.post(getContextPath()+"/dataController/exportDataOfTable",
		{
			tableId:'jc_partymember',
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
