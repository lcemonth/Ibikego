/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */


package com.forum.model;

import java.util.*;

public class jdbcUtil_CompositeQuery_forum {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;
		
		if ("forum_no".equals(columnName) || "mem_no".equals(columnName) || "forum_del".equals(columnName)) 
			aCondition = columnName + "=" + value;
		else if ("forum_title".equals(columnName) || "forum_content".equals(columnName)) 
			aCondition = columnName + " like '%" + value + "%'";
		else if ("forum_hiredate".equals(columnName))                         
			aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					/**修改**/
					whereCondition.append(" where forum_del=0 and " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count= " + count);
			}
		}
		
		return whereCondition.toString();
	}

//	public static void main(String argv[]) {
//
//		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
//		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("forum_title", new String[] { "Garmin" });
//		map.put("hiredate", new String[] { "2016-03-21" });
//		map.put("action", new String[] { "getXXX" });  // 注意Map裡面會含有action的key
//
//		String finalSQL = "select * from forum "
//				          + jdbcUtil_CompositeQuery_forum.get_WhereCondition(map)
//				          + "and forumdel=0 order by empno";
//		System.out.println("finalSQL = " + finalSQL);
//
//	}
}
