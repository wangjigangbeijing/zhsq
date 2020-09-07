

$(document).ready(function (){
	if(haveRight('jc_resident_add') == false)
	{
		$('#btnAdd1').hide();
		$('#btnAdd2').hide();
	}
	
	if(haveRight('jc_resident_exp') == false)
	{
		$('#btnExport1').hide();
		$('#btnExport2').hide();
	}
	
	$('#btnAdd1').click(ShowAddModal);
	$('#btnAdd2').click(ShowAddModal);
	
	$('.dpYears').datepicker({
		autoclose: true
	});
	
	//$('#btnReset').click(Reset);
	
	$('#btnExport1').click(exportData);
	$('#btnExport2').click(exportData);
	
	$('#btnSearch1').click(load);
	$('#btnSearch2').click(load);
	
	load();
	
	$.ajax({
	  type: 'POST',
	  url: getContextPath()+"/dictionaryController/getDataOfDic",
	  data: JSON.stringify({id:'ofcommunity'}),
	  contentType: "application/json",
	  success:function(result){
				
			if(result.success)
			{
				$('#ofcommunityQuery').html('');
				var filterArr = [];
				
				filterArr[0] = "<option value=''></option>";				
				
				for(var i=0;i<result.value.length;i++)
				{
					var filter = result.value[i];
					
					filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
				}
				$('#ofcommunityQuery').html(filterArr.join(''));
				
				$('#ofresidebuildingQuery').html('');				
				$('#ofunitQuery').html('');
				$('#ofroomQuery').html('');
				$('#offamilyQuery').html('');
			}
			else
			{
				jError("获取社区列表失败!",{
					VerticalPosition : 'center',
					HorizontalPosition : 'center'
				});
			}
		},
	  dataType: "json"
	});
	
	$('#ofcommunityQuery').change(function(){
		
		var ofcommunity = $('#ofcommunityQuery').val();
		
		$.ajax({
		  type: 'POST',
		  url: getContextPath()+"/dictionaryController/getDataOfDic",
		  data: JSON.stringify({id:'ofresidebuilding',params:[{enname:'ofcommunity',value:ofcommunity}]}),
		  contentType: "application/json",
		  success:function(result){
					
				if(result.success)
				{
					$('#ofresidebuildingQuery').html('');
					var filterArr = [];
					filterArr[0] = "<option value=''></option>";				
					for(var i=0;i<result.value.length;i++)
					{
						var filter = result.value[i];
						
						filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
					}
					$('#ofresidebuildingQuery').html(filterArr.join(''));
					
					$('#ofunitQuery').html('');
					$('#ofroomQuery').html('');
					$('#offamilyQuery').html('');
				}
				else
				{
					jError("获取居民楼列表失败!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'
					});
				}
			},
		  dataType: "json"
		});
	});
	
	$('#ofresidebuildingQuery').change(function(){
		
		var ofresidebuilding = $('#ofresidebuildingQuery').val();
		var ofcommunity = $('#ofcommunityQuery').val();
		
		$.ajax({
		  type: 'POST',
		  url: getContextPath()+"/dictionaryController/getDataOfDic",
		  data: JSON.stringify({id:'ofunit',params:[{enname:'ofcommunity',value:ofcommunity},{enname:'ofresidebuilding',value:ofresidebuilding}]}),
		  contentType: "application/json",
		  success:function(result){
					
				if(result.success)
				{
					$('#ofunit').html('');
					var filterArr = [];
					filterArr[0] = "<option value=''></option>";							
					for(var i=0;i<result.value.length;i++)
					{
						var filter = result.value[i];
						
						filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
					}
					$('#ofunitQuery').html(filterArr.join(''));
					
					$('#ofroomQuery').html('');
					$('#offamilyQuery').html('');
				}
				else
				{
					jError("获取单元列表失败!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'
					});
				}
			},
		  dataType: "json"
		});
	});
	
	$('#ofunitQuery').change(function(){
		
		var ofresidebuilding = $('#ofresidebuildingQuery').val();
		var ofcommunity = $('#ofcommunityQuery').val();
		var ofunit = $('#ofunitQuery').val();
		
		$.ajax({
		  type: 'POST',
		  url: getContextPath()+"/dictionaryController/getDataOfDic",
		  data: JSON.stringify({id:'ofroom',params:[{enname:'ofcommunity',value:ofcommunity},{enname:'ofresidebuilding',value:ofresidebuilding},{enname:'ofunit',value:ofunit}]}),
		  contentType: "application/json",
		  success:function(result){
					
				if(result.success)
				{
					$('#ofroomQuery').html('');
					var filterArr = [];
					filterArr[0] = "<option value=''></option>";							
					for(var i=0;i<result.value.length;i++)
					{
						var filter = result.value[i];
						
						filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
					}
					$('#ofroomQuery').html(filterArr.join(''));
					$('#offamilyQuery').html('');
				}
				else
				{
					jError("获取房间列表失败!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'
					});
				}
			},
		  dataType: "json"
		});
	});
	
	$('#ofroomQuery').change(function(){
		
		var ofresidebuilding = $('#ofresidebuildingQuery').val();
		var ofcommunity = $('#ofcommunityQuery').val();
		var ofunit = $('#ofunitQuery').val();
		var ofroom = $('#ofroomQuery').val();
		
		$.ajax({
		  type: 'POST',
		  url: getContextPath()+"/dictionaryController/getDataOfDic",
		  data: JSON.stringify({id:'offamily',params:[{enname:'ofcommunity',value:ofcommunity},{enname:'ofresidebuilding',value:ofresidebuilding},{enname:'ofunit',value:ofunit},{enname:'ofroom',value:ofroom}]}),
		  contentType: "application/json",
		  success:function(result){
					
				if(result.success)
				{
					$('#offamilyQuery').html('');
					var filterArr = [];
					filterArr[0] = "<option value=''></option>";							
					for(var i=0;i<result.value.length;i++)
					{
						var filter = result.value[i];
						
						filterArr[i+1] = "<option value='" + filter.key + "'>" + filter.value + "</option>";						
					}
					$('#offamilyQuery').html(filterArr.join(''));
				}
				else
				{
					jError("获取房间列表失败!",{
						VerticalPosition : 'center',
						HorizontalPosition : 'center'
					});
				}
			},
		  dataType: "json"
		});
	});
	
	
});

