package weektest.baway.com.yuekaolx;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import weektest.baway.com.yuekaolx.base.BaseActivity;
import weektest.baway.com.yuekaolx.frag.Afragment;
import weektest.baway.com.yuekaolx.frag.Bfragment;
import weektest.baway.com.yuekaolx.frag.Cfragment;

public class MainActivity extends BaseActivity {

    private LinearLayout linearLayout;
    private ImageView imageView, images;
    private DrawerLayout drawerLayout;
    private RadioGroup group;
    private Afragment afragment;
    private Bfragment bfragment;
    private Cfragment cfragment;
    private ViewPager viewPager;
    private List<Fragment> list;

    @Override
    protected int initlayout() {

        return R.layout.activity_main;
    }

    @Override
    protected void findview() {
        imageView = findViewById(R.id.imgs);
        drawerLayout = findViewById(R.id.drawer);
        linearLayout = findViewById(R.id.linear2);
        images = findViewById(R.id.tx);
        group = findViewById(R.id.group);
        viewPager = findViewById(R.id.vp);
    }

    @Override
    protected void initdata() {

        vpfm();

    }

    @Override
    protected void initlisten() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1000);
            }
        });

            images.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerLayout.openDrawer(linearLayout);
                }
            });

    }
    public void vpfm() {
        afragment = new Afragment();
        bfragment = new Bfragment();
        cfragment = new Cfragment();
        list = new ArrayList<>();
        list.add(afragment);
        list.add(bfragment);
        list.add(cfragment);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rb2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rb3:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        group.check(R.id.rb1);
                        break;
                    case 1:
                        group.check(R.id.rb2);
                        break;
                    case 2:
                        group.check(R.id.rb3);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
