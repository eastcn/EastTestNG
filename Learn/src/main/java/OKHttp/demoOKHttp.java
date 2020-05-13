package OKHttp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class demoOKHttp {

    /**
     * get请求
     */
    public static void getHttp(){
        OkHttpClient client = new OkHttpClient(); // new 一个OKHttpClient对象，用于后续执行http请求
        String URL = "http://eastfly.top:8089/api/user/getUserInfo";
        Request request = new Request.Builder().url(URL).build(); // new一个Request对象，用于构建请求内容，如url，headers等
        try{
            Response response = client.newCall(request).execute(); // 使用client执行http请求，返回结果为Response的对象
            if (response.isSuccessful()){
                System.out.println(response.body().string()); // response对象可以查看结果中的各种数据
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        getHttp();
    }
}
