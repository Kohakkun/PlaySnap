// Generated by view binder compiler. Do not edit!
package com.example.playsnapui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.playsnapui.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatButton btnBack;

  @NonNull
  public final TextInputEditText etConfirmpass;

  @NonNull
  public final TextInputEditText etEmail;

  @NonNull
  public final TextInputEditText etFullname;

  @NonNull
  public final TextInputEditText etPassword;

  @NonNull
  public final TextInputEditText etUsername;

  @NonNull
  public final ImageView icAccount;

  @NonNull
  public final ImageView icAccountRectangle;

  @NonNull
  public final ImageView icConfirmpass;

  @NonNull
  public final ImageView icLock;

  @NonNull
  public final ImageView icPassword;

  @NonNull
  public final TextView masuk;

  @NonNull
  public final AppCompatButton registerButton;

  @NonNull
  public final TextView registerHaveAccount;

  @NonNull
  public final TextInputLayout tilConfirmpass;

  @NonNull
  public final TextInputLayout tilEmail;

  @NonNull
  public final TextInputLayout tilFullname;

  @NonNull
  public final TextInputLayout tilPassword;

  @NonNull
  public final TextInputLayout tilUsername;

  @NonNull
  public final TextView tvRegisterSubtitle;

  @NonNull
  public final TextView tvRegisterTitle;

  private ActivityRegisterBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatButton btnBack, @NonNull TextInputEditText etConfirmpass,
      @NonNull TextInputEditText etEmail, @NonNull TextInputEditText etFullname,
      @NonNull TextInputEditText etPassword, @NonNull TextInputEditText etUsername,
      @NonNull ImageView icAccount, @NonNull ImageView icAccountRectangle,
      @NonNull ImageView icConfirmpass, @NonNull ImageView icLock, @NonNull ImageView icPassword,
      @NonNull TextView masuk, @NonNull AppCompatButton registerButton,
      @NonNull TextView registerHaveAccount, @NonNull TextInputLayout tilConfirmpass,
      @NonNull TextInputLayout tilEmail, @NonNull TextInputLayout tilFullname,
      @NonNull TextInputLayout tilPassword, @NonNull TextInputLayout tilUsername,
      @NonNull TextView tvRegisterSubtitle, @NonNull TextView tvRegisterTitle) {
    this.rootView = rootView;
    this.btnBack = btnBack;
    this.etConfirmpass = etConfirmpass;
    this.etEmail = etEmail;
    this.etFullname = etFullname;
    this.etPassword = etPassword;
    this.etUsername = etUsername;
    this.icAccount = icAccount;
    this.icAccountRectangle = icAccountRectangle;
    this.icConfirmpass = icConfirmpass;
    this.icLock = icLock;
    this.icPassword = icPassword;
    this.masuk = masuk;
    this.registerButton = registerButton;
    this.registerHaveAccount = registerHaveAccount;
    this.tilConfirmpass = tilConfirmpass;
    this.tilEmail = tilEmail;
    this.tilFullname = tilFullname;
    this.tilPassword = tilPassword;
    this.tilUsername = tilUsername;
    this.tvRegisterSubtitle = tvRegisterSubtitle;
    this.tvRegisterTitle = tvRegisterTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_back;
      AppCompatButton btnBack = ViewBindings.findChildViewById(rootView, id);
      if (btnBack == null) {
        break missingId;
      }

      id = R.id.et_confirmpass;
      TextInputEditText etConfirmpass = ViewBindings.findChildViewById(rootView, id);
      if (etConfirmpass == null) {
        break missingId;
      }

      id = R.id.et_email;
      TextInputEditText etEmail = ViewBindings.findChildViewById(rootView, id);
      if (etEmail == null) {
        break missingId;
      }

      id = R.id.et_fullname;
      TextInputEditText etFullname = ViewBindings.findChildViewById(rootView, id);
      if (etFullname == null) {
        break missingId;
      }

      id = R.id.et_password;
      TextInputEditText etPassword = ViewBindings.findChildViewById(rootView, id);
      if (etPassword == null) {
        break missingId;
      }

      id = R.id.et_username;
      TextInputEditText etUsername = ViewBindings.findChildViewById(rootView, id);
      if (etUsername == null) {
        break missingId;
      }

      id = R.id.ic_account;
      ImageView icAccount = ViewBindings.findChildViewById(rootView, id);
      if (icAccount == null) {
        break missingId;
      }

      id = R.id.ic_account_rectangle;
      ImageView icAccountRectangle = ViewBindings.findChildViewById(rootView, id);
      if (icAccountRectangle == null) {
        break missingId;
      }

      id = R.id.ic_confirmpass;
      ImageView icConfirmpass = ViewBindings.findChildViewById(rootView, id);
      if (icConfirmpass == null) {
        break missingId;
      }

      id = R.id.ic_lock;
      ImageView icLock = ViewBindings.findChildViewById(rootView, id);
      if (icLock == null) {
        break missingId;
      }

      id = R.id.ic_password;
      ImageView icPassword = ViewBindings.findChildViewById(rootView, id);
      if (icPassword == null) {
        break missingId;
      }

      id = R.id.masuk;
      TextView masuk = ViewBindings.findChildViewById(rootView, id);
      if (masuk == null) {
        break missingId;
      }

      id = R.id.registerButton;
      AppCompatButton registerButton = ViewBindings.findChildViewById(rootView, id);
      if (registerButton == null) {
        break missingId;
      }

      id = R.id.register_have_account;
      TextView registerHaveAccount = ViewBindings.findChildViewById(rootView, id);
      if (registerHaveAccount == null) {
        break missingId;
      }

      id = R.id.til_confirmpass;
      TextInputLayout tilConfirmpass = ViewBindings.findChildViewById(rootView, id);
      if (tilConfirmpass == null) {
        break missingId;
      }

      id = R.id.til_email;
      TextInputLayout tilEmail = ViewBindings.findChildViewById(rootView, id);
      if (tilEmail == null) {
        break missingId;
      }

      id = R.id.til_fullname;
      TextInputLayout tilFullname = ViewBindings.findChildViewById(rootView, id);
      if (tilFullname == null) {
        break missingId;
      }

      id = R.id.til_password;
      TextInputLayout tilPassword = ViewBindings.findChildViewById(rootView, id);
      if (tilPassword == null) {
        break missingId;
      }

      id = R.id.til_username;
      TextInputLayout tilUsername = ViewBindings.findChildViewById(rootView, id);
      if (tilUsername == null) {
        break missingId;
      }

      id = R.id.tv_register_subtitle;
      TextView tvRegisterSubtitle = ViewBindings.findChildViewById(rootView, id);
      if (tvRegisterSubtitle == null) {
        break missingId;
      }

      id = R.id.tv_register_title;
      TextView tvRegisterTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvRegisterTitle == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((ConstraintLayout) rootView, btnBack, etConfirmpass,
          etEmail, etFullname, etPassword, etUsername, icAccount, icAccountRectangle, icConfirmpass,
          icLock, icPassword, masuk, registerButton, registerHaveAccount, tilConfirmpass, tilEmail,
          tilFullname, tilPassword, tilUsername, tvRegisterSubtitle, tvRegisterTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
