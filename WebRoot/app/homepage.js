﻿


var curTableId;//在动态表单显示的时候有用

var curUserName;

var ip = '127.0.0.1';

var maintab = 'tabl_2', secondtab = null;

//地图相关
var ditutype = 0;
var curmap = '';


$(document).ready(function (){
	
	generateMenu();
	
	
	
	
	
	$('#passwordAnchor').click(function () {
        loadPasswordPage();
    });
	
	
	
	
	
	$('#dashboardAnchor').click(function () {
        loadDashboardPage();
    });
	
	$('#tableAnchor').click(function () {
		updateActiveMenu('tableAnchor');
        loadTablePage();
    });
	
	$('#userAnchor').click(function () {
        loadUserPage();
    });
	
	$('#menuAnchor').click(function () {
        loadMenuPage();
    });
	
	
	$('#chatAnchor').click(function () {
        loadChatPage();
    });
	
	
	
	
	
	$('#generatedAnchor').click(function () {
        loadGeneratedPage();
    });
	
	
	
	
	$('#sqzzqdAnchor').click(function () {
		updateActiveMenu('sqzzqdAnchor');
        loadSqzzqdPage();
    });
	
	$('#jcsqsjAnchor').click(function () {
		updateActiveMenu('jcsqsjAnchor');
        loadJcsqsjPage();
		maintab = 'tabl_2';
		secondtab = null;
    });
	
	$('#sxsqsjAnchor').click(function () {
		updateActiveMenu('sxsqsjAnchor');
        loadSxsqsjPage();
    });
	
	
	
	
	$('#sqgktAnchor').click(function () {
		updateActiveMenu('sqgktAnchor');
        loadSqgktPage();
    });
	
	$('#sqmqtAnchor').click(function () {
		updateActiveMenu('sqmqtAnchor');
        loadSqmqtPage();
    });
	
	$('#sqzttAnchor').click(function () {
		updateActiveMenu('sqzttAnchor');
        loadSqzttPage();
    });
	
	
	
	
	$('#zhfwAnchor').click(function () {
		updateActiveMenu('zhfwAnchor');
        loadZhfwPage();
    });
	
	
	$('#sqbsfwAnchor').click(function () {
		updateActiveMenu('sqbsfwAnchor');
        loadSqbsfwPage();
    });
	
	$('#zmblfwAnchor').click(function () {
		updateActiveMenu('zmblfwAnchor');
        loadZmblfwPage();
    });
	
	$('#myslfwAnchor').click(function () {
		updateActiveMenu('myslfwAnchor');
        loadMyslfwPage();
    });
	
	$('#wwzffwAnchor').click(function () {
		updateActiveMenu('wwzffwAnchor');
        loadWwzffwPage();
    });
	
	$('#xfslfwAnchor').click(function () {
		updateActiveMenu('xfslfwAnchor');
        loadXfslfwPage();
    });
	
	$('#jsjbfwAnchor').click(function () {
		updateActiveMenu('jsjbfwAnchor');
        loadJSJBFW();
    });
	
	$('#sqcsfwAnchor').click(function () {
		updateActiveMenu('sqcsfwAnchor');
        loadSqcsfwPage();
    });
	
	$('#znhffwAnchor').click(function () {
		updateActiveMenu('znhffwAnchor');
        loadZnhffwPage();
    });
	
	
	$('#homeAnchor').click(function () {
		$('.nav-link').each(function () {
		
			//$(this).removeClass('active');
			$(this).closest("li").removeClass('tj-fux');
		});
        window.location.href = "../app/homepage.html";
    });
	
	/*$('#xxqffwAnchor').click(function () {
		updateActiveMenu('xxqffwAnchor');
        loadXxqffwPage();
    });
	
	
	
	
	
	
	
	
	
	
	*/
	$('#dicAnchor').click(function () {
		updateActiveMenu('dicAnchor');
        loadDicPage();
    });
	
	$('#noticeAnchor').click(function () {
		updateActiveMenu('noticeAnchor');
        loadNoticePage();
    });
	
	$('#contactAnchor').click(function () {
		updateActiveMenu('contactAnchor');
        loadContactPage();
    });
	
	$('#nodeAnchor').click(function () {
		updateActiveMenu('nodeAnchor');
        loadNodePage();
    });
	
	$('#templateAnchor').click(function () {
		updateActiveMenu('templateAnchor');
        loadTemplatePage();
    });
	
	
	
	
	
	$('#smsAnchor').click(function () {
		updateActiveMenu('smsAnchor');
        loadSMSPage();
    });
	$('#telAnchor').click(function () {
		updateActiveMenu('telAnchor');
        loadTelPage();
    });
	$('#boardAnchor').click(function () {
		updateActiveMenu('boardAnchor');
        loadBoardPage();
    });
	$('#wechatAnchor').click(function () {
		updateActiveMenu('wechatAnchor');
        loadWechatPage();
    });
	
	
	$('#organizationAnchor').click(function () {
		updateActiveMenu('organizationAnchor');
        loadOrganizationPage();
    });
	$('#roleAnchor').click(function () {
		updateActiveMenu('roleAnchor');
        loadRolePage();
    });
	$('#userAnchor').click(function () {
		updateActiveMenu('userAnchor');
        loadUserPage();
    });
	
	
	
	
	$('#homeChartAnchor').click(function () {
        loadHomeChartPage();
    });
	
	
	
	
	getCurrentLoginUserInfo();
	
	//loadTablePage();
	
	//updateActiveMenu('sqzzqdAnchor');
    //loadSqzzqdPage();
	
	getsqname();
	
	//加载数据
	loaddata();
	
	setInterval(checkSessionStatus,10000);
});	

