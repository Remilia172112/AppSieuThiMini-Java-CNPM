package DTO;



public class ChiTietHoaDonDTO extends ChiTietPhieuDTO{
    private int MKM;

    public ChiTietHoaDonDTO(int MKM) {
        this.MKM = MKM;
    }

    public ChiTietHoaDonDTO(int MP, int MSP, int SL, int TIENXUAT, int MKM) {
        super(MP, MSP, SL, TIENXUAT);
        this.MKM = MKM;
    }

    public int getMKM() {
        return MKM;
    }

    public void setMKM(int MKM) {
        this.MKM = MKM;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.MKM;
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
        final ChiTietHoaDonDTO other = (ChiTietHoaDonDTO) obj;
    
        return this.MKM == other.MKM;
    }

    @Override
    public String toString() {
        return "ChiTietPhieuXuatDTO [MKM=" + MKM + "]";
    }

} 
