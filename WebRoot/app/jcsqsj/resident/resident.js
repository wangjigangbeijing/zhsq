

$(document).ready(function (){
	
	$('#btnAdd').click(ShowAddModal);
	
	$('.dpYears').datepicker({
		autoclose: true
	});
	
	//$('#btnReset').click(Reset);
	
	$('#btnSearch').click(load);
	
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

function load()
{
	$('#btnSearch').attr('disabled','disabled');
	 var name = $('#nameQuery').val();
 var identitytype = $('#identitytypeQuery').val();
 var idnumber = $('#idnumberQuery').val();
 var characteristics = $('#characteristicsQuery').val();
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
 var politicalstatus = $('#politicalstatusQuery').val();
 var education = $('#educationQuery').val();
 var professionstatus = $('#professionstatusQuery').val();
 var professiontype = $('#professiontypeQuery').val();
 var issocialsecurity = $('#issocialsecurityQuery').val();
 var tel = $('#telQuery').val();
 var mobile = $('#mobileQuery').val();
 var marriage = $('#marriageQuery').val();
 var isforeignmarriage = $('#isforeignmarriageQuery').val();
 var health = $('#healthQuery').val();
 var blood = $('#bloodQuery').val();
 var custodianincommunity = $('#custodianincommunityQuery').val();
 var dy_partymembertype = $('#dy_partymembertypeQuery').val();
 var dy_of_partyorganization = $('#dy_of_partyorganizationQuery').val();
 var dy_joinpartydate = $('#dy_joinpartydateQuery').val();
 var dy_membership = $('#dy_membershipQuery').val();
 var dy_islost = $('#dy_islostQuery').val();
 var dy_movemember = $('#dy_movememberQuery').val();
 var zdr_type = $('#zdr_typeQuery').val();
 var jzr_correctioncontent = $('#jzr_correctioncontentQuery').val();
 var sy_unemployedreemployment = $('#sy_unemployedreemploymentQuery').val();
 var lnr_economicsources = $('#lnr_economicsourcesQuery').val();
 var lnr_livingconditions = $('#lnr_livingconditionsQuery').val();
 var cj_disabilitytype = $('#cj_disabilitytypeQuery').val();
 var cj_disabilitylevel = $('#cj_disabilitylevelQuery').val();
 var jsb_type = $('#jsb_typeQuery').val();

	
	$.get(getContextPath()+'/residentController/load?name='+name+'&identitytype='+identitytype+'&idnumber='+idnumber+'&characteristics='+characteristics+'&ofcommunity='+ofcommunity+'&ofresidebuilding='+ofresidebuilding+'&ofunit='+ofunit+'&ofroom='+ofroom+'&offamily='+offamily+'&sex='+sex+'&residencestatus='+residencestatus+'&ishouseholder='+ishouseholder+'&relationshiphouseholder='+relationshiphouseholder+'&registrationcategory='+registrationcategory+'&birthday='+birthday+'&age='+age+'&nation='+nation+'&politicalstatus='+politicalstatus+'&education='+education+'&professionstatus='+professionstatus+'&professiontype='+professiontype+'&issocialsecurity='+issocialsecurity+'&tel='+tel+'&mobile='+mobile+'&marriage='+marriage+'&isforeignmarriage='+isforeignmarriage+'&health='+health+'&blood='+blood+'&custodianincommunity='+custodianincommunity+'&dy_partymembertype='+dy_partymembertype+'&dy_of_partyorganization='+dy_of_partyorganization+'&dy_joinpartydate='+dy_joinpartydate+'&dy_membership='+dy_membership+'&dy_islost='+dy_islost+'&dy_movemember='+dy_movemember+'&zdr_type='+zdr_type+'&jzr_correctioncontent='+jzr_correctioncontent+'&sy_unemployedreemployment='+sy_unemployedreemployment+'&lnr_economicsources='+lnr_economicsources+'&lnr_livingconditions='+lnr_livingconditions+'&cj_disabilitytype='+cj_disabilitytype+'&cj_disabilitylevel='+cj_disabilitylevel+'&jsb_type='+jsb_type+'&',
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
										{ 'data': 'dataid' ,'sClass':'text-center'},
					{ 'data': 'name' ,'sClass':'text-center'},
					{ 'data': 'identitytype' ,'sClass':'text-center'},
					{ 'data': 'idnumber' ,'sClass':'text-center'},
					{ 'data': 'characteristics' ,'sClass':'text-center'},
					{ 'data': 'ofcommunity' ,'sClass':'text-center'},
					{ 'data': 'ofresidebuilding' ,'sClass':'text-center'},
					{ 'data': 'ofunit' ,'sClass':'text-center'},
					{ 'data': 'ofroom' ,'sClass':'text-center'},
					{ 'data': 'offamily' ,'sClass':'text-center'},
					{ 'data': 'sex' ,'sClass':'text-center'},
					{ 'data': 'residencestatus' ,'sClass':'text-center'},
					{ 'data': 'ishouseholder' ,'sClass':'text-center'},
					{ 'data': 'relationshiphouseholder' ,'sClass':'text-center'},
					{ 'data': 'registrationcategory' ,'sClass':'text-center'},
					{ 'data': 'nationality' ,'sClass':'text-center'},
					{ 'data': 'homeplace' ,'sClass':'text-center'},
					{ 'data': 'birthday' ,'sClass':'text-center'},
					{ 'data': 'age' ,'sClass':'text-center'},
					{ 'data': 'nation' ,'sClass':'text-center'},
					{ 'data': 'politicalstatus' ,'sClass':'text-center'},
					{ 'data': 'usedname' ,'sClass':'text-center'},
					{ 'data': 'residenceaddress' ,'sClass':'text-center'},
					{ 'data': 'residentialaddress' ,'sClass':'text-center'},
					{ 'data': 'education' ,'sClass':'text-center'},
					{ 'data': 'professionstatus' ,'sClass':'text-center'},
					{ 'data': 'professiontype' ,'sClass':'text-center'},
					{ 'data': 'workunit' ,'sClass':'text-center'},
					{ 'data': 'job' ,'sClass':'text-center'},
					{ 'data': 'issocialsecurity' ,'sClass':'text-center'},
					{ 'data': 'email' ,'sClass':'text-center'},
					{ 'data': 'tel' ,'sClass':'text-center'},
					{ 'data': 'fax' ,'sClass':'text-center'},
					{ 'data': 'mobile' ,'sClass':'text-center'},
					{ 'data': 'eligiousbelief' ,'sClass':'text-center'},
					{ 'data': 'marriage' ,'sClass':'text-center'},
					{ 'data': 'isforeignmarriage' ,'sClass':'text-center'},
					{ 'data': 'health' ,'sClass':'text-center'},
					{ 'data': 'military' ,'sClass':'text-center'},
					{ 'data': 'stature' ,'sClass':'text-center'},
					{ 'data': 'blood' ,'sClass':'text-center'},
					{ 'data': 'hobby' ,'sClass':'text-center'},
					{ 'data': 'reasonnotresidence' ,'sClass':'text-center'},
					{ 'data': 'custodian' ,'sClass':'text-center'},
					{ 'data': 'custodiantel' ,'sClass':'text-center'},
					{ 'data': 'custodianrelationship' ,'sClass':'text-center'},
					{ 'data': 'custodianincommunity' ,'sClass':'text-center'},
					{ 'data': 'note' ,'sClass':'text-center'},
					{ 'data': 'dy_partymembertype' ,'sClass':'text-center'},
					{ 'data': 'dy_of_partyorganization' ,'sClass':'text-center'},
					{ 'data': 'dy_joinpartydate' ,'sClass':'text-center'},
					{ 'data': 'dy_inpartydate' ,'sClass':'text-center'},
					{ 'data': 'dy_membership' ,'sClass':'text-center'},
					{ 'data': 'dy_islost' ,'sClass':'text-center'},
					{ 'data': 'dy_lostdate' ,'sClass':'text-center'},
					{ 'data': 'dy_movemember' ,'sClass':'text-center'},
					{ 'data': 'dy_moveto' ,'sClass':'text-center'},
					{ 'data': 'zdr_type' ,'sClass':'text-center'},
					{ 'data': 'zdr_reason' ,'sClass':'text-center'},
					{ 'data': 'zdr_custodian' ,'sClass':'text-center'},
					{ 'data': 'zdr_custodiantel' ,'sClass':'text-center'},
					{ 'data': 'jzr_correctioncontent' ,'sClass':'text-center'},
					{ 'data': 'jzr_correctiondate' ,'sClass':'text-center'},
					{ 'data': 'jzr_correctionaddress' ,'sClass':'text-center'},
					{ 'data': 'sy_unemployedreason' ,'sClass':'text-center'},
					{ 'data': 'sy_unemployedreemployment' ,'sClass':'text-center'},
					{ 'data': 'sy_reemploymentunit' ,'sClass':'text-center'},
					{ 'data': 'ylfn_firstmarriagedate' ,'sClass':'text-center'},
					{ 'data': 'ylfn_children' ,'sClass':'text-center'},
					{ 'data': 'ylfn_bornchildren' ,'sClass':'text-center'},
					{ 'data': 'ylfn_pregnancy' ,'sClass':'text-center'},
					{ 'data': 'ylfn_lastmenstruation' ,'sClass':'text-center'},
					{ 'data': 'ylfn_terminationpregnancy' ,'sClass':'text-center'},
					{ 'data': 'lnr_oldid' ,'sClass':'text-center'},
					{ 'data': 'lnr_economicsources' ,'sClass':'text-center'},
					{ 'data': 'lnr_livingconditions' ,'sClass':'text-center'},
					{ 'data': 'lnr_selfcare' ,'sClass':'text-center'},
					{ 'data': 'lnr_specialsubsidies' ,'sClass':'text-center'},
					{ 'data': 'lnr_emergencycontact' ,'sClass':'text-center'},
					{ 'data': 'lnr_emergencycontactrelated' ,'sClass':'text-center'},
					{ 'data': 'lnr_emergencycontacttel' ,'sClass':'text-center'},
					{ 'data': 'lnr_physicalcondition' ,'sClass':'text-center'},
					{ 'data': 'lnr_medicationcondition' ,'sClass':'text-center'},
					{ 'data': 'cj_disabilitytype' ,'sClass':'text-center'},
					{ 'data': 'cj_disabilitylevel' ,'sClass':'text-center'},
					{ 'data': 'cj_disabilityreason' ,'sClass':'text-center'},
					{ 'data': 'cj_disabilityemployment' ,'sClass':'text-center'},
					{ 'data': 'jsb_type' ,'sClass':'text-center'},
					{ 'data': 'jsb_medicalhistory' ,'sClass':'text-center'},
					{ 'data': 'jhr_name' ,'sClass':'text-center'},
					{ 'data': 'hjr_tel' ,'sClass':'text-center'},
					{ 'data': 'zyz_certificate_id' ,'sClass':'text-center'},
					{ 'data': 'zyz_special_skill' ,'sClass':'text-center'},
					{ 'data': 'jmdb_representative_level' ,'sClass':'text-center'},
					{ 'data': 'jmdb_startofterm' ,'sClass':'text-center'},
					{ 'data': 'jmdb_endofterm' ,'sClass':'text-center'},
					{ 'data': 'wtgg_skill_type' ,'sClass':'text-center'},
					{ 'data': 'wtgg_special_skill' ,'sClass':'text-center'},
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
					targets:  98,//从0开始
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


