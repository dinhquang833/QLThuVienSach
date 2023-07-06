package quangndph27050.fpoly.damthvinpn.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import quangndph27050.fpoly.damthvinpn.R;
import quangndph27050.fpoly.damthvinpn.dao.ThanhVienDAO;
import quangndph27050.fpoly.damthvinpn.model.ThanhVien;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder>{

    private Context context;
    private ArrayList<ThanhVien> list;
    private ThanhVienDAO thanhVienDAO;

    public ThanhVienAdapter(Context context, ArrayList<ThanhVien> list, ThanhVienDAO thanhVienDAO) {
        this.context = context;
        this.list = list;
        this.thanhVienDAO = thanhVienDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_thanhvien,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaTV.setText("Mã TV: " + list.get(position).getMatv());
        holder.txtHoTen.setText("Họ Tên: " + list.get(position).getHoten());
        holder.txtNamSinh.setText("Năm Sinh: " + list.get(position).getNamsinh());
        holder.txtGioiTinh.setText("Giới tính: " + list.get(position).getGioitinh());

        if (holder.txtGioiTinh.equals("Nam")){
            holder.txtGioiTinh.setTextColor(Color.BLUE);
        }else {
            holder.txtGioiTinh.setTextColor(Color.YELLOW);
        }

        holder.ivChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChiTietTT(list.get(holder.getAdapterPosition()));
            }
        });

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogCapNhatTT(list.get(holder.getAdapterPosition()));
            }
        });

        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = thanhVienDAO.xoaThongTinTV(list.get(holder.getAdapterPosition()).getMatv());
                switch (check){
                    case 1:
                        Toast.makeText(context, "Xóa thành viên thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(context, "Thành viên tồn tại phiếu mượn, không được phép xóa", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
//        holder.parentThanhVien.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDialogThanhVien(holder.getAdapterPosition());
//            }
//        });
    }

//    private void showDialogThanhVien(int adapterPosition) {
//        ThanhVien thanhVien = list.get(adapterPosition);
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        View view = LayoutInflater.from(context).inflate(R.layout.dialog_chitiet_thanhvien,null);
//        TextView txtMaTV = view.findViewById(R.id.txtMaTV);
//        TextView txtHoTen = view.findViewById(R.id.txtHoTen);
//        TextView txtNamSinh = view.findViewById(R.id.txtNamSinh);
//        TextView txtGioiTinh = view.findViewById(R.id.txtGioiTinh);
//
//        builder.setTitle("Chi tiết");
//        txtMaTV.setText(thanhVien.getMatv());
//        txtHoTen.setText(thanhVien.getHoten());
//        txtNamSinh.setText(thanhVien.getNamsinh());
//        txtGioiTinh.setText(thanhVien.getGioitinh());
//
//        builder.setView(view);
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaTV,txtHoTen,txtNamSinh,txtGioiTinh;
        ImageView ivEdit, ivDel, ivChiTiet;
        CardView parentThanhVien;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMaTV = itemView.findViewById(R.id.txtMaTV);
            txtHoTen = itemView.findViewById(R.id.txtHoTen);
            txtNamSinh = itemView.findViewById(R.id.txtNamSinh);
            txtGioiTinh = itemView.findViewById(R.id.txtGioiTinh);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDel = itemView.findViewById(R.id.ivDel);
            parentThanhVien = itemView.findViewById(R.id.parentThanhVien);
            ivChiTiet = itemView.findViewById(R.id.ivChiTiet);
        }
    }
    private void showChiTietTT(ThanhVien thanhVien){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_chitiet_thanhvien, null);
        builder.setView(view);

        EditText edtHoTen = view.findViewById(R.id.edtHoTen);
        EditText edtNamSinh = view.findViewById(R.id.edtNamSinh);
        EditText edtGioiTinh = view.findViewById(R.id.edtGioiTinh);

        edtHoTen.setText(thanhVien.getHoten());
        edtNamSinh.setText(thanhVien.getNamsinh());
        edtGioiTinh.setText(thanhVien.getGioitinh());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showDialogCapNhatTT(ThanhVien thanhVien){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_chinhsua_thanhvien,null);
        builder.setView(view);

        TextView txtMaTV = view.findViewById(R.id.txtMaTV);
        EditText edtHoTen = view.findViewById(R.id.edtHoTen);
        EditText edtNamSinh = view.findViewById(R.id.edtNamSinh);
        EditText edtGioiTinh = view.findViewById(R.id.edtGioiTinh);

        txtMaTV.setText("Mã TV: "+thanhVien.getMatv());
        edtHoTen.setText(thanhVien.getHoten());
        edtNamSinh.setText(thanhVien.getNamsinh());
        edtGioiTinh.setText(thanhVien.getGioitinh());

        builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String hoten = edtHoTen.getText().toString();
                String namsinh = edtNamSinh.getText().toString();
                String gioitinh = edtGioiTinh.getText().toString();
                int id = thanhVien.getMatv();

                boolean check = thanhVienDAO.capNhatThongTinTV(id, hoten, namsinh, gioitinh);
                if (check){
                    Toast.makeText(context, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                }else {
                    Toast.makeText(context, "Cập nhật thông tin không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void loadData(){
        list.clear();
        list = thanhVienDAO.getDSThanhVien();
        notifyDataSetChanged();
    }
}
