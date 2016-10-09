package org.baltic.rxjavaretrofit2okhttp3butterknife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.baltic.EventBus.MessageOnEventBus;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Home extends Activity {

    MessageOnEventBus messageOnEventBus;
    @BindView(R.id.btn_only)
    Button btnOnly;
    @BindView(R.id.btn_rxjava)
    Button btnRxjava;
    @BindView(R.id.btn_rxjava_okhttp)
    Button btnRxjavaOkhttp;
    @BindView(R.id.show)
    TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("debug", "mianmian: ");
        setContentView(R.layout.activity_home);
        messageOnEventBus = new MessageOnEventBus();
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
    }





    @Subscribe
    public void onEventMainThread(MessageOnEventBus bundleMessage) {
        show.setText(bundleMessage.getMessage());
        Log.e("debug", "onEventMainThread: " + Thread.currentThread().getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @OnClick({R.id.btn_only, R.id.btn_rxjava, R.id.btn_rxjava_okhttp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_only:
                Log.e("debug", "onRxjavaClick: ");
                Intent intent = new Intent(this, HttpHandleActivity.class);
                intent.putExtra(messageOnEventBus.TYPE_A, "A");
                startActivity(intent);
                break;
            case R.id.btn_rxjava:
                Log.e("debug", "onRxjavaClick: ");
                Intent intent1 = new Intent(this, HttpHandleActivity.class);
                intent1.putExtra(messageOnEventBus.TYPE_A, "B");
                startActivity(intent1);
                break;
            case R.id.btn_rxjava_okhttp:
                Intent intent2 = new Intent(this, HttpHandleActivity.class);
                intent2.putExtra(messageOnEventBus.TYPE_A, "C");
                startActivity(intent2);
                break;
        }
    }
}
