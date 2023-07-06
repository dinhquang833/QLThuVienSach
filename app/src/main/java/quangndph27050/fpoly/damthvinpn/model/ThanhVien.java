package quangndph27050.fpoly.damthvinpn.model;

public class ThanhVien {
    private int matv;
    private String hoten;
    private String namsinh;
    private String gioitinh;

    public ThanhVien() {
    }

    public ThanhVien(int matv, String hoten, String namsinh, String gioitinh) {
        this.matv = matv;
        this.hoten = hoten;
        this.namsinh = namsinh;
        this.gioitinh = gioitinh;
    }

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }
}
