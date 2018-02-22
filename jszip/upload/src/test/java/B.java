import java.lang.reflect.Method;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.client.HessianProxy;
import com.caucho.hessian.client.HessianProxyFactory;

public class B {
	public static void main(String[] args) throws Throwable {
		String serviceUrl = "http://10.65.255.122:8380/system_service/remoting/SmsLogService";
		HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
		Class<?> clazz = Class.forName("cn.net.gwbnsh.system.service.SmsLogService");
		HessianProxy hessianProxy = new WSHessianProxy(new URL(serviceUrl), hessianProxyFactory);
		Method method = clazz.getMethod("findBySmsLogById", Long.class);
		Object[] os = { 1L };
		Object obj = hessianProxy.invoke(clazz, method, os);
		System.out.println(JSON.toJSONString(obj));
	}

}

class WSHessianProxy extends HessianProxy {

	private static final long serialVersionUID = -6421295361339742085L;

	protected WSHessianProxy(URL url, HessianProxyFactory factory) {
		super(url, factory);
	}

	protected WSHessianProxy(URL url, HessianProxyFactory factory, Class<?> type) {
		super(url, factory, type);
	}

}