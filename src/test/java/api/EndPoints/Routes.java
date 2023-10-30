package api.EndPoints;

public class Routes
{   // this contains all the domain and endpoints fro diff. modules
	//urls for all the CRUD operations like get, put, post, delete
	//base url for all mopdules will remain same only endpoints will change
	
    public static String Base_url="https://petstore.swagger.io/v2";
    //USER module
    public static String Post_url=Base_url+"/user";
    
    public static String Get_url=Base_url+"/user/{username}";
    
    public static String Put_url=Base_url+"/user/{username}";
    
    public static String Delete_url=Base_url+"/user/{username}";
    
    //Store Module
    //url of store module
    
    //pet
    //url of pet module
    
}
