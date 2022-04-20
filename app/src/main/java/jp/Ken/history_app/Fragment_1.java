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
 * Use the {@link Fragment_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam2;

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
    static final private int QUIZ_COUNT = 40;
    private String alertTitle;

    final ArrayList<ArrayList<String>> quizList = new ArrayList <> (  );

    //問題数,問題,正解,選択肢１,選択肢2,選択肢３,選択肢4
    final String[][] quizData = {
            {"今から約700〜600万年前、アフリカに現れた最古の人類を何というか","猿人","原人","新人","旧人"},
            {"約250万年前から1万年ほど前に、地球は寒冷化し氷期と間氷期が繰り返された。この時期を一般に何時代というか","氷河時代","氷床時代","エール時代","ビカリア時代"},
            {"約200万年前に出現し、猿人に次ぐ段階の人類を何というか","原人","新人","旧人","亜人"},
            {"石を打ち欠いて作った石器をなんというか","打製石器","磨製石器","台形石器","オルドワン石器"},
            {"約20万年前に現在の人類の直接の祖先が現れた。これを何というか","新人","旧人","北京人","原人"},
            {"打製石器を使っていた、約1万年前までの時代を何というか","旧石器時代","新石器時代","古墳時代","先カンプリア時代"},
            {"石を磨いて作った石器をなんというか","磨製石器","台形石器","打製石器","オルドワン石器"},
            {"紀元前3000年頃、ナイル川流域で起こった文明を何というか","エジプト文明","中国文明","インダス文明","メソポタミア文明","8"},
            {"エジプト文明で使われた文字を何というか","象形文字","甲骨文字","くさび形文字","漢字","9"},
            {"紀元前3000年頃、チグリス・ユーフラテス川流域で起こった文明を何というか","メソポタミア文明","エジプト文明","中国文明","インダス文明"},
            {"中国文明は黄河流域で起こったが、この時使われた漢字のもととなる文字は何か","甲骨文字","ヒエログリフ","くさび形文字","漢字"},
            {"エジプト文明において、神として敬われた国王の墓として何が作られたか","ピラミッド","前方後円墳","アブシンベル","タージマハル"},
            {"メソポタミア文明で使われた文字を何というか","くさび形文字","ルーン文字","トンパ文字","象形文字"},
            {"ヨーロッパの方からみて「太陽ののぼる土地」という意味がある言葉で、古代文明が発生したエジプトとメソポタミアを含む地域は何とよばれたか","オリエント","ポリス","トロイア","オリエンタル"},
            {"紀元前2500年頃、インド北西部に起こった文明を何というか","インダス文明","マヤ文明","ミケーネ文明","メソポタミア文明"},
            {"仏教を説いたのは誰か","シャカ","イエス","ムハンマド","孔子"},
            {"イスラム教を説いたのは誰か","ムハンマド","アブラハム","ガンディー","孔子"},
            {"紀元前6世紀頃、儒学のもととなる教えを説いたのは誰か","孔子","始皇帝","孫武","卑弥呼"},
            {"紀元前3世紀、始皇帝の元で中国を統一した王朝を何というか","秦","高句麗","新羅","漢"},
            {"今から一万年ほど前からつくられるようになった、表面に網目のような模様がつけられた土器を何というか","縄文土器","土偶","弥生土器","千網式土器"},
            {"縄文時代からつくるられるようになった、浅く土を掘りその上に屋根をふきおろした住居を何というか","たて穴住居","地堀式住居","よこ穴住居","ふきおろし住居"},
            {"縄文時代の人々が、海岸や水辺に食べ物の残りかすなどを捨てた場所を何というか","貝塚","残飯","石塚","食塚"},
            {"縄文時代につくられた、主に女性の形をした土製の人形を何というか","土偶","女形","はにわ","土人形"},
            {"青森市の郊外で発掘された、縄文時代中期の代表的な遺跡を何というか","山内丸山遺跡","石見銀山遺跡","荒神谷遺跡","山大丸山遺跡"},
            {"弥生時代になると、大陸から何が伝わったか","稲作","火起こし","狩猟","焼畑"},
            {"稲を収穫するために使われた石器を何というか","石包丁","鋸鎌","がんづめ","ノコギリ"},
            {"縄文土器に比べるとうすくて硬めで縄文時代に使われた土器を何というか","弥生土器","青銅器","硬質土器","薄生地土器"},
            {"弥生時代に金属器が大陸から伝わったが、このうち祭などで使われたものは何か","青銅器","すず","鉄器","鋼器"},
            {"金属器のうち、斧やナイフ、農具、武器として使われたものは何か","鉄器","銅鐸","銅矛","銅鏡"},
            {"収穫した米などをねずみや湿気から防いで保存するためにつくられた倉庫を何というか","高床倉庫","米貯蔵庫","台上倉庫","床上倉庫"},
            {"大規模な環濠集落の跡が残る佐賀県にある遺跡は何か","吉野ヶ里遺跡","登呂遺跡","弥生町遺跡","板付遺跡",""},
            {"いくつかある古墳の種類のなかで、四角形の前部分と円形の後部からなる代表的な形の古墳を何というか","前方後円墳","前方後方墳","上円下方墳","前円後方墳"},
            {"祭祀や魔除けなどのため、古墳のまわりに並べられていた素焼の焼物を何というか","はにわ","焼人形","はこにわ","土偶"},
            {"3世紀後半に現在の奈良盆地を中心に、王と豪族が連合してつくった政権を何というか","大和政権","倭政権","大王政権","弥生政権"},
            {"7世紀ごろから天皇と呼ばれるようになる大和政権の王のことを何というか","大王","名王","和王","大君"},
            {"紀元前1世紀におこり、朝鮮半島の北部を支配した国を何というか","高句麗","百済","新羅","伽耶"},
            {"4世紀ごろにおこり、朝鮮半島南東部を支配した国を何というか","新羅","百済","伽耶","唐"},
            {"4世紀ごろにおこり、朝鮮半島の南西部にあった国を何というか","百済","伽耶","唐","長安"},
            {"3世紀から7世紀頃に中国大陸や朝鮮半島から日本に移住した人々を何というか","渡来人","移住人","大陸人","渡陸人"},
            {"紀元前4世紀にギリシアを支配したマケドニアの王は誰か","アレクサンドロス大王","卑弥呼","始皇帝","イエス"},
    };

    public Fragment_1 ( ) {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment Fragment_1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_1 newInstance ( String param1) {
        Fragment_1 fragment = new Fragment_1 ( );
        Bundle args = new Bundle ( );
        args.putString ( ARG_PARAM1 , param1 );
        fragment.setArguments ( args );
        return fragment;
    }

    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        if ( getArguments ( ) != null ) {
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments ( ).getString ( ARG_PARAM1 );
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