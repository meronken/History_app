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
 * Use the {@link Fragment_4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_4 extends Fragment {

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
    static final private int QUIZ_COUNT = 120;
    private String alertTitle;

    final ArrayList<ArrayList<String>> quizList = new ArrayList <> (  );

    //問題数,問題,正解,選択肢１,選択肢2,選択肢３,選択肢4
    final String[][] quizData = {
            {"1543年に日本に伝わった武器は何か","鉄砲","大砲","剣","薙刀"},
            {"鉄砲を伝えたのはどこの国か","ポルトガル","モンゴル","朝鮮","中国"},
            {"鉄砲伝来の地はどこか","種子島","硫黄島","軍艦島","屋久島"},
            {"1549年に日本に伝わった宗教は何か","キリスト教","イスラム教","ヒンドゥー教","ユダヤ教"},
            {"キリスト教を日本に伝えたのは誰か","フランシスコ=ザビエル","イエスキリスト","マゼラン","マルコ=ポーロ"},
            {"フランシスコ=ザビエルは何という団体の一員であったか","イエスズ会","正教会","十字軍","カトリック教会"},
            {"キリスト教を保護し、自らが信者となった大名を何というか","キリシタン大名","キリスト大名","プロテスタント","ルネサンス"},
            {"日本の商人、南蛮人、中国人などとの間で行われていた貿易を何というか","南蛮貿易","多国籍貿易","朱印船貿易","勘合防衛"},
            {"14世紀にイタリアで始まった古典古代の文化を復興しようとする動きを何というか","ルネサンス","プロテクト","スパルタン","宗教改革"},
            {"ローマ教皇の権威が衰え、堕落した教会を改革しキリスト教本来の精神に戻ろうとする改革を何というか","宗教改革","キリスト改革","宗祖改革","革命改革"},
            {"宗教改革の中心人物となったのは誰か","ルター","コロンブス","マゼラン","フランシスコ=ザビエル"},
            {"自己の犯した罪の償いを免除されると信じられた、教皇が発行する証書を何というか","免罪符","太宰府","逃罪符","取除罪符"},
            {"ルターやカルバンらの、カトリック教会に反対して改革を進めた人々を何というか","プロテスタント","革命家","キリシタン","ルネサンス"},
            {"プロテスタントに対して、従来のキリスト教の教えを何というか","カトリック","ルネサンス","イエスズ","旧キリスト"},
            {"調味料や肉の保存料として、ヨーロッパに広まったアジアの特産物は何か","香辛料","山椒","甘味料","酸味料"},
            {"1492年に西インド諸島に到達したのは誰か","コロンブス","ドレーク","マゼラン","バスコ=ダ=ガマ"},
            {"1498年にアフリカの喜望峰を中継してインドへ到達したのは誰か","バスコ=ダ=ガマ","マゼラン","コロンブス","ドレーク"},
            {"1522年に世界一周を達成した船隊を率いていたのは誰か","マゼラン","バスコ=ダ=ガマ","コロンブス","ドレーク"},
            {"正式な貿易船であることを示す朱印状を携えた貿易を何というか","朱印船貿易","南蛮貿易","勘合貿易","日明貿易"},
            {"1637年に九州でおきたキリスト教徒の一揆を何というか","島原・天草一気","山城の国一揆","加賀の一向一揆","正長の土一揆"},
            {"今川義元を桶狭間の戦いで破ったあとに勢力を広げた尾張の戦国大名は誰か","織田信長","徳川家康","明智光秀","豊臣秀吉"},
            {"織田信長は鉄砲を使った戦いで、甲斐び武田勝頼を何の戦いで破ったか","長篠の戦い","壇ノ浦の戦い","関ヶ原の戦い","桶狭間の戦い"},
            {"島原の乱のときの将軍は誰か","徳川家光","徳川家康","徳川秀忠","徳川家綱"},
            {"仏教徒であることを寺院に保証させる制度を何というか","寺請制度","宗門改帳","生類憐みの令","寺保制"},
            {"仏教徒であることを寺院に保証させる制度で人々の名前は何に記録されたか","宗門改帳","聖書","公事方御定書","解体新書"},
            {"キリスト教の信者を発見するために用いた方法は何か","絵踏","踏絵","画踏","踏画"},
            {"1693年に日本への来航を禁止された国はどこか","ポルトガル","インド","モンゴル","朝鮮"},
            {"日本人の海外渡航を禁止し、外国との交際を制限した政策を何というか","鎖国","閉国","独立国","孤国"},
            {"江戸時代に鎖国政策の一環として造られた人工の島は何か","出島","開島","公島","貿島"},
            {"将軍が代わるごとに朝鮮から来た使いを何というか","朝鮮通信使","遣唐使","遣隋使","岩倉使節団"},
            {"1644年に明に代わって中国を支配した国を何というか","清","秦","漢","宋"},
            {"金貨や銀貨の質を落として幕府の財政難を切り抜けようとした5代将軍は誰か","徳川綱吉","徳川家綱","徳川家康","徳川家光"},
            {"5代将軍の出した極端な動物愛護の法律を何というか","生類憐みの令","動物愛護令","生類憐れみ制","動物保護令"},
            {"江戸幕府8代将軍は誰か","徳川吉宗","徳川家光","徳川綱吉","徳川綱吉"},
            {"徳川吉宗が行った政治改革を何というか","享保の改革","天保の改革","寛政の改革","天明のききん"},
            {"徳川吉宗が民衆の意見を聞くために江戸に置いたものを何というか","目安箱","ご意見箱","見聞箱","江戸箱"},
            {"徳川吉宗が作った裁判の基準となる法令集を何というか","公事方御定書","生類憐れみの令","宗門改帳","寺請制度"},
            {"徳川吉宗の後、18世紀後半に老中となったのは誰か","田沼意次","松平定信","水野忠邦","吉田松陰"},
            {"江戸時代に同業者どうしで結成された組合を何というか","株仲間","労働組合","同業仲間","商人集"},
            {"田沼意次の政治が乱れたのは大商人から何をもらったからか","わいろ","米","鉄砲","宝石"},
            {"田沼意次の政治の後に起きたききんを何というか","天明のききん","享保のききん","宝暦のききん","寛永のききん"},
            {"都市の町人が米商人をおそうことを何というか","打ちこわし","ぶっ壊し","百姓一揆","米騒動"},
            {"18世紀末、田沼意次に代わり老中となったのは誰か","松平定信","水野忠邦","吉田松陰","大塩平八郎"},
            {"松平定信の行った政治改革を何というか","寛政の改革","享保の改革","天保の改革","正徳の治"},
            {"松平定信が朱子学以外の学問を禁止した事を何というか","寛政異学の禁","異端審問","異学禁制","学問統一"},
            {"18世紀末ごろから日本沿岸にあらわれはじめた外国船に対して1825年に出された命令は何か","異国船打払令","異国船侵入禁止令","外国船排除令","外国船殲滅令"},
            {"1837年にもと大阪町奉行所の役人が中心となり、大阪で起こした反乱を何というか","大塩平八郎の乱","島原の乱","太平天国の乱","由井正雪の乱"},
            {"19世紀の中ごろ、江戸幕府の改革を行った老中は誰か","水野忠邦","松平定信","田沼意次","井伊直弼"},
            {"水野忠邦の行った改革を何というか","天保の改革","寛政の改革","享保の改革","田沼の改革"},
            {"外国人を打ち払えという論を何というか","攘夷論","殲滅論","異国論","打払論"},
            {"朝廷の権威を高めようという論を何というか","尊王論","朝威論","天皇論","上王論"},
            {"尊王攘夷が結びついた運動を何というか","尊王攘夷運動","攘夷運動","尊王運動","尊王攘夷結束運動"},
            {"井伊直弼が尊王攘夷の考えをとなえる人々を厳しく取り締まった事件を何というか","安政の大獄","桜田門外の変","紫衣事件"," 蛮社の獄"},
            {"1860年に井伊直弼が水戸浪士に暗殺された事件を何というか","桜田門外の変","安政の大獄","坂下門外の変","天誅組の変"},
            {"1866年ごろ、各地で起きた打ちこわしや一揆を何というか","世直し","世作り","打ちこわし","革命"},
            {"1867年に幕府は政権を天皇に返すと申し出たが、それを何というか","大政奉還","政権返還","大政返還","政権奉還"},
            {"旧幕府軍と新政府軍との戦いを何というか","戊辰戦争","下関戦争","西南戦争","日清戦争"},
            {"織田信長が今川義元を破った戦いを何というか","桶狭間の戦い","長篠の戦い","壇ノ浦の戦い","関ヶ原の戦い"},
            {"織田信長に追放された室町幕府最後の将軍は誰か","足利義昭","足利義政","足利尊氏","足利義満"},
            {"織田信長が琵琶湖のほとりに築いた城を何というか","安土城","姫路城","小田原城","大阪城"},
            {"織田信長が焼きうちにした天台宗の寺は何という寺か","延暦寺","鹿苑寺","慈照寺","東大寺"},
            {"織田信長が出した、自由に商工業ができる法令を何というか","楽市楽座令","極楽浄土","自由商業化","楽一楽座"},
            {"織田信長の跡を継いで全国統一を成し遂げたのは誰か","豊臣秀吉","徳川家康","明智光秀","狩野永徳"},
            {"豊臣秀吉は天皇の権利を利用するため、何という位についたか","関白","征夷大将軍","摂政","内大臣"},
            {"豊臣秀吉は田畑の面積や収穫高、土地の耕作者を調べたがこれを何というかあ","太閤検地","兵農分離","刀狩","禁中並公家諸法度"},
            {"豊臣秀吉は一揆を防ぐために農民から武器を取り上げたが、これを何というか","刀狩","武器狩","武器禁令","刀奪"},
            {"検地や刀狩で武士と農民を区別する政策を何というか","兵農分離","武農分離","兵農区別","武民選別"},
            {"茶の湯を大成させた人は誰か","千利休","シャクシャイン","松尾芭蕉","近松門左衛門"},
            {"歌舞伎踊りを始めたのは誰か","出雲の阿国","井原西鶴","本居宣長","大塩平八郎"},
            {"障壁画を大成させた人は誰か","狩野瑛永徳","雪舟","天草四郎","水野忠邦"},
            {"織田信長や豊臣秀吉の時代に栄えた、豪華で雄大な文化を何というか","桃山文化","国風文化","元禄文化","東山文化"},
            {"江戸時代の大名の領地とその支配のしくみを何というか","藩","荘園","株仲間","5人組"},
            {"関ヶ原の戦いの前から徳川氏の家臣であった大名を何というか","譜代大名","外様大名","親藩大名","キリシタン大名"},
            {"江戸幕府において、将軍のすぐ下にあって政治の運営にあたった役職を何というか","老中","大老","御用人","京都所司代"},
            {"江戸幕府は大名の統制のために何を定めたか","武家諸法度","参勤交代","公事方御定書","禁中並公家諸法度"},
            {"大名を1年おきに領地と江戸を往復させる制度を何というか","参勤交代","三勤交代","大名大移動","大名交代"},
            {"17世紀後半、アイヌ首長は誰か","シャクシャイン","コマシャイン","オニビシ","大河内善兵衛"},
            {"儒学のなかで、身分秩序を重視する学問を何というか","朱子学","蘭学","国学","古学"},
            {"日本永代蔵などの浮世草子を書いたのは誰か","井原西鶴","清少納言","雪舟","本居宣長"},
            {"曽根崎心中などの人形浄瑠璃の台本を描いたのは誰か","近松門左衛門","松尾芭蕉","十返舎一九","出雲の阿国"},
            {"紀行文の奥の細道を書いたのは誰か","松尾芭蕉","近松門左衛門","十返舎一九","井原西鶴"},
            {"日本の古典を研究し、日本古来の文化を明らかにしようとする学問は何か","国学","蘭学","古学","朱子学"},
            {"国学という学問を完成させたのは誰か","本居宣長","杉田玄白","孔子","前野良沢"},
            {"オランダ語を通してヨーロッパの文化を学ぼうとする学問は何か","蘭学","国学","古学","朱子学"},
            {"前野良沢や杉田玄白らは、ヨーロッパの解剖書を翻訳した何を出版したか","解体新書","解剖書","解体書","新解体書"},
            {"19世紀初めに、江戸を中心に栄えた文化を何というか","化政文化","元禄文化","桃山文化","国風文化"},
            {"日本国中を測量してまわり、初めて実測により日本地図を完成させた人は誰か","伊能忠敬","杉田玄白","松尾芭蕉","千利休"},
            {"浮世絵の風景画で富嶽三十六景を描いたのは誰か","葛飾北斎","ゴッホ","歌川広重","小林一茶"},
            {"浮世絵の風景画で東海道五十三次を描いたのは誰か","歌川広重","葛飾北斎","雪舟","ミケランジェロ"},
            {"子供たちに文字の読み・書き、場所によってはそろばんを教える庶民の教育施設のことを何というか","寺子屋","蔵屋敷","問屋","家屋"},
            {"イギリスのエリザベス1世やフランスのルイ14世などが行った専制政治を何というか","絶対王政","絶対王権","独裁政治","支配王政"},
            {"商工業者などの市民階級が、自由と平等を目指して国王の専制政治を倒した革命を何というか","市民革命","名誉革命","ピューリタン革命","フランス革命"},
            {"1642年にイギリスでクロムウェルが市民を率いて起こした革命を何というか","ピューリタン革命","フランス革命","市民革命","名誉革命"},
            {"1688年にイギリスで起こった、血を流さずに行われた改革を何というか","名誉革命","ピューリタン革命","市民革命","フランス革命"},
            {"1689年に、新国王とイギリス議会の間で結ばれた政治の取り決めを何というか","権利の章典","ハンムラビ法典","フランス民法典","連合王国の憲法"},
            {"法の精神により、司法・立法・行政の三権による三権分立を説いた啓蒙思想は誰か","モンテスキュー","ルソー","ロック","レッシング"},
            {"社会契約論により、人民主権の考えを説いた啓蒙思想は誰か","ルソー","ロック","モンテスキュー","レッシング"},
            {"1775年に、アメリカで起こった戦争を何というか","アメリカ独立戦争","南北戦争","クリミア戦争","米英戦争"},
            {"1776年にアメリカで、人間の平等、自由などが宣言される。この宣言を何というか","独立宣言","人権宣言","アメリカ宣言","開国宣言"},
            {"アメリカの初代大統領となった人物は誰か","ワシントン","リンカーン","トランプ","アダムズ"},
            {"1789年にパリの民衆がバスティーユ監獄を襲撃してい起こった革命を何というか","フランス革命","ピューリタン革命","市民革命","名誉革命"},
            {"フランス革命の革命の結果、何という宣言が出されたか","人権宣言","ポツダム宣言","独立宣言","開国宣言"},
            {"1804年、国民投票で皇帝となり、ヨーロッパの大部分を支配した人物は誰か","ナポレオン","ルイ14世","クロムウェル","シモン"},
            {"18世紀後半イギリスで始まった、技術の発達による産業と社会のしくみの変化を何というか","産業革命","グローバル化","高度経済成長","産業成長期"},
            {"資本家が労働者を雇い、利益を目的として、自由に生産を行っていくしくみを何というか","資本主義","社会主義","民主主義","完璧主義"},
            {"1871年にドイツ帝国の宰相ビスマルクによって進められた政策を何というか","鉄血政策","熱血政策","独裁政策","服属政策"},
            {"アメリカで1861年に起こった戦争を何というか","南北戦争","アメリカ独立戦争","太平洋戦争","アヘン戦争"},
            {"アメリカの南北戦争で、北部を勝利に導いた指導者は誰か","リンカーン","ワシントン","アダムズ","マディソン"},
            {"アメリカの南北戦争で、対立の焦点となっていた制度は何か","奴隷制度","人種制度","労働制度","植民地制度"},
            {"1840年にイギリスと清の間に起こった戦争は何か","アヘン戦争","南北戦争","英西戦争","クリミア戦争"},
            {"アヘン戦争の結果、何という条約が結ばれたか","南京条約","ポーツマス条約","下関条約","北京条約"},
            {"アヘン戦争後、洪秀全が農民を率いてつくった国を何というか","太平天国","満州国","中華人民共和国","清"},
            {"1857年、イギリスに対してインド国民が起こした大規模な反乱を何というか","インドの大反乱","インドの謀反","インドの大革命","インドの乱"},
            {"1866年、土佐藩の坂本龍馬の中立によって何が結ばれたか","薩長同盟","藩同盟","日英通商航海条約","日清修好条規"},
            {"1854年、幕府はアメリカとの間で何という条約を結んだか","日米和親条約","日英通商航海条約","","日清修好条規"},
            {"1858年、幕府はアメリカとの間で何という条約を結んだか","日米修好通商条約","日米和親条約","日英通商航海条約","樺太・千島交換条約"},
            {"1867年12月、朝廷は天皇を中心とする政治に戻すことを宣言したが、これを何というか","王政復古の大号令","大政奉還","版籍奉還","朝廷返還"},
            {"徳川家康が豊臣家を滅ぼした1614年と1615年の2度にわたる戦いを何というか","大坂の陣","関ヶ原の戦い","桶狭間の戦い","壇ノ浦の戦い"},
            {"江戸幕府の役職で、大名や朝廷を監視した職は何か","大目付","老中","大老","目付"},
            {"江戸幕府の役職で、老中の補佐と旗本や御家人を観察した職は何か","若年寄","目付","大老","老中"},
    };


    public Fragment_4 ( ) {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_4.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_4 newInstance ( String param1 , String param2 ) {
        Fragment_4 fragment = new Fragment_4 ( );
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