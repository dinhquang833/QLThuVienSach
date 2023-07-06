package quangndph27050.fpoly.damthvinpn.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import quangndph27050.fpoly.damthvinpn.R;
import quangndph27050.fpoly.damthvinpn.model.Sach;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Sach> list;

    public SachAdapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_recycler_sach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaSach.setText("Mã Sách: "+list.get(position).getMasach());
        holder.txtTenSach.setText("Tên Sách: "+list.get(position).getTensach());
        holder.txtGiaThue.setText("Giá Thuê: "+list.get(position).getGiathue());
        holder.txtMaLoai.setText("Mã Loại: "+list.get(position).getMaloai());
        holder.txtTenLoai.setText("Tên Loại: "+list.get(position).getTenloai());

//        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showDialog(list.get(holder.getAdapterPosition()));
//            }
//        });
//        holder.ivDel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int check = sachDAO.xoaSach(list.get(holder.getAdapterPosition()).getMasach());
//                switch (check){
//                    case 1:
//                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
//                        loadData();
//                        break;
//                    case 0:
//                        Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
//                        break;
//                    case -1:
//                        Toast.makeText(context, "Không xóa được sách này vì sách có trong phiếu mượn", Toast.LENGTH_SHORT).show();
//                        break;
//                    default:
//                        break;
//
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaSach,txtTenSach,txtGiaThue,txtMaLoai,txtTenLoai;
        ImageView ivEdit,ivDel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMaSach = itemView.findViewById(R.id.txtMaSach);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtGiaThue = itemView.findViewById(R.id.txtGiaThue);
            txtMaLoai = itemView.findViewById(R.id.txtMaLoai);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDel = itemView.findViewById(R.id.ivDel);
        }
    }
}
