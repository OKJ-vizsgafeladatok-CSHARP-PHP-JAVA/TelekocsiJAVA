import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Main {
	public static List<Autok> autokLista() {
		List<Autok> autokLista = new ArrayList<Autok>();
		try {
			List<String> sorok = Files.readAllLines(Paths.get("autok_utf.csv"));
			for (String sor : sorok.subList(1, sorok.size())) {
				String[] split = sor.split(";");
				Autok o = new Autok(split[0], split[1], split[2], split[3], Integer.parseInt(split[4]));
				autokLista.add(o);
			}
		} catch (Exception e) {
			System.out.println("Hiba a f�jl beolvas�sakor! " + e);
		}
		return autokLista;
	}

	public static List<Igenyek> igenyekLista() {
		List<Igenyek> igenyekLista = new ArrayList<Igenyek>();
		try {
			List<String> sorok = Files.readAllLines(Paths.get("igenyek.csv"));
			for (String sor : sorok.subList(1, sorok.size())) {
				String[] split = sor.split(";");
				Igenyek o = new Igenyek(split[0], split[1], split[2], Integer.parseInt(split[3]));
				igenyekLista.add(o);
			}
		} catch (Exception e) {
			System.out.println("Hiba a f�jl beolvas�sakor! " + e);
		}
		return igenyekLista;
	}

	public static void main(String[] args) throws IOException {
		List<Autok> autok = autokLista();
		List<Igenyek> igeny = igenyekLista();

		System.out.println("2. feladat: " + autok.size() + " autós hirdet fuvart!");

		HashMap<String, Integer> stat = new HashMap<String, Integer>();
		int osszesFerohely = 0;
		for (Autok a : autok) {
			if (a.getIndulas().equals("Budapest") && a.getCel().equals("Miskolc")) {
				osszesFerohely += a.getFerohely();
			}

			stat.merge(a.getIndulas() + "-" + a.getCel(), a.getFerohely(), Integer::sum);

		}

		String kulcs = "";
		int ertek = 0;
		int max = Integer.MIN_VALUE;
		for (Entry<String, Integer> s : stat.entrySet()) {
			if (s.getValue() > max) {
				max = s.getValue();
				kulcs = s.getKey();
				ertek = max;
			}
		}
		System.out.println("3. feladat: " + osszesFerohely + " férőhelyet hirdettek az autósok Budapestről Miskolcra.");
		System.out.println("4. feladat: A legtöbb férőhelyet (" + ertek + "-t) a " + kulcs
				+ " útvonalon ajánlották fel a hírdetők."); //ctrl+shift+f - rendezés
		
		//
        System.out.println("5. feladat: ");
        for (Igenyek i : igeny) {
            for (Autok a : autok) {
                if (i.getIndulas().equals(a.getIndulas()) && i.getCel().equals(a.getCel())
                        && i.getSzemelyek() <= a.getFerohely()) {
                    System.out.println("\t" + i.getAzonosito() + " => " + a.getRendszam());
                }
            }
        }
		System.out.println("6. feladat: ");
        String fajlba = "";
        for (Igenyek i : igeny) {
            boolean talalat = false;
            for (Autok a : autok) {
                if (i.getIndulas().equals(a.getIndulas()) && i.getCel().equals(a.getCel())
                        && i.getSzemelyek() <= a.getFerohely()) {
                    fajlba += "\t" + i.getAzonosito() + ": Rendszám: " + a.getRendszam() + ", Telefonszám: "
                            + a.getTelefonszam() + "\n";
                }
            }
            if (!talalat) {
                fajlba += "\t" + i.getAzonosito() + ": Sajnos nincs találat" + "\n";
            }
        }
        
        Files.write(Paths.get("utasuzenetek.txt"), fajlba.getBytes());
	}

}
