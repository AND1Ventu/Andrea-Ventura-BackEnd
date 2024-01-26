package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cognome;
    private String email;

    @Column(name = "data_nascita")
    private LocalDate dataNascita;

    @Enumerated(EnumType.STRING)
    private Sesso sesso;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Partecipazione> listaPartecipazioni;

    @OneToMany(mappedBy = "vincitore")
    private List<GaraDiAtletica> gareVinte;

    @ManyToMany
    @JoinTable(name = "persone_gare",
            joinColumns = @JoinColumn(name = "persona_fk"),
    inverseJoinColumns = @JoinColumn(name = "gare_fk"))
    private Set<GaraDiAtletica> gare = new HashSet<>;


    public List<GaraDiAtletica> getGareVinte() {
        return gareVinte;
    }

    public void setGareVinte(List<GaraDiAtletica> gareVinte) {
        this.gareVinte = gareVinte;
    }

    public Set<GaraDiAtletica> getGare() {
        return gare;
    }

    public void setGare(Set<GaraDiAtletica> gare) {
        this.gare = gare;
    }

    public Persona() {
    }

    public Persona(String nome, String cognome, String email, LocalDate dataNascita, Sesso sesso) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataNascita = dataNascita;
        this.sesso = sesso;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public Sesso getSesso() {
        return sesso;
    }

    public void setSesso(Sesso sesso) {
        this.sesso = sesso;
    }

    public List<Partecipazione> getListaPartecipazioni() {
        return listaPartecipazioni;
    }

    public void setListaPartecipazioni(List<Partecipazione> listaPartecipazioni) {
        this.listaPartecipazioni = listaPartecipazioni;
    }

    public void addPartecipazione(Partecipazione partecipazione) {
        if (listaPartecipazioni == null) {
            listaPartecipazioni = new ArrayList<>();
        }
        listaPartecipazioni.add(partecipazione);
        partecipazione.setPersona(this);
    }
}
