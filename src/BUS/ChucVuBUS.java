package BUS;

import DAO.ChucVuDAO;
import DTO.ChucVuDTO;
import GUI.Dialog.ChucVuDialog;
import GUI.Panel.ChucVu;
import helper.Validation;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ChucVuBUS implements ActionListener, DocumentListener  {
    
    public GUI.Panel.ChucVu cv;
    private JTextField textField;
    public final ChucVuDAO ChucVuDAO = new ChucVuDAO();
    public ArrayList<ChucVuDTO> listChucVu;

    public ChucVuBUS() {
        this.listChucVu = ChucVuDAO.selectAll();
    }

    public ArrayList<ChucVuDTO> getAll() {
        return this.listChucVu;
    }

    public ChucVuBUS(ChucVu cv) {
        this.cv = cv;
        this.listChucVu = ChucVuDAO.selectAll();
    }

    public ChucVuBUS(JTextField textField, ChucVu cv) {
        this.textField = textField;
        this.cv = cv;
        this.listChucVu = ChucVuDAO.selectAll();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String btn = e.getActionCommand();
        switch (btn) {
            case "THÊM" -> {
                new ChucVuDialog(this, cv.owner, true, "Thêm chức vụ", "create");
            }
            case "SỬA" -> {
                int index = cv.getRow();
                if (index < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn chức vụ cần sửa");
                } else {
                    new ChucVuDialog(this, cv.owner, true, "Sửa chức vụ", "update", cv.getChucVu());
                }
            }
            case "XÓA" -> {
                if (cv.getRow() < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn chức vụ cần xóa");
                } else {
                    deleteNv(cv.getChucVu());
                }
            }
            case "CHI TIẾT" -> {
                if (cv.getRow() < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn chức vụ cần xóa");
                } else {
                    new ChucVuDialog(this, cv.owner, true, "Xem chức vụ", "detail", cv.getChucVu());
                }
            }
            case "NHẬP EXCEL" -> {
                importExcel();
            }
            case "XUẤT EXCEL" -> {
                String[] header = new String[]{"MãNV", "Tên chức vụ", "Email chức vụ", "Số điên thoại", "Giới tính", "Ngày sinh"};
                exportExcel(listChucVu, header);
            }
        }
        cv.loadDataTalbe(listChucVu);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        String text = textField.getText();
        if (text.length() == 0) {
            cv.loadDataTalbe(listChucVu);
        } else {
            ArrayList<ChucVuDTO> listSearch = search(text);
            searchLoadTable(listSearch);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String text = textField.getText();
        if (text.length() == 0) {
            cv.loadDataTalbe(listChucVu);
        } else {
            ArrayList<ChucVuDTO> listSearch = search(text);
            searchLoadTable(listSearch);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
//        System.out.println("Text field changed: " + textField.getText());
    }

    public void insertNv(ChucVuDTO cv) {
        listChucVu.add(cv);
    }

    public void updateNv(int index, ChucVuDTO cv) {
        listChucVu.set(index, cv);
    }

    public int getIndex() {
        return cv.getRow();
    }

    public void deleteNv(ChucVuDTO cv) {
        ChucVuDAO.delete(cv.getMCV() + "");
        listChucVu.removeIf(n -> (n.getMCV() == cv.getMCV()));
        loadTable();
    }

    public void loadTable() {
        cv.loadDataTalbe(listChucVu);
    }

    public void searchLoadTable(ArrayList<ChucVuDTO> list) {
        cv.loadDataTalbe(list);
    }

    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void exportExcel(ArrayList<ChucVuDTO> list, String[] header) {
        try {
            if (!list.isEmpty()) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.showSaveDialog(cv.owner);
                File saveFile = jFileChooser.getSelectedFile();
                if (saveFile != null) {
                    saveFile = new File(saveFile.toString() + ".xlsx");
                    Workbook wb = new XSSFWorkbook();
                    Sheet sheet = wb.createSheet("Chức vụ");

                    writeHeader(header, sheet, 0);
                    int rowIndex = 1;
                    for (ChucVuDTO cv : list) {
                        Row row = sheet.createRow(rowIndex++);
                        writeChucVu(cv, row);
                    }
                    FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                    wb.write(out);
                    wb.close();
                    out.close();
                    openFile(saveFile.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ChucVuDTO> search(String text) {
        String luachon = (String) cv.search.cbxChoose.getSelectedItem();
        text = text.toLowerCase();
        ArrayList<ChucVuDTO> result = new ArrayList<>();
        switch (luachon) {
            case "Tất cả" -> {
                for (ChucVuDTO i : this.listChucVu) {
                    if (i.getTENCV().toLowerCase().contains(text) || (i.getMUCLUONG()+"").contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Tên" -> {
                for (ChucVuDTO i : this.listChucVu) {
                    if (i.getTENCV().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Mức lương" -> {
                for (ChucVuDTO i : this.listChucVu) {
                    if ((i.getMUCLUONG()+"").contains(text)) {
                        result.add(i);
                    }
                }
            }
            default ->
                throw new AssertionError();
        }

        return result;
    }

    private static void writeHeader(String[] list, Sheet sheet, int rowIndex) {
        CellStyle cellStyle = createStyleForHeader(sheet);
        Row row = sheet.createRow(rowIndex);
        Cell cell;
        for (int i = 0; i < list.length; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(list[i]);
            sheet.autoSizeColumn(i);
        }
    }

    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    private static void writeChucVu(ChucVuDTO cv, Row row) {
        CellStyle cellStyleFormatNumber = null;
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");

            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }
        Cell cell = row.createCell(0);
        cell.setCellValue(cv.getMCV());

        cell = row.createCell(1);
        cell.setCellValue(cv.getTENCV());

        cell = row.createCell(2);
        cell.setCellValue(cv.getMUCLUONG());
    }

    public void importExcel() {
        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelJTableImport = null;
        JFileChooser jf = new JFileChooser();
        int result = jf.showOpenDialog(null);
        jf.setDialogTitle("Open file");
        int k = 0;
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = jf.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelJTableImport = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);

                for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
                    int check = 1;
                    XSSFRow excelRow = excelSheet.getRow(row);
                    int id = ChucVuDAO.getAutoIncrement();
                    String tencv = excelRow.getCell(0).getStringCellValue();
                    int ml = Integer.parseInt(excelRow.getCell(1).getStringCellValue());
                    if (Validation.isEmpty(tencv)) {
                        check = 0;
                    }
                    if (check == 0) {
                        k += 1;
                    } else {
                        ChucVuDTO nvdto = new ChucVuDTO(id, tencv, ml);
                        ChucVuDAO.insert(nvdto);
                    }
                    JOptionPane.showMessageDialog(null, "Nhập thành công");
                }

            } catch (FileNotFoundException ex) {
                System.out.println("Lỗi đọc file");
            } catch (IOException ex) {
                System.out.println("Lỗi đọc file");
            }
        }
        if (k != 0) {
            JOptionPane.showMessageDialog(null, "Những dữ liệu không chuẩn không được thêm vào");
        }
    }

}
