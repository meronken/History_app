package jp.Ken.history_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private QuizSound quizSound;


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_result );

        quizSound = new QuizSound ( getApplication () );

        //正解数受け取り
        int score = getIntent ().getIntExtra ( "RIGHT_ANSWER_COUNT",1 );
        int count = getIntent ().getIntExtra ( "QUIZ_COUNT",1 );


        //正解率 算出
        float ans = (int)((float)score/(float)count*100);


        PieChart mPieChart = findViewById ( R.id.pieChart );

        List<PieEntry> integer = new ArrayList ();
        integer.add ( new PieEntry ( ans ) );
        integer.add ( new PieEntry (100-ans) );

        //円グラフ及び文字の色を格納
        List< Integer > color = new ArrayList<> (  );
        color.add ( getColor ( R.color.g_1 ) );
        color.add ( getColor ( R.color.g_4 ) );


        //データ格納
        PieDataSet dataset = new PieDataSet (integer,null);
        //円グラフの色を指定
        dataset.setColors ( color );
        //文字の色を変更
        dataset.setValueTextColors ( color );

        //chartに設定
        PieData pieData = new PieData (dataset);
        //グラフ中心に表示
        mPieChart.setCenterText (score+"/"+count);


        ConstraintLayout v = findViewById ( R.id.result_n );
        ConstraintLayout v1 = findViewById ( R.id.result_360 );
        ConstraintLayout v2 = findViewById ( R.id.result_400 );
        ConstraintLayout v3 = findViewById ( R.id.result_480 );
        ConstraintLayout v4 = findViewById ( R.id.result_600 );
        ConstraintLayout v5 = findViewById ( R.id.result_720 );

        //文字大きさ
        //画面判定(使用レイアウトの判定)
        if ( v != null ){
            mPieChart.setCenterTextSize ( 25f );

        }else if ( v1 != null ){
            mPieChart.setCenterTextSize ( 30f );

        }else if ( v2 != null ) {
            mPieChart.setCenterTextSize ( 30f );

        }else if ( v3 != null ){
            mPieChart.setCenterTextSize ( 30f );

        }else if ( v4 != null ){
            mPieChart.setCenterTextSize ( 40f );

        }else if ( v5 != null ){
            mPieChart.setCenterTextSize ( 45f );

        }

        //文字色
        mPieChart.setCenterTextColor ( getColor ( R.color.g_5 ) );
        mPieChart.setData ( pieData );


        //Descriptionを非表示
        mPieChart.getDescription ().setEnabled ( false );


        //外側　色非表示
        mPieChart.getLegend ().setEnabled ( false );

        //反映
        mPieChart.invalidate ();

        Button button = findViewById ( R.id.button_home );
        //ホームに戻る
        button.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                quizSound.play_next_bgm ();
                Intent intent = new Intent ( getApplicationContext (),MainActivity.class );
                startActivity ( intent );
            }
        } );
    }

    //端末もどるボタン無効
    @Override
    public void onBackPressed() {
    }

}