function loaddata(){
	
	loadownerinfo();
	
	loadbaseinfo1();
	
	loadhomeinfo();
	
	loadminqininfo();
	
	loadsxsq();
	
	loadinfodata();
	
	loadbaseinfo2();
	
	loadbaseinfo3();
	
	loadbaseinfo4();
	
	loadbaseinfo5();
	
	loadbaseinfo6();
	
	loadbaseinfo7();
	
	loadsqbs();
	
	getgpyip();
}

function loadPasswordPage()
{
	$('#main-content').load("./changePassword.html", function () {
        
    });
}

function loadHomeChartPage()
{
	$('#main-content').load("./homechart.html", function () {
        
    });
}

function loadownerinfo(){
	$.get(getContextPath()+'/homeController/getownerinfo',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				console.log(obj);
				if(obj.ownernum > 1){
					for(var i = 0; i < obj.data.length; i++){
						var opt = '<option value="' + obj.data[i].id + '">' + obj.data[i].name + '</option>';
						$("#ownerlist").append(opt);
					}
					$("#ownerli").show();
					$("#ownerlist").val(obj.selectowner);
				}
				else {
					$("#ownerli").hide();
				}
			}
		});
}

function selectowner(){
	var owner = $("#ownerlist").val();
	
	$.post(getContextPath()+"/homeController/selectowner",
			{
				owner:owner
			},
			function(result){
				var obj = jQuery.parseJSON(result);  
				if(obj.success)
				{
					
					window.location.reload();
				}
				else
				{
					
				}
			});
}

function loadbaseinfo1(){
	$.get(getContextPath()+'/homeController/loadbaseinfo1',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$("#b11").html(obj.data[0]);
				$("#b12").html(obj.data[1]);
				$("#b13").html(obj.data[2]);
				$("#b14").html(obj.data[3]);
				$("#b15").html(obj.data[4]);
				$("#b16").html(obj.data[5]);
				$("#b17").html(obj.data[6]);
			}
		});	
}

function getsqname(){
	$.get(getContextPath()+'/homeController/getsqname',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$("#sqname").html("(" + obj.data + ")");
				$("#sqtitle").html(obj.data);
				$("#username").html(obj.username);
			}
		});	
}

function getgpyip(){
	$.get(getContextPath()+'/homeController/getgpyip',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				ip = obj.ip;
			}
		});	
}

function loadbaseinfo2(){
	$.get(getContextPath()+'/homeController/loadbaseinfo2',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$("#b21").html(obj.data[0]);
				$("#b22").html(obj.data[1]);
				$("#b23").html(obj.data[2]);
				$("#b24").html(obj.data[3]);
				$("#b25").html(obj.data[4]);
				$("#b26").html(obj.data[5]);
				$("#b27").html(obj.data[6]);
				$("#b28").html(obj.data[7]);
			}
		});	
}

function loadbaseinfo3(){
	$.get(getContextPath()+'/homeController/loadbaseinfo3',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$("#b31").html(obj.data[0]);
				$("#b32").html(obj.data[1]);
				$("#b33").html(obj.data[2]);
				$("#b34").html(obj.data[3]);
				$("#b35").html(obj.data[4]);
				$("#b36").html(obj.data[5]);
				$("#b37").html(obj.data[6]);
				$("#b38").html(obj.data[7]);
				$("#b39").html(obj.data[8]);
			}
		});	
}