var curId;

var dataGridTable;

function load()
{
	var searchtype = $("#searchtype").val();
	if(searchtype == 1){
		$('#btnSearch1').attr('disabled','disabled');
	}
	else {
		$('#btnSearch2').attr('disabled','disabled');
	}

	var name = $('#nameQuery').val();
	var idnumber = $('#idnumberQuery').val();
	var characteristics = $('#characteristicsQuery').val();
 if(searchtype == 2){
	 name = $('#nameQuery2').val();
	 idnumber = $('#idnumberQuery2').val();
	 characteristics = $('#characteristicsQuery2').val();
 }

 
 var ofcommunity = $('#ofcommunityQuery').val();
 var ofresidebuilding = $('#ofresidebuildingQuery').val();
 var ofunit = $('#ofunitQuery').val();
 var ofroom = $('#ofroomQuery').val();
 var offamily = $('#offamilyQuery').val();
 var sex = $('#sexQuery').val();
 var residencestatus = $('#residencestatusQuery').val();
 var ishouseholder = $('#ishouseholderQuery').val();
 var relationshiphouseholder = $('#relationshiphouseholderQuery').val();
 var registrationcategory = $('#registrationcategoryQuery').val();
 var birthday = $('#birthdayQuery').val();
 var age = $('#ageQuery').val();
 var nation = $('#nationQuery').val();
 var education = $('#educationQuery').val();
 var professionstatus = $('#professionstatusQuery').val();
 var professiontype = $('#professiontypeQuery').val();
 var mobile = $('#mobileQuery').val();
 var marriage = $('#marriageQuery').val();
 var zdr_type = $('#zdr_typeQuery').val();
 var cj_disabilitytype = $('#cj_disabilitytypeQuery').val();

			lTotalCnt = 0;
			
			if(dataGridTable!=null){  
				dataGridTable.fnClearTable(0);  
			}
			
			dataGridTable = $("#dataTable").dataTable({	
				destroy: true,
				stateSave: false,
				iDisplayLength: 10,
				lengthChange: false,
				"paging": true,
				"lengthChange": false,
				"searching": false,
				"bProcessing" : true,  
				"aaSorting": [[ 0, "asc" ]],  
				"ordering": false,
				"info": true,
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
				"autoWidth": false,
				"bServerSide" : true,
				"sAjaxSource" : getContextPath()+'/residentController/load',
				"fnServerParams" : function(aoData) {  
					aoData.push({  
						"name" : "statId",  
						"value" : "abc"
					});  
				},  
				"columns": [
							{ 'data': 'name' ,'sClass':'text-center',
								mRender : function(data,type,full){
									return showData(aesDecrypt(full.name), 1);
								}
							},					
							{ 'data': 'characteristics' ,'sClass':'text-center'},	
							{ 'data': 'ofcommunity' ,'sClass':'text-center'},					
							{ 'data': 'sex' ,'sClass':'text-center'},
							{ 'data': 'residencestatus' ,'sClass':'text-center'},					
							{ 'data': 'age' ,'sClass':'text-center'},
							{ 'data': 'professionstatus' ,'sClass':'text-center'},
							{ 'data': 'mobile' ,'sClass':'text-center',
								mRender : function(data,type,full){
									return showData(aesDecrypt(full.mobile), 2);
								}
							},
							{ 'data': '' ,'sClass':'text-center'}
						],
				columnDefs: [
						{
					className: 'control',
					orderable: false,
					targets:  8,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" onclick=\"viewData('"+full.id+"')\" class=\"btn btn-info btn-xs\"><i class=\"fa fa-pencil\"></i>查看</a>&nbsp;";
						
						if(haveRight('jc_resident_edit') == true)
							btn += "<a href=\"#\" onclick=\"editData('"+full.id+"')\" class=\"btn btn-primary btn-xs\"><i class=\"fa fa-pencil\"></i>编辑</a>&nbsp;";

						if(haveRight('jc_resident_del') == true)
							btn += "<a href=\"#\" onclick=\"deleteData('"+full.id+"')\"  class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash-o\"></i>删除</a>";
						
						return btn;
					}
					}
				],
				"fnServerData" : function(sSource, aoData, fnCallback) {  
					$('#btnSearch').attr('disabled','disabled');
					
					$.ajax({
						"type" : 'get',  
						"url" : sSource,  
						"dataType" : "json",  
						"data" : {  
							aoData : JSON.stringify(aoData),
							totalCnt : lTotalCnt,
							name : name,
							idnumber : idnumber,
							characteristics : characteristics,
							ofcommunity : ofcommunity,
							ofresidebuilding : ofresidebuilding,
							ofunit : ofunit,
							ofroom : ofroom,
							offamily : offamily,
							sex : sex,
							residencestatus : residencestatus,
							ishouseholder : ishouseholder,
							relationshiphouseholder : relationshiphouseholder,
							registrationcategory : registrationcategory,
							birthday : birthday,
							age : age,
							nation : nation,
							education : education,
							professionstatus : professionstatus,
							professiontype : professiontype,
							mobile : mobile,
							marriage : marriage,
							zdr_type : zdr_type,
							cj_disabilitytype : cj_disabilitytype
						},
						"success" : function(resp) {
							
							if(searchtype == 1){
			
								$('#btnSearch1').removeAttr('disabled');
							}
							else {
								$('#btnSearch2').removeAttr('disabled');
							}
							//$('#btnSearch').removeAttr('disabled');
							if(resp.success == true)
							{
								fnCallback(resp);
							
								lTotalCnt = resp.iTotalRecords;
							}
							else
							{
								jError("加载数据出错!",{
									VerticalPosition : 'center',
									HorizontalPosition : 'center'
								});
							}
						},
						"failure":function (result) {
							$('#btnSearch').removeAttr('disabled');
						}
					});  
				}
			});
}


