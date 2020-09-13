package com.example.circularrevealcardview;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton button;
    View myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (FloatingActionButton) findViewById(R.id.fab);
        boolean isopen = false;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView = findViewById(R.id.card_view);

                if(myView.getVisibility() == View.INVISIBLE) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        // get the center for the clipping circle
                        int cx = myView.getWidth();
                        int cy = myView.getHeight();

                        // get the final radius for the clipping circle
                        float finalRadius = (float) Math.hypot(cx, cy);

                        // create the animator for this view (the start radius is zero)
                        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0f, finalRadius);

                        // make the view visible and start the animation
                        Drawable drawable = new ColorDrawable(Color.parseColor("#FFA500"));
                        myView.setBackground(drawable);
                        myView.setVisibility(View.VISIBLE);
                        int color = Color.parseColor("#FFFF00");
                        button.setBackgroundTintList(ColorStateList.valueOf(color));
                        anim.start();
                    } else {
                        // set the view to invisible without a circular reveal animation below Lollipop
                        myView.setVisibility(View.INVISIBLE);
                    }
                }
                else{

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        // get the center for the clipping circle
                        int cx = myView.getWidth();
                        int cy = myView.getHeight();

                        // get the initial radius for the clipping circle
                        float initialRadius = (float) Math.hypot(cx, cy);

                        // create the animation (the final radius is zero)
                        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0f);
                        anim.start();
                        // make the view invisible when the animation is done
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                int color = Color.parseColor("#A14BDC");

                                button.setBackgroundTintList(ColorStateList.valueOf(color));
// Check if the runtime version is at least Lollipop
                                myView.setVisibility(View.INVISIBLE);
                            }
                        });
                        // start the animation
                    } else {
                        // set the view to visible without a circular reveal animation below Lollipop
                        myView.setVisibility(View.VISIBLE);
                    }

                }

            }

        });
    }
}
