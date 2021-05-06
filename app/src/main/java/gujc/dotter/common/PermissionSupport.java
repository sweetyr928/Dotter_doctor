package gujc.dotter.common;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import java.util.List;


public class PermissionSupport {
    private Context context;
    private Activity activity;
    private String[] permissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE
    };

    private List permissionList;

}