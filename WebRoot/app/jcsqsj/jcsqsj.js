
var curUserType = -1;

$(document).ready(function (){
	
	$('#btnAdd').click(addsqzzqd);
	
	//小区信息
	$('#xqxxAnchor').click(function () {
        $('#jcsqsjDiv').load("./jcsqsj/community/community.html", function () {
        
		});
    });
	
	//房屋信息
	$('#fwxxAnchor').click(function () {
        $('#jcsqsjDiv').load("./jcsqsj/room/room.html", function () {
        
		});
    });
	
	//家庭信息
	$('#jtxxAnchor').click(function () {
        $('#jcsqsjDiv').load("./jcsqsj/family/family.html", function () {
        
		});
    });
	
	//居民信息
	$('#jmxxAnchor').click(function () {
        $('#jcsqsjDiv').load("./jcsqsj/resident/resident.html", function () {
        
		});
    });
	
	
	
	
	
	
	
	//常驻人口
	$('#czrkAnchor').click(function () {
		alert('未实现');
        /*$('#jcsqsjDiv').load("./jcsqsj/community/community.html", function () {
        
		});*/
    });
	
	//流动人口
	$('#ldrkAnchor').click(function () {
        
		alert('未实现');
		/*$('#jcsqsjDiv').load("./jcsqsj/community/community.html", function () {
        
		});*/
    });
	
	//周边道路
	$('#zbdlAnchor').click(function () {
        $('#jcsqsjDiv').load("./jcsqsj/roads/roads.html", function () {
        
		});
    });
	
	//党组织
	$('#dzzAnchor').click(function () {
        $('#jcsqsjDiv').load("./jcsqsj/partyorganization/partyorganization.html", function () {
        
		});
    });
	
	//市政设施
	$('#szbjAnchor').click(function () {
        $('#jcsqsjDiv').load("./jcsqsj/publicfacilities/publicfacilities.html", function () {
        
		});
    });
	
	//住宅楼宇
	$('#zzlyAnchor').click(function () {
        $('#jcsqsjDiv').load("./jcsqsj/residebuilding/residebuilding.html", function () {
        
		});
    });
	
	//商务楼宇
	$('#swlyAnchor').click(function () {
        $('#jcsqsjDiv').load("./jcsqsj/jc_bizbuilding/jc_bizbuilding.html", function () {
        
		});
    });
	
	//地下空间
	$('#dxkjAnchor').click(function () {
        $('#jcsqsjDiv').load("./jcsqsj/undergroundspace/undergroundspace.html", function () {
        
		});
    });
	
	//车辆信息
	$('#clxxAnchor').click(function () {
        $('#jcsqsjDiv').load("./jcsqsj/vehicle/vehicle.html", function () {
        
		});
    });
	
	//问题设施
	$('#wtssAnchor').click(function () {
        $('#jcsqsjDiv').load("./jcsqsj/culturefacilities/culturefacilities.html", function () {
        
		});
    });
	
	//商业门店
	$('#symdAnchor').click(function () {
		
		alert('未实现');
		/*$('#jcsqsjDiv').load("./jcsqsj/community/community.html", function () {
        
		});*/
    });
	
	//停车资源
	$('#tczyAnchor').click(function () {
		$('#jcsqsjDiv').load("./jcsqsj/parking/parking.html", function () {
        
		});
    });
	
	//法人组织
	$('#frzzAnchor').click(function () {
		$('#jcsqsjDiv').load("./jcsqsj/organization/organization.html", function () {
        
		});
    });
	
	//志愿者队伍
	$('#zyzdwAnchor').click(function () {
		$('#jcsqsjDiv').load("./jcsqsj/volunteerteam/volunteerteam.html", function () {
        
		});
    });
	
	//居民团体
	$('#jmttAnchor').click(function () {
		$('#jcsqsjDiv').load("./jcsqsj/populationgroup/populationgroup.html", function () {
        
		});
    });
	
	//服务网点
	$('#ffwdAnchor').click(function () {
		$('#jcsqsjDiv').load("./jcsqsj/service_store/service_store.html", function () {
        
		});
    });
	
	//垃圾站
	$('#ljzAnchor').click(function () {
		$('#jcsqsjDiv').load("./jcsqsj/jc_rubbish/jc_rubbish.html", function () {
        
		});
    });
	
	
	/*$('#jcsqsjDiv').load("./jcsqsj/resident/resident.html", function () {
        
	});*/
	
	$.get(getContextPath()+"/jcsqsjController/get",
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			$('#xqxxSpan').html(obj.community);//小区信息
			$('#zzlySpan').html(obj.residebuilding);//住宅楼宇
			
			$('#fwxxSpan').html(obj.room);//房屋信息
			$('#jtxxSpan').html(obj.family);//家庭信息
			$('#jmxxSpan').html(obj.resident);//居民信息
			$('#clxxSpan').html(obj.vehicle);//车辆信息
			$('#swlySpan').html(obj.commercialbuilding);//商务楼宇
			$('#frzzSpan').html(obj.organization);//法人组织
			$('#dxkjSpan').html(obj.undergroundspace);//地下空间
			$('#dzzxxSpan').html(obj.partyorganization);//党组织信息
			$('#zyzdwSpan').html(obj.volunteerteam);//志愿者队伍
			$('#jmttSpan').html(obj.populationgroup);//居民团体
			$('#zbdlSpan').html(obj.roads);//周边道路
			$('#hhsxSpan').html(obj.watersystem);//河湖水系
			//$('#szssSpan').html(obj.);
			$('#wtssSpan').html(obj.culturefacilities);//文体设施
			//$('#tczySpan').html(obj.);
			//$('#bncsSpan').html(obj.);
			$('#ffwdSpan').html(obj.service_store);//服务网点
			$('#ljzSpan').html(obj.ljz);//垃圾站
		}
	});	
});

