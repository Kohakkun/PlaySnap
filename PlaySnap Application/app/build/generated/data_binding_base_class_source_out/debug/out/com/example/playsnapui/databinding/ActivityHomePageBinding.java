// Generated by view binder compiler. Do not edit!
package com.example.playsnapui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.playsnapui.R;
import com.google.android.material.textfield.TextInputEditText;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityHomePageBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ImageView banner;

  @NonNull
  public final AppCompatButton btnFilterGame;

  @NonNull
  public final AppCompatButton btnScanObject;

  @NonNull
  public final AppCompatButton btnTypeObject;

  @NonNull
  public final TextInputEditText etSearchGame;

  @NonNull
  public final ActivityNavigationBarBinding include;

  @NonNull
  public final RecyclerView recentRecyclerForyou;

  @NonNull
  public final RecyclerView recentRecyclerPopgame;

  @NonNull
  public final TextView tvSubtitleHome;

  @NonNull
  public final TextView tvSubtitleHome2;

  @NonNull
  public final TextView tvTitleForyou;

  @NonNull
  public final TextView tvTitleGreeting;

  @NonNull
  public final TextView tvTitleName;

  @NonNull
  public final TextView tvTitleRecommendation;

  private ActivityHomePageBinding(@NonNull RelativeLayout rootView, @NonNull ImageView banner,
      @NonNull AppCompatButton btnFilterGame, @NonNull AppCompatButton btnScanObject,
      @NonNull AppCompatButton btnTypeObject, @NonNull TextInputEditText etSearchGame,
      @NonNull ActivityNavigationBarBinding include, @NonNull RecyclerView recentRecyclerForyou,
      @NonNull RecyclerView recentRecyclerPopgame, @NonNull TextView tvSubtitleHome,
      @NonNull TextView tvSubtitleHome2, @NonNull TextView tvTitleForyou,
      @NonNull TextView tvTitleGreeting, @NonNull TextView tvTitleName,
      @NonNull TextView tvTitleRecommendation) {
    this.rootView = rootView;
    this.banner = banner;
    this.btnFilterGame = btnFilterGame;
    this.btnScanObject = btnScanObject;
    this.btnTypeObject = btnTypeObject;
    this.etSearchGame = etSearchGame;
    this.include = include;
    this.recentRecyclerForyou = recentRecyclerForyou;
    this.recentRecyclerPopgame = recentRecyclerPopgame;
    this.tvSubtitleHome = tvSubtitleHome;
    this.tvSubtitleHome2 = tvSubtitleHome2;
    this.tvTitleForyou = tvTitleForyou;
    this.tvTitleGreeting = tvTitleGreeting;
    this.tvTitleName = tvTitleName;
    this.tvTitleRecommendation = tvTitleRecommendation;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityHomePageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityHomePageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_home_page, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityHomePageBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.banner;
      ImageView banner = ViewBindings.findChildViewById(rootView, id);
      if (banner == null) {
        break missingId;
      }

      id = R.id.btn_filter_game;
      AppCompatButton btnFilterGame = ViewBindings.findChildViewById(rootView, id);
      if (btnFilterGame == null) {
        break missingId;
      }

      id = R.id.btn_scan_object;
      AppCompatButton btnScanObject = ViewBindings.findChildViewById(rootView, id);
      if (btnScanObject == null) {
        break missingId;
      }

      id = R.id.btn_type_object;
      AppCompatButton btnTypeObject = ViewBindings.findChildViewById(rootView, id);
      if (btnTypeObject == null) {
        break missingId;
      }

      id = R.id.et_search_game;
      TextInputEditText etSearchGame = ViewBindings.findChildViewById(rootView, id);
      if (etSearchGame == null) {
        break missingId;
      }

      id = R.id.include;
      View include = ViewBindings.findChildViewById(rootView, id);
      if (include == null) {
        break missingId;
      }
      ActivityNavigationBarBinding binding_include = ActivityNavigationBarBinding.bind(include);

      id = R.id.recent_recycler_foryou;
      RecyclerView recentRecyclerForyou = ViewBindings.findChildViewById(rootView, id);
      if (recentRecyclerForyou == null) {
        break missingId;
      }

      id = R.id.recent_recycler_popgame;
      RecyclerView recentRecyclerPopgame = ViewBindings.findChildViewById(rootView, id);
      if (recentRecyclerPopgame == null) {
        break missingId;
      }

      id = R.id.tv_subtitle_home;
      TextView tvSubtitleHome = ViewBindings.findChildViewById(rootView, id);
      if (tvSubtitleHome == null) {
        break missingId;
      }

      id = R.id.tv_subtitle_home2;
      TextView tvSubtitleHome2 = ViewBindings.findChildViewById(rootView, id);
      if (tvSubtitleHome2 == null) {
        break missingId;
      }

      id = R.id.tv_title_foryou;
      TextView tvTitleForyou = ViewBindings.findChildViewById(rootView, id);
      if (tvTitleForyou == null) {
        break missingId;
      }

      id = R.id.tv_title_greeting;
      TextView tvTitleGreeting = ViewBindings.findChildViewById(rootView, id);
      if (tvTitleGreeting == null) {
        break missingId;
      }

      id = R.id.tv_title_name;
      TextView tvTitleName = ViewBindings.findChildViewById(rootView, id);
      if (tvTitleName == null) {
        break missingId;
      }

      id = R.id.tv_title_recommendation;
      TextView tvTitleRecommendation = ViewBindings.findChildViewById(rootView, id);
      if (tvTitleRecommendation == null) {
        break missingId;
      }

      return new ActivityHomePageBinding((RelativeLayout) rootView, banner, btnFilterGame,
          btnScanObject, btnTypeObject, etSearchGame, binding_include, recentRecyclerForyou,
          recentRecyclerPopgame, tvSubtitleHome, tvSubtitleHome2, tvTitleForyou, tvTitleGreeting,
          tvTitleName, tvTitleRecommendation);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
