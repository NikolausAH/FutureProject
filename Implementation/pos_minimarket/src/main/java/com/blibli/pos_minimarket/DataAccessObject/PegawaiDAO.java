package com.blibli.pos_minimarket.DataAccessObject;

import com.blibli.pos_minimarket.Model.Pegawai;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PegawaiDAO extends ConnectionSettings implements InterfaceDAO<Pegawai,Integer,String>{
    private static final String id = "id";
    private static final String password = "password";
    private static final String nama = "nama";
    private static final String idRole = "idrole";
    private static final String email = "email";
    private static final String role = "namarole";


    @Override
    public List<Pegawai> getAll() {
        List<Pegawai> pegawaiList = new ArrayList<>();
        String sql = "SELECT id,nama,email,namarole,pegawai.idrole,namarole FROM pegawai join role on (pegawai.idrole = role.idrole) ORDER BY id ;";
        try {
            this.makeConnection();
            Statement preparedStatement = this.connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            if (resultSet != null) {
                while (resultSet.next()) {
                    Pegawai pegawai = new Pegawai();
                    pegawai.setId(resultSet.getInt(id));
                    pegawai.setRole(resultSet.getString(role));
                    pegawai.setNama(resultSet.getString(nama));
                    pegawai.setIdRole(resultSet.getInt(idRole));
                    pegawai.setEmail(resultSet.getString(email));
                    pegawaiList.add(pegawai);
                }
                resultSet.close();
            }
            this.closeConnection();

        } catch (Exception EX) {
            System.out.println("Error PegawaiDAO getAll");
            System.out.println(EX.toString());
        }
        return pegawaiList;
    }

    @Override
    public List<Pegawai> search(String key) {
        return null;
    }

    @Override
    public void add(Pegawai pegawai) {

        String sql = "INSERT INTO pegawai (nama,idrole,email) VALUES (?,(SELECT idrole FROM role WHERE namarole LIKE ?),?);";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, pegawai.getNama());
            preparedStatement.setString(2, pegawai.getRole());
            preparedStatement.setString(3, pegawai.getEmail());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            this.closeConnection();
        } catch (Exception EX) {
            System.out.println("Error PegawaiDAO Add");
            System.out.println(EX.toString());
        }
    }

    @Override
    public void update(Pegawai t) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void softDelete(Integer integer) {

    }
}
