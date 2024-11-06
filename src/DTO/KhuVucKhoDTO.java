package DTO;

import java.util.Objects;

public class KhuVucKhoDTO {

    private int MKVS;
    private String TEN;
    private String GHICHU;

    public KhuVucKhoDTO() {
    }

    public KhuVucKhoDTO(int MKVS, String TEN, String GHICHU) {
        this.MKVS = MKVS;
        this.TEN = TEN;
        this.GHICHU = GHICHU;
    }

    public int getMakhuvuc() {
        return MKVS;
    }

    public void setMakhuvuc(int MKVS) {
        this.MKVS = MKVS;
    }

    public String getTenkhuvuc() {
        return TEN;
    }

    public void setTenkhuvuc(String TEN) {
        this.TEN = TEN;
    }

    public String getGhichu() {
        return GHICHU;
    }

    public void setGhichu(String GHICHU) {
        this.GHICHU = GHICHU;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.MKVS;
        hash = 97 * hash + Objects.hashCode(this.TEN);
        hash = 97 * hash + Objects.hashCode(this.GHICHU);
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
        final KhuVucKhoDTO other = (KhuVucKhoDTO) obj;
        if (this.getMakhuvuc() != other.getMakhuvuc()) {
            return false;
        }
        if (!Objects.equals(this.getTenkhuvuc(), other.getTenkhuvuc())) {
            return false;
        }
        return Objects.equals(this.getGhichu(), other.getGhichu());
    }

    @Override
    public String toString() {
        return "KhuVucSach{" + "Ma khu vuc sach = " + MKVS+ ", Ten khu vuc sach = " + TEN + ", Ghi chu = " + GHICHU + '}';
    }
    
}
