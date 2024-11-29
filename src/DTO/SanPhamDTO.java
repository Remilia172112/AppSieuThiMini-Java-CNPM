package DTO;

public class SanPhamDTO {

    private int MSP;
    private String TEN;
    private String HINHANH;
    private int ML;
    private int TIENX;
    private int SL;
    private int MDV;
    private String MV;

    public SanPhamDTO() {

    }

    public SanPhamDTO(int mSP, String tEN, String hINHANH, int mL, int tIENX, int sL, int mDV, String mV) {
        MSP = mSP;
        TEN = tEN;
        HINHANH = hINHANH;
        ML = mL;
        TIENX = tIENX;
        SL = sL;
        MDV = mDV;
        MV = mV;
    }

    public int getMSP() {
        return MSP;
    }

    public void setMSP(int mSP) {
        MSP = mSP;
    }

    public String getTEN() {
        return TEN;
    }

    public void setTEN(String tEN) {
        TEN = tEN;
    }

    public String getHINHANH() {
        return HINHANH;
    }

    public void setHINHANH(String hINHANH) {
        HINHANH = hINHANH;
    }

    public int getML() {
        return ML;
    }

    public void setML(int mL) {
        ML = mL;
    }

    public int getTIENX() {
        return TIENX;
    }

    public void setTIENX(int tIENX) {
        TIENX = tIENX;
    }

    public int getSL() {
        return SL;
    }

    public void setSL(int sL) {
        SL = sL;
    }

    public int getMDV() {
        return MDV;
    }

    public void setMDV(int mDV) {
        MDV = mDV;
    }

    public String getMV() {
        return MV;
    }

    public void setMV(String mV) {
        MV = mV;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + MSP;
        result = prime * result + ((TEN == null) ? 0 : TEN.hashCode());
        result = prime * result + ((HINHANH == null) ? 0 : HINHANH.hashCode());
        result = prime * result + ML;
        result = prime * result + TIENX;
        result = prime * result + SL;
        result = prime * result + MDV;
        result = prime * result + ((MV == null) ? 0 : MV.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SanPhamDTO other = (SanPhamDTO) obj;
        if (MSP != other.MSP)
            return false;
        if (TEN == null) {
            if (other.TEN != null)
                return false;
        } else if (!TEN.equals(other.TEN))
            return false;
        if (HINHANH == null) {
            if (other.HINHANH != null)
                return false;
        } else if (!HINHANH.equals(other.HINHANH))
            return false;
        if (ML != other.ML)
            return false;
        if (TIENX != other.TIENX)
            return false;
        if (SL != other.SL)
            return false;
        if (MDV != other.MDV)
            return false;
        if (MV == null) {
            if (other.MV != null)
                return false;
        } else if (!MV.equals(other.MV))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SanPhamDTO [MSP=" + MSP + ", TEN=" + TEN + ", HINHANH=" + HINHANH + ", ML=" + ML + ", TIENX=" + TIENX
                + ", SL=" + SL + ", MDV=" + MDV + ", MV=" + MV + "]";
    }
    

}