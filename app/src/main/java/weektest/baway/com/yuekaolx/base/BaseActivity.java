package weektest.baway.com.yuekaolx.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initlayout());
        findview();
        initdata();
        initlisten();
    }




    protected abstract int initlayout();
    protected abstract void findview();
    protected abstract void initdata();
    protected abstract void initlisten();
}
