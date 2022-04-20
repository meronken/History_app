package jp.Ken.history_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MyDialogFragment extends DialogFragment {

    private int quiz_count = 30;

    @NonNull
    @Override
    public Dialog onCreateDialog( @Nullable Bundle savedInstanceState) {

        //問題数
        String[] choices = {"30問", "50問", "100問","150問","200問","250問","300問","440問"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("出題数を選択")
                .setPositiveButton("決定", new DialogInterface.OnClickListener() {
                    public void onClick( DialogInterface dialog, int id) {
                        //問題画面に遷移
                        FragmentManager fragmentManager = getActivity ().getSupportFragmentManager ();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ();
                        Fragment_7 fragment_7 = new Fragment_7 ();
                        //選択した問題数の値を渡す
                        Bundle bundle = new Bundle ();
                        bundle.putInt ( "quiz_num",quiz_count );
                        fragment_7.setArguments ( bundle );

                        Bundle bundle2 = new Bundle () ;
                        bundle2.putBoolean ( "Button_Check",false );
                        fragmentManager.setFragmentResult ( "Button_Check",bundle2 );



                        fragmentTransaction.replace ( R.id.Fragment,fragment_7 );
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit ();
                    }

                })

                .setSingleChoiceItems(choices, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //選択した問題数を判定　
                        switch (which){
                            case 0:
                                quiz_count = 30;
                                break;
                            case 1:
                                quiz_count = 50;
                                break;
                            case 2:
                                quiz_count = 100;
                                break;
                            case 3:
                                quiz_count = 150;
                                break;
                            case 4:
                                quiz_count = 200;
                                break;
                            case 5:
                                quiz_count = 250;
                                break;
                            case 6:
                                quiz_count = 300;
                                break;
                            case 7:
                                quiz_count = 440;
                                break;
                        }
                    }
                });


        return builder.create();
    }
}
