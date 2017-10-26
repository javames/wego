
************* Crash Log Head ****************
Device Manufacturer: HUAWEI
Device Model       : PLK-AL10
Android Version    : 6.0
Android SDK        : 23
App VersionName    : 1.0
App VersionCode    : 1
************* Crash Log Head ****************

java.lang.RuntimeException: Unable to start activity ComponentInfo{demo.com.demo/demo.com.demo.ClockActivity}: java.lang.NullPointerException: Attempt to invoke virtual method 'java.lang.String[] java.lang.String.split(java.lang.String)' on a null object reference
	at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2451)
	at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2511)
	at android.app.ActivityThread.access$900(ActivityThread.java:165)
	at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1375)
	at android.os.Handler.dispatchMessage(Handler.java:102)
	at android.os.Looper.loop(Looper.java:150)
	at android.app.ActivityThread.main(ActivityThread.java:5621)
	at java.lang.reflect.Method.invoke(Native Method)
	at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:794)
	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:684)
Caused by: java.lang.NullPointerException: Attempt to invoke virtual method 'java.lang.String[] java.lang.String.split(java.lang.String)' on a null object reference
	at demo.com.demo.ClockActivity.onCreate(ClockActivity.java:60)
	at android.app.Activity.performCreate(Activity.java:6367)
	at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1110)
	at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2404)
	... 9 more
java.lang.NullPointerException: Attempt to invoke virtual method 'java.lang.String[] java.lang.String.split(java.lang.String)' on a null object reference
	at demo.com.demo.ClockActivity.onCreate(ClockActivity.java:60)
	at android.app.Activity.performCreate(Activity.java:6367)
	at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1110)
	at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2404)
	at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2511)
	at android.app.ActivityThread.access$900(ActivityThread.java:165)
	at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1375)
	at android.os.Handler.dispatchMessage(Handler.java:102)
	at android.os.Looper.loop(Looper.java:150)
	at android.app.ActivityThread.main(ActivityThread.java:5621)
	at java.lang.reflect.Method.invoke(Native Method)
	at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:794)
	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:684)

	
	
	
	
	                                   .demo I/test:  standardDate= null
10-20 09:30:03.930 29450-29450/demo.com.demo I/test:  今天时间戳 = 1508512830000
10-20 09:30:03.931 29450-29450/demo.com.demo I/test:  明天时间戳 = 1508552430000
10-20 09:30:03.933 29450-29450/demo.com.demo I/test:  后天时间戳 = 1508638830000
10-20 09:30:03.938 29450-29450/demo.com.demo I/test:  今天 = today,23:20:30
10-20 09:30:03.938 29450-29450/demo.com.demo I/test:  split = today
10-20 09:30:03.938 29450-29450/demo.com.demo I/test:  split = 23:20:30
10-20 09:30:03.938 29450-29450/demo.com.demo I/test:  split = tomorrow
10-20 09:30:03.938 29450-29450/demo.com.demo I/test:  split = 10:20:30
10-20 09:30:03.939 29450-29450/demo.com.demo I/test:  split = else
10-20 09:30:03.939 29450-29450/demo.com.demo I/test:  split = 2017-10-22 10:20:30
10-20 09:30:03.939 29450-29450/demo.com.demo I/test:  明天 = tomorrow,10:20:30
10-20 09:30:03.939 29450-29450/demo.com.demo I/test:  后天 = else,2017-10-22 10:20:30