var dataTable;
/*
function loadsqzzqd()
{
	$('#btnSearch').attr('disabled','disabled');
	var tableName = $('#tableNameQry').val();
	
	$.get(getContextPath()+"/tableController/loadTable?tableName="+tableName,
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
				"data":obj.tableList,
				"columns": [
					{ "data": "tableZHName" ,"sClass":"text-center"},
					{ "data": "status" ,"sClass":"text-center"},
					{ "data": "" ,"sClass":"text-center"}
				],
				columnDefs: [ 
					{
					className: 'control',
					orderable: false,
					targets:   2,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" onclick=\"tableManagement('"+full.id+"')\" class=\"btn btn-info btn-xs\"><i class=\"fa fa-pencil\"></i>管理</a>";

						btn += "<a href=\"#\" onclick=\"tableData('"+full.id+"')\" class=\"btn btn-info btn-xs\"><i class=\"fa fa-pencil\"></i>数据</a>&nbsp;";
						
						btn += "<a href=\"#\" onclick=\"deleteTable('"+full.id+"')\"  class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash-o\"></i>删除</a>&nbsp;";
						
						btn += "<a href=\"#\" onclick=\"generateCode('"+full.id+"')\"  class=\"btn btn-info btn-xs\"><i class=\"fa fa-trash-o\"></i>生成代码</a>&nbsp;";
						
						//<a href=\"#\" onclick=\"viewDetail('"+full.id+"',true)\" class=\"btn btn-info btn-xs\">编辑</a>&nbsp;
						
						//var btn = '<button class="btn btn-success btn-xs" onclick="tableManagement(\''+full.id+'\');return false;"><i class="fa fa-pencil"></i></button>';
						
						//var btn = '<a id="inspectorAnchor" class="btn-primary" href=""><i class="fa fa-pencil"></i></a>';
						
						return btn;
					}
					}
				]
			} );
			
			$('#tableSelect').html('');
			var userArr = [];
			
			for(var i=0;i<obj.tableList.length;i++)
			{
				var table = obj.tableList[i];
				
				userArr[i] = "<option value='" + table.id + "'>" + table.tableName + "</option>";						
			}
			$('#tableSelect').html(userArr.join(''));
	
		}
		else
		{
			jError(obj.errMsg,{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'});
		}
	});
}
*/
var uuid = '';
function addsqzzqd()
{
	uuid = guid();
	$('#main-content').load("./sqzzqd/sqzzqdDetail.html", function () {
		//EditableTable.init();
		//loadStyle();//如果放到ready方法里会出现加载了layerStyle被覆盖的情况
    });
	
	curTableId = '';
}

function deleteTable(tableId)
{
	$.confirm({
		title:"删除确认",
		text:"确认删除表单?",
		confirm: function(button) {
			
			$.post(getContextPath()+"/tableController/deleteTable",
			{
				tableId:tableId
			},
			function(result){
				var obj = jQuery.parseJSON(result);  
				if(obj.success)
				{
					jSuccess("表单删除成功!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'
					});
					
					loadTable();
				}
				else
				{
					jError("表单删除成功!",{
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

function tableManagement(tableId)
{
	curTableId = tableId;
	
	$('#main-content').load("./table/tableDetail.html", function () {
		getTableInfo();
    });
}

function tableData(tableId)
{
	curTableId = tableId;
	
	$('#main-content').load("./table/tableData.html", function () {
		
    });
}
/*
function generateCode(tableId)
{
	$.post(getContextPath()+"/templateController/generateCode",
	{
		tableId:tableId
	},
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			jSuccess("代码生成成功!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
			
		}
		else
		{
			jError("代码生成失败!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
		}
	});
}


function guid() {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
    var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
    return v.toString(16);
  });
}
*/