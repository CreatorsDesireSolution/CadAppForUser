package com.example.cadappforuser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.util.Pair;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cadappforuser.SqliteDatabase.MyTable;
import com.example.cadappforuser.SqliteDatabase.dbOperation;
import com.example.cadappforuser.model.CheckBoxModel;
import com.example.cadappforuser.model.OrderSummaryModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    ArrayList<CheckBoxModel> checkBoxModels;
    private HashMap<String, List<String>> expandableListDetail;
    SparseBooleanArray mSelections = new SparseBooleanArray();

    boolean checkAll_flag = false;
    boolean checkItem_flag = false;

    int checked =0;



//    public Set<Pair<Long, Long>> getCheckedItems() {
//        return mCheckedItems;
//    }


    public ExpandableListAdapter(Context context, List<String> expandableListTitle,
                                 ArrayList<CheckBoxModel> checkBoxModels,HashMap<String, List<String>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
        this.checkBoxModels = checkBoxModels;

        initializeDB();

    }



    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);



        final CheckBox chkbox = (CheckBox) convertView
                .findViewById(R.id.check);

        chkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checked = 1;
                    updateTable(checked);
                    Toast.makeText(context, "Saved '"+ checked + "' in DB", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(context, "Check Box Select....", Toast.LENGTH_SHORT).show();
                }else{ checked = 0;
                }
            }
        });

        checkBoxModels.add(new CheckBoxModel(checked));





        expandedListTextView.setText(expandedListText);
        return convertView;
    }



    @Override
    public int getChildrenCount(int listPosition) {

        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return false;
    }


    public  void initializeDB(){
        MyTable user = new MyTable();
        String[] tableCreateArray = { user.getDatabaseCreateQuery() };
        dbOperation operation = new dbOperation(context,tableCreateArray);
        operation.open();
        operation.close();
    }

    /*** SAVE THE DATA IN DB - GIVE FILENAME AND DATA ***/
    public  void saveData(int data){
        dbOperation operationObj = new dbOperation(context);
        operationObj.open();
        MyTable Fields = new MyTable();
        ContentValues initialValues = new ContentValues();
        initialValues.put(Fields.getScore(), data);
        operationObj.insertTableData(Fields.getTableName(),initialValues);
        operationObj.close();
    }

    /*** GET THE DATA FROM DB ,PARAMS - FILENAME -> GET THE DATA ***/
    public String getData(int id){
        String _data = "";
        dbOperation operationObj = new dbOperation(context);
        operationObj.open();
        MyTable fields = new MyTable();
        String  condition2 = fields.getID() + " ='" + id + "'";
        String[] dbFields4 = {fields.getScore()};
        Cursor cursor2 =  operationObj.getTableRow(fields.getTableName(),dbFields4,condition2,fields.getID() + " ASC ","1");
        if(cursor2.getCount() > 0)
        {
            cursor2.moveToFirst();
            do{
                _data = cursor2.getString(0);
            }while(cursor2.moveToNext());
        }else{
            _data = "error";
        }
        cursor2.close();
        cursor2.deactivate();
        operationObj.close();
        return _data;
    }

    /*** SAVE OR UPDATE DB -> GIVE THE FILENAME AND DATA ***/
    public void updateTable(int updt_data){
        dbOperation operationObj = new dbOperation(context);
        operationObj.open();
        MyTable Fields = new MyTable();
        //check for the value to update if no value then insert.
        String file_ = getData(1);
        if(file_.equals("error")){
            saveData(updt_data);
        }else{
            String  condition = Fields.getID() +" = '1'";
            ContentValues initialValues = new ContentValues();
            initialValues.put(Fields.getScore(), updt_data);
            operationObj.updateTable(Fields.getTableName(),initialValues,condition);
        }
        operationObj.close();
    }
}
