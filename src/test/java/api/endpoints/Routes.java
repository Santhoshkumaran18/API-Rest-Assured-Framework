package api.endpoints;
//Swagger URLs

/*
 * https://petstore.swagger.io/v2/user -->POST (Create user details)
 * https://petstore.swagger.io/v2/user/{{firstName}} -->Get the user details
 * https://petstore.swagger.io/v2/user/{{firstName}} -->Update the user details
 * https://petstore.swagger.io/v2/user/{{firstName}}-->Delete the user details
 */
public class Routes {
	public static String base_url="https://petstore.swagger.io/v2";
	
	//user module
	public static String post_url=base_url+ "/user";
	public static String get_url=base_url+ "/user/{username}";
	public static String update_url=base_url+ "/user/{username}";
	public static String deleter_url=base_url+ "/user/{username}";
	
	//Pet Module 
		//This holder the url details of the pet module
	
	//Store module
		//This module holds the url details of the store module
}
