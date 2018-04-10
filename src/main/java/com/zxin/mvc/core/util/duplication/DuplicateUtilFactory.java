package com.zxin.mvc.core.util.duplication;

import org.nutz.lang.Mirror;

/**
 * 复制工具工厂方法
 * 【建议】优先选用对象流工具，对象流工具的在大数据量情况下效率更高<br/>
 * 在复制同一对象的条件下，对两种工具进行比较的结果如下（单位毫秒）：
 * <table>
 *   <tr>
 *   	<td>复制次数</td>
 *   	<td>1</td>
 *   	<td>10</td>
 *   	<td>20</td>
 *   	<td>50</td>
 *   	<td>100</td>
 *   	<td>500</td>
 *   	<td>1000</td>
 *   	<td>10000</td>
 *   </tr>
 *   <tr>
 *   	<td>对象流复制时间</td>
 *   	<td>0</td>
 *   	<td>15</td>
 *   	<td>31</td>
 *   	<td>16</td>
 *   	<td>31</td>
 *   	<td>110</td>
 *   	<td>171</td>
 *   	<td>1421</td>
 *   </tr>
 *   <tr>
 *   	<td>XML流复制时间</td>
 *   	<td>0</td>
 *   	<td>31</td>
 *   	<td>63</td>
 *   	<td>141</td>
 *   	<td>235</td>
 *   	<td>672</td>
 *   	<td>1266</td>
 *   	<td>7532</td>
 *   </tr>
 * </table>
 *
 */
public class DuplicateUtilFactory
{
	/**
	 * 对象流工具
	 */
	public static final int TYPE_STREAM = 1;
	/**
	 * XML流工具
	 */
	public static final int TYPE_XML = 2;
	
	public static IDuplicateUtil getUtilInstance() {
		return getUtilInstance(TYPE_STREAM);
	}
	
	/**
	 * 获取复制工具实例
	 * @param type 类型，CloneUtilFactory.TYPE_XXXX
	 * @return
	 */
	public static IDuplicateUtil getUtilInstance(int type)
	{
		switch (type)
		{
			case TYPE_XML:
				return XStreamDuplicateUtil.getInstance();
			case TYPE_STREAM:
			default:
				return StreamDuplicateUtil.getInstance();
		}
	}
	
	public static <T extends IDuplicateUtil> IDuplicateUtil getUtilInstance(Class<T> classOfT) {
		return Mirror.me(classOfT).born();
	}
}

