
var curUserType = -1;

$(document).ready(function (){
	
	$('#btnAdd').click(ShowAddModal);
	
	$('#btnReset').click(Reset);
	
	$('#hireDateQry').datepicker({
            format: 'yyyy-mm',
            autoclose: true
        });
		
	$('#hireDate').datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true
        });	
		
	
});

function Reset()
{
	$('#userNameQry').val('');
	$('#mobileQry').val('');
	$('#hireDateQry').val('');
	
	loadUser();
}

var map;
var dataTable;
var dataLayer;

var curId;

function loadUser()
{
	$('#btnSearch').attr('disabled','disabled');
	var userName = $('#userNameQry').val();
	
	$.get(getContextPath()+"/userController/loadUser?userName="+userName,
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
				"data":obj.userList,
				"columns": [
					{ "data": "userName" ,"sClass":"text-center"},
					{ "data": "loginId" ,"sClass":"text-center"},
					{ "data": "mobile" ,"sClass":"text-center"},
					{ "data": "status" ,"sClass":"text-center"},
					{ "data": "" ,"sClass":"text-center"}
				],
				columnDefs: [ {
					className: 'control',
					orderable: false,
					targets:   0,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" onclick=\"viewDetail('"+full.id+"')\" data-toggle=\"tooltip\" title=\"查看\">"+full.userName+"</a>";
						return btn;
					}
					},
					{
					className: 'control',
					orderable: false,
					targets:  4,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" onclick=\"deleteUser('"+full.id+"')\" data-toggle=\"tooltip\">删除</a>";
						return btn;
					}
					}
				]
			} );
		}
	});
}


function viewDetail(id)
{
	$('#modalTitle').text('修改用户信息');
	curId = id;
	$.get(getContextPath()+"/userController/getUser?userId="+curId,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
				$("#loginId").val(obj.loginId);
				$("#userName").val(obj.userName);
				$("#mobile").val(obj.mobile);
				$("#password").val(obj.password);
				$("#status").val(obj.status);
			}
		});
}

function closeModalDetail()
{
	$('#modalDetail').hide();
	curId = '';
	$("#loginId").val('');
	$("#userName").val('');
	$("#mobile").val('');
	$("#password").val('');
	$("#status").val('');
}

function ShowAddModal()
{
	$('#modalDetail').show();
	
	$('#modalTitle').text('新增用户');
	
	$('#addOrUpdateBtn').text('确定');
	
}

function addOrUpdateUser()
{
	if($("#loginId").val() == null || $("#loginId").val() == '')
	{
		jError("用户名不能为空!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
		return null;
	}
	
	if($("#password").val() == null || $("#password").val() == '')
	{
		jError("密码不能为空!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
		return null;
	}
	
	$.post(getContextPath()+"/userController/addOrUpdateUser",
	{
		userId:curId,
		loginId:$("#loginId").val(),
		userName:$("#userName").val(),
		mobile:$("#mobile").val(),
		password:$("#password").val(),
		status:$("#status").val()
	},
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			jSuccess("数据修改成功!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
			
			closeModalDetail();
			
			loadUser();
		}
		else
		{
			jError("数据修改失败!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
		}
	});
}


function deleteUser(userId)
{
	$.confirm({
		title:"删除确认",
		text:"确认删除用户?",
		confirm: function(button) {
			
			$.post(getContextPath()+"/userController/deleteUser",
			{
				userId:userId
			},
			function(result){
				var obj = jQuery.parseJSON(result);  
				if(obj.success)
				{
					jSuccess("数据删除成功!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'
					});
					
					loadUser();
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



