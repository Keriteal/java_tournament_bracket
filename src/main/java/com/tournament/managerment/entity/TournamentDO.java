package com.tournament.managerment.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tournament_record")

public class TournamentDO {
    public enum tournamentFormat {
        SINGLE, CONSOLATION
    }

    @Id
    private String tournamentId;
    @Column(nullable = false)
    private int userId;         //user_record表的外键  暂不设置
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private tournamentFormat format;
    @CreationTimestamp
    private Timestamp timeCreate;
    @UpdateTimestamp
    private Timestamp timeModified;

    public TournamentDO() {
    }

    public String getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(String tournamentId) {
        this.tournamentId = tournamentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public tournamentFormat getFormat() {
        return format;
    }

    public void setFormat(tournamentFormat format) {
        this.format = format;
    }

    public Timestamp getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Timestamp getTimeModified() {
        return timeModified;
    }

    public void setTimeModified(Timestamp timeModified) {
        this.timeModified = timeModified;
    }

    @Override
    public String toString() {
        return "TournamentDO{" +
                "tournamentId='" + tournamentId + '\'' +
                ", userId=" + userId +
                ", format=" + format +
                ", timeCreate=" + timeCreate +
                ", timeModified=" + timeModified +
                '}';
    }

    public static TournamentBuilder builder() {
        return new TournamentBuilder();
    }

    public static final class TournamentBuilder {
        private String tournamentId;
        private int userId; //user_record表的外键
        private tournamentFormat format;
        private Timestamp timeCreate;
        private Timestamp timeModified;

        private TournamentBuilder() {
        }

        public static TournamentBuilder aTournamentDO() {
            return new TournamentBuilder();
        }

        public TournamentBuilder withTournamentId(String tournamentId) {
            this.tournamentId = tournamentId;
            return this;
        }

        public TournamentBuilder withUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public TournamentBuilder withFormat(tournamentFormat format) {
            this.format = format;
            return this;
        }

        public TournamentBuilder withTimeCreate(Timestamp timeCreate) {
            this.timeCreate = timeCreate;
            return this;
        }

        public TournamentBuilder withTimeModified(Timestamp timeModified) {
            this.timeModified = timeModified;
            return this;
        }

        public TournamentDO build() {
            TournamentDO tournamentDO = new TournamentDO();
            tournamentDO.setTournamentId(tournamentId);
            tournamentDO.setUserId(userId);
            tournamentDO.setFormat(format);
            tournamentDO.setTimeCreate(timeCreate);
            tournamentDO.setTimeModified(timeModified);
            return tournamentDO;
        }
    }
}
