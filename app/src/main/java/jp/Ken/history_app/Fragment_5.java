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
 * Use the {@link Fragment_5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_5 extends Fragment {

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

    //問題数,問題,正解,選択肢１,選択肢2,選択肢３,選択肢4
    final String[][] quizData= {
            {"明治時代に一部の藩の出身者によって行われた政治を何というか","藩閥政治","摂関政治","藩主政治","王権政治"},
            {"征韓論を唱え、政府をさった薩摩藩出身者は誰か","西郷隆盛","坂本龍馬","島津斉彬","西郷吉兵衛"},
            {"1877年に西郷隆盛が起こした乱を何というか","西南戦争"," 薩英戦争","東北戦争"," 秋田戦争"},
            {"1874年に政府に対して出された国会開設の要求を何というか","民撰議院開設の要求","国会開設の要求","議員開設要求","議院開設の要求"},
            {"国会開設の要求の中心人物は誰か","板垣退助","伊藤博文","森鴎外","西郷隆盛"},
            {"板垣退助が高知県に作った政治団体を何というか","立志社","イエスズ会","立憲同志会","帝国財政革新会"},
            {"板垣退助が中心となって行った運動を何というか","自由民権運動","自由民主運動","平和運動","国民運動"},
            {"1880年に自由民権運動の代表が作った、国会開設を要求する団体を何というか","国会期成同盟","国会要求組合","国会開設同盟","自由党"},
            {"板垣退助が作った急進的な議会政治をめざす政党を何というか","自由党","立憲改進党","憲政党","立憲革新党"},
            {"大隈重信が作った財産家を中心とする議会政治を理想とした政党を何というか","立憲改進党","憲政党","立憲革新党","自由党"},
            {"1884年に埼玉県で貧しい農民が起こした事件を何というか","秩父事件","相馬事件","大逆事件","大津事件"},
            {"明治政府は憲法を作るためにどこの憲法を調べたか","ドイツ","フランス","イギリス","アメリカ"},
            {"ドイツの国へ憲法を調べに行き、初代内閣総理大臣になったのは誰か","伊藤博文","板垣退助","福沢諭吉","黒田清隆"},
            {"日本での初めての憲法を何というか","大日本帝国憲法","日本国憲法","大日本憲法","帝国憲法"},
            {"日本と清との間に起きた戦争を何というか","日清戦争","日中戦争","清日戦争","日本清戦争"},
            {"日清戦争の翌年に結ばれた講和条約を何というか","下関条約","ポーツマス条約","樺太・千島交換条約","日米和親条約"},
            {"下関条約で日本が清からもらった領土はどこか","遼東半島","台湾","樺太","千島"},
            {"1899年に中国の人々が帝国主義諸国の侵略に反対してい起こした事件を何というか","義和団事件","秩父事件","大津事件","相馬事件"},
            {"1904年に起きた日本とロシアとの戦争を何というか","日露戦争","日清戦争","戊辰戦争","アヘン戦争"},
            {"1902年にロシアの南下をおさえるために日本と同盟を結んだ国はどこか","イギリス","フランス","アメリカ","ドイツ"},
            {"日露戦争後の講和条約を何というか","ポーツマス条約","樺太・千島交換条約","日米和親条約","下関条約"},
            {"ポーツマス条約で日本はロシアからどこの南半分をもらったか","樺太","千島","台湾","ハワイ"},
            {"1910年に日本は韓国に対して何をしたか","韓国併合","文化統制","借金","請求"},
            {"義和団事件の後、中国の近代化をすすめたのは誰か","孫文","毛沢東","孔子","孫正義"},
            {"孫文がかかげた指導方法を何主義というか","三民主義","民主主義","立憲主義","資本主義"},
            {"1911年に孫文が中心となって清を滅ぼした革命を何というか","辛亥革命","市民革命","名誉革命","ピューリタン革命"},
            {"清が滅んで中国に新しくできた国を何というか","中華民国","中華人民共和国","漢","明"},
            {"1894年にイギリスとの間で撤廃された不平等条約は何か","領事裁判権","関税自主権","外国人租借地","鉄道附属地"},
            {"1911年にアメリカとの間で回復された不平等条約は何か","関税自主権","領事裁判権","外国人租借地","鉄道附属地"},
            {"日清戦争の賠償金で作った製鉄所は何か","八幡製鉄所","釜石製鉄所","室蘭製鉄所","加古川製鉄所"},
            {"足尾銅山の公害反対運動を起こした国会議員は誰か","田中正造","伊藤博文","福沢諭吉","板垣退助"},
            {"三井・三菱・住友などの大企業を何というか","財閥","派閥","名家","最大手"},
            {"労働条件の改善を求めて、労働者が資本家に対して起こした争議を何というか","労働争議","労働戦争","労働会合","労働資本"},
            {"1910年に天皇暗殺を計画したとして、社会主義者を死刑にした事件を何というか","大逆事件","大津事件","義和団事件","秩父事件"},
            {"日本の伝統美術を復興した日本人は誰か","岡倉天心","福沢諭吉","森鴎外","西郷隆盛"},
            {"岡倉天心とともに日本の伝統美術を復興したアメリカ人は誰か","フェノロサ","ペリー","ゴッホ","ピカソ"},
            {"欧米の列強に対抗するため、経済を発展させ軍隊を強くしようとして進められた政策を何というか","富国強兵","鉄血政策","欧化政策","軍国政策"},
            {"1868年3月、新政府は新しい政治の方針として、天皇が神に誓うという形で何が出されたか","五箇条の御誓文","王政復古の大号令","版籍奉還","朝廷"},
            {"1873年、満20歳以上の男子に兵役の義務を負わせる何が出されたか","徴兵令","出兵令","総動員令","兵役令"},
            {"1873年、税収の安定をめざして、何が始められたか","地租改正","富国強兵","廃藩置県","徴兵令"},
            {"1869年、それまで藩主が治めていたいた土地と人民とを朝廷に返させる何が行われたか","版籍奉還","廃藩置県","王政復古の大号令","大政奉還"},
            {"政府は近代的な産業を育てる政策を行ったが、このことを何というか","殖産興業","近代産業","明治産業","欧化政策"},
            {"官営模範工場として群馬県に何が作られたか","富岡製糸場","八幡製鉄所","弦月工場","中京煉瓦工場"},
            {"学問のすすめを書いた人は誰か","福沢諭吉","森鴎外","樋口一葉","野口英世"},
            {"1871年、日本は清との間で対等な立場で何を結んだか","日清修好条規","日米和親条約","日米修好通商条約","下関条約"},
            {"国境が不明確であったロシアとの間では、1875年に何条約が結ばれたか","樺太・千島交換条約","英露協商","日露和親条約","樺太島仮規則"},
            {"琉球藩は1879年に何になったか","沖縄県","琉球王国","龍国","中華民国"},
            {"日本の文化・制度・風習などを西洋化することで、日本が文明国であることを欧米諸国に示そうとした政策は何か","欧化政策","欧米化","文明開化","明治維新"},
            {"ロシアがフランスとドイツとともに遼東半島の返還を日本に勧告したことを何というか","三国干渉","三帝同盟","遼東勧告令","遼東返還令"},
            {"1902年ロシアに対して利害を同じくする日本とイギリスは何を結んだか","日英同盟","三国同盟","日英通商航海条約","薩長同盟"},
            {"にごりえやたけくらべを書いたのは誰か","樋口一葉","ペリー","森鴎外","福沢諭吉"},
            {"舞姫などを書いたのは誰か","森鴎外","樋口一葉","福沢諭吉","フェノロサ"},
            {"1882年から1915年まで存続したドイツ、オーストラリア、イタリア間で結ばれた同盟は何か","三国同盟","日独伊三国同盟","三国協商","三帝同盟"},
            {"三国同盟のとった政策を何というか","3B政策","3C政策","3A政策","3D政策"},
            {"三国同盟に対してイギリスはフランスとロシアとの間で何を結んだか","三国協商","三帝同盟","防共協定","鋼鉄協約"},
            {"三国協商のとった政策を何というか","3C政策","3A政策","3B政策","3D政策"},
            {"サラエボ事件をきっかけに1914年に始まった戦争は何か","第一次世界大戦","第二次世界大戦","第一次バルカン戦争","第二次バルカン戦争"},
            {"1915年、日本が中国に出した要求を何というか","二十一カ条の要求","民撰議院開設の要求","十三カ条の要求","五十カ条の要求"},
            {"1917年に起きた世界最初の社会主義革命を何というか","ロシア革命","フランス革命","ピューリタン革命","市民革命"},
            {"ロシア革命の指導者は誰か","レーニン","スターリン","マレンコフ","フルシチョフ"},
            {"ロシア革命の結果、できた国は何か","ソビエト社会主義共和国連邦","ロシア連邦","ロシア帝国","モスクワ大公国"},
            {"ロシア革命の広がりを恐れて日本は何をしたか","シベリア出兵","シベリア撤兵","米騒動","日独伊三国同盟"},
            {"パリで結ばれた第一次世界大戦の講和条約を何というか","ベルサイユ条約","パリ条約 ","ロンドン条約","ローザンヌ条約"},
            {"1920年、アメリカ大統領の提唱でつくられた国際機構は何か","国際連盟","国際連合","国際通貨基金","世界保健機関"},
            {"国際連盟結成のときのアメリカ大統領は誰か","ウィルソン","ケネディ","ルーズベルト","レーガン"},
            {"1921年に開かれた軍備縮小の会議は何か","ワシントン会議","ビルダーバーグ会議","ダボス会議","日米欧委員会"},
            {"1919年にドイツで制定された、当時最も民主的といわれた憲法は何か","ワイマール憲法","ドイツ国憲法","ドイツ基本法","ドイツ民主憲法"},
            {"1919年、朝鮮で起きた独立運動を何というか","三・一独立運動","五・四運動","護憲運動","自由民権運動"},
            {"1919年、中国でおきた講和条約反対運動を何というか","五・四運動","三・一独立運動","護憲運動","自由民権運動"},
            {"インドの独立運動の指導者は誰か","ガンジー","ウィルソン","ヒトラー","レーニン"},
            {"ガンジーの唱えた方針は何か","非暴力・不服従","非核化・不公平","非戦論・不道徳","非課税・不利益"},
            {"1918年に民衆が米の安売りを求めておこした暴動を何というか","米騒動","打ちこわし","米暴動","米争議"},
            {"米騒動が最初に起きたのは何県か","富山県","新潟県","北海道","秋田県"},
            {"女性の地位向上を目指して1911年に結成された組織は何か","青鞜社","全国水平社","立憲政友会","JA全国女性組織協議会"},
            {"青鞜社の中心となったのは誰か","平塚らいてふ","与謝野晶子","樋口一葉","岡本かの子"},
            {"部落差別解放を求めて1992年に結成された組織は何か","全国水平社","大政翼賛会","青鞜社","労働組合"},
            {"1923年におきた大地震は何か","関東大震災","昭和三陸地震","阪神・淡路大震災","釧路沖地震"},
            {"尾崎行雄らが議会中心の政治を求めた運動は何か","護憲運動","三・一独立運動","五・四運動","婦人参政権運動"},
            {"1918年、日本初の政党内閣を作ったのは誰か","原敬","伊藤博文","大隈重信","犬養毅"},
            {"原敬の政党を何というか","立憲政友会","日本共産党","社会民主党","自由民主党"},
            {"大正時代にさかんだった民主主義を求める動きを何というか","大正デモクラシー","五・四運動","三・一独立運動","婦人参政権運動"},
            {"1925年に成立した選挙法を何というか","普通選挙法","衆議院議員選挙法","公職選挙法","参議院議員選挙法"},
            {"普通選挙法で選挙権が与えられた人はどんな人か","満25歳以上男子","20歳以上","満25歳以上女子","満25歳以上"},
            {"普通選挙法と同時に制定された法律は何か","治安維持法","治安警察法","郵便法","食品衛生法"},
            {"白樺派で友情の作者は誰か","武者小路実篤","夏目漱石","福沢諭吉","志賀直哉"},
            {"白樺派で暗夜行路の作者は誰か","志賀直哉","芥川龍之介","武者小路実篤","小林多喜二"},
            {"羅生門などの作者は誰か","芥川龍之介","武者小路実篤","太宰治","小林多喜二"},
            {"プロレタリア文学者で蟹工船の作者は誰か","小林多喜二","夏目漱石","与謝野晶子","志賀直哉"},
            {"1925年には何の放送が始まったか","ラジオ","NHK","バラエティ","アニメ"},
            {"1925年に成立した治安維持法の当初の目的は何を取り締まるものだったか","共産主義","部落差別","立憲政友会","全国水平社"},
    };

    public Fragment_5 ( ) {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_5.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_5 newInstance ( String param1 , String param2 ) {
        Fragment_5 fragment = new Fragment_5 ( );
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