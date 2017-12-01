<%@page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<script type="text/javascript" src="${ctx}/static/back/js/ue1433/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/static/back/js/ue1433/ueditor.all.js"></script>
<div class="col-xs-12 padding-foot">
	<div class="white-content">
		<h1 class="page-title txt-color-blueDark">
			新建楼盘动态
		</h1>
		<form class="form-horizontal" id="houseNewsForm">
		    <div class="row">
		        <div class="col-xs-8 col-xs-offset-2" style="margin-left: 5.666667%;">
		        	<div class="form-group">
		                <label class="col-sm-2 control-label">楼盘名称*</label>
		                <div class="col-sm-6">
		                  	<select id="dicSelectId"  class="form-control dsj-inline js-example-basic-multiple" onchange="setOther()" data-validate="required">
							 </select>
							 <input type="hidden" class="form-control" name="houseName" id="houseName">
		                </div>
					</div>
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">楼盘ID*</label>
		                <div class="col-sm-6">
		                  <input id="houseId"name="houseId"type="text" readonly="readonly" class="form-control"  data-validate="required">
		                </div>
		            </div>
					
					 <div class="form-group">
		                <label  class="col-sm-2 control-label">动态标题*</label>
		                <div class="col-sm-6">
		                  <input name="title"type="text" class="form-control" placeholder="20个字以内" data-validate="required,maxLength20">
		                </div>
		            </div> 
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">动态内容*</label>
		                <div class="col-sm-9">
			                <script name="content" id="saveEditor" type="text/plain"
								style="width: 800px; height: 600px;margin-left:0px;"></script>
							<div id="jqk" class="hide"><p style="color:#F00;">不能为空<p></div>
						</div>
						<input id="contentst" type="hidden" name="contentst" >
		            </div>
					<!-- 
		            <div class="form-group">
		                <label  class="col-sm-2 control-label">添加照片</label>
		                 <div class="col-sm-6">
			                 <img id="photoShowImg" style="width: 180px;height: 180px;"/>
			                 <div id="photoFilePicker" style="display: inline-block;"> 
			     				 <button class="btn btn-primary" type="button" >选择图片</button>            
			                 </div>
			                 <input name="pictureUrl" style="width: 0px;border: 0px;" type="text" id="photoFileUrl" >
		                 </div>
		            </div>
					-->
		             <div class="text-center">
	                     <button class="btn btn-primary" type="button" onclick="addHouseNews()">确认</button>
	                     <button class="btn btn-primary" type="button" onclick="yulan()" >预览</button>
	                     <button class="btn btn-default" type="button" onclick="cancel()">取消</button>
	                </div>
		        </div>
		    </div>
		</form>
	</div>
</div>
<script type="text/javascript">

var editor;
editor = new UE.ui.Editor();
editor.render("saveEditor");

var result = null;
//设置ID
function setOther(){
	var houseId = $("#dicSelectId").val();
	if(houseId!=null){
		console.log(houseId);
		for(var i in result){
			if(result[i].id==houseId){
				$("#houseId").val(result[i].id);
				$("#houseName").val(result[i].text)
			}
		}
	}
}

$(function(){
	$("#dicSelectId").select2({
			"ajax":{
			    url: _ctx+"/back/newHouse/edit/getDic",
			    data: function (params) {
			      var query = { //请求的参数, 关键字和搜索条件之类的
			        name: params.term //select搜索框里面的value
			      }
			      return query;
			    },
			    delay: 1000,
			    processResults: function (data, params) {
			      //返回的选项必须处理成以下格式
			     result = data.data;
			      return {
			        results: result  //必须赋值给results并且必须返回一个obj
			      };
			    }
			  }
	});
	
})
</script>

<script src="${ctx}/static/back/content/houseNews/houseNews_add.js"></script>
<script src="${ctx}/static/back/lib/webupload/webuploader.js"></script>
<script src="${ctx}/static/back/lib/webupload/single-upload-ex.js"></script>
<script src="${ctx}/static/front/js/loading-back.js"></script>