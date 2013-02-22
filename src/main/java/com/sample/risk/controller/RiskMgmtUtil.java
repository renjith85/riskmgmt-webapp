package com.sample.risk.controller;

import org.json.JSONException;
import org.json.JSONObject;

public class RiskMgmtUtil {
	
	public static boolean isServiceSuccess(String serviceMessage)
	{
		try {
			if(null != serviceMessage)
				{
				JSONObject json = new JSONObject(serviceMessage);
				String message = json.getString("MESSAGE");
				if("SUCCESS".equalsIgnoreCase(message))
				{
					return true;
				}
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	
}
