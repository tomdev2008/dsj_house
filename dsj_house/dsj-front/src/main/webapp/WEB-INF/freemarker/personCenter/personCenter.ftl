    <#include "common/taglibs.ftl">
    <link rel="stylesheet" href="${ctx }/static/front/css/centre.css">
    <link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/UploadId.css">
    <link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/crop.css">
    <link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/dsj_agent.css">
    <div id="dsj" class="pc-entrust">
      <!-- 经纪人前端logo搜索框 -->
        <div class="BHLogo">
          <div class="BHLogoLeft">
           <h1>大搜家
	         	<a href="#dsj"  onclick="javascript:window.location.href='${ctx }/'"></a>
	         </h1>
	         <span>个人中心</span>
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
            <li>个人中心
            </li>
          </ul>
        </div>
      <!-- pc主体 -->
        <div class="centre_main">
           <div class="centre_lefttab">
                <div>
                    <#if user.avatar>
                    	<img id="avatar" src="${user.avatar}?x-oss-process=image/resize,m_lfit,h_70,w_70" >
                    <#else>
                    	<img id="avatar" src="${ctx }/static/front/img/z44@3x.png" > 
                    </#if>
                    <p id="name"><a class="deli_a" onclick="modifyName()">${user.username}</a></p>
                </div>
                <ul>
                    <li id="order" onclick="myReplace('myOrder-head','order',0,1,1)" class="centre_leftActive" style="cursor:pointer;"><a>我的订单</a></li>
                    <li id="entrust" onclick="myReplace('myEntrust-sell','entrust',1,1,1)" style="cursor:pointer;"><a>业主委托</a></li>
                    <li id="follow" onclick="myReplace('myFollow-newHouse','follow',1,1,1)" style="cursor:pointer;"><a>我的关注</a></li>
                    <li onclick="myReplace('myComment','comment',1,1)" style="cursor:pointer;"><a>我的点评</a></li>
                    <li id="lookHistory" onclick="myReplace('lookHistory-newHouse','lookHistory',1,1,1)" style="cursor:pointer;"><a>浏览历史</a></li>
                    <li class="editInfo" onclick="replace('infomation-avatar')" style="cursor:pointer;"><a>编辑资料</a></li>
                </ul>
           </div>
           <div class="centre_content" id="head_content">
   
           </div>
        </div>
	<input type="hidden" value="${pay}" id="payId" />
    <script>
        // lefttab切换
        var lefttabs = $(".centre_lefttab ul").find("li"),
            contentdivs = $(".centre_content_div ul").find("li");
        // 遍历赋值index
        for(var i=0;i<lefttabs.length;i++){
            lefttabs[i].index=i;
        }
        for(var i=0;i<contentdivs.length;i++){
            contentdivs[i].index=i;
        }
        // lefttab事件委托
       $(".centre_lefttab ul").delegate("li","click",function(e){
           $(this).addClass("centre_leftActive").siblings().removeClass("centre_leftActive");
        }); 

        
    </script>
    
    
    <script id="myComment" type="text/x-handlebars-template">
        <!--我的点评  -->
        <div class="centre_content_div user_comment">
            {{#each list}}
            <div class="row dsj_update_detail">
                <div class="col-xs-1">
                	<#if user.avatar>
                    	<img class="img-responsive" src="${user.avatar}">
                    <#else>
                    	<img class="img-responsive" src="${ctx }/static/front/img/z44@3x.png" > 
                    </#if>
                    
                </div>
                <div class="col-xs-11">
                    <p class="dsj_update_title" >
                      ${user.username}
                    </p>
                    <p class="dsj_update_content">
                      {{this.content}}
                    </p>
                    <div class="dsj_update_pic row">
                        {{#each this.pictureArray}}
                        
                        	{{{commentPic this}}}
                            
                        {{/each}}

                      
                    </div>
                    <p class="dsj_update_time clearfix">
                      <span class="update_time_title">点评时间:</span>{{prettifyDate this.createTime "YYYY-MM-DD"}}
                      <span class="update_time_title">点评楼盘:</span>{{this.houseName}}
                    </p>
                </div>
            </div>
            {{/each}}
            <div class="{{className}}">
                        {{noResult}}
            </div>
            <div id="pageContainer"></div>
            </div>
        </div>
    </script>
    
    <#include "personCenter/template.ftl">
    <script src="${ctx }/static/front/js/moment.js"></script>
    <script src="${ctx }/static/front/js/handlebars.js"></script>
    <script src="${ctx }/static/front/js/handlebars-utils.js"></script>
    <script src="${ctx }/static/front/js/webuploader.js"></script>
    <script src="${ctx }/static/front/js/crop.js"></script>
    <script src="${ctx }/static/front/js/base64-binary.js"></script>
    <script type="text/javascript">
        //var userType=${user.userType}
        var typeOfPage = '${typeOfPage}';
        
        function modifyName(){
        	$(".editInfo").click();
        	$(".update-nice").click();
        }
    </script>
    <script src="${ctx }/static/front/js/personCenter/personCenter.js"></script>
    <script src="${ctx }/static/front/js/personCenter/order.js"></script>
    



