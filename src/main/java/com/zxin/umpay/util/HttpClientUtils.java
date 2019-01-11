package com.zxin.umpay.util;
import javax.net.ssl.SSLContext;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 联动请求工具类
 * 依赖 jdk 与 httpclient、httpclient、slf4j
 *
 * @author umf
 * @since jdk1.7 httpclient4.4
 *  
 * maven依赖:
 <dependency>
 	<groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
    <version>4.4</version> 4.4以上，不支持httpclient5.0
 </dependency>
 <dependency>  
 	<groupId>org.slf4j</groupId> 
    <artifactId>slf4j-api</artifactId>  
    <version>1.7.2</version>  
 </dependency> 
 */
public class HttpClientUtils {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
	
	/**
	 * 读取时间
	 */
	public final static int DEFAULT_REQUEST_TIMEOUT = 3 * 1000;

	public final static int DEFAULT_CONNECT_TIMEOUT = 1 * 1000;
	
	private final static int DEFAULT_CONNECT_REQUEST_TIMEOUT = 1 * 1000;
	
	private static CloseableHttpClient httpclient = null;
	
	/**
	 * 构造器私有化，只作为静态工具类
	 */
	private HttpClientUtils() {
		
	}
	
	/**
	 * 获取httpclient实例
	 * 采用http连接池管理
	 * 信任自签名证书，无主机验证
	 * 双重校验锁单例
	 * @return
	 */
	public static CloseableHttpClient getHttpclient(){
		if(httpclient == null){
			synchronized(HttpClientUtils.class){
				if(httpclient == null){
					try {
						//------ssl-----------
						SSLContext sslcontext = new SSLContextBuilder().loadTrustMaterial(null, TrustSelfSignedStrategy.INSTANCE).build();
						SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, NoopHostnameVerifier.INSTANCE);
						
						Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
								.register("http", PlainConnectionSocketFactory.getSocketFactory())
								.register("https", sslsf).build();
						
						//----pool-------
						PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
						connectionManager.setMaxTotal(500);
						connectionManager.setDefaultMaxPerRoute(50);
						
						//----timeout---
						RequestConfig config = RequestConfig.custom()
								.setConnectionRequestTimeout(DEFAULT_CONNECT_REQUEST_TIMEOUT)
								.setConnectTimeout(DEFAULT_CONNECT_TIMEOUT)
								.setSocketTimeout(DEFAULT_REQUEST_TIMEOUT).build();
						
						httpclient = HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(config).build();
						
//						HttpHost proxy = new HttpHost("192.168.110.128", 80);  
//						DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
//						httpclient = HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(config).setRoutePlanner(routePlanner).build();
						
					} catch (Exception e) {
						logger.error("初始化 httpclient时出现异常", e);
						throw new RuntimeException(e);
					}
				}
			}
		}
		return httpclient;
	}
	
	/**
	 * post请求
	 * Content-type：text/plain
	 * 仅限调用联动接口
	 * @param url 请求地址
	 * @param content 请求体
	 * @return
	 * @throws Exception 
	 */
	public static String post(String url, String content) throws Exception{
		return post(url, content, DEFAULT_CONNECT_TIMEOUT, DEFAULT_REQUEST_TIMEOUT);
	}
	
	/**
	 * post请求
	 * Content-type：text/plain
	 * 仅限调用联动接口
	 * @param url 请求地址
	 * @param content 请求体
	 * @param connectTimeout 建立连接超时时间
	 * @param requestTimeout 请求超时时间
	 * @return
	 * @throws Exception 
	 */
	public static String post(String url, String content, int connectTimeout, int requestTimeout) throws Exception{
		logger.info("发送的pos请求路径："+url+"发送的报文："+content.replace("\r\n", "").replace("\n", ""));
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-type", "text/plain");
		
		StringEntity reqEntity = new StringEntity(content, Consts.UTF_8);
		httpPost.setEntity(reqEntity);
		
		RequestConfig config = RequestConfig.custom().setConnectTimeout(connectTimeout)
				.setSocketTimeout(requestTimeout).build();
		httpPost.setConfig(config);
		
		String respXml = send(httpPost);
		logger.info("收到的响应报文："+respXml.replace("\r\n", "").replace("\n", ""));
		return respXml;
	}
	
	/**
	 * 发送get请求
	 * @param url 请求地址与参数，参数由外部用户自己处理url编码
	 * @return 
	 * @throws Exception
	 */
	public static String get(String url) throws Exception{
		logger.info("发送的get请求路径：{}", url);
		HttpGet httpGet = new HttpGet(url);
		String response = send(httpGet);
		return response;
	}
	
	
	public static String post(String url, ContentType contentType, String content, int connectTimeout, int requestTimeout) throws Exception{
		logger.info("发送的pos请求路径："+url+"发送的报文："+content.replace("\r\n", "").replace("\n", ""));
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(HTTP.CONTENT_TYPE, contentType.getMimeType());
		
		StringEntity reqEntity = new StringEntity(content, Consts.UTF_8);
		httpPost.setEntity(reqEntity);
		RequestConfig config = RequestConfig.custom().setConnectTimeout(connectTimeout)
				.setSocketTimeout(requestTimeout).build();
		httpPost.setConfig(config);
		
		String respXml = send(httpPost);
		logger.info("收到的响应报文："+respXml.replace("\r\n", "").replace("\n", ""));
		return respXml;
	}
	
	public static String post(String url, ContentType contentType, String content) throws Exception{
		return post(url, contentType, content, DEFAULT_CONNECT_TIMEOUT, DEFAULT_REQUEST_TIMEOUT);
	}
	
	public static String postJson(String url, String json) throws Exception{
		return post(url, ContentType.APPLICATION_JSON, json);
	}
	
	/**
	 * 发送http请求
	 * 逻辑处理较简略，仅限工具类内部使用
	 * @param httpRequestBase
	 * @return
	 * @throws Exception
	 */
	static String send(HttpRequestBase httpRequestBase) throws Exception{
		String responseContent = null;
		CloseableHttpResponse resp = null;
		try {
			resp = getHttpclient().execute(httpRequestBase);
			HttpEntity entity  = resp.getEntity();
			if (entity != null) {
				responseContent = EntityUtils.toString(entity, Consts.UTF_8);
			}
			
			if(resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
				logger.info("响应的异常信息：{}", responseContent);
				httpRequestBase.abort();
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (resp != null) {
					resp.close();
				}
			} catch (Exception e) {
				logger.error("关闭请求连接失败", e);
			}
		}
		return responseContent;
	}
	
	
	public static void main(String[] args) {
		String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><request>  <identityNo>150104198211200513</identityNo>  <license>4vnvm648tz1inki2qxa2</license>  <datetime>20181029165601</datetime>  <funcode>Gup107822</funcode>  <transid>postTest</transid>  <name>%E6%BB%A1%E9%B8%BF%E5%BF%97</name>  <sign>97c3fe82b5ece5a77420bf04692a84b5</sign>  <merid>10001234</merid>  <childmerid>YLZHMYTK</childmerid></request>";
		try {
//			String response = post("http://10.102.5.53:9005//umpaydc/dataQuery/", content);
			String response = post("https://103.235.230.237:8444//umpaydc/dataQuery/", content);
//			String response = get("https://10.102.1.75/v2/dps-registry/tags/list");
			System.out.println(response);
		} catch (Exception e) {
			logger.error("",e);
		}
	}
	
}

