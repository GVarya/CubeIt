package com.example.cube;


import static java.lang.String.format;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class CreateCubeFragment extends Fragment {

    static final String urlForLeds = "http://192.168.1.175:80/cube?query1=";
    static final String urlForLocation = "http://192.168.1.175:80/location?region=";
    RadioButton checkedLayer;
    int currentImageId = 0;
    ArrayList<String> checkedLeds = new ArrayList<>();
    Button saveCube;
    Button addImage;
    RadioGroup rGroupLayers;
    Integer[] leds_ids = new Integer[]{R.id.I0, R.id.I1, R.id.I2, R.id.I3, R.id.I4, R.id.I5, R.id.I6, R.id.I7, R.id.I8, R.id.I9, R.id.I10, R.id.I11, R.id.I12, R.id.I13,
            R.id.I14, R.id.I15, R.id.I16, R.id.I17, R.id.I18, R.id.I19, R.id.I20, R.id.I21, R.id.I22, R.id.I23, R.id.I24, R.id.I25, R.id.I26, R.id.I27,
            R.id.I28, R.id.I29, R.id.I30, R.id.I31, R.id.I32, R.id.I33, R.id.I34, R.id.I35, R.id.I36, R.id.I37, R.id.I38, R.id.I39, R.id.I40, R.id.I41,
            R.id.I42, R.id.I43, R.id.I44, R.id.I45, R.id.I46, R.id.I47, R.id.I48, R.id.I49, R.id.I50, R.id.I51, R.id.I52, R.id.I53, R.id.I54, R.id.I55,
            R.id.I56, R.id.I57, R.id.I58, R.id.I59, R.id.I60, R.id.I61, R.id.I62, R.id.I63
    };
    String currentLayerStatus = "";
    RadioButton checkedRadioButton;
    boolean isChecked;
    CheckBox curLed;
    int numLayer;
    Dialog newCubeDialog;
    Button buttonSaveCube;
    EditText cubeNameEt;
    String newCubeName;
    Button moveRight;
    Button moveLeft;
    TextView currentImg;
    ArrayList<Image> cubeImages = new ArrayList<>();
    String ledsStatusHex;
    View view;


    public CreateCubeFragment(Animation cube) {
        cubeImages = new ArrayList<>(cube.getImages());

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_cube, container, false);
        saveCube = view.findViewById(R.id.save);
        addImage = view.findViewById(R.id.addImage);
        moveLeft = view.findViewById(R.id.button_left);
        moveRight = view.findViewById(R.id.button_right);
        currentImg = view.findViewById(R.id.current_image);
        rGroupLayers = (RadioGroup)view.findViewById(R.id.layers);
        checkedLayer = (RadioButton)rGroupLayers.findViewById(rGroupLayers.getCheckedRadioButtonId());

        for(int i = 0; i < 8; i++){
            checkedLeds.add("0000000000000000000000000000000000000000000000000000000000000000");
        }
        if(cubeImages.size() == 0){
            cubeImages.add(new Image(-1, "", stringLedsToStringHex(checkedLeds)));
        }
        setNewImage(0);
        rGroupLayers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                isChecked = checkedRadioButton.isChecked();
                if (isChecked)
                {
                    onLedSelected();
                    if(checkedLayer.getId() == R.id.l1){
                        checkedLeds.set(0, currentLayerStatus);
                    }
                    if(checkedLayer.getId() == R.id.l2){
                        checkedLeds.set(1, currentLayerStatus);

                    }
                    if(checkedLayer.getId() == R.id.l3){
                        checkedLeds.set(2, currentLayerStatus);

                    }
                    if(checkedLayer.getId() == R.id.l4){
                        checkedLeds.set(3, currentLayerStatus);

                    }
                    if(checkedLayer.getId() == R.id.l5){
                        checkedLeds.set(4, currentLayerStatus);

                    }
                    if(checkedLayer.getId() == R.id.l6){
                        checkedLeds.set(5, currentLayerStatus);

                    }
                    if(checkedLayer.getId() == R.id.l7){
                        checkedLeds.set(6, currentLayerStatus);
                    }
                    if(checkedLayer.getId() == R.id.l8){
                        checkedLeds.set(7, currentLayerStatus);

                    }

//                    Log.d("from Checked:", checkedLayer.getId() + "");
//                    Log.d("Checked:", checkedRadioButton.getId() + "");
                    Log.d("Checked:", checkedLeds + "");
                    checkedLayer = checkedRadioButton;
                    numLayer = -1;
                    if(checkedLayer.getId() == R.id.l1){
                        numLayer = 0;
                    }
                    if(checkedLayer.getId() == R.id.l2){
                        numLayer = 1;
                    }
                    if(checkedLayer.getId() == R.id.l3){
                        numLayer = 2;
                    }
                    if(checkedLayer.getId() == R.id.l4){
                        numLayer = 3;
                    }
                    if(checkedLayer.getId() == R.id.l5){
                        numLayer = 4;
                    }
                    if(checkedLayer.getId() == R.id.l6){
                        numLayer = 5;
                    }
                    if(checkedLayer.getId() == R.id.l7){
                        numLayer = 6;
                    }
                    if(checkedLayer.getId() == R.id.l8){
                        numLayer = 7;
                    }
                    if(numLayer >= 0){
                        if(!checkedLeds.get(numLayer).equals("")){
                            for(int i = 0; i < 64; i++){
                                curLed = view.findViewById(leds_ids[i]);
                                if (checkedLeds.get(numLayer).charAt(i) == '0') curLed.setChecked(false); // Выставляем точки при открытии нового слоя
                                else curLed.setChecked(true);
                            }
                        }
                    }

                }
            }
        });

        saveCube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewOn) {
                updateLastLayer();
                newCubeDialog = new Dialog(getActivity());
                newCubeDialog.setContentView(R.layout.add_cube_dialog);
                buttonSaveCube = newCubeDialog.findViewById(R.id.saveCubeButton);
                cubeNameEt = newCubeDialog.findViewById(R.id.editTextCubeName);
                buttonSaveCube.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("Checked:", checkedLeds + "");
                        newCubeName = cubeNameEt.getText().toString();
                        ledsStatusHex = stringLedsToStringHex(new ArrayList<>(checkedLeds));
                        Log.d("Int Leds", ledsStatusHex + "");
                        Log.d("AMOUNT OF IMGS", currentImageId+" "+cubeImages.size());
                        if(currentImageId == cubeImages.size() - 2){
                            cubeImages.add(new Image(-1, "image" + cubeImages.size(), ledsStatusHex));
                        }
                        else{
                            cubeImages.set(currentImageId, new Image(-1, "image" + cubeImages.size(), ledsStatusHex));
                        }
                        if (MainActivity.DBHelper.addCube(new Animation(-1, newCubeName, new ArrayList<Image>(cubeImages)))) {
                            Toast.makeText(view.getContext(), "Успешно сохранено", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(view.getContext(), "Не получилось сохранить", Toast.LENGTH_SHORT).show();
                        }
                        Log.d("AMOUNT OF IMGS", currentImageId+" "+cubeImages.size());
//                        cubeImages.clear();
//                        checkedLeds.clear();
//                        currentImageId = 0;
//                        for (int i = 0; i < 8; i++) {
//                            checkedLeds.add("0000000000000000000000000000000000000000000000000000000000000000");
//                        }
//                        cubeImages.add(new Image(-1, "", stringLedsToStringHex(checkedLeds)));
                        newCubeDialog.dismiss();
                    }
                });
                newCubeDialog.show();
            }
        });

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewAddImage) {
                updateLastLayer();
                cubeImages.set(currentImageId, new Image(-1, "image" + currentImageId, stringLedsToStringHex(new ArrayList<>(checkedLeds))));
                currentImageId = cubeImages.size();
                currentLayerStatus = "";
                checkedLeds.clear();
                for(int i = 0; i < 8; i++){
                    checkedLeds.add("0000000000000000000000000000000000000000000000000000000000000000");
                }
                cubeImages.add(new Image(-1, "image" + currentImageId, stringLedsToStringHex(new ArrayList<>(checkedLeds))));
                currentImg.setText(currentImageId + 1 + "");
                //onLedSelected();
                Log.i("NEW ONE CLEARED", stringLedsToStringHex(new ArrayList<>(checkedLeds)));
                for(int i = 0; i < 64; i++){
                    curLed = view.findViewById(leds_ids[i]);
                    if (checkedLeds.get(0).charAt(i) == '0') curLed.setChecked(false); // Выставляем точки нулевого слоя при открытии нового изображни
                    else curLed.setChecked(true);
                    //onLedSelected();
                }
                RadioButton layerFirs = view.findViewById(R.id.l1);
                layerFirs.setChecked(true);
                //Log.i("NEW ONE CLEARED", stringLedsToStringHex(new ArrayList<>(checkedLeds)));

            }
        });
        moveRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //updateLastLayer();
                cubeImages.set(currentImageId, new Image(-1, "image" + currentImageId, stringLedsToStringHex(new ArrayList<>(checkedLeds))));
