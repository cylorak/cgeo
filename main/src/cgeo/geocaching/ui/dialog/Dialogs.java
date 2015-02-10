package cgeo.geocaching.ui.dialog;

import cgeo.geocaching.CgeoApplication;
import cgeo.geocaching.R;
import cgeo.geocaching.settings.Settings;
import cgeo.geocaching.utils.ImageUtils;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.annotation.Nullable;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Wrapper for {@link AlertDialog}. If you want to show a simple text, use one of the
 * {@link #message(Activity, String, String)} variants. If you want the user to confirm using Okay/Cancel or
 * Yes/No, select one of the {@link #confirm(Activity, String, String, String, OnClickListener)} or
 * {@link #confirmYesNo(Activity, String, String, OnClickListener)} variants.
 *
 */
public final class Dialogs {
    private Dialogs() {
        // utility class
    }

    /**
     * Confirm using two buttons "yourText" and "Cancel", where "Cancel" just closes the dialog.
     *
     * @param context
     *            activity hosting the dialog
     * @param title
     *            dialog title
     * @param msg
     *            dialog message
     * @param positiveButton
     *            text of the positive button (which would typically be the OK button)
     * @param okayListener
     *            listener of the positive button
     */
    public static AlertDialog.Builder confirm(final Activity context, final String title, final String msg, final String positiveButton, final OnClickListener okayListener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog dialog = builder.setTitle(title)
                .setCancelable(true)
                .setMessage(msg)
                .setPositiveButton(positiveButton, okayListener)
                .setNegativeButton(android.R.string.cancel, null)
                .create();
        dialog.setOwnerActivity(context);
        dialog.show();
        return builder;
    }

    /**
     * Confirm using two buttons "yourText" and "Cancel", where "Cancel" just closes the dialog.
     *
     * @param context
     *            activity hosting the dialog
     * @param title
     *            dialog title
     * @param msg
     *            dialog message
     * @param positiveButton
     *            text of the positive button (which would typically be the OK button)
     * @param okayListener
     *            listener of the positive button
     */
    public static AlertDialog.Builder confirm(final Activity context, final int title, final int msg, final int positiveButton, final OnClickListener okayListener) {
        return confirm(context, getString(title), getString(msg), getString(positiveButton), okayListener);
    }

    /**
     * Confirm using two buttons "Yes" and "No", where "No" just closes the dialog.
     *
     * @param context
     *            activity hosting the dialog
     * @param title
     *            dialog title
     * @param msg
     *            dialog message
     * @param yesListener
     *            listener of the positive button
     */
    public static AlertDialog.Builder confirmYesNo(final Activity context, final String title, final String msg, final OnClickListener yesListener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog dialog = builder.setTitle(title)
                .setCancelable(true)
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, yesListener)
                .setNegativeButton(android.R.string.no, null)
                .create();
        dialog.setOwnerActivity(context);
        dialog.show();
        return builder;
    }

    /**
     * Confirm using two buttons "Yes" and "No", where "No" just closes the dialog.
     *
     * @param context
     *            activity hosting the dialog
     * @param title
     *            dialog title
     * @param msg
     *            dialog message
     * @param yesListener
     *            listener of the positive button
     */
    public static AlertDialog.Builder confirmYesNo(final Activity context, final String title, final int msg, final OnClickListener yesListener) {
        return confirmYesNo(context, title, getString(msg), yesListener);
    }

    /**
     * Confirm using two buttons "Yes" and "No", where "No" just closes the dialog.
     *
     * @param context
     *            activity hosting the dialog
     * @param title
     *            dialog title
     * @param msg
     *            dialog message
     * @param yesListener
     *            listener of the positive button
     */
    public static AlertDialog.Builder confirmYesNo(final Activity context, final int title, final String msg, final OnClickListener yesListener) {
        return confirmYesNo(context, getString(title), msg, yesListener);
    }

    /**
     * Confirm using two buttons "Yes" and "No", where "No" just closes the dialog.
     *
     * @param context
     *            activity hosting the dialog
     * @param title
     *            dialog title
     * @param msg
     *            dialog message
     * @param yesListener
     *            listener of the positive button
     */
    public static AlertDialog.Builder confirmYesNo(final Activity context, final int title, final int msg, final OnClickListener yesListener) {
        return confirmYesNo(context, getString(title), getString(msg), yesListener);
    }

    /**
     * Confirm using two buttons "OK" and "Cancel", where "Cancel" just closes the dialog.
     *
     * @param context
     *            activity hosting the dialog
     * @param title
     *            dialog title
     * @param msg
     *            dialog message
     * @param okayListener
     *            listener of the positive button
     */
    public static AlertDialog.Builder confirm(final Activity context, final String title, final String msg, final OnClickListener okayListener) {
        return confirm(context, title, msg, getString(android.R.string.ok), okayListener);
    }

    /**
     * Confirm using two buttons "OK" and "Cancel", where "Cancel" just closes the dialog.
     *
     * @param context
     *            activity hosting the dialog
     * @param title
     *            dialog title
     * @param msg
     *            dialog message
     * @param okayListener
     *            listener of the positive button
     */
    public static AlertDialog.Builder confirm(final Activity context, final int title, final String msg, final OnClickListener okayListener) {
        return confirm(context, getString(title), msg, okayListener);
    }

    /**
     * Confirm using two buttons "OK" and "Cancel", where "Cancel" just closes the dialog.
     *
     * @param context
     *            activity hosting the dialog
     * @param title
     *            dialog title
     * @param msg
     *            dialog message
     * @param okayListener
     *            listener of the positive button
     */
    public static AlertDialog.Builder confirm(final Activity context, final int title, final int msg, final OnClickListener okayListener) {
        return confirm(context, getString(title), getString(msg), okayListener);
    }

    private static String getString(final int resourceId) {
        return CgeoApplication.getInstance().getString(resourceId);
    }

    /**
     * Show a message dialog with a single "OK" button.
     *
     * @param context
     *            activity owning the dialog
     * @param message
     *            message dialog content
     */
    public static void message(final Activity context, final String message) {
        message(context, null, message);
    }

    /**
     * Show a message dialog with a single "OK" button.
     *
     * @param context
     *            activity owning the dialog
     * @param message
     *            message dialog content
     */
    public static void message(final Activity context, final int message) {
        message(context, null, getString(message));
    }

    /**
     * Show a message dialog with a single "OK" button.
     *
     * @param context
     *            activity owning the dialog
     * @param title
     *            message dialog title
     * @param message
     *            message dialog content
     */
    public static void message(final Activity context, final @Nullable String title, final String message) {
        message(context, title, message, null);
    }

    /**
     * Show a message dialog with a single "OK" button and an eventual icon.
     *
     * @param context
     *            activity owning the dialog
     * @param title
     *            message dialog title
     * @param message
     *            message dialog content
     * @param iconObservable
     *            observable (may be <tt>null</tt>) containing the icon(s) to set
     */
    public static void message(final Activity context, final @Nullable String title, final String message, final @Nullable Observable<Drawable> iconObservable) {
        final Builder builder = new AlertDialog.Builder(context)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton(getString(android.R.string.ok), null);
        if (title != null) {
            builder.setTitle(title);
        }
        builder.setIcon(ImageUtils.getTransparent1x1Drawable(context.getResources()));

        final AlertDialog dialog = builder.create();
        if (iconObservable != null) {
            iconObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Drawable>() {
                @Override
                public void call(final Drawable drawable) {
                    dialog.setIcon(drawable);
                }
            });
        }
        dialog.show();
    }

    /**
     * Show a message dialog with a single "OK" button and an icon.
     *
     * @param context
     *            activity owning the dialog
     * @param title
     *            message dialog title
     * @param message
     *            message dialog content
     */
    public static void message(final Activity context, final int title, final String message) {
        message(context, getString(title), message);
    }

    /**
     * Show a message dialog with a single "OK" button and an icon.
     *
     * @param context
     *            activity owning the dialog
     * @param title
     *            message dialog title
     * @param message
     *            message dialog content
     */
    public static void message(final Activity context, final int title, final int message) {
        message(context, getString(title), getString(message));
    }

    /**
     * Show a message dialog with a single "OK" button and an icon.
     *
     * @param context
     *            activity owning the dialog
     * @param title
     *            message dialog title
     * @param message
     *            message dialog content
     * @param iconObservable
     *            message dialog title icon
     */
    public static void message(final Activity context, final int title, final int message, final Observable<Drawable> iconObservable) {
        message(context, getString(title), getString(message), iconObservable);
    }

    /**
     * Show a message dialog for input from the user. The okay button is only enabled on non empty input.
     *
     * @param context
     *            activity owning the dialog
     * @param title
     *            message dialog title
     * @param defaultValue
     *            default input value
     * @param buttonTitle
     *            title of the okay button
     * @param okayListener
     *            listener to be run on okay
     */
    public static void input(final Activity context, final int title, final String defaultValue, final int buttonTitle, final Action1<String> okayListener) {
        final Context themedContext;

        if (Settings.isLightSkin() && VERSION.SDK_INT < VERSION_CODES.HONEYCOMB) {
            themedContext = new ContextThemeWrapper(context, R.style.dark);
        } else {
            themedContext = context;
        }

        final EditText input = new EditText(themedContext);
        input.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | InputType.TYPE_CLASS_TEXT);
        input.setText(defaultValue);

        final AlertDialog.Builder builder = new AlertDialog.Builder(themedContext);
        builder.setTitle(title);
        builder.setView(input);
        builder.setPositiveButton(buttonTitle, new OnClickListener() {

            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                okayListener.call(input.getText().toString());
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int whichButton) {
                dialog.dismiss();
            }
        });
        final AlertDialog dialog = builder.create();

        input.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                // empty
            }

            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
                // empty
            }

            @Override
            public void afterTextChanged(final Editable editable) {
                enableDialogButtonIfNotEmpty(dialog, editable.toString());
            }
        });
        // force keyboard
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        // disable button
        dialog.show();
        enableDialogButtonIfNotEmpty(dialog, defaultValue);

        moveCursorToEnd(input);
    }

    /**
     * Move the cursor to the end of the input field.
     *
     */
    public static void moveCursorToEnd(final EditText input) {
        input.setSelection(input.getText().length(), input.getText().length());
    }

    private static void enableDialogButtonIfNotEmpty(final AlertDialog dialog, final String input) {
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(StringUtils.isNotBlank(input));
    }

    public static interface ItemWithIcon {
        /**
         * @return the drawable
         */
        int getIcon();
    }

    public static <T extends ItemWithIcon> void select(final Activity activity, final String title, final List<T> items, final Action1<T> listener) {
        final ListAdapter adapter = new ArrayAdapter<T>(
                activity,
                android.R.layout.select_dialog_item,
                android.R.id.text1,
                items) {
            @Override
            public View getView(final int position, final View convertView, final ViewGroup parent) {
                // standard list entry
                final View v = super.getView(position, convertView, parent);

                // add image
                final TextView tv = (TextView) v.findViewById(android.R.id.text1);
                tv.setCompoundDrawablesWithIntrinsicBounds(items.get(position).getIcon(), 0, 0, 0);

                // Add margin between image and text
                final int dp5 = (int) (5 * activity.getResources().getDisplayMetrics().density + 0.5f);
                tv.setCompoundDrawablePadding(dp5);

                return v;
            }
        };

        new AlertDialog.Builder(activity)
                .setTitle(title)
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int item) {
                        listener.call(items.get(item));
                    }
                }).show();
    }
}
