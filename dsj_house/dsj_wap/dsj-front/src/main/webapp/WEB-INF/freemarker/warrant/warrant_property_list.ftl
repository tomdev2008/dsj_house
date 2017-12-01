	<#include "common/taglibs.ftl">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse.css">
	<link rel="stylesheet" href="${ctx }/static/front/css/dsj_newhouse_list.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/warrant.css">

	    <div class="BHLogo">
	      <div class="BHLogoLeft">
	       <h1>大搜家
	         	<a  href="#dsj"  onclick="javascript:window.location.href='${ctx }/'"></a>
	         </h1>
	        <span>自交易</span>
	      </div>
	      <div class="BHLogoRight">
	        <!-- 输入框搜索按钮 -->
	          <div class="TextSeek">
	            
	          </div>
	      </div>
	    </div>
	  <!-- 进程 -->
	    <div class="PGress"> 
	      <ul>
	        <li><a href="${ctx }/">大搜家首页</a>
	        <div class="progressTriangle">
	          <div> 
	          </div>
	        </div>
	        </li>
	         <li><a href="${ctx }/front/warrant/list">自交易列表</a>
	        <div class="progressTriangle">
	          <div> 
	          </div>
	        </div>
	        </li>
	         <li><a href="javascript:history.back(-1)">自交易详情</a>
	        <div class="progressTriangle">
	          <div> 
	          </div>
	        </div>
	        </li>
	        <li><a href="javascript:void(0)">选择服务人员</a>
	        </li>
	      </ul>
	    </div>
	  <!-- 前端主体 -->
	  	<div class="dsj-main warrant_cen">
	  		<!-- 表单选项 -->
	  		<form id="dsj_form" class="dsj_form" onsubmit="return false">
	            <div class="dsj_form_line dsj_sort">
	            	<span>排序:</span>
	            	<span onclick="setOrder(0,this)"><span class="active">默认排序</span></span>
	            	<span onclick="setOrder(1,this)">好评率由高到低<span class="dsj_arrow glyphicon glyphicon-arrow-up" aria-hidden="true"></span></span>
	            	<span onclick="setOrder(2,this)">接单数由高到低<span class="dsj_arrow glyphicon glyphicon-arrow-down" aria-hidden="true"></span></span>
	            	<input type="hidden" id="orderType" name="orderType" >
	            	<input type="hidden" id="skuId"  value="${skuId }">
	            	<input type="hidden" id="spuId"  name=business  value="${spuId }">
	  				<p class="pull-right pull-down-kuang">
	  					<span class="no_icon">服务区域：</span>
	  					<span class="form-control form_control_box">北京</span>
						<select class="form-control" onchange="reloadSearch()" id="areaCodeThree" name="area">
							  <#if threeAreaList??> 
						  		<#list threeAreaList as area> 
						  		    	 <option  <#if areaCodeThree==area.areaCode>selected='selected'</#if>  value="${area.areaCode}">${area.name}</option>
						  		    	 
						  		</#list>
						    </#if>
						</select>
	  				</p>
	            </div>
	  		</form>
	        <!-- 主体内容 -->
	      	<div class="newhouse_content" id="content_containers">
	      		
			</div>	
		</div>	
	 <script id="dsj_row_template" type="text/x-handlebars-template">
      			<div class="servant_list">
					<div class="list_img">
						<img style="cursor:pointer;" onclick="toFwUserDetail({{id}})" src="{{avatarReUrl}}?x-oss-process=image/resize,m_fixed,h_132,w_98" height="132" width="98">
						<div style="cursor:pointer;" class="online-consult"  onclick="openChatWindow('{{userName }}', '{{realName }}', '{{tellPhone }}', '{{avatarReUrl }}')">
							在线咨询
						</div>
					</div>
					<div class="list_details">
						<div class="d_name">
							<h3 onclick="toFwUserDetail({{id}})">{{realName }}</h3>
							<p>好评率：<strong>{{#compare haoPingLv "!=" null}}{{haoPingLv}}{{else}}0%{{/compare}}</strong></p>
							<p>接单数：<strong>{{orderCount}}</strong></p>
						</div>
						<div class="d_describe">
							<p>服务区域：{{areaName}}</p>
							<p>所在公司：{{companyName}}</p>
							<p>主营业务：{{businessName}}</p>
						</div>
					</div>
					<div class="list_side">
						<p>{{tellPhone}}</p>
						<div style="cursor:pointer;" class="selectTA" onclick="orderComfirm({{id}})" >选TA为我服务</div>
					</div>
	      		</div>
    </script>
	<script id="dsj_page_template" type="text/x-handlebars-template">
      <div class="row dsj_page_block" {{#compare totalPage "<=" 1}}style="display:none;"{{/compare}}>
        <div></div>
        <div class="col-sm-9">
          <nav aria-label="Page navigation">
            <ul class="pagination">
              <li>
                <a class="first" href="#" aria-label="first">
                  <span aria-hidden="true">首页</span>
                </a>
              </li>
              <li>
                <a class="prev" href="#" aria-label="Previous">
                  <span aria-hidden="true">上一页</span>
                </a>
              </li>
              {{#each list}}
              <li><a class="page_button" href="#">{{this}}</a></li>
              {{/each}}
              <li>
                <a class="next" href="#" aria-label="Next">
                  <span aria-hidden="true">下一页</span>
                </a>
              </li>
              <li>
                <a class="last" href="#" aria-label="last">
                  <span aria-hidden="true">尾页</span>
                </a>
              </li>
            </ul>
          </nav>
        </div>
        <div class="col-sm-1">
            <span class="">共<span>{{totalPage}}</span>页</span>
        </div>
       </div>
      </div>
    </script>
		 <script src="${ctx }/static/front/js/handlebars.js"></script>
		<script src="${ctx }/static/front/js/handlebars-utils.js"></script>
		<script src="${ctx }/static/front/js/housepage.js"></script>
		<script src="${ctx }/static/front/js/formUtils.js"></script>
		<script src="${ctx }/static/back/warrant/warrant_property_list.js"></script>
		  <script type="text/javascript">
		  var houseList;
		    $(document).ready(function(){
	  		 var houseOption = {
  	              container_id : "content_containers",
  	              rowTemplate_id : "dsj_row_template",
  	              pageTemplate_id : "dsj_page_template",
  	              pagecount : "30",
  	              url:_ctx+"/front/warrant/fwUserList"
  	            }
  	            houseList = new $.fn.houseList(houseOption);
  	            houseList._init();
	         	
		    })
		   		 function reloadSearch() {
              		houseList.searchAjax();
              	}
	         	 function setOrder(type,_this){
 		    		$("#orderType").val(type);
 		    		$("span").removeClass("active");
	    			$(_this).find("span").addClass("active");
 		    		reloadSearch();
	 		    }
		    </script>