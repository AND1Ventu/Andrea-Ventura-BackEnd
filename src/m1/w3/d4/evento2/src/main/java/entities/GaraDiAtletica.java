package entities;

import org.hibernate.mapping.Set;

import javax.persistence.*;

@Entity
@Table(name = "gara_di_atletica")
public class GaraDiAtletica extends Evento{

    @ManyToMany(mappeBy = "")
    private Set<Persona> atleti;
    @ManyToOne
    @JoinColumn(name = "persona_fk")
    private Persona vincitore;

    public GaraDiAtletica(Set<Persona> atleti, Persona vincitore) {
        this.atleti = atleti;
        this.vincitore = vincitore;
    }

    public GaraDiAtletica() {
    }

    public Set<Persona> getAtleti() {
        return atleti;
    }

    public void setAtleti(Set<Persona> atleti) {
        this.atleti = atleti;
    }

    public Persona getVincitore() {
        return vincitore;
    }

    public void setVincitore(Persona vincitore) {
        this.vincitore = vincitore;
    }
}
