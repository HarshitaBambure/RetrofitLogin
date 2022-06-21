package com.harshita.retrofitlogin.utils

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.preference.PreferenceManager

object PermissionUtils {

    fun useRunTimePermissions():Boolean {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1;
    }

    fun hasPermission(activity: Activity, permission:String):Boolean {
        if (useRunTimePermissions()) {
            return activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        }
        return true
    }

    fun requestPermissions( activity:Activity, permission:Array<String>, requestCode:Int=100 ) {
        if (useRunTimePermissions()) {
            activity.requestPermissions(permission, requestCode);
        }
    }

    fun requestPermissions(fragment: androidx.fragment.app.Fragment, permission:Array<String>, requestCode:Int) {
        if (useRunTimePermissions()) {
            fragment.requestPermissions(permission, requestCode);
        }
    }

    fun shouldShowRational( activity:Activity,  permission:String):Boolean {
        if (useRunTimePermissions()) {
            return activity.shouldShowRequestPermissionRationale(permission);
        }
        return false;
    }

    fun shouldAskForPermission( activity:Activity,  permission:String):Boolean {
        if (useRunTimePermissions()) {
            return !hasPermission(activity, permission) &&
                    (!hasAskedForPermission(activity, permission) ||
                            shouldShowRational(activity, permission));
        }
        return false;
    }

    fun goToAppSettings(activity:Activity ) {
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", activity.getPackageName(), null));
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    fun  hasAskedForPermission(activity:Activity ,permission: String ):Boolean {

        return PreferenceManager
            .getDefaultSharedPreferences(activity)
            .getBoolean(permission, false);
    }

    fun markedPermissionAsAsked(activity:Activity, permission:String ) {
        PreferenceManager
            .getDefaultSharedPreferences(activity)
            .edit()
            .putBoolean(permission, true)
            .apply();
    }
}