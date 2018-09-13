package com.zxin.jdk.node.enums;

public enum SingleEnum{
	INSTANCE();

	private int id;
	private String score;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	private SingleEnum(){
		
	}

	public static class Mine{
		private Mine(){
			
		}
		
		public void ca(){
			
		}
		
		private void ea(){
			
		}
	}
}



