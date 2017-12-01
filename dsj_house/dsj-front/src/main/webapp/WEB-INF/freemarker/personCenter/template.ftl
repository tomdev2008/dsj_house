<script id="head-myOrder-head" type="text/x-handlebars-template">
    <div class="centre_content_div">
        <ul class="content_title warrant_title">
            <li onclick="myReplace('myOrder-head','order',0,1)" class="content_title_active"  style="cursor:pointer;">全部订单（<span id="allOrderNum">0</span>）</li>
            <li onclick="myReplace('myOrder-head','order',1,1)"  style="cursor:pointer;">待付款（<span id="waitOrderNum">0</span>）</li>
            <li onclick="myReplace('myOrder-head','order',4,1)"  style="cursor:pointer;">已付款（<span id="finishOrderNum">0</span>）</li>
            <li onclick="myReplace('myOrder-head','order',9,1)" style="cursor:pointer;">待评论（<span id="waitViewNum">0</span>）</li>
            <li onclick="myReplace('myOrder-head','order',10,1)" style="cursor:pointer;">已评论（<span id="finishViewNum">0</span>）</li>
            <li onclick="myReplace('myOrder-head','order',2,1)" style="cursor:pointer;">已取消（<span id="cancelNum">0</span>）</li>
            <li onclick="myReplace('myOrder-head','order',5,1)" style="cursor:pointer;">退款（<span id="refundNum">0</span>）</li>
        </ul>
        <div  id="centre_content">
   
        </div>
    </div>

</script>

