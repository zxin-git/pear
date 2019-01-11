package com.zxin.apache.http.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http post
 * 
 * @author
 * @date 2016年7月27日
 */
//@SuppressWarnings({ "deprecation" })
public class HttpClientUtil {
	private final static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

	public final static int DEFAULT_REQUEST_TIMEOUT = 10 * 1000;

	public final static int DEFAULT_CONNECT_TIMEOUT = 2 * 1000;
	
	public static CloseableHttpClient httpclient;

	// 获得池化得HttpClient
	static {
		SSLContext sslcontext = null;
		try {
			sslcontext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 默认信任所有证书
				public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
					return true;
				}
			}).build();
			
		} catch (Exception e) {
			log.error("初始化https连接池失败：", e);
		}

		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, NoopHostnameVerifier.INSTANCE);

		//---------------pool------------------------
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory())
				.register("https", sslsf).build();
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		connectionManager.setMaxTotal(500);
		connectionManager.setDefaultMaxPerRoute(50);
		httpclient = HttpClients.custom().setConnectionManager(connectionManager).build();
	}
	
	/**
	 * 静态工具类，不对外提供实例
	 */
	private HttpClientUtil() {
		
	}

	
	
	/**
	 * 
	 * @param url
	 * @param content
	 * @param contentType
	 * @return
	 * @throws Exception
	 */
	public static String sendByPost(String url, String content, String contentType) throws Exception {
		log.info("发送的路径："+url+"发送的报文："+content.replace("\r\n", "").replace("\n", ""));
		StringEntity resEntity = new StringEntity(content, Consts.UTF_8);
		List<Header> headers = new ArrayList<Header>();
		Header header = null;
		if (StringUtils.isNotEmpty(contentType)) {
			header = new BasicHeader("Content-type", contentType);
			headers.add(header);
		}
		HttpPost httpPost = null;
		httpPost = new HttpPost(url);
		httpPost.setEntity(resEntity);
		RequestConfig config = RequestConfig.custom().setConnectTimeout(DEFAULT_CONNECT_TIMEOUT)
				.setSocketTimeout(DEFAULT_REQUEST_TIMEOUT).build();
		httpPost.setConfig(config);
		String respXml = sendHttpsRequestByPost(httpPost, contentType);
		log.info("收到的响应报文："+respXml.replace("\r\n", "").replace("\n", ""));
		return respXml;
		// return sendRequest(httpPost,resEntity,headers,true);
	}

	/**
	 * 
	 * @param url
	 * @param content
	 * @param readTime
	 * @param contentType
	 * @return 
	 * @throws Exception
	 */
	public static String sendByPost(String url, String content,Integer readTime, String contentType) throws Exception {
		log.info("发送的路径："+url+"发送的报文："+content.replace("\r\n", "").replace("\n", ""));
		StringEntity resEntity = new StringEntity(content, Consts.UTF_8);
		List<Header> headers = new ArrayList<Header>();
		Header header = null;
		if (StringUtils.isNotEmpty(contentType)) {
			header = new BasicHeader("Content-type", contentType);
			headers.add(header);
		}
		HttpPost httpPost = null;
		httpPost = new HttpPost(url);
		httpPost.setEntity(resEntity);
		RequestConfig config = RequestConfig.custom().setConnectTimeout(DEFAULT_CONNECT_TIMEOUT)
				.setSocketTimeout(readTime != null ? readTime : DEFAULT_REQUEST_TIMEOUT).build();
		httpPost.setConfig(config);
		String respXml = sendHttpsRequestByPost(httpPost, contentType);
		log.info("收到的响应报文："+respXml.replace("\r\n", "").replace("\n", ""));
		return respXml;
		// return sendRequest(httpPost,resEntity,headers,true);
	}
	/**
	 * 发送HTTPS POST请求
	 * 
	 * @param 要访问的HTTPS地址,POST访问的参数Map对象
	 * @return 返回响应值
	 * @throws Exception
	 */
	public static final String sendHttpsRequestByPost(HttpPost httpPost, String contentType) throws Exception {
		String responseContent = null;
		CloseableHttpResponse resp = null;
		try {
			httpPost.addHeader("Content-type", contentType);
			resp = httpclient.execute(httpPost);
			if (resp.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity  = resp.getEntity();
				responseContent = EntityUtils.toString(entity, "UTF-8");
				resp.getHeaders("content-type");
			}else {
				HttpEntity entity  = resp.getEntity();
				log.info("响应的异常信息："+EntityUtils.toString(entity, "UTF-8"));
				httpPost.abort();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (resp != null) {
					resp.close();
				}
			} catch (Exception e) {
				log.error("关闭请求连接失败", e);
			}
		}
		return responseContent;
	}

	/**
	 * 
	 * @param httpRequest
	 * @param entity
	 * @param headers
	 * @param checkResponseStatus
	 * @return
	 */
	private static String sendRequest(HttpRequestBase httpRequest, HttpEntity entity, List<Header> headers,
			boolean checkResponseStatus) {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(DEFAULT_REQUEST_TIMEOUT)
				.setSocketTimeout(DEFAULT_CONNECT_TIMEOUT).build();
		httpRequest.setConfig(config);
		httpRequest.setHeader("User-Agent", "okHttp");
		if (httpRequest instanceof HttpEntityEnclosingRequestBase) {
			((HttpEntityEnclosingRequestBase) httpRequest).setEntity(entity);
		}
		if (null != headers) {
			// 添加请求头
			for (Header header : headers) {
				httpRequest.addHeader(header);
			}
		}
		// CloseableHttpClient httpClient = HttpClients.createDefault();

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		String resString = null;
		try {
			HttpsURLConnection.setDefaultHostnameVerifier(new AllowAllHostnameVerifier());
			// SSLSocketFactory.getSocketFactory().setHostnameVerifier(new
			// AllowAllHostnameVerifier());
			SSLContextBuilder builder = new SSLContextBuilder();
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
			httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			response = httpClient.execute(httpRequest);
			HttpEntity resEntity = response.getEntity();
			int statusCode = response.getStatusLine().getStatusCode();
			resString = EntityUtils.toString(resEntity, "UTF-8");
			return resString;
		} catch (Exception e) {
			log.error("请求出现异常：", e);
			throw new RuntimeException(resString, e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpRequest != null) {
					httpRequest.releaseConnection();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				log.error("关闭连接异常：", e);

			}
		}
	}

	public static String sendByPost(String url, String content) throws Exception {
		return sendByPost(url, content, "text/plain");
	}
	
	public static String sendByPost(String url, String content,Integer readTime) throws Exception {
		return sendByPost(url, content, readTime,"text/plain");
	}

	public static String sendGet(String url,int readTimeOut , int connectTimeOut) throws Exception{
		String result = "";
		BufferedReader in = null;
		try {
			URL realUrl = new URL(url);
			URLConnection connection = realUrl.openConnection();
			connection.setReadTimeout(readTimeOut);
			connection.setConnectTimeout(connectTimeOut);
			connection.connect();
			System.out.println(connection.getHeaderFields());
			Map<String, List<String>> map = connection.getHeaderFields();
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		}finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	public static String sendGet(String url) throws Exception{
		HttpGet httpGet = new HttpGet(url);
		RequestConfig config = RequestConfig.custom().setConnectTimeout(DEFAULT_CONNECT_TIMEOUT)
				.setSocketTimeout(DEFAULT_REQUEST_TIMEOUT).build();
		httpGet.setConfig(config);
		
		String responseContent = null;
		CloseableHttpResponse resp = null;
		try {
			resp = httpclient.execute(httpGet);
			if (resp.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity  = resp.getEntity();
				responseContent = EntityUtils.toString(entity, "UTF-8");
			}else {
				HttpEntity entity  = resp.getEntity();
				log.info("响应的异常信息："+EntityUtils.toString(entity, "UTF-8"));
				httpGet.abort();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (resp != null) {
					resp.close();
				}
			} catch (Exception e) {
				log.error("关闭请求连接失败", e);
			}
		}
		return responseContent;
	}
	
	public static void main(String[] args){
		try {
			
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 1000; i++) {
			final int a = i ;
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						String repo = sendByPost("http://localhost:80/umpaydc/dataQuery/","<?xml version=\"1.0\" encoding=\"UTF-8\"?><request></request>");
						System.out.println(a+"\t"+repo);
						repo = sendByPost("http://localhost:80/umpaydc/dataQuery/","<?xml version=\"1.0\" encoding=\"UTF-8\"?><request></request>");
					} catch (Exception e) {
						log.error("",e);
					}
				}
			});
			Thread.sleep(100);
		}
		} catch (Exception e) {
			log.error("",e);
		}
		
		
//				System.out.println(sendByPost("https://103.235.230.237:8444/umpaydc/dataQuery/", "<?xml version=\"1.0\" encoding=\"UTF-8\"?><request></request>"));
//				Thread.sleep(10);
	}
	
	
    private static class SSLHandler implements  X509TrustManager, HostnameVerifier{
		
		@Override
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return new java.security.cert.X509Certificate[]{};
			//return null;
		}
		
		@Override
		public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
				String authType) throws java.security.cert.CertificateException {
		}
		
		@Override
		public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
				String authType) throws java.security.cert.CertificateException {
		}

		@Override
		public boolean verify(String paramString, SSLSession paramSSLSession) {
			return true;
		}
	};
}
