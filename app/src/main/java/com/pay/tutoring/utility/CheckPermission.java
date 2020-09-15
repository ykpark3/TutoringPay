package com.pay.tutoring.utility;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * permission 체크 클래스
 */
public class CheckPermission {

    private Context context;
    private Activity activity;
    private List permissionList;
    private final int MULTIPLE_PERMISSIONS = 2020; //권한 요청을 할 때 발생하는 창에 대한 결과값을 받기위해 지정해주는 int형 코드

    public String[] permissions = {
            //요청할 권한 배열로 저장하기
    };


    public CheckPermission(Activity _activity, Context _context) {
        this.activity = _activity;
        this.context = _context;
    }

    /**
     * 허용 받아야할 권한이 남았는지 체크
     * @return
     */
    public boolean isChecked() {

        int result;
        permissionList = new ArrayList<>();


        // 위에서 배열로 선언한 권한 중 허용되지 않은 권한이 있는지 체크
        for (String pm : permissions) {

            result = ContextCompat.checkSelfPermission(context, pm);
            if (result != PackageManager.PERMISSION_GRANTED) {
                //Log.i("모은 pm 체크",pm);
                permissionList.add(pm);
            }

        }


        if (!permissionList.isEmpty()) {
            return false;
        }

        return true;

    }

    /**
     * 권한 허용 요청
     */
    public void requestPermission() {
        //Log.i("모은 ","requestPermission(permissionCheck)");
        ActivityCompat.requestPermissions(activity, (String[]) permissionList.toArray(new String[permissionList.size()]), MULTIPLE_PERMISSIONS);

    }

    /**
     * 권한 요청에 대한 결과 처리
     * @param requestCode
     * @param permissions
     * @param grantResults
     * @return
     */
    public boolean permissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.i("모은 ","permissionResult(permissionCheck)");

        // 우선 requestCode가 아까 위에 final로 선언하였던 숫자와 맞는지, 결과값의 길이가 0보다는 큰지 먼저 체크

        if (requestCode == MULTIPLE_PERMISSIONS && (grantResults.length > 0)) {

            for (int i = 0; i < grantResults.length; i++) {

                //grantResults 가 0이면 사용자가 허용한 것이고 / -1이면 거부
                // -1이 있는지 체크하여 하나라도 -1이 나온다면 false를 리턴
                if (grantResults[i] == -1) {
                    return false;
                }
            }
        }
        return true;
    }
}
