//import com.aliyuncs.DefaultAcsClient;
//import com.aliyuncs.IAcsClient;
//import com.aliyuncs.exceptions.ClientException;
//import com.aliyuncs.profile.DefaultProfile;
//import com.aliyuncs.ram.model.v20150501.CreateUserRequest;
//import com.aliyuncs.ram.model.v20150501.CreateUserResponse;
//import com.google.gson.Gson;
//
///**
// * @Author shenhaijin
// * @Date 2020/12/31 10:16
// * @Description TODO
// * @Version 1.0
// **/
//public class SdkTest {
//
//    public static void main(String[] args) throws ClientException {
//        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou","myAccessKeyId","myAccessSecret");
//        IAcsClient client = new DefaultAcsClient(profile);
//
//        CreateUserRequest userRequest = new CreateUserRequest();
//        userRequest.setUserName("myUserName");
//        userRequest.setSysRegionId("cn-hangzhou");
//
//        CreateUserResponse userResponse = client.getAcsResponse(userRequest);
//        System.out.println(new Gson().toJson(userResponse));
//    }
//}
