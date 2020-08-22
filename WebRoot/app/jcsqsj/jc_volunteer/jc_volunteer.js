

$(document).ready(function (){
	
	$('#btnAdd').click(ShowAddModal);
	
	$('.dpYears').datepicker({
		autoclose: true
	});
	
	//$('#btnReset').click(Reset);
	
	$('#btnSearch').click(load);
	
	
	$.ajax({
	  type: 'POST',
	  url: getContextPath()+"/volunteerteamController/load",
	  data: JSON.stringify({id:'ofcommunity'}),
	  contentType: "application/json",
	  success:function(result){
				
			if(result.success)
			{
				$('#of_volunteerteam').html('');
				var filterArr = [];
				
				filterArr[0] = "<option value=''>全部</option>";				
				
				for(var i=0;i<result.list.length;i++)
				{
					var filter = result.list[i];
					
					filterArr[i+1] = "<option value='" + filter.id + "'>" + filter.value + "</option>";						
				}
				$('#of_volunteerteam').html(filterArr.join(''));
				
			}
			else
			{
				jError("获取志愿组织列表失败!",{
					VerticalPosition : 'center',
					HorizontalPosition : 'center'
				});
			}
		},
	  dataType: "json"
	});
	
	load();
});

var curId;

function load()
{
	$('#btnSearch').attr('disabled','disabled');
	 var name = $('#nameQuery').val();
 var idnumber = $('#idnumberQuery').val();
 var sex = $('#sexQuery').val();
 var birthday = $('#birthdayQuery').val();
 var age = $('#ageQuery').val();
 var mobile = $('#mobileQuery').val();
 var education = $('#educationQuery').val();
 var politicalstatus = $('#politicalstatusQuery').val();
 var of_volunteerteam = $('#of_volunteerteamQuery').val();
 //var join_date = $('#join_dateQuery').val();
 //var status = $('#statusQuery').val();

	
	$.get(getContextPath()+'/jc_volunteerController/load?name='+name+'&idnumber='+idnumber+'&sex='+sex+'&birthday='+birthday+'&age='+age+'&mobile='+mobile+'&education='+education+'&politicalstatus='+politicalstatus+'&of_volunteerteam='+of_volunteerteam,//+'&join_date='+join_date+'&status='+status+'&',
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
					{ 'data': 'idnumber' ,'sClass':'text-center'},
					{ 'data': 'sex' ,'sClass':'text-center'},
					{ 'data': 'birthday' ,'sClass':'text-center'},
					{ 'data': 'age' ,'sClass':'text-center'},
					{ 'data': 'mobile' ,'sClass':'text-center'},
					{ 'data': 'education' ,'sClass':'text-center'},
					{ 'data': 'politicalstatus' ,'sClass':'text-center'},
					{ 'data': 'of_volunteerteam' ,'sClass':'text-center'},
					{ 'data': 'join_date' ,'sClass':'text-center'},
					{ 'data': 'certificate_id' ,'sClass':'text-center'},
					{ 'data': 'special_skill' ,'sClass':'text-center'},
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
					targets:  14,//从0开始
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
	$.get(getContextPath()+"/jc_volunteerController/get?id="+curId,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#name').val(obj.name);
				$('#idnumber').val(obj.idnumber);
				$('#sex').val(obj.sex);
				$('#birthday').val(obj.birthday);
				$('#age').val(obj.age);
				$('#mobile').val(obj.mobile);
				$('#education').val(obj.education);
				$('#politicalstatus').val(obj.politicalstatus);
				$('#of_volunteerteam').val(obj.of_volunteerteam);
				$('#join_date').val(obj.join_date);
				$('#certificate_id').val(obj.certificate_id);
				$('#special_skill').val(obj.special_skill);
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
	
		$('#name').val('');
	$('#idnumber').val('');
	$('#sex').val('');
	$('#birthday').val('');
	$('#age').val('');
	$('#mobile').val('');
	$('#education').val('');
	$('#politicalstatus').val('');
	$('#of_volunteerteam').val('');
	$('#join_date').val('');
	$('#certificate_id').val('');
	$('#special_skill').val('');
	$('#status').val('');
	$('#pictures').val('');
	$('#note').val('');

}
*/

function editData(id)
{
	curId = id;
	$('#main-content').load("./jc_volunteer/jc_volunteerDetail.html", function () {
		
    });
}


function ShowAddModal()
{
	//$('#modalDetail').show();
	
	curId = '';
	$('#main-content').load("./jc_volunteer/jc_volunteerDetail.html", function () {
		
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
			
			$.post(getContextPath()+"/jc_volunteerController/delete",
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


