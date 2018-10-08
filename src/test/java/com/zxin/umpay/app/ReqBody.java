package com.zxin.umpay.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReqBody {

	private static final Logger logger = LoggerFactory.getLogger(ReqBody.class);

	private String funcode;
	
	private String merid;
	
	private String license;
	
	private String datetime;
	
	private String mobileid;
	
	private String transid;
	
	private String childmerid;
	
	private String sign;
	
	
	public String toXml(){
		return "";
	}
	
	
	public static class ReqBodyBuilder {
		
		private String funcode = "";
		
		private String merid = "";
		
		private String license;
		
		private String datetime = System.currentTimeMillis()+"";
		
		private String mobileid;
		
		private String transid = "zxin_transid";
		
		private String childmerid;
		
		private String sign;
		
		public ReqBody build(){
			ReqBody reqBody = new ReqBody();
			reqBody.funcode = this.funcode;
			reqBody.merid = this.merid;
			reqBody.license = this.license;
			reqBody.datetime = this.datetime;
			reqBody.mobileid = this.mobileid;
			reqBody.transid = this.transid;
			reqBody.childmerid = this.childmerid;
			reqBody.sign = this.sign;
			return reqBody;
		}

		public ReqBodyBuilder setFuncode(String funcode) {
			this.funcode = funcode;
			return this;
		}

		public ReqBodyBuilder setMerid(String merid) {
			this.merid = merid;
			return this;
		}

		public ReqBodyBuilder setLicense(String license) {
			this.license = license;
			return this;
		}

		public ReqBodyBuilder setDatetime(String datetime) {
			this.datetime = datetime;
			return this;
		}

		public ReqBodyBuilder setMobileid(String mobileid) {
			this.mobileid = mobileid;
			return this;
		}

		public ReqBodyBuilder setTransid(String transid) {
			this.transid = transid;
			return this;
		}

		public ReqBodyBuilder setChildmerid(String childmerid) {
			this.childmerid = childmerid;
			return this;
		}

		public ReqBodyBuilder setSign(String sign) {
			this.sign = sign;
			return this;
		}
		
		public ReqBodyBuilder makeSign() {
			StringBuffer strBuffer = new StringBuffer();
			strBuffer.append("funcode").append(funcode);
			strBuffer.append("datetime").append(datetime);
			strBuffer.append("merid").append(merid);
			strBuffer.append("transid").append(transid);
			this.sign = strBuffer.toString();
			return this;
		}
		
	}
	
}
