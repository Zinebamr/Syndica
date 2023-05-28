package com.FSTM.syndica.DAL;

import com.FSTM.syndica.Model.Corpropriete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CorproprieteDal {

    private database db;

    public CorproprieteDal() {
        this.db = new database();
    }

    public List<Corpropriete> getCorproprietesBySyndicId(int syndicId) {
        List<Corpropriete> corproprietes = new ArrayList<>();

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Corpropriete WHERE Syndic_id = ?")) {
            stmt.setInt(1, syndicId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Corpropriete corpropriete = mapResultSetToCorpropriete(rs);
                    corproprietes.add(corpropriete);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return corproprietes;
    }

    private Corpropriete mapResultSetToCorpropriete(ResultSet rs) throws SQLException {
        Corpropriete corpropriete = new Corpropriete();
        corpropriete.setId(rs.getInt("id"));
        corpropriete.setNom(rs.getString("nom"));
        corpropriete.setVille(rs.getString("ville"));
        corpropriete.setCodePostal(rs.getInt("code_postal"));
        corpropriete.setNbrUnite(rs.getInt("nbr_unite"));
        corpropriete.setMontantCotis(rs.getDouble("montant_cotis"));
        corpropriete.setMontantDispo(rs.getDouble("montant_dispo"));
        corpropriete.setAdresse(rs.getString("adresse"));
        corpropriete.setSyndicId(rs.getInt("Syndic_id"));
        corpropriete.setTypeCorproprieteId(rs.getInt("Type_corpropriete_id"));

        return corpropriete;
    }
}

