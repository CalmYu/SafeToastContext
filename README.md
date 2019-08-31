# SafeToastContext
A ContextWrapper to solve Toast BadTokenException on devices below Android O.

## Crash Stacktrace:
<pre>
android.view.WindowManager$BadTokenException
Unable to add window -- token android.os.BinderProxy@3d9b3d1 is not valid; is your activity running?
at android.view.ViewRootImpl.setView(ViewRootImpl.java:811)
at android.view.WindowManagerGlobal.addView(WindowManagerGlobal.java:353)
at android.view.WindowManagerImpl.addView(WindowManagerImpl.java:93)
at android.widget.Toast$TN.handleShow(Toast.java:465)
at android.widget.Toast$TN$2.handleMessage(Toast.java:347)
at android.os.Handler.dispatchMessage(Handler.java:110)
at android.os.Looper.loop(Looper.java:203)
at android.app.ActivityThread.main(ActivityThread.java:6385)
at java.lang.reflect.Method.invoke(Native Method)
at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:1106)
at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:967)
</pre>

## Usage
1. Copy the SafeToastContext.java to your project
2. Use SafeToastContext to show Toast instead.
<br/>
<pre>
// normal text toast
Toast.makeText(new SafeToastContext(context), s, Toast.LENGTH_SHORT).show();
<br/>
// custom view toast
Context context = new SafeToastContext(context);
View content = LayoutInflater.from(context).inflate(R.layout.xxx, null);
Toast toast = new Toast(context);
toast.setView(layout);
toast.setDuration(Toast.LENGTH_SHORT);
toast.show();
</pre>
