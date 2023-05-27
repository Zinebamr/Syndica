package com.FSTM.syndica.DAL;

import com.FSTM.syndica.Model.Syndic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SyndicSal {
    private database db;

    public SyndicSal() {
        this.db = new database();
    }

    public void createSyndic(Syndic syndic) {
        Connection connection = null;
        try {
            connection = database.getConnection();
            String insertSql = "INSERT INTO syndic (syndicId, userName, passWord) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, syndic.getSyndicId());
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
            String updateSql = "UPDATE syndic SET userName = ?, passWord = ? WHERE syndicId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, syndic.getUserName());
            preparedStatement.setString(2, syndic.getPassWord());
            preparedStatement.setString(3, syndic.getSyndicId());
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
            String selectSql = "SELECT syndicId, userName, passWord FROM syndic WHERE userName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String syndicId = resultSet.getString("syndicId");
                String retrievedUserName = resultSet.getString("userName");
                String passWord = resultSet.getString("passWord");

                syndic = new Syndic(syndicId, retrievedUserName, passWord);
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
