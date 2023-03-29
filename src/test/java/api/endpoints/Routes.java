package api.endpoints;

public class Routes {

	// SwaggerURI -->https://petstore.swagger.io

		public static String base_url = "https://petstore.swagger.io/v2";
		public static String post_url =base_url+ "/user";
		public static String get_url = base_url+"/user/{username}";
		public static String update_url =base_url+ "/user/{username}";
		public static String delete_url = base_url+"/user/{username}";

}
