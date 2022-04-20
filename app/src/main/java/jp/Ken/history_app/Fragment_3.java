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
 * Use the {@link Fragment_3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_3 extends Fragment {

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
    static final private int QUIZ_COUNT = 90;
    private String alertTitle;

    final ArrayList<ArrayList<String>> quizList = new ArrayList <> (  );

    final String[][] quizData = {
            {"784年に都を長岡京に移し、次いで794年に平安京に移し政治の立て直しをはかった天皇は誰か","桓武天皇","聖武天皇","推古天皇","天武天皇"},
            {"8世紀末に征夷大将軍に任命されたのは誰か","坂上田村麻呂","徳川家康","菅原道真","藤原道長"},
            {"9世紀の初めに唐にわたり帰国後、比叡山に延暦寺を建てたのは誰か","最澄","空海","行基","栄西"},
            {"最澄は何を伝えたか","天台宗","真言宗","浄土宗","日蓮宗"},
            {"最澄とともに唐にわたり帰国後、高野山に金剛峯寺を建てたのは誰か","空海","最澄","行基","鑑真"},
            {"空海は何を伝えたか","真言宗","浄土宗","日蓮宗","天台宗"},
            {"894年に遣唐使に任命されたのは誰か","菅原道真","菅原道長","藤原道長","藤原定家"},
            {"唐は10世紀の初めに滅び、小国の分立をへてやがて中国を統一したのはどこか","宋","高麗","西夏","遼"},
            {"9世紀後半以降に、藤原氏によって行われた政治を何というか","摂関政治","藤原政治","関白政治","大名政治"},
            {"天皇が小さいとき、天皇の代わりに政治を行う役職は何か","摂政","代理人","摂関","関白"},
            {"天皇が成人してから、天皇の代わりに政治を行う役職は何か","関白","摂政","代政","摂関"},
            {"11世紀前半に、子の藤原頼通とともに藤原氏の全盛時代を築いたのは誰か","藤原道長","藤原定家","藤原実定","藤原長道"},
            {"11世紀初め、紫式部は長編小説の何を書いたか","源氏物語","枕草子","平家物語","古今和歌集"},
            {"11世紀初め、清少納言は随筆の何を書いたか","枕草子","源氏物語","平家物語","土佐日記"},
            {"ひらがなやかたかなを何文字というか","かな文字","ひら文字","かた文字","にほん文字"},
            {"平安時代に紀貫之によってまとめられた和歌集は何か","古今和歌集","万葉集","新古今和歌集","拾遺和歌集"},
            {"紀貫之が女性を装って書いた日記を何というか","土佐日記","日本書記","平安日記","装飾日記"},
            {"平安時代の貴族の住宅の造を何というか","寝殿造","貴族院","平安造","書院造"},
            {"日本の風景や人物を描いた絵画を何というか","大和絵","日本絵","和絵","皇風絵"},
            {"枕草子の作者は誰か","清少納言","紫式部","紀貫之","松尾芭蕉"},
            {"源氏物語の作者は誰か","紫式部","清少納言","紀貫之","吉田兼好"},
            {"阿弥陀仏にすがり、極楽浄土に生まれ変わろうという信仰を何というか","浄土信仰","極楽信仰","阿弥陀信仰","不動信仰"},
            {"藤原頼通が京都の宇治に建てた阿弥陀堂を何というか","平等院鳳凰堂","中尊寺金色堂","三十三間堂","東求堂"},
            {"12世紀初め、東北地方の豪族「奥州藤原氏」によって平泉に建てられた阿弥陀堂を何というか","中尊寺金色堂","平等院鳳凰堂","三十三間堂","東求堂"},
            {"貴族や寺社の広大な私有地を何というか","荘園","公有地","貴園","荘院"},
            {"1185年に平氏が滅んだのはどこか","壇ノ浦","外ノ浦","下関","防府"},
            {"1192年に征夷大将軍となり、鎌倉幕府を開いたのは誰か","源頼朝","北条政子","平清盛","平将門"},
            {"将軍と主従関係を結んだ武士を何というか","御家人","用心棒","武家人","将武士"},
            {"鎌倉時代に国ごとにおかれ、軍事・警察の仕事をしたのは何という身分の人か","守護","百姓","商人","地頭"},
            {"鎌倉時代に荘園におかれ、年貢のといたてを行う仕事をしたのは何という身分の人か","地頭","荘人","集人","守護"},
            {"鎌倉幕府で将軍を助けて政治を行う役を何というか","執権","関白","院政","将助"},
            {"執権には代々、何氏がなったか","北条氏","源氏","藤原氏","徳川氏"},
            {"源頼朝の妻は誰か","北条政子","北条重時","北条敦子","北条滝子"},
            {"鎌倉幕府で御家人をまとめる役所を何というか","侍所","公文所","門注所","政所"},
            {"鎌倉幕府で訴えをさばく役所を何というか","門注所","公文所"," 侍所","政所"},
            {"鎌倉幕府で一般政務をする役所を何というか","政所","公文所"," 侍所","門注所"},
            {"御家人が将軍から領地をもらうことを何というか","御恩","奉公","御縁","譲渡"},
            {"御家人が将軍のために戦うことを何というか","奉公","御恩","心将","奉仕"},
            {"御恩と奉公から成り立つ将軍と御家人の関係を何というか","主従関係","上下関係","信頼関係","従属関係"},
            {"主従関係で結ばれた武士が、農民を支配する社会を何というか","封建社会","風化社会","縦社会","武士社会"},
            {"1221年に幕府を倒そうと兵をあげたのは誰か","後鳥羽上皇","平清盛","平将門","北条泰時"},
            {"後鳥羽上皇は1221年に幕府を倒そうと兵をあげた。この戦いは何と呼ばれたか","承久の乱","保元の乱","応仁の乱","源平の乱"},
            {"承久の乱の後、京都におかれた役所を何というか","六波羅探題","御成敗式目","武所","詰所"},
            {"1232年に出された日本初の武家法で、武家社会のならわしをまとめたものを何というか","御成敗式目","武家諸法度","武家目録","武家式目"},
            {"御成敗式目を制定した執権は誰か","北条泰時","北条泰家","北条政子","北条敦子"},
            {"13世紀にモンゴル民族をまとめ中国北方を支配したのは誰か","チンギス=ハン","ジンギス=カン","フビライ=ハン","マルコ=ポーロ"},
            {"チンギス=ハンが1206年に建国した国を何というか","モンゴル帝国","モンゴル大帝国","モンゴル共和国","モンゴル国"},
            {"モンゴル帝国の第五代目皇帝であり、元朝の初代皇帝は誰か","フビライ=ハン","チンギス=ハン","マルコ=ポーロ","モンケ=ハン"},
            {"フビライ=ハンが建国した国を何というか","元","漢","印","門"},
            {"東方見聞録の中で、日本を「黄金の国ジパング」として紹介したのは誰か","マルコ=ポーロ","フビライ=ハン","クトルン","ボクト=ハン"},
            {"マルコ=ポーロがイタリアに帰って書いた本を何というか","東方見聞録","ガリバー旅行記","漂流記","旅行日記"},
            {"元に服従させられた朝鮮の国を何というか","高麗","高句麗","大礼","元鮮"},
            {"1274年と1281年の2度にわたる元の日本襲来を何というか","元寇","文永の役","弘安の役","元の役"},
            {"元の襲来のときの執権は誰か","北条時宗","北条泰時","北条政子","平清盛"},
            {"元寇の後、生活が苦しくなった御家人を救うために出した法令を何というか","徳政令","特別例","改革令","困窮令"},
            {"鎌倉幕府を倒そうと兵をあげた天皇は誰か","後醍醐天皇","後鳥羽天皇","後白河天皇","光明天皇"},
            {"後醍醐天皇の行った天皇中心の政治を何というか","建武の新政","中央集権","天皇国家","王族制度"},
            {"後醍醐天皇にそむいて京都に新しい天皇をたて、再び幕府の政治をおこしたのは誰か","足利尊氏","足利義満","藤原道長","中臣鎌足"},
            {"後醍醐天皇は京都からどこへ逃れたか","吉野","紀伊","尾張","伊勢"},
            {"吉野と京都に二人の天皇がいた時代を何時代というか","南北朝時代","二天時代","双国時代","東西和時代"},
            {"南北中時代に守護は一国を支配する領主になっていくが、このような守護を何というか","守護大名","管領","守護領主","守護大公"},
            {"14世紀ごろ、元を滅ぼして中国を支配した国はどこか","明","清","漢","宋"},
            {"日本の海賊は何と呼ばれていたか","倭寇","バイキング","ならず者","倭族"},
            {"倭寇と区別するために正式な貿易船が持っていた割札を何というか","勘合","勘交","合札","重札"},
            {"日本と明の間で行った貿易を何というか","勘合貿易","日明貿易","倭寇貿易","勘交貿易"},
            {"日本と明の間の貿易を始めた将軍は誰か","足利義満","足利尊氏","徳川家康","足利直義"},
            {"室町幕府で将軍を助ける役職を何というか","管領","摂政","軍補","軍子"},
            {"室町幕府で関東を監視する役所を何というか","鎌倉府","太宰府","目付け","座"},
            {"14世紀末に高麗の後にできた国を何というか","朝鮮","韓国","新羅","百済"},
            {"商人や手工業者の作った同業者の組合を何というか","座","商","労集","商工"},
            {"寺社の門前などで開かれた市を何というか","定期市","神子市","闇市","寺院市"},
            {"室町時代の質屋で高利貸しを行っていたものを何というか","土倉","座","問丸","馬借"},
            {"室町時代に港にあった運送業者を何というか","問丸","海船","寄港","座"},
            {"室町時代に馬で荷物を運んだ運送業者を何というか","馬借","馬車","問屋","送馬"},
            {"村の運営のために、おきてなどを決めた会合を何というか","寄合","束集","村会","集村"},
            {"農村の自治組織を何というか","惣","全","一揆","郷"},
            {"1428年に農民が徳政令を要求して起こした乱を何というか","正長土一揆","山城の国一揆","嘉吉の徳政一揆","島原・天草の一揆"},
            {"下の身分の者が上の身分の者をたおすことを何というか","下克上","革命","挑戦状","掌握"},
            {"1467年に将軍の後継争いと守護大名の対立から京都でおきた戦乱を何というか","応仁の乱","承久の乱","源平の内乱","島原の乱"},
            {"応仁の乱の後、戦乱が100年近く続いた時代を何というか","戦国時代","安土桃山時代","戦乱時代","戦争100年時代"},
            {"守護大名の代わりに実力で領国を治めた大名を何というか","戦国大名","外様大名","譜代大名","軍師大名"},
            {"戦国大名が領国支配のために作った法律を何というか","分国法","配領","共国法","領分法"},
            {"琉球では、15世紀初めに尚氏が沖縄島を統一し首里城を都とする何を建国したか","琉球王国","モンゴル帝国","琉球大国","エイサー"},
            {"蝦夷地とは北海道以北で、主に何民族が住んでいた","アイヌ民族","ウィルタ民族","大和民族","山窩民族"},
            {"足利義満の時代の文化を何というか","北山文化","東山文化","南山文化","西山文化"},
            {"足利義政の時代の文化を何というか","東山文化","北山文化","南山文化","西山文化"},
            {"猿楽や田楽などの芸能をもとに、「観阿弥」と「世阿弥」親子は何を大成したか","能","狂言","歌舞伎","落語"},
            {"中国にわたり新しい絵の技法を学び、帰国後に日本の水墨画を完成させたのは誰か","雪舟","海舟","北斎","広重"},
            {"一寸法師などの絵入りの物語を何というか","御伽草子","仮名草子","浮世草子","枕草子"},
            {"能の合間に演じられた民衆の生活や感情を表現したものを何というか","狂言","歌舞伎","落語","浄瑠璃"},
    };

    public Fragment_3 ( ) {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_3.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_3 newInstance ( String param1 , String param2 ) {
        Fragment_3 fragment = new Fragment_3 ( );
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