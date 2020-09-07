


$(document).ready(function (){
	if(haveRight('jc_qtbuilding_add') == false)
	{
		$('#btnAdd1').hide();
		$('#btnAdd2').hide();
	}
	
	if(haveRight('jc_qtbuilding_exp') == false)
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
	
	$('#btnExport1').click(exportData);
	$('#btnExport2').click(exportData);
	
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

 var address = $('#addressQuery').val();
 var purpose = $('#purposeQuery').val();
 var propertyyears = $('#propertyyearsQuery').val();
 var propertyrights = $('#propertyrightsQuery').val();
 var heatingsystem = $('#heatingsystemQuery').val();
 var ofcommunity = $('#ofcommunityQuery').val();
 var buildtype = $('#buildtypeQuery').val();
 var buildframework = $('#buildframeworkQuery').val();
 var constructiontype = $('#constructiontypeQuery').val();
 var status = $('#statusQuery').val();

	
	$.get(getContextPath()+'/jc_qtbuildingController/load?name='+name+'&address='+address+'&purpose='+purpose+'&propertyyears='+propertyyears+'&propertyrights='+propertyrights+'&heatingsystem='+heatingsystem+'&ofcommunity='+ofcommunity+'&buildtype='+buildtype+'&buildframework='+buildframework+'&constructiontype='+constructiontype+'&status='+status+'&',
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
					
					
					{ 'data': 'purpose' ,'sClass':'text-center'},
					{ 'data': 'propertyyears' ,'sClass':'text-center'},
					
					{ 'data': 'ofcommunity' ,'sClass':'text-center'},
					{ 'data': 'buildtype' ,'sClass':'text-center'},
					
					{ 'data': 'levels' ,'sClass':'text-center'},
					
					{ 'data': 'propertymanage' ,'sClass':'text-center'},
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
					targets:  8,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" onclick=\"viewData('"+full.id+"')\" class=\"btn btn-info btn-xs\"><i class=\"fa fa-pencil\"></i>查看</a>&nbsp;";
						
						if(haveRight('jc_qtbuilding_edit') == true)
							btn += "<a href=\"#\" onclick=\"editData('"+full.id+"')\" class=\"btn btn-primary btn-xs\"><i class=\"fa fa-pencil\"></i>编辑</a>&nbsp;";

						if(haveRight('jc_qtbuilding_del') == true)
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
	$.get(getContextPath()+"/jcqtbuildingController/get?id="+curId,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#dataid').val(obj.dataid);
				$('#name').val(obj.name);
				$('#address').val(obj.address);
				$('#year').val(obj.year);
				$('#purpose').val(obj.purpose);
				$('#propertyyears').val(obj.propertyyears);
				$('#propertyrights').val(obj.propertyrights);
				$('#heatingsystem').val(obj.heatingsystem);
				$('#ofcommunity').val(obj.ofcommunity);
				$('#buildtype').val(obj.buildtype);
				$('#buildframework').val(obj.buildframework);
				$('#constructiontype').val(obj.constructiontype);
				$('#units').val(obj.units);
				$('#levels').val(obj.levels);
				$('#elevators').val(obj.elevators);
				$('#area').val(obj.area);
				$('#developer').val(obj.developer);
				$('#propertyowner').val(obj.propertyowner);
				$('#propertyownertel').val(obj.propertyownertel);
				$('#user').val(obj.user);
				$('#usertel').val(obj.usertel);
				$('#propertymanage').val(obj.propertymanage);
				$('#propertymanagecontact').val(obj.propertymanagecontact);
				$('#propertymanagecontacttel').val(obj.propertymanagecontacttel);
				$('#status').val(obj.status);
				$('#pictures').val(obj.pictures);
				$('#note').val(obj.note);
				$('#orginbuilding').val(obj.orginbuilding);

			}
		});
}

function closeModalDetail()
{
	$('#modalDetail').hide();
	curId = '';
	
		$('#dataid').val('');
	$('#name').val('');
	$('#address').val('');
	$('#year').val('');
	$('#purpose').val('');
	$('#propertyyears').val('');
	$('#propertyrights').val('');
	$('#heatingsystem').val('');
	$('#ofcommunity').val('');
	$('#buildtype').val('');
	$('#buildframework').val('');
	$('#constructiontype').val('');
	$('#units').val('');
	$('#levels').val('');
	$('#elevators').val('');
	$('#area').val('');
	$('#developer').val('');
	$('#propertyowner').val('');
	$('#propertyownertel').val('');
	$('#user').val('');
	$('#usertel').val('');
	$('#propertymanage').val('');
	$('#propertymanagecontact').val('');
	$('#propertymanagecontacttel').val('');
	$('#status').val('');
	$('#pictures').val('');
	$('#note').val('');
	$('#orginbuilding').val('');

}
*/


function viewData(id)
{
	curId = id;
	$('#main-content').load("./jcsqsj/jc_qtbuilding/jc_qtbuildingDetail.html", function () {
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
	$('#main-content').load("./jcsqsj/jc_qtbuilding/jc_qtbuildingDetail.html", function () {
		
    });
}


function ShowAddModal()
{
	//$('#modalDetail').show();
	
	curId = '';
	$('#main-content').load("./jcsqsj/jc_qtbuilding/jc_qtbuildingDetail.html", function () {
		
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
			
			$.post(getContextPath()+"/jc_qtbuildingController/delete",
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
 
	 var address = $('#addressQuery').val();
	 if(address != '')
		queryStr += "address like '%"+address+"%' AND ";
	 
	 var purpose = $('#purposeQuery').val();
	 if(purpose != '')
		queryStr += "purpose like '%"+purpose+"%' AND ";
	 
	 var propertyyears = $('#propertyyearsQuery').val();
	 if(propertyyears != '')
		queryStr += "propertyyears = '"+propertyyears+"' AND ";
	 
	 var propertyrights = $('#propertyrightsQuery').val();
	 if(propertyrights != '')
		queryStr += "propertyrights = '"+propertyrights+"' AND ";
	 
	 var heatingsystem = $('#heatingsystemQuery').val();
	 if(heatingsystem != '')
		queryStr += "heatingsystem = '"+heatingsystem+"' AND ";
	 
	 var ofcommunity = $('#ofcommunityQuery').val();
	 if(ofcommunity != '' && ofcommunity != null)
		queryStr += "ofcommunity = '"+ofcommunity+"' AND ";
	 
	 var buildtype = $('#buildtypeQuery').val();
	 if(buildtype != '')
		queryStr += "buildtype = '"+buildtype+"' AND ";
	 
	 var buildframework = $('#buildframeworkQuery').val();
	 if(buildframework != '')
		queryStr += "buildframework = '"+buildframework+"' AND ";
	 
	 var constructiontype = $('#constructiontypeQuery').val();
	 if(constructiontype != '')
		queryStr += "constructiontype = '"+constructiontype+"' AND ";
	 
	 var status = $('#statusQuery').val();
	 if(status != '')
		queryStr += "status = '"+status+"' AND ";
	
	$.post(getContextPath()+"/dataController/exportDataOfTable",
		{
			tableId:'jc_qtbuilding',
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
