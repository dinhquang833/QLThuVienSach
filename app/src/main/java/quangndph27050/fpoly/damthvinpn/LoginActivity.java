package quangndph27050.fpoly.damthvinpn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import quangndph27050.fpoly.damthvinpn.dao.ThuThuDAO;

public class LoginActivity extends AppCompatActivity {
    EditText edUserName,edPassword;
    Button btnLogin,btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);

        ThuThuDAO thuThuDAO = new ThuThuDAO(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edUserName.getText().toString();
                String pass = edPassword.getText().toString();
                if (thuThuDAO.checkDangNhap(user, pass)){
                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("matt", user);
                    editor.commit();

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });



//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (edUserName.getText().toString().equals("quang")&&edPassword.getText().toString().equals("123")){
//                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
//
//                    Intent intent = new Intent();
//                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
//                }else {
//                    Toast.makeText(LoginActivity.this, "Đăng nhập không thành công!", Toast.LENGTH_SHORT).show();
//                }
//            }
//          });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edUserName.setText("");
                edPassword.setText("");
                Toast.makeText(LoginActivity.this, "Đã làm rỗng Tên đăng nhập và Mật khẩu!!!", Toast.LENGTH_SHORT).show();
            }
        });
   }
}