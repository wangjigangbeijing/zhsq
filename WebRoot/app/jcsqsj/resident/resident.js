

$(document).ready(function (){
	
	$('#btnAdd1').click(ShowAddModal);
	$('#btnAdd2').click(ShowAddModal);
	
	$('.dpYears').datepicker({
		autoclose: true
	});
	
	//$('#btnReset').click(Reset);
	
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

	
	/*$.get(getContextPath()+'/residentController/load?name='+name+'&idnumber='+idnumber+'&characteristics='+characteristics+'&ofcommunity='+ofcommunity+'&ofresidebuilding='+ofresidebuilding+'&ofunit='+ofunit+'&ofroom='+ofroom+'&offamily='+offamily+'&sex='+sex+'&residencestatus='+residencestatus+'&ishouseholder='+ishouseholder+'&relationshiphouseholder='+relationshiphouseholder+'&registrationcategory='+registrationcategory+'&birthday='+birthday+'&age='+age+'&nation='+nation+'&education='+education+'&professionstatus='+professionstatus+'&professiontype='+professiontype+'&tel='+tel+'&mobile='+mobile+'&marriage='+marriage+'&health='+health+'&zdr_type='+zdr_type+'&cj_disabilitytype='+cj_disabilitytype+'&',
	function(result){
		
		if(searchtype == 1){
			
			$('#btnSearch1').removeAttr('disabled');
		}
		else {
			$('#btnSearch2').removeAttr('disabled');
		}

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
					{ 'data': 'characteristics' ,'sClass':'text-center'},	
					{ 'data': 'ofcommunity' ,'sClass':'text-center'},					
					{ 'data': 'sex' ,'sClass':'text-center'},
					{ 'data': 'residencestatus' ,'sClass':'text-center'},					
					{ 'data': 'age' ,'sClass':'text-center'},
					{ 'data': 'professionstatus' ,'sClass':'text-center'},
					{ 'data': 'mobile' ,'sClass':'text-center'},
					{ 'data': '' ,'sClass':'text-center'}
				],
				columnDefs: [ 
					{
					className: 'control',
					orderable: false,
					targets:  8,//从0开始
					mRender : function(data,type,full){
						var btn = "<a href=\"#\" onclick=\"viewData('"+full.id+"')\" class=\"btn btn-info btn-xs\"><i class=\"fa fa-pencil\"></i>查看</a>&nbsp;";
						
						btn += "<a href=\"#\" onclick=\"editData('"+full.id+"')\" class=\"btn btn-primary btn-xs\"><i class=\"fa fa-pencil\"></i>编辑</a>&nbsp;";

						btn += "<a href=\"#\" onclick=\"deleteData('"+full.id+"')\"  class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash-o\"></i>删除</a>";
						
						return btn;
					}
					}
				]
			} );
		}
	});*/
	
	
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
				"ordering": true,
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
						
						btn += "<a href=\"#\" onclick=\"editData('"+full.id+"')\" class=\"btn btn-primary btn-xs\"><i class=\"fa fa-pencil\"></i>编辑</a>&nbsp;";

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

/*
function viewDetail(id)
{
	//$('#modalTitle').text('修改用户信息');
	curId = id;
	$.get(getContextPath()+"/residentController/get?id="+curId,
		function(result){
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$('#modalDetail').show();
				
								$('#dataid').val(obj.dataid);
				$('#name').val(obj.name);
				$('#identitytype').val(obj.identitytype);
				$('#idnumber').val(obj.idnumber);
if(obj.characteristics != null){
	var characteristicsArr = obj.characteristics.split(VALUE_SPLITTER);
	for(var j=0;j<characteristicsArr.length;j++)
	{
		if(characteristicsArr[j] != '')
		{
			$("input[name='characteristics'][value='"+characteristicsArr[j]+"']").attr('checked','true');
		}
	}	
}	
				$('#ofcommunity').val(obj.ofcommunity);
				$('#ofresidebuilding').val(obj.ofresidebuilding);
				$('#ofunit').val(obj.ofunit);
				$('#ofroom').val(obj.ofroom);
				$('#offamily').val(obj.offamily);
				$('#sex').val(obj.sex);
				$('#residencestatus').val(obj.residencestatus);
				$('#ishouseholder').val(obj.ishouseholder);
				$('#relationshiphouseholder').val(obj.relationshiphouseholder);
				$('#registrationcategory').val(obj.registrationcategory);
				$('#nationality').val(obj.nationality);
				$('#homeplace').val(obj.homeplace);
				$('#birthday').val(obj.birthday);
				$('#age').val(obj.age);
				$('#nation').val(obj.nation);
				$('#politicalstatus').val(obj.politicalstatus);
				$('#usedname').val(obj.usedname);
				$('#residenceaddress').val(obj.residenceaddress);
				$('#residentialaddress').val(obj.residentialaddress);
				$('#education').val(obj.education);
				$('#professionstatus').val(obj.professionstatus);
				$('#professiontype').val(obj.professiontype);
				$('#workunit').val(obj.workunit);
				$('#job').val(obj.job);
				$('#issocialsecurity').val(obj.issocialsecurity);
				$('#email').val(obj.email);
				$('#tel').val(obj.tel);
				$('#fax').val(obj.fax);
				$('#mobile').val(obj.mobile);
				$('#eligiousbelief').val(obj.eligiousbelief);
				$('#marriage').val(obj.marriage);
				$('#isforeignmarriage').val(obj.isforeignmarriage);
				$('#health').val(obj.health);
				$('#military').val(obj.military);
				$('#stature').val(obj.stature);
				$('#blood').val(obj.blood);
				$('#hobby').val(obj.hobby);
				$('#reasonnotresidence').val(obj.reasonnotresidence);
				$('#custodian').val(obj.custodian);
				$('#custodiantel').val(obj.custodiantel);
				$('#custodianrelationship').val(obj.custodianrelationship);
				$('#custodianincommunity').val(obj.custodianincommunity);
				$('#pictures').val(obj.pictures);
				$('#note').val(obj.note);
				$('#dy_partymembertype').val(obj.dy_partymembertype);
				$('#dy_of_partyorganization').val(obj.dy_of_partyorganization);
				$('#dy_joinpartydate').val(obj.dy_joinpartydate);
				$('#dy_inpartydate').val(obj.dy_inpartydate);
				$('#dy_membership').val(obj.dy_membership);
				$('#dy_islost').val(obj.dy_islost);
				$('#dy_lostdate').val(obj.dy_lostdate);
				$('#dy_movemember').val(obj.dy_movemember);
				$('#dy_moveto').val(obj.dy_moveto);
				$('#zdr_type').val(obj.zdr_type);
				$('#zdr_reason').val(obj.zdr_reason);
				$('#zdr_custodian').val(obj.zdr_custodian);
				$('#zdr_custodiantel').val(obj.zdr_custodiantel);
				$('#jzr_correctioncontent').val(obj.jzr_correctioncontent);
				$('#jzr_correctiondate').val(obj.jzr_correctiondate);
				$('#jzr_correctionaddress').val(obj.jzr_correctionaddress);
				$('#sy_unemployedreason').val(obj.sy_unemployedreason);
				$('#sy_unemployedreemployment').val(obj.sy_unemployedreemployment);
				$('#sy_reemploymentunit').val(obj.sy_reemploymentunit);
				$('#ylfn_firstmarriagedate').val(obj.ylfn_firstmarriagedate);
				$('#ylfn_children').val(obj.ylfn_children);
				$('#ylfn_bornchildren').val(obj.ylfn_bornchildren);
				$('#ylfn_pregnancy').val(obj.ylfn_pregnancy);
				$('#ylfn_lastmenstruation').val(obj.ylfn_lastmenstruation);
				$('#ylfn_terminationpregnancy').val(obj.ylfn_terminationpregnancy);
				$('#lnr_oldid').val(obj.lnr_oldid);
				$('#lnr_economicsources').val(obj.lnr_economicsources);
				$('#lnr_livingconditions').val(obj.lnr_livingconditions);
				$('#lnr_selfcare').val(obj.lnr_selfcare);
				$('#lnr_specialsubsidies').val(obj.lnr_specialsubsidies);
				$('#lnr_emergencycontact').val(obj.lnr_emergencycontact);
				$('#lnr_emergencycontactrelated').val(obj.lnr_emergencycontactrelated);
				$('#lnr_emergencycontacttel').val(obj.lnr_emergencycontacttel);
				$('#lnr_physicalcondition').val(obj.lnr_physicalcondition);
				$('#lnr_medicationcondition').val(obj.lnr_medicationcondition);
				$('#cj_disabilitytype').val(obj.cj_disabilitytype);
				$('#cj_disabilitylevel').val(obj.cj_disabilitylevel);
				$('#cj_disabilityreason').val(obj.cj_disabilityreason);
				$('#cj_disabilityemployment').val(obj.cj_disabilityemployment);
				$('#jsb_type').val(obj.jsb_type);
				$('#jsb_medicalhistory').val(obj.jsb_medicalhistory);
				$('#jhr_name').val(obj.jhr_name);
				$('#hjr_tel').val(obj.hjr_tel);
				$('#zyz_certificate_id').val(obj.zyz_certificate_id);
				$('#zyz_special_skill').val(obj.zyz_special_skill);
				$('#jmdb_representative_level').val(obj.jmdb_representative_level);
				$('#jmdb_startofterm').val(obj.jmdb_startofterm);
				$('#jmdb_endofterm').val(obj.jmdb_endofterm);
				$('#wtgg_skill_type').val(obj.wtgg_skill_type);
				$('#wtgg_special_skill').val(obj.wtgg_special_skill);

			}
		});
}

function closeModalDetail()
{
	$('#modalDetail').hide();
	curId = '';
	
		$('#dataid').val('');
	$('#name').val('');
	$('#identitytype').val('');
	$('#idnumber').val('');
	$('#characteristics').val('');
	$('#ofcommunity').val('');
	$('#ofresidebuilding').val('');
	$('#ofunit').val('');
	$('#ofroom').val('');
	$('#offamily').val('');
	$('#sex').val('');
	$('#residencestatus').val('');
	$('#ishouseholder').val('');
	$('#relationshiphouseholder').val('');
	$('#registrationcategory').val('');
	$('#nationality').val('');
	$('#homeplace').val('');
	$('#birthday').val('');
	$('#age').val('');
	$('#nation').val('');
	$('#politicalstatus').val('');
	$('#usedname').val('');
	$('#residenceaddress').val('');
	$('#residentialaddress').val('');
	$('#education').val('');
	$('#professionstatus').val('');
	$('#professiontype').val('');
	$('#workunit').val('');
	$('#job').val('');
	$('#issocialsecurity').val('');
	$('#email').val('');
	$('#tel').val('');
	$('#fax').val('');
	$('#mobile').val('');
	$('#eligiousbelief').val('');
	$('#marriage').val('');
	$('#isforeignmarriage').val('');
	$('#health').val('');
	$('#military').val('');
	$('#stature').val('');
	$('#blood').val('');
	$('#hobby').val('');
	$('#reasonnotresidence').val('');
	$('#custodian').val('');
	$('#custodiantel').val('');
	$('#custodianrelationship').val('');
	$('#custodianincommunity').val('');
	$('#pictures').val('');
	$('#note').val('');
	$('#dy_partymembertype').val('');
	$('#dy_of_partyorganization').val('');
	$('#dy_joinpartydate').val('');
	$('#dy_inpartydate').val('');
	$('#dy_membership').val('');
	$('#dy_islost').val('');
	$('#dy_lostdate').val('');
	$('#dy_movemember').val('');
	$('#dy_moveto').val('');
	$('#zdr_type').val('');
	$('#zdr_reason').val('');
	$('#zdr_custodian').val('');
	$('#zdr_custodiantel').val('');
	$('#jzr_correctioncontent').val('');
	$('#jzr_correctiondate').val('');
	$('#jzr_correctionaddress').val('');
	$('#sy_unemployedreason').val('');
	$('#sy_unemployedreemployment').val('');
	$('#sy_reemploymentunit').val('');
	$('#ylfn_firstmarriagedate').val('');
	$('#ylfn_children').val('');
	$('#ylfn_bornchildren').val('');
	$('#ylfn_pregnancy').val('');
	$('#ylfn_lastmenstruation').val('');
	$('#ylfn_terminationpregnancy').val('');
	$('#lnr_oldid').val('');
	$('#lnr_economicsources').val('');
	$('#lnr_livingconditions').val('');
	$('#lnr_selfcare').val('');
	$('#lnr_specialsubsidies').val('');
	$('#lnr_emergencycontact').val('');
	$('#lnr_emergencycontactrelated').val('');
	$('#lnr_emergencycontacttel').val('');
	$('#lnr_physicalcondition').val('');
	$('#lnr_medicationcondition').val('');
	$('#cj_disabilitytype').val('');
	$('#cj_disabilitylevel').val('');
	$('#cj_disabilityreason').val('');
	$('#cj_disabilityemployment').val('');
	$('#jsb_type').val('');
	$('#jsb_medicalhistory').val('');
	$('#jhr_name').val('');
	$('#hjr_tel').val('');
	$('#zyz_certificate_id').val('');
	$('#zyz_special_skill').val('');
	$('#jmdb_representative_level').val('');
	$('#jmdb_startofterm').val('');
	$('#jmdb_endofterm').val('');
	$('#wtgg_skill_type').val('');
	$('#wtgg_special_skill').val('');

}
*/

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


