

$(document).ready(function (){
	
	$('#btnAdd').click(ShowAddModal);
	
	$('.dpYears').datepicker({
		autoclose: true
	});
	
	//$('#btnReset').click(Reset);
	
	$('#btnSearch').click(load);
	
	//load();
	
	initialOrganizationTree('');
	
	if(curId != '')
		viewDetail(curId);
	
	
	$('input[type=radio][name=orgtype]').change(function() {
         if (this.value == '社区') {
             $('.community').show();
         }
         else if (this.value == '部门') {
             $('.community').hide();
         }
     });
});


function viewDetail(id)
{
	$.get(getContextPath()+"/sysOrganizationController/get?id="+id,
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

				$("input[name='orgtype'][value='"+obj.orgtype+"']").attr("checked",true); 	
			}
		});
}

function gobackPage()
{
	
	curId = '';
	
	$('#main-content').load("./sys/sys_organization/sys_organization.html", function () {
		
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
	var valtypeArr=new Array();
	$('input[name="type"]:checked').each(function()
	{      
		valtypeArr.push($(this).val());//向数组中添加元素  		
	});
	var valtype = valtypeArr.join(VALUE_SPLITTER);//将数组元素连接起来以构建一个字符串

	$("input[name='buildtype']:checked").val()

	$.post(getContextPath()+"/sysOrganizationController/addOrUpdate",
	{
		id:curId,
		name:$('#name').val(),
		code:$('#code').val(),
		type:valtype,
		border:$('#border').val(),
		area:$('#area').val(),
		address:$('#address').val(),
		telphone:$('#telphone').val(),
		secretary:$('#secretary').val(),
		secretaryphone:$('#secretaryphone').val(),
		directorname:$('#directorname').val(),
		directorphone:$('#directorphone').val(),
		note:$('#note').val(),
		orgtype:$('input:radio[name="orgtype"]:checked').val()
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
