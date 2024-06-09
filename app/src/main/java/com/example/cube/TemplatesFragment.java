package com.example.cube;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TemplatesFragment extends Fragment {

    Button addText;
    Button displayWeather;
    Dialog textTemplateDialog;
    Button saveTemplate;
    EditText etTemplateName;
    EditText etTemplate;
    String newTemplateName = "";
    String newTemplate = "";
    Map<String, String> russianCities = new HashMap<String, String>()
    {{
        put("Архангельск", "Arkhangelsk ");
        put("Астрахань", "Astrakhan");
        put("Великий Новгород", "Velikiy Novgorod");
        put("Великий Устюг", "Velikiy Ustyug");
        put("Владивосток", "Vladivostok");
        put("Волгоград", "Volgograd");
        put("Воркута", "Vorkuta");
        put("Воронеж", "Voronezh");
        put("Грозный", "Grozny");
        put("Екатеринбург", "Ekaterinburg");
        put("Иркутск", "Irkutsk");
        put("Казань", "Kazan");
        put("Калининград", "Kaliningrad");
        put("Калуга", "Kaluga");
        put("Курган", "Kurgan");
        put("Курск", "Kursk");
        put("Магадан", "Magadan");
        put("Махачкала", "Makhachkala");
        put("Москва", "Moscow");
        put("Мурманск", "Murmansk");
        put("Муром", "Murom");
        put("Набережные Челны", "Naberezhnye Chelny");
        put("Нижний Новгород", "Nizhny Novgorod");
        put("Новороссийск", "Novorossiysk");
        put("Новосибирск", "Novosibirsk");
        put("Омск", "Omsk");
        put("Оренбург", "Orenburg");
        put("Пенза", "Penza");
        put("Пермь", "Perm");
        put("Петропавловск-Камчатский", "Petropavlovsk-Kamchatski");
        put("Псков", "Pskov");
        put("Ростов-на-Дону", "Rostov-on-Don");
        put("Рязань", "Ryazan");
        put("Салехард", "Salekhard");
        put("Самара", "Samara");
        put("Санкт-Петербург", "Saint Petersburg");
        put("Саратов", "Saratov");
        put("Смоленск", "Smolensk");
        put("Сочи", "Sochi");
        put("Ставрополь", "Stavropol");
        put("Сургут", "Surgut");
        put("Тверь", "Tver");
        put("Тобольск", "Tobolsk");
        put("Тольятти", "Toglyatti");
        put("Томск", "Tomsk");
        put("Тула", "Tula");
        put("Тюмень", "Tyumen");
        put("Уфа", "Ufa");
        put("Ханты-Мансийск", "Khanty-Mansyisk");
        put("Чебоксары", "Cheboksary");
        put("Челябинск", "Chelyabinsk");
        put("Якутск", "Yakutsk");
        put("Ярославль", "Yaroslavl");

    }};

    public TemplatesFragment() {
    }


    public static TemplatesFragment newInstance(String param1, String param2) {
        TemplatesFragment fragment = new TemplatesFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_templates, container, false);
        addText = view.findViewById(R.id.textAnimButton);
        ArrayList<Animation> letters = new ArrayList<>(MainActivity.DBHelper.getClosedCubes());
        displayWeather = view.findViewById(R.id.weatherButton);
        Spinner spinner = view.findViewById(R.id.spinnerCities);

        int n = russianCities.size();
        List<String> keysCities = new ArrayList<String>(n);
        for (String x : russianCities.keySet())
            keysCities.add(x);
        Collections.sort(keysCities);
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, keysCities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        addText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textTemplateDialog = new Dialog(getActivity());
                textTemplateDialog.setContentView(R.layout.add_text_template_dialog);
                saveTemplate = textTemplateDialog.findViewById(R.id.saveTemplateButton);
                etTemplateName = textTemplateDialog.findViewById(R.id.editTextCubeName);
                etTemplate = textTemplateDialog.findViewById(R.id.editTextTemplateText);
                saveTemplate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        newTemplateName = etTemplateName.getText().toString();
                        ArrayList<Image> templateImages = new ArrayList<>();
                        newTemplate = etTemplate.getText().toString();
                        for(int i = 0; i < newTemplate.length(); i++){
                            for(int j = 0; j < letters.size(); j++){
                                if((newTemplate.charAt(i)+"").equals(letters.get(j).getName().toLowerCase())){
                                    Log.i("ANIMATION", (newTemplate.charAt(i)+"")+" "+(letters.get(j).getName().toLowerCase())+" "+letters.get(j).getImages().size());
                                    templateImages.addAll(letters.get(j).getImages());
                                }
                            }
                        }
                        MainActivity.DBHelper.addCube(new Animation(-1, newTemplateName, new ArrayList<>(templateImages)));
                        Log.i("ANIMATION", templateImages.size()+"");
                        Toast.makeText(getContext(), "Ваша анимация сохранена в Мои кубы", Toast.LENGTH_SHORT).show();
                        textTemplateDialog.dismiss();
                    }
                });
                textTemplateDialog.show();

            }
        });
//        displayWeather.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CreateCubeFragment.sendToCube(russianCities.get(keysCities.get(spinner.getSelectedItemPosition())), CreateCubeFragment.urlForLocation, getContext());
//            }
//        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CreateCubeFragment.sendToCube(russianCities.get(keysCities.get(position)), CreateCubeFragment.urlForLocation, getContext());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }
}