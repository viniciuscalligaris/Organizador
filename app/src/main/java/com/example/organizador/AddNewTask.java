package com.example.organizador;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import com.example.organizador.Adapter.ToDoAdapter;
import com.example.organizador.Model.ToDoModel;
import com.example.organizador.Utils.DatabaseHandler;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class AddNewTask extends BottomSheetDialogFragment{
    public static final String TAG = "ActionBottomDialog";

    private EditText newTaskText;
    private Button newTaskSaveButton;
    private DatabaseHandler db;

    public static AddNewTask newInstance(){
        return new AddNewTask();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.DialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.new_task, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        newTaskText = view.findViewById(R.id.newTaskText);
        newTaskSaveButton = view.findViewById(R.id.newTaskSaveButton);

        db = new DatabaseHandler(getActivity());
        db.openDatabase();

            newTaskText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(charSequence.toString().isEmpty()){
                        newTaskSaveButton.setEnabled(false);
                        newTaskSaveButton.setTextColor(Color.GRAY);
                    }
                    else{
                        newTaskSaveButton.setEnabled(true);
                        newTaskSaveButton.setTextColor(ContextCompat.getColor(getContext(),R.color.colorPrimaryDark));
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });


            newTaskSaveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text = newTaskText.getText().toString();
                    ToDoModel task = new ToDoModel();
                    ToDoAdapter tasksAdapter;
                    task.setTask(text);
                    task.setStatus(0);
                    db.insertTask(task);
                    List<ToDoModel> taskList;
                    taskList = db.getAllTasks();
                    tasksAdapter = new ToDoAdapter();
                    tasksAdapter.setTasks(taskList);

                    dismiss();
                }
            });
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        Activity activity = getActivity();
        if(activity instanceof DialogCloseListener){
            ((DialogCloseListener) activity).handleDialogClose(dialog);
        }
    }
}
