package com.zxin.app.home;

public enum DateUnit {
	YEARS{
		public long toYears(long duration) { return duration;}
		public long toMonths(long duration) { return duration*12;}
	},
	MONTHS{
		public long toMonths(long duration) { return duration;}
	},
	WEEKS{
		public long toWeeks(long duration) { return duration;}
		public long toDays(long duration) { return duration*7;}
	},
	DAYS{
		public long toDays(long duration) { return duration;}
	};

	public long toYears(long duration) {
        throw new AbstractMethodError();
    }
	
	public long toMonths(long duration) {
        throw new AbstractMethodError();
    }
	
	public long toWeeks(long duration) {
        throw new AbstractMethodError();
    }
	
	public long toDays(long duration) {
        throw new AbstractMethodError();
    }
}