function loadbaseinfo4(){
	$.get(getContextPath()+'/homeController/loadbaseinfo4',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			//console.log(obj);
			if(obj.success)
			{
				$("#b41").html(obj.data[0]);
				$("#b42").html(obj.data[1]);
				$("#b43").html(obj.data[2]);
				$("#b44").html(obj.data[3]);
				$("#b45").html(obj.data[4]);
				$("#b46").html(obj.data[5]);
				$("#b47").html(obj.data[6]);
				$("#b48").html(obj.data[7]);
				$("#b49").html(obj.data[8]);
				$("#b410").html(obj.data[9]);
				$("#b411").html(obj.data[10]);
				$("#b412").html(obj.data[11]);
				$("#b413").html(obj.data[12]);
				$("#b414").html(obj.data[13]);
			}
		});	
}

function loadbaseinfo5(){
	$.get(getContextPath()+'/homeController/loadbaseinfo5',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			//console.log(obj);
			if(obj.success)
			{
				$("#b51").html(obj.data[0]);
				$("#b52").html(obj.data[1]);
				$("#b53").html(obj.data[2]);
				$("#b54").html(obj.data[3]);
			}
		});	
}

function loadbaseinfo6(){
	$.get(getContextPath()+'/homeController/loadbaseinfo6',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			//console.log(obj);
			if(obj.success)
			{
				$("#b61").html(obj.data[0]);
				$("#b62").html(obj.data[1]);
				$("#b63").html(obj.data[2]);
				$("#b64").html(obj.data[3]);
				$("#b65").html(obj.data[4]);
				$("#b66").html(obj.data[5]);
			}
		});	
}

function loadbaseinfo7(){
	$.get(getContextPath()+'/homeController/loadbaseinfo7',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			//console.log(obj);
			if(obj.success)
			{
				$("#b71").html(obj.data[0]);
				$("#b72").html(obj.data[1]);
				$("#b73").html(obj.data[2]);
				$("#b74").html(obj.data[3]);
				$("#b75").html(obj.data[4]);
				$("#b76").html(obj.data[5]);
				$("#b77").html(obj.data[6]);
				$("#b78").html(obj.data[7]);
			}
		});	
}

function loadhomeinfo(){
	$.get(getContextPath()+'/homeController/loadhomeinfo',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			//console.log(obj);
			if(obj.success)
			{
				$("#h1").html(obj.data[0]);
				$("#h2").html(obj.data[1]);
				$("#h3").html(obj.data[2]);
				$("#h4").html(obj.data[3]);
				$("#h5").html(obj.data[4]);
			}
		});	
}

function loadminqininfo(){
	$.get(getContextPath()+'/homeController/loadminqininfo',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				$("#d1").html(obj.data[0]);
				$("#d2").html(obj.data[1]);
				$("#d3").html(obj.data[2]);
				$("#d4").html(obj.data[3]);
				$("#d5").html(obj.data[4]);
				$("#d6").html(obj.data[5]);
				$("#d7").html(obj.data[6]);
				$("#d8").html(obj.data[7]);
				$("#d9").html(obj.data[8]);
				$("#d10").html(obj.data[9]);
				$("#d11").html(obj.data[10]);
				$("#d12").html(obj.data[11]);
				$("#d13").html(obj.data[12]);
				$("#d14").html(obj.data[13]);
				$("#d15").html(obj.data[14]);
				$("#d16").html(obj.data[15]);
				$("#d17").html(obj.data[16]);
				$("#d18").html(obj.data[17]);
				$("#d19").html(obj.data[18]);
				$("#d20").html(obj.data[19]);
				$("#d21").html(obj.data[20]);
				$("#d22").html(obj.data[21]);
				$("#d23").html(obj.data[22]);
				$("#d24").html(obj.data[23]);
				$("#d25").html(obj.data[24]);
			}
		});	
}

function loadsxsq(){
	$.get(getContextPath()+'/homeController/loadsxsq',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			//console.log(obj);
			if(obj.success)
			{
				$("#s1").html(obj.data[0]);
				$("#s2").html(obj.data[1]);
				$("#s3").html(obj.data[2]);
				$("#s4").html(obj.data[3]);
				$("#s5").html(obj.data[4]);
				$("#s6").html(obj.data[5]);
				
				myChart.setOption({
					series: [{
						// 根据名字对应到相应的系列
						name: '数量',
						data: obj.data
					}]
				});
				
				
			}
		});	
}

