package cn.itcast.bos.web.action;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;


import cn.itcast.bos.utils.SmsUtils;
import cn.itcast.crm.domain.Customer;

@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class CustomerAction extends BaseAction<Customer> {
	
	@Action(value = "customer_sendSms")
	public String sendSms() throws IOException {
		// 手机号保存在Customer对象
		// 生成短信验证码
		String randomCode = RandomStringUtils.randomNumeric(4);
		// 将短信验证码 保存到session
		ServletActionContext.getRequest().getSession().setAttribute(model.getTelephone(), randomCode);

		System.out.println("生成手机验证码为：" + randomCode);
		// 编辑短信内容
		String msg = "尊敬的用户您好，本次获取的验证码为：" + randomCode + ",服务电话：4006184000";
		// 调用MQ服务，发送一条消息;
		String result = SmsUtils.sendSmsByHTTP(model.getTelephone(), msg);
		if(result.startsWith("000")){
			return NONE;
		}else{
			throw new RuntimeException("短信发送失败,信息码:"+result);
		}

	}

	

}
