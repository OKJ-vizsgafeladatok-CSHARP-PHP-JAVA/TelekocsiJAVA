
public class Igenyek {

	public String azonosito;
	public String indulas;
	public String cel;
	public int szemelyek;
	public String getAzonosito() {
		return azonosito;
	}
	public void setAzonosito(String azonosito) {
		this.azonosito = azonosito;
	}
	public String getIndulas() {
		return indulas;
	}
	public void setIndulas(String indulas) {
		this.indulas = indulas;
	}
	public String getCel() {
		return cel;
	}
	public void setCel(String cel) {
		this.cel = cel;
	}
	public int getSzemelyek() {
		return szemelyek;
	}
	public void setSzemelyek(int szemelyek) {
		this.szemelyek = szemelyek;
	}
	public Igenyek(String azonosito, String indulas, String cel, int szemelyek) {
		super();
		this.azonosito = azonosito;
		this.indulas = indulas;
		this.cel = cel;
		this.szemelyek = szemelyek;
	}
	@Override
	public String toString() {
		return "Igenyek [azonosito=" + azonosito + ", indulas=" + indulas + ", cel=" + cel + ", szemelyek=" + szemelyek
				+ "]";
	}
	
	
}
