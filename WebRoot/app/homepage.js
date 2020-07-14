


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
	
	$('#xxqffwAnchor').click(function () {
		updateActiveMenu('xxqffwAnchor');
        loadXxqffwPage();
    });
	
	
	
	
	
	
	
	
	
	
	
	$('#smsAnchor').click(function () {
		updateActiveMenu('smsAnchor');
        loadSMSPage();
    });
	
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
	
	
	getCurrentLoginUserInfo();
	
	//loadTablePage();
	
	updateActiveMenu('sqzzqdAnchor');
    loadSqzzqdPage();
});	

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
		
        $(this).removeClass('active');
    });
	
    $('#'+activeObj).addClass('active');
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

function loadXxqffwPage()
{
	$('#main-content').load("./nfw/xxqffw.html", function () {
        
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


