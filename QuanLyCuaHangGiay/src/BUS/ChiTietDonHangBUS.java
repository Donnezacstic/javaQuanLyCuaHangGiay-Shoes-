/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChiTietDonHangDAO;
import DTO.ChiTietDonHangDTO;
import java.util.ArrayList;

/**
 *
 * @author HUUNHAN
 */
public class ChiTietDonHangBUS {
    public ArrayList<ChiTietDonHangDTO> ChiTietDonHangList;
    public ChiTietDonHangDAO ChiTietDonHangDAO;
    
    public ChiTietDonHangBUS(){
        ChiTietDonHangDAO = new ChiTietDonHangDAO();
        ChiTietDonHangList = new ArrayList<>();
        fetchAll();   
    }
    
    public ChiTietDonHangDTO getByMaDonHang(String MaDonHang){
        for (ChiTietDonHangDTO ChiTietDonHang : ChiTietDonHangList) {
            if (ChiTietDonHang.getMaDonHang().toLowerCase().equals(MaDonHang.toLowerCase())) {
                return ChiTietDonHang;
            }
        }
        return null;
    }
    public ArrayList<ChiTietDonHangDTO> getList(String MaDonHang){
        ArrayList<ChiTietDonHangDTO> list = new ArrayList<ChiTietDonHangDTO>();
        for (ChiTietDonHangDTO ChiTietDonHang : ChiTietDonHangList) {
            if (ChiTietDonHang.getMaDonHang().toLowerCase().equals(MaDonHang.toLowerCase())) {
                list.add(ChiTietDonHang);
            }
        }
        return list;
    }
    public boolean add(ChiTietDonHangDTO ChiTietDonHang){
        ChiTietDonHangDAO.add(ChiTietDonHang);
        ChiTietDonHangList = ChiTietDonHangDAO.fetchAll();
        return true;
    }
    
    public void  fetchAll(){
        ChiTietDonHangList = ChiTietDonHangDAO.fetchAll();
    }
}
