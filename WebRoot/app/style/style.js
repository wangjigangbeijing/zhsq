
var curUserType = -1;

$(document).ready(function (){
	
	$('#btnAdd').click(ShowAddModal);
	
	
	/*var checkin = $('.dpd1').datepicker({
            onRender: function(date) {
                return date.valueOf() < now.valueOf() ? 'disabled' : '';
            }
        }).on('changeDate', function(ev) {
                if (ev.date.valueOf() > checkout.date.valueOf()) {
                    var newDate = new Date(ev.date)
                    newDate.setDate(newDate.getDate() + 1);
                    checkout.setValue(newDate);
                }
                checkin.hide();
                $('.dpd2')[0].focus();
            }).data('datepicker');
        var checkout = $('.dpd2').datepicker({
            onRender: function(date) {
                return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
            }
        }).on('changeDate', function(ev) {
                checkout.hide();
            }).data('datepicker');*/
});

var map;
var dataTable;
var dataLayer;

var curId;

function loadStyle()
{
	$('#btnSearch').attr('disabled','disabled');
	var layerType = $('#styleTypeQry').val();
	
	$.get(getContextPath()+"/styleController/loadStyle?layerType="+layerType,
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
				"data":obj.styleList,
				"columns": [
					{ "data": "styleType" ,"sClass":"text-center"},
					{ "data": "styleName" ,"sClass":"text-center"},
					{ "data": "" ,"sClass":"text-center"}
				],
				columnDefs: [ /*{
					className: 'control',
					orderable: false,
					targets:   0,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" onclick=\"viewDetail('"+full.id+"')\" data-toggle=\"tooltip\" title=\"查看\">"+full.userName+"</a>";
						return btn;
					}
					},*/
					{
					className: 'control',
					orderable: false,
					targets:  2,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" onclick=\"deleteStyle('"+full.styleName+"')\" data-toggle=\"tooltip\" >删除</a>";
						return btn;
					}
					}
				]
			} );
		}
	});
}


function viewDetail(styleName)
{
	curId = styleName;
	$.get(getContextPath()+"/styleController/getStyle?styleName="+styleName,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
				$('#styleName').val(obj.styleName);
				
				$('#styleType').val(obj.styleType);
				
				$('#sld').val(obj.sld);				
			}
		});
}

function closeModalDetail()
{
	$('#modalDetail').hide();
	
	curId = '';
	$("#styleName").val('');
	$("#styleType").val('');
	$("#sld").val('');
}

function ShowAddModal()
{
	$('#modalDetail').show();
	
	$('#modalTitle').text('新增Style');
	
	$('#addOrUpdateBtn').text('确定');
	
	
}

function addOrUpdateStyle()
{
	var patt = /[a-z,0-9]/;
	
	var styleName = $("#styleName").val();
	
	if(patt.test(styleName) == false)
	{
		jError("Style名称 只能包含字母或者数字!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
		return ;
	}
	
	$.post(getContextPath()+"/styleController/addOrUpdateStyle",
	{
		styleId:curId,
		styleName:$("#styleName").val(),
		styleType:$("#styleType").val(),
		sld:$("#sld").val()
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
			
			loadStyle();
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


function deleteStyle(styleName)
{
	
	$.confirm({
		title:"删除确认",
		text:"确认删除Style?",
		confirm: function(button) {
			
			$.post(getContextPath()+"/styleController/deleteStyle",
			{
				styleName:styleName
			},
			function(result){
				var obj = jQuery.parseJSON(result);  
				if(obj.success)
				{
					jSuccess("数据删除成功!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'
					});
					
					loadStyle();
				}
				else
				{
					jError("数据删除失败!",{
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











