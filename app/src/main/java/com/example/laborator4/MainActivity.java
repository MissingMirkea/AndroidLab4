package com.example.laborator4;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    ImageView imageView1, imageView2;
    private int xDelta, yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize ImageViews
        imageView1 = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);

        // Set touch listener for the first image
        imageView1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return handleTouch(event, imageView1);
            }
        });

        // Set touch listener for the second image
        imageView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return handleTouch(event, imageView2);
            }
        });
    }

    // Method to handle touch events
    private boolean handleTouch(MotionEvent event, ImageView imageView) {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) imageView.getLayoutParams();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Save the initial touch coordinates
                xDelta = (int) event.getRawX() - params.leftMargin;
                yDelta = (int) event.getRawY() - params.topMargin;
                break;

            case MotionEvent.ACTION_MOVE:
                // Calculate new position based on touch movement
                params.leftMargin = (int) event.getRawX() - xDelta;
                params.topMargin = (int) event.getRawY() - yDelta;

                // Ensure the image stays within screen bounds
                int screenWidth = getResources().getDisplayMetrics().widthPixels;
                int screenHeight = getResources().getDisplayMetrics().heightPixels;

                if (params.leftMargin < 0) params.leftMargin = 0;
                if (params.topMargin < 0) params.topMargin = 0;
                if (params.leftMargin + imageView.getWidth() > screenWidth) params.leftMargin = screenWidth - imageView.getWidth();
                if (params.topMargin + imageView.getHeight() > screenHeight) params.topMargin = screenHeight - imageView.getHeight();

                // Apply the new layout parameters
                imageView.setLayoutParams(params);
                break;

            case MotionEvent.ACTION_UP:
                // Optional: Handle when the touch is released
                break;

            default:
                break;
        }
        return true;
    }
}