function loadsqbs(){
	$.get(getContextPath()+'/homeController/loadsqbs',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			//console.log(obj);
			if(obj.success)
			{
				$("#bs1").html(obj.data[0]);
				$("#bs2").html(obj.data[1]);
				$("#bs3").html(obj.data[2]);
				$("#bs4").html(obj.data[3]);
				$("#bs5").html(obj.data[4]);
				$("#bs6").html(obj.data[5]);
				$("#bs7").html(obj.data[6]);
				$("#bs8").html(obj.data[7]);
				
				/*
				var arr = new Array();
				for(var i = 0; i < 7; i++){
					arr.push(obj.data[0]);
				}
				*/
				myChart2.setOption({
					series: [{
						// 根据名字对应到相应的系列
						name: '数量',
						type: 'bar',
						data: obj.data
					}]
				});
			}
		});	
}

function loadinfodata(){
	$.get(getContextPath()+'/homeController/loadinfodata',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			//console.log(obj);
			if(obj.success)
			{
				$("#i1").html(obj.data[0]);
				$("#i2").html(obj.data[1]);
				$("#i3").html(obj.data[2]);
				$("#i4").html(obj.data[3]);
				$("#i5").html(obj.data[4]);
				$("#i6").html(obj.data[5]);
				$("#i7").html(obj.data[6]);
				$("#i8").html(obj.data[7]);
				$("#i9").html(obj.data[8]);
				$("#i10").html(obj.data[9]);
				$("#i11").html(obj.data[10]);
				$("#i12").html(obj.data[11]);
				$("#i13").html(obj.data[12]);
				$("#i14").html(obj.data[13]);
				$("#i15").html(obj.data[14]);
				$("#i16").html(obj.data[15]);
				$("#i17").html(obj.data[16]);
				$("#i18").html(obj.data[17]);
				$("#i19").html(obj.data[18]);
				$("#i20").html(obj.data[19]);
				$("#i21").html(obj.data[20]);
				$("#i22").html(obj.data[21]);
				$("#i23").html(obj.data[22]);
				$("#i24").html(obj.data[23]);
				$("#i25").html(obj.data[24]);
				$("#i26").html(obj.data[25]);
				$("#i27").html(obj.data[26]);
			}
		});	
}


function generateMenu()
{
	$.get(getContextPath()+'/menuController/load',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			if(obj.success)
			{
				for(var i=0;i<obj.list.length;i++)
				{
					console.log(obj.list[i].menu_zhname);
					
					var menu = '<li>' +
						  '<a id="'+obj.list[i].menu_enname+'" onclick="loadDynamicPage(\''+obj.list[i].menu_type+'\',\''+obj.list[i].table_id+'\',\''+obj.list[i].file_name+'\',\''+obj.list[i].external_url+'\')" href="#">' +
						  '    <i class="icon-book"></i>' +
						  '    <span>'+obj.list[i].menu_zhname+'</span>' +
						  '</a>' +
					  '</li>';
					
					$("#nav-accordion").append(menu);
				}
			}
		});	
}

function loadDynamicPage(menu_type,table_id,file_name,external_url)
{
	curTableId = table_id;
	
	$('#main-content').load("./table/tableData.html", function () {
        
    });
	
}




function updateActiveMenu(activeObj) {
    $('.nav-link').each(function () {
		
		//$(this).removeClass('active');
        $(this).closest("li").removeClass('tj-fux');
    });
	
	//$('#'+activeObj).parents("li").addClass('active');
    $('#'+activeObj).closest("li").addClass('tj-fux');
}

function loadDashboardPage()
{
	$('#main-content').load("./dashboard/dashboard.html", function () {
        
    });
}

function loadTablePage()
{
	$('#main-content').load("./table/table.html", function () {
        
    });
}

function loadUserPage()
{
	$('#main-content').load("./user/user.html", function () {
        
    });
}

function loadChatPage()
{
	$('#main-content').load("./chat/chat.html", function () {
        
    });
}

function loadGeneratedPage()
{
	$('#main-content').load("./sysEquipment/sysEquipment.html", function () {
        
    });
}

function loadMenuPage()
{
	$('#main-content').load("./menu/menu.html", function () {
        
    });
}



function loadSqzzqdPage()
{
	$('#main-content').load("./sqzzqd/sqzzqd.html", function () {
        
    });
}

function loadJcsqsjPage()
{
	$('#main-content').load("./jcsqsj/jcsqsj.html", function () {
        
    });
}


