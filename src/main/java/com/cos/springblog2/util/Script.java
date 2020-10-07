package com.cos.springblog2.util;

public class Script {

	
	public static String href(String msg, String uri) {
		
		return
			"<script>"+
			"alert('"+ msg +"');"+
			"location.href='" + uri + "';" + 
			"</script>";
	}
	
	
public static String href(String uri) {
		
		return
			"<script>"+
			"location.href='" + uri + "';" + 
			"</script>";
	}
	
	
	public static String back(String msg) {

		return 
			"<script>" + 
			"alert('" + msg + "');" + 
			"history.back();" + 
			"</script>";
	}
	

	public static String outText(String msg) {

		return 
			"<script>" + 
			"alert('" + msg + "');" + 
			"</script>";
	}
	
	
	
}
