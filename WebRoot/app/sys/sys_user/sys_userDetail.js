

$(document).ready(function (){
	
	$('#btnAdd').click(ShowAddModal);
	
	$('.dpYears').datepicker({
		autoclose: true
	});
	
	//$('#btnReset').click(Reset);
	
	$('#btnSearch').click(load);
	
	//load();
	
	$("#role").select2({	 
		multiple: true
	});
	
	$.get(getContextPath()+"/sysRoleController/load",
		function(result){
			
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#role').html('');
				
				var roleArr = [];
				
				for(var i=0;i<obj.list.length;i++)
				{
					var role = obj.list[i];
					
					roleArr[i] = "<option value='" + role.id + "'>" + role.name + "</option>";						
				}
				$('#role').html(roleArr.join(''));
			}
		}
	);
	
	initialOrganizationTree('');
	
	if(curId != '')
		viewDetail(curId);
});


function viewDetail(id)
{
	$.get(getContextPath()+"/sysUserController/get?id="+id,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#name').val(obj.name);
				$('#loginid').val(obj.loginid);
				$('#password').val(obj.password);
				//$('#gender').val(obj.gender);
				
				$("input[name='gender'][value='"+obj.gender+"']").attr("checked",true); 
				
				$('#birthday').val(obj.birthday);
				$('#joinday').val(obj.joinday);
				$('#mobile').val(obj.mobile);
				//$('#department').val(obj.department);
				$('#job').val(obj.job);
				//$('#role').val(obj.role);
				
				if(obj.role != null && obj.role != undefined)							
					$("#role").val(obj.role.split(',')).trigger("change");
						
				$('#status').val(obj.status);
				
				$('#parentOrgInput').val(obj.orgIds);
				$('#parentOrgNameInput').val(obj.orgNames);
			}
		});
}

function gobackPage()
{
	
	curId = '';
	
	$('#main-content').load("./sys/sys_user/sys_user.html", function () {
		
    });
	
}

function addOrUpdate()
{
	var roleArr = $('#role').val();
	
	$.post(getContextPath()+"/sysUserController/addOrUpdate",
	{
		id:curId,
		name:$('#name').val(),
		loginid:$('#loginid').val(),
		password:$('#password').val(),
		gender:$("input[name='gender']:checked").val(),//$('#gender').val(),
		birthday:$('#birthday').val(),
		joinday:$('#joinday').val(),
		mobile:$('#mobile').val(),
		department:$('#parentOrgInput').val(),
		job:$('#job').val(),
		role:roleArr.join(','),
		status:$('#status').val()
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


var setting = {
	view: {
		dblClickExpand: false,
		selectedMulti : true,//可以多选
		showLine: true
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	check: {
		enable: true 
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
	if (v.length > 0 ) v = v.substring(0, v.length-1);
	*/
	
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

function initialOrganizationTree(selectedOrgId)
{
	$.get(getContextPath()+"/sysOrganizationController/loadOrganizationTree",
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
				//$("#parentOrgNameInput").val('丰台区');
			}
		}
	});
}

function downloadAttach(fileName)
{
	var url = getContextPath()+"/fileController/download?fileName="+fileName;
	
	window.open(encodeURI(url));
	
	//window.open(getContextPath()+"/fileController/downLoad/"+encodeURI(obj.fileName));
}
