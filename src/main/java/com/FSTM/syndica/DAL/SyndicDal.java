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

    public void createSyndic(Syndic syndic) {
        Connection connection = null;
        try {
            connection = database.getConnection();
            String insertSql = "INSERT INTO Syndic (syndicId, userName, passWord) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setInt(1, syndic.getSyndicId());
            preparedStatement.setString(2, syndic.getUserName());
            preparedStatement.setString(3, syndic.getPassWord());
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

    public void updateSyndic(Syndic syndic) {
        Connection connection = null;
        try {
            connection = database.getConnection();
            String updateSql = "UPDATE Syndic SET userName = ?, passWord = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, syndic.getUserName());
            preparedStatement.setString(2, syndic.getPassWord());
            preparedStatement.setInt(3, syndic.getSyndicId());
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

    public Syndic getSyndicByUserName(String userName) {
        Connection connection = null;
        Syndic syndic = null;
        try {
            connection = database.getConnection();
            String selectSql = "SELECT * FROM Syndic WHERE userName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String adresse = resultSet.getString("adresse");
                String username = resultSet.getString("userName");
                String passWord = resultSet.getString("passWord");

                syndic = new Syndic(id, nom, prenom, adresse, username, passWord);
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
