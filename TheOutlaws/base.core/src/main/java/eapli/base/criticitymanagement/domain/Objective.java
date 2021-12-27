/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.criticitymanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Time;
import java.time.LocalTime;

/**
 *
 * @author raulcoelho
 */
@Embeddable
public class Objective implements ValueObject {

    private static final long serialVersionUID = 1L;

    private LocalTime tempoMedAprovacao;

    private LocalTime tempoMaxAprovacao;

    private LocalTime tempoMedResolucao;

    private LocalTime tempoMaxResolucao;

    public Objective(LocalTime tempoMedAprovacao, LocalTime tempoMaxAprovacao, LocalTime tempoMedResolucao, LocalTime tempoMaxResolucao) {
        Preconditions.ensure(tempoMedAprovacao.isAfter(LocalTime.of(0, 0, 0)) && tempoMaxAprovacao.isAfter(LocalTime.of(0, 0, 0)) && tempoMedResolucao.isAfter(LocalTime.of(0, 0, 0)) && tempoMaxResolucao.isAfter(LocalTime.of(0, 0, 0)));

        this.tempoMedAprovacao = tempoMedAprovacao;
        this.tempoMaxAprovacao = tempoMaxAprovacao;
        this.tempoMedResolucao = tempoMedResolucao;
        this.tempoMaxResolucao = tempoMaxResolucao;
    }

    protected Objective() {
        tempoMedAprovacao = tempoMaxAprovacao = tempoMedResolucao = tempoMaxResolucao = LocalTime.of(0, 0, 0);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Objective)) {
            return false;
        }

        final Objective that = (Objective) o;
        return tempoMedAprovacao == that.tempoMedAprovacao && tempoMaxAprovacao == that.tempoMaxAprovacao
                && tempoMedResolucao == that.tempoMedResolucao && tempoMaxResolucao == that.tempoMaxResolucao;
    }

    public void changeAverageApprovalTime(LocalTime tempoMedAprovacao) {
        this.tempoMedAprovacao = tempoMedAprovacao;
    }

    public void changeMaxApprovalTime(LocalTime tempoMaxAprovacao) {
        this.tempoMaxAprovacao = tempoMaxAprovacao;
    }

    public void changeAverageResolutionTime(LocalTime tempoMedResolucao) {
        this.tempoMedResolucao = tempoMedResolucao;
    }

    public void changeMaxResolutionTime(LocalTime tempoMaxResolucao) {
        this.tempoMaxResolucao = tempoMaxResolucao;
    }

    public LocalTime tempoMaxResolucao() {
        return tempoMaxResolucao;
    }

    public LocalTime tempoMaxAprovacao() {
        return tempoMaxAprovacao;
    }
    
    public LocalTime tempoMedResolucao() {
        return tempoMedResolucao;
    }
    
    public LocalTime tempoMedAprovacao() {
        return tempoMedAprovacao;
    }

    @Override
    public int hashCode() {
        return new HashCoder().with(tempoMedAprovacao).with(tempoMaxAprovacao).with(tempoMedResolucao).with(tempoMaxResolucao).code();
    }

    @Override
    public String toString() {
        return String.format("(tempoMedAprovacao: %s, tempoMaxAprovacao: %s, tempoMedResolucao: %s, tempoMaxResolucao: %s)", tempoMedAprovacao, tempoMaxAprovacao, tempoMedResolucao, tempoMaxResolucao);
    }

}
