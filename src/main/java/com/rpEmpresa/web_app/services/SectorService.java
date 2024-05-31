package com.rpEmpresa.web_app.services;


import com.rpEmpresa.web_app.database.PgConnection;
import com.rpEmpresa.web_app.entity.Sector;
import com.rpEmpresa.web_app.services.dto.CreateSectorDto;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class SectorService {

    private  final PgConnection db;

    public SectorService(PgConnection connection) {
        this.db = connection;
    }


    public void createSector(CreateSectorDto dto)  throws  Exception{

            var connection = this.db.getConnection();

            PreparedStatement smt = connection.prepareStatement(
                    "INSERT INTO sector (name,description) VALUES (?, ?)"
            );

            Sector st = new Sector();

            st.setName(dto.getName());
            st.setDescription(dto.getDescription());

            smt.setString(1, st.getName());
            smt.setString(2, st.getDescription());

            smt.executeUpdate();

            smt.close();
            connection.close();

    }

    public Sector findById(Long id)  throws  Exception{
        String query = "SELECT * FROM sector WHERE id = ?";

        var connection = this.db.getConnection();

        PreparedStatement smt = connection.prepareStatement(query);

        smt.setLong(1, id);

        ResultSet rs = smt.executeQuery();

        Sector sector = new Sector();

        while (rs.next()) {

            sector.setId(rs.getLong("id"));
            sector.setName(rs.getString("name"));
            sector.setDescription(rs.getString("description"));
        }

        return sector.getId() == null ? null : sector;
    }

}
