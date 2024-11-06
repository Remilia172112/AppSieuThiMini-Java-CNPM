package DTO;

import java.sql.Timestamp;
// import java.util.ArrayList;

public class HoaDonDTO extends PhieuDTO{
    private int MKH;

    public HoaDonDTO(int MKH) {
        this.MKH = MKH;
    }

    public HoaDonDTO(int MKH, int MP, int MNV, Timestamp TG, long TIENX, int TT) {
        super(MP, MNV, TG, TIENX, TT);
        this.MKH = MKH;
    }

    public int getMKH() {
        return MKH;
    }

    public void setMKH(int MKH) {
        this.MKH = MKH;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.MKH;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HoaDonDTO other = (HoaDonDTO) obj;
        return this.MKH == other.MKH;
    }

    @Override
    public String toString() {
        return "PhieuXuatDTO{" + "MKH=" + MKH + '}';
    }

    
}
