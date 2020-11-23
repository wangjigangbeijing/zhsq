
//var curUserType = -1;

$(document).ready(function (){
	
	$('#btnAdd').click(addsxsqsj);
	
	$('#btnSearch').click(loadsxsqsj);
	
	loadsxsqsj();
	
	$('#sxType').text(curSXType);
	
	var sxxlArr = [];
				
		if(curSXType == '社区党建事项')
		{
			sxxlArr[0] = "<option value='党建活动'>党建活动</option>";
			sxxlArr[1] = "<option value='党员志愿活动'>党员志愿活动</option>";
		}
		else if(curSXType == '民主自治事项')
		{
			sxxlArr[0] = "<option value='社区代表会议'>社区代表会议</option>";
			sxxlArr[1] = "<option value='居委会换届'>居委会换届</option>";
		}
		else if(curSXType == '社区服务事项')
		{
			sxxlArr[0] = "<option value='就业指导'>就业指导</option>";
			sxxlArr[1] = "<option value='低保救助'>低保救助</option>";
			sxxlArr[2] = "<option value='优抚安置'>优抚安置</option>";
			sxxlArr[3] = "<option value='志愿者服务'>志愿者服务</option>";
			sxxlArr[4] = "<option value='精神文明建设'>精神文明建设</option>";
			sxxlArr[5] = "<option value='信访信息'>信访信息</option>";
		}
		else if(curSXType == '平安建设事项')
		{
			sxxlArr[0] = "<option value='民事纠纷调解'>民事纠纷调解</option>";
			sxxlArr[1] = "<option value='出租房屋管理'>出租房屋管理</option>";
			sxxlArr[2] = "<option value='养犬养宠管理'>养犬养宠管理</option>";
			sxxlArr[3] = "<option value='特殊人群关注'>特殊人群关注</option>";
			sxxlArr[4] = "<option value='消防安全事件'>消防安全事件</option>";
		}
		else if(curSXType == '文化教育事项')
		{
			sxxlArr[0] = "<option value='文化文艺活动'>文化文艺活动</option>";
			sxxlArr[1] = "<option value='文化团体活动'>文化团体活动</option>";
			sxxlArr[2] = "<option value='全民健身活动'>全民健身活动</option>";
			sxxlArr[3] = "<option value='入园入学登记'>入园入学登记</option>";
		}
		else if(curSXType == '社区环境事项')
		{
			sxxlArr[0] = "<option value='社区环境建设'>社区环境建设</option>";
			sxxlArr[1] = "<option value='垃圾分类管理'>垃圾分类管理</option>";
			sxxlArr[2] = "<option value='除害用药事件'>除害用药事件</option>";
		}		
		else if(curSXType == '卫生健康事项')
		{
			sxxlArr[0] = "<option value='居民健康宣传'>居民健康宣传</option>";
			sxxlArr[1] = "<option value='育龄妇女关怀'>育龄妇女关怀</option>";
			sxxlArr[2] = "<option value='重病人员关怀'>重病人员关怀</option>";
			sxxlArr[3] = "<option value='两癌人员关怀'>两癌人员关怀</option>";
		}	
		$('#sxxl').html(sxxlArr.join(''));
	
});

var dataTable;

function loadsxsqsj()
{
	$('#btnSearch').attr('disabled','disabled');
	//var tableName = $('#tableNameQry').val();
	var sxbm = $('#sxbm').val();
	var sxmc = $('#sxmc').val();
	var sxxl = $('#sxxl').val();
	
	$.get(getContextPath()+"/sxsqsjController/load?sxdl="+curSXType + "&sxbm=" + sxbm + "&sxmc=" + sxmc + "&sxxl=" + sxxl,
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
					{ "data": "sxbm" ,"sClass":"text-center"},
					{ "data": "sxmc" ,"sClass":"text-center"},
					{ "data": "sxdl" ,"sClass":"text-center"},
					{ "data": "sxxl" ,"sClass":"text-center"},
					{ "data": "sxdd" ,"sClass":"text-center"},
					{ "data": "sxdsrname" ,"sClass":"text-center"},
					{ "data": "sxkssj" ,"sClass":"text-center"},
					{ "data": "sxjssj" ,"sClass":"text-center"},
					{ "data": "" ,"sClass":"text-center"}
				],
				columnDefs: [
					{
					className: 'control',
					orderable: false,
					targets:   8,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" onclick=\"view('"+full.id+"')\" class=\"btn btn-info btn-xs\"><i class=\"fa fa-pencil\"></i>查看</a>&nbsp;";
						btn += "<a href=\"#\" onclick=\"editObj('"+full.id+"')\"  class=\"btn btn-primary btn-xs\"><i class=\"fa fa-trash-o\"></i>修改</a>&nbsp;";
						btn += "<a href=\"#\" onclick=\"deleteObj('"+full.id+"')\"  class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash-o\"></i>删除</a>";
						
						return btn;
					}
					}
				]
			} );
			
			/*$('#tableSelect').html('');
			var userArr = [];
			
			for(var i=0;i<obj.tableList.length;i++)
			{
				var table = obj.tableList[i];
				
				userArr[i] = "<option value='" + table.id + "'>" + table.tableName + "</option>";						
			}
			$('#tableSelect').html(userArr.join(''));*/
	
		}
		else
		{
			jError(obj.errMsg,{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'});
		}
	});
}

var curId = '';
function addsxsqsj()
{
	curId = '';
	$('#main-content').load("./sxsqsj/sxsqsjDetail.html", function () {
		//EditableTable.init();
		//loadStyle();//如果放到ready方法里会出现加载了layerStyle被覆盖的情况
    });
}

var curId = '';

function view(id)
{
	curId = id;
	
	$('#main-content').load("./sxsqsj/sxsqsjDetail.html", function () {
		get();
		
		$('#confirmBtn').hide();
		
		$("select").attr("disabled","disabled");
		$("textarea").attr("disabled","disabled");
		$("input").attr("disabled","disabled");
		$("#picturespick").hide();
    });
}

function editObj(id)
{
	curId = id;
	
	$('#main-content').load("./sxsqsj/sxsqsjDetail.html", function () {
		get();
    });
}



function deleteObj(id)
{
	$.confirm({
		title:"删除确认",
		text:"确认删除事项?",
		confirm: function(button) {
			
			$.post(getContextPath()+"/sxsqsjController/delete",
			{
				id:id
			},
			function(result){
				var obj = jQuery.parseJSON(result);  
				if(obj.success)
				{
					jSuccess("事项删除成功!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'
					});
					
					loadsxsqsj();
				}
				else
				{
					jError("事项删除成功!",{
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

