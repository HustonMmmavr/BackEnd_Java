//package tests.Test;
//
//
//import net.minidev.json.JSONObject;
//
//import javax.validation.constraints.NotNull;
//
//public class JsonRequestBuilder
//{
//    public static String getJsonRequestForSignUp(@NotNull String uName, @NotNull String uPassword, @NotNull String uEmail){
//        JSONObject jso = new JSONObject();
//        jso.put("userName", uName);
//        jso.put("userPassword", uPassword);
//        jso.put("userEmail", uEmail);
//        return jso.toString();
//    }
//
//    private final JSONObject jso;
//
//    public JsonRequestBuilder(){
//        jso = new JSONObject();
//    }
//
//    public void init(String ){
//    }
//
//    private static String getJsonRequestNewEmail(String newEmail){
//        JSONObject jso = new JSONObject();
//        jso.put("newEmail", newEmail);
//        return jso.toString();
//    }
//
//
//    private static String getJsonRequest(String uName, String uPassword, String uEmail, boolean emailNeeds ){
//        JSONObject jso = new JSONObject();
//        jso.put("userName", uName);
//        jso.put("userPassword", uPassword);
//        if (emailNeeds)
//            jso.put("userEmail", uEmail);
//        return jso.toString();
//    }
//}