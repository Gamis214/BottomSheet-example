package com.example.gamis214.bottomsheet_example;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import static android.support.design.widget.BottomSheetBehavior.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_bottom_sheet,btn_bottom_sheet_dialog,btn_bottom_sheet_dialog_fragment;
    private LinearLayout bottom_sheet;
    private BottomSheetBehavior bottomSheetBehavior;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_bottom_sheet                    = findViewById(R.id.btn_bottom_sheet);
        btn_bottom_sheet_dialog             = findViewById(R.id.btn_bottom_sheet_dialog);
        btn_bottom_sheet_dialog_fragment    = findViewById(R.id.btn_bottom_sheet_dialog_fragment);
        bottom_sheet                        = findViewById(R.id.bottom_sheet);
        toolbar                             = findViewById(R.id.toolbar);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setSupportActionBar(toolbar);
        bottomSheetBehavior = from(bottom_sheet);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState){
                    case STATE_HIDDEN:
                        break;
                    case STATE_EXPANDED: {
                        btn_bottom_sheet.setText("Close Sheet");
                    }
                    break;
                    case STATE_COLLAPSED: {
                        btn_bottom_sheet.setText("Expand Sheet");
                    }
                    break;
                    case STATE_DRAGGING:
                        break;
                    case STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        btn_bottom_sheet.setOnClickListener(this);
        btn_bottom_sheet_dialog.setOnClickListener(this);
        btn_bottom_sheet_dialog_fragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_bottom_sheet:
                if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    btn_bottom_sheet.setText("Close sheet");
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    btn_bottom_sheet.setText("Expand sheet");
                }
                break;
            case R.id.btn_bottom_sheet_dialog:
                View view = getLayoutInflater().inflate(R.layout.fragment_bottomsheet_dialog, null);
                BottomSheetDialog dialog = new BottomSheetDialog(this);
                dialog.setContentView(view);
                dialog.show();
                break;
            case R.id.btn_bottom_sheet_dialog_fragment:
                BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
                bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
                break;
        }

    }
}
