package bosowa.hris.cordova;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings.Secure;
import android.util.Log;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.ArrayList;

public class MockChecker extends CordovaPlugin {

  private int MY_PERMISSIONS_REQUEST = 0;

  private JSONArray arrayGPS = new JSONArray();
  private JSONArray indicated = new JSONArray();
  private JSONObject objGPS = new JSONObject();
  private bosowa.hris.cordova.MockChecker mContext;

  @Override
  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    mContext = this;
    if (action.equals("check")) {
      objGPS = new JSONObject();
      if (android.os.Build.VERSION.SDK_INT <= 22) {
        if (Secure.getString(this.cordova.getActivity().getContentResolver(), Secure.ALLOW_MOCK_LOCATION).equals("0")) {
          objGPS.put("isMock", false);
        } else {
          objGPS.put("isMock", true);
          objGPS.put("messages", "Please turn off Allow Mock locations option in developer options.");
        }

      } else {
        ArrayList<String> listdata = new ArrayList<String>();
        JSONArray jArray = args.getJSONArray(0);
        if (jArray != null) {
          for (int i = 0; i < jArray.length(); i++) {
            listdata.add(jArray.getString(i));
          }
        }

        objGPS.put("isMock", areThereMockPermissionApps(mContext.cordova.getActivity(), listdata));
        if (objGPS.getBoolean("isMock")) {
          objGPS.put("messages",
              "We've detected that there are other apps in the device, which are using Mock Location access (Location Spoofing Apps). Please uninstall first.");
          objGPS.put("indicated", "nahal");
        }
      }
      Log.i("Location", "isMock: " + objGPS.get("isMock"));
      callbackContext.success(objGPS);
      return true;
    } else {
      return false;
    }

  }

  public boolean areThereMockPermissionApps(Context context, ArrayList<String> listdata) {
    int count = 0;

    PackageManager pm = context.getPackageManager();
    List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

    for (ApplicationInfo applicationInfo : packages) {
      try {
        PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);

        // Get Permissions
        String[] requestedPermissions = packageInfo.requestedPermissions;

        if (requestedPermissions != null) {
          for (int i = 0; i < requestedPermissions.length; i++) {
            // Check for System App //
            if (!((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1)) {
              if (requestedPermissions[i].equals("android.permission.ACCESS_MOCK_LOCATION")
                  && !applicationInfo.packageName.equals(context.getPackageName())) {
                // indicated.put(applicationInfo.packageName);
                count++;
              }
            }
          }
        }
      } catch (PackageManager.NameNotFoundException e) {
        Log.e("Got exception ", e.getMessage());
      }
    }

    if (count > 0)
      return true;
    return false;
  }

}
