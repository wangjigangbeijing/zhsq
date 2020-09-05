

$(document).ready(function (){
	
	if(haveRight('jc_organization_add') == false)
	{
		$('#btnAdd1').hide();
		$('#btnAdd2').hide();
	}
	
	if(haveRight('jc_organization_exp') == false)
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
	
 var haslicence = $('#haslicenceQuery').val();
 var socialcode = $('#socialcodeQuery').val();
 var orgtype = $('#orgtypeQuery').val();
 var economictype = $('#economictypeQuery').val();
 var industry = $('#industryQuery').val();
 var scale = $('#scaleQuery').val();
 var officeaddress = $('#officeaddressQuery').val();
 var ofbizbuilding = $('#ofbizbuildingQuery').val();
 var contacttel = $('#contacttelQuery').val();
  var status = $('#statusQuery').val();

	
	$.get(getContextPath()+'/jc_organizationController/load?name='+name+'&haslicence='+haslicence+'&socialcode='+socialcode+'&orgtype='+orgtype+'&economictype='+economictype+'&industry='+industry+'&scale='+scale+'&officeaddress='+officeaddress+'&ofbizbuilding='+ofbizbuilding+'&contacttel='+contacttel+'&status='+status+'&',
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

					//{ 'data': 'dataid' ,'sClass':'text-center'},
					{ 'data': 'name' ,'sClass':'text-center'},
					{ 'data': 'haslicence' ,'sClass':'text-center'},
					{ 'data': 'socialcode' ,'sClass':'text-center'},
					{ 'data': 'orgtype' ,'sClass':'text-center'},
					{ 'data': 'economictype' ,'sClass':'text-center'},
					{ 'data': 'establishdate' ,'sClass':'text-center'},
					{ 'data': 'scale' ,'sClass':'text-center'},
					{ 'data': 'officeaddress' ,'sClass':'text-center'},
					{ 'data': 'ofbizbuilding' ,'sClass':'text-center'},
					{ 'data': 'contactname' ,'sClass':'text-center',
								mRender : function(data,type,full){
									return showData(aesDecrypt(full.contactname), 1);
								}
							},	
					{ 'data': 'contacttel' ,'sClass':'text-center',
							mRender : function(data,type,full){
								return showData(aesDecrypt(full.contacttel), 2);
							}
						},	
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
						//var btn = "<a href=\"#\" onclick=\"editData('"+full.id+"')\" data-toggle=\"tooltip\" title=\"查看\">编辑</a>";
						
						//btn += "<a href=\"#\" onclick=\"deleteData('"+full.id+"')\" data-toggle=\"tooltip\">删除</a>";
						
						var btn = "<a href=\"#\" onclick=\"viewData('"+full.id+"')\" class=\"btn btn-info btn-xs\"><i class=\"fa fa-pencil\"></i>查看</a>&nbsp;";
						
						if(haveRight('jc_organization_edit') == true)
							btn += "<a href=\"#\" onclick=\"editData('"+full.id+"')\" class=\"btn btn-primary btn-xs\"><i class=\"fa fa-pencil\"></i>编辑</a>&nbsp;";

						if(haveRight('jc_organization_del') == true)
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
	$('#main-content').load("./jcsqsj/jc_organization/jc_organizationDetail.html", function () {
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
	$('#main-content').load("./jcsqsj/jc_organization/jc_organizationDetail.html", function () {
		
    });
}


function ShowAddModal()
{
	//$('#modalDetail').show();
	
	curId = '';
	$('#main-content').load("./jcsqsj/jc_organization/jc_organizationDetail.html", function () {
		
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
			
			$.post(getContextPath()+"/jc_organizationController/delete",
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
 
	
	var haslicence = $('#haslicenceQuery').val();
	if(haslicence != '')
		queryStr += "haslicence = '"+haslicence+"' AND ";
	
	var socialcode = $('#socialcodeQuery').val();
	if(socialcode != '')
		queryStr += "socialcode = '"+socialcode+"' AND ";
	
	var orgtype = $('#orgtypeQuery').val();
	if(orgtype != '')
		queryStr += "orgtype = '"+orgtype+"' AND ";
	
	var economictype = $('#economictypeQuery').val();
	if(economictype != '')
		queryStr += "economictype = '"+economictype+"' AND ";
	
	var industry = $('#industryQuery').val();
	if(industry != '')
		queryStr += "industry = '"+industry+"' AND ";
	
	var scale = $('#scaleQuery').val();
	if(scale != '')
		queryStr += "scale = '"+scale+"' AND ";
	
	var officeaddress = $('#officeaddressQuery').val();
	if(officeaddress != '')
		queryStr += "officeaddress = '"+officeaddress+"' AND ";
	
	var ofbizbuilding = $('#ofbizbuildingQuery').val();
	if(ofbizbuilding != '')
		queryStr += "ofbizbuilding = '"+ofbizbuilding+"' AND ";
	
	var contacttel = $('#contacttelQuery').val();
	if(contacttel != '')
		queryStr += "contacttel like '%"+contacttel+"%' AND ";
	
	var status = $('#statusQuery').val();
	if(status != '')
		queryStr += "status = '"+status+"' AND ";
  
	$.post(getContextPath()+"/dataController/exportDataOfTable",
		{
			tableId:'jc_organization',
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
