package com.fitucab.ds1617b.fitucab.UI.Fragments.M01;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fitucab.ds1617b.fitucab.Model.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class M01HomeFragment extends Fragment {

    private Button _btnIrIniciarSesion;
    private Button _btnIrRegistro;
    private View _view;
    private OnFragmentSwap _callBack;

    public M01HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Una vez la activity llama a un fragment se ejecuta este metodo
     * @param activity recibe la activity que llamo o instancio al fragment
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            _callBack = (OnFragmentSwap) activity;
        } catch (ClassCastException e) {


            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view =  inflater.inflate(R.layout.fragment_m01_home, container, false);
        setupViewValues();
        manageChangeFragmentLogin();
        manageChangeFragmentSignUp();
        return _view;
    }

    private void manageChangeFragmentLogin() {
        _btnIrIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _callBack.onSwap("M01LoginFragment",null);
            }
        });
    }
    private void manageChangeFragmentSignUp() {
        _btnIrRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _callBack.onSwap("M01SignUpFragment",null);
            }
        });

    }

    private void setupViewValues() {
        _btnIrIniciarSesion = (Button) _view.findViewById(R.id.btn_m01_iniciarSesion);
        _btnIrRegistro = (Button) _view.findViewById(R.id.btn_m01_comienza);

    }

}