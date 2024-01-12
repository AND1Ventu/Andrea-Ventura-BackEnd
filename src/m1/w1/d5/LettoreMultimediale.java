package m1.w1.d5;

import java.util.Scanner;

// Interfaccia per gli elementi riproducibili
interface Riproducibile {
    void play();
}

// Classe astratta per gli elementi multimediali
abstract class ElementoMultimediale {
    protected String titolo;

    public ElementoMultimediale(String titolo) {
        this.titolo = titolo;
    }

    public abstract void esegui();
}

// Classe concreta per le Immagini
class Immagine extends ElementoMultimediale {
    public int luminosita;

    public Immagine(String titolo, int luminosita) {
        super(titolo);
        this.luminosita = luminosita;
    }

    public void show() {
        System.out.println(titolo + ": " + "*".repeat(luminosita));
    }

    @Override
    public void esegui() {
        show();
    }
}

// Classe concreta per le Registrazioni Audio
class RegistrazioneAudio extends ElementoMultimediale implements Riproducibile {
    public int durata;
    public int volume;

    public RegistrazioneAudio(String titolo, int durata, int volume) {
        super(titolo);
        this.durata = durata;
        this.volume = volume;
    }

    public void abbassaVolume() {
        if (volume > 0) {
            volume--;
        }
    }

    public void alzaVolume() {
        volume++;
    }

    public void play() {
        for (int i = 0; i < volume; i++) {
            System.out.println(titolo + ": " + "!".repeat(volume));
        }
    }

    @Override
    public void esegui() {
        play();
    }
}

// Classe concreta per i Video
class Video extends RegistrazioneAudio {
    public int luminosita;

    public Video(String titolo, int durata, int volume, int luminosita) {
        super(titolo, durata, volume);
        this.luminosita = luminosita;
    }

    public void aumentaLuminosita() {
        luminosita++;
    }

    public void diminuisciLuminosita() {
        if (luminosita > 0) {
            luminosita--;
        }
    }

    @Override
    public void play() {
        for (int i = 0; i < durata; i++) {
            System.out.println(titolo + ": " + "!".repeat(volume) + "*".repeat(luminosita));
        }
    }
}


public class LettoreMultimediale {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creazione degli elementi multimediali
        ElementoMultimediale[] elementiMultimediali = new ElementoMultimediale[5];
        for (int i = 0; i < 5; i++) {
            System.out.println("Inserisci i dettagli per l'elemento multimediale " + (i + 1) + ":");
            System.out.print("Titolo: ");
            String titolo = scanner.next();
            System.out.print("1. Immagine, 2. Registrazione Audio, 3. Video: ");
            int sceltaTipo = scanner.nextInt();

            switch (sceltaTipo) {
                case 1:
                    System.out.print("Luminosita: ");
                    int luminosita = scanner.nextInt();
                    elementiMultimediali[i] = new Immagine(titolo, luminosita);
                    break;
                case 2:
                    System.out.print("Durata: ");
                    int durata = scanner.nextInt();
                    System.out.print("Volume: ");
                    int volume = scanner.nextInt();
                    elementiMultimediali[i] = new RegistrazioneAudio(titolo, durata, volume);
                    break;
                case 3:
                    System.out.print("Durata: ");
                    durata = scanner.nextInt();
                    System.out.print("Volume: ");
                    volume = scanner.nextInt();
                    System.out.print("Luminosita: ");
                    int luminositaVideo = scanner.nextInt();
                    elementiMultimediali[i] = new Video(titolo, durata, volume, luminositaVideo);
                    break;
                default:
                    System.out.println("Scelta non valida.");
                    i--;
            }
        }

        // Esecuzione degli elementi multimediali
        int scelta;
        do {
            System.out.println("\nScegli l'elemento multimediale da eseguire (1-5, 0 per uscire): ");
            scelta = scanner.nextInt();

            if (scelta >= 1 && scelta <= 5) {
                elementiMultimediali[scelta - 1].esegui();
            } else if (scelta != 0) {
                System.out.println("Scelta non valida.");
            }

        } while (scelta != 0);

        scanner.close();
    }
}