<script id="myOrder-head" type="text/x-handlebars-template">
        <div class="myorder_list">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>订单编号</th>
                        <th>服务名称</th>
                        <th>服务者</th>
                        <th>金额</th>
                        <th>下单时间</th>
                        <th>订单状态</th>
                        <th>操作/说明</th>
                    </tr>
                </thead>
                <tbody>
                	{{#each list}}
                    <tr>
                        <td>{{this.orderNo}}</td>
                        <td>{{this.productName}}</td>
                        <td>{{this.realName}}</td>
                        <td>{{this.payment}}元</td>
                        <td class="order_time">
                            <p>{{#timestamp this.createTime '%Y-%M-%d' }}{{/timestamp}}</p>
                            <p>{{#timestamp this.createTime '%H:%m:%s' }}{{/timestamp}}</p>
                        </td>
                        <td>
                            <p class="stay_icon">
							{{#compare this.status '==' 1 }}
								待付款
							{{/compare}}
							{{#compare this.status '==' 2 }}
								已取消
							{{/compare}}
							{{#compare this.status '==' 3 }}
								超时未付款
							{{/compare}}
							{{#compare this.status '==' 4 }}
								已付款
							{{/compare}}
							{{#compare this.status '==' 5 }}
								退款受理中
							{{/compare}}
							{{#compare this.status '==' 6 }}
								退款完成
							{{/compare}}
							{{#compare this.status '==' 7 }}
								待买家确认
							{{/compare}}
							{{#compare this.status '==' 9 }}
								待评价
							{{/compare}}
							{{#compare this.status '==' 10 }}
								买家已评价
							{{/compare}}
							{{#compare this.status '==' 11 }}
								已付款
							{{/compare}}
							{{#compare this.status '==' 12 }}
								服务进行中
							{{/compare}}
							{{#compare this.status '==' 13 }}
								服务进行中
							{{/compare}}
							</p>
                            <p><a href="${ctx }/front/personCenter/order/detail?id={{this.id}}">订单详情</a></p>
                        </td>
                        <td class="operate">
                        	<!--待付款-->
                        	
                        	{{#compare this.status '==' 1 }}<!-- 待付款 -->
								<p><button onclick="gotoPay(this,{{this.orderId}})">去付款</button></p>
								<a href="#" onclick="gotoCancel(this,{{this.orderId}})" class="operate_icon">取消订单</a>
							{{/compare}}
							{{#compare this.status '==' 2 }}<!-- 已取消 -->
								<p><button onclick="gotoReOrder(this,{{this.id}})">重新下单</button><p>
							{{/compare}}
							{{#compare this.status '==' 3 }}<!-- 已过期 -->
								<p><button onclick="gotoReOrder(this,{{this.id}})">重新下单</button><p>
							{{/compare}}
							{{#compare this.status '==' 4 }}<!-- 已付款 -->
								<p><button onclick="gotoRefund(this,{{this.orderId}})">申请退款</button></p>
							{{/compare}}
							{{#compare this.status '==' 5 }}<!-- 退款受理中 -->
							{{/compare}}
							{{#compare this.status '==' 6 }}<!-- 退款完成 -->
							{{/compare}}
							{{#compare this.status '==' 7 }}<!-- 待买家确认 -->
								<p><button onclick="gotoSucess(this,{{this.orderId}})">确认完成</button></p>
								<p onclick="gotoRefund(this,{{this.orderId}})" class="operate_icon">申请退款</p>
							{{/compare}}
							{{#compare this.status '==' 9 }}<!-- 待评价 -->
								<p><button onclick="gotoAddReview(this,{{this.orderId}})">去评价</button></p>
							{{/compare}}
							{{#compare this.status '==' 10 }}<!-- 买家已评价 -->
								<p><button onclick="gotoShowReview(this,{{this.orderId}})">查看评价</button></p>
							{{/compare}}
							{{#compare this.status '==' 11 }}<!-- 待开启服务 -->
								<p><button onclick="gotoRefund(this,{{this.orderId}})">申请退款</button></p>
							{{/compare}}
							{{#compare this.status '==' 12 }}<!-- 已开启服务 -->
								<p><button onclick="gotoRefund(this,{{this.orderId}})">申请退款</button></p>
							{{/compare}}
							{{#compare this.status '==' 13 }}<!-- 待结束服务 -->
								<p><button onclick="gotoRefund(this,{{this.orderId}})">申请退款</button></p>
							{{/compare}}
                        	<!--
								<p><button onclick="gotoReOrder(this,{{this.id}})">重新下单</button><p>
								<p><button onclick="gotoPay(this,{{this.orderId}})">去付款</button></p>
								<p onclick="gotoCancel(this,{{this.orderId}})" class="operate_icon">取消订单</p>
								<p><button onclick="gotoSucess(this,{{this.orderId}})">确认完成</button></p>
								<p onclick="gotoRefund(this,{{this.orderId}})" class="operate_icon">申请退款</p>
								<p><button onclick="gotoAddReview(this,{{this.orderId}})">去评价</button></p>
								<p><button onclick="gotoShowReview(this,{{this.orderId}})">查看评价</button></p>
							-->
                        </td>
                    </tr>
                    {{/each}}
                </tbody>        
            </table>
            <div class="{{className}}">
                {{noResult}}
            </div>       
            <div id="pageContainer"></div>
        </div>
</script>

<script id="head-myEntrust-sell" type="text/x-handlebars-template">
        <!-- 我的委托 -->
        <div class="centre_content_div" >
            <ul class="content_title">
                <li onclick="myReplace('myEntrust-sell','entrust',1,1)" class="content_title_active">卖房委托</li>
                <!--<li onclick="myReplace('myEntrust-rent','entrust',2,1)">出租委托</li>-->
            </ul>
            
        </div>
        <div  id="centre_content">
   
        </div>

</script>

<script id="head-lookHistory-newHouse" type="text/x-handlebars-template">
    <!--浏览历史  -->
    <div class="centre_content_div usreplace">
        <ul class="content_title">
            <li class="content_title_active" onclick="myReplace('lookHistory-newHouse','lookHistory',1,1)">新房</li>
            <li onclick="myReplace('lookHistory-oldHouse','lookHistory',2,1)">二手房</li>
            <!--<li onclick="myReplace('lookHistory-rent','lookHistory',3,1)">租房</li>-->
            <li onclick="myReplace('lookHistory-agent','lookHistory',4,1)">经纪人</li>
        </ul> 
        <div  id="centre_content">
   
        </div>
    </div>
</script>

<script id="head-myFollow-newHouse" type="text/x-handlebars-template">
    <!--我的关注  -->
    <div class="centre_content_div usreplace">
        <ul class="content_title">
            <li class="content_title_active" onclick="myReplace('myFollow-newHouse','follow',1,1)">新房</li>
            <li onclick="myReplace('myFollow-oldHouse','follow',2,1)">二手房</li>
            <li  onclick="myReplace('myFollow-agent','follow',4,1)">经纪人</li>
        </ul> 
        <div id="centre_content">
   
        </div>
    </div>
</script>

<script id="loading" type="text/x-handlebars-template">
    <!--我的关注  -->
    <div class="" style="text-align: center;margin-top: 10px">
        加载中...
    </div>
</script>

<script id="myEntrust-sell" type="text/x-handlebars-template">
        <!-- 我的委托 -->
        
            <div class="row content_sellers_card">               
                    {{#each list}}
                    <div class="col-md-4 card">
                        <div>
                            序号：<span>{{addOne @index}}</span>
                            <span class="time">{{prettifyDate this.createTime "YYYY-MM-DD"}}</span>
                        </div>
                        <ul>
                            <li>
                                <p>小区所在城市：</p>
                                <p>{{this.city}}</p>
                            </li>
                            <li>
                                <p>小区名称：</p>
                                <p><a href="##">{{this.buildingName}}</a></p>
                            </li>
                            <li>
                                <p>房屋地址：</p>
                                <p>{{this.buildingNum}}号楼-{{this.unitNum}}单元-{{this.roomNum}}</p>
                            </li>
                            <li>
                                <p>期望售价：</p>
                                <p><strong>{{this.expectedPrice}}  </strong>万</p>
                            </li>
                            <li>
                                <p>称呼：</p>
                                <p>{{this.entrustUsername}}</p>
                            </li>
                            <li>
                                <p>手机号：</p>
                                <p>{{this.entrustPhone}}</p>
                            </li>
                        </ul>
                    </div>
                    {{/each}}
                    <div class="{{className}}">
                        {{noResult}}
                    </div> 
            </div>


</script>
<script id="myEntrust-rent" type="text/x-handlebars-template">
    <!-- 我的委托 -->

        <div class="row content_sellers_card">
            {{#each list}}
                <div class="col-md-4 card">
                    <div>
                        序号：<span>{{addOne @index}}</span>
                        <span class="time">{{prettifyDate this.createTime "YYYY-MM-DD"}}</span>
                    </div>
                    <ul>
                        <li>
                            <p>小区所在城市：</p>
                            <p>{{this.city}}</p>
                        </li>
                        <li>
                            <p>小区名称：</p>
                            <p><a href="##">{{this.buildingName}}</a></p>
                        </li>
                        <li>
                            <p>房屋地址：</p>
                            <p>{{this.buildingNum}}号楼-{{this.unitNum}}单元-{{this.roomNum}}</p>
                        </li>
                        <li>
                            <p>期望售价：</p>
                            <p><strong>{{this.expectedPrice}} </strong>元/月</p>
                        </li>
                        <li>
                            <p>称呼：</p>
                            <p>{{this.entrustUsername}}</p>
                        </li>
                        <li>
                            <p>手机号：</p>
                            <p>{{this.entrustPhone}}</p>
                        </li>
                    </ul>
                </div>
                {{/each}}
                <div class="{{className}}">
                    {{noResult}}
                </div> 
        </div>


</script>

<script id="infomation-avatar" type="text/x-handlebars-template">
    <!--编辑资料 -->
    <div class="centre_content_div" >
        <ul class="content_title">
            <li class="content_title_active">上传头像</li>
            <li class="update-nice" onclick="replace('infomation-nikename')">修改昵称</li>
            <li class="update-" onclick="replace('infomation-password')">修改密码</li>
            <li class="update-" onclick="replace('infomation-phone')">修改手机号码</li>
        </ul>
    </div>
    <div class="centre_form compilephoto">
        <form>
            <div class="form-group buttons">
                <button type="button" id="picker" class="btn submit_botton ">点击上传</button> 
                <span class="photo_hint">注：上传文件大于20k，小于600k，支持jpg、jpeg、png格式的图片；</span>   
            </div> 
            <div class="crop_container_kuang">
            <div class="crop_container row" style="display: none;">
              <div class="col-sm-4" >
                <div class="component" >
                  <div class="overlay">
                  </div>
                  <!-- This image must be on the same domain as the demo or it will not work on a local file system -->
                  <!-- http://en.wikipedia.org/wiki/Cross-origin_resource_sharing -->
                  <img class="resize-image-small" style="display: none;" src="" alt="image for resizing">
                </div>
              </div>
            </div>
            </div>
            <div class="form-group buttons">
                <button type="button" class="btn  save_botton">保存</button>    
            </div> 
        </form>
    </div>
</script>
<script id="infomation-nikename" type="text/x-handlebars-template">
    <!--编辑资料 -->
    <div class="centre_content_div" >
        <ul class="content_title">
            <li class="update-touxiang" onclick="replace('infomation-avatar')">上传头像</li>
            <li class="content_title_active">修改昵称</li>
            <li class="update-" onclick="replace('infomation-password')">修改密码</li>
            <li class="update-" onclick="replace('infomation-phone')">修改手机号码</li>
        </ul>
    </div>
    <div class="centre_form compileusername">
        <form>
            <div class="form-group">
            	<div class="input_width">
                <label for="newInputNikename"> 设置昵称：</label>
                <input type="text" class="form-control" style="display: inline-block;" id="newInputNikename" placeholder="2-10个字符">
                <span style="color:red" id="nikenameMsg"></span>
                </div>
            </div>
            <div class="form-group buttons">
                <button type="button" onclick="saveNikename()" class="btn reset_botton save_botton">保存</button>    
            </div> 
        </form>
    </div>
</script>
<script id="infomation-password" type="text/x-handlebars-template">
    <!--编辑资料 -->
    <div class="centre_content_div" >
        <ul class="content_title">
            <li class="update-touxiang" onclick="replace('infomation-avatar')">上传头像</li>
            <li class="update-nice" onclick="replace('infomation-nikename')">修改昵称</li>
            <li class="content_title_active" >修改密码</li>
            <li class="update-" onclick="replace('infomation-phone')">修改手机号码</li>
        </ul>
    </div>
    <div class="centre_form compilepassword">
        <form>
            <div class="form-group">
            	<div class="input_width">
                <label for="oldInputpwd"> 输入旧密码：</label>
                <input type="password" class="form-control" autocomplete="off" style="display: inline-block;" id="oldInputpwd" placeholder="请输入密码"><sapn style="color:red" id="pwdMsg1"></sapn>
            	</div>
            </div>
             <div class="form-group">
             	<div class="input_width">
               		<label for="newInputpwd"> 输入新密码：</label>
                	<input type="password" class="form-control" autocomplete="off" id="newInputpwd" style="display: inline-block;" placeholder="6-20位以内的字母、数字、下划线"><sapn style="color:red" id="pwdMsg"></sapn>
            	</div>
            </div>
             <div class="form-group">
             	<div class="input_width">
                <label for="affirmnewInputpwd"> 确认新密码：</label>
                <input type="password" class="form-control" autocomplete="off"  id="affirmnewInputpwd" placeholder="请输入新密码">
            	</div>
            </div>
            <div class="form-group buttons">
                <button type="button" onclick="savePwd()"class="btn reset_botton save_botton">保存</button>    
            </div> 
        </form>
    </div>
</script>
<script id="infomation-phone" type="text/x-handlebars-template">
    <!--编辑资料 -->
    <div class="centre_content_div" >
        <ul class="content_title">
            <li class="update-touxiang" onclick="replace('infomation-avatar')">上传头像</li>
            <li class="update-nice" onclick="replace('infomation-nikename')">修改昵称</li>
            <li class="update-" onclick="replace('infomation-password')">修改密码</li>
            <li class="content_title_active" >修改手机号码</li>
        </ul>
    </div>
     <div class="centre_form compilephone">
        <form>
            <div class="form-group">
            	<div class="input_width">
                <label for="exampleInputphone"> 手机号码：</label>
                <input type="text" class="form-control" id="exampleInputphone" onblur="validateYourPhone()" style="display: inline-block;"><sapn style="color:red" id="phone"></sapn>
           		</div>
            </div>
            <!-- 验证码 -->
            <div class="form-group verifyCode">
                <label for="exampleInputVcode">验证码：</label>
                <div class="row">
                    <div class="col-xs-3">
                        <div class="input-group">
                            <input type="text" class="form-control" id="exampleInputVcode" >
                            <div class="verifyCodeButton" >
                                    <input type="button"onclick="getVcode(this)" style="display: inline-block;" value="获取验证码"><sapn style="color:red" id="vcodeMsg"></sapn>
                            </div>  
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group buttons">
                <button type="button" onclick="savePhone()" class="btn reset_botton save_botton">保存</button>    
            </div> 
        </form>
    </div>
</script>

<script id="lookHistory-newHouse" type="text/x-handlebars-template">
    <!--浏览历史  -->
        <div class="subscribe_content newhouse_subsribe ">
            {{#each list}}
            <div class="dsj_row clearfix guanzhu_list">
                <div class="dsj_row_img">
                    <div class="dsj_img_container">  
                        <img class="img-responsive" src="{{pictureHandler this.pictureUrl}}">
                    </div>
                </div>
                <div class="dsj_row_content">
                    <h4>
                        <a href="${ctx }/front/newHouse/index_detail?id={{this.id}}">{{this.name}}</a>
                    </h4>
                    <p class="clearfix">
                        <span>地址:</span>
                        <span class="add_address">{{this.address}}</span>
                        <span class="contect_price pull-right">{{{price this.priceName this.indexPrice this.pricedw}}}</span>
                    </p>
                    <p class="clearfix">
                        <span>户型:</span>
                        {{this.housetypeNames}}
                        <span class="contect_phone pull-right"><span class="glyphicon glyphicon-earphone" aria-hidden="true"></span>{{this.phone}}转 {{this.mobile}}</span>
                    </p>
                    <div class="agent">
                        <span>动态:</span>
                        {{this.newsTitle}}
                    </div>
                    <span class="dsj_badge">
                      {{status this.status}}
                    </span>
                </div>
                <div class="history_time clearfix">浏览时间：{{prettifyDate this.lookTime "YYYY-MM-DD"}}
                </div>
            </div>
            {{/each}}
            <div class="{{className}}">
                {{noResult}}
            </div> 
            <div id="pageContainer"></div>
        </div>
</script>
<script id="lookHistory-oldHouse" type="text/x-handlebars-template">
    <!--浏览历史  -->
        <div class="subscribe_content secondhand_subsribe">
            {{#each list}}
            <div class="dsj_row uncentain clearfix guanzhu_list">
                <div class="dsj_row_img">
                    <div class="dsj_img_container">
                        <img class="img-responsive" src="{{this.imageUrl}}?x-oss-process=image/resize,m_lfit,h_250,w_170">
                    </div>
                </div>
                <div class="dsj_row_content">
                    <h4>
                        <a href="${ctx }/oldmaster/{{this.id}}.html">
                        {{this.title}}
                        </a>
                    </h4>
                    <p class="clearfix dsj_detail">
                        <span>{{this.buildArea}}m<sup>2</sup></span>
                        <span>{{this.room}}室{{this.hall}}厅</span>
                        <span>{{this.floor}}层（共{{this.floorNum}}层）</span>
                        <span>{{this.buildYear}}年建造</span>
                    </p>
                    <p class="clearfix">
                        <span>{{this.sprayName}}</span>
                        <span class="pull-right">
                          <span class="rent-price">{{this.price}}</span>
                          <span class="rent-unit">万</span>
                        </span>
                    </p>
                    <p class="clearfix">
                        <span>{{this.companyName}}</span>
                        <span class="single-price pull-right">
                          单价：<span>{{this.unitPrice}}</span>元/平
                        </span>
                    </p>
                    <p class="dsj_tag">
                        {{#each this.featureArrayName}}
                            <span class="{{color @index}}">{{this}}</span>
                        {{/each}}
                    </p>
                </div>
                <div class="history_time clearfix">
                    浏览时间：{{prettifyDate this.lookTime "YYYY-MM-DD"}}


                    
                </div>
            </div>
            {{/each}}
            <div class="{{className}}">
                {{noResult}}
            </div> 
            <div id="pageContainer"></div>
        </div>
</script>
<script id="lookHistory-rent" type="text/x-handlebars-template">
    <!--浏览历史  -->
        <div class="history_content renthouse_subsribe">
            {{#each list}}
            <div class="dsj_row clearfix guanzhu_list">
                <div class="dsj_row_img">
                    <div class="dsj_img_container">
                        <img class="img-responsive" src="{{this.pictureUrl}}">
                    </div>
                </div>
                <div class="dsj_row_content">
                    <h4>
                        <a href="${ctx }/rentHouseDetail/detail?id={{this.id}}">
                        {{this.houseTitle}}
                    </a>
                    </h4>
                    <p class="clearfix dsj_detail">
                        <span>{{this.area}}m<sup>2</sup></span>
                        <span>{{this.door}}室{{this.hall}}厅</span>
                        <span>{{this.houseFloor}}层（共{{this.totalFloors}}层）</span>
                        <span>{{this.rentTypeName}}</span>
                        <span>{{this.payTypeName}}</span>
                    </p>
                    <p class="clearfix">
                        <span>{{this.cardName}}</span>
                        <span class="pull-right">
                            <span class="rent-price">{{this.rentPrice}}</span>
                            <span class="rent-unit">元/月</span>
                        </span>
                    </p>
                    <div class="dsj_contect rent-house clearfix">
                        <div class="sale-person">
                            <img class="img-responsive" src="{{this.avatar}}">
                        </div>
                        <p class="pull-left">
                            <span class="sale_name">{{this.agentName}}</span>
                            <span class="sale_company">{{this.companyName}}</span>
                            <br>
                            <span class="sale_phone">{{this.agentPhone}}</span>
                        </p>
                    </div>
                </div>
                <div class="history_time clearfix">浏览时间：{{prettifyDate this.lookTime "YYYY-MM-DD"}}
                </div>   
            </div>
             {{/each}}
             <div style="text-align: center;">
                {{noResult}}
            </div>
            <div id="pageContainer"></div>
            </div>
        </div>
</script>
<script id="lookHistory-agent" type="text/x-handlebars-template">
    <!--浏览历史  -->
        <div class="history_content agent_subscribe">
        {{#each list}}
            <div class="dsj_row clearfix guanzhu_list">
                <div class="dsj_row_img">
                    <div class="dsj_img_container">
                        <img class="img-responsive" src="{{this.avatarUrl}}?x-oss-process=image/resize,m_lfit,h_100,w_132">
                    </div>

                </div>
                <div class="dsj_row_content">
                    <h4>
                        <a href="${ctx }/front/agentIndex/info?userId={{this.userId}}">{{this.name}}
                        </a>
                    </h4>
                    <p class="clearfix">
                        <span class="agent_span_title">服务公司:</span>
                        {{this.companyName}}
                    </p>
                    <p class="clearfix">
                        <span class="agent_span_title">职业资历:</span>
                        {{this.workyears}}年服务经验
                        <span class="contect_phone pull-right">{{{mobile this.mobile}}}</span>
                    </p>
                    <div class="agent">
                        <span class="agent_span_title">精耕区域:</span>
                        {{this.businessName}}
                    </div>
                    <p class="dsj_tag">
                        {{#each this.featureArray }} 
                            <span class="{{color @index}}">{{this}}</span>
                        {{/each}}
                    </p>
                </div>
                <div class="history_time clearfix">浏览时间：{{this.lookTimeString}}</div>
            </div>
            {{/each}}
            <div style="text-align: center;">
                {{noResult}}
            </div>
            <div id="pageContainer"></div>
            </div>
        </div>
</script>

<script id="myFollow-newHouse" type="text/x-handlebars-template">
    <!--我的关注  -->
        <div class="subscribe_content newhouse_subsribe">
            {{#each list}}
            <div class="dsj_row clearfix guanzhu_list">
                <div class="subscribe" id="{{this.objectId}}" onclick="cancleFollow({{this.objectId}},'myFollow-newHouse',1)">
                       	 取消关注
                    </div>
                <div class="dsj_row_img">
                    <div class="dsj_img_container">  
                        <img class="img-responsive" src="{{pictureHandler this.pictureUrl}}">
                    </div>
                </div>
                <div class="dsj_row_content">
                    <h4>
                        <a href="${ctx }/front/newHouse/index_detail?id={{this.id}}">{{this.name}}</a>
                    </h4>
                    <p class="clearfix">
                        <span>地址:</span>
                       <span class="add_address">{{this.address}}</span>
                       <span class="contect_price pull-right">{{{price this.priceName this.indexPrice this.pricedw}}}</span>
                    </p>
                    <p class="clearfix">
                        <span>户型:</span>
                        {{this.housetypeNames}}
                        <span class="contect_phone pull-right"><span class="glyphicon glyphicon-earphone" aria-hidden="true"></span>{{this.phone}}转 {{this.mobile}}</span>
                    </p>
                    <div class="agent">
                        <span>动态:</span>
                        {{this.newsTitle}}
                    </div>
                    <span class="dsj_badge">
                      {{status this.status}}
                    </span>
                </div>
            </div>
            {{/each}}
            <div class="{{className}}">
                {{noResult}}
            </div>
            <div id="pageContainer"></div>
        </div>
</script>
<script id="myFollow-oldHouse" type="text/x-handlebars-template">
    <!--我的关注  -->
        <div class="subscribe_content secondhand_subsribe">
            {{#each list}}
            <div class="dsj_row uncentain clearfix guanzhu_list">
            <div class="subscribe" id="{{this.objectId}}" onclick="cancleFollow({{this.objectId}},'myFollow-oldHouse',2)">
                        取消关注
                    </div>
                <div class="dsj_row_img">
                    <div class="dsj_img_container">  
                        <img class="img-responsive" src="{{this.imageUrl}}?x-oss-process=image/resize,m_lfit,h_250,w_170">
                    </div>
                </div>
                <div class="dsj_row_content">
                    <h4>
                     <a href="${ctx }/oldmaster/{{this.id}}.html">
                        {{this.title}}
                    </a>
                    </h4>
                    <p class="clearfix dsj_detail">
                        <span>{{this.buildArea}}m<sup>2</sup></span>
                        <span>{{this.room}}室{{this.hall}}厅</span>
                        <span>{{this.floor}}层（共{{this.floorNum}}层）</span>
                        <span>{{this.buildYear}}年建造</span>
                    </p>
                    <p class="clearfix">
                        <span>{{this.sprayName}}</span>
                        <span class="pull-right">
                          <span class="rent-price">{{this.price}}</span>
                          <span class="rent-unit">万</span>
                        </span>
                    </p>
                    <p class="clearfix">
                        <span>{{this.companyName}}</span>
                        <span class="single-price pull-right">
                          单价：<span>{{this.unitPrice}}</span>元/平
                        </span>
                    </p>
                    <p class="dsj_tag">
                        {{#each this.featureArrayName}}
                            <span class="{{color @index}}">{{this}}</span>
                        {{/each}}
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
<script id="myFollow-rentHouse" type="text/x-handlebars-template">
    <!--我的关注  -->
        <div class="subscribe_content renthouse_subsribe">
            {{#each list}} 
                <div class="dsj_row clearfix guanzhu_list">
                    <div class="subscribe" id="{{this.objectId}}" onclick="cancleFollow({{this.objectId}},'myFollow-rentHouse',3)">
                        取消关注
                    </div>
                    <div class="dsj_row_img">
                        <div class="dsj_img_container">
                            <img class="img-responsive" src="{{this.pictureUrl}}">
                        </div>
                    </div>
                    <div class="dsj_row_content">
                        <h4>
                        <a href="${ctx }/rentHouseDetail/detail?id={{this.id}}">
                        {{this.houseTitle}}
                        </a>
                        </h4>
                        <p class="clearfix dsj_detail">
                            <span>{{this.area}}m<sup>2</sup></span>
                            <span>{{this.door}}室{{this.hall}}厅</span>
                            <span>{{this.houseFloor}}层（共{{this.totalFloors}}层）</span>
                            <span>{{this.rentTypeName}}</span>
                            <span>{{this.payTypeName}}</span>
                        </p>
                        <p class="clearfix">
                            <span>{{this.cardName}}</span>
                            <span class="pull-right">
                                <span class="rent-price">{{this.rentPrice}}</span>
                                <span class="rent-unit">元/月</span>
                            </span>
                        </p>
                        <div class="dsj_contect rent-house clearfix">
                            <div class="sale-person">
                                <img class="img-responsive" src="{{this.avatar}}">
                            </div>
                            <p class="pull-left">
                                <span class="sale_name">{{this.agentName}}</span>
                                <span class="sale_company">{{this.companyName}}</span>
                                <br>
                                <span class="sale_phone">{{this.agentPhone}}</span>
                            </p>
                        </div>
                    </div>
                </div>
            {{/each}}
            <div class="{{className}}">
                {{noResult}}
            </div>
            <div id="pageContainer"></div>
        </div>
</script>
<script id="myFollow-agent" type="text/x-handlebars-template">
    <!--我的关注  -->
        <div class="subscribe_content agent_subscribe">
            {{#each list}}    
                <div class="dsj_row clearfix guanzhu_list">
                    <div class="subscribe" id="{{this.objectId}}" onclick="cancleFollow({{this.objectId}},'myFollow-agent',4)">
                        取消关注
                    </div>
                    <div class="dsj_row_img">
                        <div class="dsj_img_container">
                            <img class="img-responsive" src="{{this.avatarUrl}}?x-oss-process=image/resize,m_lfit,h_100,w_132">
                        </div>
                    </div>
                    <div class="dsj_row_content">
                        <h4>
                            <a href="${ctx }/front/agentIndex/info?userId={{this.userId}}">{{this.name}}</a>
                        </h4>
                        <p class="clearfix">
                            <span class="agent_span_title">服务公司:</span>
                            {{this.companyName}}
                        </p>
                        <p class="clearfix">
                            <span class="agent_span_title">职业资历:</span>
                            {{this.workyears}}年服务经验
                            <span class="contect_phone pull-right">{{{mobile this.mobile}}}</span>
                        </p>
                        <div class="agent">
                            <span class="agent_span_title">精耕区域:</span>
                            {{this.businessName}}
                        </div>
                        <p class="dsj_tag">
                            {{#each this.featureArray}} 
                                <span class="{{color @index}}">{{this}}</span>
                            {{/each}}
                        </p>
                    </div>
                </div>       
　          {{/each}}   
            <div class="{{className}}">
                {{noResult}}
            </div>       
            <div id="pageContainer"></div>
        </div>
</script>

<script id="page" type="text/x-handlebars-template">
    <div class="row dsj_page_block">
        <div class="col-sm-12">
            <nav aria-label="Page navigation" style="text-align: center;">
                <ul class="pagination" >
                    <li>
                        <a class="first" onclick="changePage({{firstPage}})" aria-label="first">
                          <span aria-hidden="true" >首页</span>
                        </a>
                    </li>
                    <li>
                        <a class="prev" onclick="changePage('previous')" aria-label="Previous">
                            <span aria-hidden="true" >上一页</span>
                        </a>
                    </li>
                    {{#each pageList}}
                        <li class="{{active this}}">
                            <a class="page_button " onclick="changePage({{this}})">{{this}}
                            </a>
                        </li>
                    {{/each}}
                    <li>
                        <a class="next" onclick="changePage('next')" aria-label="Next">
                            <span aria-hidden="true" >下一页</span>
                        </a>
                    </li>
                    <li>
                        <a class="last" onclick="changePage({{pageNum}})" aria-label="last">
                            <span aria-hidden="true" >尾页</span>
                        </a>
                    </li>
                </ul>
                <div style="display: inline-block;line-height: 34px;margin: 20px 0;vertical-align: text-bottom;">
                    <span class="">共<span>{{count}}</span>条</span>
                </div>
            </nav>
        </div>
    </div>
</script>