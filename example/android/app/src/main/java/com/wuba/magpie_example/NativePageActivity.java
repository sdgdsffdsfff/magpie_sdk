package com.wuba.magpie_example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.idlefish.flutterboost.interfaces.IFlutterViewContainer;
import com.wuba.magpie.Magpie;

import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.MethodChannel;

public class NativePageActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setBackgroundDrawableResource(R.color.white);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.native_page1);
        // 设置文字是否黑色
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        decorView.setSystemUiVisibility(option);
        //StatusBarUtil.darkMode(this, true);

        View header_left = findViewById(R.id.header_left);
        header_left.setOnClickListener(this);


        View open_flutter_page = findViewById(R.id.open_flutter_page);
        View open_flutter_fragment_page = findViewById(R.id.open_flutter_fragment_page);
        View open_native_page = findViewById(R.id.open_native_page);
        //View get_dart_data = findViewById(R.id.get_dart_data);
        open_flutter_page.setOnClickListener(this);
        open_flutter_fragment_page.setOnClickListener(this);
        open_native_page.setOnClickListener(this);
        //get_dart_data.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Map params = new HashMap();
        params.put("name","张三");
        switch (v.getId()) {

            case R.id.open_flutter_page:

                PageRouter.openPageByUrl(NativePageActivity.this,PageRouter.FLUTTER_PAGE_URL,new HashMap());
//                startActivity(MagpieOriginalFlutterActivity.createDefaultIntent(MainActivity.this));
                break;

            case R.id.open_flutter_fragment_page:
                PageRouter.openPageByUrl(NativePageActivity.this,PageRouter.FLUTTER_FRAGMENT_PAGE_URL,new HashMap());
                break;

            case R.id.open_native_page:
                PageRouter.openPageByUrl(NativePageActivity.this,PageRouter.NATIVE_PAGE_URL,new HashMap());
                break;

//            case R.id.get_dart_data:
//                HashMap<String, Object> args = new HashMap<>();
//                args.put("name", "李四");
//                args.put("params", params);
//                Magpie.getInstance().invokeMethod(Magpie.MAGPIE_DATA, args, new MethodChannel.Result() {
//                    @Override
//                    public void success(@Nullable Object result) {
//                        Toast.makeText(MyApplication.instance.getApplicationContext(),
//                                JSON.toJSONString(result),Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void error(String errorCode, @Nullable String errorMessage, @Nullable Object errorDetails) {
//                        Toast.makeText(MyApplication.instance.getApplicationContext(),
//                                errorMessage + " errorDetails:" + JSON.toJSONString(errorDetails),Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void notImplemented() {
//                        Toast.makeText(MyApplication.instance.getApplicationContext(),
//                                "notImplemented",Toast.LENGTH_LONG).show();
//                    }
//                });
//                break;

            case R.id.header_left:
                finish();
                break;
        }

    }

    @Override
    public void finish() {
        HashMap<String,String> result = new HashMap<>();
        result.put("native_back_key","我是native数据");
        Intent intent = new Intent();
        if (result != null) {
            intent.putExtra(IFlutterViewContainer.RESULT_KEY, result);
        }
        this.setResult(Activity.RESULT_OK, intent);
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}