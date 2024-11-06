package BUS;

import DAO.ChiTietHoaDonDAO;
// import DAO.SanPhamDAO;
import DAO.HoaDonDAO;
import DTO.ChiTietPhieuDTO;
import DTO.HoaDonDTO;
import DTO.SanPhamDTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class HoaDonBUS {

    private final HoaDonDAO phieuXuatDAO = HoaDonDAO.getInstance();

    private final ChiTietHoaDonDAO chiTietPhieuXuatDAO = ChiTietHoaDonDAO.getInstance();
    private ArrayList<HoaDonDTO> listPhieuXuat;

    NhanVienBUS nvBUS = new NhanVienBUS();
    KhachHangBUS khBUS = new KhachHangBUS();

    public HoaDonBUS() {
        this.listPhieuXuat = phieuXuatDAO.selectAll();
    }

    public ArrayList<HoaDonDTO> getAll() {
        this.listPhieuXuat = phieuXuatDAO.selectAll();
        return this.listPhieuXuat;
    }

    public ArrayList<HoaDonDTO> getAll(int mkh) {
        this.listPhieuXuat = phieuXuatDAO.selectByMKH(mkh+"");
        
        return this.listPhieuXuat;
    }

    public HoaDonDTO getSelect(int index) {
        return listPhieuXuat.get(index);
    }

    public void cancel(int px) {
        phieuXuatDAO.cancel(px);
    }

    public void remove(int px) {
        listPhieuXuat.remove(px);
    }

    public void update(HoaDonDTO px) {
        phieuXuatDAO.update(px);
        chiTietPhieuXuatDAO.updateSL(px.getMP()+"");
    }

    public void insert(HoaDonDTO px, ArrayList<ChiTietPhieuDTO> ct) {
        phieuXuatDAO.insert(px); //ghi phieu xuat vao sql
        chiTietPhieuXuatDAO.insert(ct); //goi updatesoluongTon cua sanphamDAO để chỉnh số lượng trong kho -> ghi váo sql
    }

    public void insertGH(HoaDonDTO px, ArrayList<ChiTietPhieuDTO> ct) {
        phieuXuatDAO.insert(px); //ghi phieu xuat vao sql
        chiTietPhieuXuatDAO.insertGH(ct); //goi updatesoluongTon cua sanphamDAO để chỉnh số lượng trong kho -> ghi váo sql
    }

    public ArrayList<ChiTietPhieuDTO> selectCTP(int maphieu) {
        return chiTietPhieuXuatDAO.selectAll(Integer.toString(maphieu));
    }

    public int getMPMAX() {
        ArrayList<HoaDonDTO> listPhieuXuat = phieuXuatDAO.selectAll();
        int s = 1;
        for (HoaDonDTO i : listPhieuXuat) {
            if(i.getMP() > s) s = i.getMP();
        }
        return s;
    }
    //moi them, nó tương tự insert nhưng có check
    // public boolean add(PhieuXuatDTO phieu, ArrayList<ChiTietPhieuDTO> ctPhieu, HashMap<Integer, ArrayList<SanPhamDTO>> chitietsanpham) {
    //     boolean check = phieuXuatDAO.insert(phieu) != 0;
    //     if (check) {
    //         check = chiTietPhieuXuatDAO.insert(ctPhieu) != 0;
    //     }
    //     return check;
    // }

    public ChiTietPhieuDTO findCT(ArrayList<ChiTietPhieuDTO> ctphieu, int masp) {
        ChiTietPhieuDTO p = null;
        int i = 0;
        while (i < ctphieu.size() && p == null) {
            if (ctphieu.get(i).getMSP() == masp) {
                p = ctphieu.get(i);
            } else {
                i++;
            }
        }
        return p;
    }

    public ArrayList<HoaDonDTO> fillerPhieuXuat(int type, String input, int makh, int manv, Date time_s, Date time_e, String price_minnn, String price_maxxx) {
        Long price_min = !price_minnn.equals("") ? Long.valueOf(price_minnn) : 0L;
        Long price_max = !price_maxxx.equals("") ? Long.valueOf(price_maxxx) : Long.MAX_VALUE;
        Timestamp time_start = new Timestamp(time_s.getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time_e.getTime());

        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Timestamp time_end = new Timestamp(calendar.getTimeInMillis());

        ArrayList<HoaDonDTO> result = new ArrayList<>();
        for (HoaDonDTO phieuXuat : getAll()) {
            boolean match = false;
            switch (type) {
                case 0 -> {
                    if (Integer.toString(phieuXuat.getMP()).contains(input)
                            || khBUS.getTenKhachHang(phieuXuat.getMKH()).toLowerCase().contains(input)
                            || nvBUS.getNameById(phieuXuat.getMNV()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 1 -> {
                    if (Integer.toString(phieuXuat.getMP()).contains(input)) {
                        match = true;
                    }
                }
                case 2 -> {
                    if (khBUS.getTenKhachHang(phieuXuat.getMKH()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 3 -> {
                    if (nvBUS.getNameById(phieuXuat.getMNV()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
            }

            if (match
                    && (manv == 0 || phieuXuat.getMNV() == manv) && (makh == 0 || phieuXuat.getMKH() == makh)
                    && (phieuXuat.getTG().compareTo(time_start) >= 0)
                    && (phieuXuat.getTG().compareTo(time_end) <= 0)
                    && phieuXuat.getTIEN() >= price_min
                    && phieuXuat.getTIEN() <= price_max) {
                result.add(phieuXuat);
            }
        }

        return result;
    }

    public String[] getArrMPX() {
        int size = listPhieuXuat.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = Integer.toString(listPhieuXuat.get(i).getMP());
        }
        return result;
    }

    public HoaDonDTO getByIndex(int index) {
        return this.listPhieuXuat.get(index);
    }

    public boolean checkSLPx(ArrayList <ChiTietPhieuDTO> listctpx) {
        SanPhamBUS spBus = new SanPhamBUS();
        ArrayList<SanPhamDTO> SP = new ArrayList<SanPhamDTO>();
        for(ChiTietPhieuDTO i : listctpx) SP.add(spBus.spDAO.selectById(i.getMSP() + ""));
        for (int i = 0; i < SP.size(); i++) {
            if(listctpx.get(i).getSL() > SP.get(i).getSL()){
                return false;
            }
        }
        return true;
    }

    public boolean checkSLPx(int maphieu) {
        return phieuXuatDAO.checkSLPx(maphieu);
    }

    public int cancelPhieuNhap(int maphieu) {
        return phieuXuatDAO.cancelPhieuXuat(maphieu);
    }

}
