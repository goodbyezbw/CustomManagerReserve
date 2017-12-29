package com.jald.reserve.widget.pullrefresh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.jald.reserve.R;

public class FooterLoadingLayout extends LoadingLayout {

	private ProgressBar mProgressBar;
	private TextView mHintView;

	public FooterLoadingLayout(Context context) {
		super(context);
		init(context);
	}

	public FooterLoadingLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		mProgressBar = (ProgressBar) findViewById(R.id.pull_to_load_footer_progressbar);
		mHintView = (TextView) findViewById(R.id.pull_to_load_footer_hint_textview);
		setState(State.RESET);
	}

	@SuppressLint("InflateParams")
	@Override
	protected View createLoadingView(Context context, AttributeSet attrs) {
		View container = LayoutInflater.from(context).inflate(R.layout.pull_to_load_footer, null);
		return container;
	}

	@Override
	public void setLastUpdatedLabel(CharSequence label) {
	}

	@Override
	public int getContentSize() {
		View view = findViewById(R.id.pull_to_load_footer_content);
		if (null != view) {
			return view.getHeight();
		}
		return (int) (getResources().getDisplayMetrics().density * 40);
	}

	@Override
	protected void onStateChanged(State curState, State oldState) {
		mProgressBar.setVisibility(View.GONE);
		mHintView.setVisibility(View.INVISIBLE);
		super.onStateChanged(curState, oldState);
	}

	@Override
	protected void onReset() {
		mHintView.setText(R.string.pull_to_refresh_header_hint_loading);
	}

	@Override
	protected void onPullToRefresh() {
		mHintView.setVisibility(View.VISIBLE);
		mHintView.setText(R.string.pull_to_refresh_footer_hint_normal2);
	}

	@Override
	protected void onReleaseToRefresh() {
		mHintView.setVisibility(View.VISIBLE);
		mHintView.setText(R.string.pull_to_refresh_footer_hint_ready);
	}

	@Override
	protected void onRefreshing() {
		mProgressBar.setVisibility(View.VISIBLE);
		mHintView.setVisibility(View.VISIBLE);
		mHintView.setText(R.string.pull_to_refresh_header_hint_loading);
	}

	@Override
	protected void onNoMoreData() {
		mHintView.setVisibility(View.VISIBLE);
		mHintView.setText(R.string.pushmsg_center_no_more_msg);
	}
}
