package com.hsdcw.kktestokhtp;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity   {
    private OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


mygetObjectjson();
//        mypostObjectjson();
// mygetFuZaObjectjson();

        httpGet();
       // httpPost();
        /*
        OkHttpClient mOkHttpClient = new OkHttpClient();
        String url="http://ip.taobao.com/service/getIpInfo.php?ip=63.223.108.42";
        Request request = new Request.Builder().url(url).get().build();
        Call call = mOkHttpClient.newCall(request);
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                //请求失败
                Log.e("TAG", "请求失败");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                //不是UI线程,请不要在此更新界面
                String htmlStr = response.body().string();
                Log.e("TAG", "htmlStr ==" + htmlStr);
            }




            });
        */
    }
    /**
     * Http Get 请求
     */
    private void httpGet() {
        ///创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request Request是OkHttp中访问的请求，Builder是辅助类。Response即OkHttp中的响应。
        final Request request = new Request.Builder()
                .url("http://ip.taobao.com/service/getIpInfo.php?ip=63.223.108.42")
                .build();
        //得到一个call对象
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                //请求失败
                Log.e("TAG", "请求失败");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String htmlStr = response.body().string();
                Log.e("TAG", "htmlStr ==" + htmlStr);
            }

        });
    }
    /**
     * Http Post请求
     */
    private void httpPost() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder()
                .add("ip", "63.223.108.42")
                .build();
        Request request = new Request.Builder()
                .url("http://ip.taobao.com/service/getIpInfo.php?")
                .post(formBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                //请求失败
                Log.e("TAG", "请求失败");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String htmlStr = response.body().string();
                Log.e("TAG", "htmlStr ==" + htmlStr);
            }
        });
    }

    String run(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
/* 封装后get的使用
     * 已测试
     */
    private void myGet(){
        OkHttpClientManager.getAsyn("https://www.baidu.com", new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(String u) {
//               主线程
                Log.e("1", "返回数据：" + u);
            }
        });
    }

    /**
     * 分装后的post请求
     * 已测试
     *http://blog.csdn.net/zhaihaohao1/article/details/51151114
     */
    private void  myPost(){
//       设置参数
        Map<String,String> map = new HashMap<String,String>();
        map.put("type", "1");
//       请求
        OkHttpClientManager.postAsyn("http://112.124.22.238:8081/course_api/banner/query", new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                Log.e("1", "" + response.toString());

            }
        }, map);
    }

    /**
     * get
     * 整合json解析json[]
     * 已测试
     */
    private void  mygetArrayjson(){
        OkHttpClientManager.getAsyn("http://112.124.22.238:8081/course_api/banner/query?type=1",
                new OkHttpClientManager.ResultCallback<List<Entity>>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(List<Entity> entitity) {
                        Log.e("TAG", entitity.size() + "");

                        for (int i = 0; i < entitity.size(); i++) {
                            Log.e("1", "" + entitity.get(i).getName().toString());
                        }
                    }
                });
    }

    /**
     * get
     * 整合json解析{}
     * 已测试
     */
    private void mygetObjectjson(){
        OkHttpClientManager.getAsyn("http://fanyi.youdao.com/openapi.do?keyfrom=woshilaowang3&key=940408814&type=data&doctype=json&version=1.1&q=牛",
                new OkHttpClientManager.ResultCallback<Cow>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Cow cow) {
//                     主线程
                        Log.e("1", "" + cow.getQuery());

                    }
                });

    }
    /**
     * post
     * 整合json解析json[]
     * 已测试
     */
    private void  mypostArrayjson(){
        //       设置参数
        Map<String,String>map = new HashMap<String,String>();
        map.put("type", "1");
        //       请求
        OkHttpClientManager.postAsyn("http://112.124.22.238:8081/course_api/banner/query", new OkHttpClientManager.ResultCallback<List<Entity>>() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(List<Entity> entitity) {
                for (int i = 0; i < entitity.size(); i++) {
                    Log.e("1", "" + entitity.get(i).getName().toString());
                }
            }
        }, map);
    }
    /**
     * post
     * 整合json解析object{}
     * 已测试
     */
    private void  mypostObjectjson(){
        //       设置参数
        Map<String,String>map = new HashMap<String,String>();
        map.put("q", "牛");
        //       请求
        OkHttpClientManager.postAsyn("http://fanyi.youdao.com/openapi.do?keyfrom=woshilaowang3&key=940408814&type=data&doctype=json&version=1.1", new OkHttpClientManager.ResultCallback<Cow>() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(Cow cow) {
                Log.e("1",cow.getQuery());
            }
        }, map);

    }
    /**
     * 解析复杂数据
     * 和解析一个对象是一样的，只是在javaBean中改变即可
     */
    private void mygetFuZaObjectjson(){
        OkHttpClientManager.getAsyn("http://fanyi.youdao.com/openapi.do?keyfrom=woshilaowang3&key=940408814&type=data&doctype=json&version=1.1&q=牛",
                new OkHttpClientManager.ResultCallback<JsonBean>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(JsonBean jsonBean) {
                        List<JsonBean.Web> web = jsonBean.web;
                        for (JsonBean.Web webData  : web) {
                            String webDataStr=webData.key;
                            Log.e("1",webDataStr);
                        }


                    }
                });

    }
    /**
     * 文件上传且携带参数
     * 我们希望提供一个方法，传入url,params,file,callback即可。
     * 未测试
     */
//    private void  upLoaderFile(){
//        OkHttpClientManager.postAsyn("http://192.168.1.103:8080/okHttpServer/fileUpload",//
//                new OkHttpClientManager.ResultCallback<String>()
//                {
//
//                    @Override
//                    public void onError(Request request, Exception e) {
//
//                    }
//
//                    @Override
//                    public void onResponse(String result)
//                    {
//
//                    }
//                },//
//                file,//
//                "mFile",//
//                new OkHttpClientManager.Param[]{
//                        new OkHttpClientManager.Param("username", "zhy"),
//                        new OkHttpClientManager.Param("password", "123")}
//        );
//
//    }

    /**
     * 文件下载
     * 对于文件下载，提供url，目标dir，callback即可。
     * 未测试
     */
    private void downLoader(){
        OkHttpClientManager.downloadAsyn(
                "http://192.168.1.103:8080/okHttpServer/files/messenger_01.png",
                Environment.getExternalStorageDirectory().getAbsolutePath(),
                new OkHttpClientManager.ResultCallback<String>() {

                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        //文件下载成功，这里回调的reponse为文件的absolutePath
                    }
                });

    }
}