function viewData(id)
{
	curId = id;
	$('#main-content').load("./jcsqsj/resident/residentDetail.html", function () {
		$('#confirmBtn').hide();
		
		$("select").attr("disabled","disabled");
		$("textarea").attr("disabled","disabled");
		$("input").attr("disabled","disabled");
		$("#picturespick").hide();
		
		$("#cancelBtn").text('返回');
    });
}

function editData(id)
{
	curId = id;
	$('#main-content').load("./jcsqsj/resident/residentDetail.html", function () {
		
    });
}


function ShowAddModal()
{
	//$('#modalDetail').show();
	
	curId = '';
	$('#main-content').load("./jcsqsj/resident/residentDetail.html", function () {
		
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
			
			$.post(getContextPath()+"/residentController/delete",
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

function selectidtype(){
	var val = $('#identitytype').val();
	if(val == '居民身份证'){
		$('#idcardBn').show();
	}
	else {
		$('#idcardBn').hide();
	}
}



function exportData()
{
	var queryStr = '';
	
	var searchtype = $("#searchtype").val();
	if(searchtype == 1){
		$('#btnExport1').attr('disabled','disabled');
	}
	else {
		$('#btnExport2').attr('disabled','disabled');
	}
	
	
	var searchtype = $("#searchtype").val();
	if(searchtype == 1){
		$('#btnSearch1').attr('disabled','disabled');
	}
	else {
		$('#btnSearch2').attr('disabled','disabled');
	}

	var name = $('#nameQuery').val();
	var idnumber = $('#idnumberQuery').val();
	var characteristics = $('#characteristicsQuery').val();
	 if(searchtype == 2){
		 name = $('#nameQuery2').val();
		 idnumber = $('#idnumberQuery2').val();
		 characteristics = $('#characteristicsQuery2').val();
	 }

	if(name != '')
		queryStr += "name like '%"+name+"%' AND ";
	if(idnumber != '')
		queryStr += "idnumber like '%"+idnumber+"%' AND ";
	if(characteristics != '')
		queryStr += "characteristics like '%"+characteristics+"%' AND ";

	var ofcommunity = $('#ofcommunityQuery').val();
	if(ofcommunity != '')
		queryStr += "ofcommunity = '"+ofcommunity+"' AND ";
	
	var ofresidebuilding = $('#ofresidebuildingQuery').val();
	if(ofresidebuilding != '' && ofresidebuilding != null)
		queryStr += "ofresidebuilding = '"+ofresidebuilding+"' AND ";
	
	var ofunit = $('#ofunitQuery').val();
	if(ofunit != '' && ofunit != null)
		queryStr += "ofunit = '"+ofunit+"' AND ";
	
	var ofroom = $('#ofroomQuery').val();
	if(ofroom != '' && ofroom != null)
		queryStr += "ofroom = '"+ofroom+"' AND ";
	
	var offamily = $('#offamilyQuery').val();
	if(offamily != '' && offamily != null)
		queryStr += "offamily = '"+offamily+"' AND ";
	
	var sex = $('#sexQuery').val();
	if(sex != '')
		queryStr += "sex = '"+sex+"' AND ";
	
	var residencestatus = $('#residencestatusQuery').val();
	if(residencestatus != '')
		queryStr += "residencestatus = '"+residencestatus+"' AND ";
	
	var ishouseholder = $('#ishouseholderQuery').val();
	if(ishouseholder != '')
		queryStr += "ishouseholder = '"+ishouseholder+"' AND ";
	
	var relationshiphouseholder = $('#relationshiphouseholderQuery').val();
	if(relationshiphouseholder != '')
		queryStr += "relationshiphouseholder = '"+relationshiphouseholder+"' AND ";
	
	var registrationcategory = $('#registrationcategoryQuery').val();
	if(registrationcategory != '')
		queryStr += "registrationcategory = '"+registrationcategory+"' AND ";
	
	var birthday = $('#birthdayQuery').val();
	if(birthday != '')
		queryStr += "birthday = '"+birthday+"' AND ";
	
	var age = $('#ageQuery').val();
	if(age != '')
		queryStr += "age = '"+age+"' AND ";
	
	var nation = $('#nationQuery').val();
	if(nation != '')
		queryStr += "nation = '"+nation+"' AND ";
	
	var education = $('#educationQuery').val();
	if(education != '')
		queryStr += "education = '"+education+"' AND ";
	
	var professionstatus = $('#professionstatusQuery').val();
	if(professionstatus != '')
		queryStr += "professionstatus = '"+professionstatus+"' AND ";
	
	var professiontype = $('#professiontypeQuery').val();
	if(professiontype != '')
		queryStr += "professiontype = '"+professiontype+"' AND ";
	
	var mobile = $('#mobileQuery').val();
	if(mobile != '')
		queryStr += "mobile like '%"+mobile+"%' AND ";
	
	var marriage = $('#marriageQuery').val();
	if(marriage != '')
		queryStr += "marriage = '"+marriage+"' AND ";
	
	var zdr_type = $('#zdr_typeQuery').val();
	if(zdr_type != '')
		queryStr += "zdr_type = '"+zdr_type+"' AND ";
	
	var cj_disabilitytype = $('#cj_disabilitytypeQuery').val();
	if(cj_disabilitytype != '')
		queryStr += "cj_disabilitytype = '"+cj_disabilitytype+"' AND ";
	
	$.post(getContextPath()+"/dataController/exportDataOfTable",
		{
			tableId:'jc_resident',
			queryStr:queryStr
		},
		function(result){
			
			$('#btnExport1').removeAttr('disabled');
			$('#btnExport2').removeAttr('disabled');
			//$('#loading').hide();
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				window.open(getContextPath()+"/fileController/download?fileName="+encodeURI(obj.fileName));
			}
			else
			{
				jError("数据导出失败,请联系管理员!",{
							VerticalPosition : 'center',
							HorizontalPosition : 'center'
						});
			}
	});
}