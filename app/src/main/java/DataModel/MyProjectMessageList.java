package DataModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.media.CamcorderProfile.get;

public class MyProjectMessageList {
    private HashMap<Integer, MyProjectMessage> msgList;

    private List<MyProjectMessage> mList;

    public HashMap<Integer, MyProjectMessage> getMsgList() {
        return msgList;
    }

    public MyProjectMessageList() {
        msgList = new HashMap<>();
        mList = new ArrayList<>();
    }

    public void addItem(MyProjectMessage message){
        msgList.put(message.getID(), message);
        mList.add(message);
    }

    public int getLastIndex(){

        if (mList.size() <= 0)
            return 0;
        else
            return mList.size() - 1;
    }
    public msgKind getKind(int index){
        return mList.get(index).getKind();
    }

    public int getSize(){return mList.size();}

    public MyProjectMessage getMessage(int index){
        return mList.get(index);
    }
}
