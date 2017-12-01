<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <#include "common/taglibs.ftl">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="keywords" content="北京楼盘,新楼盘,二手房，权证过户，新房价格信息，购买新房，二手房买卖">
	<meta name="description" content="大搜家提供北京新楼盘、北京开盘楼盘、在售楼盘、房价、二手房买卖、权证过户等信息查询，让您买房不难">
	<title>用户登录-大搜家</title>
    <!-- Bootstrap -->
    <link rel="icon" href="${ctx}/static/front/img/favicon.ico" type="image/x-icon">
	<link rel="shortcut icon" href="${ctx}/static/front/img/favicon.ico" type="image/x-icon">
    <link href="${ctx }/static/front/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/popbox.css">
    <link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/warrant.css">
    <link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/login.css">
    <link rel="stylesheet" type="text/css" href="${ctx }/static/front/css/footer.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.src.js"></script>
    <![endif]-->
      <script src="${ctx }/static/front/js/fles.js"></script>
  </head>
  <body>
      <div id="bingPhone">
      <!-- header -->
        <div class="BingHeader">
          <div class="binglogo"> 
            <a href="${ctx }/"><h1>大搜家</h1></a>
          </div>
        </div>
      <!-- mian -->
        <div class="BingMain">
          <div class="BingMainLaft">
             <p>大搜家，让买房不难</p>
          </div>
          <!-- 登录框 -->
          <div class="LoginKuang">
              <!--登录table  -->
              <ul class="registerH4 colora_2">
                  <li><a href="javascript:void(0);" class="color_blue" >账号密码登录</a></li>
                  <li><a href="javascript:void(0);" >手机快捷登录</a></li>
              </ul>
              <!-- 错误提示 -->
              <div class="errorHint" style="visibility: hidden;">
               		 您输入的手机号有误，请重新输入
              </div>
              <!-- form -->
              <form class="form-inline" id="fromId">
                <!-- 账户密码登录 -->
                <div class="passworgRegister">
                  <!--账号  -->
                  <div class="form-group">
                    <div class="input-group">
                      <label class="user_leftIcon" for="user_label"></label>
                      <input type="text" class="form-control ursname ursnameCode"  id="loginName" name='loginName' id="user_label"  placeholder="请输入用户名">
                      <div class="demptyText"></div>
                    </div>
                  </div>
                  <!-- 输入密码 -->
                  <div class="form-group">
                    <div class="input-group">
                      <label class="password_leftIcon" for="pass_label"></label>
                      <input type="password" class="form-control password passwordCode2" id="password" name="password" id="pass_label" maxlength="20" placeholder="请输入密码">
	                     <div class="showText">
	                        <label for="showText" class="showTextlabel"></label>
	                        <input name="showText" id="showText" type="checkbox" value="" />
	                      </div>
                    </div>
                  </div>
                </div>
                <!-- 手机号登录 -->
                <div class="phoneRegister"  style="display: none">
                  <!--账号  -->
                  <div class="form-group">
                    <div class="input-group">
                       <label class="user_leftIcon" for="user_label"></label>
                      <input type="text" class="form-control phone ursnameCode"  id="phoneId" placeholder="请输入手机号">
                       <div class="demptyText"></div>
                    </div>
                  </div>
                  <!-- 验证码 -->
                  <div class="form-group">
                    <div class="input-group">
                     <label class="verification_leftIcon" for="verification_label"></label>
                      <input type="text" class="form-control verifyCode" id="verifyCodeId" placeholder="请输入验证码">
                      <button type="button"  style="  cursor: pointer;" class="verifyCodeButton">获取验证码</button>
                      <!-- <button type="buttom" class="btn btn-primary verifyCodeButton">获取验证码</button> -->
                    </div>
                  </div>
                </div>
                <!-- 确认登录 -->
                 <button type="button" class="login_botton countersign">登录</button>
                <!-- 下次自动登录 -->
                <div class="checkbox autoRegister">
                  <label class="check_yess" id='zddlLbId'>
                    <input type="checkbox" > 下次自动登录
                  </label>
                  <label class="check_yess check_yess_active" id='wyydLbId' style="display: none" >
                    <input type='checkbox' checked='checked' id='checkboxId' > 我已阅读<a href='##'onclick='showUserProtocol()'>用户协议</a>
                  </label>
                  
                  <ul  class="forget">
                    <li><a href="${ctx}/login/forgetPassword">忘记密码</a></li>
                    <li><a href="${ctx}/login/register" class="color_blue">立即注册</a></li>
                  </ul>
                </div>
                <!-- 第三方登录 -->
                <div class="thirdRegisterKuang">
                  <!--第三方登录标题  -->
                   <div class="thirdRegisterTitle">
                    <p></p>
                    <div>第三方登录</div>
                  </div> 
                  <!-- 第三方登录图标 -->
                  <ul>
                    <li onclick="weixin()"><a href="">WinXin</a></li>
                    <li onclick="qqLogin()"><a href="##">QQ</a></li>
                    <li><a href="##">Sina</a></li>
                  </ul>
                </div>
              </form>
              <input id="typeId" value="1" type="hidden"/>
             <!-- 经纪人登录开发商登录 -->
              <div class="agentRegister">
                <ul class="registerH4">
                  <li><a href="http://agent.dasoujia.com">经纪人登录</a></li>
                  <li><a href="http://www.dasoujia.com/dsj-developer-back/">开发商登录</a></li>
                </ul>
              </div>
          </div>
          <div class="clear"></div>
        </div>
      </div>
               <div class="newhouse_footer login_footer">
            <div class="footer_kuang">
            <ul class="footer1">
                <li><a href="${ctx}/about/page?about=0">关于我们</a></li>
                <li><a href="${ctx}/about/page?about=1">平台协议</a></li>
                <li><a href="${ctx}/about/page?about=2">合作伙伴</a></li>
                <li><a href="http://agent.dasoujia.com/login/to_login">经纪人登录</a></li>
                <li><a href="http://pc.dasoujia.com/dsj-developer-back/login/to_login">开发商登录</a></li>
                <li><a href="http://wap.dasoujia.com/dsj-warrant-back/login/to_login">自交易商家登录</a></li>
                <li class="phone_lianxi">4008986868转888</li>
            </ul>
            </div>
            <div class="modules_kuang">
            <ul class="modules_a">
             	<li><a href="${ctx}/">首页</a></li>
                <li><a href="${ctx}/front/newHouse/list">新房</a></li>
                <li><a href="${ctx}/ershoufang">二手房</a></li>
                <li><a href="${ctx}/front/agent">经纪人</a></li>
                <li><a href="${ctx}/warrant/index">自交易</a></li>
                <li><a href="${ctx}/front/entrust/sell">业主委托</a></li>
            </ul>
            </div>
           <div class="footer2_kuang">
            <ul class="footer2">
                <li >北京大搜家信息技术服务有限公司</li>
                <li>Copyright  © 2017 大搜家  All Rights Reserved</li>
                <li class="last-li">京ICP备17018930号</li>
            </ul>
            </div> 
		</div>
      <div id="popup_box" style="display:none">            
            <div class=" box_style box_succeed" style="display:none">
                <span class="box_close"></span>
                <div class="box_kuang">
                    <div class="box_title "> 
                        <span id="successMsg">成功</span>
                    </div> 
                    <form>
                        <div class=" buttons">    
                            <button type="button" class="btn submit_botton" id="successBox">确认</button>
                        </div>  
                    </form>
                </div>       
            </div>
            <!-- 协议 -->
            <div class=" box_style box_agreement" style="display:none">
                <span class="box_close"></span>
                <div class="agreement_kuang">
                	<h1>大搜家用户服务协议</h1>
                	<div class="agreement_content">
                		<div class="platform_conter" id="user-agreement">
                        <div class="platform_header">
                          欢迎您使用大搜家的服务！您在使用大搜家平台（北京大搜家信息技术服务有限公司及其子公司）提供的各项服务之前，请您务必审慎阅读、充分理解本协议各条款内容，特别是免除或者限制责任的条款、管辖与法律适用条款。本协议是用户与大搜家之间的法律协议，是用户注册大搜家账号并使用大搜家平台服务或非经注册程序直接使用大搜家平台服务时的通用条款。您一旦在本网站注册，即视为您已了解并完全同意本服务协议各项内容，包括大搜家对服务协议随时所做的任何修改，并成为大搜家用户。
                        </div>
                        <div class="paragraph">
                            <h4>一、定义</h4>
                            <ul>
                                <li>1、大搜家平台，以下亦称为“大搜家”，是指北京大搜家信息技术服务有限公司运营的大搜家网站（<span class="pingFangSC">www.dasoujia.com</span>）、大搜家无线站点（包括<span class="pingFangSC">wap.dasoujia.com</span>）</li>
                                <li>2、用户，包含注册用户和非注册用户，以下亦称为“用户”或“您”。注册用户是指通过大搜家平台完成全部注册程序后，使用大搜家平台服务或大搜家网站资料的用户。非注册用户是指未进行注册、直接登录大搜家平台或通过其他大搜家平台允许的方式进入大搜家平台直接或间接地使用大搜家平台服务或大搜家网站资料的用户。</li>
                            </ul>
                        </div>
                        <div class="paragraph">
                            <h4> 二、用户注册</h4>
                            <ul>
                                <li>大搜家帐号（即大搜家用户ID）的所有权归大搜家，您按注册页面引导填写信息，阅读并同意本协议且完成全部注册程序后，即可获得大搜家帐号并成为大搜家用户。您应提供及时、详尽及准确的个人资料，并不断更新注册资料，符合及时、详尽准确的要求。所有原始键入的资料将引用为注册资料。如果因注册信息不真实或更新不及时而引发的相关问题，大搜家不负任何责任。用户提交的帐号名称、头像和简介等注册信息中不得出现违法和不良信息，经大搜家审核，如存在上述情况，大搜家将不予注册；同时，在注册后，如发现用户以虚假信息骗取帐号名称注册，或其帐号头像、简介等注册信息存在违法和不良信息的，大搜家有权不经通知单方采取限期改正、暂停使用、注销登记、收回等措施。</li>
                                <li>2、您注册的用户名不得冒用他人信息或侵犯他人合法权益，不能对他人名誉权造成侵犯。如发生上述行为，大搜家有权暂停或终止帐号，并有权追究相关责任人的法律责任。</li>
                                <li>3、您在注册帐号或使用大搜家平台服务的过程中，可能需要填写一些必要的信息。若国家法律法规有特殊规定的，您需要填写您的真实的身份信息及有效联系方式。若您填写的信息不完整，则无法使用大搜家平台服务或在使用过程中受到限制。</li>
                                <li>4、您需要对自己在大搜家的行为、言论负责。若您提供任何错误、不实的资料，并为大搜家所确知，或者大搜家有合理的理由怀疑前述资料为错误、不实，大搜家有权暂停或终止您的帐号。</li>
                                <li>5、如果发现任何人不当使用您的账户或有任何其他可能危及您的账户安全的情形时，您应当立即以有效方式通知大搜家，要求大搜家暂停相关服务。您理解大搜家对您的请求采取行动需要合理时间，大搜家对在采取行动前已经产生的后果（包括但不限于您的任何损失）不承担任何责任。</li>
                                <li>6、您了解并同意，如您符合并且遵守本协议条款，在通过大搜家平台完成注册程序之后，即可成为大搜家平台用户。</li>
                                <li>7、您了解并同意，一经注册用户账号或者使用大搜家平台服务，即视为您同意大搜家及/或其关联公司通过短信、电话或者电子邮件的方式向您注册时填写的手机号码或者电子邮箱发送相应的产品服务广告信息、促销优惠等营销信息或为您提供任何您可能感兴趣的信息；您如果不同意发送，您可以通过相应的退订功能进行退订。</li>
                            </ul>
                        </div>
                        <div class="paragraph">
                            <h4> 三、用户信息的使用</h4>
                            <ul>
                                <li> 1、为了向您提供更好的服务，大搜家会在您首次注册或使用本网站服务的情况下系统自动存储您的相关信息。如您发现您的帐号遭他人非法使用，应立即通知大搜家。互联网上不排除因黑客行为或用户的保管疏忽导致帐号、密码遭他人非法使用，此类情况与大搜家无关。用户是唯一对自己的账号密码及其相关注册信息负有权限及保密责任的人。因此请务必小心保护自己的账户安全。</li>
                                <li>2、因您使用大搜家服务而被大搜家获取的信息有：
                                        <ul class="second_ul">
                                            <li>（1）日志信息。当您使用大搜家服务时，服务器会自动记录一些信息，例如您对我们服务的使用情况、IP地址、所访问服务的URL、浏览器的类型和使用的语言以及访问日期和时间等。 </li>
                                            <li>（2）设备信息。某些产品和/或服务包含唯一应用程序编号。当您安装、激活、更新、卸载相关服务或当这些服务定期与大搜家通信（例如软件的更新）时，系统会将此编号以及与安装相关的信息（例如操作系统类型和应用程序版本号）发送给大搜家。 </li>
                                            <li>（3）当您使用具有定位功能的大搜家服务时，系统会自动处理有关设备的位置信息，以使得您不需要手动输入自身地理坐标就可获得相关服务。 </li>
                                            <li>（4）为了提供更好的服务与改善用户体验，大搜家可能会记录硬件型号、操作系统版本号、国际移动设备身份码（IMEI）、网络设备硬件地址（MAC）等信息。 </li>
                                            <li>（5）Cookie和匿名标示符等工具。cookie主要的功能是便于您使用网站产品和/或服务，以及帮助网站统计独立访客数量等。运用cookie技术，大搜家能够为您提供更加周到的个性化服务，并允许您设定您特定的服务选项。 当您可以选择拒绝 cookie。您可以通过修改浏览器设置的方式拒绝cookie。如果您选择拒绝cookie，则您可能无法登录或使用依赖于cookie的大搜家服务或功能。 </li>
                                            <li>以上数据信息都采用匿名的方式。同时，我们也会对信息采取加密处理，保证信息的安全性。</li>
                                        </ul>
                                </li>
                                <li>3、本网站为用户提供各种配套服务，如看房团、团购等各项活动，我们通常要求参与者填写相关资料登记表格，以便活动更好的开展。此类信息也将成为本网用户数据的一部分，并将被后续记录、保存、保护和合理使用。是否提供所要求的信息和是否参加此类活动，这完全取决于用户自身。</li>
                                <li>4、在不透露单个用户隐私资料的前提下，大搜家有权对整个用户数据库进行分析并对用户数据库进行商业上的利用。</li>
                                <li>5、本网站用户对于自己的个人信息享有以下权利：
                                        <ul class="second_ul">
                                            <li>（1）随时查询及请求阅览； </li>
                                            <li>（2）随时请求补充或更正； </li>
                                            <li>（3）随时请求删除；</li>
                                            <li>（4）请求停止电脑处理及使用。</li>
                                        </ul>
                                </li>
                                <li>6、大搜家会采取行业惯用措施保护用户信息的安全，但大搜家不能确信或保证任何个人信息的安全性，用户须自己承担风险。比如用户联机公布可被公众访问的个人信息时，用户有可能会收到未经用户同意的信息；大搜家的合作伙伴和可通过大搜家访问的第三方因特网站点和服务；通过抽奖、促销等活动得知用户个人信息的第三方会进行独立的数据收集工作等活动。大搜家对用户及其他任何第三方的上述行为，不承担任何责任。</li>
                                <li>7、在如下情况下，大搜家可能会披露您的信息:
                                        <ul class="second_ul">
                                            <li>（1）事先获得您的授权； </li>
                                            <li>（2）您使用共享功能； </li>
                                            <li>（3）根据法律、法规、法律程序的要求或政府主管部门的强制性要求；</li>
                                            <li>（4）以学术研究或公共利益为目的；</li>
                                            <li>（5）为维护大搜家的合法权益，例如查找、预防、处理欺诈或安全方面的问题；</li>
                                            <li>（6）符合相关服务条款或使用协议的规定。</li>
                                        </ul>
                                </li>
                            </ul>
                        </div>
                        <div class="paragraph">
                            <h4>四、平台使用规范</h4>
                            <ul>
                                <li> 1、房源发布需遵守《大搜家房源发布协议》；</li>
                                <li> 2、通过大搜家平台，您可以按照大搜家的规则发布各种信息，但所发布之信息不得含有如下内容：
                                    <ul class="second_ul">
                                            <li>（1）反对宪法所确定的基本原则，煽动抗拒、破坏宪法和法律、行政法规实施的； </li>
                                            <li>（2）煽动危害国家安全、泄露国家秘密、颠覆国家政权，推翻社会主义制度的；</li>
                                            <li>（3）煽动分裂国家、破坏国家统一、损害国家荣誉和民族利益的；</li>
                                            <li>（4）煽动民族仇恨、民族歧视，破坏民族团结的；</li>
                                            <li>（5）捏造或者歪曲事实，散布谣言，扰乱社会秩序的；</li>
                                            <li>（6）进行政治宣传或破坏国家宗教政策、宣扬封建迷信、淫秽、色情、赌博、暴力、凶杀、恐怖、教唆犯罪的；</li>
                                            <li>（7）公然侮辱他人或者捏造事实诽谤他人的，或者进行其他恶意攻击的；</li>
                                            <li>（8）损害国家机关信誉的；</li>
                                            <li>（9）其他违反宪法和法律法规的。</li>
                                        </ul>
                                </li>
                                <li>3、在接受大搜家平台服务的过程中，您不得从事下列行为：
                                        <ul class="second_ul">
                                            <li>（1）发表、传送、传播、储存侵害他人知识产权、商业秘密权等合法权利的内容。 </li>
                                            <li>（2）传送或散布以其他方式实现传送的含有受到知识产权法律保护的图像、相片、软件或其他资料的文件，作为举例（但不限于此）：包括版权或商标法（或隐私权或公开权），除非用户拥有或控制着相应的权利或已得到所有必要的认可；</li>
                                            <li>（3）进行危害计算机网络安全的行为，包括但不限于：使用未经许可的数据或进入未经许可的服务器/帐号；未经允许进入公众计算机网络或者他人计算机系统并删除、修改、增加存储信息；未经许可，企图探查、扫描、测试本平台系统或网络的弱点或其它实施破坏网络安全的行为；企图干涉、破坏本平台系统或网站的正常运行，故意传播恶意程序或病毒以及其他破坏干扰正常网络信息服务的行为；发布包含病毒、木马、定时炸弹等可能对大搜家系统造成伤害或影响其稳定性的内容制造虚假身份以误导、欺骗他人；伪造TCP/IP数据包名称或部分名称；</li>
                                            <li>（4）修改或伪造软件作品运行中的指令、数据、数据包，增加、删减、变动软件的功能或运行效果，不得将用于上述用途的软件通过信息网络向公众传播或者运营；</li>
                                            <li>（5）在未经大搜家书面明确授权前提下，不对大搜家平台上的任何数据作商业性利用，包括但不限于在未经大搜家事先书面同意的情况下，以复制、传播等任何方式使用大搜家平台站上展示的资料。</li>
                                        </ul>
                                </li>
                                <li>4、您了解并同意：
                                        <ul class="second_ul">
                                            <li>（1）您违反上述承诺时，大搜家有权依据本协议的约定，做出相应处理或终止向您提供服务，且无须征得您的同意或提前通知于您。 </li>
                                            <li>（2）根据相关法令的指定或者大搜家平台服务规则的判断，您的行为涉嫌违反法律法规的规定或违反本协议和/或规则的条款的，大搜家有权采取相应措施，包括但不限于直接屏蔽、删除侵权信息、降低您的信用值或直接停止提供服务。</li>
                                            <li>（3）对于您在大搜家平台上实施的行为，包括您未在大搜家平台上实施但已经对大搜家平台及其用户产生影响的行为，大搜家有权单方认定您行为的性质及是否构成对本协议和/或规则的违反，并据此采取相应的处理措施。您应自行保存与您行为有关的全部证据，并应对无法提供充要证据承担不利后果。</li>
                                            <li>（4）对于您涉嫌违反承诺的行为对任意第三方造成损害的，您均应当以自己的名义独立承担所有的法律责任，并应确保大搜家免于承担因此产生的损失或增加的费用。</li>
                                            <li>（5）如您涉嫌违反有关法律或者本协议之规定，使大搜家遭受任何损失，或受到任何第三方的索赔，或受到任何行政管理部门的处罚，您应当赔偿大搜家因此造成的损失及（或）发生的费用，包括合理的律师费用。</li>
                                        </ul>
                                </li>
                            </ul>
                        </div>
                        <div class="paragraph">
                            <h4>五、免责条款</h4>
                            <ul>
                                <li>1、由于您将用户密码告知他人或与他人共享注册帐户，由此导致的任何个人信息泄露，或其他非因大搜家原因导致的个人信息泄漏；</li>
                                <li>2、任何由于黑客政击、计算机病毒侵入或发作、因政府管制而造成的暂时性关闭等影响网络正常经营之非大搜家原因而造成的个人信息泄露、丢失、被盗用或被篡改等；</li>
                                <li>3、任何第三方根据大搜家各服务声明及服务协议中所列明的情况使用您的个人信息，由此所产生的纠纷；</li>
                                <li>4、大搜家在各服务声明及服务协议中列明的所有免责情形；</li>
                                <li>5、大搜家的网页上有与其他网站网页的链接。大搜家对其他任何网站的内容、隐私政策、运营情况、或经营这些网站的公司的行 为概不负责。在向这些与大搜家链接的网站提供个人信息之前，请查阅它们的隐私政策。由于与本网站链接的其它网站所造成的个人信息泄露及由此而导致的任何法 律争议和后果，大搜家概不负责；</li>
                                <li>6、任何因不可抗力所导致的后果。</li>
                            </ul>
                        </div>
                        <div class="paragraph">
                            <h4>六、法律声明</h4>
                            <ul>
                                <li>1、本声明未涉及的问题请参见国家有关法律法规，当本声明与国家有关法律法规冲突时，以国家法律法规为准。</li>
                                <li>2、本网站有权在必要时修改服务条款，本网站服务条款一旦发生变动，将会在重要页面上提示修改内容。如果不同意所改动的内容，用户可以主动取消获得的本网站信息服务。如果用户继续享用本网站信息服务，则视为接受服务条款的变动。本网站保留随时修改或中 断服务而不需通知用户的权利。本网站行使修改或中断服务的权利，不需对用户或第三方负责。</li>
                                <li>3、如双方就本协议内容或其执行发生任何争议，双方应尽量友好协商解决；协商不成时，任何一方均可向大搜家所在地北京市海淀区人民法院提起诉讼。 </li>
                                <li>4、本网站相关声明版权及其修改权、更新权和最终解释权均属大搜家所有。</li>
                            </ul>
                        </div>
                    </div>
                	</div>
                </div>
            </div>      
        </div>
      
      <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
      <script src="${ctx }/static/front/js/jquery.js"></script>
      <!-- Include all compiled plugins (below), or include individual files as needed -->
      <script src="${ctx }/static/front/js/bootstrap.js"></script>
      <script src="${ctx }/static/front/js/handlebars.js"></script>
      <script type="text/javascript" src="${ctx }/static/front/js/register.js"></script>
      
      <script type="text/javascript">
      
      var zddl = false;
      var yyd = true;
      
      //弹出框
            var box_succeed  = ".box_succeed";   //3

            setTimeout(function(){
          	    $(".submit_botton").on("click",function(event){
          	    	 $("#popup_box>div").hide();
          	         $("#popup_box").hide();
          	         event.stopPropagation();
          	    });
          	    $(".box_close").on("click",function(event){
                         $("#popup_box").hide();
                         event.stopPropagation(); 
                    });
            },2000);
         

            function show_box(state,msg){
                if(state==3){
                    $("#popup_box").show();
                    $('#successMsg').html(msg);
                    $(box_succeed).show();
                }else if(state==6){
                    $("#popup_box").show();
                    $(".box_agreement").show();
                }
                
            }
  // 点击table切换
    $(".registerH4>li>a").on("click",function(e){
      var e = e || window.event,
          liText = e.target.innerHTML;
          $(this).addClass("color_blue").parent().siblings().find("a").removeClass("color_blue");
        $("#fromId")[0].reset();
        if(liText == "手机快捷登录"){
          $(".passworgRegister").hide();
          $(".phoneRegister").show();
          $("#typeId").val(2);
          $("#zddlLbId").hide();
          $("#wyydLbId").show();
          //$(".check_yess").addClass("check_yess_active");
          //$(".autoRegister label").html("<input type='checkbox' checked='checked' id='checkboxId'> 我已阅读<a href='##'onclick='showUserProtocol()'>用户协议</a>");
        }else{
          $(".passworgRegister").show();
          $(".phoneRegister").hide();
          $("#typeId").val(1);
          $("#zddlLbId").show();
          $("#wyydLbId").hide();
          //$(".check_yess").removeClass("check_yess_active");
          //$(".autoRegister label").html("<input type='checkbox'> 下次自动登录");
        }
    });
  
    $(".verifyCodeButton").on("click",function(){
    	var b=true;
    	var phone=$("#phoneId").val();
    	if(phone.length!=11&&b){
			showErrorMsg("手机号格式错误");
			b=false;
    	}
    	if(b){
	        var _this = $(this);
	        
	        if ($(this).hasClass('verifyCodeActive')) {
	          return;
	        }
	        
	        $(this).addClass("verifyCodeActive").html("120s后重新获取");
	        waiteSecurityCode(_this, 119);
	        //下面就是发送ajax请求获取验证码了
	        
		   	 $.ajax({
		  		type:"post",
		  		url:_ctx+"/login/send_vcode",
		  		data:{
		  			type:$("#typeId").val(),
		  			phone:$("#phoneId").val(),
		  			verifyCode:$("#verifyCodeId").val()
		  		},
		  		dataType:"json",
		  		success: function(result){
		  			if(result.status ==200){
		  				
		  			}else{
		  				showErrorMsg(result.message);
		  			}
		  		}
		  	});
		   
		 
	    }
      }); 
      //60s后获取验证码
      function waiteSecurityCode (ele, time) {
        if (!ele) {
          return;
        }
        time = time || 59;
            var btntime = setInterval(function() {
                if (time >= 0) {
                    ele.html(time + 's后重新获取');
                    time--;
                } else {
                    ele.removeClass("verifyCodeActive")
                    ele.html("获取验证码");
                    clearInterval(btntime);
                }
            }, 1000)
      };
  

      // 单选框
    $(".check_yess").on("click",function(event){
      var inputItem = $(this).find("input");
      inputItem.prop('checked')?inputItem.parent().addClass('check_yess_active') : inputItem.parent().removeClass('check_yess_active');
      event.stopPropagation();
    });
    
    $(function(){
    	
		$(".countersign").click(function() {
			var type=$("#typeId").val();
			var loginName=$("#loginName").val();
	    	var password=$("#password").val();
	    	var phone=$("#phoneId").val();
	    	var verifyCode=$("#verifyCodeId").val();
	    	//登录
	    	var b=true;
			if(loginName==''&&b&&type==1){
				showErrorMsg("请输入用户名");
				b=false;
	    	}
			if(password==''&&b&&type==1){
	    		showErrorMsg("请输入密码");
	    		b=false;
	    	}
			
			if(phone==''&&b&&type==2){
				showErrorMsg("请输入用户名");
				b=false;
	    	}
			
			if(phone.length!=11&&b&&type==2){
				showErrorMsg("手机号格式错误");
				b=false;
	    	}
			
			if(verifyCode==''&&b&&type==2){
	    		showErrorMsg("请输入密码");
	    		b=false;
	    	}
			
			if(!$("#checkboxId").is(':checked')&&b&&type==2){
				showErrorMsg("请阅读用户协议");
				b=false;
			}
			
	    	if(b){
				startLogin();
	    	}
		})
    })
    
    //错误提示
    function showErrorMsg(msg){
        $(".errorHint").css("visibility","visible"); 
        $(".errorHint").html(msg);
        setTimeout(function(){
            $(".errorHint").css("visibility","hidden"); 
        },2000)
    }
    
    function startLogin(){
		 $.ajax({
     		type:"post",
     		url:_ctx+"/login/submit_login",
     		data:{
     			loginName:$("#loginName").val(),
     			password:$("#password").val(),
     			type:$("#typeId").val(),
     			phone:$("#phoneId").val(),
     			verifyCode:$("#verifyCodeId").val()
     		},
     		dataType:"json",
     		success: function(result){
     			if(result.status ==200){
     				window.location.href=result.data;
     			}else{
     				$(".errorHint").html(result.message);
    	    		$(".errorHint").css("visibility","visible");
     			}
     		}
     	});
    }
    $().ready(function(){
    	$("#loginName").val("");
     	$("#password").val("");
	})
	function showUserProtocol(){
                show_box(6);
            }
  </script>
  <script>
     function weixin(){
         window.location.href=_ctx+"/weixin/getRequestCodeUrl";
     }
     function qqLogin(){
         window.location.href=_ctx+"/QQLogin/getRequestCodeUrl";
     }
  
  </script>
  </body>
  
</html>