package BUS;

import DAO.ChucVuDAO;
import DTO.ChucVuDTO;
import java.util.ArrayList;

public class ChucVuBUS {

    private final ChucVuDAO ChucVuDAO = new ChucVuDAO();
    private ArrayList<ChucVuDTO> listChucVu;

    public ChucVuBUS() {
        this.listChucVu = ChucVuDAO.selectAll();
    }

    public ArrayList<ChucVuDTO> getAll() {
        return this.listChucVu;
    }

    public ChucVuDTO getByIndex(int index) {
        return this.listChucVu.get(index);
    }

    public boolean add(ChucVuDTO lh) {
        boolean check = ChucVuDAO.insert(lh) != 0;
        if (check) {
            this.listChucVu.add(lh);
        }
        return check;
    }

    public boolean update(ChucVuDTO lh) {
        boolean check = ChucVuDAO.update(lh) != 0;
        if (check) {
            this.listChucVu = ChucVuDAO.selectAll();
        }
        return check;
    }

    public boolean delete(ChucVuDTO nqdto) {
        boolean check = ChucVuDAO.delete(Integer.toString(nqdto.getMCV())) != 0;
        if (check) {
            this.listChucVu.remove(nqdto);
        }
        return check;
    }

    public String[] getArrTenCV() {
        int size = listChucVu.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listChucVu.get(i).getTENCV();
        }
        return result;
    }

    public int getIndexByMCV(int mancc) {
        int i = 0;
        int vitri = -1;
        while (i < this.listChucVu.size() && vitri == -1) {
            if (listChucVu.get(i).getMCV() == mancc) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }
}
