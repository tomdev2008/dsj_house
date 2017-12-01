package com.dsj.data.web.redis;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsj.common.enums.StatusCode;
import com.dsj.common.utils.sms.SMSTemplate;
import com.dsj.common.utils.sms.SMSUtil;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.modules.oldhouse.po.OldHouseEntrustPo;
import com.dsj.modules.oldhouse.service.OldHouseEntrustService;
import com.dsj.modules.other.po.VerifyCodePo;
import com.dsj.modules.other.service.VerifyCodeService;
import com.dsj.modules.rent.po.RentHouseEntrustPo;
import com.dsj.modules.rent.service.RentHouseEntrustService;

import redis.clients.jedis.Jedis;


@Controller
@RequestMapping(value = "front/redis")
public class RedisController {
	private final Logger LOGGER = LoggerFactory.getLogger(RedisController.class);
	
	@RequestMapping("test")
	@ResponseBody
	public String addOldHOuse(){
		AjaxResultVo result = new AjaxResultVo();
		   

        //连接redis服务  :第一个参数是redis的IP，第二个参数是redis访问端口

        Jedis jedis = new Jedis("10.66.145.50:6379",6379);  

          

        //密码验证-如果你没有设置redis密码可不验证即可使用相关命令   

        jedis.auth("gly2017828");  

          

        //简单的key-value 存储   

        jedis.set("redis", "myredis");  

        LOGGER.info("aaaa"+jedis.get("redis"));  
		return "ok";
	}
	
	
}
