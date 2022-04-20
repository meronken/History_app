package jp.Ken.history_app;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_6#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_6 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView q_count;
    private TextView q;
    private Button ans_A;
    private Button ans_B;
    private Button ans_C;
    private Button ans_D;
    private QuizSound quizSound;
    private ArrayList< String > remove_num;
    private boolean show = true;


    //正解を入れる
    private String rightAnswer;
    //正解数カウント
    private int rightAnswerCount;
    //問題数カウント
    private int quizCount = 1;

    //クイズ出題数
    static final private int QUIZ_COUNT = 45;
    private String alertTitle;

    final ArrayList<ArrayList<String>> quizList = new ArrayList <> (  );

    //問題数,問題,正解,選択肢１,選択肢2,選択肢３,選択肢4
    final String[][] quizData= {
            {"1983年ドイツはどこを併合したか","オーストラリア","アイスランド","トルコ","アイルランド"},
            {"ドイツはソ連とどんな条約を結んだか","独ソ不可侵条約","日ソ中立条約","日ソ基本条約","ブエノスアイレス講和条約"},
            {"1949年に日本がドイツ・イタリアと結んだ軍事同盟は何か","日独伊三国同盟","三国同盟","三国協商","日独伊防共協定"},
            {"1941年に日本とソ連との間で結ばれた条約は何か","日ソ中立条約","日独伊三国同盟","独ソ不可侵条約"," 中ソ友好同盟相互援助条約"},
            {"1945年にクリミア半島ヤルタで開かれた連合国首脳による会議は何か","ヤルタ会談","ワシントン会議","ビルダーバーグ会議","ダボス会議"},
            {"1945年7月に連合国は日本に対して何を発表したか","ポツダム宣言","世界人権宣言","独立宣言","権利の章典"},
            {"広島に原爆が落とされたのはいつか","8月6日","8月9日","9月6日","9月8日"},
            {"長崎に原爆が落とされたのはいつか","8月9日","8月6日","9月6日","6月8日"},
            {"1945年8月8日に日本に宣戦したのはどこか","ソ連","フランス","中国","イギリス"},
            {"日本がポツダム宣言を受け入れ、降伏したのはいつか","8月15日","11月3日","5月3日","8月20日"},
            {"日本が占領した連合国軍の本部を何というか","連合国総司令部","連合総本部","重要連合本部","秘密連合国軍総本部"},
            {"連合国軍をアルファベットで何というか","GHQ","WHO","IMF","WTO"},
            {"連合国軍の最高司令官は誰か","マッカーサー","ガンジー","ヒトラー","ビスマルク"},
            {"政治活動の自由を認めるために廃止された法律は何か","治安維持法","普通選挙法","労働基準法","税関法"},
            {"1945年に選挙法が改正されたが、選挙権を与えられた人の資格は何か","満20歳以上男女","満18歳以上男女","満25歳以上男女","満30歳以上男女"},
            {"1945年制定の労働社の団結を認めた法律は何か","労働組合法","労働基準法","労働関係調整法","最低賃金法"},
            {"1947年制定の労働条件の最低基準を定めた法律を何か","労働基準法","労働組合法","労働安全衛生法","労働契約法"},
            {"財閥が持っていた株を分散させたことを何というか","財閥解体","財閥崩壊","財閥分解","財閥分散"},
            {"1947年大企業が利益を独占することを禁止した法律は何か","独占禁止法","治安維持法","製造物責任法","利益独占禁止法"},
            {"地主の土地を小作農民に解放する政策は何か","農地改革","3B政策","財閥解体","国体変革"},
            {"新しい日本の憲法を何というか","日本国憲法","ワイマール憲法","大日本国憲法","大日本帝国憲法"},
            {"日本国憲法はいつ公布されたか","1946年11月3日","1947年5月3日","1945年8月6日","1945年9月6日"},
            {"日本国憲法はいつ施行されたか","1947年5月3日","1946年11月3日","1945年8月6日","1945年9月6日"},
            {"日本国憲法で天皇の地位はどうなったか","日本の象徴","日本の友達","日本の皇帝","日本の王様"},
            {"1947年に制定された教育に関する法律は何か","教育基本法","学校教育法","地方教育行政法","私立学校法"},
            {"1951年日本が48カ国と結び独立を回復した条約は何か","サンフランシスコ平和条約","日ソ中立条約","独ソ不可侵条約","ワルシャワ条約 "},
            {"サンフランシスコ平和条約の条約と同時に日本がアメリカと結んだ条約は何か","日米安全保障条約","日米通商航海条約","日米修好通商条約","日米修好通商条約"},
            {"1956年に日本とソ連の間で発表された宣言は何か","日ソ共同宣言","世界人権宣言","日ソ独立宣言","ポツダム宣言"},
            {"1956年、日本はどこに加盟したか","国際連合","国際連盟","国際海事機関","国際海事機関"},
            {"日本が1960年から始めた経済優先の政策は何か","高度経済成長政策","治安維持法","独占禁止法","労働基準法"},
            {"1965年に日本が韓国と結んだ条約は何か","日韓基本条約","日韓大陸棚条約","日韓併合条約","サンフランシスコ平和条約"},
            {"熊本県の水俣市で起こった四大公害病は何か","水俣病","四日市ぜんそく","イタイイタイ病","新潟水俣病"},
            {"三重県の四日市で起こった四大公害病は何か","四日市ぜんそく","新潟水俣病","新潟水俣病","水俣病"},
            {"富山県で起こった四大公害病は何か","イタイイタイ病","水俣病","四日市ぜんそく","新潟水俣病"},
            {"新潟県で起こった四大公害病は何か","新潟水俣病","水俣病","イタイイタイ病","四日市ぜんそく"},
            {"1972年に日本が中国との間に発表した声明は何か","日中共同声明","日米共同声明","日ソ共同宣言","日中平和友好条約"},
            {"1972年にアメリカから日本に返還されたのはどこか","沖縄","ハワイ","北海道","樺太"},
            {"1973年に第四次中東戦争で原油価格が値上がりしたために起こった事態を何というか","石油危機","ブラックマンデー","リーマンショック","コロナショック"},
            {"1978年に日本と中国との間で結ばれた条約は何か","日中平和友好条約","日中共同声明","中朝相互援助条約","日清講和条約"},
            {"1986年に制定された、雇用に関しての男女の対等な立場を目指す法律は何か","男女雇用機会均等法","男女共同参画社会基本法","育児・介護休業法","労働者派遣法"},
            {"1995年に近畿地方で起きた大地震は何か","阪神淡路大震災","芸予地震","熊本地震 ","鳥取地震"},
            {"1999年に施行された男女が性別にかかわらず個性や能力を発揮できる社会を目指す法律は何か","男女共同参画社会基本法","男女雇用機会均等法","育児・介護休業法","普通選挙法"},
            {"2000年から始まった、少子高齢化に備え社会保障費を社会全体で支える制度は何か","介護保険制度","社会福祉制度","公的扶助制度","雇用保険制度"},
            {"アメリカ、イギリス、オランダなどによる日本に対する経済封鎖は各国の頭文字をとって何と呼ばれたか","ABCD包囲陣","EFGH包囲陣","IJKL包囲陣","MNOP包囲陣"},
            {"ドイツがポーランドのオシフィエンチムに作った強制収容所を何というか","アウシュビッツ収容所","バルドゥフォス収容所","ダッハウ収容所","ベウジェツ収容所"},
    };

    public Fragment_6 ( ) {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_6.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_6 newInstance ( String param1 , String param2 ) {
        Fragment_6 fragment = new Fragment_6 ( );
        Bundle args = new Bundle ( );
        args.putString ( ARG_PARAM1 , param1 );
        args.putString ( ARG_PARAM2 , param2 );
        fragment.setArguments ( args );
        return fragment;
    }

    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        if ( getArguments ( ) != null ) {
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments ( ).getString ( ARG_PARAM1 );
            String mParam2 = getArguments ( ).getString ( ARG_PARAM2 );
        }
    }

    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
                               Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        View v = inflater.inflate ( R.layout.fragment , container , false );

        //フラグメント内に保存されたデータを取得
        if ( savedInstanceState != null ){
            //一度表示された問題
            remove_num = savedInstanceState.getStringArrayList ( "remove_quiz" );
            //出題数
            quizCount = savedInstanceState.getInt ( "quiz_count" );
            //正解数
            rightAnswerCount = savedInstanceState.getInt ( "answer_count" );

            show = savedInstanceState.getBoolean ( "show" );

        }

        //正解・不正解　効果音
        quizSound = new QuizSound ( getActivity () );

        //ラベル、ボタン
        q_count = v.findViewById ( R.id.quizCount );
        q = v.findViewById ( R.id.question);
        ImageButton home_button = v.findViewById ( R.id.button10 );

        ans_A = v.findViewById ( R.id.ans_A );
        ans_B = v.findViewById ( R.id.ans_B );
        ans_C = v.findViewById ( R.id.ans_C );
        ans_D = v.findViewById ( R.id.ans_D );


        ans_A.setOnClickListener (Button_CheckAnswer);
        ans_B.setOnClickListener (Button_CheckAnswer);
        ans_C.setOnClickListener (Button_CheckAnswer);
        ans_D.setOnClickListener (Button_CheckAnswer);

        for (int i=0;i<quizData.length;i++){

            ArrayList<String> temArray = new ArrayList <> (  );

            temArray.add ( quizData[i][0] );//問題
            temArray.add ( quizData[i][1] );//正解 選択肢
            temArray.add ( quizData[i][2] );//選択肢
            temArray.add ( quizData[i][3] );//選択肢
            temArray.add ( quizData[i][4] );//選択肢
            temArray.add ( String.valueOf ( i ) );

            quizList.add ( temArray );
        }


        ShowQuestion ();

        //ホームボタン
        home_button.setOnClickListener ( Home_button );

        return v;
    }

    //出題済みの問題を配列から削除
    public void RemoveQuestion(){

        for (int i=0;i<remove_num.size ();i++){
            int num = Integer.parseInt ( remove_num.get ( i ) );
            quizList.remove ( num );
        }
    }

    @SuppressLint ( "SetTextI18n" )
    public void ShowQuestion(){
        //クイズカウント更新
        q_count.setText ( quizCount+"問" );

        if ( show ){

            //ランダムな数字を取得
            Random random = new Random (  );
            int Random_num = random.nextInt ( quizList.size ());

            ArrayList<String> quiz = quizList.get ( Random_num );

            //表示した問題の番号を記録
            remove_num = new ArrayList <> (  );
            remove_num.add ( ( quiz.get ( 5 ) ) ) ;
            quiz.remove ( 5 );

            //問題文を表示
            q.setText ( quiz.get ( 0 ) );
            //配列から問題文を削除
            quiz.remove ( 0 );

            //正解をrightAnswerにセット
            rightAnswer = quiz.get ( 0 );

            //正解と選択肢をシャッフル
            Collections.shuffle ( quiz );

            //選択ボタンに選択肢を表示
            ans_A.setText ( quiz.get ( 0 ) );
            ans_B.setText ( quiz.get ( 1 ) );
            ans_C.setText ( quiz.get ( 2 ) );
            ans_D.setText ( quiz.get ( 3 ) );

            //配列からクイズを削除
            quizList.remove ( Random_num );

        }else {
            int num1 = Integer.parseInt ( remove_num.get ( remove_num.size ()-1 ) );

            ArrayList<String> quiz = quizList.get (num1);

            //問題文を表示
            q.setText ( quiz.get ( 0 ) );
            //配列から問題文を削除
            quiz.remove ( 0 );

            //正解をrightAnswerにセット
            rightAnswer = quiz.get ( 0 );

            //選択ボタンに選択肢を表示
            ans_A.setText ( quiz.get ( 0 ) );
            ans_B.setText ( quiz.get ( 1 ) );
            ans_C.setText ( quiz.get ( 2 ) );
            ans_D.setText ( quiz.get ( 3 ) );

            RemoveQuestion ();
        }
    }


    private final View.OnClickListener Button_CheckAnswer = new View.OnClickListener ( ) {

        @SuppressLint ( "ResourceAsColor" )
        @Override
        public void onClick ( View v ) {

            //ダイアログ　タイトルカスタム　ラベル
            TextView title_view = new TextView ( getActivity () );
            //ダイアログ　メッセージカスタム　ラベル
            TextView msg_view = new TextView ( getActivity () );

            //どの解答が押されたか
            Button ans_button = v.findViewById ( v.getId ());
            String btN_text = ans_button.getText ().toString ();

            //正解か不正解か判定
            if ( btN_text.equals ( rightAnswer ) ){
                //　♪正解　
                quizSound.play_ct_ans ();
                //ダイヤログ表示 title
                title_view.setText ( "正解" );
                title_view.setTextColor ( getResources ().getColor ( R.color.white ) );
                title_view.setTextSize(24);
                title_view.setPadding(40, 10, 10, 10);
                title_view.setBackgroundColor(getResources().getColor ( R.color.red ));
                //正解数カウント
                rightAnswerCount++;
            }else {
                // ♪不正解
                quizSound.play_it_ans ();
                //ダイヤログ表示 title
                title_view.setText ( "不正解" );
                title_view.setTextSize(20);
                title_view.setTextColor ( getResources ().getColor ( R.color.white ) );
                title_view.setPadding(40, 10, 10, 10);
                title_view.setBackgroundColor(getResources().getColor ( R.color.blue ));

            }
            //ダイアログ　msg
            msg_view.setText ( "答え: "+rightAnswer );
            msg_view.setTextSize ( 20 );
            msg_view.setTextColor ( getResources ().getColor ( R.color.g_2 ) );
            msg_view.setPadding(20, 40, 20, 40);


            //ダイアログ作成
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setCustomTitle ( title_view );
            builder.setView ( msg_view );
            builder.setPositiveButton ( "次へ" , new DialogInterface.OnClickListener ( ) {
                @Override
                public void onClick ( DialogInterface dialog , int which ) {
                    if ( quizCount==QUIZ_COUNT ){
                        //結果画面へ移動
                        Intent intent = new Intent ( getActivity (),ResultActivity.class );
                        intent.putExtra ( "RIGHT_ANSWER_COUNT",rightAnswerCount );
                        intent.putExtra ( "QUIZ_COUNT",quizCount );
                        startActivity ( intent );
                    }else {
                        show = true;
                        quizCount++;
                        ShowQuestion ();
                    }
                }
            } );

            //戻るボタン無効　フラグメントを閉じないようにする
            builder.setCancelable ( false );
            builder.show ();
        }
    };


    private final View.OnClickListener Home_button = new View.OnClickListener ( ) {
        @Override
        public void onClick ( View v ) {

            quizSound.play_next_bgm ();
            FragmentManager fragmentManager = getActivity ().getSupportFragmentManager ();
            Bundle bundle = new Bundle () ;
            bundle.putBoolean ( "Button_Check",true );
            fragmentManager.setFragmentResult ( "Button_Check",bundle );
            fragmentManager.popBackStack ();
        }
    };


    @Override
    public void onSaveInstanceState( @NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //一度表示された問題　番号
        outState.putStringArrayList ( "remove_quiz",remove_num );
        //出題数
        outState.putInt ( "quiz_count",quizCount );
        //正解数
        outState.putInt ( "answer_count",rightAnswerCount );
        //
        outState.putBoolean ("show",false);

    }


}