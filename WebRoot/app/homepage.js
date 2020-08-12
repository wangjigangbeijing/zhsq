


var curTableId;//在动态表单显示的时候有用

var curUserName;


$(document).ready(function (){
	
	generateMenu();
	
	
	
	
	
	
	
	
	
	
	
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
	
	
	
	getCurrentLoginUserInfo();
	
	//loadTablePage();
	
	//updateActiveMenu('sqzzqdAnchor');
    //loadSqzzqdPage();
	
	//加载数据
	loaddata();
	
});	

function loaddata(){
	
	loadbaseinfo1();
	
	loadminqininfo();
	
	loadsxsq();
	
	//loadinfodata();
	
	loadbaseinfo2();
	
	loadbaseinfo3();
	
	loadbaseinfo4();
	
	loadbaseinfo5();
	
	loadbaseinfo6();
	
	//loadsqbs();
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
			console.log(obj);
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
			console.log(obj);
			if(obj.success)
			{
				$("#b51").html(obj.data[0]);
				$("#b52").html(obj.data[1]);
			}
		});	
}

function loadbaseinfo6(){
	$.get(getContextPath()+'/homeController/loadbaseinfo6',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			console.log(obj);
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
			console.log(obj);
			if(obj.success)
			{
				$("#s1").html(obj.data[0]);
				$("#s2").html(obj.data[1]);
				$("#s3").html(obj.data[2]);
				$("#s4").html(obj.data[3]);
				$("#s5").html(obj.data[4]);
				$("#s6").html(obj.data[5]);
			}
		});	
}

function loadsqbs(){
	$.get(getContextPath()+'/homeController/loadsqbs',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			console.log(obj);
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
			}
		});	
}

function loadinfodata(){
	$.get(getContextPath()+'/homeController/loadinfodata',
		function(result){
		
			var obj = jQuery.parseJSON(result);  
			console.log(obj);
			if(obj.success)
			{
				$("#bi1").html(obj.data[0]);
				$("#bi2").html(obj.data[1]);
				$("#bi3").html(obj.data[2]);
				$("#bi4").html(obj.data[3]);
				$("#bi5").html(obj.data[4]);
				$("#bi6").html(obj.data[5]);
				$("#bi7").html(obj.data[6]);
				$("#bi8").html(obj.data[7]);
				$("#bi9").html(obj.data[8]);
				$("#bi10").html(obj.data[9]);
				$("#bi11").html(obj.data[10]);
				$("#bi12").html(obj.data[11]);
				$("#bi13").html(obj.data[12]);
				$("#bi14").html(obj.data[13]);
				$("#bi15").html(obj.data[14]);
				$("#bi16").html(obj.data[15]);
				$("#bi17").html(obj.data[16]);
				$("#bi18").html(obj.data[17]);
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

/*
var page = 'list';

var PAGE_LIST = 'list';
var PAGE_QUERY = 'query';
var PAGE_DISTRIBUTE = 'distribute';
var PAGE_EXECUTE = 'execute';
function loadSsjsjListPage()
{
	page = PAGE_LIST;
	loadSsjsjPage();
}


function loadDistributePage()
{
	page = PAGE_DISTRIBUTE;
	loadSsjsjPage();
}


function loadExecutePage()
{
	page = PAGE_EXECUTE;
	loadSsjsjPage();
}

function loadQueryPage()
{
	page = PAGE_QUERY;
	loadSsjsjPage();
}

function loadSsjsjPage()
{
	$('#main-content').load("./ssjsj/ssjsj.html", function () {
        
    });
	
}
*/
function getCurrentLoginUserInfo()
{
	$.get(getContextPath()+"/loginController/getCurrentLoginUserInfo",
		function(result){
			var obj = jQuery.parseJSON(result);  
			
			curUserName = obj.userName;
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