function loadSxsqsjPage()
{
	$('#main-content').load("./sxsqsj/sxsqsj.html", function () {
        
    });
}


function loadSqgktPage()
{
	$('#main-content').load("./maps/sqgkt.html", function () {
        
    });
}

function loadSqmqtPage()
{
	$('#main-content').load("./maps/sqmqt.html", function () {
        
    });
}

function loadSqzttPage()
{
	$('#main-content').load("./maps/sqztt.html", function () {
        
    });
}





function loadZhfwPage()
{
	$('#main-content').load("./nfw/zhfw.html", function () {
        
    });
}

function loadSqbsfwPage()
{
	$('#main-content').load("./nfw/sqbsfw.html", function () {
        
    });
}


function loadZnhffwPage()
{
	$('#main-content').load("./nfw/znhffw.html", function () {
        
    });
}


function loadSqcsfwPage()
{
	$('#main-content').load("./nfw/sqcsfw.html", function () {
        
    });
}


function loadXfslfwPage()
{
	$('#main-content').load("./nfw/xfslfw.html", function () {
        
    });
}


function loadWwzffwPage()
{
	$('#main-content').load("./nfw/wwzffw.html", function () {
        
    });
}


function loadMyslfwPage()
{
	$('#main-content').load("./nfw/myslfw.html", function () {
        
    });
}


function loadZmblfwPage()
{
	$('#main-content').load("./nfw/zmblfw.html", function () {
        
    });
}


function loadSqbsfwPage()
{
	$('#main-content').load("./nfw/sqbsfw.html", function () {
        
    });
}

function loadJSJBFW()
{
	$('#main-content').load("./nfw/jsjbfw.html", function () {
        
    });
}


function loadDicPage()
{
	$('#main-content').load("./dictionary/dictionary.html", function () {
        
    });
}

function loadSMSPage()
{
	$('#main-content').load("./sms/sms.html", function () {
        
    });
}


function loadTelPage()
{
	$('#main-content').load("./sys/sys_tel_publish/sys_tel_publish.html", function () {
        
    });
}

function loadWechatPage()
{
	$('#main-content').load("./sys/sys_wechat_notice/sys_wechat_notice.html", function () {
        
    });
}

function loadBoardPage()
{
	$('#main-content').load("./sys/sys_board_publish/sys_board_publish.html", function () {
        
    });
}




function loadNoticePage()
{
	$('#main-content').load("./oa/notice/notice.html", function () {
        
    });
}

function loadContactPage()
{
	$('#main-content').load("./oa/contact/contact.html", function () {
        
    });
}

function loadNodePage()
{
	$('#main-content').load("./flow/node.html", function () {
        
    });
}

function loadTemplatePage()
{
	$('#main-content').load("./flow/template.html", function () {
        
    });
}


function loadOrganizationPage()
{
	$('#main-content').load("./sys/sys_organization/sys_organization.html", function () {
        
    });
}


function loadRolePage()
{
	$('#main-content').load("./sys/sys_role/sys_role.html", function () {
        
    });
}


function loadUserPage()
{
	$('#main-content').load("./sys/sys_user/sys_user.html", function () {
        
    });
}

function getCurrentLoginUserInfo()
{
	$.get(getContextPath()+"/loginController/getCurrentLoginUserInfo",
		function(result){
			var obj = jQuery.parseJSON(result);  
			
			curUserName = obj.userName;
			
			if(obj.userId != 'admin')
				$('#sysMenu').hide();
			
			curUserOrgId = obj.userOrg;
			
			curUserId = obj.userId;
			
			curUserType = obj.userType;
			
			for(var i=0;obj.rightArr != null && i<obj.rightArr.length;i++)
			{
				var rightObj = obj.rightArr[i];
				
				if(rightObj.righttype == 'MENU')
				{
					curUserMenuRightArr[curUserMenuRightArr.length] = rightObj.rightid;
					
					$("#"+rightObj.rightid).show();
				}
				else
				{
					curUserOptRightArr[curUserOptRightArr.length] = rightObj.rightid;
				}
			}
			
			/*if(obj.userType != USER_TYPE_WEB_CUSTOMER)
			{
				//$('#DataStatAnchor').hide();
			}
			$('#usernameSpan').text(obj.userName);*/
	});
}

function logout()
{
	
}

function checkSessionStatus()
{
	$.get(getContextPath()+"/loginController/checkSessionStatus",
		function(result){
			var obj = jQuery.parseJSON(result);  
			
			if(obj.status == false)
			{
				window.open('../index.html','_self');
			}
	});
	
}

