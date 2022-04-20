package jp.Ken.history_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private boolean Button_Check = true;
    private final boolean Check = true;
    private QuizSound quizSound;



    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        if ( savedInstanceState != null ){
            Button_Check = savedInstanceState.getBoolean ( "Button_Check" );
        }

        quizSound = new QuizSound ( this );

        Button button_1 = findViewById ( R.id.button1 );
        Button button_2 = findViewById ( R.id.button13 );
        Button button_3 = findViewById ( R.id.button );
        Button button_4 = findViewById ( R.id.button2 );
        Button button_5 = findViewById ( R.id.button3 );
        Button button_6 = findViewById ( R.id.button4 );
        Button button_7 = findViewById ( R.id.button7 );

        button_1.setOnClickListener ( All_Button );
        button_2.setOnClickListener ( All_Button );
        button_3.setOnClickListener ( All_Button );
        button_4.setOnClickListener ( All_Button );
        button_5.setOnClickListener ( All_Button );
        button_6.setOnClickListener ( All_Button );
        button_7.setOnClickListener ( All_Button );

        FragmentManager fragmentManager = getSupportFragmentManager();
        //フラグメントから戻ってくる時に処理
        fragmentManager.setFragmentResultListener ( "Button_Check" , this , new FragmentResultListener ( ) {
            @Override
            public void onFragmentResult ( @NonNull String requestKey , @NonNull Bundle result ) {

                Button_Check = result.getBoolean ( "Button_Check" );
            }
        } );

    }



    private final View.OnClickListener All_Button = new View.OnClickListener ( ) {
        @Override
        public void onClick ( View v ) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            androidx.fragment.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            //問題選択画面の時のみボタン処理
            switch (v.getId ()){
                case R.id.button1:
                    if ( Button_Check ){
                        quizSound.play_home_bgm ();
                        Fragment_1 fragment1 = new Fragment_1 ();
                        fragmentTransaction.replace ( R.id.Fragment,fragment1 );
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit ();
                        Button_Check = false;
                        break;
                    }

                case R.id.button13:
                    if ( Button_Check ){
                        quizSound.play_home_bgm ();
                        Fragment_2 fragment2 = new Fragment_2 ();
                        fragmentTransaction.replace ( R.id.Fragment,fragment2 );
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit ();
                        Button_Check = false;
                        break;
                    }

                case R.id.button:
                    if ( Button_Check ){
                        quizSound.play_home_bgm ();

                        Fragment_3 fragment3 = new Fragment_3 ();
                        fragmentTransaction.replace ( R.id.Fragment,fragment3 );
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit ();
                        Button_Check = false;
                        break;
                    }

                case R.id.button2:
                    if ( Button_Check ){
                        quizSound.play_home_bgm ();

                        Fragment_4 fragment4 = new Fragment_4 ();
                        fragmentTransaction.replace ( R.id.Fragment,fragment4 );
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit ();
                        Button_Check = false;
                        break;
                    }

                case R.id.button3:
                    if ( Button_Check ){
                        quizSound.play_home_bgm ();

                        Fragment_5 fragment5 = new Fragment_5 ();
                        fragmentTransaction.replace ( R.id.Fragment,fragment5 );
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit ();
                        Button_Check = false;
                        break;
                    }

                case R.id.button4:
                    if ( Button_Check ){
                        quizSound.play_home_bgm ();

                        Fragment_6 fragment6 = new Fragment_6 ();
                        fragmentTransaction.replace ( R.id.Fragment,fragment6 );
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit ();
                        Button_Check = false;
                        break;
                    }

                case R.id.button7:
                    if ( Button_Check ){
                        quizSound.play_home_bgm ();
                        MyDialogFragment myDialogFragment = new MyDialogFragment ();
                        myDialogFragment.show ( getSupportFragmentManager (),"my_dialog" );
                        break;
                    }
            }
        }
    };

    @Override
    public void onSaveInstanceState( @NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean ( "Button_Check",Button_Check );
    }

    //端末もどるボタン無効
    @Override
    public void onBackPressed() {
    }


}