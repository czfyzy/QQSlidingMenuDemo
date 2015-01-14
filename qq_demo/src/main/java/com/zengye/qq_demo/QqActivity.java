package com.zengye.qq_demo;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;


public class QqActivity extends ActionBarActivity {
    private DrawerLayout mDrawerLayout;

    private float downX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq);
        initView();

        initEvent();
    }

    private void initEvent() {
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = mDrawerLayout.getChildAt(0);


                View mMenu = drawerView;

                float scale = 0.8f + slideOffset * 0.2f;

                if (mMenu.getTag().equals("LEFT")) {
                    ViewHelper.setScaleY(mMenu, scale);
                    ViewHelper.setScaleX(mMenu, scale);
                    ViewHelper.setAlpha(mMenu, scale);


                    ViewHelper.setTranslationX(mContent, mMenu.getMeasuredWidth() * slideOffset * 0.8f);
                    ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
                    ViewHelper.setPivotY(mContent, mContent.getMeasuredHeight() / 2);

                    mContent.invalidate();
                    ViewHelper.setScaleX(mContent, 1f - 0.2f * slideOffset);
                    ViewHelper.setScaleY(mContent, 1f - 0.2f * slideOffset);

                } else {
                    ViewHelper.setAlpha(mMenu, scale);


                    ViewHelper.setTranslationX(mContent, -mMenu.getMeasuredWidth() / 2 * slideOffset);
                    ViewHelper.setPivotX(mContent, 0);
                    ViewHelper.setPivotY(mContent, mContent.getMeasuredHeight() / 2);

                    mContent.invalidate();
                    ViewHelper.setScaleX(mContent, 1f - 0.2f * slideOffset);
                    ViewHelper.setScaleY(mContent, 1f - 0.2f * slideOffset);
                }
            }

            @Override
            public void onDrawerOpened(View view) {

            }

            @Override
            public void onDrawerClosed(View view) {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);


    }


    public void OpenRightMenu(View view)
    {
        mDrawerLayout.openDrawer(Gravity.RIGHT);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
                Gravity.RIGHT);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();

        float lastX = ev.getX();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = downX - lastX;
                Log.e("QQActivity", dx + "");
                if(dx < -20) {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }


                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_qq, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
