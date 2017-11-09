package com.intimetec.conferencemanager.ui.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.intimetec.conferencemanager.R;
import com.intimetec.conferencemanager.data.UserDataSource;
import com.intimetec.conferencemanager.data.remote.UserRemoteDataSource;
import com.intimetec.conferencemanager.model.user.User;
import com.intimetec.conferencemanager.util.schedulers.BaseSchedulerProvider;
import com.intimetec.conferencemanager.util.schedulers.ImmediateSchedulerProvider;

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
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email_login)
    EditText mEmailEditTxt;

    @BindView(R.id.password_login)
    EditText mPasswordEditTxt;

    @BindView(R.id.btn_login)
    Button mSignInBtn;

    private ProgressDialog mProgressDialog;

    @NonNull
    private BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private UserDataSource mUserDataSource;

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mProgressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Authenticating...");

        mSchedulerProvider = checkNotNull(new ImmediateSchedulerProvider(), "schedulerProvider cannot be null");
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

        if (TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailEditTxt.setError("enter a valid email address");
            valid = false;
        } else {
            mEmailEditTxt.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            mPasswordEditTxt.setError("minimum 4 characters");
            valid = false;
        } else {
            mPasswordEditTxt.setError(null);
        }
        return valid;
    }

    private void login(String email, String password) {
        mCompositeDisposable.clear();
        showProgress();

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
                        () -> hideProgress()));

        /*mUserDataSource
                .getUser(email, password)
                .observeOn(mSchedulerProvider.io())
                .subscribeOn(mSchedulerProvider.ui())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                        showProgress();
                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull User user) {
                        onLoginSuccess();
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        onLoginFailed();
                    }

                    @Override
                    public void onComplete() {

                    }
                });*/

    }

    private void onLoginSuccess(User user) {
        hideProgress();
        mSignInBtn.setEnabled(true);
        Toast.makeText(LoginActivity.this, user.getMessage(), Toast.LENGTH_LONG).show();
    }

    private void onLoginFailed(@NonNull Throwable throwable) {
        hideProgress();
        mSignInBtn.setEnabled(true);
        Toast.makeText(LoginActivity.this, throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }

    private void showProgress() {
        mProgressDialog.show();
    }

    private void hideProgress() {
        mProgressDialog.hide();
    }

}

