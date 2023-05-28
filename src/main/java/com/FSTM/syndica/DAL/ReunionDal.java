package com.FSTM.syndica.DAL;

import com.FSTM.syndica.Model.Reunion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReunionDal {
    private database db;

    public ReunionDal() {
        this.db = new database();
    }

    public void createReunion(Reunion reunion) {
        try {
            Connection connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Reunion (id, date, titre, ordre, Syndic_id, decission_final, Corpropriete_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, reunion.id);
            statement.setDate(2, Date.valueOf(reunion.date));
            statement.setString(3, reunion.titre);
            statement.setString(4, reunion.ordre);
            statement.setInt(5, reunion.Syndic_id);
            statement.setString(6, reunion.decission_final);
            statement.setInt(7, reunion.Corpropriete_id);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateReunion(Reunion reunion) {
        try {
            Connection connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE Reunion SET date = ?, titre = ?, ordre = ?, Syndic_id = ?, decission_final = ?, Corpropriete_id = ? WHERE id = ?");
            statement.setDate(1,  java.sql.Date.valueOf(reunion.date));
            statement.setString(2, reunion.titre);
            statement.setString(3, reunion.ordre);
            statement.setInt(4, reunion.Syndic_id);
            statement.setString(5, reunion.decission_final);
            statement.setInt(6, reunion.Corpropriete_id);
            statement.setInt(7, reunion.id);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteReunion(int reunionId) {
        try {
            Connection connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Reunion WHERE id = ?");
            statement.setInt(1, reunionId);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Reunion selectReunion(int reunionId) {
        Reunion reunion = null;
        try {
            Connection connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Reunion WHERE id = ?");
            statement.setInt(1, reunionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                reunion = new Reunion();
                reunion.id = resultSet.getInt("id");
                reunion.date = resultSet.getDate("date").toLocalDate();
                reunion.titre = resultSet.getString("titre");
                reunion.ordre = resultSet.getString("ordre");
                reunion.Syndic_id = resultSet.getInt("Syndic_id");
                reunion.decission_final = resultSet.getString("decission_final");
                reunion.Corpropriete_id = resultSet.getInt("Corpropriete_id");
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reunion;
    }

    public List<Reunion> selectReunionsBySyndicId(int syndicId) {
        List<Reunion> reunions = new ArrayList<>();
        try {
            Connection connection = db.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Reunion WHERE Syndic_id = ?");
            statement.setInt(1, syndicId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Reunion reunion = new Reunion();
                reunion.id = resultSet.getInt("id");
                reunion.date = resultSet.getDate("date").toLocalDate();
                reunion.titre = resultSet.getString("titre");
                reunion.ordre = resultSet.getString("ordre");
                reunion.Syndic_id = resultSet.getInt("Syndic_id");
                reunion.decission_final = resultSet.getString("decission_final");
                reunion.Corpropriete_id = resultSet.getInt("Corpropriete_id");
                reunions.add(reunion);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reunions;
    }
}
