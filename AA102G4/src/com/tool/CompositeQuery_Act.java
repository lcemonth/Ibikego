/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */


package com.tool;

import java.util.*;

public class CompositeQuery_Act {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("stroke_no".equals(columnName) || "act_no".equals(columnName) || "loc_no".equals(columnName)||"mem_no".equals(columnName)) {
			
			aCondition = columnName + "=" + value;
			
		}
		return aCondition + " ";
	}

	public static String get_AndCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)&&!"act_start_date".equals(key)&&!"act_end_date".equals(key)) {
				String aCondition = get_aCondition_For_Oracle(key, value.trim());
				whereCondition.append(" and " + aCondition);
			}
		}
		
		return whereCondition.toString();
	}
	
	public static String get_AndConditionByJoinAct(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)&&!"act_start_date".equals(key)&&!"act_end_date".equals(key)
			    &&!"mem_no".equals(key)	) {
				String aCondition = get_aCondition_For_Oracle(key, value.trim());
				whereCondition.append(" and " + aCondition);
			}
		}
		
		return whereCondition.toString();
	}
	
	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)&&!"act_start_date".equals(key)&&!"act_end_date".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

			}
		}
		
		return whereCondition.toString();
	}
	
	public static String get_WhereConditionByJoinAct(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)&&!"act_start_date".equals(key)&&!"act_end_date".equals(key)
				&&!"mem_no".equals(key)	) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

			}
		}
		
		return whereCondition.toString();
	}

}
