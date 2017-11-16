package com.intimetec.conferencemanager.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.intimetec.conferencemanager.R;
import com.intimetec.conferencemanager.constant.PreferenceConstant;
import com.intimetec.conferencemanager.data.UserDataSource;
import com.intimetec.conferencemanager.data.remote.UserRemoteDataSource;
import com.intimetec.conferencemanager.model.user.User;
import com.intimetec.conferencemanager.preference.PreferenceManager;
import com.intimetec.conferencemanager.ui.BaseActivity;
import com.intimetec.conferencemanager.ui.booking.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.email_login)
    EditText mEmailEditTxt;

    @BindView(R.id.password_login)
    EditText mPasswordEditTxt;

    @BindView(R.id.btn_login)
    Button mSignInBtn;

    @NonNull
    private UserDataSource mUserDataSource;

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mUserDataSource = checkNotNull(UserRemoteDataSource.getInstance(), "userDataSource cannot be null");
        mCompositeDisposable = new CompositeDisposable();

    }

    @OnClick(R.id.btn_login)
    void submitLogin() {
        String email = mEmailEditTxt.getText().toString();
        String password = mPasswordEditTxt.getText().toString();

        if (validateLogin(email, password)) {
            mSignInBtn.setEnabled(false);
            login(email, password);
        }
    }

    public boolean validateLogin(String email, String password) {
        boolean valid = true;

        if (TextUtils.isEmpty(email)) {
            mEmailEditTxt.setError(getString(R.string.error_field_required));
            valid = false;
        } else {
            mEmailEditTxt.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            mPasswordEditTxt.setError(getString(R.string.error_invalid_password));
            valid = false;
        } else {
            mPasswordEditTxt.setError(null);
        }
        return valid;
    }

    private void login(String email, String password) {
        mCompositeDisposable.clear();
        showProgressDialog();

        mCompositeDisposable.add(mUserDataSource
                .getUser(email, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        // onNext
                        this::onLoginSuccess,
                        // onError
                        this::onLoginFailed,
                        // onCompleted
                        () -> hideProgressDialog()));

    }

    private void onLoginSuccess(User user) {
        mSignInBtn.setEnabled(true);
        Toast.makeText(LoginActivity.this, user.getMessage(), Toast.LENGTH_LONG).show();

        PreferenceManager.saveObject(LoginActivity.this, PreferenceConstant.USER_KEY, user);
        PreferenceManager.setFlag(LoginActivity.this, PreferenceConstant.HAS_LOGIN, true);
        sendToHomeActivity();
    }

    private void onLoginFailed(@NonNull Throwable throwable) {
        mSignInBtn.setEnabled(true);
        Toast.makeText(LoginActivity.this, throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }

    private void sendToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}

