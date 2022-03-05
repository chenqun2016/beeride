package com.bee.rider.jpush

import android.content.Context
import android.content.Intent
import android.util.Log.e
import cn.jpush.android.api.*
import cn.jpush.android.service.JPushMessageReceiver
import com.bee.rider.bean.JPushBean
import com.google.gson.Gson

/**
 - @Description: 
 - @Author: bxy
 - @Time:  2022/3/5 下午6:22
 */
class PushMessageReceiver : JPushMessageReceiver() {
 override fun onMessage(context: Context, customMessage: CustomMessage) {
  e(TAG, "[onMessage] $customMessage")
  processCustomMessage(context, customMessage)
 }

 override fun onNotifyMessageOpened(context: Context?, message: NotificationMessage) {
  e(TAG, "[onNotifyMessageOpened] $message")
  try {
   val notificationExtras = message.notificationExtras
   val jPushBean: JPushBean = Gson().fromJson(notificationExtras, JPushBean::class.java)
  } catch (e: Exception) {
   e.printStackTrace()
  }
 }

 override fun onMultiActionClicked(context: Context?, intent: Intent) {
  e(TAG, "[onMultiActionClicked] 用户点击了通知栏按钮")
  val nActionExtra = intent.extras!!.getString(JPushInterface.EXTRA_NOTIFICATION_ACTION_EXTRA)

  //开发者根据不同 Action 携带的 extra 字段来分配不同的动作。
  if (nActionExtra == null) {
   e(TAG, "ACTION_NOTIFICATION_CLICK_ACTION nActionExtra is null")
   return
  }
  when (nActionExtra) {
      "my_extra1" -> {
       e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮一")
      }
      "my_extra2" -> {
       e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮二")
      }
      "my_extra3" -> {
       e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮三")
      }
      else -> {
       e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮未定义")
      }
  }
 }

 override fun onNotifyMessageArrived(context: Context?, message: NotificationMessage) {
  e(TAG, "[onNotifyMessageArrived] $message")
 }

 override fun onNotifyMessageDismiss(context: Context?, message: NotificationMessage) {
  e(TAG, "[onNotifyMessageDismiss] $message")
 }

 override fun onRegister(context: Context?, registrationId: String) {
  e(TAG, "[onRegister] $registrationId")
  // SPUtils.getInstance().put(Constant.REGISTRATIONID, registrationId);
 }

 override fun onConnected(context: Context?, isConnected: Boolean) {
  e(TAG, "[onConnected] $isConnected")
 }

 override fun onCommandResult(context: Context?, cmdMessage: CmdMessage) {
  e(TAG, "[onCommandResult] $cmdMessage")
 }

 override fun onTagOperatorResult(context: Context?, jPushMessage: JPushMessage?) {
  //TagAliasOperatorHelper.getInstance().onTagOperatorResult(context,jPushMessage);
  super.onTagOperatorResult(context, jPushMessage)
 }

 override fun onCheckTagOperatorResult(context: Context?, jPushMessage: JPushMessage?) {
  //TagAliasOperatorHelper.getInstance().onCheckTagOperatorResult(context,jPushMessage);
  super.onCheckTagOperatorResult(context, jPushMessage)
 }

 override fun onAliasOperatorResult(context: Context?, jPushMessage: JPushMessage?) {
  //TagAliasOperatorHelper.getInstance().onAliasOperatorResult(context,jPushMessage);
  super.onAliasOperatorResult(context, jPushMessage)
 }

 override fun onMobileNumberOperatorResult(context: Context?, jPushMessage: JPushMessage?) {
  //TagAliasOperatorHelper.getInstance().onMobileNumberOperatorResult(context,jPushMessage);
  super.onMobileNumberOperatorResult(context, jPushMessage)
 }

 //send msg to MainActivity
 private fun processCustomMessage(context: Context, customMessage: CustomMessage) {
  val message = customMessage.message
  val extras = customMessage.extra
  e(TAG, "[onCommandResult] $message")
  //        if (MainActivity.isForeground) {
//            String message = customMessage.message;
//            String extras = customMessage.extra;
//            Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
//            msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
//            if (!ExampleUtil.isEmpty(extras)) {
//                try {
//                    JSONObject extraJson = new JSONObject(extras);
//                    if (extraJson.length() > 0) {
//                        msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
//                    }
//                } catch (JSONException e) {
//
//                }
//
//            }
//            LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
//        }
 }

 override fun onNotificationSettingsCheck(context: Context?, isOn: Boolean, source: Int) {
  super.onNotificationSettingsCheck(context, isOn, source)
  e(TAG, "[onNotificationSettingsCheck] isOn:$isOn,source:$source"
  )
 }

 companion object {
  private const val TAG = "PushMessageReceiver"
 }
}