

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
 var mobile = $('#mobileQuery').val();
 var address = $('#addressQuery').val();
 var quezhen = $('#quezhenQuery').val();
 var qzdate = $('#qzdateQuery').val();
 var yisi = $('#yisiQuery').val();
 var mijie = $('#mijieQuery').val();
 var hsjc = $('#hsjcQuery').val();
 var hsjcjieguo = $('#hsjcjieguoQuery').val();

	
	$.get(getContextPath()+'/jsjbfwController/load?name='+name+'&mobile='+mobile+'&address='+address+'&quezhen='+quezhen+'&qzdate='+qzdate+'&yisi='+yisi+'&mijie='+mijie+'&hsjc='+hsjc+'&hsjcjieguo='+hsjcjieguo+'&',
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
					{ 'data': 'mobile' ,'sClass':'text-center'},
					{ 'data': 'address' ,'sClass':'text-center'},
					{ 'data': 'quezhen' ,'sClass':'text-center'},
					{ 'data': 'qzdate' ,'sClass':'text-center'},
					{ 'data': 'qznote' ,'sClass':'text-center'},
					{ 'data': 'yisi' ,'sClass':'text-center'},
					{ 'data': 'mijie' ,'sClass':'text-center'},
					{ 'data': 'mjnote' ,'sClass':'text-center'},
					{ 'data': 'glstartdate' ,'sClass':'text-center'},
					{ 'data': 'glenddate' ,'sClass':'text-center'},
					{ 'data': 'note' ,'sClass':'text-center'},
					{ 'data': 'hsjc' ,'sClass':'text-center'},
					{ 'data': 'hsjcdate' ,'sClass':'text-center'},
					{ 'data': 'hsjcjigou' ,'sClass':'text-center'},
					{ 'data': 'hsjcjieguo' ,'sClass':'text-center'},
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
					targets:  16,//从0开始
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
	$.get(getContextPath()+"/jsjbfwController/get?id="+curId,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#name').val(obj.name);
				$('#mobile').val(obj.mobile);
				$('#address').val(obj.address);
				$('#quezhen').val(obj.quezhen);
				$('#qzdate').val(obj.qzdate);
				$('#qznote').val(obj.qznote);
				$('#yisi').val(obj.yisi);
				$('#mijie').val(obj.mijie);
				$('#mjnote').val(obj.mjnote);
				$('#glstartdate').val(obj.glstartdate);
				$('#glenddate').val(obj.glenddate);
				$('#note').val(obj.note);
				$('#hsjc').val(obj.hsjc);
				$('#hsjcdate').val(obj.hsjcdate);
				$('#hsjcjigou').val(obj.hsjcjigou);
				$('#hsjcjieguo').val(obj.hsjcjieguo);

			}
		});
}

function closeModalDetail()
{
	$('#modalDetail').hide();
	curId = '';
	
		$('#name').val('');
	$('#mobile').val('');
	$('#address').val('');
	$('#quezhen').val('');
	$('#qzdate').val('');
	$('#qznote').val('');
	$('#yisi').val('');
	$('#mijie').val('');
	$('#mjnote').val('');
	$('#glstartdate').val('');
	$('#glenddate').val('');
	$('#note').val('');
	$('#hsjc').val('');
	$('#hsjcdate').val('');
	$('#hsjcjigou').val('');
	$('#hsjcjieguo').val('');

}
*/

function editData(id)
{
	curId = id;
	$('#main-content').load("./nfw/zhfw/jsjbfwDetail.html", function () {
		
    });
}


function ShowAddModal()
{
	//$('#modalDetail').show();
	
	curId = '';
	$('#main-content').load("./nfw/zhfw/jsjbfwDetail.html", function () {
		
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
			
			$.post(getContextPath()+"/jsjbfwController/delete",
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



function openResidentDialog()
{
	
	$('#residentDialog').show();
	
	
	
}