

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
 var haslicence = $('#haslicenceQuery').val();
 var socialcode = $('#socialcodeQuery').val();
 var orgtype = $('#orgtypeQuery').val();
 var economictype = $('#economictypeQuery').val();
 var industry = $('#industryQuery').val();
 var subordination = $('#subordinationQuery').val();
 var scale = $('#scaleQuery').val();
 var officeaddress = $('#officeaddressQuery').val();
 var ofbizbuilding = $('#ofbizbuildingQuery').val();
 var contacttel = $('#contacttelQuery').val();
 var hasfirefacilities = $('#hasfirefacilitiesQuery').val();
 var status = $('#statusQuery').val();

	
	$.get(getContextPath()+'/jc_organizationController/load?name='+name+'&haslicence='+haslicence+'&socialcode='+socialcode+'&orgtype='+orgtype+'&economictype='+economictype+'&industry='+industry+'&subordination='+subordination+'&scale='+scale+'&officeaddress='+officeaddress+'&ofbizbuilding='+ofbizbuilding+'&contacttel='+contacttel+'&hasfirefacilities='+hasfirefacilities+'&status='+status+'&',
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
					{ 'data': 'name' ,'sClass':'text-center'},
					{ 'data': 'haslicence' ,'sClass':'text-center'},
					{ 'data': 'socialcode' ,'sClass':'text-center'},
					{ 'data': 'socialcodedate' ,'sClass':'text-center'},
					{ 'data': 'orgtype' ,'sClass':'text-center'},
					{ 'data': 'economictype' ,'sClass':'text-center'},
					{ 'data': 'industry' ,'sClass':'text-center'},
					{ 'data': 'subordination' ,'sClass':'text-center'},
					{ 'data': 'establishdate' ,'sClass':'text-center'},
					{ 'data': 'capitaltype' ,'sClass':'text-center'},
					{ 'data': 'capital' ,'sClass':'text-center'},
					{ 'data': 'businessscope' ,'sClass':'text-center'},
					{ 'data': 'scale' ,'sClass':'text-center'},
					{ 'data': 'regaddress' ,'sClass':'text-center'},
					{ 'data': 'officeaddress' ,'sClass':'text-center'},
					{ 'data': 'ofbizbuilding' ,'sClass':'text-center'},
					{ 'data': 'legalname' ,'sClass':'text-center'},
					{ 'data': 'contactname' ,'sClass':'text-center'},
					{ 'data': 'contacttel' ,'sClass':'text-center'},
					{ 'data': 'moveindate' ,'sClass':'text-center'},
					{ 'data': 'responsibilityplateno' ,'sClass':'text-center'},
					{ 'data': 'hasfirefacilities' ,'sClass':'text-center'},
					{ 'data': 'wastedisposal' ,'sClass':'text-center'},
					{ 'data': 'status' ,'sClass':'text-center'},
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
					targets:  26,//从0开始
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
	$.get(getContextPath()+"/jc_organizationController/get?id="+curId,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#dataid').val(obj.dataid);
				$('#name').val(obj.name);
				$('#haslicence').val(obj.haslicence);
				$('#socialcode').val(obj.socialcode);
				$('#socialcodedate').val(obj.socialcodedate);
				$('#orgtype').val(obj.orgtype);
				$('#economictype').val(obj.economictype);
				$('#industry').val(obj.industry);
				$('#subordination').val(obj.subordination);
				$('#establishdate').val(obj.establishdate);
				$('#capitaltype').val(obj.capitaltype);
				$('#capital').val(obj.capital);
				$('#businessscope').val(obj.businessscope);
				$('#scale').val(obj.scale);
				$('#regaddress').val(obj.regaddress);
				$('#officeaddress').val(obj.officeaddress);
				$('#ofbizbuilding').val(obj.ofbizbuilding);
				$('#legalname').val(obj.legalname);
				$('#contactname').val(obj.contactname);
				$('#contacttel').val(obj.contacttel);
				$('#moveindate').val(obj.moveindate);
				$('#responsibilityplateno').val(obj.responsibilityplateno);
				$('#hasfirefacilities').val(obj.hasfirefacilities);
				$('#wastedisposal').val(obj.wastedisposal);
				$('#status').val(obj.status);
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
	$('#haslicence').val('');
	$('#socialcode').val('');
	$('#socialcodedate').val('');
	$('#orgtype').val('');
	$('#economictype').val('');
	$('#industry').val('');
	$('#subordination').val('');
	$('#establishdate').val('');
	$('#capitaltype').val('');
	$('#capital').val('');
	$('#businessscope').val('');
	$('#scale').val('');
	$('#regaddress').val('');
	$('#officeaddress').val('');
	$('#ofbizbuilding').val('');
	$('#legalname').val('');
	$('#contactname').val('');
	$('#contacttel').val('');
	$('#moveindate').val('');
	$('#responsibilityplateno').val('');
	$('#hasfirefacilities').val('');
	$('#wastedisposal').val('');
	$('#status').val('');
	$('#pictures').val('');
	$('#note').val('');

}
*/

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

