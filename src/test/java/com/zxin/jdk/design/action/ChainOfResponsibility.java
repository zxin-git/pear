package com.zxin.jdk.design.action;

/**
 * 责任链模式
 * @author Administrator
 *
 */
public class ChainOfResponsibility {
	//子类必须为static才能被main调用
	/**
	 * 处理链抽象类
	 * @author Administrator
	 *
	 */
	static abstract class Handler{
		protected Handler handler;
		public abstract void handleRequest();
		
		public Handler getHandler() {
			return handler;
		}
		
		public void setHandler(Handler handler) {
			this.handler = handler;
		}
	}
	
	static class FirstHandler extends Handler{
		@Override
		public void handleRequest() {
			System.out.println("FirstHandler");
			if (getHandler()!=null) {
				getHandler().handleRequest();
			}else {
				System.out.println("this is the end handler");
			}
		}
	}
	
	static class SecondtHandler extends Handler{
		@Override
		public void handleRequest() {
			System.out.println("SecondtHandler");
			if (getHandler()!=null) {
				getHandler().handleRequest();
			}else {
				System.out.println("this is the end handler");
			}
		}
	}
	
	public static void main(String[] args) {
		Handler handler,handler2,handler3;
		handler = new FirstHandler();
		handler2 = new SecondtHandler();
		handler3 = new SecondtHandler();
		handler.setHandler(handler2);
		handler2.setHandler(handler3);
		handler.handleRequest();
	}
	
	
}


