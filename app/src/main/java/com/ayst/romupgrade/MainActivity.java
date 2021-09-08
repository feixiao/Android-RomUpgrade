/*
 * Copyright(c) 2020 Bob Shen <ayst.shen@foxmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ayst.romupgrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ayst.romupgrade.service.UpdateService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "MainActivity";


//    private Button mBtn;
    private Button mLocalBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mBtn = findViewById(R.id.button);
//        mBtn.setOnClickListener(this);

        Intent intent = new Intent(this, UpdateService.class);
        startService(intent);

        mLocalBtn = findViewById(R.id.button2);
        mLocalBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
//            case R.id.button:
//                intent = new Intent(this, UpdateService.class);
//                startService(intent);
//                break;
            case R.id.button2:
                Log.d(TAG, "onClick: COMMAND_CHECK_LOCAL_UPDATE");
                intent = buildIntent(this,
                        UpdateService.COMMAND_CHECK_LOCAL_UPDATE,
                        10000);
                startService(intent);
                break;
            default:
                break;
        }
    }

    private Intent buildIntent(Context context, int command, int delay) {
        Intent intent = new Intent(context, UpdateService.class);
        intent.putExtra("command", command);
        intent.putExtra("delay", delay);
        return intent;
    }
}