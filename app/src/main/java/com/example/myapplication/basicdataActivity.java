package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.room.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class basicdataActivity extends AppCompatActivity {
    int index = 0; //用于删除数据库中的空数据

    Info mInfo;
    InfoDatabase mInfoDatabase;
    InfoDao mInfoDao;

    private ImageView left;//返回
    private TextView reserve;//保存
    private ImageView head;//头像
    private EditText name;//昵称
    private EditText introduce;//简介
    private RadioGroup sex;//性别，单选按钮
    private RadioButton boy;
    private RadioButton girl;
    String msex;//记录选择的性别
    private static final String DIALOG_DATE = "DialogDate";
    private Bitmap touxiang;// 头像Bitmap
    private static String path = "/sdcard/myHead/";// sd路径

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mInfoDatabase = InfoDatabase.getInstance(this);
        mInfoDao = mInfoDatabase.getInfoDao();

        List<Info> list = mInfoDao.getAllInfo();
        index = list.size() - 1;
        mInfo = list.get(index);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_basicdata);

        //实现返回的imageview《-------
        left = (ImageView) findViewById(R.id.iv_data_left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left();
            }
        });

        //textview实现保存修改的资料《-------
        reserve = (TextView) findViewById(R.id.tv_data_reserve);
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Info> list = mInfoDao.getAllInfo();
                index = list.size() - 2;

                //保存姓名简介等内容
                String name_text = name.getText().toString();
                String intro_text = introduce.getText().toString();
                Info info = new Info(name_text, intro_text, msex);
                mInfoDao.InsertInfo(info);
                Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
            }
        });

        //修改昵称《------
        name = (EditText) findViewById(R.id.et_data_name);
        name.setText(mInfo.getName());

        //简介《-----
        introduce = (EditText) findViewById(R.id.et_data_introduce);
        name.setText(mInfo.getIntroduction());

        //性别
        sex = (RadioGroup) findViewById(R.id.rg_data_sex);//获取单选按钮
        sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()) {
                    case R.id.rd_data_boy:
                        msex = "男";
                        break;
                    case R.id.rd_data_girl:
                        msex = "女";
                        break;
                }
            }
        });


        //更换头像
        head = (ImageView) findViewById(R.id.iv_head);
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_head:// 更换头像
                        showTypeDialog();//下方弹出dialog并且获取拍照和相册两个view
                        break;
                }
            }
        });
    }

    //设置返回事件
    public void left(){
        //删除从 mineFragment 保存的空数据
        List<Info> list2 = mInfoDao.getAllInfo();
        Info info = list2.get(index);
        mInfoDao.DeleteInfo(info);

        Intent intent = new Intent(basicdataActivity.this,MainActivity.class);
        intent.putExtra("choose",3);
        startActivity(intent);

        finish();//直接finish就能返回上一个gragment，即minefragment
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            left();
            return false;
        }
        return false;

    }

    private void initView(View inflate1) {

        head = (ImageView) findViewById(R.id.iv_head);
        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");// 从SD卡中找头像，转换成Bitmap
        if (bt != null) {
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);// 转换成drawable
            head.setImageDrawable(drawable);
        } else {
            /**
             * 如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
             *
             */
        }
    }

    private void showTypeDialog() {
        //显示对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(basicdataActivity.this);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(basicdataActivity.this, R.layout.dialog_head, null);
        ImageView tv_select_gallery = (ImageView) view.findViewById(R.id.iv_select_gallery);
        ImageView tv_select_camera = (ImageView) view.findViewById(R.id.iv_select_camera);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {//选择相册
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                //打开文件
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                dialog.dismiss();
            }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener() {// 调用照相机《---------未能实现
            @Override
            public void onClick(View v) {//相机
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(intent2, 2);// 采用ForResult打开
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();//改变dialog的样式，背景变灰
        lp.alpha = 1f;//设置本身透明度
        lp.width = 900;
        lp.height = 700;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }
                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        /**
                         * 上传服务器代码
                         */
                        setPicToView(touxiang);// 保存在SD卡中
                        head.setImageBitmap(touxiang);// 用ImageView显示出来
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 调用系统的裁剪功能
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
