

$(document).ready(function (){
	
	$('#btnAdd').click(ShowAddModal);
	
	$('.dpYears').datepicker({
		autoclose: true
	});
	
	$('#btnSearch').click(load);
	
	if(curId != '')
		viewDetail(curId);
	else
	{
		initialOrganizationTree(curUserOrgId,'');
	
		var now = new Date();
		 var year = now.getFullYear();
		 var month = now.getMonth()+1;
		 var date = now.getDate();
		$('#time').val(year+'-'+month+'-'+date);
	}
	
	if(curUserType == USER_TYPE_COMMUNITY)
		$('#typeDiv').hide();
});


function viewDetail(id)
{
	$.get(getContextPath()+"/noticeController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
				$('#title').val(obj.title);
				//$('#type').val(obj.type);
				$("input[name='type'][value='"+obj.type+"']").attr("checked",true); 
				$('#authorityorg').val(obj.authorityorg);
				$('#body').val(obj.body);
				$('#attach').val(obj.attach);
				
				initialOrganizationTree(curUserOrgId,obj.authorityorg);
				
				var attachArr = obj.attach.split(VALUE_SPLITTER);		

				for(var j=0;j<attachArr.length;j++)				
				{					
					if(attachArr[j] != '')					
					{						
						$('#attachpicktable').append('<tr><td>'+attachArr[j]+'</td><td>上传成功</td>'+							'<td><button type="button" class="btn btn-success btn-xs" onclick="javascript:downloadAttach(\''+attachArr[j]+'\');return false;"><i class="fa fa-check"></i></button></td>'+							'</tr>');					
						
					}
				}				
						
				$('#note').val(obj.note);
				$('#time').val(obj.time);
			}
		});
}

function gobackPage()
{
	
	curId = '';
	
	$('#main-content').load("./oa/notice/notice.html", function () {
		
    });
	
}
/*
function ShowAddModal()
{
	$('#modalDetail').show();
	
	$('#modalTitle').text('新增');
	
	$('#addOrUpdateBtn').text('确定');
	
}
*/
function addOrUpdate()
{
	var type = $('input:radio[name="type"]:checked').val();
	
	if(type == null || type == '')
		type = '社区公告';
	
	$.post(getContextPath()+"/noticeController/addOrUpdate",
	{
		id:curId,
		title:$('#title').val(),
		type:type,    //$('#type').val(),
		authorityorg:$("#parentOrgInput").val(),//$('#authorityorg').val(),
		body:$('#body').val(),
		attach:$('#attach').val(),
		time:$('#time').val()
	},
	function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			jSuccess("数据修改成功!",{
				VerticalPosition : 'center',
				HorizontalPosition : 'center'
			});
			
			gobackPage();
			
			//load();
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


function downloadAttach(fileName)
{
	var url = getContextPath()+"/fileController/download?fileName="+fileName;
	
	window.open(encodeURI(url));
	
	//window.open(getContextPath()+"/fileController/downLoad/"+encodeURI(obj.fileName));
}

var setting = {
	view: {
		dblClickExpand: false
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		beforeClick: beforeClick,
		onClick: onClick
	}
};

function beforeClick(treeId, treeNode) {
	/*var check = (treeNode && !treeNode.isParent);
	if (!check) alert("只能选择城市...");
	return check;*/
}

function onClick(e, treeId, treeNode) {
	//var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
	/*
	debugger;
	nodes = orgTree.getSelectedNodes(),
	v = "";
	nodes.sort(function compare(a,b){return a.id-b.id;});
	for (var i=0, l=nodes.length; i<l; i++) {
		v += nodes[i].name + ",";
	}
	if (v.length > 0 ) v = v.substring(0, v.length-1);*/

	$("#parentOrgNameInput").val(treeNode.name);
	
	 $("#parentOrgInput").val(treeNode.id);
}
		 
		 
function showMenu() {	
	$("#menuContent").slideDown("fast");
	
	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideMenu();
	}
}
	
var orgTree;

function initialOrganizationTree(rootOrgId,selectedOrgId)
{
	$.get(getContextPath()+"/sysOrganizationController/loadOrganizationTree?rootOrgId="+rootOrgId,
		function(result){
		var obj = jQuery.parseJSON(result);  
		if(obj.success)
		{
			orgTree = $.fn.zTree.init($("#treeDemo"), setting, obj.organizationList);
			if(selectedOrgId != null && selectedOrgId != '')
			{
				var nodes = orgTree.getNodesByParam("id", selectedOrgId, null);
				if (nodes.length > 0)
				{
					orgTree.selectNode(nodes[0]);
					
					$("#parentOrgNameInput").val(nodes[0].name);
					$("#parentOrgInput").val(selectedOrgId);
				}
			}
			else{
				var node = orgTree.getNodesByFilter(function (node) { return node.level == 0 }, true);  
				$("#parentOrgNameInput").val(node.name);
				$("#parentOrgInput").val(node.id);				
			}
		}
	});
}