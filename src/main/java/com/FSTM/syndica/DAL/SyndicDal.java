package com.FSTM.syndica.DAL;

import com.FSTM.syndica.Model.Syndic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SyndicDal {
    private database db;

    public SyndicDal() {
        this.db = new database();
    }

    public void deleteSyndic(String syndicId) {
        Connection connection = null;
        try {
            connection = database.getConnection();
            String deleteSql = "DELETE FROM syndic WHERE syndicId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setString(1, syndicId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Syndic getSyndic() {
        Connection connection = null;
        Syndic syndic = null;
        try {
            connection = database.getConnection();
            String selectSql = "SELECT * FROM Syndic WHERE id = 1";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String adresse = resultSet.getString("adresse");
                syndic = new Syndic(id, nom, prenom, adresse);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return syndic;
    }
}
