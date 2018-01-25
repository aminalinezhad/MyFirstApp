package DataAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amin.myfirstapp.R;

import DataModel.MyProjectMessage;
import DataModel.MyProjectMessageList;
import DataModel.msgKind;

import static DataModel.msgKind.SimpleText;

public class ProjectDataAdapter extends RecyclerView.Adapter {
    private MyProjectMessageList mlist;

    public ProjectDataAdapter(MyProjectMessageList list){
        mlist = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;

        switch (msgKind.valueOf(viewType)){
            case SimpleText:
                View mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_simple_text, null);
                viewHolder = new SimpleTextCell(mainView);
                break;
            case Image:
                break;
            case Audio:
                break;
            case Video:
                break;
            case none:
                throw new Error("Something's wrong!");
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyProjectMessage message = mlist.getMessage(position);

        switch (msgKind.valueOf(holder.getItemViewType())){
            case SimpleText:
                SimpleTextCell cell = (SimpleTextCell)holder;
                cell.txtSimpleText.setText(message.getText());
                cell.txtCreatedate.setText(message.getCreateDateStr());
                break;
            case Image:
                break;
            case Audio:
                break;
            case Video:
                break;
            case none:
                throw new Error("Something's wrong!");

        }
    }

    @Override
    public int getItemCount() {
        return mlist.getSize();
    }

    @Override
    public int getItemViewType(int position) {
        return msgKind.toInt(mlist.getKind(position));
    }

    public int addMessage(MyProjectMessage message){
        mlist.addItem(message);
        int position = mlist.getSize() - 1;
        notifyItemInserted(position);
        return(position);

    }

    public void addMessages(MyProjectMessageList list){
        mlist = list;
        notifyDataSetChanged();
    }

}
