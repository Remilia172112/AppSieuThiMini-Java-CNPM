package DTO;

import java.sql.Timestamp;
// import java.util.ArrayList;

public class HoaDonDTO extends PhieuDTO {
    private int MKH;
    private int DIEMTICHLUY;

    public HoaDonDTO(int MKH) {
        this.MKH = MKH;
    }

    public HoaDonDTO(int MKH, int MP, int MNV, Timestamp TG, long TIENX, int TT, int DIEMTICHLUY) {
        super(MP, MNV, TG, TIENX, TT);
        this.MKH = MKH;
        this.DIEMTICHLUY = DIEMTICHLUY;
    }

    public int getMKH() {
        return MKH;
    }

    public void setMKH(int MKH) {
        this.MKH = MKH;
    }
    
    public int getDIEMTICHLUY() {
        return DIEMTICHLUY;
    }

    public void setDIEMTICHLUY(int dIEMTICHLUY) {
        DIEMTICHLUY = dIEMTICHLUY;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + MKH;
        result = prime * result + DIEMTICHLUY;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        HoaDonDTO other = (HoaDonDTO) obj;
        if (MKH != other.MKH)
            return false;
        if (DIEMTICHLUY != other.DIEMTICHLUY)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "HoaDonDTO [MKH=" + MKH + ", DIEMTICHLUY=" + DIEMTICHLUY + "]";
    }
    
}
