package DataAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.amin.myfirstapp.R;

import org.w3c.dom.Text;

public class SimpleTextCell extends RecyclerView.ViewHolder {
    public TextView txtSimpleText;
    public TextView txtCreatedate;
    public SimpleTextCell(View itemView) {
        super(itemView);

        txtSimpleText = itemView.findViewById(R.id.txt_SimpleText);
        txtCreatedate = itemView.findViewById(R.id.txt_CreateDate);
    }
}
