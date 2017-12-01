<script id="new_house"type="text/x-handlebars-template">
    <div class="container" style="padding-left: 300px;">
        <div class="agent_tab_content dsj_form">
            <p class="dsj_form_line dsj_sort">
                <span>排序:</span>
                <span name="sort1" onclick="changeSort('new',1,0)">默认排序</span>
                <span name="sort1" onclick="changeSort('new',1,1)">价格由低到高<span class="dsj_arrow glyphicon glyphicon-arrow-up" aria-hidden="true"></span></span>
                <span name="sort1" onclick="changeSort('new',1,2)" >时间由新到旧<span class="dsj_arrow glyphicon glyphicon-arrow-down" aria-hidden="true"></span></span>
                <span class="total pull-right">
                  	{{{quantity}}}
                </span>
            </p>  
            {{#each list}}    
            <div class="dsj_row clearfix">
                <div class="dsj_row_img">
                    <div class="dsj_img_container">
                        <a href="{{ctx}}/front/newHouse/index_detail?id={{this.id}}"><img class="img-responsive" src="{{pictureHandler this.pictureUrl}}"></a>
                    </div>
                </div>
                <div class="dsj_row_content">
                    <h4 class="ellipsis_15">
                    <a href="{{ctx}}/front/newHouse/index_detail?id={{this.id}}">{{this.name}}</a>
                    
                        {{{status this.status}}}
                    
                    </h4>
                    <p class="clearfix">
                        <span>
                            <span>地址:</span>
                            {{this.address}}
                        </span>
                    </p>
                    <p class="clearfix">
                        <span>户型:</span>
                        {{this.housetypeNames}}
                        <span class="contect_price pull-right">{{{price this.priceName this.indexPrice this.pricedw}}}</span>
                    </p>
                    <div class="agent">
                        <span>
                          <span>动态:</span>
                            {{this.newsTitle}}
                        </span>
                        <span class="contect_phone pull-right"><span class="glyphicon glyphicon-earphone" aria-hidden="true"></span>400-898-6868转 {{this.mobile}}</span>
                    </div>
                        

                        <p class="dsj_tag">
                        {{#each this.dicTraitList}}
                            <span class="{{color @index}}">{{this}}</span>
                        {{/each}}
                    </p>
                </div>
            </div>
            {{/each}}
            <div style="text-align: center; height: {{height}};">
                {{noResult}}
            </div>
            <div class="dsj_page_container">
                <nav aria-label="Page navigation" class="text-center">
                    <ul class="pagination">
                        <li>
                            <a onclick="changePage('previous')" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                上一页
                            </a>
                        </li>
                        {{#each pageList}}
                            <li class="{{active this}}">
                                <a onclick="changePage({{this}})">
                                    {{this}}
                                </a>
                            </li>
                        {{/each}}
                        <li>
                            <a onclick="changePage('next')" aria-label="Next">
                                下一页
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                    <!--
                    <div class="dsj_page">
                        共100页到第
                        <input type="" name="">
                        页
                        <button class="btn btn-default">确定</button>
                    </div>-->
                </nav>         
            </div>
        </div>
    </div>
</script>
<script id="old_house"type="text/x-handlebars-template">
    <div class="container" style="padding-left: 300px;">
        <div class="agent_tab_content dsj_form" >
            <p class="dsj_form_line dsj_sort">
                <span>排序:</span>
                <span name="sort2" onclick="changeSort('old',2,0)">默认排序</span>
                <span name="sort2" onclick="changeSort('old',2,1)">价格由低到高<span class="dsj_arrow glyphicon glyphicon-arrow-up" aria-hidden="true"></span></span>
                <span name="sort2" onclick="changeSort('old',2,2)">时间由新到旧<span class="dsj_arrow glyphicon glyphicon-arrow-down" aria-hidden="true"></span></span>
                <span class="total pull-right">
                  {{{quantity}}}
                </span>
            </p>     
            {{#each list}} 
            <div class="dsj_row uncentain clearfix">
                <div class="dsj_row_img">
                    <div class="dsj_img_container">
                        <a href="{{ctx}}/oldmaster/{{this.id}}.html">
                            <img class="img-responsive" src="{{oldHousePic this.imageUrl}}">
                        </a>
                    </div>
                </div>
                <div class="dsj_row_content">
                    <h4 class="ellipsis_15">
                    <a href="{{ctx}}/oldmaster/{{this.id}}.html">
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
            <div style="text-align: center;height: {{height}}">
                {{noResult}}
            </div>
            <div class="dsj_page_container">
                <nav aria-label="Page navigation" class="text-center">
                    <ul class="pagination">
                        <li>
                            <a onclick="changePage('previous')" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                上一页
                            </a>
                        </li>
                        {{#each pageList}}
                            <li class="{{active this}}">
                                <a onclick="changePage({{this}})">
                                    {{this}}
                                </a>
                            </li>
                        {{/each}}
                        <li>
                            <a onclick="changePage('next')" aria-label="Next">
                                下一页
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                    <!--
                    <div class="dsj_page">
                        共100页到第
                        <input type="" name="">
                        页
                        <button class="btn btn-default">确定</button>
                    </div>-->
                </nav>         
            </div>
        </div>
    </div>
</script>
<script id="rent_house"type="text/x-handlebars-template">
    <div class="container" style="padding-left: 300px;">
        <div class="agent_tab_content dsj_form" >
            <p class="dsj_form_line dsj_sort">
                <span>排序:</span>
                <span onclick="changeSort('rent',3,0)">默认排序</span>
                <span onclick="changeSort('rent',3,1)">价格由低到高<span class="active dsj_arrow glyphicon glyphicon-arrow-up" aria-hidden="true"></span></span>
                <span onclick="changeSort('rent',3,2)">时间由新到旧<span class="dsj_arrow glyphicon glyphicon-arrow-down" aria-hidden="true"></span></span>
                <span class="total pull-right">
                  为您找到<span class="bluefont">{{count}}</span>个北京楼盘
                </span>
            </p>
            {{#each list}} 
            <div class="dsj_row uncentain clearfix">
                <div class="dsj_row_img">
                  <a href="{{ctx}}/rentHouseDetail/detail?id={{this.id}}"><img class="img-responsive" src="{{this.pictureUrl}}"></a>
                </div>
                <div class="dsj_row_content">
                    <h4 class="ellipsis_15">
                    <a href="{{ctx}}/rentHouseDetail/detail?id={{this.id}}">
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
                    <p class="clearfix">
                        <span class="sale_company">{{this.companyName}}</span>
                    </p>
                </div>
            </div>
            {{/each}} 
            <div style="text-align: center;height: {{height}};">
                {{noResult}}
            </div>
            <div class="dsj_page_container">
                <nav aria-label="Page navigation" class="text-center">
                    <ul class="pagination">
                        <li>
                            <a onclick="changePage('previous')" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                上一页
                            </a>
                        </li>
                        {{#each pageList}}
                            <li class="{{active this}}">
                                <a onclick="changePage({{this}})">
                                    {{this}}
                                </a>
                            </li>
                        {{/each}}
                        <li>
                            <a onclick="changePage('next')" aria-label="Next">
                                下一页
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                    <!--
                    <div class="dsj_page">
                        共100页到第
                        <input type="" name="">
                        页
                        <button class="btn btn-default">确定</button>
                    </div>-->
                </nav>         
            </div>
        </div>
    </div>
</script>
<script id="loading" type="text/x-handlebars-template">
    <!--我的关注  -->
    <div class="container" style="padding-left: 300px;padding-bottom: 1000px; text-align: center;margin-top: 10px">
        加载中...
    </div>
</script>
   

