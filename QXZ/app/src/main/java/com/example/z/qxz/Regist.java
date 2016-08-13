package com.example.z.qxz;


import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//注册的类
public class Regist extends AppCompatActivity
{
//    邮箱正则
    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    Button regist;
    EditText email,username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        final TextInputLayout emailWrapper = (TextInputLayout) findViewById(R.id.emailWrapper);
        final TextInputLayout usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        final TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        actionBar.setHomeAsUpIndicator(R.drawable.back);  设置样式


        String email1 = getResources().getString(R.string.please_email).toString();
        String username1 = getResources().getString(R.string.please_username).toString();
        String password1 = getResources().getString(R.string.please_password).toString();

        //要让浮动标签动起来，你只需设置一个hint，使用setHint方法：
        emailWrapper.setHint(email1);
        usernameWrapper.setHint(username1);
        passwordWrapper.setHint(password1);


        email = emailWrapper.getEditText();
        username = usernameWrapper.getEditText();
        password = passwordWrapper.getEditText();

        regist = (Button) findViewById(R.id.regist);
        regist.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                hideKeyboard();
                String email = emailWrapper.getEditText().getText().toString();

                String username = usernameWrapper.getEditText().getText().toString();

                String password = passwordWrapper.getEditText().getText().toString();

                    if(!validateEmail(email))
                    {
                        emailWrapper.setErrorEnabled(true);
                        emailWrapper.setError("请确认输入邮箱格式正确");
                        //Log.i("----------------------------------","调用了validateEmail方法");
                    }
                    else
                    {
                        //Log.i("----------------------------------","有重新雄心雄心调用了validateEmail方法");
                        emailWrapper.setError(null);
                    }
                    if(!validateUsername(username))
                    {
                        usernameWrapper.setErrorEnabled(true);
                        //Log.i("----------------------------------","调用了validateUsername方法");
                        usernameWrapper.setError("用户名不能为空");
                    }
                    else
                    {
                        //Log.i("----------------------------------","又雄心调用了调用了validateUsername方法");
                        usernameWrapper.setError(null);

                    }
                    if(!validatePassword(password))
                    {
                        passwordWrapper.setErrorEnabled(true);
                        //Log.i("----------------------------------","调用了validatePassword方法");
                        passwordWrapper.setError("请确认密码长度在6-20位字符之间");
                    }
                    else
                    {
                        //Log.i("----------------------------------","又雄心调用了调用了validatePassword方法");
                        passwordWrapper.setError(null);
                    }
                    if(validateEmail(email) && validateUsername(username) && validatePassword(password))
                    {
                        Toast.makeText(Regist.this,"注册成功了",Toast.LENGTH_SHORT).show();
                    }
                }
        });
   }

    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

//    验证邮箱
    public boolean validateEmail(String email)
    {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
//  当Button.onClick方法调用之后，用户不再需要键盘。但如果你不告诉它，安卓不会自动的隐藏虚拟键盘。
// 在onClick方法体中调用hideKeyboard。
    private void hideKeyboard()
    {
        View view = getCurrentFocus();
        if (view != null)
        {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    private boolean validatePassword(String password)
    {
        return password.length()>5 && password.length()<20;
    }
    private boolean validateUsername(String username)
    {
        return username.length()>0;
    }
}
