package DTO;

public class SanPhamDTO {

    private int MSP;
    private String TEN;
    private String HINHANH;
    private String LOAI;
    private int TIENX;
    private int SL;
    private String DONVI;
    private String MV;

    public SanPhamDTO() {

    }

    public SanPhamDTO(int mSP, String tEN, String hINHANH, String lOAI, int tIENX, int sL, String dONVI, String mV) {
        MSP = mSP;
        TEN = tEN;
        HINHANH = hINHANH;
        LOAI = lOAI;
        TIENX = tIENX;
        SL = sL;
        DONVI = dONVI;
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

    public String getLOAI() {
        return LOAI;
    }

    public void setLOAI(String lOAI) {
        LOAI = lOAI;
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

    public String getDONVI() {
        return DONVI;
    }

    public void setDONVI(String dONVI) {
        DONVI = dONVI;
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
        result = prime * result + ((LOAI == null) ? 0 : LOAI.hashCode());
        result = prime * result + TIENX;
        result = prime * result + SL;
        result = prime * result + ((DONVI == null) ? 0 : DONVI.hashCode());
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
        if (LOAI == null) {
            if (other.LOAI != null)
                return false;
        } else if (!LOAI.equals(other.LOAI))
            return false;
        if (TIENX != other.TIENX)
            return false;
        if (SL != other.SL)
            return false;
        if (DONVI == null) {
            if (other.DONVI != null)
                return false;
        } else if (!DONVI.equals(other.DONVI))
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
        return "SanPhamDTO [MSP=" + MSP + ", TEN=" + TEN + ", HINHANH=" + HINHANH + ", LOAI=" + LOAI + ", TIENX="
                + TIENX + ", SL=" + SL + ", DONVI=" + DONVI + ", MV=" + MV + "]";
    }

}