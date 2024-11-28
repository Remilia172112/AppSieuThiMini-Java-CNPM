package DTO;



public class ChiTietHoaDonDTO extends ChiTietPhieuDTO{
    private String MKM;

    public ChiTietHoaDonDTO(String MKM) {
        this.MKM = MKM;
    }

    public ChiTietHoaDonDTO(int MP, int MSP, int SL, int TIENXUAT, String MKM) {
        super(MP, MSP, SL, TIENXUAT);
        this.MKM = MKM;
    }

    public String getMKM() {
        return MKM;
    }

    public void setMKM(String MKM) {
        this.MKM = MKM;
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((MKM == null) ? 0 : MKM.hashCode());
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
        ChiTietHoaDonDTO other = (ChiTietHoaDonDTO) obj;
        if (MKM == null) {
            if (other.MKM != null)
                return false;
        } else if (!MKM.equals(other.MKM))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ChiTietPhieuXuatDTO [MKM=" + MKM + "]";
    }

} 