//                onLedSelected();
               // Log.i("MOVE RIGHT", stringLedsToStringHex(checkedLeds));
                if(currentImageId < cubeImages.size() - 1){
                    currentImageId += 1;
                    currentImg.setText(currentImageId + 1 +"");
                    setNewImage(currentImageId);
                    //Log.i("MOVE RIGHT AFT", stringLedsToStringHex(checkedLeds));

                }
            }
        });
        moveLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //updateLastLayer();
                cubeImages.set(currentImageId, new Image(-1, "image" + currentImageId, stringLedsToStringHex(new ArrayList<>(checkedLeds))));
//                onLedSelected();
                //Log.i("MOVE LEFT", stringLedsToStringHex(checkedLeds));
                if(currentImageId > 0){
                    currentImageId -= 1;
                    currentImg.setText(currentImageId + 1 + "");
                    setNewImage(currentImageId);
                    //Log.i("MOVE LEFT AFt", stringLedsToStringHex(checkedLeds));

                }
            }
        });

        for(int i = 0; i < 64; i++){
            CheckBox ledi = view.findViewById(leds_ids[i]);
            ledi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onLedSelected();
                }
            });
        }

        return view;
    }
    public void setNewImage(int newImageId){
        currentLayerStatus = "";
        checkedLeds.clear();
        //Log.i("CLAREd", stringLedsToStringHex(checkedLeds));
        String binLeds = stringHexToStringLeds(cubeImages.get(newImageId).getLeds_status());
        for(int i = 0; i < 8; i++){
            checkedLeds.add(binLeds.substring(i*64, i*64+64));
        }
        for(int i = 0; i < 64; i++){
            curLed = view.findViewById(leds_ids[i]);
            if (checkedLeds.get(0).charAt(i) == '0') curLed.setChecked(false); // Выставляем точки нулевого слоя при открытии нового изображни
            else curLed.setChecked(true);
        }
        RadioButton layerFirs = view.findViewById(R.id.l1);
        layerFirs.setChecked(true);
        onLedSelected();
    }
    public String stringHexToStringLeds(String hexString){
        String bin = "";
        for(int i = 0; i < 128; i++){
            String binElem = "";
            int elemOf4Leds = Integer.parseInt(hexString.charAt(i)+"", 16);
            for(int j = 0; j < 4; ++j, elemOf4Leds /=2 ) {
                switch (elemOf4Leds % 2) {
                    case 0:
                        binElem = "0" + binElem;
                        break;
                    case 1:
                        binElem = "1" + binElem;
                        break;
                }
            }
            //Log.i("String", binElem);
            bin += binElem;
        }

        Log.i("TAG", bin);
        return bin;
    }
    public String stringLedsToStringHex(ArrayList<String> stringLeds){
        int[] ledsInt = new int[128];
        for(int i = 0; i < stringLeds.size(); i++){
            ledsInt[16*i] = Integer.parseInt(stringLeds.get(i).substring(0, 4), 2);
            ledsInt[16*i+1] = Integer.parseInt(stringLeds.get(i).substring(4, 8), 2);
            ledsInt[16*i+2] = Integer.parseInt(stringLeds.get(i).substring(8, 12), 2);
            ledsInt[16*i+3] = Integer.parseInt(stringLeds.get(i).substring(12, 16), 2);
            ledsInt[16*i+4] = Integer.parseInt(stringLeds.get(i).substring(16, 20), 2);
            ledsInt[16*i+5] = Integer.parseInt(stringLeds.get(i).substring(20, 24), 2);
            ledsInt[16*i+6] = Integer.parseInt(stringLeds.get(i).substring(24, 28), 2);
            ledsInt[16*i+7] = Integer.parseInt(stringLeds.get(i).substring(28, 32), 2);
            ledsInt[16*i+8] = Integer.parseInt(stringLeds.get(i).substring(32, 36), 2);
            ledsInt[16*i+9] = Integer.parseInt(stringLeds.get(i).substring(36, 40), 2);
            ledsInt[16*i+10] = Integer.parseInt(stringLeds.get(i).substring(40, 44), 2);
            ledsInt[16*i+11] = Integer.parseInt(stringLeds.get(i).substring(44, 48), 2);
            ledsInt[16*i+12] = Integer.parseInt(stringLeds.get(i).substring(48, 52), 2);
            ledsInt[16*i+13] = Integer.parseInt(stringLeds.get(i).substring(52, 56), 2);
            ledsInt[16*i+14] = Integer.parseInt(stringLeds.get(i).substring(56, 60), 2);
            ledsInt[16*i+15] = Integer.parseInt(stringLeds.get(i).substring(60, 64), 2);
        }

        String cubeLedsHex = "";
        for(int i = 0; i < ledsInt.length; i++){
            cubeLedsHex += Integer.toHexString(ledsInt[i]);
        }
        return cubeLedsHex;
    }
    public static void sendToCube(String leds, String url, Context context){
        Log.i("RESULT TO SEND", leds);
        Data cube_data = new Data.Builder().putString("leds", leds).putString("url", url).build();
        OneTimeWorkRequest work = new OneTimeWorkRequest.Builder(SendCubesWorker.class)
                .setInputData(cube_data)
                .build();
        WorkManager.getInstance(context).enqueue(work);

    }


    public void updateLastLayer(){
        currentLayerStatus = "";
        for(int i = 0; i < 64; i++){
            curLed = view.findViewById(leds_ids[i]);
            if (curLed.isChecked()) currentLayerStatus += "1";
            else currentLayerStatus += "0";
        }
        if(checkedLayer.getId() == R.id.l1){
            checkedLeds.set(0, currentLayerStatus);
        }
        if(checkedLayer.getId() == R.id.l2){
            checkedLeds.set(1, currentLayerStatus);

        }
        if(checkedLayer.getId() == R.id.l3){
            checkedLeds.set(2, currentLayerStatus);

        }
        if(checkedLayer.getId() == R.id.l4){
            checkedLeds.set(3, currentLayerStatus);

        }
        if(checkedLayer.getId() == R.id.l5){
            checkedLeds.set(4, currentLayerStatus);

        }
        if(checkedLayer.getId() == R.id.l6){
            checkedLeds.set(5, currentLayerStatus);

        }
        if(checkedLayer.getId() == R.id.l7){
            checkedLeds.set(6, currentLayerStatus);
        }
        if(checkedLayer.getId() == R.id.l8){
            checkedLeds.set(7, currentLayerStatus);

        }
    }
    public void onLedSelected(){
        currentLayerStatus = "";
        for(int i = 0; i < 64; i++){
            curLed = view.findViewById(leds_ids[i]);
            if (curLed.isChecked()) currentLayerStatus += "1";
            else currentLayerStatus += "0";
        }
        updateLastLayer();
        //Log.d("SIZE LEDS", checkedLeds.size()+"");
        ledsStatusHex = stringLedsToStringHex(new ArrayList<>(checkedLeds));
        sendToCube(ledsStatusHex, urlForLeds, view.getContext());
    }

}