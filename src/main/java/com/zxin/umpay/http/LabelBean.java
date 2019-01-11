package com.zxin.umpay.http;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

import com.zxin.javaee.jaxb.Jaxb7Util;
import com.zxin.umpay.handler.IPare;

public class LabelBean extends ResponseBean implements IPare{

	private List<Label> labels;
	
	@Override
	public String pare(String response) throws Exception{
		LabelBean labelBean = Jaxb7Util.toBean(response, LabelBean.class);
		return labelBean.getLabels().get(0).getValue().getVal();
	}
	
	public static String pare2(String response) throws Exception{
		LabelBean labelBean = Jaxb7Util.toBean(response, LabelBean.class);
		return labelBean.getLabels().get(0).getValue().getVal();
	}
	
	public static void main(String[] args) {
		try {
			String str = "<response><sign>1fd075c4850c520aa95e8b8cc96257a4</sign><retcode>0000</retcode><funcode>Mkt2000009</funcode><datetime>1538134337203</datetime><transid>zhangxin</transid><Label><id>Con00017</id><value m=\"1\">(55;3;81;71;91;49;175;123)</value></Label></response>";
			System.out.println(pare2(str));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@XmlElement(name="Label")
	public List<Label> getLabels() {
		return labels;
	}

	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}

//	public Label getLabel() {
//		return label;
//	}
//	public void setLabel(Label label) {
//		this.label = label;
//	}

	public static class Label{
		
		private String id;
		
		private Value value;
		
		@XmlElement(name="id")
		public String getId() {
			return id;
		}
		
		public void setId(String id) {
			this.id = id;
		}
		
		@XmlElement(name="value")
		public Value getValue() {
			return value;
		}
		
		public void setValue(Value value) {
			this.value = value;
		}		
		
		private static class Value{
			
			private String m;
			
			private String val;
			
			@XmlAttribute(name="m")
			public String getM() {
				return m;
			}
			
			public void setM(String m) {
				this.m = m;
			}

			@XmlValue
			public String getVal() {
				return val;
			}
			
			public void setVal(String val) {
				this.val = val;
			}
			
		}
		
		
	}
}

