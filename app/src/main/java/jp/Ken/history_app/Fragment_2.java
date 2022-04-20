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
 * Use the {@link Fragment_2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_2 extends Fragment {

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
    static final private int QUIZ_COUNT = 55;
    private String alertTitle;

    final ArrayList<ArrayList<String>> quizList = new ArrayList <> (  );

    //問題,正解,選択肢１,選択肢2,選択肢３
    final String[][] quizData = {
            {"紀元前使後、日本の九州北部にあり中国に使いを送った国を何というか","奴国","倭国","鎖国","九国"},
            {"奴国がいを送った当時の中国の王朝名は何か","漢","隋","唐","清"},
            {"奴国の王が漢の皇帝から授かり、のちに福岡県の志賀島で発見されたものは何か","金印","漢印","王印","帝印"},
            {"中国の歴史書によると、当時の日本は何と呼ばれていたか","倭","和","日","武"},
            {"邪馬大国の女王である卑弥呼が使いを送った中国の国はどこか","魏","蜀","呉","志"},
            {"卑弥呼や邪馬台国に代表される古代日本の様子や、魏との関係が記されている中国で書かれた史書は何か","魏志倭人伝","漢書地理志","後漢東夷伝","魏志和人伝"},
            {"いくつかある古墳の種類のなかで、四角形の前部分と円形の後部からなる代表的な形の古墳を何というか","前方後円墳","前円後方墳","前方後方墳","上円下方墳"},
            {"祭祀や魔除けなどのため、古墳のまわりに並べられていた素焼の焼物を何というか","はにわ","土偶","はこにわ","焼人形"},
            {"3世紀後半に現在の奈良盆地を中心に、王と豪族が連合してつくった政権を何というか","大和政権","弥生政権","倭政権","大王政権"},
            {"7世紀ごろから天皇と呼ばれるようになる大和政権の王のことを何というか","大王","大君","名王","和王"},
            {"紀元前1世紀におこり、朝鮮半島の北部を支配した国を何というか","高句麗","百済","新羅","伽耶"},
            {"4世紀ごろにおこり、朝鮮半島南東部を支配した国を何というか","新羅","百済","伽耶","唐"},
            {"4世紀ごろにおこり、朝鮮半島の南西部にあった国を何というか","百済","伽耶","唐","長安"},
            {"3世紀から7世紀頃に中国大陸や朝鮮半島から日本に移住した人々を何というか","渡来人","移住人","大陸人","渡陸人"},
            {"590年代に初の女性の天皇として政治を行なった天皇は誰か","推古天皇","斉明天皇","持統天皇","皇極天皇"},
            {"6世紀末、長い間混乱が続いた中国を統一した王朝を何というか","隋","唐","漢","秦"},
            {"隋に代わり、７世紀初めに中国を統一した王朝を何というか","唐","漢","秦","呉"},
            {"6世紀末に物部氏を滅ぼし、強大な勢力をもつようになった豪族は誰か","蘇我氏","飛鳥氏","北条氏","藤原氏"},
            {"603年に定められた氏や姓にとらわれずに人材を登用するようにした制度を何というか","冠位十二階","武家諸法度","十七条憲法","氏姓不問"},
            {"604年に役人の心得を示すために、何をさだめたか","十七条憲法","冠位十二階","十七条心得","役人心得"},
            {"推古天皇の政治をたすける摂政という役職についたのは誰か","聖徳太子","中大兄皇子","蘇我馬子","殖栗皇子"},
            {"文化や制度を取り入れるために、聖徳太子が大陸に送った使節を何というか","遣隋使","遣唐使","遣漢使","遣大使"},
            {"遣隋使の最初の使節は誰か","小野妹子","中大兄皇子","中臣鎌足","佐野妹子"},
            {"聖徳太子がいた時代にさかえた文化を何というか","飛鳥文化","奈良文化","聖徳文化","大化文化"},
            {"現存する世界最古の木造建築物で、聖徳太子が建てたとされる寺院は何か","法隆寺","法観寺","明王寺","室生寺"},
            {"645年に起こった中央集権国家を作るための変革を何というか","大化の改新","大化の改革","中央の変革","集権の改新"},
            {"大化の改新で滅ぼされた豪族は何氏か","蘇我氏","物部氏","中臣氏","藤我氏"},
            {"大化の改新の中心人物で後に藤原氏となったのは誰か","中臣鎌足","中大兄皇子","中臣塊","藤原鎌足"},
            {"大化の改新でしめされた、土地と人民を国家の支配のもとにおく制度を何というか","公地公民","公民公地","独裁制","民地支配"},
            {"中大兄皇子は何天皇になったか","天智天皇","持統天皇","斉明天皇","皇極天皇"},
            {"天智天皇の死後、後継をめぐって起こった争いを何というか","壬申の乱","応仁の乱","天智の乱","承久の乱"},
            {"4世紀ごろ、朝鮮半島の南東部に建てられた国は何か","新羅","百済","高句麗","伽耶"},
            {"九州北部の防衛にあたった兵士を何というか","防人","守人","守衛","九兵"},
            {"701年にできた法律を何というか","大宝律令","宝化律令","大化律令","奈良律令"},
            {"法律国家のもとでは、戸籍に登録された6歳以上の人々に何が与えられたか","口分田","土地","お金","家畜"},
            {"口分田を与え、死ねば返させる制度を何というか","班田収授法","墾田永年私財法","班田返還法","口分田返還"},
            {"大宝律令には税が3種類あったが、稲を納める税を何というか","租","庸","調","稲"},
            {"大宝律令には税が3種類あったが、麻を納める税を何というか","庸","租","調","麻"},
            {"大宝律令には税が3種類あったが、特産物を納める税を何というか","調","租","庸","産"},
            {"地方の国ごとにおかれた役人を何というか","国司","都司","国人","国役"},
            {"710年に「唐」の都「長安」にならって、奈良につくられた都を何というか","平城京","平安京","平成京","平長京"},
            {"日本最古の貨幣としては「富本銭」などがあげれるが、広く流通した貨幣としては、708年につくられた何があるか","和同開珎","和銅開珎","永楽通宝","宋銭"},
            {"743年に出された、新しく開墾した土地の私有を認めた法律を何というか","墾田永年私財法","班田収授法","公地公民","新田永年私財法"},
            {"仏教の力にたよって国家を守ろうとした天皇は誰か","聖武天皇","文武天皇","持統天皇","天武天皇"},
            {"聖武天皇が奈良につくった大仏のある寺を何というか","東大寺","善光寺","法隆寺","薬師寺"},
            {"東大寺にある聖武天皇の宝物を収めた建物を何というか","正倉院","宝物庫","宝物院","東大宝庫"},
            {"正倉院の建て方を何というか","校倉造","正倉造","寝殿造","書院造"},
            {"九州におかれた役所を何というか","太宰府","防人","国司","都司"},
            {"日本にわたろうとして何度も遭難し、盲目になりながらも来日した唐の僧は誰か","鑑真","空海","行基","最澄"},
            {"奈良時代にできた最も古い歴史書を何というか","古事記","風土記","日本書紀","三国志"},
            {"地方の国ごとの自然、産物、伝説などを記したものは何か","風土記","日本書紀","三国志","太平記"},
            {"奈良時代にできた和歌集を何というか","万葉集","古今和歌集","新古今和歌集","後撰和歌集"},
            {"奈良時代に栄えた、唐の文化と仏教の影響を強く受けた文化を何というか","天平文化","奈良文化","唐仏文化","大平文化"},
            {"奈良時代に唐へ送った使いを何というか","遣唐使","遣隋使","唐使","送唐使"},
            {"聖武天皇と光明皇后は国ごとに何を建てたか","国分寺・国分尼寺","東大寺","薬師寺","善光寺"},
    };

    public Fragment_2 ( ) {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_2.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_2 newInstance ( String param1 , String param2 ) {
        Fragment_2 fragment = new Fragment_2 ( );
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