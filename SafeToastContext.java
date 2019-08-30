package safetoast;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;


/**
 * @author: luke.yujb
 */
public class SafeToastContext extends ContextWrapper {

    private Context mAppContext;

    public SafeToastContext(Context context) {
        super(context.getApplicationContext());
        mAppContext = context.getApplicationContext();
    }

    @Override
    public Context getApplicationContext() {
        return this;
    }

    @Override
    public Object getSystemService(String name) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            if (WINDOW_SERVICE.equals(name)) {
                WindowManager wm = (WindowManager) mAppContext.getSystemService(WINDOW_SERVICE);
                return new WindowManagerWrapper(wm);
            } else if (LAYOUT_INFLATER_SERVICE.equals(name)) {
                LayoutInflater raw = (LayoutInflater) mAppContext.getSystemService(name);
                return raw.cloneInContext(this);
            }
        }
        return super.getSystemService(name);
    }

    private final class WindowManagerWrapper implements WindowManager {

        WindowManager mRaw;

        private WindowManagerWrapper(@NonNull WindowManager wm) {
            this.mRaw = wm;
        }

        @Override
        public Display getDefaultDisplay() {
            return mRaw.getDefaultDisplay();
        }

        @Override
        public void removeViewImmediate(View view) {
            mRaw.removeViewImmediate(view);
        }

        @Override
        public void addView(View view, ViewGroup.LayoutParams params) {
            try {
                mRaw.addView(view, params);
            } catch (Throwable throwable) {
                // do nothing
            }
        }

        @Override
        public void updateViewLayout(View view, ViewGroup.LayoutParams params) {
            mRaw.updateViewLayout(view, params);
        }

        @Override
        public void removeView(View view) {
            mRaw.removeView(view);
        }
    }
}
