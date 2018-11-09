package fr.eseo.dis.godetgui.somanagerapp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "pseudojuries", primaryKeys = {"idPseudoJuries"})
public class PseudoJuries {

    @NonNull
    private int idPseudoJuries;

    private String loginPj;

    private String mdpPj;

    private String fonctionPj;

    private String emailPj;

    public PseudoJuries(@NonNull int idPseudoJuries, String loginPj, String mdpPj, String fonctionPj, String emailPj) {
        this.idPseudoJuries = idPseudoJuries;
        this.loginPj = loginPj;
        this.mdpPj = mdpPj;
        this.fonctionPj = fonctionPj;
        this.emailPj = emailPj;
    }

    @NonNull
    public int getIdPseudoJuries() {
        return idPseudoJuries;
    }

    public void setIdPseudoJuries(@NonNull int idPseudoJuries) {
        this.idPseudoJuries = idPseudoJuries;
    }

    public String getLoginPj() {
        return loginPj;
    }

    public void setLoginPj(String loginPj) {
        this.loginPj = loginPj;
    }

    public String getMdpPj() {
        return mdpPj;
    }

    public void setMdpPj(String mdpPj) {
        this.mdpPj = mdpPj;
    }

    public String getFonctionPj() {
        return fonctionPj;
    }

    public void setFonctionPj(String fonctionPj) {
        this.fonctionPj = fonctionPj;
    }

    public String getEmailPj() {
        return emailPj;
    }

    public void setEmailPj(String emailPj) {
        this.emailPj = emailPj;
    }
}
