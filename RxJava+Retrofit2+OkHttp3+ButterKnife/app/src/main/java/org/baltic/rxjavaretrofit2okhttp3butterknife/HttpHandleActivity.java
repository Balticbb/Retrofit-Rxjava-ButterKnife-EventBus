package org.baltic.rxjavaretrofit2okhttp3butterknife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import org.baltic.EventBus.MessageOnEventBus;
import org.baltic.retrofit.ResponseCallBackServer;
import org.baltic.retrofit.RxCallbackServer;
import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jsonbean.JsonDataClass;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Baltic on 2016/10/8.
 */
public class HttpHandleActivity extends Activity {
    @BindView(R.id.back)
    Button back;
    String message;
    MessageOnEventBus messageOnEventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
//        EventBus.getDefault().register();
        ButterKnife.bind(this);

    }

    @OnClick(R.id.back)

    public void onClick() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String type = intent.getStringExtra(messageOnEventBus.TYPE_A);
        if (type.equals("A")) {
            doOnlyHttp();
        } else if (type.equals("B")) {
            doRxjavaHttp();
        } else if (type.equals("C")) {
            doRxRetrofitOkhttp();
        }

    }

    private void doRxRetrofitOkhttp() {
        String url = "http://192.168.1.114:8080/";
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(500, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .baseUrl(url)
                .build();
        RxCallbackServer rxCallbackServer = retrofit.create(RxCallbackServer.class);
        rxCallbackServer.getTopMovie().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JsonDataClass>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        message = e.getMessage();
                    }

                    @Override
                    public void onNext(JsonDataClass response) {
                        message = response.getList().get(0).getName();
                    }
                });

    }

    private void doRxjavaHttp() {
        String url = "http://192.168.1.114:8080/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        RxCallbackServer rxCallbackServer = retrofit.create(RxCallbackServer.class);
        rxCallbackServer.getTopMovie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JsonDataClass>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        message = e.getMessage();
                    }

                    @Override
                    public void onNext(JsonDataClass response) {
                        message = response.getList().get(0).getName();
                    }
                });


    }

    private void doOnlyHttp() {
        String url = "http://192.168.1.114:8080/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ResponseCallBackServer responseCallBackServer = retrofit.create(ResponseCallBackServer.class);
        Call<JsonDataClass> call = responseCallBackServer.getTopMovie();
        call.enqueue(new Callback<JsonDataClass>() {
            @Override
            public void onResponse(Call<JsonDataClass> call, Response<JsonDataClass> response) {
                message = response.body().getList().get(0).getName();
            }

            @Override
            public void onFailure(Call<JsonDataClass> call, Throwable t) {
                message = t.getLocalizedMessage();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (message == null) {
            message = "message == null";
        }
        messageOnEventBus = new MessageOnEventBus();
        messageOnEventBus.setMessage(message);
        EventBus.getDefault().post(messageOnEventBus);
    }
}
