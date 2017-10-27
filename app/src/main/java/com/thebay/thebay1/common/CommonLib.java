package com.thebay.thebay1.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;

import com.thebay.thebay1.ActivityChangeIntentDataModel;
import com.thebay.thebay1.SubActivity;
import com.thebay.thebay1.dto.KeyDTO;
import com.thebay.thebay1.dto.MemberDTO;

/**
 * Created by kyoungae on 2017-09-18.
 */

public class CommonLib {

    public static final String id = "id";
    public static final String password = "password";
    public static MemberDTO memberDTO = null;

    public static String authKey = "auth_key";
    public static String memCode = "mem_code";
    public static KeyDTO keyDTO = null;

//    public static int getStatusBarHeight(Resources resources) {
//        int result = 0;
//        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
//        if (resourceId > 0) {
//            result = resources.getDimensionPixelSize(resourceId);
//        }
//        return result;
//    }

    /**
     * id랑 password값이 없으면 null 값 리턴하고
     * 값이 있으면 memberDTO 값 리턴
     *
     * @param context
     * @return null or memberDTO
     */
//    public static MemberDTO getMemberInfo(Context context) {
//        if (memberDTO == null) {
//            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
//            String id = sharedPreferences.getString(CommonLib.id, null);
//            String password = sharedPreferences.getString(CommonLib.password, null);
//
//            Toast.makeText(context, ""+id+password, Toast.LENGTH_SHORT).show();
//
//            if (id == null || password == null) {
//                return null;
//            }
//
//            memberDTO = new MemberDTO(id, password);
//            return memberDTO;
//        } else {
//            return memberDTO;
//        }
//    }

    /**
     * authkey 값 없으면 null 값 리턴하고
     * 값이 있으면 keyDTO 값 리턴
     *
//     * @param context
     * @return null or keyDTO
     */
    public static KeyDTO getKeyInfo(Context context) {
        if (keyDTO == null) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            String memCode = sharedPreferences.getString(CommonLib.memCode, null);
            String authKey = sharedPreferences.getString(CommonLib.authKey, null);

            if (authKey == null || memCode == null) {
                return null;
            }

            keyDTO = new KeyDTO(memCode, authKey);
            return keyDTO;
        } else {
            return keyDTO;
        }
    }

    public static void subActivityIntent(Activity activity, Fragment fragment){
        Intent intent = new Intent(activity, SubActivity.class);
        Bundle bundle = new Bundle();
        ActivityChangeIntentDataModel dataModel = new ActivityChangeIntentDataModel(fragment);
        bundle.putSerializable("data",dataModel);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }
}
