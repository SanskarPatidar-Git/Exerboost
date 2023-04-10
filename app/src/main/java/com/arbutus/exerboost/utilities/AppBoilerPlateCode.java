package com.arbutus.exerboost.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;

public class AppBoilerPlateCode {

    public static void navigateToActivity(Context context , Class nextActivity , Bundle bundle){
        Intent intent = new Intent(context,nextActivity);
        if(bundle != null)
            intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void navigateToActivityWithFinish(Context context , Class nextActivity , Bundle bundle){
        Intent intent = new Intent(context,nextActivity);
        if(bundle != null)
            intent.putExtras(bundle);
        context.startActivity(intent);
        ((Activity)context).finish();
    }

    public static void navigateToActivityForResult(Context context , Class nextActivity , Bundle bundle , ActivityResultLauncher<Intent> mapLocationLauncher){
        Intent intent = new Intent(context,nextActivity);
        if(bundle!=null)
            intent.putExtras(bundle);
        mapLocationLauncher.launch(intent);
    }
}
