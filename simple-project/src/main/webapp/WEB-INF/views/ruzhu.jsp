<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="入住学校管理" />
	<meta name="author" content="" />
	
	<title>入住学校管理</title>

	<script src="${projectpath}/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="${projectpath}/js/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${projectpath}/js/ueditor/ueditor.all.js"></script>
	<script type="text/javascript" charset="utf-8" src="${projectpath}/js/ueditor/lang/zh-cn/zh-cn.js"></script>
	
</head>
<body class="page-body">
	<div class="page-container">
		
		<!-- main content start -->
		<div class="main-content">
			<!-- top bar start -->
			<nav class="navbar user-info-navbar" role="navigation">
				<!-- Left links for user info navbar -->
				<ul class="user-info-menu left-links list-inline list-unstyled">
					<li>
						<ol class="breadcrumb panel-title" style="margin-top:24px;">
							<li class="active">
								<strong>入住学校管理</strong>
							</li>
						</ol>
					</li>
				</ul>
				
			</nav>
			<!-- top bar end -->
			
			<!-- main section -->
			<div class="panel panel-default ">			
				<div class="tab-content">
					<!-- 商品信息 -->
					<div class="tab-pane active" style="width:90%" >
						<div class="panel panel-default">
							<div class="panel-body">
								<form role="form" action="${projectpath}/ruzhuschool/doUpdate" id="addTemplateForm" method="post"  class="validate form-horizontal">
									<div class="form-group">
										<label class="col-sm-2 control-label" style="margin-left: -105px;">内容:</label>
										<div class="panel-body">
											<script id="editor" type="text/plain" style="width: 100%; height: 350px;">
													${content }
											</script>
										</div>
									</div>
									<div class="form-group-separator"></div>
									<div class="text-center">
										<input type="button" onclick="updateContent();javascript:{this.disabled=true;}" class="btn btn-success" value="提交">
										<input type="button" style="display:none" class="btn btn-success" value="提交">
									</div>
 									
								</form>
							</div>
						</div>
						</div>					
				</div>
			</div>				
		</div>
		<!-- main content end -->
		
	</div>
	

	<!-- Bottom Scripts -->
	
	<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
	<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
	
	<script type="text/javascript">
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE.getEditor('editor');
	UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
	UE.Editor.prototype.getActionUrl = function(action) {
	    if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadimage') {
	        return '${projectpath}/image/ueditorUpload';
	    } else if (action == 'uploadvideo') {
	        return 'http://a.b.com/video.php';
	    } else {
	        return this._bkGetActionUrl.call(this, action);
	    }
	}
	
	function isFocus(e) {
		alert(UE.getEditor('editor').isFocus());
		UE.dom.domUtils.preventDefault(e)
	}
	function setblur(e) {
		UE.getEditor('editor').blur();
		UE.dom.domUtils.preventDefault(e)
	}
	function insertHtml() {
		var value = prompt('插入html代码', '');
		UE.getEditor('editor').execCommand('insertHtml', value)
	}
	function createEditor() {
		enableBtn();
		UE.getEditor('editor');
	}
	function getAllHtml() {
		alert(UE.getEditor('editor').getAllHtml())
	}
	function getContent() {
		var arr = [];
		arr.push("使用editor.getContent()方法可以获得编辑器的内容");
		arr.push("内容为：");
		arr.push(UE.getEditor('editor').getContent());
		alert(arr.join("\n"));
	}
	function getPlainTxt() {
		var arr = [];
		arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
		arr.push("内容为：");
		arr.push(UE.getEditor('editor').getPlainTxt());
		alert(arr.join('\n'))
	}
	function setContent(isAppendTo) {
		var arr = [];
		arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
		UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
		alert(arr.join("\n"));
	}
	function setDisabled() {
		UE.getEditor('editor').setDisabled('fullscreen');
		disableBtn("enable");
	}

	function setEnabled() {
		UE.getEditor('editor').setEnabled();
		enableBtn();
	}

	function getText() {
		//当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
		var range = UE.getEditor('editor').selection.getRange();
		range.select();
		var txt = UE.getEditor('editor').selection.getText();
		alert(txt)
	}

	function getContentTxt() {
		var arr = [];
		arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
		arr.push("编辑器的纯文本内容为：");
		arr.push(UE.getEditor('editor').getContentTxt());
		alert(arr.join("\n"));
	}
	function hasContent() {
		var arr = [];
		arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
		arr.push("判断结果为：");
		arr.push(UE.getEditor('editor').hasContents());
		alert(arr.join("\n"));
	}
	function setFocus() {
		UE.getEditor('editor').focus();
	}
	function deleteEditor() {
		disableBtn();
		UE.getEditor('editor').destroy();
	}
	function disableBtn(str) {
		var div = document.getElementById('btns');
		var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
		for (var i = 0, btn; btn = btns[i++];) {
			if (btn.id == str) {
				UE.dom.domUtils.removeAttributes(btn, [ "disabled" ]);
			} else {
				btn.setAttribute("disabled", "true");
			}
		}
	}
	function enableBtn() {
		var div = document.getElementById('btns');
		var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
		for (var i = 0, btn; btn = btns[i++];) {
			UE.dom.domUtils.removeAttributes(btn, [ "disabled" ]);
		}
	}

	function getLocalData() {
		alert(UE.getEditor('editor').execCommand("getlocaldata"));
	}

	function clearLocalData() {
		UE.getEditor('editor').execCommand("clearlocaldata");
		alert("已清空草稿箱")
	}

	function gotolist() {
		$("#aback-list").click();
	}
	function wrapEppGroup() {
		var tt = $("#wrapEppGroup").attr({
			style : "display"
		});
		alert(tt);
	}
	
	function updateContent(){	
		if(checkEmpty()){
			$.ajax({
			    url :"${projectpath}/ruzhuschool/doUpdate",
		        data: { 
		        	id:'school',//√
		        	content:$.trim(UE.getEditor('editor').getContent())//获取内容 √
		        },
		        type : "post",
			    success : function(data) {
			    	if(data == "ok"){
			    		alert("更新成功");
			    		window.location.href="${projectpath}/ruzhuschool/detail";
			    	}else{
			    		alert(data);
			    	}
		     	}
		    });
		}
	}
	
	//非空判断
	function checkEmpty(){	
		var content = $.trim(UE.getEditor('editor').getContent());
		if(content==null || ""==content){
			alert('请维护内容'); 
	 		return false;
		}
		return true;
	}
	</script>
</body>
</html>
