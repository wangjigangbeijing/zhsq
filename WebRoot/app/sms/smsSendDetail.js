
//获取地址栏参数，name:参数名称
 function getUrlParms(name){
   var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
   var r = window.location.search.substr(1).match(reg);
   if(r!=null)
   return unescape(r[2]);
   return null;
   }

$(document).ready(function (){
	
	/*$.get(getContextPath()+"/tableController/loadTable",
		function(result){
			
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#table_id').html('');
				var userArr = [];
				
				for(var i=0;i<obj.tableList.length;i++)
				{
					var user = obj.tableList[i];
					
					userArr[i] = "<option value='" + user.id + "'>" + user.tableZHName + "</option>";						
				}
				$('#table_id').html(userArr.join(''));
			}
		}
	);
	*/
	if(curId != null && curId != '')
		get();
});

function get()
{
	$.get(getContextPath()+"/smsController/get?msgId="+curId,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#smsContent').val(obj.messageContent);
				$('#mobileList').val(obj.mobileList);
				$('#sender').val(obj.sender);
				
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
						{ 'data': 'mobile' ,'sClass':'text-center'},
						{ 'data': 'sendTime' ,'sClass':'text-center'},
						{ 'data': 'status' ,'sClass':'text-center'},
						{ 'data': '' ,'sClass':'text-center'}

					],
					columnDefs: [
						{
						className: 'control',
						orderable: false,
						targets:  4,//从0开始
						mRender : function(data,type,full){
							var btn = "<a href=\"#\" onclick=\"resend('"+full.id+"')\" class=\"btn btn-info btn-xs\"><i class=\"fa fa-pencil\">重新发送</a>&nbsp;";
							
							if(full.status != '失败')//如果状态不是失败,则不允许重新发送
							{
								btn = full.status;
							}
							
							//btn += "<a href=\"#\" onclick=\"deleteData('"+full.id+"')\" class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash-o\">删除</a>";
							
							return btn;
						}
						}
					]
				} );
				
			}
		});
}

function resend(curId)
{
	$.post(getContextPath()+"/smsController/resend",
	{
		id:curId
	},
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			jSuccess("数据修改成功!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
			
			get();
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

function gobackPage()
{
	$('#main-content').load("./sms/sms.html", function () {
		
	});
}
