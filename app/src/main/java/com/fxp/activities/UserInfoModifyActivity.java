package com.fxp.activities;

import com.example.handlerthreadtest.R;
import com.fxp.entity.User;
import com.fxp.manager.UserInfoManager;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserInfoModifyActivity extends Activity {
	private EditText et_userinfo_modify_name,
				et_userinfo_modify_sex,
				et_userinfo_modify_phone,
				et_userinfo_modify_address;
	private Button btn_userinfo_modify_submit;
	private User user;
	private UserInfoManager userInfoManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		user=(User) this.getIntent().getExtras().getSerializable("user");
		Log.i("test",user.toString());
		setContentView(R.layout.activity_userinfomodify);
		userInfoManager=new UserInfoManager(getContentResolver());
		findViews();
		initViews();
	}
	private void initViews() {
		if(TextUtils.isEmpty(user.getName())){
			et_userinfo_modify_name.setHint("未设置");
			user.setName("");
		}else{
			et_userinfo_modify_name.setText(user.getName());
		}
		if(-1==user.getSex()){
			et_userinfo_modify_sex.setHint("未设置");
		}else{
			et_userinfo_modify_sex.setText(user.getSex()==User.ISMALE?"男":"女");
		}
		if(TextUtils.isEmpty(user.getPhone())){
			et_userinfo_modify_phone.setHint("未设置");
			user.setPhone("");
		}else{
			et_userinfo_modify_phone.setText(user.getPhone());
		}
		if(TextUtils.isEmpty(user.getAddress())){
			et_userinfo_modify_address.setHint("未设置");
			user.setAddress("");
		}else{
			et_userinfo_modify_address.setText(user.getAddress());
		}
	}

	public void onClick(View v){
		switch (v.getId()) {
		case R.id.btn_userinfo_modify_submit:
			if(null==userInfoManager){
				userInfoManager=new UserInfoManager(getContentResolver());
			}
			setEditTextStatusToUser();
			userInfoManager.updateUserInfo(user);
			break;

		default:
			break;
		}
	}

	private void setEditTextStatusToUser() {
		if(!TextUtils.isEmpty(et_userinfo_modify_name.getText().toString().trim())){
			user.setName(et_userinfo_modify_name.getText().toString().trim());
		}

		if(!TextUtils.isEmpty(et_userinfo_modify_sex.getText().toString().trim())){
			if(et_userinfo_modify_sex.getText().toString().trim().equals("男")){
				user.setSex(User.ISMALE);
			}
			if(et_userinfo_modify_sex.getText().toString().trim().equals("女")){
				user.setSex(User.ISFEMALE);
			}
		}
		if(!TextUtils.isEmpty(et_userinfo_modify_phone.getText().toString().trim())){
			user.setPhone(et_userinfo_modify_phone.getText().toString().trim());
		}
		if(!TextUtils.isEmpty(et_userinfo_modify_address.getText().toString().trim())){
			user.setAddress(et_userinfo_modify_address.getText().toString().trim());
		}
	}

	private void findViews() {
		et_userinfo_modify_name=(EditText) findViewById(R.id.et_userinfo_modify_name);
		et_userinfo_modify_sex=(EditText) findViewById(R.id.et_userinfo_modify_sex);
		et_userinfo_modify_phone=(EditText) findViewById(R.id.et_userinfo_modify_phone);
		et_userinfo_modify_address=(EditText) findViewById(R.id.et_userinfo_modify_address);
		btn_userinfo_modify_submit=(Button) findViewById(R.id.btn_userinfo_modify_submit);
	}
	@Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
		}
}