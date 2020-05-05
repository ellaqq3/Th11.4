package com.example.th113;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    EditText Fontti;
    EditText Leveys;
    EditText Rivit;
    EditText Väri;
    EditText kysyNimi;
    Button button;
    Button button_s;
    Button button_name;
    Spinner spinner;
    ArrayList<String> lista = new ArrayList<>();
    ArrayList<String> kieli_lista = new ArrayList<>();

    public static String nameSelection;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        kieli_lista.add("Valitse kieli");
        kieli_lista.add("Suomi");
        kieli_lista.add("Ruotsi");
        kieli_lista.add("Englanti");
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = getView().findViewById(R.id.button);
        button_s = getView().findViewById(R.id.button3);
        button_name = getView().findViewById(R.id.button4);
        spinner = (Spinner) getView().findViewById(R.id.spinner);


        ////// button for editig text////////////////////////////////////////////////////////

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fontti = getView().findViewById(R.id.editText1);
                Leveys = getView().findViewById(R.id.editText2);
                Rivit = getView().findViewById(R.id.editText4);
                Väri = getView().findViewById(R.id.editText3);

                String fonttiS = Fontti.getText().toString();
                String leveysS = Leveys.getText().toString();
                String rivitS = Rivit.getText().toString();
                String väriS = Väri.getText().toString();

                lista.add(fonttiS);
                lista.add(leveysS);
                lista.add(rivitS);
                lista.add(väriS);
                ((MainActivity) getActivity()).editTextView();

            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////

        ////// button for switch ///////////////////////////////////////////////////////////////

        button_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Switch sw;
                sw = (Switch) getView().findViewById(R.id.switch1);
                if (sw.isChecked()) {
                    ((MainActivity) getActivity()).isAllowed();
                    System.out.println("Switch päällä, toimii");
                } else {
                    ((MainActivity) getActivity()).isntAllowed();
                    System.out.println("Switch ei päällä, toimii");
                }
            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////

        ////// button for getting the name ///////////////////////////////////////////////////////////////

        button_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            kysyNimi = getView().findViewById(R.id.editText5);
            String nimi = kysyNimi.getText().toString();
            nameSelection = nimi;
            sendToActivity();
            }

        });
        ///////////////////////////////////////////////////////////////////////////////////////


        //////////////  Spinneri  -> ///////////////////////////////////////////////////////////

        //Luodaan ArrayAdapter sijainnille//
        ArrayAdapter<String> adapterLocation = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, kieli_lista);
        //Määritellään spinnerin asettelutyyli//
        adapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //lisätään adapteri spinneriin//
        spinner.setAdapter(adapterLocation);

        String kieli = spinner.getSelectedItem().toString();

        ///////////////////////////////////////////////////////////////////////////////////////

    }

    public void sendToActivity(){
        Intent intent = new Intent(getActivity().getBaseContext(), MainActivity.class);
        startActivity(intent);
    }

    public ArrayList<String> sendInfo() {
        return lista;
    }
}


