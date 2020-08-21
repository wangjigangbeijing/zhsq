

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
	/*$('#btnSearch').attr('disabled','disabled');
	 var name = $('#nameQuery').val();

	$.get(getContextPath()+'/sysOrganizationController/load?name='+name+'&',
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
					{ 'data': 'name' ,'sClass':'text-center'},
					{ 'data': 'code' ,'sClass':'text-center'},
					{ 'data': 'type' ,'sClass':'text-center'},
					//{ 'data': 'border' ,'sClass':'text-center'},
					{ 'data': 'area' ,'sClass':'text-center'},
					{ 'data': 'address' ,'sClass':'text-center'},
					{ 'data': 'telphone' ,'sClass':'text-center'},
					{ 'data': 'secretary' ,'sClass':'text-center'},
					{ 'data': 'secretaryphone' ,'sClass':'text-center'},
					{ 'data': 'directorname' ,'sClass':'text-center'},
					{ 'data': 'directorphone' ,'sClass':'text-center'},
					//{ 'data': 'note' ,'sClass':'text-center'},
					{ 'data': '' ,'sClass':'text-center'}

				],
				columnDefs: [
					{
					className: 'control',
					orderable: false,
					targets:  10,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" onclick=\"editData('"+full.id+"')\" class=\"btn btn-info btn-xs\"><i class=\"fa fa-pencil\">编辑</a>&nbsp;";
						
						btn += "<a href=\"#\" onclick=\"deleteData('"+full.id+"')\" class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash-o\">删除</a>";
						
						return btn;
					}
					}
				]
			} );
		}
	});*/
	
	
	
	var tableHtml = '<thead><th>组织名称</th><th>上级组织</th><th>地址</th><th>联系电话</th><th>操作</th></thead>';
	
				/*<tbody>
					<tr class="treegrid-1">
						<td>Root node 1</td><td>Additional info</td>
					</tr>
					<tr class="treegrid-2 treegrid-parent-1">
						<td>Node 1-1</td><td>Additional info</td>
					</tr>
					<tr class="treegrid-3 treegrid-parent-1">
						<td>Node 1-2</td><td>Additional info</td>
					</tr>
					<tr class="treegrid-4 treegrid-parent-3">
						<td>Node 1-2-1</td><td>Additional info</td>
					</tr>
					<tr class="treegrid-5">
						<td>Root node 2</td><td>Additional info</td>
					</tr>
					<tr class="treegrid-6 treegrid-parent-5">
						<td>Node 2-1</td><td>Additional info</td>
					</tr>
					<tr class="treegrid-7 treegrid-parent-5">
						<td>Node 2-2</td><td>Additional info</td>
					</tr>
					<tr class="treegrid-8 treegrid-parent-7">
						<td>Node 2-2-1</td><td>Additional info</td>
					</tr>				  
				  </tbody-->*/
	
			
	var name = $('#nameQuery').val();
	
	$.get(getContextPath()+"/sysOrganizationController/load?name="+name,
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			var tableData = '';
			
			for(var i=0;i<obj.list.length;i++)
			{
				var org = obj.list[i];
				
				var trData = '<tr class="treegrid-'+org.id;
				
				if(org.parentOrgId != '')
					trData += ' treegrid-parent-'+org.parentid;
				
				trData += '">';
				trData += '<td>'+org.name+'</td><td>'+org.parentName+'</td><td>'+org.address+'</td><td>'+org.telphone+'</td>';
				
				var btn = "<a href=\"#\" onclick=\"editData('"+org.id+"')\" data-toggle=\"tooltip\"><i class=\"fa fa-edit text-blue\"></i></a>&nbsp;&nbsp;";						
				//用户不能删除自己归属的组织
				/*if(curUserOrgId != org.id)
				{					
					btn += "<a href=\"#\" onclick=\"deleteOrganizaiton('"+org.id+"')\" data-toggle=\"tooltip\"><i class=\"fa fa-trash text-red\"></i></a>&nbsp;&nbsp;";
				}*/
				
				trData += '<td>'+btn+'</td>';
				
				trData += '</tr>';
				
				tableData += trData;
			}
			
			tableHtml += '<tbody>'+tableData+'</tbody>';
			
			$('#dataTable').html(tableHtml);			
			$('#dataTable').treegrid({initialState:'collapsed'});
			
			$('#dataTable').treegrid('expandAll');
			
			//$('#progressbar').hide();
		}
	});
}

/*
function viewDetail(id)
{
	//$('#modalTitle').text('修改用户信息');
	curId = id;
	$.get(getContextPath()+"/organizationController/get?id="+curId,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#name').val(obj.name);
				$('#code').val(obj.code);
if(obj.type != null){
	var typeArr = obj.type.split(VALUE_SPLITTER);
	for(var j=0;j<typeArr.length;j++)
	{
		if(typeArr[j] != '')
		{
			$("input[name='type'][value='"+typeArr[j]+"']").attr('checked','true');
		}
	}	
}	
				$('#border').val(obj.border);
				$('#area').val(obj.area);
				$('#address').val(obj.address);
				$('#telphone').val(obj.telphone);
				$('#secretary').val(obj.secretary);
				$('#secretaryphone').val(obj.secretaryphone);
				$('#directorname').val(obj.directorname);
				$('#directorphone').val(obj.directorphone);
				$('#note').val(obj.note);

			}
		});
}

function closeModalDetail()
{
	$('#modalDetail').hide();
	curId = '';
	
		$('#name').val('');
	$('#code').val('');
	$('#type').val('');
	$('#border').val('');
	$('#area').val('');
	$('#address').val('');
	$('#telphone').val('');
	$('#secretary').val('');
	$('#secretaryphone').val('');
	$('#directorname').val('');
	$('#directorphone').val('');
	$('#note').val('');

}
*/

function editData(id)
{
	curId = id;
	$('#main-content').load("./sys/sys_organization/sys_organizationDetail.html", function () {
		
    });
}


function ShowAddModal()
{
	//$('#modalDetail').show();
	
	curId = '';
	$('#main-content').load("./sys/sys_organization/sys_organizationDetail.html", function () {
		
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
			
			$.post(getContextPath()+"/sysOrganizationController/delete",
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


